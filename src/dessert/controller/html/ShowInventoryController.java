package dessert.controller.html;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.HtmlController;
import dessert.rvo.commodity.InventoryRVO;
import dessert.service.CommodityService;
import dessert.service.MemberService;
import dessert.service.StoreService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("showInventory")
public class ShowInventoryController extends HtmlController{

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
	
	}

	@Override
	public String process(FormValidator validator) {
		ServletContext sc = request().getServletContext();
		
			sc.setAttribute(Configure.SUCCESS, Configure.SUCCESS_INT);
			//TODO
			Date date=Util.getCurrentDate();
//			Date date=Util.getDateFromString("2016-02-28");
			String[] storeName=storeService.getAllStoreName();
			List<InventoryRVO> firstList=commodityService.getByNameandDate(storeName[0], date);
			List<InventoryRVO> secondList=commodityService.getByNameandDate(storeName[0], Util.theDateAfterday(date, 1));
			List<InventoryRVO> thirdList=commodityService.getByNameandDate(storeName[0], Util.theDateAfterday(date, 2));
			
			sc.setAttribute(Configure.VISITED, storeName[0]);
			sc.setAttribute(Configure.STORE_NAME, storeName);
			sc.setAttribute(Configure.DATE_FIRST,firstList);
			sc.setAttribute(Configure.DATE_SECOND, secondList);
			sc.setAttribute(Configure.DATE_THIRD, thirdList);
			return Configure.SUCCESS;
	}

}
