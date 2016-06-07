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
import dessert.util.FormValidator;
import dessert.util.Util;

@Controller("sale")
public class SaleController extends HtmlController{

	@Autowired
	CommodityService commodityService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		
	}

	@Override
	public String process(FormValidator validator) {
		Date date=Util.getCurrentDate();
		session().removeAttribute(Configure.RECEIPT_LIST);
//		Date date=Util.getDateFromString("2016-02-28");
		List<InventoryRVO> list=commodityService.getByIDandDate((int)session().getAttribute(Configure.S_ID), date);
		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.INVENTORY_LIST, list);
		return Configure.SUCCESS;
	}
}
