package dessert.service;

import java.util.List;
import java.util.Map;

import dessert.pvo.StorePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.commodity.SaleRecordRVO;
import dessert.rvo.store.StoreRVO;

public interface StoreService {

	public StoreRVO addStore(StorePVO pvo);
	
	public ResultVO deleteStore(String id);
	
	public ResultVO updateStore(StorePVO pvo,String id);
	
	public String[] getAllStoreName();
	
	public Map<Integer, String> getStores();
	
	public List<StoreRVO> getAllStoreNotDelete();
	
	public StoreRVO getStore(String id);
	
	 public List<SaleRecordRVO> getSaleRecord(int month,int s_id);
}
