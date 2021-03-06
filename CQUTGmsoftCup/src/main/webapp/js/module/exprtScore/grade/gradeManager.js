var isOver = false;
var target1 = [];
var target2 = [];
var target3 = [];
var target4 = [];
var resuleScore = [];
var maxLevel = 1;
var checkValidation=true;
var expertTargetDistributeID = "";

$(document).ready(function() {
	getTime();
	getProject();
	findTargets();
	getExpertTargetDistribute();
	bindBlurToInput();
});

function getProject(){
	ProjectService.getFile(teamId,function(data){
		var showRelatedDocHtml = "";
		if(data){
			showRelatedDocHtml = "<li><a onclick=\"getID('"+data["fileId"]+"')\">"+data["ps_fileName"]
		      +"</a></li>";
			
		}else{
			showRelatedDocHtml = "<li><span>未提交文档</span></li>";
		}
		$(".relatedData").empty();
		$(".relatedData").append(showRelatedDocHtml);
	});
}

function findTargets(){
	target1 = [];
	target2 = [];
	target3 = [];
	target4 = [];
	resuleScore = [];
	maxLevel = 1;
	dwr.engine.setAsync(false);
	TargetService.getTargets([],"",[teamId,gameStepDetailID],"","",false,function(datas){
		if(datas){
			for(var v=0;v<datas.length;v++){
				var data = datas[v];
				maxLevel = data.targetLevel > maxLevel ? data.targetLevel : maxLevel;
				if(data.targetLevel == "1"){
					target1.push(data);
				}else if(data.targetLevel == "2"){
					target2.push(data);
				}else if(data.targetLevel == "3"){
					target3.push(data);
				}else if(data.targetLevel == "4"){
					target4.push(data);
				}
			}
			initTable();
		}else{
			
		}
	});
	dwr.engine.setAsync(true);
}

function initTable(){
	var tableHtml = "<table cellspacing='0px' style=\"word-break:break-all; word-wrap:break-all;\">";
	var width = parseInt(53 / maxLevel);
	tableHtml += "<tr  class='table-title'>"+getTitle(width)+"<td style=\"width:20%;text-align: center;\">指标说明</td><td style=\"width:8%;text-align: center;\">打分</td><td style=\"width:5%;text-align: center;\">排名</td><td style=\"width:"+(100-(width * maxLevel + 8 + 5 + 20))+"%;text-align: center;\">操作</td></tr>";
	for(var i=0;i<target1.length;i++){
		if(target1[i].isLastTarget == "1"){
			tableHtml += "<tr><td style=\"text-align: left;\" rowspan=\""+target1[i].count+"\">"+target1[i].targetName+"("+target1[i].targetScore+"分)</td>";
			for(var k = 0;k<maxLevel-1;k++){
				tableHtml += "<td>&nbsp;</td>";
			}
			tableHtml += operatorColumn(target1[i]);
		}else{
			var isAddChild2 = true;
			tableHtml += "<tr><td style=\"text-align: left;\" rowspan=\""+target1[i].count+"\">"+target1[i].targetName+"("+target1[i].targetScore+"分)</td>";
			var parentCode1 = target1[i].targetCode;
			for(var j=0;j<target2.length;j++){
				if(target2[j].targetParentCode == parentCode1){
					if(target2[j].isLastTarget == "1"){
						if(isAddChild2){
							isAddChild2 = false;
						}else{
							tableHtml += "<tr>";
						}
						tableHtml += "<td style=\"text-align: left;\" rowspan=\""+target2[j].count+"\">"+target2[j].targetName+"("+target2[j].targetScore+"分)</td>";
						for(var k = 0;k<maxLevel-2;k++){
							tableHtml += "<td>&nbsp;</td>";
						}
						tableHtml += operatorColumn(target2[j]);
					}else{
						if(isAddChild2){
							isAddChild2 = false;
						}else{
							tableHtml += "<tr>";
						}
						var isAddChild3 = true;
						tableHtml += "<td style=\"text-align: left;\" rowspan=\""+target2[j].count+"\">"+target2[j].targetName+"("+target2[j].targetScore+"分)</td>";
						var parentCode2 = target2[j].targetCode;
						for(var m=0;m<target3.length;m++){
							if(target3[m].targetParentCode == parentCode2){
								if(target3[m].isLastTarget == "1"){
									if(isAddChild3){
										isAddChild3 = false;
									}else{
										tableHtml += "<tr>";
									}
									tableHtml += "<td style=\"text-align: left;\" rowspan=\""+target3[m].count+"\">"+target3[m].targetName+"("+target3[m].targetScore+"分)</td>";
									for(var k = 0;k<maxLevel-3;k++){
										tableHtml += "<td>&nbsp;</td>";
									}
									tableHtml += operatorColumn(target3[m]);
								}else{
									if(isAddChild3){
										isAddChild3 = false;
									}else{
										tableHtml += "<tr>";
									}
									var isAddChild4 = true;
									tableHtml += "<td style=\"text-align: left;\" rowspan=\""+target3[m].count+"\">"+target3[m].targetName+"("+target3[m].targetScore+"分)</td>";
									var parentCode3 = target3[m].targetCode;
									for(var n=0;n<target4.length;n++){
										if(target4[n].targetParentCode == parentCode3){
											if(isAddChild4){
												isAddChild4 = false;
											}else{
												tableHtml += "<tr>";
											}
											tableHtml += "<td style=\"text-align: left;\" rowspan=\""+target4[n].count+"\">"+target4[n].targetName+"("+target4[n].targetScore+"分)</td>";
											tableHtml += operatorColumn(target4[n]);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	tableHtml += "</table>";
	$("#mainTable").empty();
	$("#mainTable").append(tableHtml);
}

function operatorColumn(target){
	var html = "";
	var result = {};
	result.targetScoreID = target.targetScoreID;
	result.targetCode = target.targetCode;
	result.score = target.score;
	result.targetName = target.targetName;
	result.fullScore = target.targetScore;
	if(target.score == null || target.score == ""){
		result.isHave = "0";
	}else{
		result.isHave = "1";
	}
	resuleScore.push(result);
	html += "<td style=\"text-align: left;\">"+target.targetExplain+"</td>";
	if(isOver){
		html += "<td style='text-align:center;'><div id=\""+target.targetCode+"Info\" style=\"display:none;\"><img src='images/grade/sanjiao.png' style=\"position:absolute;margin-top: 27px;\"/><div style=\"position:absolute;width:auto;height:30px;color:#EAAA33;background:#3b3b3b;margin-left: -20px;margin-top: 33px;padding-left: 5px;padding-right: 5px;\">最高分:"+target.max+" 最低分:"+target.min+"</div></div><input style=\"border:#8f8f8f solid 1px;\" onmouseenter=\"designExpert('"+target.targetCode+"')\" onmouseleave=\"hideDesignExpert('"+target.targetCode+"')\" class='openWin' targetScore=\""+target.targetScore+"\" type=\"text\" id='"+target.targetCode+"' value='" +target.score+"'  disabled=\"disabled\"/> 分</td>";
		$("#save").hide();
	}else{
		html += "<td style='text-align:center;'><div id=\""+target.targetCode+"Info\" style=\"display:none;\"><img src='images/grade/sanjiao.png' style=\"position:absolute;margin-top: 27px;\"/><div style=\"position:absolute;width:auto;height:30px;color:#EAAA33;background:#3b3b3b;margin-left: -20px;margin-top: 33px;padding-left: 5px;padding-right: 5px;\">最高分:"+target.max+" 最低分:"+target.min+"</div></div><input style=\"border:#8f8f8f solid 1px;\" onmouseenter=\"designExpert('"+target.targetCode+"')\" onmouseleave=\"hideDesignExpert('"+target.targetCode+"')\" class='openWin' targetScore=\""+target.targetScore+"\" type=\"text\" id='"+target.targetCode+"' value='" +target.score+"'/> 分</td>";
		$("#save").show();
	}
	html += "<td style=\"text-align: center;\">"+target.ranking+"</td>";
	html += "<td style='text-align:center;'><a onclick=\"doOtherGrade('"+target.targetCode+"','"+target.targetExplain+"','"+target.targetScore+"','"+target.targetName+"')\" style='cursor:pointer;' >" +"<span  class='sheetWord' class='otherMajor'>查看其他团队情况</span></a></td></tr>";
	return html;
}

function doOtherGrade(targetCode,targetExplain,targetScore,targetName){
	window.open(basePath + "/module/exprtScore/grade/otherDisciplineScore.jsp?targetCode="+targetCode+"&gameStepDetailID="+gameStepDetailID+"&targetExplain="+targetExplain+"&targetScore="+targetScore+"&targetName="+targetName);
}

function getTitle(width){
	var html = "";
	switch(maxLevel){
	case 1:
		html = "<td style=\"width:"+width+"%;text-align: center;\">一级指标</td>";
		break;
	case 2:
		html = "<td style=\"width:"+width+"%;text-align: center;\">一级指标</td><td style=\"width:"+width+"%;text-align: center;\">二级指标</td>";
		break;
	case 3:
		html = "<td style=\"width:"+width+"%;text-align: center;\">一级指标</td><td style=\"width:"+width+"%;text-align: center;\">二级指标</td><td style=\"width:"+width+"%;text-align: center;\">三级指标</td>";
		break;
	case 4:
		html = "<td style=\"width:"+width+"%;text-align: center;\">一级指标</td><td style=\"width:"+width+"%;text-align: center;\">二级指标</td><td style=\"width:"+width+"%;text-align: center;\">三级指标</td><td style=\"width:"+width+"%;text-align: center;\">四级指标</td>";
		break;
	}
	return html;
}

function getTime() {
	GameStepDetailService.getTime(gameStepDetailID, function(data) {
		if (data == "over") {
			$("#deadLine").html("当前不是评审时间");
			isOver = true;
		} else {
			$("#deadLine").html(data);
		}
	});
}

function getExpertTargetDistribute() {
	dwr.engine.setAsync(false);
	ExpertTargetDistributeService.getData(teamId,gameStepDetailID,function(data) {
		if (data) {
			expertTargetDistributeID = data.expertTargetDistributeID;
		}
	});
	dwr.engine.setAsync(true);
}

// 输入框失去焦点的时候，验证数据
function bindBlurToInput() {
	$(".openWin").blur(function() {
		if (!isInteger($(this).val())) {// 检查输入是否是数字
			if(!$(this).val() == ""){
				$(this).css("borderColor", "red");
				$(this).css("border-style", "solid");
				$(this).css("border-width", "1px");
				$(this).parent().find("span").text('请输入非负数');
				if (checkValidation) {
					checkValidation = false;
				}
			}
		} else {
			var value = parseInt($(this).val());
			var targetScore = parseInt($(this).attr("targetScore"));
			if (value > targetScore) {// 检查输入数字是否合法
				$(this).css("borderColor", "red");
				$(this).css("border-style", "solid");
				$(this).css("border-width", "1px");
				$(this).parent().find("span").text('输入分值超过满分');
				if (checkValidation) {
					checkValidation = false;
				}
			} else {
				$(this).css("borderColor", "#8f8f8f");
				$(this).css("border-style", "solid");
				$(this).css("border-width", "1px");
				$(this).parent().find("span").text('');
			}
		}
	});
}

// 关闭浮动框
function closeWin() {
	var width = 75;
	var timerClose = setInterval(function() {
		if (width > 98) {
			window.clearInterval(timerClose);
		} else {
			width += 1;
		}
		$("#move").css("width", width + "%");
	}, 20);
	$("#showData").slideUp(1000);
	$("#floatIcon").slideDown(1000);
}

// 打开浮动框
function openWin(targetCode) {
	var width = 98;
	var timer = setInterval(function() {
		if (width <= 75) {
			window.clearInterval(timer);
		} else {
			width -= 1;
		}
		$("#move").css("width", width + "%");
	}, 20);
	//showRelatedDocument(targetCode);
	$("#showData").slideDown(1000);
	$("#floatIcon").slideUp(1000);
}

function saveScore(){
	var addResult = [];
	var updateResult = [];
	var deleteResule = [];
	for(var i=0;i<resuleScore.length;i++){
		var score = $("#" + resuleScore[i].targetCode).val();
		if(isInteger(score)){
			if(parseInt(score)<=parseInt(resuleScore[i].fullScore)){
				if(resuleScore[i].isHave == "1"){
					if(parseInt(score)!=parseInt(resuleScore[i].score)){
						resuleScore[i].score = score;
						updateResult.push(resuleScore[i]);
					}
				}else{
					resuleScore[i].score = score;
					addResult.push(resuleScore[i]);
				}
			}else{
				alert("您对指标'" + resuleScore[i].targetName + "'评分超过了该指标的满分，请重新评分");
				return;
			}
		}else if(score == ""){
			if(resuleScore[i].isHave == "1"){
				resuleScore[i].score = score;
				deleteResule.push(resuleScore[i]);
			}else{
			}
		}else{
			alert("您对指标'" + resuleScore[i].targetName + "'评分格式不对，请输入小于等于"+resuleScore[i].fullScore+"正整数");
			return;
		}
	}
	dealResult(addResult,updateResult,deleteResule);
}

function dealResult(addResult,updateResult,deleteResule){
	console.info(JSON.stringify(addResult));
	console.info(JSON.stringify(updateResult));
	console.info(JSON.stringify(deleteResule));
	var addValue = true;
	var updateValue = true;
	var deleteValue = true;
	for(var i=0;i<addResult.length;i++){
		dwr.engine.setAsync(false);
		
		TargetScoreService.saveScore(expertTargetDistributeID,addResult[i].targetCode,addResult[i].score,function(data){
			if(!data){
				 addValue = false;
			}
		});
		dwr.engine.setAsync(true);
	}
	for(var j=0;j<updateResult.length;j++){
		dwr.engine.setAsync(false);
		TargetScoreService.updateScore(expertTargetDistributeID,updateResult[j].targetCode,updateResult[j].score,updateResult[j].targetScoreID,function(data){
			if(!data){
				updateValue = false;
			}
		});
		dwr.engine.setAsync(true);
	}
	var ids = [];
	for(var k=0;k<deleteResule.length;k++){
		ids.push(deleteResule[k].targetScoreID);
		dwr.engine.setAsync(false);
		TargetScoreService.deleteByIds(ids,function(data){
			if(!data){
				deleteValue = false;
			}
		});
		dwr.engine.setAsync(true);
	}
	if(addValue&&updateValue&&deleteValue){
		mWin.ok("保存成功");
		findTargets();
	}
}

function checkNum(){
	var count = 0;
	for(var i=0;i<resuleScore.length;i++){
		if(resuleScore[i].score == null||resuleScore[i].score == ""){
			count++;
		}
	}
	return count;
}

function isInteger(num){
	var reg = /^(0|[1-9]\d*)$/;
	if (reg.test(String(num))){
		return true;
	}else{
		return false;
	}
}

/**
 * 鼠标移入到指标上时的事件
 * targetCode：末级指标
 * level：指标等级
 * isHasFour：是否有第四级（如果有则指定专家的图片偏移量减少）
 */
function designExpert(targetCode){
	$("#"+targetCode + "Info").show();
}

/**
 * 鼠标移出时的效果
 * PS：背景色消失，指定图片消失
 * @param targetCode：需要显示的targetCode
 */
function hideDesignExpert(targetCode){
	$("#"+targetCode + "Info").hide();
}

function getID(id) {
	dwr.engine.setAsync(false);
	ProjectService.getData(id, function(data) {
		if (data.length > 0) {
			loadFile(data[0]["fileId"]);
		} else {
			alert('该文件不存在');
		}
	});
	dwr.engine.setAsync(true);
}

//下载文档
function loadFile(fileID) {
	SystemFileService.downloadFile(fileID, function(data) {
		if (data) {
			dwr.engine.openInDownload(data);
		} else {
			alert('该文件不存在');
		}
	});
}