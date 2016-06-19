package dessert.controller.html;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.pvo.MessagePVO;
import dessert.rvo.employee.EmploeeInfoResultVO;
import dessert.rvo.message.MessageInfoResultVO;
import dessert.service.EmployeeService;
import dessert.service.MessageService;
import dessert.service.StoreService;
import dessert.util.FormValidator;

@Controller("manageMessage")
public class MessageManageController extends HtmlController{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	MessageService messageService;
	@Autowired
	StoreService storeService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		// TODO Auto-generated method stub
		validator.put(Configure.EMP_NAME, params.get(Configure.EMP_NAME));
		validator.isRequired(Configure.EMP_NAME, ErrorCode.EMP_NAME_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		ServletContext sc = request().getServletContext();
		ArrayList<MessageInfoResultVO> unreadList=messageService.getUnreadMessageByEmp_name(validator.getS(Configure.EMP_NAME));
		ArrayList<MessageInfoResultVO> readList=messageService.getReadMessageByEmp_name(validator.getS(Configure.EMP_NAME));
		ArrayList<MessageInfoResultVO> deleteList=messageService.getDeleteMessageByEmp_name(validator.getS(Configure.EMP_NAME));
		
		sc.setAttribute(Configure.READ_MESSAGE, readList);
		sc.setAttribute(Configure.UNREAD_MESSAGE, unreadList);
		sc.setAttribute(Configure.DELETE_MESSAGE, deleteList);
		return Configure.SUCCESS;
	}

}
