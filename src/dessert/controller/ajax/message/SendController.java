package dessert.controller.ajax.message;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.MessagePVO;
import dessert.rvo.ResultVO;
import dessert.service.MessageService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("sendMessage")
public class SendController extends AjaxController{

	/**
	 * 发送消息
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
		validator.put(Configure.NAME, params.get(Configure.NAME));
		validator.put(Configure.CONTENT, params.get(Configure.CONTENT));
		validator.isRequired(Configure.NAME, ErrorCode.NAME_IS_EMPTY);
		validator.isRequired(Configure.CONTENT, ErrorCode.CONTENT_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		MessagePVO pvo = new MessagePVO(validator.getS(Configure.CONTENT), validator.getS(Configure.NAME), Util.getCurrentDate());
		ResultVO rVo = messageService.addMessage(pvo);
		Map<String, Object> map=new HashMap<>();
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
