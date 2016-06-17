package dessert.pvo;

import java.util.Date;

public class MessagePVO {
	private String content;
	private String employee_name;
	private Date date;

	public MessagePVO(String content, String employee_name, Date date) {
		super();
		this.content = content;
		this.employee_name = employee_name;
		this.date = date;
	}

	public MessagePVO() {
		// TODO Auto-generated constructor stub
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
