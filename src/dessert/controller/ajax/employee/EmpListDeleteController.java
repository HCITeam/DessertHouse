package dessert.controller.ajax.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.employee.EmpInfoResultVO;
import dessert.rvo.employee.EmploeeInfoResultVO;
import dessert.rvo.store.StoreRVO;
import dessert.service.EmployeeService;
import dessert.service.StoreService;
import dessert.util.FormValidator;

@Controller("empListDeleteGet")
public class EmpListDeleteController extends AjaxController{
	  
		private static final long serialVersionUID = 1L;
		@Autowired
		EmployeeService employeeService;
		@Autowired
		StoreService storeService;
		@Override
		public String execute() throws Exception {
			return controller(response(), request());
		}

		@Override
		public void validate(Map<String, String> params, FormValidator validator) {
			
		}

		@Override
		public String process(FormValidator validator) {
			List<EmploeeInfoResultVO> rvos=employeeService.getEmploeeDelete();
			List<EmpInfoResultVO> vos=new ArrayList<EmpInfoResultVO>() ;
			for(int i=0;i<rvos.size();i++){
				EmpInfoResultVO vo=new EmpInfoResultVO();
				int iid=rvos.get(i).getS_id();
				if(iid==0){
					vo.setS_name("总店");
				}else{
					StoreRVO storeRVO=storeService.getStore(iid+"");
					vo.setS_name(storeRVO.getName());
				}
				vo.setName(rvos.get(i).getName());
				vo.setType(rvos.get(i).getType());
				vos.add(vo);
			}
			
			Map<String, Object> map=new HashMap<>();
			map.put(Configure.SERVERS, vos);
			setJsonResult(map);
			return Configure.SUCCESS;
		}
}
