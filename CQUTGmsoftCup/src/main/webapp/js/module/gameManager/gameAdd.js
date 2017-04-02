var main_params={
    panelType:0
};
$(document).ready(function() {
    eventBinding();
    getGameType();
    fileUploadInit({
    	templateContainer:"fine-uploader-gallery",
    	templateID:"qq-template",
    	url:"",
    	method:"post",
    	isAutoUpload:true
    });
	$('#name').Watermark("请输入大赛名称", "#8f8f8f");
	$("#year").Watermark("例如:2016", "#8f8f8f");
	
	
	FormUtil.createComponent( [ "endTime" ],"timepicker");
	FormUtil.createComponent( [ "startTime" ],"timepicker");

	//创建控件
	$("#dialog-modal").createDialog({
		height : 600,
		width : 400,
		modal : true,
		close : function(event) {
			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
				
			if (event.data.type == DialogUtil.EVENT_OK) {
					var result = {
						gameName:$("#name").val(),
						year:$("#year").val(),
						startTime:$("#startTime").val(),
						endTime:$("#endTime").val()
					};
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					return;
			} else {
					ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
});

/**
 * 操作选择器
 * @param {int} type - 选择器类型,0:主办方，1:承办方，2：宣传封面
 * @param {object} [el] - 点击的输入框对象,用于赋值选择面板
 */
function hostUnitSelector(type,el){
    main_params.panelType=type;
    var commonEventBinding=function (cb){
        $(".panel-content").delegate(".remove-item","click",function (){
            $(this).parent("li").remove();
        });
        $(".addNew").bind("click",function (){
            var list= $(this).prev("ul"),
                num=list.children().length+1;
            var itemHTML='<li><label>单位'+num+':</label><input class="hostUnit" placeholder="请输入单位名称"><button class="remove-item" title="删除">X</button></li>';
            list.append(itemHTML);
        });
        $(".panel-confirm").bind("click",function (){
            cb&&cb();
            $(this).parents(".panel-container").addClass("hidden");
        });
        $(".panel-cancel").bind("click",function (){
            $(this).parents(".panel-container").addClass("hidden");
        });
    }
    if(type===2){

    } else{
        var hostUnitPanel = $(".panel-container");
        if(hostUnitPanel.length>0){
            hostUnitPanel.removeClass("hidden");
            hostUnitPanel.find("ul").html('<li><label>单位1:</label><input class="hostUnit" placeholder="请输入单位名称"><button class="remove-item" title="删除">X</button></li>');
        }else{
            $("#dialog-modal").prepend($("#hostUnitTemplate").html());
            commonEventBinding(function (){
                var inputs=$(".hostUnit"),
                    hostUnits="";
                inputs.each(function (){
                    var value=$(this).val();
                    if(value!=""){
                        hostUnits+=(value+",");
                    }
                });
                main_params.panelType===0?$("#hostUnitName").val(hostUnits.slice(0,hostUnits.length-1)):
                    $("#secondUnitName").val(hostUnits.slice(0,hostUnits.length-1));
            });
        }
        //赋值单位选择面板
        var initValue=$(el).val();
        if(initValue){
            var lists=hostUnitPanel.find("ul"),
                values=initValue.split(",");
            setTimeout(function (){
                var valuesHTML="";
                for(var i=0,len=values.length;i<len;i++){
                	var itemHTML='<li><label>单位'+(i+1)+':</label><input class="hostUnit" value="'+values[i]+'"><button class="remove-item" title="删除">X</button></li>';
                    valuesHTML+=itemHTML;
                }
                lists.html(valuesHTML);
            },100);
        }
    }
}

function getGameType(){
	CodeTableService.findMapByPropertiesQuick(["codeTableCode","codeTableName"],"parentCode='GameType'",false,function (data){
		var typeSelector=$("#gameType"),
			optionHTML="";
		for(var i=0,len=data.length;i<len;i++){
			var item=data[i];
			optionHTML+='<option value="'+item.codeTableCode+'">'+item.codeTableName+'</option>'
		}
		typeSelector.append(optionHTML);
	});
}

//验证输入框是否为空
userDialogValid.valid = function(){
	var name=$("#name").val();
	var year=$("#year").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	
	var startTime1 = DateUtil.DateToMS($.trim($('#startTime').val()));
	var endTime1 = DateUtil.DateToMS($.trim($('#endTime').val()));
	if(name==""||name=="请输入大赛名称"){
		jAlert("请输入大赛名称");
		$("#name").focus();
		return false;
	}
	else if(year==""||year=="例如:2016"){
		jAlert("请输入年份");
		$("#year").focus();
		return false;
	}
	else if(startTime==""||startTime=="请输入报名开始时间"){
		jAlert("请输入报名开始时间");
		$("#startTime").focus();
		return false;
	}
	else if(endTime==""||endTime=="请输入报名截止时间"){
		jAlert("请输入报名截止时间");
		$("#endTime").focus();
		return false;
	}
	else if(startTime1>endTime1){
		jAlert("结束时间不能小于开始时间!");
		return false;
	}
}

function eventBinding(){
    $("#hostUnitName").bind("click",function (){
        hostUnitSelector(0,this);
    });
    $("#secondUnitName").bind("click",function (){
        hostUnitSelector(1,this);
    });
}

function fileUploadInit(options){
    fileUploadConfig(options);
}

function fileUploadConfig(options){
    var templateContent=document.getElementById(options.templateID);
    var manualUploader = new qq.FineUploader({
        element: document.getElementById(options.templateContainer),
        template: options.templateID,
        request: {
            endpoint: options.url,
            method: options.method
        },
        thumbnails: {
            placeholders: {
                waitingPath: 'js/fine-uploader/placeholders/waiting-generic.png',
                notAvailablePath: 'js/fine-uploader/placeholders/not_available-generic.png'
            }
        },
        autoUpload: options.isAutoUpload,
        debug: true
    });
}
