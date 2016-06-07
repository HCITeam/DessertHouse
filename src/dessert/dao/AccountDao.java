package dessert.dao;

import dessert.entity.Account;

public interface AccountDao extends BaseDao<Account>{
   public Account getByID(int id);
}
