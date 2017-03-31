var data = ChildDialogUtil.getExchangeData();
userDialogValid.valid = function(){
	return valid(setData());
}
$(document).ready(function() {
		load();
		
		$("#dialog-modal").createDialog( {
			height : 300,
			width : 380,
			resizable : false,
			modal : true,
			close : function(event) {
				if (!event.data) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
					return;
				}
				if (event.data.type == DialogUtil.EVENT_OK) {
					    var result = getResult();   
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
					
				}
				else if (event.data.type == DialogUtil.EVENT_CANCEL) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
				}
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
		});

	});

//验证输入框是否为空
userDialogValid.valid = function(){
	var disciplineName = $.trim($('#disciplineName').val());
	if(disciplineName == ""){
		jAlert("请输入专业名称!");
		return false;
	}
}

function load(){
	$("#academicName").val(data.academicName);
	$('#disciplineName').val(data.disciplineName);
	
}
//获取最后结果
function getResult(){
	var disciplineName = $.trim($('#disciplineName').val());

	var data={};
	data.disciplineName=disciplineName;
	return data;
}