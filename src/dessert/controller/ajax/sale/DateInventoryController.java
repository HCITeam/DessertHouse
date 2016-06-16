package dessert.controller.ajax.sale;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.rvo.commodity.InventoryRVO;
import dessert.service.CommodityService;
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("dateInventory")
public class DateInventoryController extends AjaxController {

	private static final long serialVersionUID = 1L;
	@Autowired
	CommodityService commodityService;

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
		Date date = Util.getDateFromString(validator.getS(Configure.S_DATE));
		String name = validator.getS(Configure.STORE_NAME);

		List<InventoryRVO> firstList = commodityService.getByNameandDate(name, date);
		List<InventoryRVO> secondList = commodityService.getByNameandDate(name, Util.theDateAfterday(date, 1));
		List<InventoryRVO> thirdList = commodityService.getByNameandDate(name, Util.theDateAfterday(date, 2));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Configure.DATE_FIRST, firstList);
		map.put(Configure.DATE_SECOND, secondList);
		map.put(Configure.DATE_THIRD, thirdList);
		
		setJsonResult(map);
		
		return Configure.SUCCESS;
	}

}
