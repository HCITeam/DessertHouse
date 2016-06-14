package dessert.controller.html;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.commodity.InventoryRVO;
import dessert.rvo.employee.EmployeeLoginRVO;
import dessert.rvo.plan.PlanInfoResultVO;
import dessert.rvo.store.StoreRVO;
import dessert.service.CommodityService;
import dessert.service.EmployeeService;
import dessert.service.PlanService;
import dessert.service.StoreService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("emploeeLogin")
public class EmploeeLoginController extends HtmlController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	PlanService planService;
	@Autowired
//	CommodityService commodityService;
	StoreService storeService;
	@Autowired
	CommodityService commodityService;
	
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
		
//		EmployeePVO pvo=new EmployeePVO();
//		pvo.setName("zjl");
//		pvo.setPassword("zjl");
//		pvo.setS_id(0);
//		pvo.setType(Configure.DIRECTOR);
//		employeeService.addEmployee(pvo);
//	
//		pvo.setName("admin");
//		pvo.setPassword("admin");
//		pvo.setS_id(0);
//		pvo.setType(Configure.DIRECTOR);
//		employeeService.addEmployee(pvo);
		
		EmployeeLoginRVO rvo = employeeService.login(validator.getS(Configure.NAME),
				validator.getS(Configure.PASSWORD));
		HttpSession session = session();
		ServletContext sc = request().getServletContext();
		if (rvo.getSuccess() == Configure.SUCCESS_INT) {// 登录成功
			sc.setAttribute(Configure.SUCCESS, Configure.SUCCESS_INT);
			session.setAttribute(Configure.NAME, rvo.getName());
			session.setAttribute(Configure.S_ID, rvo.getS_id());
			session.setAttribute(Configure.WORK_TYPE, rvo.getType());
			switch (rvo.getType()) {
			case Configure.ADMINISTRATOR:
				adminPage();
				return Configure.E_ADIMIN;
			case Configure.DIRECTOR:
				directorPage();
				return Configure.E_DIRECTOR;
			case Configure.HEAD_SERVER:
				headPage();
				return Configure.E_HEAD_SERVER;
			case Configure.SERVER:
				serverPage();
				return Configure.E_SERVER;
			default:
				sc.setAttribute(Configure.SUCCESS, Configure.FAIL);
				sc.setAttribute(Configure.MESSAGE, "该用户无效");
				return Configure.ERROR;
			}
		}
		sc.setAttribute(Configure.SUCCESS, Configure.FAIL);
		sc.setAttribute(Configure.MESSAGE, rvo.getMessage());
		return Configure.ERROR;
	}

	/**
	 * 管理员界面的初始化数据
	 */
	private void adminPage(){
		ServletContext sc = request().getServletContext();
		List<StoreRVO> list=storeService.getAllStoreNotDelete();	
		sc.setAttribute(Configure.STORE_LIST, list);
	}
	
	/**
	 * 
	 */
	private void headPage(){
		ServletContext sc = request().getServletContext();
		List<PlanInfoResultVO> list=planService.getInpassPlan(1);
		Map<Integer, String> store=storeService.getStores();
		sc.setAttribute(Configure.STORE_LIST, store);
		sc.setAttribute(Configure.IMPASS_PLAN, list);
	}
	private void directorPage(){
		ServletContext sc = request().getServletContext();
		List<PlanInfoResultVO> impass=planService.getInpassPlan(1);
		List<PlanInfoResultVO> pass=planService.getPassPlan(1);
		List<PlanInfoResultVO> all=planService.getAllPlan(1);
		Map<Integer, String> store=storeService.getStores();
		sc.setAttribute(Configure.STORE_LIST, store);
		sc.setAttribute(Configure.IMPASS_PLAN, impass);
		sc.setAttribute(Configure.PASS_PLAN, pass);
		sc.setAttribute(Configure.ALL_PLAN, all);
	}
	private void serverPage(){
		Date date=Util.getCurrentDate();
//		Date date=Util.getDateFromString("2016-02-28");
		List<InventoryRVO> list=commodityService.getByIDandDate((int)session().getAttribute(Configure.S_ID), date);
		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.INVENTORY_LIST, list);
	}
}
