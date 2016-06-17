package dessert.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;


@Entity(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String emp_name;
	private String content;
	private Date draftdate;
	@ColumnDefault(value = "0")
	private int isRead;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int isRead() {
		return isRead;
	}
	public void setRead(int isRead) {
		this.isRead = isRead;
	}
	
}
