package dessert.rvo.plan;

import java.util.Date;

import dessert.entity.Plan;
import dessert.rvo.ResultVO;
import dessert.util.Util;

public class PlanInfoResultVO extends ResultVO{

	private int id;
	private Date plandate;
	private int s_id;
	private String p_name;
	private int p_num;
	private double price;
	private int state;
	private String emp_name;

	public PlanInfoResultVO(){
		
	}
	
	public PlanInfoResultVO(Plan plan){
		id=plan.getId();
		plandate=plan.getPlandate();
		s_id=plan.getS_id();
		p_name=plan.getP_name();
		p_num=plan.getP_num();
		price=plan.getPrice();
		state=plan.getState();
		emp_name=plan.getEmp_name();
	}
	
	public int getId() {
		return id;
	}

	public Date getPlandate() {
		return plandate;
	}

	public void setPlandate(Date plandate) {
		this.plandate = plandate;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public String getDate(){
		return Util.getDateString(plandate);
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

}
