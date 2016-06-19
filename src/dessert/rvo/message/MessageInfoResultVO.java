package dessert.rvo.message;

import java.util.Date;

public class MessageInfoResultVO {
	private int id;
	private String emp_name;
	private String content;
	private Date draftdate;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
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
