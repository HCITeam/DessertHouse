package dessert.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.entity.Store;
import dessert.pvo.PlanPVO;
import dessert.rvo.ResultVO;
import dessert.rvo.plan.PlanInfoResultVO;
import dessert.rvo.store.StoreRVO;
import dessert.service.PlanService;
import dessert.service.StoreService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("addPlan")
public class PlanAddController extends AjaxController {

	private static final long serialVersionUID = 1L;
	@Autowired
	PlanService planService;
	@Autowired
	StoreService storeService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.P_DATE, params.get(Configure.P_DATE));
		validator.put(Configure.S_ID, params.get(Configure.S_ID));
		validator.put(Configure.P_NAME, params.get(Configure.P_NAME));
		validator.put(Configure.P_NUM, params.get(Configure.P_NUM));
		validator.put(Configure.PRICE, params.get(Configure.PRICE));
		validator.isRequired(Configure.P_DATE, ErrorCode.SDATE_IS_EMPTY);
		// validator.isRequired(Configure., code);
		validator.isInt(Configure.P_NUM, ErrorCode.NUM_NOT_INT);
		validator.isInt(Configure.S_ID, ErrorCode.ID_NOT_INT);
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<>();
		PlanPVO pvo=new PlanPVO();
		pvo.setP_name(validator.getS(Configure.P_NAME));
		pvo.setP_num(validator.getI(Configure.P_NUM));
		pvo.setPlandate(Util.getDateFromString(validator.getS(Configure.P_DATE)));
		pvo.setPrice(Double.parseDouble(validator.getS(Configure.PRICE)));
		pvo.setS_id(validator.getI(Configure.S_ID));
		PlanInfoResultVO rVo=planService.addPlan(pvo);
		StoreRVO store=storeService.getStore(rVo.getS_id()+"");
		map.put(Configure.STORE_NAME, store.getName());
		map.put(Configure.P_ID, rVo.getId());
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
