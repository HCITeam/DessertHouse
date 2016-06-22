package dessert.controller.ajax.plan;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.rvo.ResultVO;
import dessert.service.PlanService;
import dessert.util.FormValidator;

@Controller("planUnDel")
public class PlanUndeleteController extends AjaxController{

	/**
	 * 撤销删除
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
		validator.isRequired(Configure.P_ID, ErrorCode.ID_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		Map<String, Object> map=new HashMap<>();
		ResultVO rVo=planService.UndeletePlan(validator.getS(Configure.P_ID));
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
