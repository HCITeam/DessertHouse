package dessert.dao;

import java.util.List;

import dessert.entity.Storesale;
import dessert.rvo.commodity.SaleRecordRVO;

public interface StoresaleDao extends BaseDao<Storesale>{
    public List<SaleRecordRVO> getByMonthAndStore(int month,int s_id);
    
}
