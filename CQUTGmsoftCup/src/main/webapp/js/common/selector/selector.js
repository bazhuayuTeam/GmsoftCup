;
var Selector = {};
//配置表格

// 表格的配置
Selector.gridSetting = null;	
// 表格对象
Selector.gridObj = null;
// 单选还是多选，默认多选，则下方出现已选择的区域
Selector.multiselect = true;
// 选择区域的标题
Selector.selectedAreaTitle = "";
// 是否全选
Selector.allSelect = false;
// 已经选择的id
Selector.selectedrowid = new Array();
// 表格id
Selector.tableId = "";
// 翻页id
Selector.pagerId = "";
// 显示卡片的id字段
Selector.idField = "";
// 显示卡片的名字字段
Selector.nameField = "";
// 显示选择器区域的id
Selector.showSelectorAreaId = "";
// 显示已选择区域的id
Selector.showSelectedAreaId = "showSelectedAreaForSelector";
// 显示区域的title
Selector.selectedAreaTitle = "";
// 是否初始化
Selector.isInitialization = false;

// 初始化选择器
Selector.initSelector = function(tableId, pagerId, gridSetting, idField, nameField, multiselect, showSelectorAreaId, selectedAreaTitle) {
	if(!tableId || !pagerId || !gridSetting || !idField || !nameField) {
		jAlert('参数错误！请至少传tableId, pagerId, gridSetting,idField, nameField五个参数',function(){},"错误");
		return ;
	}
	
	this.tableId = "#" +tableId;
	this.pagerId = "#" + pagerId;
	this.idField = idField;
	this.nameField = nameField;
	this.gridSetting = gridSetting;
	this.multiselect = multiselect;
	
	if(multiselect) {
		this.showSelectorAreaId = "#" + showSelectorAreaId;
		this.selectedAreaTitle = selectedAreaTitle;
	}
	
	this.addAttributeForGridSetting();
	this.isInitialization = true;	// 进行了初始化
	this.initJQgrid();
	this.addShowSelectedArea();
};

// 给gridSetting添加属性
Selector.addAttributeForGridSetting = function () {
	var thisObj = this;
	if(!this.gridSetting.onSelectRow)	// 添加点击某行的操作
		this.gridSetting.onSelectRow = function(rowid,status){
				if(status){
					thisObj.selectRd(rowid);
				}else{
					thisObj.unselectRd(rowid);
				}
		};
		
	if(!this.gridSetting.onSelectAll)	// 添加全选时的操作
		this.gridSetting.onSelectAll = function(aRowids,status){
			if(thisObj.allSelect){
				return;
			}
			if(status){
				thisObj.selectCurPageRd(aRowids);
			}else{
				thisObj.unselectCurPageRd(aRowids);
			}
		};
	
	this.gridSetting.multiselect = Selector.multiselect;
};

// 创建显示已选择的区域
Selector.addShowSelectedArea = function() {
	if(!this.isInitialization)	// 未初始化
		return;
	
	if(!this.multiselect)	// 单选不需要创建已显示区域
		return;
	
	// 最外层的DIV，用来确定显示区域的位置
	var oShowDiv = $(this.showSelectorAreaId);
	// 标题div块
	var oTitleDiv = this.getHtmlObjWithAttr("div", {
		"class" : "selector-title"
	});
	// 标题里的图片
	var oImg = this.getHtmlObjWithAttr("img", {
		src : "js/common/selector/img/rectangle.png",
	});
	// 标题的文字说明
	var oP = this.getHtmlObjWithAttr("p", {});
	oP.html(this.selectedAreaTitle);
	// 操作
	var oA = this.getHtmlObjWithAttr("a", {
	});
	oA.html("| 清空 |");
	
	
	var oFieldset = this.getHtmlObjWithAttr("fieldset", {
		"class" : "showSelectedFieldset",
	});
	
	
	var oLabel = this.getHtmlObjWithAttr("label", {
		id : "clearAll",
		style : "font-size:13px;cursor:pointer"
	});
	oLabel.click(this.deleteAll);
	
	var oDiv = this.getHtmlObjWithAttr("div", {
		id : this.showSelectedAreaId,
		title : this.selectedAreaTitle,
		style : "padding:0px;height:100%;overflow-x:hidden;overflow-y:auto;font-size:13px"
	});
	
	// 操作DOM对象
	// 添加标题以及下方区域
	oShowDiv.append(oTitleDiv);
	oShowDiv.append(oFieldset);
	// 标题div
	oTitleDiv.append(oImg);
	oTitleDiv.append(oP);
	oTitleDiv.append(oLabel);
	oLabel.append(oA);
	
	// 下方区域DIV
	oFieldset.append(oDiv);
	//oLabel.append(oA);
};

// 给元素添加属性
Selector.getHtmlObjWithAttr = function(tagName,attrJson) {
	if(!this.isInitialization)	// 未初始化
		return;
	
	var obj = $("<" + tagName + ">");

	for(var key in attrJson) {
		obj.attr(key, attrJson[key]);
	}
	
	return obj;
};

// 初始化grid
Selector.initJQgrid = function() {
	if(!this.isInitialization)	// 未初始化
		return;
	
	$(this.tableId).jqGrid(this.gridSetting);
	$(window).bind('resize', function() { 
	    $(this.tableId).setGridWidth($(this.pagerId).width()-2); 
	}).trigger('resize');
};



//加载已经选择的数据  ，这个数组的内容应与jqGrid一行数据的格式一样，至少在id字段与name字段名字一样
Selector.showExitData = function(exitDatas){
	if(!this.isInitialization)	// 未初始化
		return;
	
	for(var i in exitDatas) {
		if(this.isSelected(exitDatas[i][this.idField]) == false){
			var newRd =this.getFieldSetNodeStr(exitDatas[i][this.idField],exitDatas[i][this.nameField]);
			this.selectedrowid[this.selectedrowid.length] = exitDatas[i][this.idField];
			$("#" + this.showSelectedAreaId).html($("#" + this.showSelectedAreaId).html() + newRd);
		}
	}
	this.showNumberNow();
};

//选中某一行时，将材料添加到下方栏
Selector.selectRd = function(rowid){
	if(!this.isInitialization)	// 未初始化
		return;
	
	var recevier = jQuery(this.tableId).getRowData(rowid);
	if(this.isSelected(rowid) == false){
		var newRd = this.getFieldSetNodeStr(recevier[this.idField],recevier[this.nameField]);
		if(!this.multiselect){
			this.selectedrowid[0] = rowid;
		//	document.getElementById(").innerHTML = newRd;
		}else{
			this.selectedrowid[this.selectedrowid.length] = rowid;
			$("#" + this.showSelectedAreaId).html($("#" + this.showSelectedAreaId).html() + newRd);
		}
	}
	this.showNumberNow();
};

//取消选中某行时
Selector.unselectRd = function(rowid){
	if(!this.isInitialization)	// 未初始化
		return;
	
	var selectedDom = document.getElementById(this.showSelectedAreaId);
	var allFieldSet = selectedDom.childNodes;
	for(var i = 0, len = allFieldSet.length; i < len; i++) {
		if(allFieldSet[i].nodeType == 1 && rowid == allFieldSet[i].id) {
			allFieldSet[i].style.display = "none";
			allFieldSet[i].id = "discap";
		} 
	} 
	this.unSelectRdAry(rowid);
	$(this.tableId).jqGrid("resetSelection",rowid);
	this.showNumberNow();
};

//全选
Selector.selectCurPageRd = function(aRowids){
	if(!this.isInitialization)	// 未初始化
		return;
	
	var html = "";
	for(var index in aRowids){
		var rowid = aRowids[index];
		if(this.isSelected(rowid) == false){
			var recevier = jQuery(this.tableId).getRowData(rowid);
			var newRd =this.getFieldSetNodeStr(recevier[this.idField],recevier[this.nameField]);
			this.selectedrowid[this.selectedrowid.length] = rowid;
		//	document.getElementById("selectedRelatedDocument").innerHTML += newRd;
			$("#" + this.showSelectedAreaId).html($("#" + this.showSelectedAreaId).html() + newRd);
		}
	}
//	document.getElementById("selectedRelatedDocument").innerHTML  += html;
	$("#" + this.showSelectedAreaId).html($("#" + this.showSelectedAreaId).html() + html);
	this.showNumberNow();
};

//取消全选时
Selector.unselectCurPageRd = function(aRowids){
	if(!this.isInitialization)	// 未初始化
		return;
	
	for(var index in aRowids){
		var rowid = aRowids[index];
		if(this.isSelected(rowid)){
			this.unselectRd(rowid);	
		}
	}
	this.showNumberNow();
};

//去掉删除的材料
Selector.unSelectRdAry = function(rowid){
	if(!this.isInitialization)	// 未初始化
		return;
	
	for(var i in this.selectedrowid){
		if(this.selectedrowid[i] == rowid){
			this.selectedrowid.splice(i,1);
		}
	}
};

//判断是否已经选过
Selector.isSelected = function(rowid){
	if(!this.isInitialization)	// 未初始化
		return;
	
	var exist = false;
	for( var i in this.selectedrowid){
		if(this.selectedrowid[i] == rowid){
			exist = true;
			break;
		}
	}
	return exist;
};

//显示当前选择了多少
Selector.showNumberNow = function(){
	if(!this.isInitialization)	// 未初始化
		return;
	
	$("#countNum").text(this.selectedrowid.length);
};

function unSelectFromFieldsetForSelector(id) {
	Selector.unSelectFromFieldset(id);
}

//点击删除某一个材料时
Selector.unSelectFromFieldset = function(rowid){
	if(!this.isInitialization)	// 未初始化
		return;
	
	this.unselectRd(rowid);
	this.showNumberNow();
};

//点击清空按钮
Selector.deleteAll = function(){
	if(!Selector.isInitialization)	// 未初始化
		return;
	Selector.selectedrowid.length = 0;
    $(Selector.tableId).jqGrid("resetSelection");//表格中的设置选中的为空
	$("#" + Selector.showSelectedAreaId).html("");
	Selector.showNumberNow();
};

//卡片的显示格式
Selector.getFieldSetNodeStr = function(id,name){
	if(!this.isInitialization)	// 未初始化
		return;
	
	return "<fieldset class = \"card\" id='"+id+"' title='"+name+"' >" +
					"<div class=\"infoDiv\">"
			+name+"</div><div onclick=\"unSelectFromFieldsetForSelector('"+id+"');\" " +
					"title=\"取消\" class=\"removeDiv\">删除</div></fieldset>";
};

// 获取选中结果，返回jqGrid一行组成的数组
Selector.getResult = function() {
	if(!this.isInitialization)	// 未初始化
		return;
	
	var result = new Array();
	
	for(var i in this.selectedrowid) {
		result.push(jQuery(this.tableId).getRowData(this.selectedrowid[i]));
	}
	
	return result;
};
























