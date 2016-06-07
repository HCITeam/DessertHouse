package dessert.dao;

import dessert.entity.Memberinfo;

public interface MemberinfoDao extends BaseDao<Memberinfo>{
   public Memberinfo getById(String id);
   public String getAllGender();
   public String getAllArea();
   public String getAllAge();
}
