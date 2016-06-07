package dessert.controller.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.HtmlController;
import dessert.rvo.member.MemberRecordRVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("memberRecord")
public class MemberRecordController extends HtmlController{

	
	private static final long serialVersionUID = 1L;
	@Autowired
	MemberService memberService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String process(FormValidator validator) {
		String id=(String)session().getAttribute(Configure.ID);
		List<MemberRecordRVO> allList=memberService.getAllRecord(id);
		List<MemberRecordRVO> posiList=new ArrayList<>();
		List<MemberRecordRVO> nagList=new ArrayList<>();
		for (int i = 0; i < allList.size(); i++) {
			if (allList.get(i).getType()==Configure.CASH||allList.get(i).getType()==Configure.RECHARGE) {
				posiList.add(allList.get(i));
			}else{
				nagList.add(allList.get(i));
			}
		}
		// TODO Auto-generated method stub
		ServletContext sc=request().getServletContext();
		sc.setAttribute(Configure.ALL_RECORD, allList);
		sc.setAttribute(Configure.POSI_RECORD, posiList);
		sc.setAttribute(Configure.NEG_RECORD, nagList);
		return Configure.SUCCESS;
	}

}
