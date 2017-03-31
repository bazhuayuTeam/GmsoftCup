var event_flag = DialogUtil.EVENT_CANCEL;
var This_codetablecode = null;
var This_parentcode = null;
var This_level0 = null;
var This_codetype = null;
var This_title="";

Page.initComponent = function() {//初始化控件数据    
	loadData();//加载数据
	Page.Util.callOver = true;
};

function loadData(){//加载数据
	if (Page.CustomFlag.disableInitComponent) {
		return;
	}
	This_codetype = Page.Data.exchangeData.codeType;
	This_level0 = Page.Data.exchangeData.level0==null?0:Page.Data.exchangeData.level0;
	if (Page.Data.exchangeData.codeTableCode == null) {/*没有codeTableCode表示新增*/
		if (Page.Data.exchangeData.parentCode != null) {
			This_parentcode = Page.Data.exchangeData.parentCode;
			//jAlert("parentCode="+This_parentcode);
			CodeTableService.findMapByProperties( [ 'codetablecode','codetype' ],"parentcode='" + This_parentcode + "'", "codetablecode","desc", false, function(data) {
				if (data == "") {//没有孩子
					This_codetablecode = This_parentcode + "0001";
				} else {//有孩子
					var temp = (data[0].codetablecode).replace(This_parentcode, "");
					temp = temp - (-1);/*+1*/
					temp = FormatNumber(temp);
					This_codetablecode = This_parentcode + "" + temp;
				}
				$("#field_codetype").val(This_codetype).attr("disabled", "disabled");//有codeType则不允许输入
			});
		} else if (Page.Data.exchangeData.parentCode == null) {//没有父亲
			This_level0 = 0;
			This_parentcode = Page.Data.exchangeData.parentCode;
		}
	} else {/*有codeTableCode则表示编辑*/
		This_codetablecode = Page.Data.exchangeData.codeTableCode;
		CodeTableService.findMapByPropertiesQuick( [ 'codeType','codeTableName'], "codeTableCode='" + This_codetablecode + "'", false, function(data) {
			$("#field_codetablename").val(data[0].codeTableName);
			$("#field_codetype").val(data[0].codeType);
			$("#field_codetype").attr("disabled", "true");
			//$('#field_icon').val(data[0].icon);
			//showImg();//显示图片
		});
	}
}

function showImg() {
	var file = $('#imgShow');
	file.after(file.clone());
	file.remove();
	//下载图片并显示
	if ($('#field_icon').val() != '') {
		SystemFileService.downloadFile($('#field_icon').val(), function(data) {
			if (data) {
				$('#imgShow').attr('src', data);
			}
		});
	}
}

//文件复位
function resetImgForm() {
	var file = $('#imgFile');
	$("#imgShow").attr("src", "");
	$("#field_icon").val("");//将图片置为空
	file.after(file.clone().val(""));
	file.remove();
	showImg();
}

//显示选择的图片
function changeImg(obj) {
	$('#imgShow').attr('src', '');
	var filepath = $(obj).val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
		jAlert("图片限于bmp,png,gif,jpeg,jpg格式");
		resetImgForm();
		return false;
	}
	if ($('#imgFile').get(0).value != '') {//有新图片
		dwr.engine.setAsync(false);
			SystemFileService.uploadFile($('#imgFile').get(0),"session",function(data) {
					if (data) {
						$('#field_icon').val(data.id);
						SystemFileService.downloadFile($('#field_icon').val(), function(data1) {
							if (data1) {
								$('#imgShow').attr('src', data1);
							}
						});
					}else{
						jAlert("图片上传失败!");
						$("#imgShow").attr("src","");
					}
				});
		dwr.engine.setAsync(true);
	} else {//无新图片
		obj.headPhoto0 = $("#field_icon").val();
	}
}

userDialogValid.valid = function(){
	var cn=$("#field_codetablename");
	var ct=$("#field_codetype");
	if($.trim(cn.val())==""){
		jAlert("'码表名称'不能为空!");
		cn.focus();
		return false;
	}
	if($.trim(ct.val())==""){
		jAlert("'码表类型'不能为空!");
		ct.focus();
		return false;
	}
	if(Page.Data.exchangeData.codeTableCode==null&&Page.Data.exchangeData.parentCode==null){
		var temp = $.trim(ct.val());
		var right=true;
	dwr.engine.setAsync(false);
		CodeTableService.findCountByProperties(["codeType"],"codeType ='"+temp+"'",false,function(data){
			if(data>0){
				right=false;
				$("#field_codetype").select();
				jAlert("该码表类型已经存在，请重新输入!");
			}
		});
	dwr.engine.setAsync(true);
	}
	return right;
};

$(document).ready(function() {
	if (UserPackage.PageCall.beginReady) { //调用服务	
			UserPackage.PageCall.beginReady();
		}
	Page.Data.exchangeData = ChildDialogUtil.getExchangeData();//获得父窗口中传入值
	if (Page.Data.exchangeData.codeTableCode == null){
		This_title = "新增码表";
	}else{
		if(Page.Data.exchangeData.showFlag==true){
			This_title="查看码表";
			$("[id^='field_'][save='true']").attr("disabled","disabled");
			$("#imgFile").css("display","none");
			$("#resetButton").css("display","none");
		}else{
			This_title="编辑码表";
		}
	}
	//创建控件
	$("#dialog-modal").createDialog({
						height : 250,
						width : 350,
						resizable : false,
						modal : true,
						title : This_title,
						winMode : ChildDialogUtil.getWinMode(),
						close : function(event) {
							if (!event.data) {
								ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
								return;
							}

							if (event.data.type == DialogUtil.EVENT_OK) {
								var result = {};
								if (Page.Data.exchangeData.codeTableCode == null) {//新增
									if(This_parentcode==null){
										This_codetablecode=$("#field_codetype").val();
									}
									result.codeType = '' + $("#field_codetype").val();
									result.codeTableCode = This_codetablecode;
									result.codeTableName = $("#field_codetablename").val();
									result.parentCode = This_parentcode;
									result.level0 = This_level0;
									result.hasChild=0;//新增的没有孩子
								} else {//更新
									result.codeType = $("#field_codetype").val();
									result.codeTableName = $("#field_codetablename").val();
								}
								//printObject(result);
								ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
							} else if (event.data.type == DialogUtil.EVENT_ERROR) {
								return;
							} else {
								ChildDialogUtil.doClose(event.data.type, {});
							}
						}
					});

			if (UserPackage.PageCall.beginInitComponents) {
				UserPackage.PageCall.beginInitComponents();
			}
			//控件初始化							
			if (Page.initComponent) {
				Page.initComponent();
			}
			//检查所有调用是否返回并开始加载数据
			Page.Util.checkCallFinish();
			if(Page.Data.exchangeData.showFlag){
				$(".ui-button-text-only").eq(0).hide();
			}
});

function FormatNumber(number) {
	var temp = "";
	number = parseInt(number);
	if (number > 0 && number < 10) {
		temp = number.toString();
		temp = "000" + temp;
		return temp;
	} else if (number >= 10 && number < 100) {
		temp = number.toString();
		temp = "00" + temp;
		return temp;
	} else if (number >= 100 && number < 1000) {
		temp = number.toString();
		temp = "0" + temp;
		return temp;
	} else {
		temp = number.toString();
		return temp;
	}
};
