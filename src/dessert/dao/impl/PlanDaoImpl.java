package dessert.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.PlanDao;
import dessert.entity.Plan;
import dessert.pvo.PlanPVO;

@Repository
@Transactional
public class PlanDaoImpl extends BaseDaoImpl<Plan> implements PlanDao{

	@Override
	public Plan getById(String id) {
		return getByColumn(Plan.class, "id", Integer.parseInt(id));
	}

	@Override
	public Plan getByPVO(PlanPVO pvo) {
		Session session = sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Plan.class);
    	criteria.add(Restrictions.eq("s_id", pvo.getS_id()));
    	criteria.add(Restrictions.eq("p_num", pvo.getP_num()));
    	criteria.add(Restrictions.eq("plandate", pvo.getPlandate()));
		List<?> list = criteria.list();
		if ((list.size()) == 0){
			return null;
		}else{
			return (Plan)list.get(0);
		}
	}

}
