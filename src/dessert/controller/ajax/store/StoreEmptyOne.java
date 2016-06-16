package dessert.controller.ajax.store;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.rvo.ResultVO;
import dessert.service.StoreService;
import dessert.util.FormValidator;
@Controller("storeEmptyOne")
public class StoreEmptyOne extends AjaxController{
	  
		private static final long serialVersionUID = 1L;
		@Autowired
		StoreService storeService;
		@Override
		public String execute() throws Exception {
			return controller(response(), request());
		}

		@Override
		public void validate(Map<String, String> params, FormValidator validator) {
			validator.put(Configure.S_ID, params.get(Configure.S_ID));
			validator.isRequired(Configure.S_ID, ErrorCode.ID_IS_EMPTY);	
		}

		@Override
		public String process(FormValidator validator) {
			ResultVO rVo=storeService.emptyStore(validator.getS(Configure.S_ID));
			Map<String, Object> map=new HashMap<>();
			map.put(Configure.SUCCESS, rVo.getSuccess());
			map.put(Configure.MESSAGE, rVo.getMessage());
			setJsonResult(map);
			return Configure.SUCCESS;
		}

}
