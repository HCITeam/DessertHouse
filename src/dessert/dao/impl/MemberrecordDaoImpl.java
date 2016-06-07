package dessert.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.configure.Configure;
import dessert.dao.MemberrecordDao;
import dessert.entity.Memberinfo;
import dessert.entity.Memberrecord;
import dessert.util.Util;

@Repository
@Transactional
public class MemberrecordDaoImpl extends BaseDaoImpl<Memberrecord> implements MemberrecordDao{

	@Override
	public String getAllPurcharse() {
		Date date=Util.getCurrentDate();
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		Session session = sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Memberrecord.class);
    	ProjectionList list=Projections.projectionList();
    	list.add(Projections.groupProperty("type"));
    	list.add(Projections.rowCount());
    	list.add(Projections.sum("amount"));
    	criteria.setProjection(list);
    	criteria.add(Restrictions.between("r_date", Util.getDateFromString(year+"-"+month+"-01"), date));
    	criteria.addOrder(Order.asc("type"));
    	List<Object[]> resultList=criteria.list();
    	String result="";
    	Object [] temp=resultList.get(Configure.PURCHARSE);//购买
    	result=result+temp[0]+":"+temp[1]+":"+temp[2]+";";
    	temp=resultList.get(Configure.RESERVE);//预定
    	result=result+temp[0]+":"+temp[1]+":"+temp[2]+";";
    	return result;
	}

}
