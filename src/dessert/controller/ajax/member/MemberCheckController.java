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

@Controller("checkMember")
public class MemberCheckController extends AjaxController {

	/**
	 * 
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
		validator.put(Configure.ID, params.get(Configure.ID));
		validator.put(Configure.PASSWORD, params.get(Configure.PASSWORD));
		validator.isRequired(Configure.ID, ErrorCode.ID_IS_EMPTY);
		validator.isRequired(Configure.PASSWORD, ErrorCode.PASSWORD_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		System.out.println(validator.getS(Configure.PASSWORD));
		Map<String, Object> map=new HashMap<String, Object>();
		LoginResultVO rVo=memberService.Login(validator.getS(Configure.ID),validator.getS(Configure.PASSWORD));
		if (rVo.getSuccess()==Configure.SUCCESS_INT) {
			session().setAttribute(Configure.ID, validator.getS(Configure.ID));//保存ID
			map.put(Configure.STATE, rVo.getState());
			map.put(Configure.BALANCE, rVo.getBalance());
			map.put(Configure.GRADE, rVo.getGrade());
			map.put(Configure.INTEGRAL, rVo.getIntegral());//积分
			map.put(Configure.NAME, rVo.getName());
		}
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
