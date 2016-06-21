package dessert.controller.ajax.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.plan.PlanInfoResultVO;
import dessert.service.PlanService;
import dessert.util.FormValidator;
@Controller("planListDeleteGet")
public class PlanListDeleteGetController extends AjaxController{
	  
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
			List<PlanInfoResultVO> rvos=planService.getDeletePlan();
			Map<String, Object> map=new HashMap<>();
			map.put(Configure.PLAN_LIST, rvos);
			setJsonResult(map);
			return Configure.SUCCESS;
		}
}
