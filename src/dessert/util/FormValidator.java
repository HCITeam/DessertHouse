package dessert.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 表单验证器
 * @author Richard
 * 第一步：构建。调用put方法设置原始值。
 * 第二步：验证。调用is方法进行验证。
 * 第三步：过滤。调用get方法获得处理后的值。
 */
public final class FormValidator {
	private final Map<String, Object> map;
	private final ArrayList<Integer> errors;
	
	public FormValidator(){
		this.map = new HashMap<String, Object>();
		this.errors = new ArrayList<Integer>();
	}
	//===================================put========================================
	/**
	 * 添加表单参数，该方法应当在第一阶段时调用
	 * @param key 表单键
	 * @param value 表单原始值
	 */
	public void put(String key, Object value){
		if(value!=null){
			map.put(key, value);
		}
	}
	
	public void put(String key, String value){
		if(value!=null && !value.trim().equals("")){
			map.put(key, value);
		}
	}

	public boolean has(String key) {
		return map.get(key)!=null;
	}
	
	//===================================get========================================
	/**
	 * 按照bool返回表单值，调用此方法前应当验证isBoolean
	 * 只有true和false作为布尔值，不区分大小写
	 * @param key 表单键
	 * @return
	 */
	public boolean getB(String key){
		String value = _valueOf(key);
		if(value==null){
			throw new NumberFormatException();
		}
		if(value.toLowerCase().equals("true")){
			return true;
		}
		if(value.toLowerCase().equals("false")){
			return false;
		}
		throw new FormFormatException();
	}
	
	/**
	 * 按照int返回表单值，调用此方法前应当验证isInteger（或类似正则表达式）
	 * @param key 表单值
	 * @return
	 */
	public int getI(String key){
		String value = _valueOf(key);
		try{
			return Integer.parseInt(value, 10);
		} catch(NumberFormatException e){
			throw new FormFormatException();
		}
	}

	/**
	 * 按照long返回表单值，调用此方法前应当验证isLong（或类似正则）
	 * @param key 表单键
	 * @return
	 */
	public long getL(String key){
		String value = _valueOf(key);
		try{
			return Long.parseLong(value, 10);
		}catch(NumberFormatException e){
			throw new FormFormatException();
		}
	}
	
	/**
	 * 按照字符串返回表单值
	 * @param key 表单键
	 * @return
	 */
	public String getS(String key) {
		return _valueOf(key);
	}
	
	/**
	 * 按照字符串返回文件类型表单值
	 * @param key 表单键
	 * @return
	 */
	public MultipartFile getF(String key){
		Object obj = _obj_valueOf(key);
		if(obj==null){
			return null;
		}else{
			return (MultipartFile) obj;
		}
	}
	
	//===================================error========================================
	/**
	 * 验证表单当前状态是否有错误，该方法应当在第三阶段前调用
	 * @return
	 */
	public boolean hasErrors() {
		return !this.errors.isEmpty();
	}

	/**
	 * 是否带有指定错误
	 * @param i
	 * @return
	 */
	public boolean hasError(int i) {
		return this.errors.contains(i);
	}
	
	/**
	 * 主动添加一个错误
	 */
	public void addError(int error) {
		this.errors.add(error);
	}
	
	/**
	 * 获取当前所有错误
	 * @return
	 */
	public int[] getErrors() {
		int[] result = new int[this.errors.size()];
		for(int i=0; i<result.length; i++){
			result[i] = this.errors.get(i);
		}
		return result;
	}
	
	public int getLastError(){
		return this.errors.get(this.errors.size()-1);
	}
	//===================================is========================================
	/**
	 * 该表单参数是必须的
	 * @param key 表单键
	 * @return 
	 */
	public void isRequired(String key, int code){
		String value = _valueOf(key);
		if(value==null || value.trim().equals("")){
			this.errors.add(code);
		}
	}

	/**
	 * 该表单参数是必须的，但是可以为空
	 * @param key
	 * @param code
	 */
	public void isRequiredWithEmpty(String key, int code){
		String value = _valueOf(key);
		if(value==null){
			this.errors.add(code);
		}
	}
	
	/**
	 * 表单值是否等于给定值
	 * @param key
	 * @param value
	 * @param i
	 */
	public void isEquals(String key, String value, int i) {
		String val = _valueOf(key);
		if(val==value || val!=null && val.equals(value)){
			return;
		}
		this.errors.add(i);
	}

	/**
	 * 该表单参数匹配正则正则表达式
	 * @param key 表单键
	 * @param pattern 正则表达式
	 */
	public void isMatch(String key, String pattern, int code) {
		String value = _valueOf(key);
		if(value==null || !value.matches(pattern)){
			this.errors.add(code);
		}
	}
	public void isMatchIfExist(String key, String pattern, int code) {
		String value = _valueOf(key);
		if(value!=null && !value.matches(pattern)){
			this.errors.add(code);
		}
	}

	/**
	 * 私人定制，验证参数格式
	 * @param key
	 * @param matcher
	 * @param code
	 */
	public void isMatch(String key, FormMatcher matcher, int code){
		String value = _valueOf(key);
		try{
			if(value!=null && matcher.isMatch(value)){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * 验证符合bool格式
	 * @param key 表单键
	 */
	public void isBool(String key, int code) {
		String value = _valueOf(key);
		if(value==null || (!value.toLowerCase().equals("true") && !value.toLowerCase().equals("false"))){
			this.errors.add(code);
		}
	}

	/**
	 * 验证符合int格式
	 * @param key
	 * @param code
	 */
	public void isInt(String key, int code) {
		if(!testInt(key)){
			this.errors.add(code);
		}
	}

	public boolean testInt(String key) {
		try{
			String value = _valueOf(key);
			Integer.parseInt(value, 10);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 验证如果不是int格式，替换为i
	 * @param key
	 * @param i
	 */
	public void isIntOtherwiseReplace(String key, int i) {
		if(!testInt(key)){
			this.map.put(key, String.valueOf(i));
		}
	}

	
	/**
	 * 验证符合long格式
	 * @param key
	 * @param code
	 */
	public void isLong(String key, int code){
		try{
			String value = _valueOf(key);
			Long.parseLong(value, 10);
		}catch(Exception e){
			this.errors.add(code);
		}
	}

	/**
	 * 是否满足ID， long>=0
	 * @param key
	 * @param code
	 */
	public void isId(String key, int code){
		this.isLong(key, code);
		this.isL(key, 0, code);
	}
	
	/**
	 * 是否存在数组中
	 * @param key
	 * @param types
	 * @param code
	 */
	public void isIn(String key, int[] types, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value);
			for(int type: types){
				if(type==i){
					return;
				}
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	

	public void isIn(String key, String[] strings, int code) {
		String value = _valueOf(key);
		for(String type: strings){
			if(type.equals(value)){
				return;
			}
		}
		this.errors.add(code);
	}
	
	//=============is======int===========min,max  L=Left l=left R=Right r=right===========================
	/**
	 * [min, +∞)
	 * 
	 * @param key
	 * @param min
	 * @param code
	 */
	public void isL(String key, int min, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value, 10);
			if(i>=min){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (min, +∞)
	 * 
	 * @param key
	 * @param min
	 * @param code
	 */
	public void isl(String key, int min, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value, 10);
			if(i>min){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}

	/**
	 * [min, max)
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param code
	 */
	public void isLr(String key, int min, int max, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value, 10);
			if(i>=min && i<max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * [min, max]
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param code
	 */
	public void isLR(String key, int min, int max, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value, 10);
			if(i>=min && i<=max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (min, max)
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param code
	 */
	public void islr(String key, int min, int max, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value, 10);
			if(i>min && i<max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (min, max]
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param code
	 */
	public void islR(String key, int min, int max, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value, 10);
			if(i>min && i<=max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (-∞, max]
	 * 
	 * @param key
	 * @param max
	 * @param code
	 */
	public void isR(String key, int max, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value, 10);
			if(i<=max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (-∞, max)
	 * 
	 * @param key
	 * @param max
	 * @param code
	 */
	public void isr(String key, int max, int code){
		try{
			String value = _valueOf(key);
			int i = Integer.parseInt(value, 10);
			if(i<max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	//=============is======long===========min,max  L=Left l=left R=Right r=right===========================
	/**
	 * [min, +∞)
	 * 
	 * @param key
	 * @param min
	 * @param code
	 */
	public void isL(String key, long min, int code){
		try{
			String value = _valueOf(key);
			long i = Long.parseLong(value, 10);
			if(i>=min){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (min, +∞)
	 * 
	 * @param key
	 * @param min
	 * @param code
	 */
	public void isl(String key, long min, int code){
		try{
			String value = _valueOf(key);
			long i = Long.parseLong(value, 10);
			if(i>min){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}

	/**
	 * [min, max)
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param code
	 */
	public void isLr(String key, long min, long max, int code){
		try{
			String value = _valueOf(key);
			long i = Long.parseLong(value, 10);
			if(i>=min && i<max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * [min, max]
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param code
	 */
	public void isLR(String key, long min, long max, int code){
		try{
			String value = _valueOf(key);
			long i = Long.parseLong(value, 10);
			if(i>=min && i<=max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (min, max)
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param code
	 */
	public void islr(String key, long min, long max, int code){
		try{
			String value = _valueOf(key);
			long i = Long.parseLong(value, 10);
			if(i>min && i<max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (min, max]
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param code
	 */
	public void islR(String key, long min, long max, int code){
		try{
			String value = _valueOf(key);
			long i = Long.parseLong(value, 10);
			if(i>min && i<=max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (-∞, max]
	 * 
	 * @param key
	 * @param max
	 * @param code
	 */
	public void isR(String key, long max, int code){
		try{
			String value = _valueOf(key);
			long i = Long.parseLong(value, 10);
			if(i<=max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}
	
	/**
	 * (-∞, max)
	 * 
	 * @param key
	 * @param max
	 * @param code
	 */
	public void isr(String key, long max, int code){
		try{
			String value = _valueOf(key);
			long i = Long.parseLong(value, 10);
			if(i<max){
				return;
			}
		}catch(Exception e){}
		this.errors.add(code);
	}


	//===================================others========================================
	/**
	 * 设置默认参数
	 * @param key 表单键
	 * @param b 默认值
	 */
	public void optional(String key, boolean b) {
		 String value = _valueOf(key);
		 if(value==null){
			 this.map.put(key, String.valueOf(b));
		 }
	}

	/**
	 * 设置默认参数
	 * @param key
	 * @param def
	 */
	public void optional(String key, String def) {
		String value = _valueOf(key);
		if(value==null){
			this.map.put(key, def);
		}
	}

	public void optional(String key) {
		optional(key, "");
	}

	/**
	 * 设置默认参数
	 * @param key
	 * @param i
	 */
	public void optional(String key, int i) {
		optional(key, String.valueOf(i));
	}
	
	private String _valueOf(String key){
		Object value = this.map.get(key);
		if(value==null){
			return null;
		}
		return value.toString();
	}
	
	private Object _obj_valueOf(String key){
		Object value = this.map.get(key);
		return value;
	}
}
