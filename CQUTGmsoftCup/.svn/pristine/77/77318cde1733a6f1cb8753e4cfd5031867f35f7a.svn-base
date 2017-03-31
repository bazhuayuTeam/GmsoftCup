$(function(){
    $(':input').labelauty();
});

$(function(){
	var html="";
	dwr.engine.setAsync(false);
	GameStepService.findPropertiesQuick(["gameStepID","mo_codeTableName"],"1=1",true,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i]["gameStepID"]+"'>"+data[i]["mo_codeTableName"]+"</option>";
		}
	});
	dwr.engine.setAsync(true);
	$("#classification").append(html);
	
	$('li .input[name="teamName"]').click(function(){
		$('li .input[name="teamName"]').css('border','1px solid #cccccc');
		$('#warn-name').html("");
	});
	
	$('li .input[name="teamTitle"]').click(function(){
		$('li .input[name="teamTitle"]').css('border','1px solid #cccccc');
		$('#warn-title').html("");
	});
	
	$(".center").css("height",$("form").outerHeight() + 100);
});

function enroll(){
	var mode = $('.dowebok input[name="mode"]:checked').val();
	var classification = $('#classification').find("option:selected").val();
	var teamName = $('input[name="teamName"]').val();
	var teamTitle = $('input[name="teamTitle"]').val();
	
	if(teamName == null || teamName == ""){
		$('li .input[name="teamName"]').css('border','1px solid red');
		$('#warn-name').html("请输入团队名称");
		return;
	}
	
	if(teamTitle == null || teamTitle == ""){
		$('li .input[name="teamTitle"]').css('border','1px solid red');
		$('#warn-title').html("请输入参赛名称");
		return;
	}
	var para = {
		teamName :teamName,
		title : teamTitle,
		gameStepID : classification,
		mode :mode,
		captianId:operatorId,
		gameStyle : mode
	};
	
	var arr = {
		teamName : teamName,
		mode : mode,
		classification :classification 
	};
	TeamService.getCountByProperties(teamName,mode,classification,function(data){
		if(data== 0){
			 TeamService.saveTeam(JSON.stringify(para),function(data){
				 if(data)
					window.parent.childLink("enroll");
				 else
					alert("您已加入当前比赛，请选择其他参赛方式！");
			});
		}else if(data == -1){
			alert("只有学生能参赛！");
		}else{
			alert("您输入的团队名称已存在，请重新输入!");
		}
	});
}

function quit(){
	window.parent.childLink("enrollPass");
}