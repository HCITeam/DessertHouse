package dessert.controller.html;

import java.util.Map;

import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.HtmlController;
import dessert.util.FormValidator;

@Controller("logout")
public class LogoutController extends HtmlController{

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		session().invalidate();
		return Configure.SUCCESS;
	}

}
