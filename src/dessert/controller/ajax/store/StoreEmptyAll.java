package dessert.controller.ajax.store;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.ResultVO;
import dessert.service.StoreService;
import dessert.util.FormValidator;

/**
 * 根据id彻底删除全部已删除店面
 * @author 小春
 *
 */
@Controller("storeEmptyAll")
public class StoreEmptyAll extends AjaxController{
	  
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
			ResultVO rVo=storeService.emptyAll();
			Map<String, Object> map=new HashMap<>();
			map.put(Configure.SUCCESS, rVo.getSuccess());
			map.put(Configure.MESSAGE, rVo.getMessage());
			setJsonResult(map);
			return Configure.SUCCESS;
		}

}
