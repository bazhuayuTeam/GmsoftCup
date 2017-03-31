;

var pageData={
		roleName:""
};

$(function(){
	$("#calender").css("display","none");
	pageData.roleName=getRoleName();
	desktopAllocation();//桌面分配
	$('.area').height($('body')[0].scrollHeight);
	quickView();
	$("body").niceScroll({horizrailenabled : false,scrollspeed : 20});
});

//桌面分配
function desktopAllocation(){
	 DesktopService.findMapByPropertiesQuick(['col_columnName'],"desktop.operatorId='"+roleID+"'",true,function(data){
		 if(data!=null&&data.length>0){
			for(var i=0;i<data.length;i++){
				if(data[i].col_columnName=='日历'){
					$("#calender,.area_left_prompt").css("display","block");
					importentEvent();
				}else if(data[i].col_columnName=='待办事项'){
					$("#schedule").css("display","block");
					checkSchedule();
				}else if(data[i].col_columnName=='收件箱'){
					$("#inbox").css("display","block");
					gridSettingOfCollegeInform();
				}else if(data[i].col_columnName=='发件箱'){
					$("#outbox").css("display","block");
					gridSettingOfCourseArrangement();
				}
			}
		 }
	 });
	 $("#question").css("display","block");
	 if(pageData.roleName=='教务'){
		 question();	//疑问解答
	 }else{
		 question();	
		 $('#question .area_common_titleContent').text("评估咨询");//评估咨询
	 }
}
//查询收件箱
function gridSettingOfCollegeInform() {
	dwr.engine.setAsync(false);//设为同步
	var foreData={itemPicSrc:"",itemTitle:"",itemContent_Detail:"",itemContent_date:"",statusHtml:""};
	$("#inbox").html(assembleInboxHTML(foreData));
	$(".solved").css("color","#8ab939");//区分处理与未处理
	$("#inbox .area_common_itemContent_status").removeClass("solved");
	prompt($("#inbox"));
	dwr.engine.setAsync(true);//设为异步
	
	
}

//组装发件箱的html
function assembleInboxHTML(foreData){
	var foreMannerHTML="<div class='area_common_title'><img src='images/module/desktop/inbox_titlePic.png' class='area_common_titlePic' />" +
	"<span class='area_common_titleContent'>收件箱</span> </div> ";
	
	MessageReceiveService.findMapByPropertiesWithLimit(["me_messageReceiveId","ms_title","ms_sendTime","ms_content","jsxxb_XM","me_isRead"],"me.operatorID = '" + ZGH + "' order by me.isRead,ms.sendTime DESC",'','',true,1,4,function(data){
		if(data!=null&&data.length){
			for(var i=0;i<data.length;i++){
				   if(data[i].me_isRead=="0"){
					   foreData.itemPicSrc="images/module/desktop/inbox.png";
					   foreData.statusHtml="<div class='area_common_itemContent_status solved'>来自:"+data[i].jsxxb_XM+"</div>";
				   }else{
					   foreData.itemPicSrc="images/module/desktop/inbox_finish.png";
					   foreData.statusHtml="<div class='area_common_itemContent_status'>来自:"+data[i].jsxxb_XM+"</div>";
				   }
				   foreData.itemTitle=data[i].ms_title;	  
				   foreData.itemContent_Detail=subStr(data[i].ms_content,20);
				   foreData.itemContent_date=setTimeStyle(data[i].ms_sendTime);
                   foreMannerHTML+=showForeManner(foreData);
			   }
		}else{
			foreMannerHTML+="<div style='padding:15% 0;'><img src='images/module/desktop/noData_vertical.png'/></div>";
		}		
	});

	return foreMannerHTML;
}

//查询发件箱
function gridSettingOfCourseArrangement() {
	dwr.engine.setAsync(false);//设为同步
	var foreData={itemPicSrc:"",itemTitle:"",itemContent_Detail:"",itemContent_date:"",statusHtml:""};
	foreData.itemPicSrc="images/module/desktop/outbox.png";	
	$("#outbox").html(assembleOutboxHTML(foreData));
	$(".solved").css("color","#c3a080");//区分处理与未处理
	$("#outbox .area_common_itemContent_status").removeClass("solved");
	dwr.engine.setAsync(true);//设为异步
	
}
//组装发件箱的html
function assembleOutboxHTML(foreData){
	
	var foreMannerHTML="<div class='area_common_title'><img src='images/module/desktop/outbox_titlePic.png' class='area_common_titlePic' />" +
	"<span class='area_common_titleContent'>发件箱</span> </div> ";
	
	MessageService.findMapByPropertiesWithLimit(["me_MESSAGEID","me_title","me_content","mr_operatorID","jsx_XM","me_sendtime"],"me.sender = '" + ZGH + "' order by  me.sendtime DESC",'','',true,1,4,function(data){
		if(data!=null&&data.length){
			for(var i=0;i<data.length;i++){
				   foreData.itemTitle=data[i].me_title;
				   foreData.itemContent_Detail=subStr(data[i].me_content,20);
				   foreData.itemContent_date=setTimeStyle(data[i].me_sendtime);
				   foreData.statusHtml="<div class='area_common_itemContent_status solved'>发送:"+data[i].jsx_XM+"</div>";
                   foreMannerHTML+=showForeManner(foreData);
			   }
		}else{
			foreMannerHTML+="<div style='padding:15% 0;'><img src='images/module/desktop/noData_vertical.png'/></div>";
		}			
	});

	return foreMannerHTML;
}



//疑问解答或评估咨询
function question(){
	dwr.engine.setAsync(false);//设为同步
	var foreData={itemPicSrc:"",itemTitle:"",itemContent_Detail:"",itemContent_date:"",statusHtml:""};
	foreData.itemPicSrc="images/module/desktop/question.png";
	
	$("#question").html(assembleQuestionHTML(foreData));
	$(".solved").css("color","#000000");//区分处理与未处理
	$("#question .area_common_itemContent_status").removeClass("solved");
	prompt($("#question"));
	dwr.engine.setAsync(true);//设为异步
}

//组装疑问解答或评估咨询的html
function assembleQuestionHTML(foreData){
	var foreMannerHTML="<div class='area_common_title'><img src='images/module/desktop/question_titlePic.png' class='area_common_titlePic' />" +
	"<span class='area_common_titleContent'>疑问解答</span></div> ";
	
	QuestionService.findMapByPropertiesWithLimit(["questionTitle","status","createTime","questionContext"]," 1=1 order by  status,createTime DESC",'','',true,1,4,function(data){
		if(data!=null&&data.length>0){
		   
			for(var i=0;i<data.length;i++){
			   foreData.itemTitle=data[i].questionTitle;
			   foreData.itemContent_Detail=subStr(data[i].questionContext,20);
			   foreData.itemContent_date=setTimeStyle(data[i].createTime);
			   if(data[i].status==0){
				   foreData.statusHtml="<div class='area_common_itemContent_status solved'>状态：未处理</div>";
				   
			   }else{
				   foreData.statusHtml="<div class='area_common_itemContent_status'>状态：已处理</div>";
			   }
			   
			   foreMannerHTML+=showForeManner(foreData);
		   }
			
		}else{
			foreMannerHTML+="<div style='text-align: center;margin-top: -30px;'><img src='images/module/desktop/noData_ horizontal.png'/></div>";
		}	
	});
	return foreMannerHTML;
}

//待办事项
function  checkSchedule(){	
	var result="";
	var role=pageData.roleName//获取角色名
    
	//添加条件
	if(role=='系主任'){
		result=getDetailSituation(" me.taskDetailState = 0 AND me.majorId='"+disciplineID+"'","系主任");
	}else if(role=='院秘'){
		result=getDetailSituation("me.taskDetailState=1   AND dis.academicID='"+academicID+"'","院秘");
	}else if(role=='审核专家'){
		result=getDetailSituation("me.taskDetailState=2  ","审核专家");
	}else if(role=='评估专家'){
		result=getDetailSituation("me.taskDetailState=3  ","评估专家");
	}
	
	//将展示数据封装成对象进行HTML拼装
	var foreData={itemPicSrc:"",itemTitle:"",itemContent_Detail:"",itemContent_date:"",statusHtml:""};
	foreData.itemPicSrc="images/module/desktop/schedule.png";
	foreData.itemTitle="我的待办事项";
	foreData.itemContent_Detail=subStr(result);
	foreData.itemContent_date=setTimeStyle(new Date());
	$("#schedule").html(assembleScheduleHTML(foreData));
	$(".solved").css("color","#8ab939");//区分处理与未处理
	$("#schedule .area_common_itemContent_status").removeClass("solved");
    
	
}

//获取角色名
function getRoleName(){
	dwr.engine.setAsync(false);//设为同步
	var role="";
	RoleService.findMapByPropertiesQuick(["roleName"]," roleID='"+roleID+"'",false,function(data){
		if(data!=null&&data.length>0){
			role=data[0].roleName;
		}
	})//获取角色名
	return role;
	dwr.engine.setAsync(true);//设为异步
}

//根据条件获取专业数据录入情况
function getDetailSituation(condition,role){
	dwr.engine.setAsync(false);//设为同步
	var result="";
	TaskDetailService.findMapByPropertiesQuick(['me_taskDetailId','me_taskDetailState','dis_disciplineName'],condition,true,function(data){
		if(data!=null&&data.length>0){
			if(role=='系主任'){
				result="您还有数据未提交";
			}else if(role=='院秘'){
				result="您还有数据未提交";
			}else if(role=='审核专家'){
				result="您还有数据未审核";
			}else if(role=='评估专家'){
				result="您还有数据未评估";
			}	
		}
	});
	return result;
	dwr.engine.setAsync(true);//设为异步
}

//拼装待办事项的HTML
function assembleScheduleHTML(foreData){
	var foreMannerHTML="<div class='area_common_title' style='padding-right:240px;'><img src='images/module/desktop/schedule_titlePic.png' class='area_common_titlePic' />" +
	"<span class='area_common_titleContent'>待办事项</span> </div> ";
	if(foreData.itemContent_Detail!=''){
		foreData.statusHtml+="<div class='area_common_itemContent_status solved'>状态：未处理</div>";
		foreMannerHTML+=showForeManner(foreData);
	}else{
		foreMannerHTML+="<div style='text-align: center;margin-top: -40px;'><img src='images/module/desktop/noData_ horizontal.png'/></div>";
	}
	return foreMannerHTML;
}

//展示块页面
function showForeManner(object){	
	
    var htmlStri="<div><div class='area_common_item'>" +
    		"<img src='"+object.itemPicSrc+"' class='area_common_itemPic'/>" +
    		"<span class='area_common_itemTitle'>"+object.itemTitle+"</span>" +
    		"<div class='area_common_itemContent'>" +
    		"<div class='area_common_itemContent_detail'>"+object.itemContent_Detail+"</div>" +
    		"<div class='area_common_itemContent_date'>"+object.itemContent_date+"</div>" +object.statusHtml+
    		" </div></div></div>";
    return htmlStri;
}

//快速查看信息
function quickView(){

	$(".mainContent").find(".area_common_title").click(function(){
		turnPage($(this).find(".area_common_titleContent").text());
	});//为标题添加页面跳转
	
	$(".mainContent").find(".area_common_item").click(function(){
		var titleColumn=$(this).parent().prev();
		turnPage(titleColumn.children(".area_common_titleContent").text());
	});//为块级元素添加页面跳转
	
}

//页面跳转
function turnPage(module){
    var role=pageData.roleName;
	if(module=='待办事项'){
		if(role == "系主任" || role == "院秘"){
			$('#showContent',parent.document).attr('src','module/dataAuditStateManager/dataAuditManager.jsp');
		}else{
			$('#showContent',parent.document).attr('src','module/dataAuditStateManager/expertAuditManager.jsp');
		}
		
	}else if(module=='评估咨询'){
		$('#showContent',parent.document).attr('src','module/questionManager/questionManager.jsp');
	}else if(module=='疑问解答'){
		$('#showContent',parent.document).attr('src','module/replyManager/replyManager.jsp');
	}else if(module=='收件箱'){
		$('#showContent',parent.document).attr('src','module/office/message/receivedMessages.jsp');
	}else if(module=='发件箱'){
		$('#showContent',parent.document).attr('src','module/office/message/sendedMessage.jsp');
	}else{}
}

//有新消息时进行消息提示
function  prompt(object){
	var colorObject=$(object).find(".area_common_item  .area_common_itemContent_status");//获取状态对象
	var read=true;
	colorObject.each(function(){
		if($(this).css("color").toString()!="rgb(154, 154, 154)"){
			read=false;
		}
	});
	if(!read){//为未阅读的栏目添加标识
		object.find(".area_common_titleContent").after("<span class='area_common_titlePromptIcon'></span>");
		var promptIcon=object.find(".area_common_titlePromptIcon");
		promptIcon.html("<img src='images/module/desktop/promptPic.png'/>");
		promptIcon.find("img").css("margin-top","-13px");
	}
}

//添加近期的重要事件
function importentEvent(){
	dwr.engine.setAsync(false);
	TaskService.getCurTask(function(data){
		if(data!=null){
			showImportentEvent(data);	
		}else{
			$('.area_left_currentStatus').html("<div class='nodata'>暂无数据</div>");
			$('.area_left_finishStatus').html("<div class='nodata'>暂无数据</div>");
		}
	});
	showCalendar();
	dwr.engine.setAsync(true);
}

//展示重要事件
function showImportentEvent(data){
	var date=new Date();
	var taskLunarDay=GetLunarDay(date.getFullYear(),date.getMonth() + 1,date.getDate());
	var object={detailObject:"",date:"",lunarDate:"",content:""}//date：新历的月日  lunarDate：农历月日 
	//获取任务进度
	object.date=(date.getMonth() + 1)+"月"+(date.getDate())+"日";
	object.lunarDate="(农历"+taskLunarDay.split("年")[1]+")";//获取当前农历
	object.content=data.taskName+getTaskState(data.taskState);//获取任务执行状态
	object.detailObject=$(".area_left_currentStatus");
	showDiv(object,1);//展示第一个方块
	//获取对应角色的任务截止时间
	var finishObject=finishEvent(data);
	var finishTime=finishObject.time.split("-");
	object.date=deleteZero(finishTime[1])+"月"+deleteZero(finishTime[2])+"日";
	var finishLunarDay=GetLunarDay(finishTime[0],deleteZero(finishTime[1]),deleteZero(finishTime[2]));
	object.lunarDate="(农历"+finishLunarDay.split("年")[1]+")";//获取截止时间农历
    object.detailObject=$(".area_left_finishStatus");
    object.content=finishObject.content;
    showDiv(object,2);//展示第二个方块
  
} 

//展示日历
function showCalendar(){
	var date=new Date();
	var taskLunarDay=GetLunarDay(date.getFullYear(),date.getMonth() + 1,date.getDate());
	var object={detailObject:"",date:"",lunarDate:"",content:""}//date：新历的月日  lunarDate：农历月日 
	//获取最近的节假日
    var nowMonth=(date.getMonth() + 1)<10?"0"+(date.getMonth() + 1):""+(date.getMonth() + 1);
    var nowDate=date.getDate()<10?"0"+(date.getDate()):""+(date.getDate());
    var festival=GetRecentFestival(taskLunarDay.split("年")[1],nowMonth+nowDate);
    //console.info(festival);
    object.detailObject=$(".area_left_recentFestival");
    object.date=festival.newRecentFestivalDate;
    object.lunarDate="(农历  "+festival.lunarRecentFestivalDate+")";
    object.content=festival.festival;
    showDiv(object,2); //展示第三个方块
}

function showDiv(object,type){
	var html="";
	if(type==1){
		html="<div style='padding: 18px 10px 0 11px;'>" +
		"<span style='font-size:18px;'>"+object.date+"</span>" +
		"<span style='font-size:14px;'>"+object.lunarDate+"</span>" +
		"</div><div style='padding: 13px 10px 0 11px;'><span style='font-size:24px;'>"
		+object.content.substring(0, 1)+"</span>" +
		"<span style='font-size:15px;'>"+object.content.substring(1, object.content.length)+"</span></div>";
	}else{
		html="<div style='padding: 13px 7px 0 7px;'>" +
		"<span style='font-size:15px;'>"+object.date+"</span>" +
		"<span style='font-size:11px;'>"+object.lunarDate+"</span>" +
		"</div><div style='padding: 4px 10px 0 9px;'><span style='font-size:20px;'>"
		+object.content.substring(0, 1)+"</span>" +
		"<span style='font-size:14px;'>"+object.content.substring(1, object.content.length)+"</span></div>";
	}
	$(object.detailObject).html(html);
}

//与角色对应的事件及结束的时间
function finishEvent(data){
	var object={content:"",time:""};
	if(pageData.roleName=='教务'){
		object.content="任务截止"; 
		object.time=data.taskEndDate;
	}else if(pageData.roleName=='系主任'){
		object.content="数据录入截止";
		object.time=data.taskInputDate;
	}else if(pageData.roleName=='院秘'){
		object.content= "数据审核截止";
		object.time=data.taskAuditDate;
	}else if(pageData.roleName=='审核专家'){
		object.content="数据审核截止";
		object.time=data.taskAuditDate;
	}else if(pageData.roleName=='评估专家'){
		object.content="数据评估截止";
		object.time=data.taskAssessDate;
	}
	return object;
}

function getTaskState(taskState){
	if(taskState==0){
		return "已开始";
	}else if(taskState==1){
		return "录入已完成";
	}else if(taskState==2){
		return "审核已完成";
	}else if(taskState==3){
		return "评估已完成";
	}else{
		return "已结束";
	}
}

//处理月份或天带有0的情况
function deleteZero(str){
	if(str.charAt(0)=='0'){
		return str.charAt(1);
	}else
		return str;
	
}

//设置时间格式如：2016.03.18
function  setTimeStyle(date){
	
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	month=month<10?"0"+month:""+month;
	var day=date.getDate();
	day=day<10?"0"+day:""+day;
	return year+"."+month+"."+day;
}

//字符串截取
function  subStr(str,length){
	if(str.length>length){
		return str.substring(0,length)+"...";
	}else{
		return str;
	}
}