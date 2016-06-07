package dessert.dao;

import dessert.entity.Store;

public interface StoreDao extends BaseDao<Store>{
   public Store getByAddr(String Address);
   public Store getByName(String name);
   public Store getById(String id);
}
