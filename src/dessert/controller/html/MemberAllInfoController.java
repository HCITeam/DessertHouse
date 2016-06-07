package dessert.controller.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.member.CardInfoResultVO;
import dessert.rvo.member.MemberRecordRVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("allInfo")
public class MemberAllInfoController extends HtmlController{

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
		validator.isRequired(Configure.ID, ErrorCode.ID_IS_EMPTY);
		
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		CardInfoResultVO rVo = memberService.getCardInfo(validator.getS(Configure.ID));
		List<MemberRecordRVO> allList=memberService.getAllRecord(validator.getS(Configure.ID));
		List<MemberRecordRVO> consumeList=new ArrayList<>();
		List<MemberRecordRVO> rechargeList=new ArrayList<>();
		for (int i = 0; i < allList.size(); i++) {
			if (allList.get(i).getType()==Configure.CASH||allList.get(i).getType()==Configure.RECHARGE) {
				rechargeList.add(allList.get(i));
			}else{
				consumeList.add(allList.get(i));
			}
		}
		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.CARDINFO, rVo);
		sc.setAttribute(Configure.CONSUME_LIST, consumeList);
		sc.setAttribute(Configure.RECHARGE_LIST, rechargeList);
		return Configure.SUCCESS;
	}

}
