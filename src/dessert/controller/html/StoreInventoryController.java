package dessert.controller.html;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.commodity.InventoryRVO;
import dessert.service.CommodityService;
import dessert.service.StoreService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("storeInventory")
public class StoreInventoryController extends HtmlController{

	private static final long serialVersionUID = 1L;
	@Autowired
	CommodityService commodityService;
	@Autowired
	StoreService storeService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.S_DATE, params.get(Configure.S_DATE));
		validator.put(Configure.STORE_NAME, params.get(Configure.STORE_NAME));
		validator.isRequired(Configure.S_DATE, ErrorCode.SDATE_IS_EMPTY);
		validator.isRequired(Configure.STORE_NAME, ErrorCode.STORENAME_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		ServletContext sc = request().getServletContext();
		Date date = Util.getDateFromString(validator.getS(Configure.S_DATE));
		String name = validator.getS(Configure.STORE_NAME);

		String[] storeName=storeService.getAllStoreName();
		List<InventoryRVO> firstList = commodityService.getByNameandDate(name, date);
		List<InventoryRVO> secondList = commodityService.getByNameandDate(name, Util.theDateAfterday(date, 1));
		List<InventoryRVO> thirdList = commodityService.getByNameandDate(name, Util.theDateAfterday(date, 2));

		sc.setAttribute(Configure.VISITED, name);
		sc.setAttribute(Configure.STORE_NAME, storeName);
		sc.setAttribute(Configure.DATE_FIRST,firstList);
		sc.setAttribute(Configure.DATE_SECOND, secondList);
		sc.setAttribute(Configure.DATE_THIRD, thirdList);
		
		return Configure.SUCCESS;
	}

}
