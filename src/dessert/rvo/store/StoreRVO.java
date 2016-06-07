package dessert.rvo.store;

import dessert.entity.Store;
import dessert.rvo.ResultVO;

public class StoreRVO extends ResultVO{
	private int id;
	private String name;
	private String address;
	private String telphone;
	
	public void setFromStore(Store store){
		setId(store.getId());
		setName(store.getName());
		setAddress(store.getAddress());
		setTelphone(store.getTelphone());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
}
