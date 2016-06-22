package dessert.controller.html;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.HtmlController;
import dessert.rvo.message.MessageInfoResultVO;
import dessert.service.MessageService;
import dessert.util.FormValidator;

@Controller("manageMessage")
public class MessageManageController extends HtmlController{

	
	/**
	 * 
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
		
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		String name = (String)session().getAttribute(Configure.NAME);
		ServletContext sc = request().getServletContext();
		ArrayList<MessageInfoResultVO> unreadList=messageService.getUnreadMessageByEmp_name(name);
		ArrayList<MessageInfoResultVO> readList=messageService.getReadMessageByEmp_name(name);
		ArrayList<MessageInfoResultVO> deleteList=messageService.getDeleteMessageByEmp_name(name);
		
		sc.setAttribute(Configure.READ_MESSAGE, readList);
		sc.setAttribute(Configure.UNREAD_MESSAGE, unreadList);
		sc.setAttribute(Configure.DELETE_MESSAGE, deleteList);
		sc.setAttribute(Configure.NAME, name);
		return Configure.SUCCESS;
	}

}
