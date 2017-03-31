;

var targetSysVersionID='';
var targetRelateData={
	isHaveExpert:false,//是否是专家
	isHaveAuditPerson:false,//是否是审核人员
	isOperator:false,//是否有操作
	isDifferent:false,//是否各行换色
	isExplain:false,//是否有解释
	isTdmouseEvent:false,//是否有单元格的移入移出事件
	
	tableTitle:new Array(),
	tableTitleWidth:new Array(),
	targetCodes:new Array(),
	firstTarget:new Array(),
	secondTarget:new Array(),
	thirdTarget:new Array(),
	fourTarget:new Array(),
	expert:new Array(),
	auditPerson:new Array(),
	targetCodeMajors:new Array(),
	takePartMajorNum:0,
	isChangeColor:false,
	currentNum:1,
	backgroundColor:['#f3ece6','#ffffff']
};

var selectData={
	selectMajors:new Array//选择的要指定专业
};

//------------------------------开始-----------------------------------------------

function targetTable(targetSetting){
	targetSysVersionID = targetSetting.targetSysVersionID;
	cleanData();
	initTargetData(targetSetting);
	initTargetCode();
	if(targetRelateData.isHaveExpert){
		loadExpertData();
	}else{
		if(targetRelateData.isHaveAuditPerson){
			//加载已经分配的审核人员审核人员
			loadOperatorData();
		}
	}
	
	if(targetRelateData.isOperator){
		findMajorNumInTask();
		if(targetRelateData.isHaveExpert){
			findMajorNumOfTargetCode();
		}else {
			findMajorNumOfTargetCodeOperator();
		}
	}
	
	if(targetRelateData.targetCodes.length==0){//没有数据
		noData.show("#MainArea");
	}else{
		sortTargetOfLevel(targetRelateData.targetCodes);
		loadTable();
	}
}

/**
 * 将数据归空
 */
function cleanData(){
	targetRelateData.firstTarget=new Array();
	targetRelateData.secondTarget=new Array();
	targetRelateData.thirdTarget=new Array();
	targetRelateData.fourTarget=new Array();	
}

/**
 * 初始化参数
 * targetSetting：传过来的配置文件
 */
function initTargetData(targetSetting){
	targetRelateData.isHaveExpert=targetSetting.isHaveExpert;
	targetRelateData.isHaveAuditPerson=targetSetting.isHaveAuditPerson;
	targetRelateData.isOperator=targetSetting.isOperator;
	targetRelateData.isDifferent=targetSetting.isDifferent;
	targetRelateData.isExplain=targetSetting.isExplain;
	targetRelateData.tableTitle=targetSetting.tableTitle;
	targetRelateData.tableTitleWidth=targetSetting.tableTitleWidth;
	targetRelateData.targetCodes=targetSetting.targetCodes;
	targetRelateData.isTdmouseEvent=targetSetting.isTdmouseEvent;	
	targetRelateData.isChangeColor=targetSetting.isChangeColor;
}

/**
 * 将指标按照等级分配
 * @param targetCodes
 */
function sortTargetOfLevel(targetCodes){
	for(var i=0;i<targetCodes.length;i++){
		var level=targetCodes[i].targetLevel;
		var temp=targetCodes[i];
		
		if(level==1){
			targetRelateData.firstTarget.push(temp);
		}else if(level==2){
			targetRelateData.secondTarget.push(temp);
		}else if(level==3){
			targetRelateData.thirdTarget.push(temp);
		}else{
			targetRelateData.fourTarget.push(temp);
		}
	}
}

/**
 * 加载表格
 */
function loadTable(){
	var body=loadTableBody();
	var tableHead=loadTableHead();
	var table=body+tableHead;
	for(var i=0;i<targetRelateData.firstTarget.length;i++){
		var targetCode=targetRelateData.firstTarget[i];
	
		if(targetRelateData.currentNum%2==0&&targetRelateData.isChangeColor){
			table+="<tr style=\"background:"+targetRelateData.backgroundColor[0]+"\">";
		}else{
			table+="<tr>";
		}
		
		table+=loadFirstHtml(targetCode);
		table+="</tr>";
		targetRelateData.currentNum++;
	}
	
	$("#MainArea").html(table);
}

/**
 * 加载表格的范围
 */
function loadTableBody(){
	var html="<table  id=\"table\" border=\"0\" cellspacing=\"0px\""+
					"style=\"font-family:'微软雅黑';font-size:13px;margin-top:10px;margin-bottom:20px;width:97%\" class=\"table-content\">";
	
	return html;
}

/**
 * 加载表头
 * @returns {String}
*/
function loadTableHead(){
	var head="<tr class=\"table-title\">";
	$(targetRelateData.tableTitle).each(function(index,val){
		var width=targetRelateData.tableTitleWidth[index];
		if(val=='主要观测点'){
			head+="<td colspan='2' style=\"width:"+width+"%;text-align:center\">"+val+"</td>";
		}else{
			head+="<td colspan='1' style=\"width:"+width+"%;text-align:center\">"+val+"</td>";
		}
		
	});
	
	return head+"</tr>";
}

/**
 * 拼接一级指标的代码
 * @param firstCode：一级指标
 * @returns {String}
*/
function loadFirstHtml(firstCode){
	var targetCode=firstCode.targetCode;
	var targetName=firstCode.targetName;
	var count=parseInt(firstCode.count);
	var secondTarget=findTarget(targetCode,2);
	var explain=firstCode.targetExplain;
	var level=firstCode.targetLevel;
	
	if(explain==null||explain==""){
		explain="";
	}
	
	var html="<td rowspan=\""+count+"\" ";
	if(targetRelateData.isTdmouseEvent){
		html+="style=\"cursor:pointer\"" +
			" id=\""+targetCode+"\" " +
			"onmouseenter=\"designExpert('"+targetCode+"','"+level+"')\" " +//移入
			"onmouseleave=\"hideDesignExpert('"+targetCode+"')\"";//移出
	}
	
	html+=">"+targetName+"</td>";
	
	if(secondTarget.length==0){
		html+="<td></td><td colspan='2'></td><td style=\"text-align:left;\">"+explain+"</td>";
		
		if(targetRelateData.isHaveExpert||targetRelateData.isHaveAuditPerson){
			html+=loadExpertOrAuditPerson(targetCode,targetName,explain);
		}	
	}else{
		for(var i=0;i<secondTarget.length;i++){
			if(i!=0){
				if(targetRelateData.currentNum%2==0&&targetRelateData.isChangeColor){
					html+="<tr style=\"background:"+targetRelateData.backgroundColor[0]+"\">";
				}else{
					html+="<tr>";
				}
			}
			
			html+=loadSecondHtml(secondTarget[i]);
		}
		
	}
	
	return html;
}

/**
 * 拼接二级指标的代码
 * @param secondCode：二级指标
 * @returns {String}
 */
function loadSecondHtml(secondCode){
	var targetCode=secondCode.targetCode;
	var targetName=secondCode.targetName;
	var explain=secondCode.targetExplain; 
	var count=parseInt(secondCode.count);
	var thirdTarget=findTarget(targetCode,3);
	var level=secondCode.targetLevel;
	
	if(explain==null){
		explain="";
	}
	
	var html="<td rowspan=\""+count+"\"";
	if(targetRelateData.isTdmouseEvent){
		html+=" onmouseleave=\"hideDesignExpert('"+targetCode+"')\" " +
		"onmouseenter=\"designExpert('"+targetCode+"','"+level+"')\" " +
		"style=\"cursor:pointer\""+
		" id=\""+targetCode+"\"";
	}	
	html+=">"+targetName+"</td>";
	
	if(thirdTarget.length==0){
		html+="<td colspan='2'></td><td style=\"text-align:left;\">"+explain+"</td>";
		
		if(targetRelateData.isHaveExpert||targetRelateData.isHaveAuditPerson){
			html+=loadExpertOrAuditPerson(targetCode,targetName,explain);
		}		
	}else{
		for(var i=0;i<thirdTarget.length;i++){
			if(i!=0){
				if(targetRelateData.currentNum%2==0&&targetRelateData.isChangeColor){
					html+="<tr style=\"background:"+targetRelateData.backgroundColor[0]+"\">";
				}else{
					html+="<tr>";
				}
			}
			
			html+=loadThirdHtml(thirdTarget[i]);
		}
	}
	
	return html;
}

/**
 * 拼接三级指标的代码
 * @param thirdCode：三级指标
 * @returns {String}
 */
function loadThirdHtml(thirdCode){
	var targetCode=thirdCode.targetCode;
	var targetName=thirdCode.targetName;
	var count=parseInt(thirdCode.count);
	var targetExplain=thirdCode.targetExplain;
	var fourTarget=findTarget(targetCode,4);
	var level=thirdCode.targetLevel;
	var html="";
	
	if(targetExplain==null){
		targetExplain="";
	}
	
	if(fourTarget.length==0){
		html+="<td colspan='2' " +
				"rowspan=\""+count+"\"";
				if(targetRelateData.isTdmouseEvent){
					html+="onmouseleave=\"hideDesignExpert('"+targetCode+"')\" " +
					"onmouseenter=\"designExpert('"+targetCode+"','"+level+"')\"" +
					"style=\"cursor:pointer;text-align:left;\"" +
					"id=\""+targetCode+"\"";
				}else{
					html+=" style=\"text-align:left;\"";
				}
				html+=">"+targetName+"</td>" +
				"<td style=\"text-align:left;\">"+targetExplain+"</td>";
		if(targetRelateData.isHaveExpert||targetRelateData.isHaveAuditPerson){
			html+=loadExpertOrAuditPerson(targetCode,targetName,targetExplain);
		}
	}else{
		html+="<td colspan='1' " +
			"rowspan=\""+count+"\" ";
			if(targetRelateData.isTdmouseEvent){
				html+="onmouseleave=\"hideDesignExpert('"+targetCode+"')\" " +
				"onmouseenter=\"designExpert('"+targetCode+"','"+level+"','1')\"" +
				"style=\"cursor:pointer;text-align:left;\"" +
				"id=\""+targetCode+"\"";
			}else{
				html+=" style=\"text-align:left;\"";
			}
			
			html+=">"+targetName+"</td>";
	}
	
	for(var i=0;i<fourTarget.length;i++){
		if(i!=0){
			if(targetRelateData.currentNum%2==0&&targetRelateData.isChangeColor){
				html+="<tr style=\"background:"+targetRelateData.backgroundColor[0]+"\">";
			}else{
				html+="<tr>";
			}
		}
		
		html+=loadFourHtml(fourTarget[i]);
	}
	return html;
}

/**
 * 拼接四级指标
 * @param fourCode：四级指标
 * @returns {String}
 */
function loadFourHtml(fourCode){
	var targetCode=fourCode.targetCode;
	var targetName=fourCode.targetName;
	var targetExplain=fourCode.targetExplain;
	var level=fourCode.targetLevel;
	
	if(targetExplain==null){
		targetExplain="";
	}
	
	var html="<td ";
	if(targetRelateData.isTdmouseEvent){
		html+="onmouseenter=\"designExpert('"+targetCode+"','"+level+"')\" " +
		"onmouseleave=\"hideDesignExpert('"+targetCode+"')\" " +
		"style=\"cursor:pointer;text-align:left;\" " +
		"id=\""+targetCode+"\"";
	}else{
		html+=" style=\"text-align:left;\"";
	}
	
	html+=">"+targetName+"</td>" +
			"<td style=\"text-align:left;\">"+targetExplain+"</td>";
	
	if(targetRelateData.isHaveExpert||targetRelateData.isHaveAuditPerson){
		html+=loadExpertOrAuditPerson(targetCode,targetName,targetExplain);
	}
	
	return html;
}

/**
 * 加载专家的代码
 * @param targetCode：末级指标code
 * @param targetName：指标名称
 * @param targetExplain：指标解释说明
 * @returns {String}
 */
function loadExpertOrAuditPerson(targetCode,targetName,targetExplain){
	var html="<td id=\""+targetCode+"Operator\">";
	var targetExitMajor=new Array();
	
	if(targetRelateData.isHaveExpert){
		var temp=loadExpertHtml(targetCode);
		html+=temp.html;
		targetExitMajor=temp.targetExitMajor;
	}else{
		if(targetRelateData.isHaveAuditPerson){
			//拼接审核人员的htnl
			var temp=loadOperatorHtml(targetCode);
			html+=temp.html;
			targetExitMajor=temp.targetExitMajor;
		}
	}
	
	if(targetRelateData.isOperator){
		html+=loadOperator(targetCode,targetName,targetExplain,targetExitMajor);
	}
	
	return html+"</a></td>";
}

/**
 * 拼接审核人员的html
 * 数据来源：加载的是初始已经分配的人员
 * targetCode:当前指标
 */
function loadAuditPersonExpert(targetCode){
	
}

/**
 * 拼接专家的html
 * 数据来源：初始加载的已经分配的专家
 * targetCode:当前指标
 */
function loadExpertHtml(targetCode){
	var targetExitMajor=new Array();
	var html="";
	
	$(targetRelateData.expert).each(function(index,val){
		var expertTarget=val.targetCode;
		var expertID=val.ex_expertID;
		var expertName=val.ex_name;
		
		if(jQuery.inArray(expertID,targetExitMajor)<0){
			targetExitMajor.push(expertID);
		}
		
		
		if(expertTarget==targetCode){
			var temp="<a onmouseenter=\"inExpertName('"+expertTarget+"','"+expertID+"','"+expertName+"')\" " +
					"onmouseleave=\"hideExpertName('"+expertTarget+"','"+expertID+"','"+expertName+"')\" " +
					"style=\"cursor:pointer;margin:8px;\" " +
					"id=\""+expertID+targetCode+"\"><span class=\"sheetWord\">"+expertName+"</span>";
			html+=temp;
		}
	});
	
	var result={
		html:html,
		targetExitMajor:targetExitMajor
	};
	
	return result;
	
}

/**
 * 拼接审核人员的html
 * 数据来源：初始加载的已经分配的审核人员
 * targetCode:当前指标
 */
function loadOperatorHtml(targetCode){
	var targetExitMajor=new Array();
	var html="";

	$(targetRelateData.auditPerson).each(function(index,val){
		var operatorTarget=val.targetCode;
		var operatorID=val.op_operatorID;
		var operatorName=val.js_XM;
		if(jQuery.inArray(operatorID,targetExitMajor)<0){
			targetExitMajor.push(operatorID);
		}
		
		if(operatorTarget==targetCode){
			var temp="<div style='width:30%;float:left;'><a onmouseenter=\"inExpertName('"+operatorTarget+"','"+operatorID+"','"+operatorName+"',event)\" " +
				"onmouseleave=\"hideExpertName('"+operatorTarget+"','"+operatorID+"','"+operatorName+"')\" " +
				"style=\"cursor:pointer;\" " +
				"id=\""+operatorID+targetCode+"\"><span class=\"sheetWord\">"+operatorName+"</span></a></div>";
			html+=temp;
		}
	});
	
	var result={
		html:html,
		targetExitMajor:targetExitMajor
	};
	return result;
	
}

/**
 * 加载备注操作栏
 * @param targetCode：指标
 * @param targetName：指标名字
 * @param targetExplain：解释
 * @param targetExitMajor：该指标下已经存在的指标
 * @returns {String}
 * 
 * PS:审核人员和审核专家通用
 */
function loadOperator(targetCode,targetName,targetExplain,targetExitMajor){
	var exitNumber=targetRelateData.takePartMajorNum;//该任务下参加的专业数
	
	var hasTakeNumber=exitNumber;//还有多少个专业没有参加
	
	for(var i=0;i<targetRelateData.targetCodeMajors.length;i++){
		if(targetRelateData.targetCodeMajors[i].targetCode==targetCode){
			hasTakeNumber--;
		}
	}
	
	var operator="<td id=\""+targetCode+"Remark\"><a onclick=\"remarkDetail('"+targetCode+"')\">" +
			"<span class=\"sheetWord\">"+
			hasTakeNumber+
			"个专业未分配</span>" +
			"</a></td>";
	
	return operator;
}

//----------------------------找到各个级别的----------------------------------------
function findTarget(targetCode,level){
	var result=new Array();
	var temp=new Array();
	
	if(level==1){
		temp=targetRelateData.firstTarget;
	}else if(level==2){
		temp=targetRelateData.secondTarget;
	}else if(level==3){
		temp=targetRelateData.thirdTarget;
	}else{
		temp=targetRelateData.fourTarget;
	}
	
	
	for(var i=0;i<temp.length;i++){
		var target=temp[i];
		if(target.targetParentCode==targetCode){
			result.push(target);
		}
	}
	
	return result;
}

//---------------------------数据------------------------------------------------
/**
 * 得到所有的指标（后台封装的数据）
 */
function initTargetCode(){
	var properties=["targetCode","targetName","targetExplain","targetLevel",
			         "targetParentCode","isLastTarget","targetScore"];
	var sortField="targetCode";
	var order=",showOrder";
	var needLink=false;
	var condition="standardVersionID='"+targetSysVersionID+"'";
	
	dwr.engine.setAsync(false);
	TargetService.findTargets(properties,condition,sortField,order,needLink,function(data){
		if(data!=null){
			targetRelateData.targetCodes=data;
		}
	});
	dwr.engine.setAsync(true);
}

/**
 * 加载一个指标下有多少个专业进行参加
 * targetCode：指标code（末级,可以是末级指标数组）
 */
function findMajorNumOfTargetCode(targetCode){
	var result=new Array();
	var taskID=$("#task").val();
	var append="";	
	var targetSysVersion=$("#version").val();

	if(targetCode){
		append+=" and targetCode in("+targetCode+")";
	}
	var condition="taskID='"+taskID+"' and targetSysVersionID='"+targetSysVersion+"'";
	condition+=append;
	condition+=" ORDER BY TARGETCODE";
	dwr.engine.setAsync(false);
	ExpertTargetDistributeService.findMapByPropertiesQuick(["targetCode","taskDetailID"],
												condition,
												false,
												function(data){
		if(data!=null){
			targetRelateData.targetCodeMajors=data;
			result=data;
		}
		
	});
	dwr.engine.setAsync(true);
	
	return result;
}

//---------------------------------事件相关------------------------------------

/**
 * 鼠标移入到指标上时的事件
 * targetCode：末级指标
 * level：指标等级
 * isHasFour：是否有第四级（如果有则指定专家的图片偏移量减少）
 */
function designExpert(targetCode,level,isHasFour){
	$("img").remove();
	var majors="";
	var imgPath="images/module/designExpert/designExpert.png";
	if(targetRelateData.isHaveAuditPerson) {
		imgPath = "images/module/designExpert/auditOperator.png";
	}
	
	var marginLeft=20;
	
	if(isHasFour==1){
		marginLeft=-10;
	}
	
	var appendHtml="<div onclick=\"openSelectExpert('"+majors+"','"+targetCode+"','"+1+"')\"" +
			"style=\"text-align:center;position:absolute;margin-top:-35px;margin-left:"+marginLeft+"px;\" " +
			"id=\""+targetCode+"Img\">" +
			"<img src=\""+imgPath+"\" alt=''指定 /></div>";
	$("#"+targetCode).css("background","#f3ece6");
	$("#"+targetCode).prepend(appendHtml);
}

/**
 * 鼠标移出时的效果
 * PS：背景色消失，指定图片消失
 * @param targetCode：需要显示的targetCode
 */
function hideDesignExpert(targetCode){
	$("#"+targetCode).css("background","#ffffff");
	$("#"+targetCode+"Img").remove();
}

/**
 * 移入专家时显示信息（显示信息和删除按钮）
 * @param targetCode：指标的code
 * @param personID：人员的ID
 * @param personName：人员的名字
 * PS：审核专家和评估专家用同一个（链接的ID为code和expertID）
 */
function inExpertName(targetCode,personID,personName,event){
	var deleteImgPath="images/module/designExpert/deleteExpert.png";
	var linkID=personID+targetCode;
	
	//将删除按钮显示到专家上
	var html="<img alt=\"删除\" " +
			"src=\""+deleteImgPath+"\""+
			"style=\"position:absolute;margin-left:-50px;margin-top:-30px;\" "+
			"id=\"deleteImage\" onclick=\"deleteExpert('"+personID+"','"+targetCode+"')\"/>";
	$("#"+linkID).append(html);
	if(targetRelateData.isHaveAuditPerson) {
		showMessage(targetCode, personID,event);
	}else {
		showExpertInformation(targetCode,personID,personName);
	}	
}

/**
 * 移除专家名称时的信息
 * @param targetCode：指标的code
 * @param personID：人员的ID
 * @param personName：人员的名字
 */
function hideExpertName(targetCode,personID,personName){
	$("#deleteImage").remove();
	if(targetRelateData.isHaveAuditPerson) {
		removeMessage();
	}else {
		
	}	
}

/**
 * 点击删除专家时按钮时
 * @param expertID：专家ID
 * @param targetCode：指标vode
 * PS：弹出弹框
 */
function deleteExpert(expertID,targetCode){
	var taskID=$("#task").val();
	var targetSysVersion=$("#version").val();
	
	if(targetRelateData.isHaveExpert) {
		DialogUtil.openFloatWindow("module/designatExpertManager/deletePerson.jsp",{expertID:expertID,targetCode:targetCode,taskID:taskID,targetSysVersion:targetSysVersion},		                                             
					{EVENT_OK:function(param){
						if(param.save){
							deleteExpertMajors(expertID,targetCode);
						}
					}
				});
	}else {
		deleteAuditOperator(expertID,targetCode);
	}		
}

function deleteAuditOperator(operatorID,targetCode) {
	DialogUtil.openFloatWindow("module/auditPerson/deleteAuditOperator.jsp",{operatorID:operatorID,targetCode:targetCode,taskID:$("#task").val(),targetSysVersion:$("#version").val()},
			{EVENT_OK:function(param){
				if(param.save){
					deleteOperator(operatorID,targetCode);
				}
			}
	});
}

function deleteExpertMajors(expertID,targetCode){
	var taskID=$("#task").val();
	var targetSysVersion=$("#version").val();
	
	dwr.engine.setAsync(false);
	ExpertTargetDistributeService.deleteExpertOfHead(targetSysVersion,
													 taskID,
													 expertID,
													 targetCode,
													 function(data){
		if(data){
			$("#"+expertID+targetCode).remove();
			var code=[];
			code[0]=targetCode;
			refreshNotTakeMajorNumber(code,taskID,targetSysVersion);	
			mWin.ok("删除成功");
		}
	});
	dwr.engine.setAsync(true);
}

//------------------------------------指定------------------------------------

/**
 * 指定评估专家和审核人员
 * 
 * majors：已经选择的专业
 * targetCode：末级指标的code
 * type：点击上一部返回的类型
 */
function openSelectExpert(majors,targetCode,type){
	var taskInMajors=targetRelateData.takePartMajorNum;
	if(taskInMajors==0){
		jAlert("此任务和指标下没有专业参加");
		return ;
	}
	if(targetRelateData.isHaveAuditPerson && !isHaveRelated(targetCode)) {
		jAlert("该指标下无对应的模板！");
		return ;
	}
	
	var taskID=$("#task").val();
	var targetSysVersion=$("#version").val();
	
	DialogUtil.openFloatWindow("module/designatExpertManager/designMajorSelect.jsp",
								{taskID:taskID,targetSysVersion:targetSysVersion,targetCode:targetCode,system:$("#system").val(),majors:majors, isExpert: targetRelateData.isHaveExpert},		                                             
								{EVENT_OK:function(param){
									selectData.selectMajors=param.majors;//选择的专业
									var type=param.type;
									openSelectExpertNext(param.majors,targetCode,type);//打开选择专家的弹框
		                        }
   	});
}

/**
 * 打开选择专家的弹框
 * @param majors：已经选择的专业
 * @param targetCode：末级指标的code
 * @param type：返回的类型（1-点击指标，2-点击备注）
 */
function openSelectExpertNext(majors,targetCode,type){
	var taskID=$("#task").val();
	var targetSysVersion=$("#version").val(); 

	if(targetRelateData.isHaveExpert) {
		DialogUtil.openFloatWindow("module/designatExpertManager/designatExpertAdd.jsp",
				{taskID:taskID,targetSysVersion:targetSysVersion,targetCode:targetCode,majors:majors},		                                             
				{EVENT_OK:function(param) {	
					if(param.save){//是否保存
						if(targetRelateData.isHaveExpert){
							$("#MainArea").mask("正在加载中,请耐心等候......");
							findLastTarge(targetCode,param.teacherAppend,param.name);
							$("#MainArea").unmask("");
						}
					}else{
						if(type==1){
							openSelectExpert(param.majors,targetCode,type);
						}else{
							remarkDetail(targetCode,majors);
						}								
					}
                }
			});
	}else {
		distrubuteOperator(targetCode, majors);
	}	
}

/**
 * 指定评估专家时，找到最后指标，然后保存
 * 
 * @param targetCode：末级指标
 * @param expert：选择的专家
 * @param name：选择专家的名字
 */
function findLastTarge(targetCode,expert,name){
//	var system=$("#system").val();
	var version=$("#version").val();
	var task=$("#task").val();
	//找到所选专业的任务明细表
	var taskDetailID=findTaskDetail(version,task,selectData.selectMajors);
	var lastTargetCode=findLastTarget(targetCode);
	var code=new Array();
	var taskDetail=[];
	
	var resultSave=new Array();
	for(var i=0;i<lastTargetCode.length;i++){
		code.push(lastTargetCode[i].targetCode);
		for(var j=0;j<expert.length;j++){
			for(var k=0;k<taskDetailID.length;k++){
				taskDetail.push(taskDetailID[k].taskDetailID);
				
				var param={
						taskID:task,
						targetCode:lastTargetCode[i].targetCode,
						expertID:expert[j],
						targetSysVersionID:version,
						taskDetailID:taskDetailID[k].taskDetailID,
				};
				resultSave.push(param); 
			}
		}
	}
	
	var save=saveExpert(resultSave,taskDetailID,code,task,version);
	if(save){
		getExpertNameInTheTargetCode(code,task,version,expert,name);//将添加的专家添加到表格
		refreshNotTakeMajorNumber(code,task,version);
		mWin.ok("保存成功");
	}
}


/**
 * 指定评估专家时，找到最后指标，然后保存
 * 
 * @param targetCode：末级指标
 * @param expert：选择的专家
 * @param name：选择专家的名字
 */
function findLastTargeOperator(targetCode,operators,name){
	var version=$("#version").val();
	var task=$("#task").val();
	//找到所选专业的任务明细表
	var taskDetailID=findTaskDetail(version,task,selectData.selectMajors);
	var lastTargetCode=findLastTarget(targetCode);
	var code=new Array();
	var taskDetail=[];
	
	var resultSave=new Array();
	for(var i=0;i<lastTargetCode.length;i++){
		code.push(lastTargetCode[i].targetCode);
		for(var j=0;j<expert.length;j++){
			for(var k=0;k<taskDetailID.length;k++){
				taskDetail.push(taskDetailID[k].taskDetailID);
				
				var param={
						taskID:task,
						targetCode:lastTargetCode[i].targetCode,
						expertID:expert[j],
						targetSysVersionID:version,
						taskDetailID:taskDetailID[k].taskDetailID,
				};
				resultSave.push(param); 
			}
		}
	}
	var save=saveExpert(resultSave,taskDetailID,code,task,version);
	if(save){
		getExpertNameInTheTargetCode(code,task,version,expert,name);//将添加的专家添加到表格
		refreshNotTakeMajorNumber(code,task,version);
		mWin.ok("保存成功");
	}
}



/**
 * 将指定的专家数据进行后台保存（后台方法）
 * 
 * @param result：要保存的数据
 * @param taskDetailID：所有的任务明细ID
 * @param code：要保存的所有targetCode
 * @param task：任务
 * @param version：版本
 * @returns {Boolean}
 */
function saveExpert(result,taskDetailID,code,task,version){
	var right=false;
	dwr.engine.setAsync(false);
	ExpertTargetDistributeService.addDistributeExpert(result,taskDetailID,code,task,version,function(data){
		if(data){
			right=true;
		}
	});
	dwr.engine.setAsync(true);
	return right;
}


/**
 * 找到本指标下专家的名字和信息，并且拼好代码
 * 
 * @param targetCode：末级指标
 * @param taskID：任务ID
 * @param targetSysVersionID：版本ID
 * @param expert：专家
 * @param name
 * @returns {String}
 */
function getExpertNameInTheTargetCode(targetCode,taskID,targetSysVersionID,expert,name){
	var html="";
	dwr.engine.setAsync(false);
	ExpertTargetDistributeService.findMapByPropertiesQuick(['ex_expertID','ex_name','targetCode'],
															"targetCode in("+getAllMajorsSql(targetCode)+") and taskID='"+taskID+"' and targetSysVersionID='"+targetSysVersionID+"' ORDER BY TARGETCODE",
															true,
															function(data){
		if(data){
			var targetCode=data[0].targetCode;
			var html="";
			
			for(var i=0;i<data.length;i++){
				if(targetCode!=data[i].targetCode){
					$("#"+targetCode+"Operator").html(html);
					html="";
					targetCode=data[i].targetCode;
				}
				html+="<a onmouseenter=\"inExpertName('"+data[i].targetCode+"','"+data[i].ex_expertID+"','"+data[i].ex_name+"')\" " +
					"onmouseleave=\"hideExpertName('"+data[i].targetCode+"','"+data[i].ex_expertID+"','"+data[i].ex_name+"')\"" +
					"style=\"cursor:pointer;margin:8px;\" " +
					"id=\""+data[i].ex_expertID+data[i].targetCode+"\"><span class=\"sheetWord\">"+data[i].ex_name+"</span>";
			}
			
			if(html!=""){
				$("#"+targetCode+"Operator").html(html);
			}
		}
	});
	dwr.engine.setAsync(true);
	return html;
}

/**
 * 刷新每个指标下参加的专业数目
 * @param code：要添加的targetCode
 * @param task：任务ID
 * @param version：版本ID
 * 
 * PS：通用
 */
function refreshNotTakeMajorNumber(code,task,version){
	var codeSql=getAllMajorsSql(code);
	var takedMajorCount=new Array();
	
	//数据来源
	if(targetRelateData.isHaveExpert){
		takedMajorCount=getTargetTaskDetailNum(codeSql);
	}else{
		if(targetRelateData.isHaveAuditPerson){
			//加载指标下的专业
			
		}
	}
	
	var exitNumber=targetRelateData.takePartMajorNum;//该任务下参加的专业数
	
	//当指标下不存在数据时
	if(takedMajorCount.length==0){
		for(var i=0;i<code.length;i++){
			var te="<a onclick=\"remarkDetail('"+targetCode+"')\">" +
			"<span class=\"sheetWord\">"+
			exitNumber+
			"个专业未分配</span>" +
			"</a>";
			$("#"+code[i]+"Remark").html(te);
		}
		
		return;
	}
	
	for(var i=0;i<takedMajorCount.length;i++){
		var targetCodeTemp=takedMajorCount[i].targetCode;
		var taskDetailNUm=takedMajorCount[i]["count('taskDetailID')"];
		var te="<a onclick=\"remarkDetail('"+targetCodeTemp+"')\">" +
			"<span class=\"sheetWord\">"+
			(exitNumber-taskDetailNUm)+
			"个专业未分配</span>" +
			"</a>";
		$("#"+targetCodeTemp+"Remark").html(te);
	}
}

/**
 * 找到一个指标下的专业明细表的数量
 * @param targetCode
 * @returns {Array}
 */
function getTargetTaskDetailNum(targetCode){
	var result=new Array();
	var taskID=$("#task").val();
	var append="";	
	var targetSysVersion=$("#version").val();

	if(targetCode){
		append+=" and targetCode in("+targetCode+")";
	}
	var condition="taskID='"+taskID+"' and targetSysVersionID='"+targetSysVersion+"'";
	condition+=append;
	condition+=" group by targetCode ORDER BY TARGETCODE";
	dwr.engine.setAsync(false);
	ExpertTargetDistributeService.findMapByPropertiesQuick(["targetCode","count('taskDetailID')"],
												condition,
												false,
												function(data){
		if(data!=null){
			targetRelateData.targetCodeMajors=data;
			result=data;
		}
		
	});
	dwr.engine.setAsync(true);
	
	return result;
}

/**
 * 找到一个指标下的所有末级指标
 * 
 * @param targetCode：要找的targetCode
 * @returns {Array}
 */
function findLastTarget(targetCode){
	var result=new Array();
	var condtion="TARGETCODE LIKE '"+targetCode+"%' and ISLASTTARGET=1";
	dwr.engine.setAsync(false);
	TargetService.findMapByPropertiesQuick(['targetCode'],condtion,false,function(data){
		if(data!=null){
			result=data;
		}
	});
	dwr.engine.setAsync(true);
	
	return result;
}

//找到所选专业的明细表
function findTaskDetail(version,task,majors){
	var result=[];
	dwr.engine.setAsync(false);
	TaskDetailService.findMapByPropertiesQuick(['taskDetailID'],"targetSysVersionID='"+version+"' and taskID='"+task+"' and majorId in("+getAllMajorsSql(majors)+")",false,function(data){
		if(data.length>0){
			result=data;
		}
	});
	dwr.engine.setAsync(true);
	return result;
}

/**
 * 将数组的数据拼接成sql形式
 * @param majors：要拼接的数据
 * @returns {String}
 */
function getAllMajorsSql(majors){
	var sql="";
	for(var i=0;i<majors.length;i++){
		sql+="'"+majors[i]+"'";
		if(i!=majors.length-1){
			sql+=",";
		}
	}
	return sql;
}

//------------------------------备注-------------------------------------------
/**
 * 找到没有分配和已经分配的专业（备注）
 * targetCode：需要查看的末级code
 * majors：选择的专业
 */
function remarkDetail(targetCode,majors){
	var taskInMajors=targetRelateData.takePartMajorNum;
	var task=$("#task").val();
	var version=$("#version").val();
	if(taskInMajors==0){
		jAlert("此任务和指标下没有专业参加");
		return ;
	}
	
	if(targetRelateData.isHaveExpert) {
		DialogUtil.openFloatWindow("module/designatExpertManager/majorRemark.jsp",
				{taskID:task,targetSysVersion:version,targetCode:targetCode,system:$("#system").val(),majors:majors},		                                             
				{EVENT_OK:function(param){
					selectData.selectMajors=param.majors;
					openSelectExpertNext(param.majors,targetCode,2);
	            }
		});
	}else {
		showTargetMajors(targetCode, majors);
	}	
}