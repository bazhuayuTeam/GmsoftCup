var listLeftUL=$("#listLeft .columnList");
var listrightUL=$("#listRight .columnList");
var curRoleId = null,leftFlag = false,rightFlag = true,timmerId = null;
var roleRootNode = [ {
			roleCode : null,
			roleName : "全部角色",
			isParent : true
		} ];
var roleTreeSetting = {
	nameCol : "roleName",
	async : true,
	asyncUrl : "null",
	dwr : true,
	dwrFun : RoleService.findMapByProperties,
	needLink : true,
	properties : [ 'roleId', 'roleName' ],
	condition : '',
	sortField : '',
	order : '',
	treeNodeKey : 'roleId',
	callback : {
		click : function(event, treeId, treeNode) {
			curRoleId = treeNode['roleId'];
			if(curRoleId){
				showDeskItem();				
			}
		}
	},
	nameFormatter : function(name, node) {//处理显示数据，暂时不支持html
		return name;
	},
	loadConditionFun : function(node) {//加载时的判断条件，每次加载前会调用
		return null;
	},
	isLeaf : function(node) {//是否是叶节点，不写则所有的节点都为非叶节点
		return true;
	}
};

$(function() {
	var roleTreeObj = $("#roleTree").zTree(roleTreeSetting, roleRootNode);
	roleTreeObj.expandNode(roleRootNode[0], true, false);
	
	listLeftUL.bind('dblclick',function(e){
		handleEvent(e,"left");
	});
	listrightUL.bind('dblclick',function(e){
		handleEvent(e,"right");
	});
});

function closeMask(){
	if(leftFlag && rightFlag){
		$('#cover').unmask();
	}
}

function showDeskItem(){
	listLeftUL.empty();
	listrightUL.empty();
	leftFlag = false;
	rightFlag = false;
	$('#cover').mask('正在加载中，请耐心等候......');
	if(null != curRoleId){
		ColumnsService.findMapByPropertiesQuick(["columnId","columnName"],"showMenuPage=1 and columnId not in(select columnId from desktop where operatorId='"+curRoleId+"')", false,function(data){
			var length=data.length;
			var li="";
			if(length>0){
				for(var i=0;i<length;i++){
					li+="<option columnCode='"+data[i].columnId+"'>"+data[i].columnName+"</option>";
				}
			}
			listLeftUL.append(li);
			leftFlag = true;
			closeMask();
		});
		DesktopService.findMapByPropertiesQuick(["col_columnId","col_columnName"],"col.showMenuPage=1 and col.columnId in(select columnId from desktop where operatorId='"+curRoleId+"')", true,function(data){
			var length=data.length;
			var li="";
			if(length>0){
				for(var i=0;i<length;i++){
					li+="<option columnCode='"+data[i].col_columnId+"'>"+data[i].col_columnName+"</option>";
				}
			}
			listrightUL.append(li);
			rightFlag = true;
			closeMask();
			}); 
			}
}

//绑定双击事件
function handleEvent(e,method){
	var e = e || window.event,tag = e.target || e.srcElement,
		option = tag.tagName.toLowerCase() != "option" ? $(tag).children("option:selected") : $(tag);
	method == "left" ? listrightUL.append(option.clone()) : listLeftUL.append(option.clone());
	option.remove();
}
//增加
function add(){
	var option=listLeftUL.children("option:selected");
	listrightUL.append(option.clone());
	option.remove();
}
//删除
function deleteFun(){
	var option=listrightUL.children("option:selected");
	listLeftUL.append(option.clone());
	option.remove();
}
//增加全部或删除全部
function addAllOrdeleteAll(method){
	if(method=="add"){
		listrightUL.append(listLeftUL.html());
		listLeftUL.empty();
	}else{
		listLeftUL.append(listrightUL.html());
		listrightUL.empty()
	}
}
//向上or向下
function upOrDown(method){
	var option=listrightUL.children("option:selected");
	var index=option.index();
	if(method=="up"){
	if(index>0){
		option.clone().insertBefore(option.prev());
		option.remove();
		listrightUL.children("option").each(function(i){if(i==index-1){$(this).attr("selected","selected")}});
	}
	}else if(index<listrightUL.children("option").length-1){
		option.clone().insertAfter(option.next());
		option.remove();
		listrightUL.children("option").each(function(i){if(i==index+1){$(this).attr("selected","selected")}});
	}
}
//置顶或置尾
function toTopOrToBottom(method){
	var option=listrightUL.children("option:selected");
	if(method=="top"){
		listrightUL.prepend(option.clone());
		option.remove();
		listrightUL.children("option:first").attr("selected","selected");
	}else{
		listrightUL.append(option.clone());
		option.remove();
		listrightUL.children("option:last").attr("selected","selected");
	}
}
//保存个人桌面设置
function savePersonDeskSetting(){
	var options = listrightUL.find('option'),settingIDs = [];
	if(curRoleId){
		options.each(function(){
			settingIDs.push($(this).attr("columnCode"));
		});
		DesktopService.savePersonDeskSetting(curRoleId,settingIDs,function(msg){
			if(msg == true){
				mWin.ok("保存成功!");
			}
			else{
				alert("保存失败!");
			}
		});
	}else{
		mWin.alert('请选择具体的角色!');
	}
}