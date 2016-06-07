package dessert.controller.ajax;

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

@Controller("addCommodity")
public class ComListAddController extends AjaxController {

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
		// TODO Auto-generated method stub
		validator.put(Configure.P_NAME, params.get(Configure.P_NAME));
		validator.put(Configure.P_NUM, params.get(Configure.P_NUM));
		validator.put(Configure.PRICE, params.get(Configure.PRICE));
		validator.isRequired(Configure.P_NAME, ErrorCode.NAME_IS_EMPTY);
		validator.isRequired(Configure.P_NUM, ErrorCode.NUM_IS_EMPTY);
		validator.isRequired(Configure.PRICE, ErrorCode.PRICE_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		List<ReceiptItemPVO> list=(List<ReceiptItemPVO>)session().getAttribute(Configure.RECEIPT_LIST);
		if (list==null) {
			list=new ArrayList<>();
		}
		ReceiptItemPVO itemPVO=new ReceiptItemPVO();
		itemPVO.setP_name(validator.getS(Configure.P_NAME));
		itemPVO.setP_num(validator.getI(Configure.P_NUM));
		itemPVO.setPrice(Double.parseDouble(validator.getS(Configure.PRICE)));
		list.add(itemPVO);
		session().setAttribute(Configure.RECEIPT_LIST, list);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put(Configure.SUCCESS,Configure.SUCCESS_INT);
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
