package dessert.controller.ajax.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.pvo.CartItemPVO;
import dessert.rvo.ResultVO;
import dessert.service.CommodityService;
import dessert.util.FormValidator;

@Controller("reservate")
public class ReservateController extends AjaxController {

	private static final long serialVersionUID = 1L;
	@Autowired
	CommodityService commodityService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		// TODO
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		HttpSession session=session();
		List<CartItemPVO> list=(List<CartItemPVO>)session.getAttribute(Configure.CART_LIST);
		Map<String, Object> map = new HashMap<String, Object>();
		if (list==null) {
			map.put(Configure.SUCCESS, Configure.FAIL);
			map.put(Configure.MESSAGE, "您的购物车为空");
		}else {
			ResultVO rVo=commodityService.reservate(list,(String)session.getAttribute(Configure.ID));
			map.put(Configure.SUCCESS, rVo.getSuccess());
			map.put(Configure.MESSAGE, rVo.getMessage());
		}
		session.removeAttribute(Configure.CART_LIST);
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
