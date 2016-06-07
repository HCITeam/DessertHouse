package dessert.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dessert.configure.Configure;

public class Util {

	public static String getIDString(int id) {
		String str = String.format("%07d", id);
		return str;
	}

	public static Date getCurrentDate() {
		Date date=new Date();
		String date_string=getDateString(date);
		date=getDateFromString(date_string);
		return date;
	}
	
	public static Timestamp getCurrentTimeStamp(){
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

	public static boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 根据消费金额获得积分
	 * @param amount 消费金额
	 * @return 积分
	 * 每消费10元得1分
	 * 等级不同积分不同
	 */
	public static int getintegral(double amount,int grade){
		int integral=((int)amount/10)*grade;
		return integral;
	}
	
	
	/**
	 * 根据充值总金额获得等级
	 * @param total 总金额
	 * @return
	 * 200~500：一等级
	 * 500~1000：二等级
	 * 1000~5000：三等级
	 * 5000以上：very very VIP
	 */
	public static int getGrage(double total){
		if (total>=5000) {
			return Configure.FORTH_LEVEL;
		}else if (total>=1000) {
			return Configure.THIRD_LEVEL;
		}else if (total>=500) {
			return Configure.SECOND_LEVEL;
		}else{
			return Configure.FIRST_LEVEL;
		}
	}
	
	public static double toCash(int integral){
		double amount=integral/10;
		return amount;
	}
	
	public static String DoubleToString(double input){
		DecimalFormat format=new DecimalFormat("#.00");
		return format.format(input);
	}
	
	public static String getDateString(Date date){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	public static String getDateTimeString(Date date){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * String转date,保证yyyy-MM-dd格式
	 * @param dateString
	 * @return
	 */
	public static Date getDateFromString(String dateString){
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			 date= dateFormat1.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static Date theDateAfterday(Date date,int num){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		date=calendar.getTime();
		return date;
	}
	
	public static Date theDateWhinday(Date date){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string_date=getDateString(date);
		string_date+=" 23:59:59";
		try {
			date=format.parse(string_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static int daysOfTwoDate(Date firstDate,Date secondDate){
		Calendar calendarA=Calendar.getInstance();
		Calendar calendarB=Calendar.getInstance();
		calendarA.setTime(firstDate);
		calendarB.setTime(secondDate);
		int days=(int)Math.abs((calendarB.getTimeInMillis()-calendarA.getTimeInMillis())/(24*60*60*1000));
		return days;
	}
	public static int getType(String type){
		if (type.equals("分店服务员")) {
			return Configure.SERVER;
		}else {
			return Configure.HEAD_SERVER;
		}
	}
}
