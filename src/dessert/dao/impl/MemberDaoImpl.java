package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.MemberDao;
import dessert.entity.Member;

@Repository
@Transactional
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao{
	
	
	@Override
	public Member getByID(int id) {
		return getByColumn(Member.class, "id", id);
	}

	@Override
	public Member getByName(String name) {
		return getByColumn(Member.class, "name", name);
	}
}
