package dessert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import dessert.configure.Configure;
import dessert.dao.EmployeeDao;
import dessert.dao.StoreDao;
import dessert.entity.Employee;
import dessert.entity.Store;
import dessert.pvo.EmployeePVO;
import dessert.pvo.EmployeeUpdatePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.employee.EmploeeInfoResultVO;
import dessert.rvo.employee.EmployeeAddResultVO;
import dessert.rvo.employee.EmployeeLoginRVO;
import dessert.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	StoreDao storeDao;

	@Override
	public EmployeeAddResultVO addEmployee(EmployeePVO employeePO) {
		EmployeeAddResultVO rVO = new EmployeeAddResultVO();
		rVO.setName(employeePO.getName());
		rVO.setPassword(employeePO.getPassword());

		Employee employee = employeeDao.getByColumn(Employee.class, "name", employeePO.getName());
		if (employee == null) {
			String password = DigestUtils.md5DigestAsHex(employeePO.getPassword().getBytes());
			Employee new_employee = new Employee();
			new_employee.setName(employeePO.getName());
			new_employee.setPassword(password);
			new_employee.setS_id(employeePO.getS_id());
			new_employee.setType(employeePO.getType());
			employeeDao.add(new_employee);
			if (employeePO.getS_id()==0) {
				rVO.setStore_name("总");
			}else{
				Store store=storeDao.getById(employeePO.getS_id()+"");
				rVO.setStore_name(store.getName());
			}
			rVO.setType(new_employee.getTypeString());
			rVO.setSuccess(Configure.SUCCESS_INT);
			rVO.setMessage("添加成功");
		}else {
			rVO.setStore_name(" ");
			rVO.setSuccess(Configure.FAIL);
			rVO.setMessage("账户已存在");
		}

		return rVO;
	}

	@Override
	public ResultVO deleteEmployee(String name) {
		Employee employee=employeeDao.getFromName(name);
		if (employee!=null) {
			employee.setDelete_flag(Configure.DELETE_FLAG_TRUE);
			employeeDao.update(employee);
		}
		ResultVO rVo=new ResultVO();
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("成功删除");
		return rVo;
	}

	@Override
	public ResultVO updateEmployee(EmployeeUpdatePVO po) {
		ResultVO rVo=new ResultVO();
		Employee employee=employeeDao.getFromName(po.getName());
		if (employee==null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号不存在");
		}else {
			String password = ((po.getPassword().equals("0")||po.getPassword().equals("")) || (po.getPassword()==null))?employee.getPassword():DigestUtils.md5DigestAsHex(po.getPassword().getBytes());
			String name = (po.getName()=="")?employee.getName():po.getName();
			int s_id = (po.getS_id()== -1)?employee.getS_id():po.getS_id();
			int type = (po.getType()== -1)?employee.getType():po.getType();
			employee.setName(name);
			employee.setPassword(password);
			employee.setS_id(s_id);
			employee.setType(type);
			employeeDao.update(employee);
			rVo.setSuccess(Configure.SUCCESS_INT);
			rVo.setMessage("修改成功");
		}
		return rVo;
	}

	@Override
	public ArrayList<EmploeeInfoResultVO> getEmploeeList() {
		List<Employee> employees=(List<Employee>)employeeDao.getListByColumn(Employee.class, "delete_flag", Configure.DELETE_FLAG_FALSE);
		ArrayList<EmploeeInfoResultVO> vos=new ArrayList<>();
		if (employees!=null) {
			for (int i = 0; i < employees.size(); i++) {
				EmploeeInfoResultVO infoResultVO=new EmploeeInfoResultVO();
				infoResultVO.setFromEmployee(employees.get(i));
				vos.add(infoResultVO);
			}
		}
		return vos;
	}

	@Override
	public ArrayList<EmploeeInfoResultVO> getEmploeesByType(int type) {

		List<Employee> employees=employeeDao.getListByColumn(Employee.class, "type", type,1,20,"s_id",true);

		ArrayList<EmploeeInfoResultVO> vos=new ArrayList<>();
		if (employees!=null) {
			for (int i = 0; i < employees.size(); i++) {
				EmploeeInfoResultVO infoResultVO=new EmploeeInfoResultVO();
				if (employees.get(i).getDelete_flag()==0) {
					infoResultVO.setFromEmployee(employees.get(i));
					vos.add(infoResultVO);
				}
			}
		}
		return vos;
	}

	@Override
	public ResultVO ChangePassword(String name, String password) {
		ResultVO rVO=new ResultVO();
		Employee employee=employeeDao.getFromName(name);
		if (employee==null) {
			rVO.setSuccess(Configure.FAIL);
			rVO.setMessage("找不到该账号");
		}else {
			String new_password=DigestUtils.md5DigestAsHex(password.getBytes());
			employee.setPassword(new_password);
			employeeDao.update(employee);
			rVO.setSuccess(Configure.SUCCESS_INT);
			rVO.setMessage("密码修改成功");
		}
		return rVO;
	}

	@Override
	public EmployeeLoginRVO login(String name, String password) {
		EmployeeLoginRVO rVo = new EmployeeLoginRVO();
		Employee employee=employeeDao.getFromName(name);

		if (employee == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号不存在");
		} else {
			String new_password = DigestUtils.md5DigestAsHex(password.getBytes());
			if (new_password.equals(employee.getPassword())) {
				rVo.setSuccess(Configure.SUCCESS_INT);
				rVo.setMessage("登录成功");
				rVo.setName(employee.getName());
				rVo.setS_id(employee.getS_id());
				rVo.setType(employee.getType());
			} else {
				rVo.setSuccess(Configure.FAIL);
				rVo.setMessage("密码错误");
			}
		}

		return rVo;
	}

	@Override
	public ResultVO emptyEmployee(String name) {
		Employee employee=employeeDao.getFromName(name);
		if (employee!=null) {
			employeeDao.delete(employee);
		}
		ResultVO rVo=new ResultVO();
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("成功删除");
		return rVo;
	}

	@Override
	public ResultVO emptyAll() {
		employeeDao.deleteByColumn(Employee.class, "delete_flag", Configure.DELETE_FLAG_TRUE);
		ResultVO rVo=new ResultVO();
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("成功删除");
		return rVo;
	}

	@Override
	public ArrayList<EmploeeInfoResultVO> getEmploeeDelete() {
		List<Employee> employees=employeeDao.getListByColumn(Employee.class, "delete_flag", Configure.DELETE_FLAG_TRUE);
		ArrayList<EmploeeInfoResultVO> vos=new ArrayList<>();
		if (employees!=null) {
			for (int i = 0; i < employees.size(); i++) {
				EmploeeInfoResultVO infoResultVO=new EmploeeInfoResultVO();
				infoResultVO.setFromEmployee(employees.get(i));
				vos.add(infoResultVO);
			}
		}
		return vos;
	}

	@Override
	public ResultVO UndeleteEmployee(String name) {
		Employee employee=employeeDao.getFromName(name);
		ResultVO rVo=new ResultVO();
		if (employee!=null) {
			employee.setDelete_flag(Configure.DELETE_FLAG_FALSE);
			employeeDao.update(employee);
			rVo.setSuccess(Configure.SUCCESS_INT);
			rVo.setMessage("成功撤销");
		}else {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("找不到该雇员");
		}
		return rVo;
	}

	

}
