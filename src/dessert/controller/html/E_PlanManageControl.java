package dessert.controller.html;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.plan.PlanInfoResultVO;
import dessert.service.PlanService;
import dessert.service.StoreService;
import dessert.util.FormValidator;

@Controller("e_planManage")
public class E_PlanManageControl  extends HtmlController {
	@Autowired
	PlanService planService;
	@Autowired
	StoreService storeService;
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
		// 获得参数
		validator.put(Configure.NAME, params.get(Configure.NAME));
		validator.put(Configure.PASSWORD, params.get(Configure.PASSWORD));
		validator.isRequired(Configure.NAME, ErrorCode.NAME_IS_EMPTY);
		validator.isRequired(Configure.PASSWORD, ErrorCode.PASSWORD_IS_EMPTY);
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
