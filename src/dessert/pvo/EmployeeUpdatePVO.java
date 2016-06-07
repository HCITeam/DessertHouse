package dessert.pvo;

import dessert.configure.Configure;
import dessert.util.FormValidator;

public class EmployeeUpdatePVO {
	private String name;
	private int type;
	private int  s_id;

	public EmployeeUpdatePVO() {

	}

	public EmployeeUpdatePVO(String name, int type, int s_id) {
		this.name = name;
		this.type = type;
		this.s_id = s_id;
	}

	public void setFromValidator(FormValidator validator) {
		setName(validator.getS(Configure.NAME));
		setType(validator.getI(Configure.WORK_TYPE));
		setS_id(validator.getI(Configure.S_ID));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
