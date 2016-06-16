package dessert.controller.ajax.plan;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.ResultVO;
import dessert.service.PlanService;
import dessert.util.FormValidator;

@Controller("planEmptyAll")
public class PlanEmptyAllController extends AjaxController{

	/**
	 * 彻底删除所有已删除计划
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
		
	}

	@Override
	public String process(FormValidator validator) {
		Map<String, Object> map=new HashMap<>();
		ResultVO rVo=planService.emptyAll();
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
