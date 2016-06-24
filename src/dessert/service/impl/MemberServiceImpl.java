package dessert.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import dessert.configure.Configure;
import dessert.dao.BankcardDao;
import dessert.dao.CardinfoDao;
import dessert.dao.MemberDao;
import dessert.dao.MemberinfoDao;
import dessert.dao.MemberrecordDao;
import dessert.dao.StatisticsDao;
import dessert.entity.Bankcard;
import dessert.entity.Cardinfo;
import dessert.entity.Member;
import dessert.entity.Memberinfo;
import dessert.entity.Memberrecord;
import dessert.entity.Statistics;
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
import dessert.service.MemberService;
import dessert.util.Util;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;
	@Autowired
	BankcardDao bankcardDao;
	@Autowired
	CardinfoDao cardinfoDao;
	@Autowired
	MemberinfoDao memberinfoDao;
	@Autowired
	MemberrecordDao memberrecordDao;
	@Autowired
	StatisticsDao statisticsDao;

	/**
	 * 注册
	 */
	@Override
	public SignInResultVO SignIn(String name, String password) {
		SignInResultVO rVo = new SignInResultVO();
		Member member = memberDao.getByName(name);
		if (member == null) {
			String new_password = DigestUtils.md5DigestAsHex(password.getBytes());
			Member new_member = new Member();
			new_member.setName(name);
			new_member.setPassword(new_password);
			memberDao.add(new_member);
			member = memberDao.getByName(name);

			Date date = Util.getDateFromString("2001-01-01");
			Cardinfo info = new Cardinfo();
			info.setId(member.getId());
			info.setLast_date(date);
			cardinfoDao.add(info);
			rVo.setMidFormInt(member.getId());
			rVo.setName(member.getName());
			rVo.setSuccess(Configure.SUCCESS_INT);
			rVo.setMessage("注册成功");
		} else {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("此用户名已存在");

		}
		return rVo;
	}

	/**
	 * 登录
	 */
	@Override
	public LoginResultVO Login(String identity, String password) {
		System.out.println(password);
		LoginResultVO rVo = new LoginResultVO();
		Member member = null;
		boolean isId = Util.isNumber(identity);
		System.out.println(identity + ":" + isId);
		if (isId) {
			member = memberDao.getByID(Integer.parseInt(identity));
			System.out.println(member.getName());
		} else {
			member = memberDao.getByName(identity);
		}

		if (member == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号不存在");
		} else {
			String new_password = DigestUtils.md5DigestAsHex(password.getBytes());
			if (new_password.equals(member.getPassword())) {
				Cardinfo cardinfo = cardinfoDao.getById(member.getId() + "");// 取得卡信息
				cardinfo=checkCardinfo(cardinfo);//检查状态
				rVo.setBalance(Util.DoubleToString(cardinfo.getBalance()));
				rVo.setGrade(cardinfo.getGrade());
				rVo.setState(cardinfo.getState());
				rVo.setIntegral(cardinfo.getIntegral());
				rVo.setSuccess(Configure.SUCCESS_INT);
				if (cardinfo.getState()==Configure.INACTIVE) {
					rVo.setMessage("您的卡尚未激活，请及时充值以激活");
				}else if (cardinfo.getState()==Configure.PAUSE) {
					rVo.setMessage("您的卡已暂停使用，请及时充值以激活");
				}else if (cardinfo.getState()==Configure.STOP) {
					rVo.setMessage("您的卡已被停止，请到实体店咨询");
				}else {
					rVo.setMessage("登录成功");
					
				}
				rVo.setName(member.getName());
				rVo.setMidFormInt(member.getId());
			} else {
				rVo.setSuccess(Configure.FAIL);
				rVo.setMessage("密码错误");
			}
		}

		return rVo;
	}

	@Override
	public ResultVO addBankcard(String m_id, String bank_num) {
		ResultVO rVo = new ResultVO();
		Member member = memberDao.getByID(Integer.parseInt(m_id));
		if (member == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号不存在");
			return rVo;
		}
		Bankcard bankcard = bankcardDao.getBycardnum(bank_num);
		if (bankcard != null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该银行卡已被占用");
			return rVo;
		}
		Bankcard new_bankcard = new Bankcard();
		new_bankcard.setId(Integer.parseInt(m_id));
		new_bankcard.setCardnumber(bank_num);
		bankcardDao.add(new_bankcard);
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("添加银行卡成功");
		return rVo;
	}

	@Override
	public ResultVO updateBankcard(String m_id, String bank_num) {

		ResultVO rVo = new ResultVO();
		Member member = memberDao.getByID(Integer.parseInt(m_id));
		if (member == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号不存在");
			return rVo;
		}
		Bankcard bankcard = bankcardDao.getBycardnum(bank_num);
		if (bankcard != null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该银行卡已被占用");
			return rVo;
		}
		Bankcard new_bankcard = new Bankcard();
		new_bankcard.setId(member.getId());
		new_bankcard.setCardnumber(bank_num);
		bankcardDao.update(new_bankcard);
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("修改银行卡成功");
		return rVo;
	}

	@Override
	public ResultVO changeBankcard(String m_id, String bank_num) {

		ResultVO rVo = new ResultVO();
		Member member = memberDao.getByID(Integer.parseInt(m_id));
		if (member == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号不存在");
			return rVo;
		}
		Bankcard bankcard = bankcardDao.getBycardnum(bank_num);
		if (bankcard != null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该银行卡已被占用");
			return rVo;
		}
		bankcard = bankcardDao.getByM_id(m_id);
		if (bankcard == null) {// 需要新增
			bankcard = new Bankcard();
			bankcard.setId(member.getId());
			bankcard.setCardnumber(bank_num);
			bankcardDao.add(bankcard);
		} else {
			bankcard.setCardnumber(bank_num);
			bankcardDao.update(bankcard);
		}

		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("修改银行卡成功");
		return rVo;
	}

	@Override
	public ResultVO deleteBankcard(String m_id) {
		ResultVO rVo = new ResultVO();
		Bankcard bankcard = bankcardDao.getByM_id(m_id);
		if (bankcard == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号没有绑定银行卡");
		} else {
			bankcardDao.delete(bankcard);
			rVo.setSuccess(Configure.SUCCESS_INT);
			rVo.setMessage("解绑成功");
		}
		return rVo;
	}

	/**
	 * 充值
	 */
	@Override
	public ResultVO Recharge(RechargePVO pvo) {
		ResultVO rVo = new ResultVO();
		Member member = memberDao.getByID(Integer.parseInt(pvo.getM_id()));
		Cardinfo cardinfo = cardinfoDao.getById(pvo.getM_id());
		Date date = new Date(System.currentTimeMillis());
		if (member == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号不存在");
			return rVo;
		}
		if (cardinfo.getState() == Configure.STOP) {// 已被停止
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("该账号已被停止使用");
			return rVo;
		}
		Memberrecord memberrecord = new Memberrecord();
		if (pvo.getType() == Configure.BY_CARD) {// 银行卡
			if (pvo.getCardnumber().equals(" ")) {
				rVo.setSuccess(Configure.FAIL);
				rVo.setMessage("该账号没有绑定银行卡，请绑定银行卡或者到店面现金充值");
				return rVo;
			}
			memberrecord.setExplanation(Configure.RECHARGE_BYCARD);
		} else if (pvo.getType() == Configure.BY_CASH) {// 现金
			memberrecord.setExplanation(Configure.RECHARGE_BYCASH);
		}
		memberrecord.setM_id(member.getId());
		memberrecord.setType(Configure.RECHARGE);
		memberrecord.setAmount(pvo.getAmount());
		memberrecord.setR_date(date);
		// 修改卡记录
		cardinfo = changeCardInfo(cardinfo, pvo, date);
		// 保存
		memberrecordDao.add(memberrecord);
		cardinfoDao.update(cardinfo);
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("充值成功");
		return rVo;
	}

	private Cardinfo changeCardInfo(Cardinfo info, RechargePVO pvo, Date date) {
		if (info.getState() == Configure.INACTIVE || info.getState() == Configure.PAUSE) {// 需要修改状态
			info.setState(Configure.ACTIVE);
			info.setLast_date(date);
		}
		info.recharge(pvo.getAmount());// 充值
		info.setGrade(Util.getGrage(info.getTotal()));// 修改等级
		return info;
	}

	@Override
	public int getMemberState(String id) {
		Cardinfo cardinfo = cardinfoDao.getById(Cardinfo.class, Integer.parseInt(id));
		if (cardinfo == null) {
			return Configure.FAIL;
		}
		return cardinfo.getState();
	}

	@Override
	public InfoResultVO addMemberInfo(InfoPVO pvo) {
		InfoResultVO infoResultVO = new InfoResultVO();
		Member member = memberDao.getByID(Integer.parseInt(pvo.getId()));
		if (member == null) {
			infoResultVO.setSuccess(Configure.FAIL);
			infoResultVO.setMessage("找不到该会员账号");
			return infoResultVO;
		}
		Memberinfo memberinfo = new Memberinfo();
		memberinfo.setFromInfoPVO(pvo);
		memberinfoDao.add(memberinfo);
		infoResultVO.setSuccess(Configure.SUCCESS_INT);
		infoResultVO.setMessage("新增会员个人信息成功");
		infoResultVO.setInfo(memberinfo);
		return infoResultVO;
	}

	@Override
	public InfoResultVO getMemberInfo(String id) {
		InfoResultVO infoResultVO = new InfoResultVO();
		Memberinfo memberinfo = memberinfoDao.getById(id);
		if (memberinfo == null) {
			infoResultVO.setSuccess(Configure.FAIL);
			infoResultVO.setMessage("该会员尚未填写个人信息");
			return infoResultVO;
		}
		infoResultVO.setSuccess(Configure.SUCCESS_INT);
		infoResultVO.setMessage("会员个人信息");
		infoResultVO.setInfo(memberinfo);
		return infoResultVO;
	}

	@Override
	public InfoResultVO updateMemberInfo(InfoPVO pvo) {
		InfoResultVO infoResultVO = new InfoResultVO();
		Member member = memberDao.getByID(Integer.parseInt(pvo.getId()));
		if (member == null) {
			infoResultVO.setSuccess(Configure.FAIL);
			infoResultVO.setMessage("找不到该会员账号");
			return infoResultVO;
		}
		Memberinfo memberinfo = memberinfoDao.getById(pvo.getId());
		if (memberinfo == null) {
			infoResultVO.setSuccess(Configure.FAIL);
			infoResultVO.setMessage("该会员尚未填写个人信息");
			return infoResultVO;
		}
		memberinfo.setFromInfoPVO(pvo);
		memberinfoDao.update(memberinfo);
		infoResultVO.setSuccess(Configure.SUCCESS_INT);
		infoResultVO.setMessage("修改会员个人信息成功");
		infoResultVO.setInfo(memberinfo);
		return infoResultVO;
	}

	@Override
	public InfoResultVO changeMemberInfo(InfoPVO pvo) {

		InfoResultVO infoResultVO = new InfoResultVO();
		Member member = memberDao.getByID(Integer.parseInt(pvo.getId()));
		if (member == null) {
			infoResultVO.setSuccess(Configure.FAIL);
			infoResultVO.setMessage("找不到该会员账号");
			return infoResultVO;
		}
		Memberinfo memberinfo = memberinfoDao.getById(pvo.getId());
		if (memberinfo == null) {
			memberinfo = new Memberinfo();
			memberinfo.setFromInfoPVO(pvo);
			memberinfoDao.add(memberinfo);
		} else {
			memberinfo.setFromInfoPVO(pvo);
			memberinfoDao.update(memberinfo);
		}
		infoResultVO.setSuccess(Configure.SUCCESS_INT);
		infoResultVO.setMessage("修改会员个人信息成功");
		infoResultVO.setInfo(memberinfo);
		return infoResultVO;
	}

	@Override
	public ResultVO stopMembercard(String id) {
		ResultVO rVo = new ResultVO();
		Cardinfo cardinfo = cardinfoDao.getById(id);
		if (cardinfo == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("找不到该会员账号");
		} else {
			cardinfo.setState(Configure.STOP);
			Memberrecord memberrecord = new Memberrecord();
			memberrecord.setAmount(0);
			memberrecord.setExplanation("停止会员记录");
			memberrecord.setM_id(Integer.parseInt(id));
			memberrecord.setR_date(Util.getCurrentDate());
			memberrecord.setType(Configure.CANCEL);
			cardinfoDao.update(cardinfo);
			memberrecordDao.add(memberrecord);
			rVo.setSuccess(Configure.SUCCESS_INT);
			rVo.setMessage("该账号已成功停止");
		}
		return rVo;
	}

	@Override
	public ToCashResultVO ToCash(String id) {
		ToCashResultVO rVo = new ToCashResultVO();
		Cardinfo info = cardinfoDao.getById(id);
		if (info == null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("会员卡号无效");
			rVo.setIntegral(0);
			rVo.setBalance(0.0);
			return rVo;
		}
		int integral = info.getIntegral();
		double balance = info.getBalance();
		double amount=Util.toCash(integral);
		balance += amount;
		integral = 0;
		info.setBalance(balance);
		info.setIntegral(integral);
		Memberrecord memberrecord = new Memberrecord();
		memberrecord.setAmount(amount);
		memberrecord.setExplanation("积分兑现");
		memberrecord.setM_id(Integer.parseInt(id));
		memberrecord.setR_date(Util.getCurrentDate());
		memberrecord.setType(Configure.CASH);
		cardinfoDao.update(info);
		memberrecordDao.add(memberrecord);
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("兑现成功！");
		rVo.setBalance(balance);
		rVo.setIntegral(integral);
		return rVo;
	}

	@Override
	public CardInfoResultVO getCardInfo(String id) {
		CardInfoResultVO rVo=new CardInfoResultVO();
		Cardinfo cardinfo=cardinfoDao.getById(id);
		if(cardinfo==null){
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("会员卡号无效");
			return rVo;
		}
		rVo.setFromCardinfo(cardinfo);
		Bankcard bankcard=bankcardDao.getByM_id(id);
		if (bankcard==null) {
			rVo.setBackCard(" ");
		}else {
			rVo.setBackCard(bankcard.getCardnumber());
		}
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("获取成功");
		return rVo;
	}

	/**
	 * 低于5块钱则暂停会员卡
	 * @param info
	 * @return
	 */
	private Cardinfo checkCardinfo(Cardinfo info){
		Date dateToday=new Date();
		Date dateThatday=info.getLast_date();
		int days=Util.daysOfTwoDate(dateThatday, dateToday);
		if ((info.getBalance()>=Configure.LEAST_AMOUNT)||(info.getState()==Configure.INACTIVE)) {
			return info;
		} 
		if (days>=730) {//停止
			info.setState(Configure.STOP);
			cardinfoDao.update(info);
		}else if (days>=365) {//暂停
			info.setState(Configure.PAUSE);
			cardinfoDao.update(info);
		}
		return info;
	}

	@Override
	public List<MemberRecordRVO> getAllRecord(String id) {
		List<Memberrecord> list=memberrecordDao.getListByColumn(Memberrecord.class, "m_id", Integer.parseInt(id));
		List<MemberRecordRVO> resultList=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			MemberRecordRVO rvo=new MemberRecordRVO();
			rvo.serFromRecord(list.get(i));
			resultList.add(rvo);
		}
		return resultList;
	}

	@Override
	public StatisticsRVO getStatistics(int month) {
		// TODO Auto-generated method stub
		String[] tempString;
		int[] tempInt;
		double[] tempDouble;
		StatisticsRVO rvo=new StatisticsRVO();
		String[] area= new String[]{"玄武区","鼓楼区","建邺区","秦淮区","雨花台区","浦口区","栖霞区","江宁区","六合区","溧水区","高淳区"};
		String[] age=new String[]{"0-20","20-40","40-60","60-80","80-100"};
		String[] gender=new String[]{"女","男"};
		String[] purchase=new String[]{" ","购买","预定"};
		String[] cardState=new String[]{"未激活","正常","暂停","停止"};
		Statistics statistics=statisticsDao.getByMonth(month);
		//地区
		String[] areaTemp=statistics.getArea().split(";");
		tempString=new String[areaTemp.length];
		tempInt=new int[areaTemp.length];
		for (int i = 0; i < areaTemp.length; i++) {
			tempString[i]=area[Integer.parseInt(areaTemp[i].split(":")[0])];
			tempInt[i]=Integer.parseInt(areaTemp[i].split(":")[1]);
		}
		rvo.setAreaString(tempString);
		rvo.setAreaNum(tempInt);
		//年龄
		String[] ageTemp=statistics.getAge().split(";");
		tempString=new String[ageTemp.length];
		tempInt=new int[ageTemp.length];
		for (int i = 0; i < ageTemp.length; i++) {
			tempString[i]=age[Integer.parseInt(ageTemp[i].split(":")[0])];
			tempInt[i]=Integer.parseInt(ageTemp[i].split(":")[1]);
		}
		rvo.setAgeString(tempString);
		rvo.setAgeNum(tempInt);
		//性别
		String[] gemderTemp=statistics.getGender().split(";");
		tempString=new String[gemderTemp.length];
		tempInt=new int[gemderTemp.length];
		for (int i = 0; i < gemderTemp.length; i++) {
			tempString[i]=gender[Integer.parseInt(gemderTemp[i].split(":")[0])];
			tempInt[i]=Integer.parseInt(gemderTemp[i].split(":")[1]);
		}
		rvo.setGenderString(tempString);
		rvo.setGenderNum(tempInt);
		//消费
		String[] purTemp=statistics.getPurcharse().split(";");
		tempString=new String[purTemp.length];
		tempDouble=new double[purTemp.length];
		for (int i = 0; i < purTemp.length; i++) {
			tempString[i]=purchase[Integer.parseInt(purTemp[i].split(":")[0])];
			tempDouble[i]=Double.parseDouble(purTemp[i].split(":")[1]);
		}
		rvo.setPurcharseString(tempString);
		rvo.setPurcharseNum(tempDouble);
		//卡状态
		String[] cardTemp=statistics.getCardstate().split(";");
		tempString=new String[cardTemp.length];
		tempInt=new int[cardTemp.length];
		for (int i = 0; i < cardTemp.length; i++) {
			tempString[i]=cardState[Integer.parseInt(cardTemp[i].split(":")[0])];
			tempInt[i]=Integer.parseInt(cardTemp[i].split(":")[1]);
		}
		rvo.setStateString(tempString);
		rvo.setStateNum(tempInt);
		rvo.setTotal(statistics.getNum());
		return rvo;
	}
}
