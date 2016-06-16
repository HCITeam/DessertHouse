package dessert.controller.ajax.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.pvo.CartItemPVO;
import dessert.util.FormValidator;

@Controller("getCart")
public class GetCartController extends AjaxController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		
	}

	@Override
	public String process(FormValidator validator) {
		HttpSession session = session();
		List<CartItemPVO> list = (List<CartItemPVO>) session.getAttribute(Configure.CART_LIST);
		if (list == null) {
			list = new ArrayList<>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Configure.CART_LIST, list);
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
