package dessert.dao;

import dessert.entity.Bankcard;

public interface BankcardDao extends BaseDao<Bankcard>{
     public Bankcard getBycardnum(String card_num);
     public Bankcard getByM_id(String id);
}
