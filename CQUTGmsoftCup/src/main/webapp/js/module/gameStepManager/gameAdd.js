
var pageData={
	id:null
};
var main_params={
		file:false
};

$(document).ready(function() {
	eventBinding();
	main_params.file=$("#fileUpload").fileUpload({
		uploadComplete:function (){
			var files=main_params.file.getUploadedFiles();
			var fileName=main_params.file.getUploadedFilesName();
			if(files&&files.length>0){
				var file=files[files.length-1],
					fileSrc=encodeURI(file.savePath),
					fileID=file.id;
				$(".file-list").remove();
				$("#fileID").attr("data-src",fileSrc);
				addFile(fileSrc,fileName[fileName.length-1],fileID);
			}
		},
		hideUploadFile:true
	});
	if(ChildDialogUtil.getExchangeData){
		 data = ChildDialogUtil.getExchangeData();
		 getProcess();
		 getStandardVersion(data);
	}
	FormUtil.createComponent( [ "gameTime" ],"timepicker");
	FormUtil.createComponent( [ "checkStartTime" ],"timepicker");
	FormUtil.createComponent( [ "checkEndTime" ],"timepicker");
	FormUtil.createComponent( [ "startTime" ],"timepicker");
	FormUtil.createComponent( [ "endTime" ],"timepicker");
	//创建控件
	$("#dialog-modal").createDialog({
		height :500,
		width : 400,
		resizable : false,
		modal : true,
		close : function(event) {
			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
				
			if (event.data.type == DialogUtil.EVENT_OK) {
					var result = {
						processID:$("#processID").val(),
						gameTime:DateUtil.DateToMS($("#gameTime").val()),
						standardVersionID:$("#standardVersionName").val(),
						checkStartTime:DateUtil.DateToMS($("#checkStartTime").val())||"",
						checkEndTime:DateUtil.DateToMS($("#checkEndTime").val())||"",
						works:$("#works").val(),
						startTime:DateUtil.DateToMS($("#startTime").val())||"",
						endTime:DateUtil.DateToMS($("#endTime").val())||"",
						fileID:$("#fileID").attr("data-src")||""
					};
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					return;
			} else {
					ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
});

function addFile(src,name,id){
	$("#fileID").parent().append('<div class="file-list"><a style="text-decoration:underline;color:blue;" href="'+src+'" download="">'+decodeURI(name)+'</a><a style="margin-left:5px;color:red;" data-id="'+id+'" onclick="removeFile(this)">删除</button></a>');
}
function getProcess(){
	CodeTableService.findMapByPropertiesQuick(["codeTableCode","codeTableName"],"parentCode='task'",false,function (data){
		var processHTML="";
		for(var i=0,len=data.length;i<len;i++){
			processHTML+=("<option value='"+data[i].codeTableCode+"'>"+data[i].codeTableName+"</option>");
		}
		$("#processID").append(processHTML);
	});
}

function getStandardVersion(id){
	StandardVersionService.findMapByPropertiesQuick(["standardVersionID","standardVersionName"],"",false,function (data){
		var standardVersionHTML="";
		for(var i=0,len=data.length;i<len;i++){
			standardVersionHTML+=("<option value='"+data[i].standardVersionID+"'>"+data[i].standardVersionName+"</option>");
		}
		$("#standardVersionName").append(standardVersionHTML);
	});
}

//验证输入框是否为空
userDialogValid.valid = function(){
	var name=$("#name").val();
	var works=$("#works").val();
	var standardVersionID=$("#standardVersionName").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	if(name==""){
		jAlert("请输入大赛名称");
		$("#name").focus();
		return false;
	}else if(standardVersionID==""){
		jAlert("请选择评审标准");
		$("#standardVersionName").focus();
		return false;
	}
	else if(works==1&&startTime==""){
		jAlert("请输入报名开始时间");
		$("#startTime").focus();
		return false;
	}
	else if(works==1&&endTime==""){
		jAlert("请输入报名截止时间");
		$("#endTime").focus();
		return false;
	}
};
function removeFile(el){
	var that=$(el);
	var r=confirm("确定要删除文件");
	if(that.attr("data-id")!="0"){
		r&&main_params.file.emptyFile(that.attr("data-id"));
	}
	r&&that.parent().remove();
}
function eventBinding(){
	$("#standardVersionName").bind("change",function (){
		var that=$(this);
		if(that.val()!=""){
			$(".forStandard").removeClass("hidden");
		}else{
			$(".forStandard").addClass("hidden");
		}
	});
	$("#works").bind("change",function (){
		var that=$(this);
		if(that.val()==1){
			$(".forStage").removeClass("hidden");
		}else{
			$(".forStage").addClass("hidden");
		}
	});
	$("#fileID").bind("click",function (){
		$(".MultiFile-applied").click();
	});
}
