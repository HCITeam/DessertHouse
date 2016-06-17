package dessert.controller.html;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.controller.HtmlController;
import dessert.rvo.store.StoreRVO;
import dessert.service.StoreService;
import dessert.util.FormValidator;

@Controller("manageStore")
public class StoreManageController extends HtmlController{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	StoreService storeService;
	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String process(FormValidator validator) {
		// TODO Auto-generated method stub
		ServletContext sc = request().getServletContext();
		List<StoreRVO> list=storeService.getAllStoreNotDelete();	
		System.err.println("you find me!!!!!!");
		sc.setAttribute(Configure.STORE_LIST, list);
		return Configure.SUCCESS;
	}

}
