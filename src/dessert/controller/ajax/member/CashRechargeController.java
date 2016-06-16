package dessert.controller.ajax.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.RechargePVO;
import dessert.rvo.ResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("cashRecharge")
public class CashRechargeController extends AjaxController{

	/**
	 * 
	 */
	@Autowired
	MemberService memberService;
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.AMOUNT, params.get(Configure.AMOUNT));
		validator.put(Configure.ID, params.get(Configure.ID));
		validator.isRequired(Configure.AMOUNT, ErrorCode.AMOUNT_IS_EMPTY);
		validator.isRequired(Configure.ID, ErrorCode.ID_IS_EMPTY);
//		validator.is
		
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		RechargePVO pvo=new RechargePVO();
		pvo.setM_id(validator.getS(Configure.ID));
		pvo.setAmount(Double.parseDouble(validator.getS(Configure.AMOUNT)));
//		pvo.setCardnumber(validator.getS(Configure.BANKCARD));
		pvo.setType(Configure.BY_CASH);
		ResultVO rVo=memberService.Recharge(pvo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
