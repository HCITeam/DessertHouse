package dessert.controller.ajax.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.ReceiptItemPVO;
import dessert.util.FormValidator;
@Controller("delCommodity")
public class ComListDelController extends AjaxController{

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.P_NAME, params.get(Configure.P_NAME));
		validator.isRequired(Configure.P_NAME, ErrorCode.NAME_IS_EMPTY);		
	}

	@Override
	public String process(FormValidator validator) {
		List<ReceiptItemPVO> list=(List<ReceiptItemPVO>)session().getAttribute(Configure.RECEIPT_LIST);
		if (list==null) {
			list=new ArrayList<>();
		}
		String name = validator.getS(Configure.P_NAME);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getP_name().equals(name)) {
				list.remove(i);
				i--;
			}
		}
		session().setAttribute(Configure.RECEIPT_LIST, list);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put(Configure.SUCCESS,Configure.SUCCESS_INT);
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
