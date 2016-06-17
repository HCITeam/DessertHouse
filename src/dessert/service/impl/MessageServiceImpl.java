package dessert.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import dessert.configure.Configure;
import dessert.entity.Employee;
import dessert.entity.Store;
import dessert.pvo.MessagePVO;
import dessert.pvo.MessageUpdatePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.employee.EmployeeAddResultVO;
import dessert.rvo.message.MessageAddResultVO;
import dessert.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Override
	public MessageAddResultVO addMessage(MessagePVO po) {
		// TODO Auto-generated method stub
		MessageAddResultVO rVO = new MessageAddResultVO();
		rVO.setEmp_name(po.getEmployee_name());
		rVO.setContent(po.getContent());

		

		return rVO;
	}

	@Override
	public ResultVO deleteMessage(String emp_name, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO readMessage(MessageUpdatePVO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MessagePVO> getMessageList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MessagePVO> getMessageByEmp_name(String emp_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MessagePVO> getUnreadMessageByEmp_name(String emp_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MessagePVO> getReadMessageByEmp_name(String emp_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessagePVO getMessageByDate(String emp_name, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
