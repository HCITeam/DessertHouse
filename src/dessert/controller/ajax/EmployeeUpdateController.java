package dessert.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.EmployeeUpdatePVO;
import dessert.rvo.ResultVO;
import dessert.service.EmployeeService;
import dessert.util.FormValidator;
@Controller("updateEmployee")
public class EmployeeUpdateController extends AjaxController{
	private static final long serialVersionUID = 1L;
	@Autowired
	EmployeeService employeeService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.NAME, params.get(Configure.NAME));
		validator.put(Configure.S_ID, params.get(Configure.S_ID));
		validator.put(Configure.WORK_TYPE, params.get(Configure.WORK_TYPE));
//		validator.put(Configure.PHONE, params.get(Configure.PHONE));
		validator.isRequired(Configure.NAME, ErrorCode.NAME_IS_EMPTY);
		validator.isRequired(Configure.S_ID, ErrorCode.ID_IS_EMPTY);
		validator.isRequired(Configure.WORK_TYPE, ErrorCode.TYPE_IS_EMPTY);
//		validator.isRequired(Configure.PHONE, ErrorCode.PHONE_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		EmployeeUpdatePVO pvo=new EmployeeUpdatePVO();
		pvo.setFromValidator(validator);
		
		ResultVO rVo=employeeService.updateEmployee(pvo);
		
		Map<String, Object> map=new HashMap<>();
//		map.put(Configure.S_ID, rVo.getId());	
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}}
