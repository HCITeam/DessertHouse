package dessert.controller.html;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.member.InfoResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("showMemberInfo")
public class MemberInfoController extends HtmlController {

	private static final long serialVersionUID = 1L;

	@Autowired
	MemberService memberService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		HttpSession session = session();
		String id = (String) session.getAttribute(Configure.ID);// 取得会员ID
		validator.put(Configure.ID, id);
		validator.isRequired(Configure.ID, ErrorCode.ID_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		String id = validator.getS(Configure.ID);
		InfoResultVO rvo = memberService.getMemberInfo(id);
		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.SUCCESS, rvo.getSuccess());
		sc.setAttribute(Configure.MESSAGE, rvo.getMessage());
		if (rvo.getSuccess() == Configure.SUCCESS_INT) {
			sc.setAttribute(Configure.COMPELLATION, rvo.getCompellation());
			sc.setAttribute(Configure.BIRTHDAY, Util.getDateString(rvo.getBirthday()));
			sc.setAttribute(Configure.GENDER, rvo.getGender());
			sc.setAttribute(Configure.ADDRESS, rvo.getAddress());
			sc.setAttribute(Configure.PHONE, rvo.getPhone());
			sc.setAttribute(Configure.AREA, rvo.getArea());
		} 
			return Configure.SUCCESS;
		
	}

}
