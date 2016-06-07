package dessert.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.StorePVO;
import dessert.rvo.ResultVO;
import dessert.service.StoreService;
import dessert.util.FormValidator;

@Controller("updateStore")
public class StoreUpdateController extends AjaxController{
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
		validator.put(Configure.STORE_NAME, params.get(Configure.STORE_NAME));
		validator.put(Configure.ADDRESS, params.get(Configure.ADDRESS));
		validator.put(Configure.PHONE, params.get(Configure.PHONE));
		validator.isRequired(Configure.S_ID, ErrorCode.ID_IS_EMPTY);
		validator.isRequired(Configure.STORE_NAME, ErrorCode.STORENAME_IS_EMPTY);
		validator.isRequired(Configure.ADDRESS, ErrorCode.ADDRESS_IS_EMPTY);
		validator.isRequired(Configure.PHONE, ErrorCode.PHONE_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		StorePVO pvo=new StorePVO();
		pvo.setName(validator.getS(Configure.STORE_NAME));
		pvo.setAddress(validator.getS(Configure.ADDRESS));
		pvo.setTelphone(validator.getS(Configure.PHONE));
		
		ResultVO rVo=storeService.updateStore(pvo, validator.getS(Configure.S_ID));
		
		Map<String, Object> map=new HashMap<>();
//		map.put(Configure.S_ID, rVo.getId());	
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}}
