package dessert.pvo;

import dessert.configure.Configure;
import dessert.util.FormValidator;

public class EmployeePVO {
	private String name;
	private String password;
	private int type;
	private int s_id;

	public EmployeePVO() {

	}

	public EmployeePVO(String name, String password, int type, int s_id) {
		this.name = name;
		this.password = password;
		this.type = type;
		this.s_id = s_id;
	}

	public void setFromValidator(FormValidator validator) {
		setName(validator.getS(Configure.NAME));
		setPassword(validator.getS(Configure.PASSWORD));
		setType(validator.getI(Configure.WORK_TYPE));
		setS_id(validator.getI(Configure.S_ID));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

}
