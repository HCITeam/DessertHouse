package dessert.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.EmployeePVO;
import dessert.rvo.employee.EmployeeAddResultVO;
import dessert.service.EmployeeService;
import dessert.util.FormValidator;

@Controller("addEmployee")
public class EmployeeAddController extends AjaxController{
    
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
//		System.out.println("validate");
		validator.put(Configure.NAME, params.get(Configure.NAME));
		validator.put(Configure.PASSWORD, params.get(Configure.PASSWORD));
		validator.put(Configure.WORK_TYPE,params.get(Configure.WORK_TYPE));
		validator.put(Configure.S_ID, params.get(Configure.S_ID));
		
		validator.isRequired(Configure.S_ID, ErrorCode.ID_IS_EMPTY);
		validator.isRequired(Configure.NAME, ErrorCode.NAME_IS_EMPTY);
		validator.isRequired(Configure.WORK_TYPE, ErrorCode.TYPE_IS_EMPTY);
		validator.isRequired(Configure.PASSWORD, ErrorCode.PASSWORD_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
//		ServletContext sc = request().getServletContext();
		EmployeePVO po=new EmployeePVO();
		po.setFromValidator(validator);
		EmployeeAddResultVO rVo=employeeService.addEmployee(po);
//		
		Map<String, Object> map=new HashMap<>();
		map.put(Configure.STORE_NAME, rVo.getStore_name());
		map.put(Configure.WORK_TYPE, rVo.getType());
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
