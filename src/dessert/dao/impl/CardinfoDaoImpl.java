package dessert.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.CardinfoDao;
import dessert.entity.Cardinfo;

@Repository
@Transactional
public class CardinfoDaoImpl extends BaseDaoImpl<Cardinfo> implements CardinfoDao{

	@Override
	public Cardinfo getById(String id) {
		
		return getByColumn(Cardinfo.class, "id", Integer.parseInt(id));
	}

	@Override
	public Cardinfo getById(int id) {
		return getByColumn(Cardinfo.class, "id", id);
	}

	@Override
	public String getAllCardState() {
		Session session = sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Cardinfo.class);
    	ProjectionList list=Projections.projectionList();
    	list.add(Projections.groupProperty("state"));
    	list.add(Projections.rowCount());
    	criteria.setProjection(list);
    	criteria.addOrder(Order.asc("state"));
    	List<Object[]> resultList=criteria.list();
    	String result="";
    	for(Object[] temp:resultList){
    		result=result+temp[0]+":"+temp[1]+";";//格式：1:30;2:2;
    	}
    	return result;
	}

}
