package dessert.dao;

import dessert.entity.Employee;

public interface EmployeeDao extends BaseDao<Employee>{
	/**
	 * 
	 * @param name 用户名
	 * @return
	 */
    public Employee getFromName(String name);
}
