package dessert.controller.ajax.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.AjaxController;
import dessert.rvo.member.InfoResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("memBreifInfoGet")
public class MemberInfoGetController extends AjaxController{

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
		String id = (String)session().getAttribute(Configure.ID);
		InfoResultVO infoResultVO = memberService.getMemberInfo(id);
		Map<String, Object> map = new HashMap<String, Object>();
		String phone = (infoResultVO.getPhone()==null)?"0":infoResultVO.getPhone();
		String name = (String)session().getAttribute(Configure.NAME);
		String addr = (infoResultVO.getAddress()==null)?"0":infoResultVO.getAddress();
		map.put(Configure.NAME,name);
		map.put(Configure.PHONE, phone);
		map.put(Configure.ADDRESS, addr);
		setJsonResult(map);
		return null;
	}

}
