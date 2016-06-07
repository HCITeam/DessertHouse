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

import dessert.dao.MemberinfoDao;
import dessert.entity.Memberinfo;
import dessert.util.Util;

@Repository
@Transactional
public class MemberinfoDaoImpl extends BaseDaoImpl<Memberinfo> implements MemberinfoDao{

	@Override
	public Memberinfo getById(String id) {
		return getByColumn(Memberinfo.class, "id", Integer.parseInt(id));
	}

	@Override
	public String getAllGender() {
		Session session = sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Memberinfo.class);
    	ProjectionList list=Projections.projectionList();
    	list.add(Projections.groupProperty("gender"));
    	list.add(Projections.rowCount());
    	criteria.setProjection(list);
    	criteria.addOrder(Order.asc("gender"));
    	List<Object[]> resultList=criteria.list();
    	String result="";
    	for(Object[] temp:resultList){
    		result=result+temp[0]+":"+temp[1]+";";//格式：1:30;2:2;
    	}
    	return result;
	}

	@Override
	public String getAllArea() {
		Session session = sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Memberinfo.class);
    	ProjectionList list=Projections.projectionList();
    	list.add(Projections.groupProperty("area"));
    	list.add(Projections.rowCount());
    	criteria.setProjection(list);
    	criteria.addOrder(Order.asc("area"));
    	List<Object[]> resultList=criteria.list();
    	String result="";
    	for(Object[] temp:resultList){
    		result=result+temp[0]+":"+temp[1]+";";//格式：1:30;2:2;
    	}
    	return result;
	}

	@Override
	public String getAllAge() {
		Session session = sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Memberinfo.class);
		int[] large=new int[]{20,40,60,80,100};
		int[] least=new int[]{0,20,40,60,80};
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		String result="";
		for (int i = 0; i < least.length; i++) {
			String start=(year-large[i])+"-01-01";
			String end=(year-least[i])+"-01-01";
			Date starDate=Util.getDateFromString(start);
			Date endDate=Util.getDateFromString(end);
			criteria.add(Restrictions.between("birthday", starDate, endDate));
			result=result+i+":"+criteria.list().size()+";";	
		}

		return result;
	}

}
