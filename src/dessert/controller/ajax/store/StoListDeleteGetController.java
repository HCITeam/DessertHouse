package dessert.controller.ajax.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.store.StoreRVO;
import dessert.service.StoreService;
import dessert.util.FormValidator;
@Controller("storeListDeleteGet")
public class StoListDeleteGetController extends AjaxController{
	  
		private static final long serialVersionUID = 1L;
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
			List<StoreRVO> rvos=storeService.getAllStoreDelete();
			Map<String, Object> map=new HashMap<>();
			map.put(Configure.STORE_LIST, rvos);
			setJsonResult(map);
			System.out.println(map.size());
			return Configure.SUCCESS;
		}
}
