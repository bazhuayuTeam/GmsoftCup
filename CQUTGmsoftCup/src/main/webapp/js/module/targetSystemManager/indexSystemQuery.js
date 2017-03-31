var pageData = ChildDialogUtil.getExchangeData();

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
	$("#targetHead").html(pageData.standardVersionName);
	findTargets();
});

function findTargets(){
	target1 = [];
	target2 = [];
	target3 = [];
	target4 = [];
	resuleScore = [];
	maxLevel = 1;
	dwr.engine.setAsync(false);
	TargetService.findTargets([],"standardVersionID='"+pageData.targetSysVersionID+"'","","",false,function(datas){
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
	var width = parseInt(70 / maxLevel);
	tableHtml += "<tr  class='table-title'>"+getTitle(width)+"<td style=\"width:30%;text-align: center;\">指标说明</td></tr>";
	for(var i=0;i<target1.length;i++){
		if(target1[i].isLastTarget == "1"){
			tableHtml += "<tr><td style=\"text-align: left;\" rowspan=\""+target1[i].count+"\">"+target1[i].targetName+"("+target1[i].targetScore+"分)</td>";
			for(var k = 0;k<maxLevel-1;k++){
				tableHtml += "<td>&nbsp;</td>";
			}
			tableHtml += "<td style=\"text-align: left;\">"+target1[i].targetExplain+"</td></tr>";
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
						tableHtml += "<td style=\"text-align: left;\">"+target2[j].targetExplain+"</td></tr>";
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
									tableHtml += "<td style=\"text-align: left;\">"+target3[m].targetExplain+"</td></tr>";
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
											tableHtml += "<td style=\"text-align: left;\">"+target4[n].targetExplain+"</td></tr>";
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
	$("#table").empty();
	$("#table").append(tableHtml);
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