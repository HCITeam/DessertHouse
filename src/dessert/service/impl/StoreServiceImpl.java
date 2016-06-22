package dessert.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.configure.Configure;
import dessert.dao.AccountDao;
import dessert.dao.StoreDao;
import dessert.dao.StoresaleDao;
import dessert.entity.Account;
import dessert.entity.Store;
import dessert.pvo.StorePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.commodity.SaleRecordRVO;
import dessert.rvo.store.StoreRVO;
import dessert.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	StoreDao storeDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	StoresaleDao storeSaleDao;
	
	@Override
	public StoreRVO addStore(StorePVO pvo) {
		StoreRVO resultVO=new StoreRVO();
		Store store=storeDao.getByAddr(pvo.getAddress());
		if (store!=null&&store.getDelete_flag()==Configure.DELETE_FLAG_FALSE) {//存在且未被删除
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("该地址已被占用,请重新检查该地址");
		}else {
			store=new Store();
			store.setAddress(pvo.getAddress());
			store.setName(pvo.getName());
			store.setTelphone(pvo.getTelphone());
			storeDao.add(store);
			store=storeDao.getByAddr(pvo.getAddress());//先保存才能得到id
			Account account=new Account();
			account.setS_id(store.getId());
			accountDao.add(account);
			store=storeDao.getByName(pvo.getName());
			resultVO.setId(store.getId());
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("添加成功");
		}
		return resultVO;
	}

	@Override
	public ResultVO deleteStore(String id) {
		ResultVO resultVO=new ResultVO();
		Store store=storeDao.getById(id);
		if (store==null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该店面");
		}else {
			store.setDelete_flag(Configure.DELETE_FLAG_TRUE);//删除
			storeDao.update(store);
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("删除成功");
		}
		return resultVO;
	}

	@Override
	public ResultVO updateStore(StorePVO pvo,String id) {
		ResultVO resultVO=new ResultVO();
		Store store=storeDao.getById(id);
		if (store==null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该店面");
		}else {
			store.setName(pvo.getName());
			store.setAddress(pvo.getAddress());
			store.setTelphone(pvo.getTelphone());
			storeDao.update(store);
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("修改成功");
		}
		return resultVO;
	}

	@Override
	public String[] getAllStoreName() {
		List<Store> list=storeDao.getListByColumn(Store.class, "delete_flag", Configure.DELETE_FLAG_FALSE);//未删除
		String[] result;
 		if (list==null) {
			return null;
		}
 		result=new String[list.size()];
 		for(int i=0;i<list.size();i++){
 			result[i]=list.get(i).getName();
 		}
		return result;
	}

	@Override
	public List<StoreRVO> getAllStoreNotDelete() {
		List<Store> list=storeDao.getListByColumn(Store.class, "delete_flag", Configure.DELETE_FLAG_FALSE);//未删除
		List<StoreRVO> result=new ArrayList<StoreRVO>();
		for (int i = 0; i < list.size(); i++) {
			StoreRVO rvo=new StoreRVO();
			rvo.setFromStore(list.get(i));
			result.add(rvo);
		}
		return result;
	}

	@Override
	public Map<Integer, String> getStores() {
		List<Store> list=storeDao.getListByColumn(Store.class, "delete_flag", Configure.DELETE_FLAG_FALSE);//未删除
		Map<Integer, String> result=new HashMap<>();
 		if (list==null) {
			return result;
		}
 		for(int i=0;i<list.size();i++){
 			result.put(list.get(i).getId(), list.get(i).getName());
 		}
		return result;
	}

	@Override
	public StoreRVO getStore(String id) {
		StoreRVO rvo=new StoreRVO();
		rvo.setFromStore(storeDao.getById(id));
		return rvo;
	}
	

	@Override
	public List<SaleRecordRVO> getSaleRecord(int month, int s_id) {
		
		return storeSaleDao.getByMonthAndStore(month, s_id);
	}

	@Override
	public ResultVO emptyStore(String id) {
		ResultVO resultVO=new ResultVO();
		Store store=storeDao.getById(id);
		if (store==null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该店面");
		}else {
			storeDao.delete(store);
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("删除成功");
		}
		return resultVO;
	}

	@Override
	public ResultVO emptyAll() {
		ResultVO resultVO=new ResultVO();
		storeDao.deleteByColumn(Store.class, "delete_flag", Configure.DELETE_FLAG_TRUE);
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("删除成功");
		return resultVO;
	}

	
	@Override
	public List<StoreRVO> getAllStoreDelete() {
		List<Store> list=storeDao.getListByColumn(Store.class, "delete_flag", Configure.DELETE_FLAG_TRUE);//删除
		List<StoreRVO> result=new ArrayList<StoreRVO>();
		for (int i = 0; i < list.size(); i++) {
			StoreRVO rvo=new StoreRVO();
			rvo.setFromStore(list.get(i));
			result.add(rvo);
		}
		return result;
	}
	@Override
	public ResultVO UndeleteStore(String id) {
		ResultVO resultVO=new ResultVO();
		Store store=storeDao.getById(id);
		if (store==null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该店面");
		}else {
			store.setDelete_flag(Configure.DELETE_FLAG_FALSE);//撤销
			storeDao.update(store);
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("成功撤销");
		}
		return resultVO;
	}

}
