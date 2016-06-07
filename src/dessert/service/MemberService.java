package dessert.service;

import java.util.List;

import dessert.pvo.InfoPVO;
import dessert.pvo.RechargePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.member.CardInfoResultVO;
import dessert.rvo.member.InfoResultVO;
import dessert.rvo.member.LoginResultVO;
import dessert.rvo.member.MemberRecordRVO;
import dessert.rvo.member.SignInResultVO;
import dessert.rvo.member.StatisticsRVO;
import dessert.rvo.member.ToCashResultVO;

public interface MemberService {

	// 注册
	public SignInResultVO SignIn(String name, String password);

	// 登录
	public LoginResultVO Login(String identity, String password);

	// 添加银行卡
	public ResultVO addBankcard(String m_id, String bank_num);

	// 修改银行卡
	public ResultVO updateBankcard(String m_id, String bank_num);

	// 修改银行卡
	public ResultVO changeBankcard(String m_id, String bank_num);

	// 删除银行卡
	public ResultVO deleteBankcard(String m_id);

	// 充值
	public ResultVO Recharge(RechargePVO pvo);

	// 新增会员个人信息
	public InfoResultVO addMemberInfo(InfoPVO pvo);

	// 获得会员个人信息
	public InfoResultVO getMemberInfo(String id);

	// 修改会员个人信息
	public InfoResultVO updateMemberInfo(InfoPVO pvo);

	// 会员个人信息新增或修改
	public InfoResultVO changeMemberInfo(InfoPVO pvo);

	// 停止会员卡
	public ResultVO stopMembercard(String id);

	// 获取会员卡状态
	public int getMemberState(String id);
	
	//积分兑现
	public ToCashResultVO ToCash(String id);
	
	//得到账户信息
	public CardInfoResultVO getCardInfo(String id);
	
	//得到会员记录
	public List<MemberRecordRVO> getAllRecord(String id);
	
	//得到会员统计数据
	public StatisticsRVO getStatistics(int month);

}
