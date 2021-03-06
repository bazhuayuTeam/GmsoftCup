
var pageData={
	curPage:-1,
	limits:-1
}
var userId="1";

$(function(){
	initData();
});

function initData(){
	var html="";
	$("#center").empty();
	dwr.engine.setAsync(false);

	TeamService.getTeamData(operatorId,pageData.curPage,pageData.limits,function(data){
		html=data;
	})
	dwr.engine.setAsync(true);
	$("#center").append(html);
	
	
}

function addTeacher(id){
	DialogUtil.openFloatWindow("module/personalCenter/competition/TeacherAdd.jsp",{teamId:id},{EVENT_OK:function(param){
		param.teamId=id;
   		 	 TeamService.updateEntity(param,"teamId='"+id+"'",function(data){
				if(data){
  	   				mWin.ok('添加成功！');
  	   				initData();
				}else{
					alert('添加失败！');
				}
  	   		});
		
   	 }});
}

function addStudent(id,gameId){
	CrewService.getNumber(id,function(data){
		if(data){
			getNumber(id,gameId);
		}
		else{
			alert("团队成员已有5人，不能再添加");
		}
	})
}

function getNumber(id,gameId){
	DialogUtil.openFloatWindow("module/personalCenter/competition/StudentAdd.jsp",{teamId:id,meId:operatorId,gameId:gameId},{EVENT_OK:function(param){
			param.teamId=id;
	   		 	 CrewService.saveData(param,gameId,function(data){
					if(data){
	  	   				mWin.ok('添加成功！');
	  	   				initData();
					}else{
						alert('添加失败！');
					}
	  	   		});
	   	 }});
}

function slideToggle(data){
	$("#" + data).slideToggle("slow");

	if($('.team-info img').attr("src") == "images/uo.png"){
		$('.team-info img').attr("src","images/dowm.png")
	}else{
		$('.team-info img').attr("src","images/uo.png")
	}
}

function enroll(id){
	if(confirm("报名之后,团队信息将无法修改,是否确定报名?")){
		TeamService.updateData(id,function(data){
			if(data){
				warnInfo();
				initData();
			}
			else{
				alert("提交信息失败");
			}
		})
	}
}

function warnInfo(){
	DialogUtil.openFloatWindow("module/personalCenter/competition/warnInfo.jsp",{},{EVENT_OK:function(param){
		
		
   	 }});
}

function uploadFile(id,type){
	DialogUtil.openFloatWindow("module/personalCenter/competition/leadInXls.jsp",{team:id,type:type},{EVENT_OK:function(param){
		ProjectService.saveData(id,param.fileID,type,function(data){
			if(data){
				mWin.ok("上传成功!");
			}
			else{
				alert("上传失败!");
			}
		})
		
   	 }});
}

function deleteId(id){
	if(confirm("确定要删除该数据吗?")){
	CrewService.deleteById(id,function(data){
		if(data){
			mWin.ok("删除成功");
			initData();
		}
		else{
			alert("删除失败");
		}
	})
	}
}

function deleteGroup(id){
	if(confirm("您确定删除该组？")){
		TeamService.deleteGroup(id,function(data){
			if(data){
				mWin.ok("删除成功");
				initData();
			}
			else{
				alert("删除失败");
			}
		})
	}
}