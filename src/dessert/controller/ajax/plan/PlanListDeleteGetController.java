package dessert.controller.ajax.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.plan.PlanInfoRVO;
import dessert.rvo.plan.PlanInfoResultVO;
import dessert.rvo.store.StoreRVO;
import dessert.service.PlanService;
import dessert.service.StoreService;
import dessert.util.FormValidator;
@Controller("planListDeleteGet")
public class PlanListDeleteGetController extends AjaxController{
	  
		private static final long serialVersionUID = 1L;
		@Autowired
		PlanService planService;
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
			List<PlanInfoResultVO> rvos=planService.getDeletePlan();
            List<PlanInfoRVO>vos=new ArrayList<PlanInfoRVO>();
			
			for(int i=0;i<rvos.size();i++){
				PlanInfoResultVO item=rvos.get(i);
				int iid=item.getS_id();
				StoreRVO storeRVO=storeService.getStore(iid+"");
				PlanInfoRVO info=new PlanInfoRVO(item,storeRVO.getName());
				vos.add(info);
			}
			
			Map<String, Object> map=new HashMap<>();
			map.put(Configure.PLAN_LIST, vos);
			setJsonResult(map);
			return Configure.SUCCESS;
		}
}
