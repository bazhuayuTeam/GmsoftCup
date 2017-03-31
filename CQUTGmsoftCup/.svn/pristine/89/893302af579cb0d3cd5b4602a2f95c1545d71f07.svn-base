var event_flag = DialogUtil.EVENT_CANCEL;
var This_modulecode = null;
var This_parent = null;
var This_level = null;
var This_moduletype = null;
var This_title=null;
var pageData={
	showOrder:null
};

Page.initComponent = function() {//初始化控件数据    	
	if (Page.CustomFlag.disableInitComponent) {
		return;
	}
	This_moduletype=Page.Data.exchangeData.moduletype;
	
	DropDownBox.loadForCodeTable('#field_moduletype', "parentCode='ModuleType'", true);
	if (This_moduletype!=null) {
		$('#field_moduletype').val(This_moduletype);
		$('#field_moduletype').attr('disabled','true');
	}
	if (Page.Data.exchangeData.parent != null) {
		This_parent = Page.Data.exchangeData.parent;
		ModuleService.findMapByPropertiesQuick(['level0','modulecode'],"parent='" + This_parent + "'",true, function(data) {
			if (data == '') {
				ModuleService.getModule(['level0','modulecode'], "modulecode='" + This_parent + "'", true, function(data2) {
						This_level = data2.level - (-1);
						This_modulecode = data2.modulecode+'0001';
					});
			} else {
				This_level = data[0].level;
				var temp=data[0].modulecode;
					for (var i=0; i<data.length;i++) {
						if (temp<data[i].modulecode) {
						temp = data[i].modulecode;
					} 
				}
	 			This_modulecode = '000' + (temp - (-1));
			}

		});
	} else if (Page.Data.exchangeData.parent == null){
		This_level = 0;
		ModuleService.findMapByPropertiesQuick(['modulecode'],"parent is null order by moduleCode",true,function(data){
			var temp=data[0].modulecode;
		 	for (var i=1; i<data.length;i++) {
				if (data[i-1].modulecode<data[i].modulecode) {
					temp = data[i].modulecode;
				}
			}
			This_modulecode = '000' + (temp - (-1));
		});
	}

	Page.Util.callOver = true;
};

Page.loadData = function() {//加载页面数据
	if (Page.CustomFlag.disableLoadData)
		return;
	if (Page.Data.exchangeData.modulecode != null) {
		ModuleService.getModule(['modulename','url','icon','parent','level0','modulecode','moduletype'],"modulecode='" + Page.Data.exchangeData.modulecode + "'", true, function(data) {
			if (!data) {
				jAlert('数据不存在');
				return;
			}
			var modules = {};
			if (Page.Data.exchangeData.showFlag) {//查看模式
					modules.readOnly = true;
					$('#imgForm').remove();
			}
			FormUtil.createComponent(["field_modulename","field_url","field_moduletype"]);
			FormUtil.bindData(data);
			$("#field_showMenuPage").val(data.showMenuPage + "");
			if (data.icon && data.icon != null) {
				$('#field_icon').val(data.icon);
				showImg();//显示图片
			}

			//加载数据完成
			if (UserPackage.PageCall.endLoadData) {
				UserPackage.PageCall.endLoadData(data);
			}
		});
	} else {
		//加载数据完成
		FormUtil.createComponent(["field_modulename","field_url","field_moduletype"]);
		FormUtil.bindData( {
			modulename:'',
			url:'',
			moduletype:''
		});

		if (UserPackage.PageCall.endLoadData) {
			UserPackage.PageCall.endLoadData(data);
		}
	}
};

$(document).ready(function() {
	//验证输入框是否为空
	userDialogValid.valid = function(){
	var name = $.trim($('#field_modulename').val());
	var url  = $.trim($('#field_url').val());
	
	if(name == "" && url != ""){
		jAlert("请输入模块名称!");
		return false;
		}
	
	if(url == "" && name != ""){
		jAlert("请输入路径!");
		return false;
		}
	
	if(name == "" && url == ""){
		jAlert("请输入模块名称和路径!");
		return false;
	}
};
	
	//调用服务		
	if (UserPackage.PageCall.beginReady) {
		UserPackage.PageCall.beginReady();
	}
	Page.Data.exchangeData = ChildDialogUtil.getExchangeData();//获得父窗口中传入值
	
	if(Page.Data.exchangeData.showOrder){
		pageData.showOrder = Page.Data.exchangeData.showOrder;
	}
	//jAlert(Page.Data.exchangeData.modulecode);
	if(Page.Data.exchangeData.modulecode!=null){//编辑
		if (Page.Data.exchangeData.showFlag) {//查看模式
			$("[id^='field_'][save='true']").attr("disabled","disabled");
			$('#starURL,#starModuleType').css('display','none');
			This_title="查看模块信息";
		} else {
			This_title="编辑模块";
		}
	} else {
		This_title="新增模块";
	}
	/*if (Page.CustomFlag.disableLoadData)
		return;*/
	if (Page.Data.exchangeData.modulecode != null) {
		ModuleService.getModule(['modulename','url','icon','parent','level0','modulecode','moduletype'],"modulecode='" + Page.Data.exchangeData.modulecode + "'", true, function(data) {
			if (!data) {
				jAlert('数据不存在');
				return;
			}
			var modules = {};
			if (Page.Data.exchangeData.showFlag) {//查看模式
					modules.readOnly = true;
					$('#imgForm').remove();
			}
			FormUtil.createComponent(["field_modulename","field_url","field_moduletype"]);
			FormUtil.bindData(data);
			$("#field_showMenuPage").val(data.showMenuPage + "");
			if (data.icon && data.icon != null) {
				$('#field_icon').val(data.icon);
				showImg();//显示图片
			}

			//加载数据完成
			if (UserPackage.PageCall.endLoadData) {
				UserPackage.PageCall.endLoadData(data);
			}
		});
	} else {
		//加载数据完成
		FormUtil.createComponent(["field_modulename","field_url","field_moduletype"]);
		FormUtil.bindData( {
			modulename:'',
			url:'',
			moduletype:''
		});

		if (UserPackage.PageCall.endLoadData) {
			UserPackage.PageCall.endLoadData(data);
		}
	}
	//创建控件
	$("#dialog-modal").createDialog({
		height : 310,
		width : 400,
		resizable : false,
		modal : true,
		title : This_title,
		winMode : ChildDialogUtil.getWinMode(),
		
		close : function(event) {

			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
				
			if (event.data.type == DialogUtil.EVENT_OK
					&& (Page.Data.exchangeData.showFlag == undefined || !Page.Data.exchangeData.showFlag)) {
	
				var result = {};
				if (Page.Data.exchangeData.modulecode == null) {
					result.moduletype=''+$("#field_moduletype").val();
					result.modulecode=This_modulecode;
					result.haschild=false;
					if(This_modulecode.length == 12){
						result.isEndOfModuleLevel=true;
					} else {
						result.isEndOfModuleLevel=false;
					}
					if(This_modulecode.length == 4){
						result.isfolder=true;
					} else {
						result.isfolder=false;
					}
					result.level0=This_modulecode.length/4-1;
					result.modulename=$("#field_modulename").val();
					result.parent=This_parent;
					result.showMenuPage = $("#field_showMenuPage").val()=='true' ? true : false;
					result.url=$("#field_url").val();
					result.showOrder=parseInt(pageData.showOrder);
				} else {
					result.moduletype=""+$("#field_moduletype").val();
					result.modulename=$("#field_modulename").val();
					result.url=$("#field_url").val();
					result.showMenuPage = $("#field_showMenuPage").val()=='true' ? true : false;
				}
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					return;
			} else {
					ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
	
	if(Page.Data.exchangeData.modulecode!=null && Page.Data.exchangeData.showFlag) {
		$("button:contains('确定')").remove();
	}
	if (UserPackage.PageCall.beginInitComponents) {
		UserPackage.PageCall.beginInitComponents();
	}
	//控件初始化							
	if (Page.initComponent) {
		Page.initComponent();
	}
	//检查所有调用是否返回并开始加载数据
	Page.Util.checkCallFinish();
});