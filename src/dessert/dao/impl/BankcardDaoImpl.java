package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.BankcardDao;
import dessert.entity.Bankcard;

@Repository
@Transactional
public class BankcardDaoImpl extends BaseDaoImpl<Bankcard> implements BankcardDao{

	@Override
	public Bankcard getBycardnum(String card_num) {
		return getByColumn(Bankcard.class, "cardnumber", card_num);
	}

	@Override
	public Bankcard getByM_id(String id) {
		
		return getByColumn(Bankcard.class, "id", Integer.parseInt(id));
	}

}
