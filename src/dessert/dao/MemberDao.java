package dessert.dao;

import dessert.entity.Member;


public interface MemberDao extends BaseDao<Member>{
	
	public Member getByID(int id);
	public Member getByName(String name);
	
}
