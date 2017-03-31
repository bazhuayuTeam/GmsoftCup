var isRoleAssign = 1;//用于人员选择器
var judge = null;
var file=null;
var fileID=null;
var semesterID=null;
var team=null;
var num=0;
var type=null;

function exitClick() {
	$("#picDiv").hide();
}

function init() {
	if (UserPackage.PageCall.beginReady) { //调用服务	
		UserPackage.PageCall.beginReady();
	}
	Page.Data.exchangeData = ChildDialogUtil.getExchangeData();//获得父窗口中传入值
	judge = Page.Data.exchangeData.judge;
	if(Page.Data.exchangeData.showSrc) {
		showSrc = Page.Data.exchangeData.showSrc;
	}
	if(ChildDialogUtil.getExchangeData){
		var data = ChildDialogUtil.getExchangeData();
		if(data.team){                    //课程代码  
			team=data.team;
		}
		if(data.type){
			type=data.type;
		}
	}
	if(type=="1"){
		$("#warn").text("文件名为：创意决赛-‘项目编号’.doc，创意决赛-‘项目编号’.ppt");
	}
	//创建控件
	$("#dialog-modal").createDialog({
				height : 250,
				width : 365,
				resizable : false,
				modal : true,
				winMode : ChildDialogUtil.getWinMode(),
				close : function(event) {
					if (!event.data) {
						ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
						return;
					}
					if (event.data.type == DialogUtil.EVENT_OK) {
						var result={};	
						var fileId=file.getUploadedFilesId();
						if(fileId!=null){
							if(fileId.length>0){
								result.fileID=fileId[0];
							}							
						}
						
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
					} else if (event.data.type == DialogUtil.EVENT_ERROR) {
						return;
					} else {
						ChildDialogUtil.doClose(event.data.type, {});
					}
				}
			});

	if (UserPackage.PageCall.beginInitComponents) {
		UserPackage.PageCall.beginInitComponents();
	}
	//控件初始化							
	if (Page.initComponent) {
		Page.initComponent();
	}
	//检查所有调用是否返回并开始加载数据
	Page.Util.checkCallFinish();
	if(Page.Data.exchangeData.showFlag){
		$(".ui-button-text-only").eq(0).hide();
	}
}


userDialogValid.valid = function(){
	//检测上传附件的个数，只允许上传一个附件
	fileID=file.getUploadedFilesId();
	
	if(fileID!=null){
		if(fileID.length>1){
			 alert("只能上传一份文件");
			 return false;		
		}else{
		     return true;
		}	
	}
	else{
		alert("请选择你要上传的文件");
		return false;
	}
}

$(document).ready(function() {
	dwr.engine.setAsync(false);
	init();
	file = $('#upload_info').fileUpload({});
	loadExitFile();
	dwr.engine.setAsync(true);
});

function getUploadedFiles() {
		return file.getUploadedFiles();
}

function loadExitFile(){
	ProjectService.findMapByPropertiesQuick(['fileId'],"teamId='"+team+"' and type='"+type+"'",false,function(data){
		if(data.length>0){
			file.loadUploadedFiles(data[0].fileId,false);
		}
	})
}

