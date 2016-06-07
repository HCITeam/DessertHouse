package dessert.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dessert.configure.Configure;
import dessert.util.FormValidator;

public abstract class AjaxController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> jsonResult;
	@Override
	public abstract String execute() throws Exception;

	@Override
	public String controller(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> params = getParams();
		HttpSession session=session();
//		ServletContext sc = request.getServletContext();
		if (!assertLogin(session)) {
//			sc.setAttribute(Configure.SUCCESS, Configure.FAIL);
//			sc.setAttribute(Configure.MESSAGE, "请先登录");
			map.put(Configure.SUCCESS, Configure.FAIL);
			map.put(Configure.MESSAGE, "请先登录");
			setJsonResult(map);
//			setSuccess(Configure.FAIL);
//			setMessage("请先登录");
		    return Configure.ERROR;
		}
		FormValidator validator = new FormValidator();
		validate(params, validator);
		if (validator.hasErrors()) {
//			sc.setAttribute(Configure.SUCCESS, Configure.FAIL);
//			sc.setAttribute(Configure.MESSAGE, validator.getLastError() + "");
			map.put(Configure.SUCCESS, Configure.FAIL);
			map.put(Configure.MESSAGE, validator.getLastError() + "");
			setJsonResult(map);
//			setSuccess(Configure.FAIL);
//			setMessage(validator.getLastError() + "");
		    return Configure.ERROR;
		}
		// 逻辑
		return process(validator);
	}

	/**
	 * 登陆验证
	 * 
	 * @return
	 */
	@Override
	public boolean assertLogin(HttpSession session) {
		String name=(String)session.getAttribute(Configure.NAME);
		if (name==null) {
			return false;
		}
		return true;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	

}
