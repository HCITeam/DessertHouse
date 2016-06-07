package dessert.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.configure.Configure;
import dessert.dao.AccountDao;
import dessert.dao.CardinfoDao;
import dessert.dao.InventoryDao;
import dessert.dao.MemberinfoDao;
import dessert.dao.MemberrecordDao;
import dessert.dao.ReservationDao;
import dessert.dao.StoreDao;
import dessert.dao.StoresaleDao;
import dessert.entity.Account;
import dessert.entity.Cardinfo;
import dessert.entity.Inventory;
import dessert.entity.Memberinfo;
import dessert.entity.Memberrecord;
import dessert.entity.Reservation;
import dessert.entity.Store;
import dessert.entity.Storesale;
import dessert.pvo.CartItemPVO;
import dessert.pvo.ReceiptItemPVO;
import dessert.rvo.ResultVO;
import dessert.rvo.commodity.InventoryRVO;
import dessert.service.CommodityService;
import dessert.util.Util;

@Service
public class CommodityServiceImpl implements CommodityService{

	@Autowired
	StoreDao storeDao;
	@Autowired
	InventoryDao inventoryDao;
	@Autowired
	CardinfoDao cardinfoDao;
	@Autowired
	ReservationDao reservationDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	MemberrecordDao memberRecordDao;
	@Autowired
	MemberinfoDao memberinfoDao;
	@Autowired
	StoresaleDao storesaleDao;
	@Override
	public List<InventoryRVO> getByNameandDate(String name, Date date) {
		List<InventoryRVO> resultList=new ArrayList<>();
		Store store=storeDao.getByName(name);
		if (store==null) {
//			System.out.println("name error");
			return resultList;
		}
//		System.out.println("store name"+store.getName());
		List<Inventory> list=inventoryDao.getByStoreIDandDate(store.getId(), date);
		for (int i = 0; i < list.size(); i++) {
			InventoryRVO rvo=new InventoryRVO();
			rvo.setFromInventory(list.get(i));
			resultList.add(rvo);
		}
		return resultList;
	}

	@Override
	public ResultVO reservate(List<CartItemPVO> pvos, String id) {//预定
		Map<Integer,Double> account=new HashMap<>();
		Timestamp currenttime=Util.getCurrentTimeStamp();
		Cardinfo cardinfo=cardinfoDao.getById(id);
		ResultVO rVo=new ResultVO();
		double amount=0.0;
		if (cardinfo==null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您尚未注册，不能预定");
			return rVo;
		}
		if (cardinfo.getState()==Configure.INACTIVE) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您的卡尚未激活，请及时充值以激活");
			return rVo;
		}
		if (cardinfo.getState()==Configure.PAUSE) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您的卡已暂停使用，请及时充值以激活");
			return rVo;
		}
		if (cardinfo.getState()==Configure.STOP) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您的卡已被停止，请到实体店咨询");
			return rVo;
		}
		for (int i = 0; i < pvos.size(); i++) {
			amount+=pvos.get(i).getPrice()*pvos.get(i).getP_num();
		}
		if (amount>cardinfo.getBalance()) {//检查余额
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您的余额不足");
			return rVo;
		}
		
		for (int i = 0; i < pvos.size(); i++) {
			CartItemPVO pvo=pvos.get(i);
			double money=pvo.getPrice()*pvo.getP_num();
			Store store=storeDao.getByName(pvo.getStore_name());
			Reservation reservation=new Reservation(id, pvo, currenttime, store.getId());
			if (account.containsKey(store.getId())) {
				account.replace(store.getId(), account.get(store.getId())+money);//记录账户变动
			}else {
				account.put(store.getId(), money);
			}
			reservationDao.add(reservation);/* ~  */
			Inventory inventory=inventoryDao.getByStoreIDandDateandName(store.getId(), pvo.getS_date(), pvo.getP_name());
			inventory.sale(pvo.getP_num());
			inventoryDao.update(inventory);/* ~ */
			
			Storesale storesale=new Storesale();
			storesale.setAmount(money);
			storesale.setP_name(pvo.getP_name());
			storesale.setP_num(pvo.getP_num());
			storesale.setS_date(Util.getDateFromString(pvo.getS_date()));
			storesale.setS_id(store.getId());
			storesale.setType(Configure.RESERVE);
			storesaleDao.add(storesale);/* ~ */
		}
		Memberrecord record=new Memberrecord();
		record.setM_id(Integer.parseInt(id));
		record.setR_date(currenttime);
		record.setAmount(amount);
		record.setType(Configure.RESERVE);
		record.setExplanation("在线预订");
		memberRecordDao.add(record);/* ~ */
		cardinfo.useBalance(amount);
		cardinfo.addIntegral(Util.getintegral(amount, cardinfo.getGrade()));
		cardinfoDao.update(cardinfo);/* ~ */
		for ( Map.Entry<Integer,Double> entry: account.entrySet()) {
			Account store_account=accountDao.getByID(entry.getKey());
			store_account.addAccount(entry.getValue());
			accountDao.update(store_account);/* ~ */
		}
		rVo.setSuccess(Configure.SUCCESS_INT);
		Memberinfo memberinfo=memberinfoDao.getById(id);
		if (memberinfo==null) {
			rVo.setMessage("预定成功!为了能顺利派送，请在个人资料中填写送货地址");
		}else{
			rVo.setMessage("预定成功");
		}
		return rVo;
	}

	@Override
	public List<InventoryRVO> getByIDandDate(int id, Date date) {
				List<InventoryRVO> resultList=new ArrayList<>();
				List<Inventory> list=inventoryDao.getByStoreIDandDate(id, date);
				for (int i = 0; i < list.size(); i++) {
					InventoryRVO rvo=new InventoryRVO();
					rvo.setFromInventory(list.get(i));
					resultList.add(rvo);
				}
				return resultList;
	}

	@Override
	public ResultVO payByCard(List<ReceiptItemPVO> pvos, int id,int s_id) {
		double amount=0.0;
		double Dvalue=0.0;
		Date date=Util.getCurrentDate();
//		Date date=Util.getDateFromString("2016-02-28");
		Account account=accountDao.getByID(s_id);
		Timestamp currenttime=Util.getCurrentTimeStamp();
		Cardinfo cardinfo=cardinfoDao.getById(id);
		ResultVO rVo=new ResultVO();
		
		if (cardinfo.getState()==Configure.INACTIVE) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您的卡尚未激活，请及时充值以激活");
			return rVo;
		}
		if (cardinfo.getState()==Configure.PAUSE) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您的卡已暂停使用，请及时充值以激活");
			return rVo;
		}
		if (cardinfo.getState()==Configure.STOP) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您的卡已被停止,请现金支付");
			return rVo;
		}
		
		for (int i = 0; i < pvos.size(); i++) {
			ReceiptItemPVO pvo=pvos.get(i);
			amount+=pvo.getPrice()*pvos.get(i).getP_num();
			Inventory inventory=inventoryDao.getByStoreIDandDateandName(s_id, Util.getDateString(date), pvo.getP_name());
			inventory.sale(pvo.getP_num());
			inventoryDao.update(inventory);/* ~ */
		}
		Memberrecord record=new Memberrecord();
		record.setM_id(id);
		record.setR_date(currenttime);
		record.setAmount(amount);
		record.setType(Configure.PURCHARSE);
		record.setExplanation("线下购买");
		memberRecordDao.add(record);/* ~ */
		
		if (amount>=cardinfo.getBalance()) {
			Dvalue=amount-cardinfo.getBalance();
			account.addAccount(cardinfo.getBalance());
			cardinfo.addIntegral(Util.getintegral(cardinfo.getBalance(), cardinfo.getGrade()));
			cardinfo.setBalance(0.0);
			rVo.setMessage("余额不足，请再支付"+Dvalue+"元");
		}else{
			account.addAccount(amount);
			cardinfo.addIntegral(Util.getintegral(amount, cardinfo.getGrade()));
			cardinfo.useBalance(amount);
			rVo.setMessage("支付成功");
		}
//		accountDao.update(account);/* ~ */
		cardinfoDao.update(cardinfo);/* ~ */
		saveStoreRecord(pvos, s_id, date);/* ~ */
		rVo.setSuccess(Configure.SUCCESS_INT);
		return rVo;
	}

	@Override
	public ResultVO payByCash(List<ReceiptItemPVO> pvos, int s_id) {
//		Date date=Util.getCurrentDate();
		Date date=Util.getDateFromString("2016-02-28");
		ResultVO rVo=new ResultVO();
		for (int i = 0; i < pvos.size(); i++) {
			ReceiptItemPVO pvo=pvos.get(i);
			Inventory inventory=inventoryDao.getByStoreIDandDateandName(s_id, Util.getDateString(date), pvo.getP_name());
			if (inventory.getP_num()<pvo.getP_num()) {//库存不足
				inventory.sale(inventory.getP_num());	
			}else{
				inventory.sale(pvo.getP_num());
			}
			inventoryDao.update(inventory);/* ~ */
		}	
		saveStoreRecord(pvos, s_id, date);/* ~ */
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("支付成功");
		return rVo;
	}

	private void saveStoreRecord(List<ReceiptItemPVO> pvos,int s_id,Date date){
		for (int i = 0; i < pvos.size(); i++) {
			ReceiptItemPVO item=pvos.get(i);
			Storesale storesale=new Storesale();
			storesale.setAmount(item.getPrice()*item.getP_num());
			storesale.setP_name(item.getP_name());
			storesale.setP_num(item.getP_num());
			storesale.setS_date(date);
			storesale.setS_id(s_id);
			storesale.setType(Configure.PURCHARSE);
			storesaleDao.add(storesale);
		}
	}
}
