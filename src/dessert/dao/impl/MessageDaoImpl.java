package dessert.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.MessageDao;
import dessert.entity.Message;

@Repository
@Transactional
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao{

	@Override
	public Message getById(int id) {
		// TODO Auto-generated method stub
		return getByColumn(Message.class, "id", id);
	}

	@Override
	public Message getByNameAndDate(String emp_name, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
