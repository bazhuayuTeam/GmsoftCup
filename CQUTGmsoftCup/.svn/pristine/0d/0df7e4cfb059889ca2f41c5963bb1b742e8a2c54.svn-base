var PageData = {};
PageData.mode = 1;
PageData.old = null;
PageData.roleName = null;
if(ChildDialogUtil.getExchangeData){
	var data = ChildDialogUtil.getExchangeData();
	if(data.mode&&data.roleId){
		PageData.mode = data.mode;
		PageData.id = data.roleId;
	}
}
dwr.engine.setAsync(false);
if(PageData.mode == 2 || PageData.mode ==3){
	RoleService.findMapByPropertiesQuick(['roleName'],"roleID='"+PageData.id+"'",false,function(data){
		if(data&&data.length>0){
			PageData.roleName = data[0].roleName;
		}
	});
}
dwr.engine.setAsync(true);


userDialogValid.valid = function(){
	return valid(setData());
};
$(document).ready(function() {
		if(PageData.mode == 2 || PageData.mode ==3){
			$('#field_roleName').val(PageData.roleName);
		}
		$("#dialog-modal").createDialog( {
			height : 200,
			width : 400,
			resizable : false,
			modal : true,
			close : function(event) {
				if (!event.data) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
					return;
				}
				if (event.data.type == DialogUtil.EVENT_OK) {
					if(PageData.mode == 1||PageData.mode == 3){ 
						var result = setData();
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
					}
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
	var value = $.trim($('#field_roleName').val());
	
	if(value == ""){
		jAlert("请输入角色名称!");
		return false;
	}
};

function setData(){
	var dto = {};
	dto.roleName = $.trim($('#field_roleName').val());
	//dto.roleCode = $.trim($('#remark').val());
	return dto;
}
function valid(dto){
	try{
		if(dto.roleName == '')
			//jAlert("请输入角色名称!");
			return false;
	}catch(e){
		return false;
	}
	return true;
}