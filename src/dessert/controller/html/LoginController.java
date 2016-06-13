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
//			Date date=Util.getDateFromString("2016-02-28");
			String[] storeName=storeService.getAllStoreName();
//			System.err.println(storeName);
//			System.err.println(date.toString());
			List<InventoryRVO> firstList=commodityService.getByNameandDate(storeName[0], date);
			List<InventoryRVO> secondList=commodityService.getByNameandDate(storeName[0], Util.theDateAfterday(date, 1));
			List<InventoryRVO> thirdList=commodityService.getByNameandDate(storeName[0], Util.theDateAfterday(date, 2));
			
			String date1 = Util.getDateString(date);
			String date2 = Util.getDateString(Util.theDateAfterday(date, 1));
			String date3 = Util.getDateString(Util.theDateAfterday(date, 3));
			
			sc.setAttribute(Configure.VISITED, storeName[0]);
			sc.setAttribute(Configure.STORE_NAME, storeName);
			sc.setAttribute(Configure.DATE_FIRST,firstList);
			sc.setAttribute(Configure.DATE_SECOND, secondList);
			sc.setAttribute(Configure.DATE_THIRD, thirdList);
			sc.setAttribute(Configure.DATE_ONE, date1);
			sc.setAttribute(Configure.DATE_TWO, date2);
			sc.setAttribute(Configure.DATE_THREE, date3);
			
			
			//			int size=list.size();
//			int [] s_id=new int[size];
//			String[] p_name=new String[size];
//			int [] p_num=new int[size];
//			String[] s_date=new String[size];
//			
//			for (int i = 0; i < size; i++) {
//				InventoryRVO inventoryRVO=list.get(i);
//				s_id[i]=inventoryRVO.getS_id();
//				p_name[i]=inventoryRVO.getP_name();
//				p_num[i]=inventoryRVO.getP_num();
//				s_date[i]=Util.getDateString(inventoryRVO.getS_date());
//			}
//			
//			sc.setAttribute(Configure.STORE_NAME, storeName);
//			sc.setAttribute(Configure.S_ID, s_id);
//			sc.setAttribute(Configure.P_NAME, p_name);
//			sc.setAttribute(Configure.P_NUM, p_num);
//			sc.setAttribute(Configure.S_DATE, s_date);
			return Configure.SUCCESS;
		}
//		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.SUCCESS, Configure.FAIL);
		sc.setAttribute(Configure.MESSAGE, rvo.getMessage());
		return Configure.ERROR;
	}

}
