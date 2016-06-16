package dessert.controller.ajax.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.pvo.InfoPVO;
import dessert.rvo.member.InfoResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("changeInfo")
public class ChangeMemberInfoController extends AjaxController{

	private static final long serialVersionUID = 1L;
	@Autowired
	MemberService memberService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.ID, (String)session().getAttribute(Configure.ID));
		validator.put(Configure.COMPELLATION, params.get(Configure.COMPELLATION));
		validator.put(Configure.BIRTHDAY, params.get(Configure.BIRTHDAY));
		validator.put(Configure.GENDER, params.get(Configure.GENDER));
		validator.put(Configure.ADDRESS, params.get(Configure.ADDRESS));
		validator.put(Configure.PHONE, params.get(Configure.PHONE));
		validator.put(Configure.AREA, params.get(Configure.AREA));
		validator.isRequired(Configure.ID, ErrorCode.ID_IS_EMPTY);
		validator.isRequired(Configure.COMPELLATION, ErrorCode.COMPELLATION_IS_EMPTY);
		validator.isRequired(Configure.BIRTHDAY, ErrorCode.BIRTHDAY_IS_EMPTY);
		validator.isRequired(Configure.GENDER, ErrorCode.GENDER_IS_EMPTY);
		validator.isRequired(Configure.ADDRESS, ErrorCode.ADDRESS_IS_EMPTY);
		validator.isRequired(Configure.PHONE, ErrorCode.PHONE_IS_EMPTY);
		validator.isRequired(Configure.AREA, ErrorCode.AREA_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
//		ServletContext sc=request().getServletContext();
		InfoPVO pvo=new InfoPVO();
		pvo.setFromValids(validator);
		InfoResultVO rvo=memberService.changeMemberInfo(pvo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Configure.SUCCESS, rvo.getSuccess());
		map.put(Configure.MESSAGE, rvo.getMessage());
		setJsonResult(map);
	    return Configure.SUCCESS;
	}

}
