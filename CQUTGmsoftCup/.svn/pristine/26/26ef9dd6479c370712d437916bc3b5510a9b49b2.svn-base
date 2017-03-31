
var pageData={
	teamId:null
}
$(function(data){
		if(ChildDialogUtil.getExchangeData){
	var data = ChildDialogUtil.getExchangeData();
	
	if(data.teamId){
		pageData.teamId = data.teamId;
	}
	}
	
	//创建控件
	$("#dialog-modal").createDialog({
		height : 310,
		width : 400,
		resizable : false,
		modal : true,
		winMode : ChildDialogUtil.getWinMode(),
		
		close : function(event) {

			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
				
			if (event.data.type == DialogUtil.EVENT_OK) {
					
						var result={
							userId:$("#forid").val(),
							type:"2"
						};
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
					
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					return;
			} else {
					ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
	
	$('.inputEdit').click(function(){
		$('.inputEdit').css('border','1px solid #cccccc');
		$('#warn').hide();
	})
});

userDialogValid.valid = function(){
	var id= $("#forid").val();
	if(id==""||id=="请输入账号"){
		alert("请输入账号");
		$("#forid").focus();
		return false;
	}
	var right=true;
	dwr.engine.setAsync(false);
	UserService.findUser(id,"1",function(data){
			if(data.length===0){
				right=false;
				alert("该账户不存在或账户角色为学生");
			}
		})
	dwr.engine.setAsync(true);
	
	dwr.engine.setAsync(false);
	UserService.findUserTeacher(id,pageData.teamId,function(data){
			if(data.length>0){
				right=false;
				alert("该账户已存在团队中");
			}
		})
	dwr.engine.setAsync(true);
	return right;
	
};