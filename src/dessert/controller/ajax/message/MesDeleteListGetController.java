package dessert.controller.ajax.message;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.controller.AjaxController;
import dessert.service.MessageService;
import dessert.util.FormValidator;

@Controller("mesDeleteGet")
public class MesDeleteListGetController extends AjaxController{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	MessageService messageService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
//		messageService.get
		return null;
	}

}
