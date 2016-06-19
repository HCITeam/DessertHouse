package dessert.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.configure.Configure;
import dessert.dao.MessageDao;
import dessert.entity.Message;
import dessert.pvo.MessagePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.message.MessageAddResultVO;
import dessert.service.MessageService;
import dessert.util.Util;

@Service
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
		message.setDelete_flag(Configure.DELETE_FLAG_FALSE);
		messageDao.add(message);
		
		
		rVO.setSuccess(Configure.SUCCESS_INT);
		rVO.setMessage("添加消息成功");

		

		return rVO;
	}

	@Override
	public ResultVO deleteMessage(int id) {
		// TODO Auto-generated method stub
		ResultVO rVo=new ResultVO();
		Message message=messageDao.getById(id);
		if (message == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("这天没有消息");
		} else {
			message.setDelete_flag(Configure.DELETE_FLAG_TRUE);
			messageDao.update(message);
			rVo.setSuccess(Configure.SUCCESS_INT);
			rVo.setMessage("删除消息成功");
		}
		return rVo;
	}

	@Override
	public ResultVO readMessage(int id) {
		// TODO Auto-generated method stub
		ResultVO rVo=new ResultVO();
		Message message=messageDao.getById(id);
		if (message == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("没有消息");
		} else {
			message.setRead(Configure.SUCCESS_INT);
			messageDao.update(message);
			rVo.setSuccess(Configure.SUCCESS_INT);
			rVo.setMessage("已读消息成功");
		}
		return rVo;
	}

	@Override
	public ArrayList<MessagePVO> getMessageList() {
		// TODO Auto-generated method stub
		List<Message> messages=(List<Message>)messageDao.getListByColumn(Message.class, "delete_flag", Configure.DELETE_FLAG_FALSE);
		ArrayList<MessagePVO> vos=new ArrayList<>();
		if (messages!=null) {
			for (int i = 0; i < messages.size(); i++) {
				MessagePVO infoResultVO=new MessagePVO();
				Message mes=messages.get(i);
				infoResultVO.setContent(mes.getContent());
				infoResultVO.setDate(mes.getDraftdate());
				infoResultVO.setEmployee_name(mes.getEmp_name());
				vos.add(infoResultVO);
			}
		}
		return vos;
	}

	@Override
	public ArrayList<MessagePVO> getMessageByEmp_name(String emp_name) {
		// TODO Auto-generated method stub
		List<Message> messages=(List<Message>)messageDao.getListByColumn(Message.class, "delete_flag", Configure.DELETE_FLAG_FALSE);
		ArrayList<MessagePVO> vos=new ArrayList<>();
		if (messages!=null) {
			for (int i = 0; i < messages.size(); i++) {
				Message mes=messages.get(i);
				if(mes.getEmp_name().equals(emp_name)){
					MessagePVO infoResultVO=new MessagePVO();
					infoResultVO.setContent(mes.getContent());
					infoResultVO.setDate(mes.getDraftdate());
					infoResultVO.setEmployee_name(mes.getEmp_name());
					vos.add(infoResultVO);
				}
			}
		}
		return vos;
	}

	@Override
	public ArrayList<MessagePVO> getUnreadMessageByEmp_name(String emp_name) {
		// TODO Auto-generated method stub
		List<Message> messages=(List<Message>)messageDao.getListByColumn(Message.class, "delete_flag", Configure.DELETE_FLAG_FALSE);
		ArrayList<MessagePVO> vos=new ArrayList<>();
		if (messages!=null) {
			for (int i = 0; i < messages.size(); i++) {
				Message mes=messages.get(i);
				if(mes.getEmp_name().equals(emp_name) && mes.isRead()==0){
					MessagePVO infoResultVO=new MessagePVO();
					infoResultVO.setContent(mes.getContent());
					infoResultVO.setDate(mes.getDraftdate());
					infoResultVO.setEmployee_name(mes.getEmp_name());
					vos.add(infoResultVO);
				}
			}
		}
		return vos;
	}

	@Override
	public ArrayList<MessagePVO> getReadMessageByEmp_name(String emp_name) {
		// TODO Auto-generated method stub
		List<Message> messages=(List<Message>)messageDao.getListByColumn(Message.class, "delete_flag", Configure.DELETE_FLAG_FALSE);
		ArrayList<MessagePVO> vos=new ArrayList<>();
		if (messages!=null) {
			for (int i = 0; i < messages.size(); i++) {
				Message mes=messages.get(i);
				if(mes.getEmp_name().equals(emp_name) && mes.isRead()==1){
					MessagePVO infoResultVO=new MessagePVO();
					infoResultVO.setContent(mes.getContent());
					infoResultVO.setDate(mes.getDraftdate());
					infoResultVO.setEmployee_name(mes.getEmp_name());
					vos.add(infoResultVO);
				}
			}
		}
		return vos;
	}

	

	@Override
	public MessagePVO getMessageByDate_emp(String emp_name, Date date) {
		// TODO Auto-generated method stub
		Message mes=messageDao.getByNameAndDate(emp_name, date);
		MessagePVO infoResultVO=new MessagePVO();
		infoResultVO.setContent(mes.getContent());
		infoResultVO.setDate(mes.getDraftdate());
		infoResultVO.setEmployee_name(mes.getEmp_name());
		return infoResultVO;
	}

	@Override
	public ArrayList<MessagePVO> getMessageByDate(Date date) {
		// TODO Auto-generated method stub
		List<Message> messages=(List<Message>)messageDao.getListByColumn(Message.class, "delete_flag", Configure.DELETE_FLAG_FALSE);
		ArrayList<MessagePVO> vos=new ArrayList<>();
		if (messages!=null) {
			for (int i = 0; i < messages.size(); i++) {
				Message mes=messages.get(i);
				if(mes.getDraftdate()==date){
					MessagePVO infoResultVO=new MessagePVO();
					infoResultVO.setContent(mes.getContent());
					infoResultVO.setDate(mes.getDraftdate());
					infoResultVO.setEmployee_name(mes.getEmp_name());
					vos.add(infoResultVO);
				}
			}
		}
		return vos;
	}

	@Override
	public ResultVO emptyOneMessage(int id) {
		// TODO Auto-generated method stub
		ResultVO rVo=new ResultVO();
		Message message=messageDao.getById(id);
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
	public ResultVO emptyAll() {
		// TODO Auto-generated method stub
		ResultVO resultVO = new ResultVO();
		messageDao.deleteByColumn(Message.class, "delete_flag", Configure.DELETE_FLAG_TRUE);
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("清空成功");
		return resultVO;
	}

}
