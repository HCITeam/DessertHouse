package dessert.controller.html;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.HtmlController;
import dessert.rvo.employee.EmploeeInfoResultVO;
import dessert.service.EmployeeService;
import dessert.service.StoreService;
import dessert.util.FormValidator;

@Controller("manageEmployee")
public class EmployeeManageController extends HtmlController{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	EmployeeService employeeService;
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
		// TODO Auto-generated method stub
		ServletContext sc = request().getServletContext();
		ArrayList<EmploeeInfoResultVO> serverList=employeeService.getEmploeesByType(Configure.SERVER);//服务员列表
		ArrayList<EmploeeInfoResultVO> headList=employeeService.getEmploeesByType(Configure.HEAD_SERVER);//总服务员
//		for (int i = 0; i < serverList.size(); i++) {
//			headList.add(serverList.get(i));
//		}
		Map<Integer, String> store=storeService.getStores();
		sc.setAttribute(Configure.STORE_LIST, store);
		sc.setAttribute(Configure.E_HEAD_SERVER, headList);
		sc.setAttribute(Configure.E_SERVER, serverList);
		return Configure.SUCCESS;
	}

}
