
var pageData={
	meId:null,
	teamId:null,
	gameId:null
}

$(function(data){
	
	if(ChildDialogUtil.getExchangeData){
	var data = ChildDialogUtil.getExchangeData();
	if(data.meId){
		pageData.meId = data.meId;
	}
	if(data.teamId){
		pageData.teamId = data.teamId;
	}
	if(data.gameId){
		pageData.gameId=data.gameId;
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
							type:"1"
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
	else if(pageData.meId==id){
		alert("不能添加自己为组员")
		$("#forid").focus();
		return false;
	}
	var right=true;
	dwr.engine.setAsync(false);
	UserService.findUser(id,"0",function(data){
			if(data.length===0){
				right=false;
				alert("该账户不存在或账户角色为教师");
			}
		})
		dwr.engine.setAsync(true);
	
	dwr.engine.setAsync(false);
	UserService.findUserNew(id,pageData.gameId,pageData.teamId,function(data){
			if(data.length>0){
				right=false;
				alert("该学生已参加本阶段比赛的其他队伍或已存在团队中");
				/*
				 * $('.inputEdit').css('border','1px solid red');
				$("#warn").text("");
				$("#warn").show();*/
			}
		})
		dwr.engine.setAsync(true);
	return right;
	
};