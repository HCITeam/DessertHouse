package dessert.rvo.commodity;

import dessert.entity.Inventory;
import dessert.rvo.ResultVO;
import dessert.util.Util;

public class InventoryRVO extends ResultVO{
//	private int id;
	private int s_id;
	private String p_name;
	private int p_num;
	private String s_date;
	private String price;
	
	public void setFromInventory(Inventory inventory){
		setS_id(inventory.getId());
		setP_name(inventory.getP_name());
		setP_num(inventory.getP_num());
		setS_date(Util.getDateString(inventory.getS_date()));
		setPrice(Util.DoubleToString(inventory.getPrice()));
	}
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
