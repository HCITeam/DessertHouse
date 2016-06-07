package dessert.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.ResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("cancelMember")
public class MemberCancelController extends AjaxController{


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
		String id=(String)session().getAttribute(Configure.ID);
		ResultVO rvo=memberService.stopMembercard(id);
		Map<String, Object> map=new HashMap<>();
		map.put(Configure.SUCCESS, rvo.getSuccess());
		map.put(Configure.MESSAGE, rvo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}

}
