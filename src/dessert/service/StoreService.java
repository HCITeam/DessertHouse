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
	//撤销已删除
	public ResultVO UndeleteStore(String id);
	public ResultVO emptyStore(String id);

	// 清空所有已删除
	public ResultVO emptyAll();

	public ResultVO updateStore(StorePVO pvo, String id);

	public String[] getAllStoreName();

	public Map<Integer, String> getStores();

	public List<StoreRVO> getAllStoreNotDelete();

	public StoreRVO getStore(String id);

	public List<SaleRecordRVO> getSaleRecord(int month, int s_id);
	
	//获得已删除店面
	public List<StoreRVO> getAllStoreDelete();
}
