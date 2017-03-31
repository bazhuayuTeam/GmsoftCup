var schoolYearID = "";
var data = ChildDialogUtil.getExchangeData();

$(document).ready(function() {
	//创建控件
	$("#dialog-modal").createDialog( {
		height : 250,
		width : 400,
		resizable : false,
		modal : true,
		title : "编辑",
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
	initSelect();
	initData();
	//getPhoto(data.ZGH);
});


function initSelect() {
	//添加部门到下拉框
	dwr.engine.setAsync(false);
	AcademicService.findMapByPropertiesQuick(['academicID','academicName'],"1=1 order by academicID asc",false,function(data){
		for(var i=0;i<data.length;i++){
			$("#academic").append("<option value='"+data[i]['academicID']+"'>"+data[i]['academicName']+"</option>");
		}
		$("#academic").append("<option value=''>其它</option>");
	});
	
	//添加专业到下拉框
	$("#discipline").append("<option value=''>无</option>");
	DisciplineService.findMapByPropertiesQuick(['disciplineID','disciplineName'],"1=1 order by disciplineID asc",false,function(data){
		for(var i=0;i<data.length;i++){
			$("#discipline").append("<option value='"+data[i]['disciplineID']+"'>"+data[i]['disciplineName']+"</option>");
		}
	});
	dwr.engine.setAsync(true);
}

function initData() {
	$("#ZGH").val(data.ZGH);
	$("#name").val(data.name);
	var majorID=(data.majorID==null||data.majorID=="null")?"":data.majorID;
	$("#discipline").val(majorID);
	var academicID=(data.academicID==null||data.academicID=="null")?"":data.academicID;
	$("#academic").val(academicID);
	var systemFileID = getPhoto(data.ZGH);
	$("#preview").attr("src", "common/image.action?imgId="+systemFileID+"");
	
	$("#academic").change(function() {
		loadMajors($("#academic").val());
	});
}

//验证
userDialogValid.valid = function(){
	var ZGH = $.trim($("#ZGH").val());
	var name =  $.trim($("#name").val());
	var academic = $("#academic").val();
	var major = $("#discipline").val();
	if(ZGH == "") {
		jAlert("请输入职工号");
		return false;
	}else if(ZGH.length != 8) {
		jAlert("职工号必须为8位");
		return false;
	}else if(validPersonID(ZGH) == true) {
		jAlert("职工号已存在，请重新输入");
		return false;
	}else if(name == "") {
		jAlert("请输入姓名");
		return false;
	}else if(academic == "" && major!= "") {
		jAlert("请选择部门");
		return false;
	}
}

//得到具体内容
function getResult() {
	var data = {};
	data.ZGH = $.trim($("#ZGH").val());
	data.name =  $.trim($("#name").val());
	if($("#academic").val() != "") {
		data.academicID = $("#academic").val() + "";
	}
	if($("#discipline").val() != "") {
		data.disciplineID = $("#discipline").val() + "";
	}
	return data;
}

//查看id号是否已经存在
function validPersonID(ZGH) {
	var isExist = false;
	dwr.engine.setAsync(false);
	OperatorService.findMapByPropertiesQuick(["ZGH"], "ZGH='" + ZGH + "'", false, function(backData) {
		if(backData.length>0&&backData[0].ZGH != data.ZGH) {
			isExist = true;
		}
	});
	dwr.engine.setAsync(true);
	return isExist;
}

function loadMajors(academicID) {
	var condition = "1=1";
	if(academicID != "")
		condition = "academicID='" + academicID + "'";
	$("#discipline").empty();
	DisciplineService.findMapByPropertiesQuick(["disciplineID", "disciplineName"], condition , true, function(data) {
		$("#discipline").append("<option value=''>无</option>");
		if(data.length != 0) {
			for(var i=0;i<data.length;i++){
				$("#discipline").append("<option value='"+data[i]['disciplineID']+"'>"+data[i]['disciplineName']+"</option>");
			}
		}	
	});
}
