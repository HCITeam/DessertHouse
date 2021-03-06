package dessert.controller.ajax.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.rvo.message.MessageInfoResultVO;
import dessert.service.MessageService;
import dessert.util.FormValidator;

@Controller("mesReadGet")
public class MesReadListGetController extends AjaxController{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	MessageService messageService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
//		validator.put(Configure.NAME, params.get(Configure.NAME));
//		validator.isRequired(Configure.NAME, ErrorCode.NAME_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		String name = (String)session().getAttribute(Configure.NAME);
		List<MessageInfoResultVO> messages = messageService.getReadMessageByEmp_name(name);
		Map<String, Object> map=new HashMap<>();
		map.put(Configure.MESSAGE, messages);
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
