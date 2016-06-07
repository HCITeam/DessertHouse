package dessert.dao;

import dessert.entity.Plan;
import dessert.pvo.PlanPVO;

public interface PlanDao extends BaseDao<Plan>{
  public Plan getById(String id);
  public Plan getByPVO(PlanPVO pvo);
}
