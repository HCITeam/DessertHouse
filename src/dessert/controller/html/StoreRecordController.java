package dessert.controller.html;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.commodity.SaleRecordRVO;
import dessert.service.StoreService;
import dessert.util.FormValidator;

@Controller("storeRecord")
public class StoreRecordController extends HtmlController{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	StoreService storeService;
	@Override
	public String execute() throws Exception {	
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.MONTH, params.get(Configure.MONTH));
		validator.put(Configure.S_ID, params.get(Configure.S_ID));
		validator.isRequired(Configure.MONTH, ErrorCode.MONTH_IS_EMPTY);
		validator.isRequired(Configure.S_ID, ErrorCode.ID_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		
		Map<Integer, String>  store=storeService.getStores();
		List<SaleRecordRVO> rvos=storeService.getSaleRecord(validator.getI(Configure.MONTH), validator.getI(Configure.S_ID));
		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.S_ID, validator.getI(Configure.S_ID));
		sc.setAttribute(Configure.MONTH, validator.getI(Configure.MONTH));
		sc.setAttribute(Configure.STORE_LIST, store);
		sc.setAttribute(Configure.SALE_RECORD, rvos);
		return Configure.SUCCESS;
	}

}
