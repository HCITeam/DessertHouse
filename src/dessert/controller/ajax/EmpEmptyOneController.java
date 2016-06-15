package dessert.controller.ajax;

import java.util.Map;

import org.springframework.stereotype.Controller;

import dessert.controller.AjaxController;
import dessert.util.FormValidator;

@Controller("empEmptyOne")
public class EmpEmptyOneController extends AjaxController{

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		return null;
	}

}
