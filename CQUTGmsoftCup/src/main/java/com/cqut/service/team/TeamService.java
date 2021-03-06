package com.cqut.service.team;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.components.If;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Controller;

import com.cqut.service.user.UserService;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.team.customInterface.ITeamEntityDao;
import com.cqut.dao.team.customInterface.ITeamMapDao;
import com.cqut.entity.crew.Crew;
import com.cqut.entity.gameStepDetail.GameStepDetail;
import com.cqut.entity.team.Team;
import com.cqut.service.codeTable.CodeTableService;
import com.cqut.service.crew.CrewService;
import com.cqut.service.derive.deriveInterface.IDeriveExcelService;
import com.cqut.service.expertScore.ExpertScoreService;
import com.cqut.service.gameStep.GameStepService;
import com.cqut.service.gameStepDetail.GameStepDetailService;
import com.cqut.service.operator.OperatorService;
import com.cqut.service.project.ProjectService;
import com.cqut.service.team.customInterface.ITeamService;
import com.cqut.util.OperatorUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller  
@RemoteProxy
public class TeamService implements ITeamService {

	@Resource(name = "teamMapDao")
	private ITeamMapDao mapDao;
	@Resource(name = "teamEntityDao")
	private ITeamEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "crewService")
	private CrewService crewService;
	@Resource(name = "projectService")
	private ProjectService projectService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "deriveExcelService")
	private IDeriveExcelService deriveExcelService;
	@Resource(name = "operatorService")
	private OperatorService operatorService;
	@Resource(name = "gameStepService")
	private GameStepService gameStepService;
	@Resource(name = "gameStepDetailService")
	private GameStepDetailService gameStepDetailService;
	@Resource(name = "codeTableService")
	private CodeTableService codeTableService;
	@Resource(name = "expertScoreService")
	private ExpertScoreService expertScoreService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findTeams(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimitCheck(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit,HttpServletRequest request) {
		long currentMillis = System.currentTimeMillis();
		String ZGH = request.getSession().getAttribute(OperatorUtil.ZGH) + "";
		List<Map<String, Object>> data = mapDao.findTeams(properties, condition + " and gs.checkStartTime<='"+currentMillis+"' and gs.checkEndTime>='"+currentMillis+"' and te.expertID='"+ZGH+"' and gg.state='1' and gr.teamId=me.teamId", sortField, order, needLink, ((curPage-1)*limit), limit);
		for(int i=0;i<data.size();i++){
			String teamId=data.get(i).get("me_teamId").toString();
			String[] scorePorper = {"score"};
			List<Map<String, Object>> score = expertScoreService.findMapByPropertiesQuick(scorePorper, "teamID='"+teamId+"'", false);
			if(score.size()>0){
				data.get(i).put("score", score.get(0).get("score"));
			}else{
				data.get(i).put("score", "未打分");
			}
			//指导老师
			String[] properName={"cr_name","crewId"};
			List<Map<String, Object>> teacherName=crewService.findMapByPropertiesQuick(properName, "teamID='"+teamId+"' and crew.type='2'", true);
			String teachername="";
			for(int j=0;j<teacherName.size();j++){
				if(j!=teacherName.size()-1){
					teachername+=teacherName.get(j).get("cr_name")+",";
				}
				else{
					teachername+=teacherName.get(j).get("cr_name");
				}
			}
			data.get(i).put("teacher", teachername);
			List<Map<String, Object>> studentName=crewService.findMapByPropertiesQuick(properName, "teamID='"+teamId+"' and crew.type='1'", true);
			String studentNames="";
			for(int j=0;j<studentName.size();j++){
				if(j!=studentName.size()-1){
					studentNames+=studentName.get(j).get("cr_name")+",";
				}
				else{
					studentNames+=studentName.get(j).get("cr_name");
				}
			}
			data.get(i).put("crewName", studentNames);
		}
		return data;
	}

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimitTeam(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findTeams(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		for(int i=0;i<data.size();i++){
			String captianId=data.get(i).get("captianId").toString();  //组长id
			String teamId=data.get(i).get("teamId").toString();   //团队id
			String[] proper={"name"};
			List<Map<String, Object>> teamName=userService.findMapByPropertiesQuick(proper, "userID='"+captianId+"'", false);
			if(teamName.size()>0){
				data.get(i).put("captianName", teamName.get(0).get("name"));
			}
			//指导老师
			String[] properName={"membernName","crewId"};
			List<Map<String, Object>> teacherName=crewService.findMapByPropertiesQuick(properName, "teamID='"+teamId+"' and crew.type='2'", true);
			String teachername="";
			for(int j=0;j<teacherName.size();j++){
				if(j!=teacherName.size()-1){
					teachername+=teacherName.get(j).get("membernName")+",";
				}
				else{
					teachername+=teacherName.get(j).get("membernName");
				}
			}
			data.get(i).put("teacher", teachername);
			
			List<Map<String, Object>> studentName=crewService.findMapByPropertiesQuick(properName, "teamID='"+teamId+"' and crew.type='1'", true);
			String studentNames="";
			for(int j=0;j<studentName.size();j++){
				if(j!=studentName.size()-1){
					studentNames+=studentName.get(j).get("membernName")+",";
				}
				else{
					studentNames+=studentName.get(j).get("membernName");
				}
			}
			data.get(i).put("crewName", studentNames);
		}
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimitTeamNew(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findTeams(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		for(int i=0;i<data.size();i++){
			String captianId=data.get(i).get("captianId").toString();
			String teamId=data.get(i).get("teamId").toString();
			String[] proper={"name"};
			List<Map<String, Object>> teamName=userService.findMapByPropertiesQuick(proper, "userID='"+captianId+"'", false);
			if(teamName.size()>0){
			data.get(i).put("captianName", teamName.get(0).get("name"));
			}
			//指导老师
			String[] properName={"membernName","crewId"};
			List<Map<String, Object>> teacherName=crewService.findMapByPropertiesQuick(properName, "teamID='"+teamId+"' and crew.type='1'", true);
			String teachername="";
			for(int j=0;j<teacherName.size();j++){
				if(j!=teacherName.size()-1){
					teachername+=teacherName.get(j).get("membernName")+",";
				}
				else{
					teachername+=teacherName.get(j).get("membernName");
				}
			}
			data.get(i).put("teacher", teachername);
			
			/*String[] propStrings={"fileId"};
			List<Map<String, Object>> file=projectService.findMapByPropertiesQuick(propStrings, "teamId='"+teamId+"' and type='"+data.get(i).get("gs_type")+"'", false);
			if(file.size()==0){
				data.get(i).put("fileExit", 0);
			}
			else{
				data.get(i).put("fileExit", 1);
				data.get(i).put("fileId", file.get(0).get("fileId"));
			}*/
		}
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTeams(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTeams(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getTeam(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findTeams(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Team getTeamEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findTeams(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Team(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int getCountByProperties(String teamName, String mode, String classifiction,HttpServletRequest request){
		int type = Integer.parseInt("" + request.getSession().getAttribute(OperatorUtil.OPERATOR_TYPE));
		if(type == 1){
			return -1;
		}
		if(Integer.parseInt(mode) > 1)
			return -10;
	/*	List l = commonDao.executeAndReturnParam("select COUNT(gameStepID) FROM gamestep where gameStepID =?", new String[]{classifiction});
		if(Integer.parseInt(l.get(0).toString()) > 0){
			return Integer.parseInt(commonDao.find("SELECT count(DISTINCT teamName) AS e FROM Team WHERE teamName = ?", teamName).get(0).toString());
		}else
			return -11;*/
		String[] properties={"ga_gameStepID"};
		int number=this.findCountByProperties(properties, "ga.gameStepID='"+classifiction+"' and teamName='"+teamName+"'", true);
		return number;
		 
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	@RemoteMethod
	public int findCount(String[] properties,
			String condition, boolean needLink,HttpServletRequest request){
		long currentMillis = System.currentTimeMillis();
		String ZGH = request.getSession().getAttribute(OperatorUtil.ZGH) + "";
		int length = mapDao.findCount(properties, condition + " and gs.checkStartTime<='"+currentMillis+"' and gs.checkEndTime>='"+currentMillis+"' and te.expertID='"+ZGH+"' and gg.state='1' and gr.teamId=me.teamId", needLink);
		return length;
	}
	
	public List<Team> findTeamByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findTeams(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Team.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Team value) {
		return deleteById(value.getTeamID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Team.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Team[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Team item : values) {
			ids[index++] = item.getTeamID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean saveTeam(String para,HttpServletRequest request) {
		int type = Integer.parseInt("" + request.getSession().getAttribute(OperatorUtil.OPERATOR_TYPE));
		if(type == 1){
			return false;
		}
		Gson g = new Gson();
		Map<String, Object> value = g.fromJson(para, HashMap.class);
		String id=operatorService.getID();
		value.put("teamID", id);
		value.put("no", getCode(value.remove("mode").toString(),value.get("teamName").toString(),value.get("title").toString()));
		value.put("checkState", 2);
		
		List l = commonDao.executeAndReturnParam("SELECT gameStyle FROM (SELECT team.gameStepID,team.gameStyle FROM team INNER JOIN crew ON crew.teamId=team.teamID WHERE crew.userId=?) AS t WHERE t.gameStepID=? and t.gameStyle=?", new String[]{value.get("captianId").toString(),value.get("gameStepID").toString(),value.get("gameStyle").toString()});
		
		if(l.size() > 0)
			return false;
		else{
		save(value);
		
		//保存组长
		Crew crew=new Crew();
		crew.setCrewID(operatorService.getID());
		crew.setTeamId(id);
		crew.setType("0");
		//crew.setUserId(value.get("captianId").toString());
		crewService.saveEntity(crew);
		return true;
		}
	}
	
	
	@RemoteMethod
	private String getCode(String type,String teamName,String title){
		String[] properties={"no"};
		List<Map<String, Object>> data=this.findMapByPropertiesQuick(properties,"teamName='"+teamName+"' and title='"+title+"'", false);
		if(data.size()>0){
			return data.get(0).get("no").toString();
		}
		
		Calendar now = Calendar.getInstance();
		StringBuffer param = new StringBuffer();
		param.append(now.get(Calendar.YEAR));
		int month = now.get(Calendar.MONTH) + 1;
		
		if(month < 10)
			param.append("0" + month);
		else
			param.append(month);
		int s = findCountByProperties(new String[]{"teamID"}, "", false);
		StringBuffer returnParam = new StringBuffer(++s + "");
		for(int i = returnParam.length(); i < 3; i++){
			returnParam.insert(0, 0);
		}
		
		param.append(returnParam);
		int random=(int)(Math.random()*10);
		param.append(random);
		return param.toString();
	}
	
	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Team(value));
	}

	@RemoteMethod
	public boolean saveEntity(Team value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Team(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Team value) {
		Team team = (Team) commonDao.merge(value);
		if (team != null) {
			return team.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Team data, String condition) {
		if(mapDao.updateTeam(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean update(String id,String state){
		Team team=new Team();
		team.setCheckState(Integer.parseInt(state));
		if(!updateEntity(team,"teamId='"+id+"'")){
			return false;
		}
		return true;
	}
	
	@RemoteMethod
	public FileTransfer exportTable(String [] properties,String condition,boolean needLink,String templateName,int lineNumber,boolean orderNumber,String excelName){
		List<Map<String,Object>> data = this.findMapByPropertiesWithLimitTeam(properties, condition, "", "", needLink, -1, -1);
		
		for(int i=0;i<data.size();i++){
			if(null!=data.get(i).get("checkState")){
				int state=Integer.parseInt(data.get(i).get("checkState").toString());
				if(state==0){
					data.get(i).put("checkState", "待审核");
				}
				else if(state==1){
					data.get(i).put("checkState", "已通过");
				}
				else if(state==2){
					data.get(i).put("checkState", "被退回");
				}
			}
			
		}
		String[] pro={"ga_gameStepName","teamName","title","no","captianName","crewName","teacher","checkState"};
	    String[] chineseStrings = {"竞赛阶段","团队名称","题目","项目编号","组长","组员","指导教师","状态"};
	    int[] width = {50,50,70,70,50,100,50,50};
	    String[] title = {"中","中","中","中","中","左","中","中"};
	    return deriveExcelService.deriveExcelT(excelName,"planOfExam", data, pro, chineseStrings,title,width,excelName);
	}
	
	@RemoteMethod
	public String getTeamData(String userId,int curPage,int limits){
		String condition="teamId in(select distinct teamId from crew where userId='"+userId+"')";
		String[] properties={"teamId","teamName","gameStepID","checkState","title","time","no","captianId","gameStyle"};
		List<Map<String,Object>> data=this.findMapByPropertiesWithLimit(properties, condition, "time", "asc", false, curPage, limits);
		String html="";
		try{
			
		
		for(int i=0;i<data.size();i++){
			String captianId=data.get(i).get("captianId").toString();  //组长ID
			String id = data.get(i).get("teamId").toString();
		    int check = Integer.parseInt(data.get(i).get("checkState").toString());
		    String gameStyle=data.get(i).get("gameStyle").toString();
		    String gameStep=data.get(i).get("gameStepID").toString();
		    
		   String teamName="";
		   String showName="";
		   if(null!=data.get(i).get("teamName")){
			   teamName=data.get(i).get("teamName").toString();
			   if(teamName.length()>4){
				   showName=teamName.substring(0,4)+"...";
			   }
			   else{
				   showName=teamName;
			   }
		   }
		   
		   String theme="";
		   String showTheme="";
		   if(null!=data.get(i).get("title")){
			   theme=data.get(i).get("title").toString();
			   if(theme.length()>4){
				   showTheme=theme.substring(0,4)+"...";
			   }
			   else{
				   showTheme=theme;
			   }
		   }	    
		   String showState="";
		   if(check==2){
			   showState="未报名";
		   }
		   else if(check==0){
			   showState="待审核";
		   }
		   else if(check==1){
			   showState="已通过";
		   }
		   else if(check==3){
			   showState="被退回";
		   }
		   String[] pro={"mo_codeTableName"};
		   List<Map<String, Object>> list=gameStepService.findMapByPropertiesQuick(pro, "gameStepID='"+gameStep+"'", true);
		   String stepName="";
		   if(list.size()>0){
			   stepName=list.get(0).get("mo_codeTableName").toString();
		   }
		    
			//if(check != 0){
			html+="<div class='team'><div class='title'><span></span>" +
				"<div class='team-info'>" +
					"<div class='team-name' style=\"margin-left:0px;margin-right:0px;\"><label title='"+stepName+"'>类别："+stepName + "</label></div>" +
					"<div class='team-name' style=\"margin-right:10px;\"><label title='"+teamName+"'>团队名称："+showName + "</label></div>" +
					"<div class='team-title' style=\"margin-left:10px;\"><label title='"+theme+"'>参赛题目：</label>"+showTheme+ "</div>" +
					"<div><label>参赛编号：</label>"+ data.get(i).get("no")+ "</div>" +
					"<div><label style=\"margin-left:10px;\">状态：</label>"+ showState+ "</div>" +
				"<img src='images/dowm.png' class='input' value='按钮' id='teamtitle" + id + "' onclick=slideToggle('teams"+id +"')></div></div>";
			List<Map<String,Object>> dataNew=crewService.getCrewNew(data.get(i).get("teamId").toString());;
			
			
			//判断报名时间是否截止
			String[] proper={"gameStepID"};
			long currentTime=System.currentTimeMillis();
			List<Map<String,Object>> endTime=gameStepService.findMapByPropertiesQuick(proper,
					"endTime>="+currentTime+" and gameStepID='"+data.get(i).get("gameStepID")+"'", false);
			String[] propers={"gameStepID","type"};
			List<Map<String,Object>> endTimeNew=gameStepDetailService.findMapByPropertiesQuick(propers,
					"endTime>="+currentTime+" and gameStepID='"+data.get(i).get("gameStepID")+"'", false);
			
			html +="<div class=\"teams\" id=\"teams"+  id + "\">";
			if(captianId.equals(userId)){   //组长才有操作
				html += "<div class=\"register\">";
				if(check!=0&&endTime.size()>0&&gameStyle.equals("0")){
					html +="<button class='register-teacher te' onclick=addTeacher('"+id+"')>添加指导老师</button><button onclick=addStudent('"+id+"','"+data.get(i).get("gameStepID")+"') class='register-teames te'>添加组员</button>";
                }
				else if(check!=0&&endTime.size()>0&&gameStyle.equals("1")){
					html +="<button class='register-teacher te' onclick=addTeacher('"+id+"')>添加指导老师</button>";
				}
				
				if(endTime.size()>0){
					if(check==0||check==1){
						html +="<input type='button' value='解散团队' class='submit bule' onclick=deleteGroup('"+id+"')>";
					}
					if(check == 2 || check== 3){
						html +="<input type='button' value='解散团队' class='submit bule' style=\"margin-right:100px;\" onclick=deleteGroup('"+id+"')>";
						html +="<input type='button' value='报名' class='submit bule' onclick=enroll('"+id+"')>";
						
					}
				}
				if(check == 1&&endTimeNew.size()>0){
					String type=endTimeNew.get(0).get("type")+"";
					html +="<input type='button' value='上传作品' class='submit bule' onclick=uploadFile('"+id+"','"+type+"')>";
				}
				
				html +="</div>";
			}
			html += "<table class='teams-info'><tr><td>学号</td><td>姓名</td><td>角色</td><td>学院</td><td>邮箱</td><td>QQ</td>";
			
			if(endTime.size()>0){
				if(check==2||check==3||check==1){
					if(captianId.equals(userId)){
						html+="<td>操作</td>";
					}
				}
			}
			html+="</tr>";
			
			for(int j=0;j<dataNew.size();j++){
				String type="";
				String forTYpeString=dataNew.get(j).get("me_type").toString();
				if(null!=forTYpeString){
				if(forTYpeString.equals("0")){   //0
					type="组长";
				}
				else if(forTYpeString.equals("1")){  
					type="组员";
				}
				else{
					type="指导教师";      //2
				}
				}
				html+="<tr>";
				if(null!=dataNew.get(j).get("cr_userId")){
					html += "<td>" + dataNew.get(j).get("cr_userId")+ "</td>";
					}else{
						html += "<td></td>";
					}
				if(null!=dataNew.get(j).get("cr_name")){
					html +="<td>"+dataNew.get(j).get("cr_name")+"</td>";
					}else{
						html += "<td></td>";
					}
					html+="<td>"+type+"</td>";
				if(null!=dataNew.get(j).get("ac_academicName")){
					html+="<td>"+dataNew.get(j).get("ac_academicName")+"</td>";
					}else{
						html += "<td></td>";
					}
				if(null!=dataNew.get(j).get("cr_email")){
					html+="<td>"+dataNew.get(j).get("cr_email")+"</td>";
					}else{
						html += "<td></td>";
					}
				if(null!=dataNew.get(j).get("cr_QQ")){
					html+="<td>"+dataNew.get(j).get("cr_QQ")+"</td>";
					}else{
						html += "<td></td>";
					}
				if(endTime.size()>0){
					if(check==2||check==3||check==1){
						if(captianId.equals(userId)){
							if(!forTYpeString.equals("0")){
								html +="<td><a style=\"cursor:pointer;color:red;\" onclick=deleteId('"+dataNew.get(j).get("me_crewId")+"')>删除</a></td>";
							}
							else{
								html +="<td></td>";
							}
						}
					}
				}
				html+="</tr>";
			}
			html+="</table></div></div>";
		}
		
		if(data.size()==0){
			html+="尚未加组";
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}
	
	@RemoteMethod
	public boolean updateData(String teamId){
		Team team=new Team();
		team.setCheckState(1);
		if(!this.updateEntity(team, "teamId='"+teamId+"'")){
			return false;
		}
		return true;
	}
	
	@RemoteMethod
	public boolean deleteGroup(String id){
		if(this.deleteById(id)){
			String sql="delete from crew where teamId='"+id+"'";
			commonDao.execute(sql);
			return true;
		}
		return false;
	}
}
