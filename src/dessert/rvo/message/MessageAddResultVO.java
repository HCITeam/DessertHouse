package dessert.rvo.message;

import java.util.Date;

import dessert.rvo.ResultVO;

public class MessageAddResultVO extends ResultVO{
	private String emp_name;
	private String content;
	private Date draftdate;
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDraftdate() {
		return draftdate;
	}
	public void setDraftdate(Date draftdate) {
		this.draftdate = draftdate;
	}
	
}
