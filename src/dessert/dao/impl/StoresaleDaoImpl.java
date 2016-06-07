package dessert.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.StoresaleDao;
import dessert.entity.Statistics;
import dessert.entity.Storesale;
import dessert.rvo.commodity.SaleRecordRVO;
import dessert.util.Util;

@Repository
@Transactional
public class StoresaleDaoImpl extends BaseDaoImpl<Storesale> implements StoresaleDao{

	@Override
	public List<SaleRecordRVO> getByMonthAndStore(int month, int s_id) {
		List<SaleRecordRVO> rvos=new ArrayList<>();
		ProjectionList list=Projections.projectionList();
    	list.add(Projections.groupProperty("p_name"));
    	list.add(Projections.groupProperty("type"));
    	list.add(Projections.sum("p_num"));
    	list.add(Projections.sum("amount"));
		String start="2016-0"+(month)+"-01";
		String end="2016-0"+(month+1)+"-01";
		Date starDate=Util.getDateFromString(start);
		Date endDate=Util.getDateFromString(end);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Storesale.class);
		criteria.setProjection(list);
		criteria.add(Restrictions.between("s_date", starDate, endDate));
		criteria.add(Restrictions.eq("s_id", s_id));
		List<Object[]> resultList=criteria.list();
		for(Object[] temp:resultList){
    		SaleRecordRVO rvo=new SaleRecordRVO();
    		rvo.setS_id(s_id);
    		rvo.setP_name((String)temp[0]);
    		rvo.setType((int)temp[1]);
    		rvo.setP_num(((Long)temp[2]).intValue());
    		rvo.setAmount((double)temp[3]);
    		rvos.add(rvo);
    	}
		return rvos;		
	}

}
