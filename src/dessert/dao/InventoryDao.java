package dessert.dao;

import java.util.Date;
import java.util.List;

import dessert.entity.Inventory;

public interface InventoryDao extends BaseDao<Inventory>{
    public List<Inventory> getByStoreIDandDate(int ID,Date date);
    public Inventory getByStoreIDandDateandName(int ID,String date,String p_name);
}
