$(document).ready(function() {
	showTargetInfo();
	getData();
});

function getData() {
	TargetScoreService.getOthers(gameStepDetailID, targetCode, function(data) {
		if (data) {
			showScoreTable(data);
		}
	});
}

// 显示指标信息
function showTargetInfo() {
	var head = "指标名称:" + targetName + "（满分:" + targetScore + "分）";
	$("#targetHead").text(head);
	$("#targetInfo").text("指标说明:" + targetExplain);
}

// 显示分数表格
function showScoreTable(scoreInfos) {
	var width = ($(".content").outerWidth(true)) / 5 - 290;
	var tableString = "<table  cellspacing='0px' border='0'><tr class='table-title'>"
			+ "<td width='" + width + "' style=\"text-align:center;\">团队名称</td>"
			+ "<td width='" + width + "' style=\"text-align:center;\">组长</td>"
			+ "<td width='" + width + "'  style=\"text-align:center;\">得分</td>"
			+ "<td width='" + width + "'  style=\"text-align:center;\">排名</td>"
			+ "<td width='" + width + "'  style=\"text-align:center;\">操作</td></tr>";
	if (scoreInfos != null&&scoreInfos.length>0) {
		for (var i = 0; i < scoreInfos.length; i++) {
			tableString += "<tr><td style='text-align:center;'>" + scoreInfos[i].teamName + "</td>";
			tableString += "<td style='text-align:center;'>" + scoreInfos[i].name + "</td>";
			tableString += "<td style='text-align:center;'>" + scoreInfos[i].Score + "</td>";
			tableString += "<td style='text-align:center;'>" + scoreInfos[i].link + "</td>";
			tableString += "<td style='text-align:center;'><a onclick=\"showRelatedDocument('"+scoreInfos[i].teamName+"','"+scoreInfos[i]["te_teamID"]+"')\" style='cursor:pointer;'>" +"<span class='sheetWord'>查看文档</span></a></td>";
			tableString += "</tr>";
			if(i == 0){
				showRelatedDocument(scoreInfos[i].teamName,scoreInfos[i]["te_teamID"]);
			}
		}
	} else {
		tableString += "<tr><td colspan='5'  style='text-align:center;'>暂无数据</td></tr>";
	}
	tableString += "</table>";
	$("#scoreTable").empty();
	$("#scoreTable").html(tableString);
}

/** ****************************************打分保存*********************************************** */
// 加载显示支撑材料
function showRelatedDocument(teamName,teamId) {
	$("#infoTitle").html("团队：" + teamName);
	ProjectService.getFile(teamId,function(data){
		var showRelatedDocHtml = "";
		if(data){
			showRelatedDocHtml = "<li><a onclick=\"loadFile('"+data["fileId"]+"')\">"+data["ps_fileName"]
		      +"</a></li>";
		}else{
			showRelatedDocHtml = "<li><span>暂无数据</span></li>";
		}
		$(".relatedData").empty();
		$(".relatedData").append(showRelatedDocHtml);
	});
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