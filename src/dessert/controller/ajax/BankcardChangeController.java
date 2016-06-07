package dessert.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.rvo.ResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("changeBankcard")
public class BankcardChangeController extends AjaxController{

	private static final long serialVersionUID = 1L;
	@Autowired
	MemberService memberService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.BANKCARD, params.get(Configure.BANKCARD));
		validator.isRequired(Configure.BANKCARD, ErrorCode.BANKCARD_IS_EMPTY);
		
	}

	@Override
	public String process(FormValidator validator) {
		ResultVO rVo=memberService.changeBankcard((String)session().getAttribute(Configure.ID), validator.getS(Configure.BANKCARD));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
