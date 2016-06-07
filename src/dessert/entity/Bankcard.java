package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="bankcard")
public class Bankcard {
@Id
private int id;
private String cardnumber;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCardnumber() {
	return cardnumber;
}
public void setCardnumber(String cardnumber) {
	this.cardnumber = cardnumber;
}

}
