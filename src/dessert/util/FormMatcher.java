package dessert.util;

public interface FormMatcher {

	/**
	 * @param value 不会为null
	 * @return
	 */
	public boolean isMatch(String value) throws Exception;
	
}
