package dessert.service;

import java.util.ArrayList;
import java.util.List;

import dessert.pvo.PlanPVO;
import dessert.rvo.ResultVO;
import dessert.rvo.plan.PlanInfoResultVO;

public interface PlanService {

	// 新增计划
	public ResultVO addPlans(ArrayList<PlanPVO> pvo);
	public PlanInfoResultVO addPlan(PlanPVO pvo);

	// 修改计划
	// 已通过计划不可修改
	public ResultVO updatePlan(PlanPVO pvo, String id);
	// 删除计划
	// 已通过计划不可删除
	public ResultVO deletePlan(String id);
	//撤销删除
	public ResultVO UndeletePlan(String id);
	public ResultVO emptyPlan(String id);
	
	//清空所有已删除
	public ResultVO emptyAll();

	// 通过计划
	public ResultVO passPlan(String id);

	// 批量通过
	public ResultVO passPlanList(ArrayList<String> ids);

	// 获得计划
	public List<PlanInfoResultVO> getAllPlan();

	// 获得未通过计划
	public List<PlanInfoResultVO> getInpassPlan();

	// 获得已通过计划
	public List<PlanInfoResultVO> getPassPlan();
	
	//获得已删除计划
	public List<PlanInfoResultVO> getDeletePlan();
	
	public ResultVO rejectPlan(PlanPVO pvo, String id);
	
	public PlanPVO getPlanById(String id);
}
