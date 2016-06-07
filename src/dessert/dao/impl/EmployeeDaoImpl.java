package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.EmployeeDao;
import dessert.entity.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao{

	@Override
	public Employee getFromName(String name) {
		
		return getByColumn(Employee.class, "name", name);
	}

}
