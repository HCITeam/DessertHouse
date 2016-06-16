package dessert.controller.ajax.employee;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.employee.EmploeeInfoResultVO;
import dessert.service.EmployeeService;
import dessert.util.FormValidator;

@Controller("empListDeleteGet")
public class EmpListDeleteController extends AjaxController{
	  
		private static final long serialVersionUID = 1L;
		@Autowired
		EmployeeService employeeService;
		@Override
		public String execute() throws Exception {
			return controller(response(), request());
		}

		@Override
		public void validate(Map<String, String> params, FormValidator validator) {
			
		}

		@Override
		public String process(FormValidator validator) {
			List<EmploeeInfoResultVO> rvos=employeeService.getEmploeeDelete();
			Map<String, Object> map=new HashMap<>();
			map.put(Configure.SERVERS, rvos);
			setJsonResult(map);
			return Configure.SUCCESS;
		}
}
