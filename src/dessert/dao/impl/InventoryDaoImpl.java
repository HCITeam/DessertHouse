package dessert.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.InventoryDao;
import dessert.entity.Inventory;
import dessert.util.Util;

@Repository
@Transactional
public class InventoryDaoImpl extends BaseDaoImpl<Inventory> implements InventoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getByStoreIDandDate(int ID, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Inventory.class);
		criteria.add(Restrictions.eq("s_id", ID));
		// criteria.add(Restrictions.eq("s_date", date));
		criteria.add(Restrictions.between("s_date", date, Util.theDateWhinday(date)));
		criteria.addOrder(Order.asc("p_num"));
		return criteria.list();
	}

	@Override
	public Inventory getByStoreIDandDateandName(int ID, String date, String p_name) {
		Date theday=Util.getDateFromString(date);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Inventory.class);
		criteria.add(Restrictions.eq("s_id", ID));
		criteria.add(Restrictions.eq("p_name", p_name));
		// criteria.add(Restrictions.eq("s_date", date));
		criteria.add(Restrictions.between("s_date", theday, Util.theDateWhinday(theday)));
		criteria.addOrder(Order.asc("p_num"));
		return (Inventory)criteria.list().get(0);
	}

}
