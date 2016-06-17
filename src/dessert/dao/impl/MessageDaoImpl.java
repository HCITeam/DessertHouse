package dessert.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.MessageDao;
import dessert.entity.Message;
import dessert.entity.Plan;

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
		Session session = sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Message.class);
    	criteria.add(Restrictions.eq("emp_name", emp_name));
    	criteria.add(Restrictions.eq("draftdate", date));
		List<?> list = criteria.list();
		if ((list.size()) == 0){
			return null;
		}else{
			return (Message)list.get(0);
		}
	}

}
