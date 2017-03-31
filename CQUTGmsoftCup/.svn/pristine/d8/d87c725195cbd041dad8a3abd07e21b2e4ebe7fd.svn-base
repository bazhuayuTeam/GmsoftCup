package com.cqut.service.reviewManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.entity.gameStepDetail.GameStepDetail;
import com.cqut.entity.standardVersion.StandardVersion;
import com.cqut.service.codeTable.customInterface.ICodeTableService;
import com.cqut.service.gameStepDetail.customInterface.IGameStepDetailService;
import com.cqut.service.standardVersion.StandardVersionService;
@Controller  
@RemoteProxy
public class reviewManagementService {
	@Resource(name = "gameStepDetailService")
	private IGameStepDetailService gameStepDetailService;
	@Resource(name = "codeTableService")
	private ICodeTableService codeTableService;
	@Resource(name = "standardVersionService")
	private StandardVersionService standardVersionService;
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimitNew(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = gameStepDetailService.findMapByPropertiesWithLimit(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		if(data!=null&&data.size()!=0){
			for(int i=0,len=data.size();i<len;i++){
				List<Map<String,Object>> gameStep=codeTableService.findMapByPropertiesQuick(new String[]{"codeTableName"}, "codeTableCode='"+data.get(i).get("gs_gameStep")+"'", false);
				if(gameStep!=null&&gameStep.size()!=0){					
					data.get(i).put("gs_gameStep",gameStep.get(0).get("codeTableName"));
				}else{
					data.get(i).put("gs_gameStep","未知");
				}
				List<Map<String,Object>> map=gameStepDetailService.findMapByPropertiesQuick(
					new String[]{"sv_standardVersionName"}, "gameStepDetailID='"+data.get(i).get("gameStepDetailID")+"'", true);
				String name="";
				if(map.size()>0){
					name=map.get(0).get("sv_standardVersionName").toString();
				}
				data.get(i).put("standardVersionName", name);
			}
		}
		return data;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = gameStepDetailService.findCountByProperties(properties, condition, needLink);
		return length;
	}
	
	@RemoteMethod
	public boolean updateEntity(GameStepDetail data, String condition) {
		StandardVersion version=new StandardVersion();
		version.setCiteState("1");
		standardVersionService.updateEntity(version, "standardVersionID='"+data.getStandardVersionID()+"'");
		return gameStepDetailService.updateEntity(data, condition);
	}
}
