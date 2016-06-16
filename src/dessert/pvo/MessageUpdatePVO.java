package dessert.pvo;

import java.util.Date;

public class MessageUpdatePVO {
private String emp_name;
private Date date;
public MessageUpdatePVO(String emp_name, Date date) {
	super();
	this.emp_name = emp_name;
	this.date = date;
}

public String getEmp_name() {
	return emp_name;
}
public void setEmp_name(String emp_name) {
	this.emp_name = emp_name;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}


}
