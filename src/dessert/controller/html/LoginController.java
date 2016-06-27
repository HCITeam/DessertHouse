package dessert.controller.html;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.commodity.InventoryRVO;
import dessert.rvo.member.InfoResultVO;
import dessert.rvo.member.LoginResultVO;
import dessert.service.CommodityService;
import dessert.service.MemberService;
import dessert.service.StoreService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("login")
public class LoginController extends HtmlController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	MemberService memberService;
	@Autowired
	StoreService storeService;
	@Autowired
	CommodityService commodityService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		//获得参数
		validator.put(Configure.NAME, params.get(Configure.NAME));
		validator.put(Configure.PASSWORD, params.get(Configure.PASSWORD));
		validator.isRequired(Configure.NAME, ErrorCode.NAME_IS_EMPTY);
		validator.isRequired(Configure.PASSWORD, ErrorCode.PASSWORD_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		
		LoginResultVO rvo=memberService.Login(validator.getS(Configure.NAME), validator.getS(Configure.PASSWORD));
		HttpSession session=session();
		ServletContext sc = request().getServletContext();
		if (rvo.getSuccess()==Configure.SUCCESS_INT) {
			sc.setAttribute(Configure.SUCCESS, Configure.SUCCESS_INT);
			session.setAttribute(Configure.ID, rvo.getMember_id());
			session.setAttribute(Configure.NAME, rvo.getName());
			session.setAttribute(Configure.STATE, rvo.getState());
			//获得商家信息
			//TODO
			Date date=Util.getCurrentDate();
//			Date date=Util.getDateFromString("2016-05-05");
			String[] storeName=storeService.getAllStoreName();
			List<InventoryRVO> firstList=commodityService.getByNameandDate(storeName[0], date);
			List<InventoryRVO> secondList=commodityService.getByNameandDate(storeName[0], Util.theDateAfterday(date, 1));
			List<InventoryRVO> thirdList=commodityService.getByNameandDate(storeName[0], Util.theDateAfterday(date, 2));
			
			String date1 = Util.getDateString(date);
			String date2 = Util.getDateString(Util.theDateAfterday(date, 1));
			String date3 = Util.getDateString(Util.theDateAfterday(date, 2));
//			System.out.println(date1+"dddd:::"+date2);
			
			sc.setAttribute(Configure.VISITED, storeName[0]);
			sc.setAttribute(Configure.STORE_NAME, storeName);
			sc.setAttribute(Configure.DATE_FIRST,firstList);
			sc.setAttribute(Configure.DATE_SECOND, secondList);
			sc.setAttribute(Configure.DATE_THIRD, thirdList);
			sc.setAttribute(Configure.DATE_ONE, date1);
			sc.setAttribute(Configure.DATE_TWO, date2);
			sc.setAttribute(Configure.DATE_THREE, date3);
			
			InfoResultVO info = memberService.getMemberInfo(rvo.getMember_id());
			if (rvo.getSuccess() == Configure.SUCCESS_INT) {
				sc.setAttribute(Configure.COMPELLATION, info.getCompellation());
				sc.setAttribute(Configure.ADDRESS, info.getAddress());
				sc.setAttribute(Configure.PHONE, info.getPhone());
			} 	
			
			return Configure.SUCCESS;
		}
//		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.SUCCESS, Configure.FAIL);
		sc.setAttribute(Configure.MESSAGE, rvo.getMessage());
		return Configure.ERROR;
	}

}
