package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.StoreDao;
import dessert.entity.Store;

@Repository
@Transactional
public class StoreDaoImpl extends BaseDaoImpl<Store> implements StoreDao{

	@Override
	public Store getByAddr(String Address) {
		
		return getByColumn(Store.class, "address", Address);
	}

	@Override
	public Store getByName(String name) {
		return getByColumn(Store.class, "name", name);
	}

	@Override
	public Store getById(String id) {
		return getByColumn(Store.class, "id", Integer.parseInt(id));
	}

}
