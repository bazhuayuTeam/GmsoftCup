var main_params={
    panelType:0,
    file:false,
    fileAgain:false,
    currentImage:false
};
$(document).ready(function() {
    eventBinding();
    getData();
    getGameType();
	$('#name').Watermark("请输入大赛名称", "#8f8f8f");
	$("#year").Watermark("例如:2016", "#8f8f8f");
	
	
	FormUtil.createComponent( [ "endTime" ],"timepicker");
	FormUtil.createComponent( [ "startTime" ],"timepicker");

	//创建控件
	$("#dialog-modal").createDialog({
		height : 540,
		width : 500,
		modal : true,
		close : function(event) {
			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
			if (event.data.type == DialogUtil.EVENT_OK) {
				var result={};
				if($("#isMultiStage").val()==0){
					result= {
							game:{
								gameName:$("#gameName").val(),
								isMultiStage:$("#isMultiStage").val(),
								signUpStartTime:DateUtil.DateToMS($("#startTime").val()),
								sigeUpEndTime:DateUtil.DateToMS($("#endTime").val()),
								competitionType:$("#competitionType").val(),
								gameType:$("#gameType").val(),
								level:$("#level").val(),
								propagandaPath:$("#propagandaPath").attr("data-id")
							},
							hostUnit:{
								hostUnitName:$("#hostUnitName").val(),
								secondUnitName:$("#secondUnitName").val()
							}
						};
					if($("#competitionType").val()==0){
						result.game.leastNumbe=$("#leastNumbe").val();
						result.game.maxNumber=$("#maxNumber").val();
					}
				}else{
					 result = {
							 game:{
								gameName:$("#gameName").val(),
								isMultiStage:$("#isMultiStage").val(),
								gameType:$("#gameType").val(),
								level:$("#level").val(),
								propagandaPath:$("#propagandaPath").attr("data-id")
							 },
							 hostUnit:{
								 hostUnitName:$("#hostUnitName").val(),
								secondUnitName:$("#secondUnitName").val()
							 }		
						};
				}
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					return;
			} else {
					ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
});

function getData(){
	if(ChildDialogUtil.getExchangeData){
		var passParams=ChildDialogUtil.getExchangeData(),
		data=passParams.id,
		type=passParams.type;
		GameService.getGame(["gameName","isMultiStage","signUpStartTime","sigeUpEndTime","competitionType","leastNumbe","maxNumber","gameType","level","propagandaPath"],["hostUnitName","type"],"gameID='"+data+"'","gameId='"+data+"'",false,function (params){
			if(params){
				$("#gameName").val(params.gameName);
				$("#isMultiStage").val(params.isMultiStage);
				$("#startTime").val(DateUtil.dateDiffMills(params.signUpStartTime));
				$("#endTime").val(DateUtil.dateDiffMills(params.sigeUpEndTime));
				$("#competitionType").val(params.competitionType);
				$("#leastNumbe").val(params.leastNumbe);
				$("#maxNumber").val(params.maxNumber);
				$("#hostUnitName").val(trimComma(params.hostUnitName));
				$("#secondUnitName").val(trimComma(params.secondUnitName));
				setTimeout(function (){
					$("#gameType").val(params.gameType);
				},500);
				$("#level").val(params.level);
				$("#propagandaPath").attr("data-id",params.propagandaPath);
				$("#competitionType").change();
				$("#isMultiStage").change();
			}
			//设置只读状态
			type&&$(".ui-dialog-buttonset").children().eq(0).addClass("hidden");
		});
	}
}

function trimComma(str){
	if(str.split(",").length>1){
		return str.slice(0,str.length-1);
	}else{
		return str;
	}
}

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
            $(this).parents(".panel-container-image").addClass("hidden");
        });
        $(".panel-cancel").bind("click",function (){
            $(this).parents(".panel-container").addClass("hidden");
            $(this).parents(".panel-container-image").addClass("hidden");
            $(".uploadImage").remove();
        });
        $(".image-group-list").delegate("li","mouseover",function (){
        	var options = $(this).children(".image-options");
        	options.removeClass("hidden");
        	$(this).hasClass("selected-image")?$(this).find(".options-selected").addClass("hidden"):
    		$(this).find(".options-selected").removeClass("hidden");
        });
        $(".image-group-list").delegate("li","mouseout",function (){
        	var options = $(this).children(".image-options");
        	options.addClass("hidden");
        });
        $("#imageClick").bind("click",function (){
        	$("#imageUpload").click();
        });
        $("#imageUpload").bind("mouseover",function (){
        	main_params.fileAgain=false;
        });
    }
    if(type===2){
		var imageGroupPanel=$(".panel-container-image");
		if(imageGroupPanel.length>0){
			imageGroupPanel.removeClass("hidden");
			imageGroupPanel.find("ul").html();
        }else{
        	$("#dialog-modal").prepend($("#imageGroupTemplate").html());
        	commonEventBinding(function (){
        		var ids="",
        		images=$(".uploadImage");
        		images.each(function (){
        			ids+=($(this).attr("src")+",");
        		});
        		$("#propagandaPath").attr("data-id",ids.slice(0,ids.length-1));
        	});
        	initUploadBtn();
        }
		var initValue=$("#propagandaPath").attr("data-id");
		if(initValue){
			setTimeout(function (){
				var imageList=initValue.split(",");
				for(var i=0,len=imageList.length;i<len;i++){
					addImage(imageList[i],"",$(".image-group-list"));
				}	
            },300);
		}
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
            setTimeout(function (){
        	 var lists=$(".panel-container").find("ul"),
                 values=initValue.split(",");
                var valuesHTML="";
                for(var i=0,len=values.length;i<len;i++){
                	var itemHTML='<li><label>单位'+(i+1)+':</label><input class="hostUnit" value="'+values[i]+'"><button class="remove-item" title="删除">X</button></li>';
                    valuesHTML+=itemHTML;
                }
                lists.html(valuesHTML);
            },300);
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

function setIndexImage(that){
	var selectedOld=$(".selected-image");
	selectedOld.removeClass("selected-image");
	$(that).parents("li").addClass("selected-image");
}

function removeImage(that){
	var item=$(that).parents("li");
	main_params.file.emptyFile(item.children("img").attr("data-id"));
	item.remove();
}

function addImage(src,id,container){
	var imageItemHTML=$("#imageItemTemplate").html(),
		imageValuedItemHTML=imageItemHTML.replace("{{imageSrc}}",src).replace("{{imageID}}",id).replace("{{uploadAgainID}}",id);
	container.prepend(imageValuedItemHTML);
}

function uploadAgain(that){
	main_params.currentImage=$(that).parent().prev("img");
	main_params.fileAgain=true;
	$(".MultiFile").click();
}

function initUploadBtn(){
	main_params.file=$("#imageUpload").fileUpload({
		uploadComplete:function (){
			var images=main_params.file.getUploadedFiles();
			for(var i=images.length-1,len=images.length;i<len;i++){
				var image=images[i],
					imageSrc=image.savePath,
					imageID=image.id;
				!main_params.fileAgain?addImage(imageSrc,imageID,$(".image-group-list")):main_params.currentImage.attr("src",imageSrc).attr("data-id",imageID);
			}
		},
		hideUploadFile:true
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
    $("#propagandaPath").bind("click",function (){
        hostUnitSelector(2,this);
    });
    $("#competitionType").bind("change",function (){
		$(this).val()==0?$(".teamLimit").removeClass("hidden"):$(".teamLimit").addClass("hidden");
    });
    $("#isMultiStage").bind("change",function (){
		$(this).val()==0?$(".forStage").addClass("hidden"):$(".forStage").removeClass("hidden");
    });
}