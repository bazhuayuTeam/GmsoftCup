package com.cqut.service.target;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.common.MyCommonDao;
import com.cqut.dao.target.customInterface.ITargetEntityDao;
import com.cqut.dao.target.customInterface.ITargetMapDao;
import com.cqut.entity.target.Target;
import com.cqut.service.gameStepDetail.GameStepDetailService;
import com.cqut.service.target.customInterface.ITargetService;

@Controller  
@RemoteProxy
public class TargetService implements ITargetService {

	@Resource(name = "targetMapDao")
	private ITargetMapDao mapDao;
	@Resource(name = "targetEntityDao")
	private ITargetEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "gameStepDetailService")
	private GameStepDetailService gameStepDetailService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findTargets(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTargets(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTargets(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getTargetInfo(String targetCode){
		List<Map<String, Object>> data = mapDao.findTargets(new String[]{"targetName","targetExplain","targetScore"}, "targetCode='"+targetCode+"'", "", "", false, -1, -1);
		
		Map<String,Object> map = data != null && data.size() > 0 ? data.get(0) : null;
		
		return map;
	}
	
	@RemoteMethod
	public Map<String, Object> getTarget(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findTargets(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Target getTargetEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findTargets(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Target(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Target> findTargetByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findTargets(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Target.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Target value) {
		return deleteById(value.getTargetCode());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Target.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Target[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Target item : values) {
			ids[index++] = item.getTargetCode();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Target(value));
	}

	@RemoteMethod
	public boolean saveEntity(Target value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Target(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Target value) {
		Target target = (Target) commonDao.merge(value);
		if (target != null) {
			return target.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Target data, String condition) {
		if(mapDao.updateTarget(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public List<Map<String,Object>> getTargets(String[] properties,String condition,String[] propers,String sortField, String order, boolean needLink){
		if(properties.length==0){	
			String[] pro={"targetCode","targetName","targetExplain","targetLevel",
			         "targetParentCode","isLastTarget","targetScore","standardVersionID"};
			properties=pro;
		}		
		//获取版本号
		Map<String, Object> map = gameStepDetailService.getGameStepDetail(new String[]{"standardVersionID"}, "gameStepDetailID = '"+propers[1]+"'", false);
		String standardVersionID = "";
		if(map!=null){
			standardVersionID = map.get("standardVersionID") + "";
		}else{
			return null;
		}
		//获取指标数据
		//List<Map<String,Object>> original=this.findMapByProperties(properties, condition, sortField, order, needLink);
		List<Map<String,Object>> original=this.findMapByProperties(properties, "standardVersionID = '"+standardVersionID+"'", sortField, order, needLink);
		return dealTarget(original,propers);//将处理了的数据返回去
	}
	
	/***************************获取指标数据-lile****************************/
	//注：properties中一定要包含targetLevel，targetParentCode
	@RemoteMethod
	public List<Map<String,Object>> findTargets(String[] properties,String condition, String sortField, String order, boolean needLink){
		if(properties.length==0){	
			String[] pro={"targetCode","targetName","targetExplain","targetLevel",
			         "targetParentCode","isLastTarget","targetScore","standardVersionID"};
			properties=pro;
		}		
		//获取指标数据
		List<Map<String,Object>> original=this.findMapByProperties(properties, condition, sortField, order, needLink);
		return dealTarget(original,null);//将处理了的数据返回去
	}
	
	@RemoteMethod
	private List<Map<String,Object>> dealTarget(List<Map<String,Object>> original,String[] propers){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list.addAll(this.getTargetByLevel("1", original,propers));//添加level等于1的；
		list.addAll(this.getTargetByLevel("2", original,propers));//添加level等于2的；
		list.addAll(this.getTargetByLevel("3", original,propers));//添加level等于3的；
		list.addAll(this.getTargetByLevel("4", original,propers));//添加level等于4的；
		return list;
	}
	
	private List<Map<String, Object>> getTargetByLevel(String level,List<Map<String,Object>> original,String[] propers){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> childList=new ArrayList<Map<String,Object>>();
		//找出该层所有指标
		for(Map<String,Object> map:original){
			String nowLevel=map.get("targetLevel").toString()+"";
			if((level).equals(nowLevel)){
				list.add(map);
			}
			
			if(map.containsKey("isLastTarget")&&("1").equals((map.get("isLastTarget").toString()+""))){
				//获取分值  并计算名次
				if(propers!=null){
					String sql = "select s.score,s.targetScoreID from targetScore s,expertTargetDistribute e WHERE s.targetCode = '"+map.get("targetCode")+"' and s.expertTargetDistributeID = e.expertTargetDistributeID and e.teamID = '"+propers[0]+"' and e.gameStepDetailid = '"+propers[1]+"'";
					List<Object> scoreList = commonDao.executeAndReturn(sql);
					Object[] score = null;
					if(scoreList!=null&&scoreList.size()>0){
						score =  (Object[]) scoreList.get(0);
						map.put("score", score[0]);
						map.put("targetScoreID", score[1]);
					}else{
						map.put("score", "");
						map.put("ranking", "暂无");
					}
					
					//获取名次
					if(score != null){
						sql = "select score from targetScore WHERE targetCode = '"+map.get("targetCode")+"' ORDER BY score desc";
						List<Object> rankList = commonDao.executeAndReturn(sql);
						if(rankList!=null&&rankList.size()>0){
							map.put("max", rankList.get(0));
							map.put("min", rankList.get(rankList.size() - 1));
							int rank = 1;
							int temp = -1;
							for(int i=0;i<rankList.size();i++){
								int ranking = Integer.parseInt(rankList.get(i) + "");
								if(ranking == Integer.parseInt(score[0] + "")){
									break;
								}else{
									if(ranking == temp){
										continue;
									}else{
										temp = ranking;
										rank ++;
									}
								}
							}
							map.put("ranking", rank);
						}else{
							map.put("max", "暂无");
							map.put("min", "暂无");
							map.put("ranking", "暂无");
						}
					}else{
						map.put("max", "暂无");
						map.put("min", "暂无");
						map.put("ranking", "暂无");
					}
				}
				childList.add(map);
			}
		}
		//计算该层每个指标下面的指标
		for(Map<String,Object> map: list){
			String parentCode=map.get("targetCode").toString()+"";
			if(parentCode!=null&&(!parentCode.isEmpty())){
				//遍历子节点，统计个数
				for(Map<String,Object> childMap: childList){
					String targetCode=childMap.get("targetCode").toString()+"";
					int index=targetCode.indexOf(parentCode);
					if(index==0){
						if(map.containsKey("count")){
							int count=Integer.parseInt(map.get("count").toString()+"")+1;
							map.put("count", count);
						}else{
							map.put("count", 1);
						}
					}
				}
			}
		}	
		return list;
	}

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimitNew(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		// 获取删除部分字段后的数据
		List<Map<String, Object>> data = mapDao.findTargets(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String,Object> findTarget(String targetCode){
		List<Map<String, Object>> data = this.findMapByPropertiesWithLimitNew(new String[]{
				"targetCode","targetName","targetParentCode",
				"targetScore","targetExplain","isLastTarget"
		}, "targetCode='" + targetCode + "'", "", "", false, 1, 1);
		
		if(data.size() >0) {
			return data.get(0);
		}
		return null;
	}
	
	@RemoteMethod
	public int findCountByPropertiesNew(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	//新增指标
	@RemoteMethod
	public boolean saveTarget(Target target) {
		if(target.getTargetLevel()<=4){
			if (target.getTargetCode() == null || target.getTargetCode().equals("")) {
				target.setTargetCode(getTargetCode(target.getTargetParentCode(),target.getStandardVersionID()));
				target.setIsLastTarget(1); 	// 是末级指标
			}

			boolean targetRs = this.saveEntity(target);
			this.modifyParentTargetIsLastTarget(target.getTargetParentCode(), 0);
			return targetRs;
		}
		return false;
		
	}
	
	//获取指标代码
	public String getTargetCode(String parentCode,String targetSysVersionID) {
		String targetCode = "0001";
		String condition = "";
		if (parentCode == null || parentCode == "") {
			condition = " (targetParentCode is null or targetParentCode='" + parentCode + "' or targetParentCode='')";
		} else {
			condition = " targetParentCode='" + parentCode + "'";
		}
		
		condition += " AND standardVersionID='" + targetSysVersionID + "' and targetCode is not null order by targetCode desc";
		
		//String sql = "SELECT DISTINCT targetCode AS code FROM Target WHERE " + condition;
		//MyCommonDao dao = new MyCommonDao();
	//    List<Map<String, Object>> list = dao.executeQuery(sql);
	    String[] proper={"targetCode"};
	    List<Map<String, Object>> list=this.findMapByPropertiesQuick(proper, condition, false);
		
		if (list.size() > 0) {
			if(list.get(0).get("targetCode") == null){	// 说明数据库还没有数据
				if(parentCode !=null && parentCode !=""){	// 判断是1级指标还是后面的指标
					targetCode = parentCode + targetCode;
				}else
					targetCode = targetSysVersionID + targetCode;
			}else{
				int codeLen =4;
				String code = list.get(0).get("targetCode").toString();
				String prefix = code.substring(0, code.length()-codeLen);	// 获取前缀，到后四位前
				String suffix = code.substring(code.length()-codeLen, code.length());	// 获得后四位
				long suffixCode = Long.parseLong(suffix) + 1;

				targetCode = prefix + add0(suffixCode, codeLen);
			}
		}
		else{  
			if (!(parentCode == null || parentCode == "")) {
				targetCode=parentCode + targetCode;
			}
		}
		
		return targetCode;
	}
	
	
	
	// 判断一个整数有多少位
	private int digitOfNumber(long num){
		int digit =1;
		
		num = num/10;
		while(num !=0){
			digit ++;
			num = num/10;
		}
		
		return digit;
	}
	
	// 对于需要0开头的code进行补0
	private String add0(long code,int codeLen){
		StringBuilder sb = new StringBuilder();
		int need0 = codeLen * (digitOfNumber(code) / codeLen + 1)
				- digitOfNumber(code);	//	 编码长度*（需要补位编码长度的倍数）-位数，差几就在前面填几个0
		for(int i =0;i <need0;i++){
			sb.append("0");
		}
		sb.append(code);
		
		return sb.toString();
	}
	
	// 删除指标及下级指标
	@RemoteMethod
	public boolean deleteTargetAndLowerTargets(String targetCode) {
		Map<String,Object> target = this.findTarget(targetCode);
		String targetParentCode = null;
		if(target.get("targetParentCode") != null)
			targetParentCode = target.get("targetParentCode").toString();

		String delTargetSql = "DELETE from TARGET WHERE TARGETCODE LIKE '"
				+ targetCode + "%'";
		int targetRs = commonDao.execute(delTargetSql);
		
		// 当所有的子指标被删除后再将父级指标设置为末级指标
		String queryChilden = "SELECT targetCode FROM TARGET WHERE targetCode LIKE '%" + targetParentCode +"____%'";
		if(targetRs > 0 && commonDao.executeAndReturn(queryChilden).size() == 0)
			modifyParentTargetIsLastTarget(targetParentCode, 1);
		
		return targetRs > 0;

	}
	
	
	// 修改父级指标的isLastTarget
	private boolean modifyParentTargetIsLastTarget(String targetParentCode,int isLastTarget){
		if(targetParentCode == null || targetParentCode.equals(""))	// 没有父级指标的情况，认为不需要修改，则直接成功
			return true;
		
		String sql = "UPDATE Target SET isLastTarget = " + isLastTarget + " WHERE targetCode='" + targetParentCode + "'";
		
		return (commonDao.execute(sql) > 0)? true:false;
	}
	
	// 获取非末级指标下级指标的总分和
	@RemoteMethod
	public List<Map<String,Object>> findLowerTargetsTotalScores(String condition) {
		String sql = "SELECT DISTINCT SUM(targetScore) as totalScore,targetParentCode FROM Target " +
				"WHERE targetParentCode IN (SELECT DISTINCT targetCode FROM Target " +
				"WHERE isLastTarget = 0 )   " + condition + " GROUP BY targetParentCode ORDER BY targetParentCode";
		
		MyCommonDao dao = new MyCommonDao();
		List<Map<String,Object>> rs = dao.executeQuery(sql);
		
		for(Map<String, Object> map :rs){
			map.put("totalScore", map.remove(""));
		}
		return rs;
	}
	
		//分数为null事，转换为0
		public Object changeScore(Object scoObject){
			if(scoObject==null){
				return "0";
			}
			else {
				return scoObject;
			}
		}

		@RemoteMethod
		public boolean saveAllEntity(Target[] value){
			for(int i=0;i<value.length;i++){
				if(!saveEntity(value[i])){
					return false;
				}
			}
			return true;
		}
}
