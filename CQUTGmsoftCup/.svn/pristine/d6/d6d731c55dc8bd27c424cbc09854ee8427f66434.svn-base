var pageData = {
	messageType:'graduationProjectPub',
	graduationProjectInfoId: '',
	needSysMessage : true,
	needShortMessage : false,
	needVoidMessage : false,
	strategySetting : {strategyType:1},
	curSelectedOperId:0,
	sendWay:1
};
var operChooser = {
	teacherNameArray:[],
	teacherIdArray:[]
};
var content = {};

//得到从子页面传递过来的参数
if(ChildDialogUtil.getExchangeData){
	var data = ChildDialogUtil.getExchangeData();
	if(data.graduationProjectInfoId){
		pageData.graduationProjectInfoId = data.graduationProjectInfoId;
	}
	if(data.teacherIdArr){
		pageData.teacherIdArr = data.teacherIdArr;
		operChooser.teacherIdArray = pageData.teacherIdArr.split(","); 
		
	}
	if(data.teacherNameArr){
		pageData.teacherNameArr = data.teacherNameArr;
	}
	if(data.propositionId){
		pageData.propositionId = data.propositionId;
	}
	if(data.title){
		pageData.title = data.title;
	}
	if(data.mode){
		pageData.mode = data.mode;
	}
}

$(function(){
	//创建弹窗
	$("#dialog-modal").createDialog({
		height : 450,
		width : 600,
		resizable : false,
		modal : true,
		winMode : ChildDialogUtil.getWinMode(),
		buttons:{
			"发送":function(event){
				$("body > div:first").mask("发送中...");
				event.data = {};
				event.data.type=DialogUtil.EVENT_OK;
				content[pageData.curSelectedOperId] =  KE.html('Editor');
				pageData.teacherIdArr = operChooser.teacherIdArray.join(",");
				if(operChooser.teacherIdArray.length == 0) {
					$("body > div:first").unmask();
					alert("请选择接收人员");
					return;
				}
				if(($("#xt_open_de").attr("checked") != "checked") && ($("#sm_open_de").attr("checked") != "checked")){
					$("body > div:first").unmask();
					alert("请选择发送方式!");
					return;
				}
				if(pageData.sendWay == 2){//多人短信发送
					for(var tId in content){
						content[tId] = content[pageData.curSelectedOperId];
					}
				}
				if(content[pageData.curSelectedOperId].replace(/<[^>]+>/g,"").length > 80){
					alert("您输入的内容已经超过80个字符");
				}
				MessageService.sendSelfMessage(
					pageData, pageData.strategySetting,operatorId,content,
					 function(data) {
						$("body > div:first").unmask();
					 	ChildDialogUtil.doClose(DialogUtil.EVENT_OK,data);
					 }
				);
			},
			"关闭":function(event){
				event.data={};
				event.data.type=DialogUtil.EVENT_CANCEL;
				$( this ).modalDialog( "close" ,event);
				window.close();
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
		},
		close : function(event){
		   	if (!event.data) {
			  DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			  return;
		    }
		    if (event.data.type == DialogUtil.EVENT_ERROR){
		        return;
			} else {
				ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
	document.getElementById("addreceiver").onclick = showAddReceiver;
	iniKindEditor();//初始化kindEditor
	initPageData();
});

//初始化页面的数据
function initPageData() {
	//如果是命题申报通过或则不通过:3
	dwr.engine.setAsync(true);
	if(pageData.mode == 3) {
		TeacherGroupDetailService.getMyPropositionMessage(data.propositionId, function(data){
			if(data) {
				content = data[0].content;
				console.dir(content);
				KE.html('Editor', content[data[1].fac_teacherID]);
				var nameArray = [];
				nameArray.push(data[1].fac_name);
				
				if(!pageData.teacherIdArr) {
					pageData.teacherIdArr = [];
				}
				pageData.teacherIdArr.push(data[1].fac_teacherID);
				
				operChooser.teacherIdArray = pageData.teacherIdArr;
				operChooser.teacherNameArray = nameArray;
				pageData.teacherIdArr = pageData.teacherIdArr.join(",");
				initRecieverNames();
			}
		}); 
	}
	//命题未提交时发送消息
	else if(pageData.mode == 1) {
		TeacherGroupDetailService.getMessgageContentByMe(
			pageData.teacherIdArr,pageData.teacherNameArr, function(data) {
				if(data) {
					KE.html('Editor', data[pageData.teacherIdArr]);
					var nameArray = [];
					nameArray.push(pageData.teacherNameArr);
					var teacherIdArr = pageData.teacherIdArr;
					
					if(pageData.teacherIdArr) {
						pageData.teacherIdArr = [];
					}
					pageData.teacherIdArr.push(teacherIdArr);
					operChooser.teacherIdArray = pageData.teacherIdArr;
					operChooser.teacherNameArray = nameArray;
					initRecieverNames();
				}
			});
	}
	//如果是毕业信息任务管理直接发送消息
	else {
		//得到默认的短信内容和短信接收人
		TeacherGroupDetailService.getMessgageContent(
			pageData.graduationProjectInfoId, function(data) {
				if(data && data.length > 1) {
					content = data[0].content;
					KE.html('Editor', content[data[1].fac_teacherID]);
					var nameArray = [];
					for(var i = 1, len = data.length; i < len; i++) {
						nameArray.push(data[i].fac_name);
						
						if(!pageData.teacherIdArr) {
							pageData.teacherIdArr = [];
						}
						pageData.teacherIdArr.push(data[i].fac_teacherID);
					}
					operChooser.teacherIdArray = pageData.teacherIdArr;
					operChooser.teacherNameArray = nameArray;
					pageData.teacherIdArr = pageData.teacherIdArr.join(",");
					initRecieverNames();
				}
		});
	}
}

//初始化接收人员
function initRecieverNames() {
	if(operChooser.teacherNameArray.length > 0){
		$("#addreceiver").text("修改");
	}
	var htmlT = "<tr height='20px'>";
	for(var i = 0, len = operChooser.teacherNameArray.length; i < len; i++) {
		if(i == 0){
			htmlT += "<td><a class = 'teacherNameHref' href='javascript:void(0);' id = '" 
				+ operChooser.teacherIdArray[i] + "' style='color:red;'" 
				+ " onclick='changeInnerHTML(\"" + operChooser.teacherIdArray[i] 
				+ "\")' title = '点击修改发送给该教师信息内容'>" + 
				operChooser.teacherNameArray[i] + "</a></td>";
		}else{
			htmlT += "<td><a class = 'teacherNameHref' href='javascript:void(0);' id = '" 
				+ operChooser.teacherIdArray[i] 
				+ "' onclick='changeInnerHTML(\"" + operChooser.teacherIdArray[i] 
				+ "\")' title = '点击修改发送给该教师信息内容'>" + 
				operChooser.teacherNameArray[i] + "</a></td>";
		}
		if((i+1)%9 == 0 && (i+1)<operChooser.teacherNameArray.length){
			htmlT += "</tr><tr height='20px'>";
		}
	}
	htmlT += "</tr>";
	//document.getElementById("receiverTable").innerHTML = htmlT;
	$("#receiverTable").html(htmlT);
	pageData.curSelectedOperId = operChooser.teacherIdArray[0];//初始化修改
}

//点击发送教师信息的时候设置内容
function changeInnerHTML(operId) {
	//将其他href的标签颜色还原为黑色,然后将自身染黑
	if(pageData.sendWay == 1){//是否是单人
		$(".teacherNameHref").css("color","black");
		$(".teacherNameHref[id='" + operId + "']").css("color","red");
	
		if(!pageData.curSelectedOperId) {
			pageData.curSelectedOperId = operId;
		}
		content[pageData.curSelectedOperId] = KE.html('Editor');
		if(!content[operId]) {
			KE.html('Editor', '');
		}
		else {
			KE.html('Editor', content[operId]);
		}
		pageData.curSelectedOperId = operId;
	}
}

//初始化富文本编辑器
function iniKindEditor() {
	var KESetting = {
		id : 'Editor',//指定应用kindEditor的TextArea的ID
		imageUploadJson : '../../jsp/upload_json.jsp',
		resizeMode : 2,//允许更改高度和宽度
		fileManagerJson : '../../jsp/file_manager_json.jsp',
		allowFileManager : true,//允许访问服务器
		newlineTag : 'p',//shift+enter换行  	newlineTag : 'p',//回车换行
		pluginsPath : '<%=path%>/plugins/kindeditor/plugins/', //注意这里必须加一个<%=path%>指定绝对路径，否则表情无法正常显示 
		afterChange : function() {
			checkIsOutBound();
        }  
	};
	KE.show(KESetting);
}

//点击系统消息的事件
function xtChangeDe() {
	if($("#xt_open_de").attr("checked") == "checked"){
		pageData.needSysMessage = true;
	} else {
		pageData.needSysMessage = false;
	}
}

//点击短信消息的事件
function smChangeDe() {
	if($("#sm_open_de").attr("checked") == "checked"){
		pageData.needShortMessage = true;
		checkIsOutBound();
	} else {
		pageData.needShortMessage = false;
		document.getElementById("messageAlertDiv").style.display='none';
	}
}

//判断是否超出70字
function checkIsOutBound() {
	if($("#sm_open_de").attr("checked") == "checked") {
		var curValue = KE.text('Editor');
		if(curValue.length > 70) {
			showMessageAlertDiv();
		}
		else {
			document.getElementById("messageAlertDiv").style.display='none';
		}
	}
}

//显示超出字符长度信息提示
function showMessageAlertDiv() {
	document.getElementById("messageAlertDiv").style.display='block';
}

//打开短信发送的详细页面
function smShowDe(){
	var param = pageData.strategySetting;
	param.model = 2;
	param.sendWay = pageData.sendWay;
	DialogUtil.openModalWindow(base + "plugins/sendMessageUtil/sendMessageSetting.jsp", param,
		{
			EVENT_OK : function(param){
				judgeStrategy(param);
			}
		},500,390);
}

//判断发送策略是 单人  还是   全体 
function judgeStrategy(strategySetting){
	pageData.sendWay = strategySetting.sendWay;
	if(pageData.sendWay == 2){
		$(".teacherNameHref").css("color","black");
	}else if(pageData.sendWay == 1){
		$("a[id='"+pageData.curSelectedOperId+"']").css("color","red");
	}
	strategySetting.sendWay = null;
	pageData.strategySetting = strategySetting;
}

//打开人员选择器
function showAddReceiver() {
	var selectedAry = {};
	selectedAry.name = operChooser.teacherNameArray.concat();
	selectedAry.id = operChooser.teacherIdArray.concat();
	DialogUtil.openModalWindow(base + "plugins/sendMessage/operatorChooser.jsp", selectedAry, {
		EVENT_OK : function(param) {
			if(param.name == '') {
				operChooser.teacherNameArray = [];
				operChooser.teacherIdArray = [];
				KE.html('Editor', '');
				document.getElementById("receiverPersonNames").innerHTML = "";
				return;
			}
			operChooser.teacherNameArray = param.name;
			operChooser.teacherIdArray = param.id;
			var newContent = {};
			for (var i = 0;i<operChooser.teacherIdArray.length;i++) {
				if(content[operChooser.teacherIdArray[i]] && content[operChooser.teacherIdArray[i]] != '') {
					newContent[operChooser.teacherIdArray[i]] = content[operChooser.teacherIdArray[i]];
				}
				else {
					newContent[operChooser.teacherIdArray[i]] = '';
				}
			}
			content = newContent;
			if(operChooser.teacherIdArray.length > 0) {
				KE.html('Editor', content[operChooser.teacherIdArray[0]]);
				pageData.curSelectedOperId = 0;
				initRecieverNames();
				$(".teacherNameHref:first").click();
			}
		}
	},830,480);
}