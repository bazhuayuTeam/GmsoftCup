;
var pageData={
	level0:0,
	targetSystemID:null,
	standardVersionID:null,
	relatedDocuments:null,
	isLastTarget:null, // 保存当前target的是否是最末级
};

var targetCode = null;	// 保存的指标编码
var isModify = null ; 	// 判断是修改还是查看

//入口
$(document).ready(function(){   
	targetCode =  ChildDialogUtil.getExchangeData().targetCode;
	isModify = ChildDialogUtil.getExchangeData().isModify;
	
	if (targetCode) { // 说明targetCode有值，则是编辑或者详情
			$("#dialog-modal").attr("title", "编辑指标");
	}else
		targetCode = null;
	
	
	if (isModify == "false") {
		$("#dialog-modal").attr("title", "指标详情");
	}

	$("#dialog-modal").createDialog( {
			height : 450,
			width :520,
			resizable : false,
			modal : true,
			close : function(event) {
				if (!event.data) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
					return;
				}
				if (event.data.type == DialogUtil.EVENT_OK) {
				   var result =getResult();
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
				}
				else if (event.data.type == DialogUtil.EVENT_CANCEL) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
				}
				if(DialogUtil)
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
		});
	
	
	if (isModify == "false") {
		$(".ui-dialog-buttonpane button").eq(0).hide();
		forbid();
	}

	if (targetCode) { // 说明targetCode有值，则是编辑或者详情
			bindDataForInputs(getTarget());
	}
});

//验证
userDialogValid.valid = function(){
	var targetName=$.trim($("#targetName").val());
//	var targetType=$("#targetType").val();
//	var score=$.trim($("#score").val());	
	if(targetName==""||targetName==null){
		jAlert("请输入指标名称");
		return false;
	}
	
	if(!$("#score").verificationNonNegativeInteger()) {
		jAlert("请输入大于0的分值");
		return false;
	}
	/*
	*/
	return true;
};


function getResult(){
	var data={};
	if(targetCode) {
		data.targetCode = targetCode;
	}
	data.targetName=$("#targetName").val();
	data.targetScore =parseInt($("#score").val());
	data.targetExplain=$("#explain").val();

	if(pageData.isLastTarget)
		data.isLastTarget = pageData.isLastTarget;
	
	return data;
}

// 从后台获取数据
function getTarget(){
	var target ={};
	dwr.engine.setAsync(false);
	TargetService.findTarget(targetCode,function(data){
		if(!data)
			jAlert("获取数据失败");
		else{
				target = data;
				pageData.isLastTarget = data.isLastTarget;
				pageData.relatedDocuments = data.relatedDocuments;
				pageData.standardVersionID = data.standardVersionID;
		}
	} );
	
	dwr.engine.setAsync(true);
	return target;
}

// 给输入框绑定值
function bindDataForInputs(data){
	$("#targetName").val(data.targetName);
	$("#targetType").val(data.targetType);
	$("#score").val(data.targetScore);
	if(data.relatedDoc_relatedDocumentName)
		$("#referenceData").val(data.relatedDoc_relatedDocumentName);
	$("#explain").val(data.targetExplain);
}

// 禁用输入框
function forbid(){
	$("#targetName").attr("disabled",true);
	$("#targetType").attr("disabled",true);
	$("#score").attr("disabled",true);
	$("#explain").attr("disabled",true);
	$("#referenceDataBtn").hide();
	$("#clearDataBtn").hide();
}

// 清空选择的模板名称
function clearReferenceData(){
	$("#referenceData").val("");
	pageData.relatedDocuments=null;
}
// 修改指标分值行的显示
function modifyTargetScoreStatus(isHide) {
	$(".hideScore").each(function(index,element){
		if(isHide)
			$(element).hide();
		else
			$(element).show();
	});
}













