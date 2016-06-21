package dessert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.configure.Configure;
import dessert.dao.InventoryDao;
import dessert.dao.PlanDao;
import dessert.dao.StoreDao;
import dessert.entity.Inventory;
import dessert.entity.Plan;
import dessert.entity.Store;
import dessert.pvo.PlanPVO;
import dessert.rvo.ResultVO;
import dessert.rvo.plan.PlanInfoResultVO;
import dessert.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanDao planDao;
	@Autowired
	StoreDao storeDao;
	@Autowired
	InventoryDao inventoryDao;

	@Override
	public ResultVO addPlans(ArrayList<PlanPVO> pvo) {
		ResultVO resultVO = new ResultVO();
		try {
			for (int i = 0; i < pvo.size(); i++) {
				PlanPVO vo = pvo.get(i);
				Store store = storeDao.getById(Store.class, Integer.toUnsignedLong(vo.getS_id()));
				if (store == null) {
					resultVO.setSuccess(Configure.FAIL);
					resultVO.setMessage("该店面不存在，请重新输入");
					return resultVO;
				}
				Plan plan = new Plan();
				plan.setP_name(vo.getP_name());
				plan.setP_num(vo.getP_num());
				plan.setPlandate(vo.getPlandate());
				plan.setPrice(vo.getPrice());
				plan.setS_id(vo.getS_id());
				plan.setEmp_name(vo.getEmployee_name());
				planDao.add(plan);
			}

		} catch (Exception e) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("未知错误");
			return resultVO;
		}

		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("计划列表添加成功");
		return resultVO;
	}

	@Override
	public PlanInfoResultVO addPlan(PlanPVO pvo) {
		PlanInfoResultVO resultVO;
		// ResultVO resultVO = new ResultVO();
		Store store = storeDao.getById(pvo.getS_id() + "");
		if (store == null) {
			resultVO = new PlanInfoResultVO();
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("该店面不存在，请重新输入");
			return resultVO;
		}
		Plan plan = new Plan();
		plan.setP_name(pvo.getP_name());
		plan.setP_num(pvo.getP_num());
		plan.setPlandate(pvo.getPlandate());
		plan.setPrice(pvo.getPrice());
		plan.setS_id(pvo.getS_id());
		plan.setEmp_name(pvo.getEmployee_name());
		planDao.add(plan);
		resultVO = new PlanInfoResultVO(planDao.getByPVO(pvo));
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("计划添加成功");
		return resultVO;
	}

	@Override
	public ResultVO updatePlan(PlanPVO pvo, String id) {
		ResultVO resultVO = new ResultVO();
		Plan plan = planDao.getById(id);
		if (plan == null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该计划");
			return resultVO;
		}

		if (plan.getState() == Configure.PASS) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("该计划已通过审批，不可修改");
			return resultVO;
		}
		// plan.setP_name(pvo.getP_name());
		plan.setP_num(pvo.getP_num());
		// plan.setPlandate(pvo.getPlandate());
		plan.setPrice(pvo.getPrice());
		// plan.setS_id(pvo.getS_id());
		planDao.update(plan);
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("修改成功");

		return resultVO;
	}

	@Override
	public ResultVO deletePlan(String id) {
		ResultVO resultVO = new ResultVO();
		Plan plan = planDao.getById(id);
		if (plan == null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该计划");
			return resultVO;
		}
		if (plan.getState() == Configure.PASS) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("该计划已通过审批，不可删除");
			return resultVO;
		}
		plan.setDelete_flag(Configure.DELETE_FLAG_TRUE);
		planDao.update(plan);
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("删除成功");

		return resultVO;
	}

	@Override
	public ResultVO passPlan(String id) {
		ResultVO resultVO = new ResultVO();
		Plan plan = planDao.getById(id);
		if (plan == null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该计划");
		} else if (plan.getState() == Configure.PASS) {
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("修改成功");
		} else {
			plan.setState(Configure.PASS);
			planDao.update(plan);
			// 计划通过后，新增店面库存
			Inventory inventory = new Inventory();
			inventory.setFromPlan(plan);
			inventoryDao.add(inventory);
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("修改成功");
		}
		return resultVO;
	}

	@Override
	public ResultVO passPlanList(ArrayList<String> ids) {
		ResultVO resultVO = new ResultVO();
		for (int i = 0; i < ids.size(); i++) {
			Plan plan = planDao.getById(ids.get(i));
			if (plan == null) {
				continue;// 忽略错误计划id
			} else {
				plan.setState(Configure.PASS);
				planDao.update(plan);
			}
		}
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("修改成功");
		return resultVO;
	}

	@Override
	public List<PlanInfoResultVO> getAllPlan(int page) {
		if (page <= 0) {
			page = 1;
		}
		List<Plan> list = planDao.getListByColumn(Plan.class, Configure.DELETE_FLAG, Configure.DELETE_FLAG_FALSE);
		List<PlanInfoResultVO> resultVOs = new ArrayList<>();
		if (list == null) {
			return resultVOs;
		}
		for (int i = 0; i < list.size(); i++) {
			resultVOs.add(new PlanInfoResultVO(list.get(i)));
		}
		return resultVOs;
	}

	@Override
	public List<PlanInfoResultVO> getInpassPlan(int page) {
		if (page <= 0) {
			page = 1;
		}
		List<Plan> list = planDao.getListByColumn(Plan.class, "state", Configure.IMPASS, page, 10);
		List<PlanInfoResultVO> resultVOs = new ArrayList<>();
		if (list == null) {
			return resultVOs;
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDelete_flag()==0) {
				resultVOs.add(new PlanInfoResultVO(list.get(i)));
			}
		}
		return resultVOs;
	}

	@Override
	public List<PlanInfoResultVO> getPassPlan(int page) {
		if (page <= 0) {
			page = 1;
		}
		List<Plan> list = planDao.getListByColumn(Plan.class, "state", Configure.PASS, page, 10);
		List<PlanInfoResultVO> resultVOs = new ArrayList<>();
		if (list == null) {
			return resultVOs;
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDelete_flag()==0) {
				resultVOs.add(new PlanInfoResultVO(list.get(i)));
			}
		}
		return resultVOs;
	}

	@Override
	public ResultVO emptyPlan(String id) {
		ResultVO resultVO = new ResultVO();
		Plan plan = planDao.getById(id);
		if (plan == null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该计划");
			return resultVO;
		}
		if (plan.getState() == Configure.PASS) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("该计划已通过审批，不可删除");
			return resultVO;
		}
		planDao.delete(plan);
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("清空成功");
		return resultVO;
	}

	@Override
	public ResultVO emptyAll() {
		ResultVO resultVO = new ResultVO();
		planDao.deleteByColumn(Plan.class, "delete_flag", Configure.DELETE_FLAG_TRUE);
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("清空成功");
		return resultVO;
	}

	@Override
	public List<PlanInfoResultVO> getDeletePlan() {
		List<Plan> list = planDao.getListByColumn(Plan.class, "delete_flag", Configure.DELETE_FLAG_TRUE);
		List<PlanInfoResultVO> resultVOs = new ArrayList<>();
		if (list == null) {
			return resultVOs;
		}
		for (int i = 0; i < list.size(); i++) {
			resultVOs.add(new PlanInfoResultVO(list.get(i)));
		}
		return resultVOs;
	}

	@Override
	public ResultVO rejectPlan(PlanPVO pvo, String id) {
		// TODO Auto-generated method stub
		ResultVO resultVO = new ResultVO();
		Plan plan = planDao.getById(id);
		if (plan == null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该计划");
			return resultVO;
		}

		if (plan.getState() == Configure.PASS) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("该计划已通过审批，不可驳回");
			return resultVO;
		}
		
		plan.setRemark(pvo.getRemark());
		planDao.update(plan);
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("修改成功");

		return resultVO;
	}

	@Override
	public PlanPVO getPlanById(String id) {
		// TODO Auto-generated method stub
		Plan plan= planDao.getById(id);
		PlanPVO planPVO=new PlanPVO(plan);
		return planPVO;
		
		

	}

}
