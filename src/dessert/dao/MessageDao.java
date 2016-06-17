package dessert.dao;
import java.util.Date;

import dessert.entity.Message;
public interface MessageDao extends BaseDao<Message>{
public Message getById(int id);
public Message getByNameAndDate(String emp_name,Date date);

}
