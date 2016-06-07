package dessert.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.StatisticsDao;
import dessert.entity.Statistics;
import dessert.util.Util;
@Repository
@Transactional
public class StatisticsDaoImpl extends BaseDaoImpl<Statistics> implements StatisticsDao {

	@Override
	public Statistics getByMonth(int month) {
		String start="2016-0"+(month)+"-01";
		String end="2016-0"+(month+1)+"-01";
		Date starDate=Util.getDateFromString(start);
		Date endDate=Util.getDateFromString(end);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Statistics.class);
		criteria.add(Restrictions.between("s_date", starDate, endDate));
		List<Statistics> list = criteria.list();
		if ((list.size()) == 0){
			return null;
		}else{
			return (Statistics)list.get(0);
		}		
	}
	
}
