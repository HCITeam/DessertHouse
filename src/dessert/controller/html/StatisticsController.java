package dessert.controller.html;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.member.StatisticsRVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("statistics")
public class StatisticsController extends HtmlController{

	
	private static final long serialVersionUID = 1L;
	@Autowired
	MemberService memberService;
	@Override
	public String execute() throws Exception {	
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.MONTH, params.get(Configure.MONTH));
		validator.isRequired(Configure.MONTH, ErrorCode.MONTH_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		StatisticsRVO rvo=memberService.getStatistics(validator.getI(Configure.MONTH));
		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.MONTH, validator.getI(Configure.MONTH));
		sc.setAttribute(Configure.STATISTICS, rvo);
		return Configure.SUCCESS;
	}

}
