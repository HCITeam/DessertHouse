package dessert.rvo.member;

import java.util.Date;

import dessert.entity.Memberrecord;
import dessert.util.Util;

public class MemberRecordRVO {
	private String amount;
	private String r_date;
	private int type;
	private String explanation;
	public void serFromRecord(Memberrecord record){
		setAmount(record.getAmount());
		setR_date(record.getR_date());
		setType(record.getType());
		setExplanation(record.getExplanation());
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setAmount(Double amount) {
		this.amount = Util.DoubleToString(amount);
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public void setR_date(Date date){
		r_date=Util.getDateString(date);
	}
}
