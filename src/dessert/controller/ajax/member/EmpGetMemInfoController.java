package dessert.controller.ajax.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.rvo.member.LoginResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("empGetMemInfo")
public class EmpGetMemInfoController extends AjaxController{

	/**
	 * 根据用户名和密码获得会员卡信息
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	MemberService memberService;
	
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		//获得参数
		validator.put(Configure.ID, params.get(Configure.ID));
		validator.put(Configure.PASSWORD, params.get(Configure.PASSWORD));
		validator.isRequired(Configure.ID, ErrorCode.ID_IS_EMPTY);
		validator.isRequired(Configure.PASSWORD, ErrorCode.PASSWORD_IS_EMPTY);	
	}

	@Override
	public String process(FormValidator validator) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginResultVO login = memberService.Login(validator.getS(Configure.ID), validator.getS(Configure.PASSWORD));
		
		if (login.getSuccess() == Configure.SUCCESS_INT) {//密码正确
//			CardInfoResultVO cardInfoResultVO = memberService.getCardInfo(validator.getS(Configure.ID));
			map.put(Configure.SUCCESS, Configure.SUCCESS_INT);
			map.put(Configure.MESSAGE, "密码正确");
			map.put(Configure.BALANCE, login.getBalance());//余额
			map.put(Configure.INTEGRAL, login.getIntegral());//积分
			map.put(Configure.NAME, login.getName());
		}else {
			map.put(Configure.SUCCESS, Configure.FAIL);
			map.put(Configure.MESSAGE, "密码错误");
		}
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
