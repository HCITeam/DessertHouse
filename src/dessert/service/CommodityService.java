package dessert.service;

import java.util.Date;
import java.util.List;

import dessert.pvo.CartItemPVO;
import dessert.pvo.ReceiptItemPVO;
import dessert.rvo.ResultVO;
import dessert.rvo.commodity.InventoryRVO;

public interface CommodityService {
    public List<InventoryRVO> getByNameandDate(String name,Date date);
    public List<InventoryRVO> getByIDandDate(int id,Date date);
    public ResultVO reservate(List<CartItemPVO> pvos,String id);
    public ResultVO payByCard(List<ReceiptItemPVO> pvos,int id,int s_id);
    public ResultVO payByCash(List<ReceiptItemPVO> pvos,int s_id);
}
