package dessert.service;

import java.util.ArrayList;
import java.util.Date;

import dessert.pvo.MessagePVO;
import dessert.pvo.MessageUpdatePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.message.MessageAddResultVO;

public interface MessageService {

	//添加
	public ResultVO addMessage(MessagePVO po);
	//删除
	public ResultVO deleteMessage(int id);
	//已读，修改状态
	public ResultVO readMessage(MessageUpdatePVO po,int id);
	//得到所有消息
	public ArrayList<MessagePVO> getMessageList();
	//得到某个服务员的所有消息
	public ArrayList<MessagePVO> getMessageByEmp_name(String emp_name);
	//得到某个服务员的所有未读消息
	public ArrayList<MessagePVO> getUnreadMessageByEmp_name(String emp_name);
	//某个服务员的所有已读消息
	public ArrayList<MessagePVO> getReadMessageByEmp_name(String emp_name);
	// 得到某天消息
	public MessagePVO getMessageByDate_emp(String emp_name,Date date);
	//经理得到某天消息
	public ArrayList<MessagePVO> getMessageByDate(Date date);
	//清空一条消息
	public ResultVO emptyOneMessage(int id);
	//清空所有已删除信息
	public ResultVO  emptyAll();
}
