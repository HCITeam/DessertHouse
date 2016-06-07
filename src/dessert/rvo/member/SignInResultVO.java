package dessert.rvo.member;

import dessert.rvo.ResultVO;
import dessert.util.Util;

public class SignInResultVO extends ResultVO {
	private String member_id;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public void setMidFormInt(int id){
		member_id=Util.getIDString(id);
	}
    
}
