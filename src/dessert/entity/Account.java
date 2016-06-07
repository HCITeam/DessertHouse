package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

@Entity(name="account")
public class Account {
@Id
private int s_id;
@ColumnDefault(value = "0.0")
private double amount;
public int getS_id() {
	return s_id;
}
public void setS_id(int s_id) {
	this.s_id = s_id;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public void addAccount(double amount){
	this.amount+=amount;
}
}
