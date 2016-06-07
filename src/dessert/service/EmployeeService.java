package dessert.service;

import java.util.ArrayList;

import dessert.pvo.EmployeePVO;
import dessert.pvo.EmployeeUpdatePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.employee.EmploeeInfoResultVO;
import dessert.rvo.employee.EmployeeAddResultVO;
import dessert.rvo.employee.EmployeeLoginRVO;

public interface EmployeeService {
   
	//添加工作人员信息
	public EmployeeAddResultVO addEmployee(EmployeePVO po);
	//删除工作人员信息
	public ResultVO deleteEmployee(String name);
	//修改工作人员信息
	public ResultVO updateEmployee(EmployeeUpdatePVO po);
	//得到所有工作人员
	public ArrayList<EmploeeInfoResultVO> getEmploeeList();
	//按类型取得Employee
	public ArrayList<EmploeeInfoResultVO> getEmploeesByType(int type);
	//修改密码
	public ResultVO ChangePassword(String name,String password);
	//工作人员登录
	public EmployeeLoginRVO login(String name,String password);
}
