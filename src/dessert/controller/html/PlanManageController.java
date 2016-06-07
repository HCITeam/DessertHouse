package dessert.controller.html;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.HtmlController;
import dessert.rvo.plan.PlanInfoResultVO;
import dessert.service.PlanService;
import dessert.service.StoreService;
import dessert.util.FormValidator;

@Controller("managePlan")
public class PlanManageController extends HtmlController{

	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String process(FormValidator validator) {
		ServletContext sc = request().getServletContext();
		List<PlanInfoResultVO> impass=planService.getInpassPlan(1);
		List<PlanInfoResultVO> pass=planService.getPassPlan(1);
		List<PlanInfoResultVO> all=planService.getAllPlan(1);
		Map<Integer, String> store=storeService.getStores();
		sc.setAttribute(Configure.STORE_LIST, store);
		sc.setAttribute(Configure.IMPASS_PLAN, impass);
		sc.setAttribute(Configure.PASS_PLAN, pass);
		sc.setAttribute(Configure.ALL_PLAN, all);
		return Configure.SUCCESS;
	}

}
