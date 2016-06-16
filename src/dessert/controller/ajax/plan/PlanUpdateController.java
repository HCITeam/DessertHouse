package dessert.controller.ajax.plan;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.PlanPVO;
import dessert.rvo.ResultVO;
import dessert.service.PlanService;
import dessert.util.FormValidator;

@Controller("updatePlan")
public class PlanUpdateController extends AjaxController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	PlanService planService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.P_ID, params.get(Configure.P_ID));
		validator.put(Configure.P_NUM, params.get(Configure.P_NUM));
		validator.put(Configure.PRICE, params.get(Configure.PRICE));
		validator.isRequired(Configure.P_ID, ErrorCode.ID_IS_EMPTY);
		validator.isRequired(Configure.P_NUM, ErrorCode.NUM_IS_EMPTY);
		validator.isRequired(Configure.PRICE, ErrorCode.PRICE_IS_EMPTY);
		validator.isInt(Configure.P_NUM, ErrorCode.NUM_NOT_INT);
		validator.isInt(Configure.P_ID, ErrorCode.ID_NOT_INT);
		
	}

	@Override
	public String process(FormValidator validator) {
		Map<String, Object> map=new HashMap<>();
		PlanPVO pvo=new PlanPVO();
		System.err.println("new");
		pvo.setP_num(validator.getI(Configure.P_NUM));
		pvo.setPrice(Double.parseDouble(validator.getS(Configure.PRICE)));
		System.err.println("two");
		ResultVO rVo=planService.updatePlan(pvo,validator.getS(Configure.P_ID));
		System.err.println("three");
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
