package dessert.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.member.ToCashResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("toCash")
public class ToCashController extends AjaxController{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	MemberService memberService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		ToCashResultVO rVo=memberService.ToCash((String)session().getAttribute(Configure.ID));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		map.put(Configure.BALANCE, Util.DoubleToString(rVo.getBalance()));//余额
		map.put(Configure.INTEGRAL, rVo.getIntegral()+"");//积分
		setJsonResult(map);
		return Configure.SUCCESS;
	}
}
