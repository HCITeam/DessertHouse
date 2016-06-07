package dessert.pvo;

public class CartItemPVO {
	private String store_name;
	private String p_name;
	private int p_num;
	private double price;
	private int left_num;
	private String s_date;

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
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

	public void setP_num(String p_num) {
		// this.p_num = Integer.parseInt(p_num);
	}

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPrice(String price) {
		this.price = Double.parseDouble(price);
	}
	
	
	public int getLeft_num() {
		return left_num;
	}

	public void setLeft_num(int left_num) {
		this.left_num = left_num;
	}

	public boolean isEqual(CartItemPVO pvo){
		if ((pvo.getP_name().equals(p_name))&&(pvo.getS_date().equals(s_date))&&(pvo.getStore_name().equals(store_name))) {
			return true;
		}
		return false;
	}
}
