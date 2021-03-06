;


$(function() {
	managerPageData.roleId=managerPageData.roleId;
	initManagerPageData();
	setUserName();
	loadFirstLevelMenu();
	
	addEvent();

	modifyToNiceScroll("#menu");
});

function addEvent() {
	$("#modifyPassword").click(function() {
		changePassWord();
	});
	$("#exit").click(function() {
		operatorExit();
	});
}

function initManagerPageData() {
	managerPageData.firstLevelBgColor = "#466785";
	managerPageData.secondLevelBgColor = "#d4b598";
	managerPageData.menuItemClassName = "menuItemDiv";
	managerPageData.secondLevelMenuItemClassName = "secondLevelMenuItemDiv";
	managerPageData.defaultWidth = "182px";
	managerPageData.animateSpeed = 900;
	managerPageData.isDeploying =false;
	managerPageData.desktopUrl = "module/desktop/desktop.jsp";
	managerPageData.niceScrollSpeed = 20;
}

// 修改为新滚动条
function modifyToNiceScroll(selector) {
	$(selector).each(function(index, element) {
		$(element).niceScroll({scrollspeed : managerPageData.niceScrollSpeed});
	});
}

// 设置头像
function setHeadPic(imgId) {
	$("#headPic").attr("src", "common/image.action?imgId="+ imgId  +"");
	//$("#headPic").form.submit();
	/*$.ajax({
		async:true,// 设置为同步
		cache:false,
		type:"POST",
		dataType:"json",
		url:"common/image.action",
		data : {imgId : imgId},
		error:function(e){
			alert("请求失败！");
			console.info(e);
		},
		success:function(data){
		//	$("#headPic").attr("src", "data:image/gif;base64," + data);
			console.info(data);
		}
	});
	*/
}

// 设置用户名
function setUserName() {
	$("#userName").html(managerPageData.operatorName);
}

// 加载一级菜单
function loadFirstLevelMenu() {
	ModuleService.findFirstLevelMenu(managerPageData.roleId, 
			function(data) {
				if(data) {
					$("#menu").empty();
					for(var i in data) 
						$("#menu").append(
								getMenuItem(data[i], managerPageData.menuItemClassName, 
										true, ".innerMenuDiv > .menuItemDiv", managerPageData.firstLevelBgColor));
				}
	});
}

// 获取菜单项
function getMenuItem(menuItemData,className, hasImg, clickEventSelector, clickEventBgColor) {
	var child="0";
	if(menuItemData.hasChild){
		child="1";
		
	}
	var oMenuItemDiv = getHtmlTag("div", 
			{
				"class": className,
				"moduleCode" : menuItemData.moduleCode,
				"hasChild" : child,
				"url" : menuItemData.url,
				"title" : menuItemData.moduleName,
				"parent" : menuItemData.parent
			});
	var oSpan = getHtmlTag("span", {});
	oSpan.html(menuItemData.moduleName);
	
	if(hasImg) {
		oMenuItemDiv.append(getHtmlTag("img", 
				{
					src : "images/manager/menu-head-pic.png",
					"class" : "menuIconImg"
				}));
	}
	oMenuItemDiv.append(oSpan);
	if(hasImg) {
		oMenuItemDiv.append(getHtmlTag("img", 
				{
					src : "images/manager/menuPitchOnIcon.png",
					"class" : "menuPitchOnIconImg"
				}));
	}
	
	addClickForMenuItem(oMenuItemDiv, clickEventSelector,clickEventBgColor);
	return oMenuItemDiv;
}

// 根据tagname获取一个tag
function getHtmlTag(tagName, attrJson) {
	//var oTag = $("<" + tagName + "><" + tagName + "/>" );
	var oTag = $("<" + tagName + "/>");
	for(var key in attrJson)
		oTag.attr(key, attrJson[key]);
	
	return oTag;
}

// 增加点击事件
function addClickForMenuItem(oMenuItemDiv, selector, bgcolor) {
	oMenuItemDiv.click(function() {
		if(managerPageData.isDeploying)	// 有二级菜单正在展开
			return;
		
		var thisObj = $(this);
		clearStyle(selector);
		
		thisObj.attr("style","background-color:" + bgcolor + ";");
		
		if(thisObj.attr("deploy")) {	// 如果已展开则隐藏
			hideSecondLevelMenu(thisObj.attr("moduleCode"));
			thisObj.removeAttr("deploy");
			showFirstLevelMenuPic(thisObj);
			return;
		}
		
		// 隐藏所有的二级菜单并移除所有一级菜单的deploy属性
		if(thisObj.attr("hasChild") == "1")	{// 有二级的情况
			hideAllSecondLevelMenu();
			removeAllFirstLevelMenuItemAttr("deploy");
		}
		if(!parseFloat(thisObj.attr("hasChild"))) {
			skipLinkToIframe(thisObj.attr("url"));
			if(thisObj.attr("parent")){	// 说明是二级菜单
				hideSecondLevelMenu(thisObj.attr("parent"));
				removeAllFirstLevelMenuItemAttr("deploy");
				$("." + managerPageData.menuItemClassName).each(function(index, element) {
					if($(this).attr("style"))
						showFirstLevelMenuPic($(this));
				});
			}
		}else if(!thisObj.attr("hasSecond")){
			getSecondLevelMenu(thisObj.attr("moduleCode"));
			thisObj.attr("hasSecond", "1");
			thisObj.attr("deploy", true);
		}else if(thisObj.attr("hasSecond")) {
			showSecondLevelMenu(thisObj.attr("moduleCode"));
			thisObj.attr("deploy", true);
		}
		
	});
}

// 显示一级菜单的图片
function showFirstLevelMenuPic(obj) {
	obj.find(".menuPitchOnIconImg").each(function() {
		$(this).attr("style", "visibility:visible;");
	});
}

// 移除所有一级菜单的属性
function removeAllFirstLevelMenuItemAttr(attr) {
	$("." + managerPageData.menuItemClassName).each(function(index, element) {
		$(this).removeAttr(attr);
	});
}

// 隐藏所有的二级菜单
function hideAllSecondLevelMenu() {
	$(".secondLevelMenuBgDiv").each(function(index, element) {
		$(this).css("width","0px");
		$(this).find("div").css("width","0px");
	});
}

// 修改某个二级菜单样式
function modifySecondLevelMenu(id, cssJson, childDivWidth) {
	var obj = $("#" + id);
	animateForSecondLevelMenuDiv(obj, cssJson);
	obj.find("div").css("width",childDivWidth);
}

// 展开某个二级菜单
function showSecondLevelMenu(id) {
	modifySecondLevelMenu(id, {width : managerPageData.defaultWidth}, "");
}

// 隐藏某个二级菜单
function hideSecondLevelMenu(id) {
	modifySecondLevelMenu(id, {width : "0px"}, "0px");
}

// 清除style
function clearStyle(selector) {
	$(selector).each(function(index,element) {
		$(element).removeAttr("style");
		$(element).find(".menuPitchOnIconImg").each(function() {
			$(this).removeAttr("style");
		});
	});
}

// 展开
function animateForSecondLevelMenuDiv(oDiv, cssJson) {
	managerPageData.isDeploying = true;
	oDiv.animate(cssJson, managerPageData.animateSpeed
			,function() {
		managerPageData.isDeploying = false;
	});
}

// 生成二级菜单
function getSecondLevelMenu(parentCode) {
	ModuleService.findSecondLevelMenu(managerPageData.roleId, parentCode, 
			function(data) {
				if(data) {
					var oSecondLevelMenuDiv = createSecondLevelMenu(parentCode, data);
					$("#body").append(oSecondLevelMenuDiv);
					animateForSecondLevelMenuDiv(oSecondLevelMenuDiv, {width : managerPageData.defaultWidth});
				}
	});
}

function createSecondLevelMenu(parentCode, secondLevelMenuDatas) {
	var oSecondLevelMenuBgDiv = getHtmlTag("div", {"id" : parentCode, "class" : "secondLevelMenuBgDiv"});
	oSecondLevelMenuBgDiv.append(getHtmlTag("div", {"class" : "topDiv"}));
	
	var oOuterSecondLevelMenuDiv = getHtmlTag("div", {"class" : "outerSecondLevelMenuDiv"});
	oSecondLevelMenuBgDiv.append(oOuterSecondLevelMenuDiv);
	
	var oSecondLevelMenuDiv = getHtmlTag("div", {"class" : "secondLevelMenuDiv"});
	
	oOuterSecondLevelMenuDiv.append(oSecondLevelMenuDiv);
	
	for(var i in secondLevelMenuDatas)
		oSecondLevelMenuDiv.append(
				getMenuItem(secondLevelMenuDatas[i], 
						managerPageData.secondLevelMenuItemClassName,
						false, ".secondLevelMenuDiv > .secondLevelMenuItemDiv", managerPageData.secondLevelBgColor));
	
	oSecondLevelMenuBgDiv.append(getHtmlTag("div", {"class" : "bottomDiv"}));
	oSecondLevelMenuDiv.niceScroll({scrollspeed : managerPageData.niceScrollSpeed});
	return oSecondLevelMenuBgDiv;
}

// 跳转链接
function skipLinkToIframe(url) {
	$("#showContent").attr("src",url);
	
}

// 修改密码
function changePassWord() {
	DialogUtil.openFloatWindow("module/manager/changePassWord.jsp", null, {
				EVENT_OK : function(param) {
					OperatorService.changePassWord(
							managerPageData.operatorId,param.oldPSW, param.newPSW, function(data) {
								if (data == 0) {
									jAlert("修改失败,原密码不正确！",function(){},"错误");
								} else if (data == 1) {
									mWin.ok("密码修改成功！");
								} else if (data == -1) {
									jAlert("系统错误，修改失败！",function(){},"错误");
								} else {

								}
							});
				}
			});
}

// 退出
function operatorExit() {
	OperatorService.operatorExit(function(data){
	     location.href = "login.jsp";
	});
}











