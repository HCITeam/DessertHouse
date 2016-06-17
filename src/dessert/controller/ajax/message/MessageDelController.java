package dessert.controller.ajax.message;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.rvo.ResultVO;
import dessert.service.MessageService;
import dessert.util.FormValidator;

@Controller("messageDel")
public class MessageDelController extends AjaxController{

	/**
	 * set delete_flag=1
	 */
	private static final long serialVersionUID = 1L;
    
	@Autowired
	MessageService messageService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.ID, params.get(Configure.ID));
		validator.isRequired(Configure.ID, ErrorCode.ID_IS_EMPTY);
		validator.isInt(Configure.ID, ErrorCode.ID_NOT_INT);
	}

	@Override
	public String process(FormValidator validator) {
		ResultVO rVo = messageService.deleteMessage(validator.getI(Configure.ID));
		Map<String, Object> map=new HashMap<>();
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
