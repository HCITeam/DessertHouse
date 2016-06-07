package dessert.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dessert.configure.Configure;
import dessert.util.FormValidator;

public abstract class HtmlController extends BaseController{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public abstract String execute() throws Exception;

	@Override
	public String controller(HttpServletResponse response, HttpServletRequest request) {
		Map<String, String> params = getParams();
		HttpSession session=session();
		ServletContext sc = request.getServletContext();
		if (!assertLogin(session)) {
			sc.setAttribute(Configure.SUCCESS, Configure.FAIL);
			sc.setAttribute(Configure.MESSAGE, "请先登录");
		    return Configure.ERROR;
		}
		FormValidator validator = new FormValidator();
		validate(params, validator);
		if (validator.hasErrors()) {
			sc.setAttribute(Configure.SUCCESS, Configure.FAIL);
			sc.setAttribute(Configure.MESSAGE, validator.getLastError() + "");
//			sc.setAttribute(Configure.RESULTVO, new ResultVO(Configure.FAIL, validator.getLastError() + ""));
		    return Configure.ERROR;
		}
		// 逻辑
		return process(validator);
	}

	


}
