package dessert.controller.ajax.sale;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.pvo.ReceiptItemPVO;
import dessert.rvo.ResultVO;
import dessert.service.CommodityService;
import dessert.util.FormValidator;

@Controller("payByCard")
public class PayByCardController extends AjaxController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CommodityService commodityService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		
		
	}

	@Override
	public String process(FormValidator validator) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<ReceiptItemPVO> list=(List<ReceiptItemPVO>)session().getAttribute(Configure.RECEIPT_LIST);
		String id=(String)session().getAttribute(Configure.ID);
		int s_id=(int)session().getAttribute(Configure.S_ID);
		if (list==null) {
			map.put(Configure.SUCCESS, Configure.FAIL);
			map.put(Configure.MESSAGE, "请输入商品信息");
		}else if(id==null) {
			map.put(Configure.SUCCESS, Configure.FAIL);
			map.put(Configure.MESSAGE, "请输入会员卡");
		}else {
			ResultVO resultVO=commodityService.payByCard(list, Integer.parseInt(id), s_id);
			map.put(Configure.SUCCESS, resultVO.getSuccess());
			map.put(Configure.MESSAGE, resultVO.getMessage());
		}
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
