package dessert.controller.ajax;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.pvo.EmployeePVO;
import dessert.rvo.employee.EmployeeAddResultVO;
import dessert.service.EmployeeService;
import dessert.util.FormValidator;

@Controller("testController")
public class TestController extends AjaxController{
    
	private static final long serialVersionUID = 1L;

	@Autowired
	EmployeeService employeeService;
	@Override
	public String execute() throws Exception {
		
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		// TODO Auto-generated method stub
		System.out.println("validate");
		validator.put(Configure.NAME, "admin");
		validator.put(Configure.PASSWORD, "admin");
		validator.put(Configure.WORK_TYPE, Configure.ADMINISTRATOR);
		validator.put(Configure.S_ID, 0);
	}

	@Override
	public String process(FormValidator validator) {
		ServletContext sc = request().getServletContext();
		EmployeePVO po=new EmployeePVO();
		po.setFromValidator(validator);
		EmployeeAddResultVO rVo=employeeService.addEmployee(po);
		sc.setAttribute(Configure.RESULTVO, rVo);
		if (rVo.getSuccess()==Configure.FAIL) {
			return Configure.ERROR;
		}
		return Configure.SUCCESS;
	}

}
