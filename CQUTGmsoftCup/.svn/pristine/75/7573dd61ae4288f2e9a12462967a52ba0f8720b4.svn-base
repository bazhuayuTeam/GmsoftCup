$(function() {
	$(".username").Watermark("请输入学号", "#8b8b8b");

});

function query() {
	var usernameT = document.getElementById("username");
	var username = usernameT.value;
	if (username != "" && username != "请输入学号") {
		GameStepService.findMapByPropertiesWithLimit( [ "mo_codeTableName",
				"tm_no", "cm_teamId","tm_checkState","tm_gameStyle" ], "cm.UserId=" + "'"
				+ username.toString() + "'", "tm.no", "ASC", true, 0, 3,
				function(data) {
					if (data.length != 0) {
						var html="";
						for(var i=0;i<data.length;i++){
							var state="";
							if(data[i].tm_checkState=="0"){
								state="正在审核中...";
							}
							else if(data[i].tm_checkState=="1"){
								state="报名成功!";
							}
							else if(data[i].tm_checkState=="3"){
								state="被退回!";
							}
							else if(data[i].tm_checkState=="2"){
								state="未报名!";
							}
							
							var style="";
							if(data[i].tm_gameStyle=="0"){
								style="团队";
							}
							else if(data[i].tm_gameStyle=="1"){
								style="个人";
							}
							
							html+=data[i].mo_codeTableName+"("+style+"):" + state+"\n您的编号为 "
								+ data[i].tm_no+"\n\n";
						}
						alert(html);
					} else {
						alert("暂无该学生报名信息");
					}
				});
	} else {
		alert("请输入学号！");
	}
}