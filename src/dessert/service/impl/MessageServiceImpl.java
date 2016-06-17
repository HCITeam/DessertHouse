package dessert.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;


import dessert.configure.Configure;
import dessert.dao.MessageDao;
import dessert.entity.Employee;
import dessert.entity.Message;
import dessert.entity.Store;
import dessert.pvo.MessagePVO;
import dessert.pvo.MessageUpdatePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.employee.EmployeeAddResultVO;
import dessert.rvo.message.MessageAddResultVO;
import dessert.service.MessageService;
import dessert.util.Util;

public class MessageServiceImpl implements MessageService{
	@Autowired
	MessageDao messageDao;
	
	@Override
	public ResultVO addMessage(MessagePVO po) {
		// TODO Auto-generated method stub
		MessageAddResultVO rVO = new MessageAddResultVO();
		rVO.setEmp_name(po.getEmployee_name());
		rVO.setContent(po.getContent());
		Message message=new Message();
		message.setContent(po.getContent());
		message.setEmp_name(po.getEmployee_name());
		message.setRead(0);
		Date date=Util.getCurrentDate();
		message.setDraftdate(date);
		messageDao.add(message);
		
		
		rVO.setSuccess(Configure.SUCCESS_INT);
		rVO.setMessage("添加消息成功");

		

		return rVO;
	}

	@Override
	public ResultVO deleteMessage(String emp_name, Date date) {
		// TODO Auto-generated method stub
		ResultVO rVo=new ResultVO();
		Message message=messageDao.getByNameAndDate(emp_name, date);
		if (message == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("这天没有消息");
		} else {
			messageDao.delete(message);
			rVo.setSuccess(Configure.SUCCESS_INT);
			rVo.setMessage("删除消息成功");
		}
		return rVo;
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
