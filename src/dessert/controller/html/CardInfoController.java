package dessert.controller.html;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.HtmlController;
import dessert.rvo.member.CardInfoResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("showCardInfo")
public class CardInfoController extends HtmlController {

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
	
		CardInfoResultVO rVo = memberService.getCardInfo((String)session().getAttribute(Configure.ID));
		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.SUCCESS, rVo.getSuccess());
		sc.setAttribute(Configure.MESSAGE, rVo.getMessage());
		if (rVo.getSuccess() == Configure.SUCCESS_INT) {
			sc.setAttribute(Configure.ID, rVo.getId());
			sc.setAttribute(Configure.NAME, (String)session().getAttribute(Configure.NAME));
			sc.setAttribute(Configure.BALANCE, rVo.getBalance());
			sc.setAttribute(Configure.STATE, rVo.getStateString());
			sc.setAttribute(Configure.TOTAL, rVo.getTotal());
			sc.setAttribute(Configure.GRADE, rVo.getGrade());
			sc.setAttribute(Configure.INTEGRAL, rVo.getIntegral());
			sc.setAttribute(Configure.BANKCARD, rVo.getBackCard());
		}
		return Configure.SUCCESS;
		
	}

}
