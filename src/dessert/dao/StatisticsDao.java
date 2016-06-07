package dessert.dao;

import dessert.entity.Statistics;

public interface StatisticsDao extends BaseDao<Statistics>{
     public Statistics getByMonth(int month);
}
