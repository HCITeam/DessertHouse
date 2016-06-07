package dessert.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dessert.util.FormValidator;

public abstract class BaseController extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 主控制器
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	public abstract String controller(HttpServletResponse response, HttpServletRequest request);

	/**
	 * 登陆验证
	 * 
	 * @return
	 */
	public boolean assertLogin(HttpSession session) {
		return true;
	}

	/**
	 * 参数验证
	 * 
	 * @param validator
	 * @param params
	 */
	public abstract void validate(Map<String, String> params, FormValidator validator);

	/**
	 * 主要逻辑
	 * 
	 * @param validator
	 */
	public abstract String process(FormValidator validator);


	public Map<String, String> getParams() {
		Map<String, String[]> in = request().getParameterMap();
		Map<String, String> out = new HashMap<>();
		Iterator<Entry<String, String[]>> i = in.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<String, String[]> entry = i.next();
			out.put(entry.getKey(), entry.getValue()[0]);
		}
		return out;
	}
	
	protected HttpServletRequest request(){
		HttpServletRequest request = ServletActionContext.getRequest();
		return request;
	}
	
	protected HttpServletResponse response(){
		HttpServletResponse response = ServletActionContext.getResponse();
		return response;
	}
	
	public ActionContext actionContext(){
		ActionContext context = ActionContext.getContext();
		return context;
	}
	
	protected HttpSession session(){
		HttpSession session = request().getSession();
		return session;
	}
}
