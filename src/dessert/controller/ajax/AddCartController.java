package dessert.controller.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.CartItemPVO;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("addCart")
public class AddCartController extends AjaxController {

	/**
	 * 添加购物车
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.STORE_NAME, params.get(Configure.STORE_NAME));
		validator.put(Configure.P_NAME, params.get(Configure.P_NAME));
		validator.put(Configure.P_NUM, params.get(Configure.P_NUM));
		validator.put(Configure.LEFT_NUM, params.get(Configure.LEFT_NUM));
		validator.put(Configure.S_DATE, params.get(Configure.S_DATE));
		validator.put(Configure.PRICE, params.get(Configure.PRICE));
		validator.isRequired(Configure.STORE_NAME, ErrorCode.STORENAME_IS_EMPTY);
		validator.isRequired(Configure.P_NAME, ErrorCode.NAME_IS_EMPTY);
		validator.isRequired(Configure.P_NUM, ErrorCode.NUM_IS_EMPTY);
		validator.isRequired(Configure.LEFT_NUM, ErrorCode.NUM_IS_EMPTY);
		validator.isRequired(Configure.S_DATE, ErrorCode.SDATE_IS_EMPTY);
		validator.isRequired(Configure.PRICE, ErrorCode.PRICE_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		HttpSession session = session();
		List<CartItemPVO> list = (List<CartItemPVO>) session.getAttribute(Configure.CART_LIST);
		if (list == null) {
			list = new ArrayList<>();
		}
		CartItemPVO pvo = new CartItemPVO();
		pvo.setP_name(validator.getS(Configure.P_NAME));
		pvo.setP_num(validator.getI(Configure.P_NUM));
		pvo.setLeft_num(validator.getI(Configure.LEFT_NUM));
		pvo.setPrice(validator.getS(Configure.PRICE));
		pvo.setS_date(validator.getS(Configure.S_DATE));
		pvo.setStore_name(validator.getS(Configure.STORE_NAME));
		list.add(pvo);
		session.setAttribute(Configure.CART_LIST, list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Configure.SUCCESS, Configure.SUCCESS_INT);
		map.put(Configure.MESSAGE, "添加购物车成功");
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
