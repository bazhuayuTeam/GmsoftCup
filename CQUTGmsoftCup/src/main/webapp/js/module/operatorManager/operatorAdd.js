;

var schoolYearID = "";
//初始化数据
Page.initComponent = function() {
	Page.Util.callOver = true;
}

//程序入口
$(document).ready(function() {
	Page.Data.exchangeData = ChildDialogUtil.getExchangeData();
	//创建控件
	$("#dialog-modal").createDialog( {
		height : 250,
		width : 400,
		resizable : false,
		modal : true,
		title : "新增",
		winMode : ChildDialogUtil.getWinMode(),
		close : function(event) {
			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL, {});
				return;
			}
			if (event.data.type == DialogUtil.EVENT_OK) {
				var result = getResult();
				ChildDialogUtil.doClose(DialogUtil.EVENT_OK, result);
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
				ChildDialogUtil.doClose(event.data.type, {});
			} else {
				ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
	
	//添加部门到下拉框
	dwr.engine.setAsync(false);
	AcademicService.findMapByPropertiesQuick(['academicID','academicName'],"1=1 order by academicID asc",false,function(data){
		for(var i=0;i<data.length;i++){
			$("#academic").append("<option value='"+data[i]['academicID']+"'>"+data[i]['academicName']+"</option>");
		}
		$("#academic").append("<option value=''>其它</option>");
		$("#academic").val("");
	});
	dwr.engine.setAsync(true);
	//添加专业到下拉框
	dwr.engine.setAsync(false);
	DisciplineService.findMapByPropertiesQuick(['disciplineID','disciplineName'],"1=1 order by disciplineID asc",false,function(data){
		for(var i=0;i<data.length;i++){
			$("#discipline").append("<option value='"+data[i]['disciplineID']+"'>"+data[i]['disciplineName']+"</option>");
		}
	});
	dwr.engine.setAsync(true);
	$("#academic").change(function() {
		var academic = $("#academic").val();
		loadMajors(academic);
	});	
});

//验证
userDialogValid.valid = function(){
	var personID = $.trim($("#personID").val());
	var name =  $.trim($("#name").val());
	var academic = $("#academic").val();
	var major = $("#discipline").val();
	if(personID == "") {
		jAlert("请输入职工号");
		return false;
	}else if(personID.length != 8) {
		jAlert("职工号必须为8位");
		return false;
	}else if(validPersonID(personID) == true) {
		jAlert("职工号已存在，请重新输入");
		return false;
	}else if(name == "") {
		jAlert("请输入姓名");
		return false;
	}else if(academic == "" && major != "") {
		jAlert("请选择部门");
		return false;
	}
}

//得到具体内容
function getResult() {
	var data = {};
	data.ZGH = $.trim($("#personID").val());
	data.name =  $.trim($("#name").val());
	data.password =  $.trim($("#personID").val());
	data.disabled = 1;
	if($("#academic").val() != "") {
		data.academicID = $("#academic").val() + "";
	}
	if($("#discipline").val() != "") {
		data.disciplineID = $("#discipline").val() + "";
	}
	return data;
}

//查看id号是否已经存在
function validPersonID(personID) {
	var isExist = false;
	dwr.engine.setAsync(false);
	OperatorService.findCountByProperties(["operatorID"], "ZGH='" + personID + "'", false, function(count) {
		if(count > 0) {
			isExist = true;
		}
	});
	dwr.engine.setAsync(true);
	return isExist;
}

//加载学院下拉框的内容
function loadSelectData(param) {
	var academicID = "";
	$("#personID").val(param.ZGH);
	$("#personID").attr("disabled", true);
	$("#name").val(param.XM);
	$("#name").attr("disabled", true);
	dwr.engine.setAsync(false);
	AcademicService.findMapByPropertiesQuick(["academicID", "academicName"], "academicCode='" + param.XYDM +"'", false, function(academics) {
		if(academics.length == 0) {
			$("#academic").val("");
		}else {
			var academicID = academics[0].academicID;
			$("#academic").val(academicID);
			loadMajors(academicID);
		}	
	});
	dwr.engine.setAsync(true);	
}

//加载专业下拉框的内容
function loadMajors(academicID) {
	var condition = "1=1";
	if(academicID != "")
		condition = "academicID='" + academicID + "'";
	$("#discipline").empty();
	dwr.engine.setAsync(false);
	DisciplineService.findMapByPropertiesQuick(["disciplineID", "disciplineName"], condition , true, function(data) {
		$("#discipline").append("<option value=''>无</option>");
		if(data.length != 0) {
			for(var i=0;i<data.length;i++){
				$("#discipline").append("<option value='"+data[i]['disciplineID']+"'>"+data[i]['disciplineName']+"</option>");
			}
		}	
	});
	dwr.engine.setAsync(true);
}