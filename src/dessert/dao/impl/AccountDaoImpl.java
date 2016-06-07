package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.AccountDao;
import dessert.entity.Account;

@Repository
@Transactional
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao{

	@Override
	public Account getByID(int id) {
		return getByColumn(Account.class, "s_id", id);
	}

}
