;

var data ={} ;
if(ChildDialogUtil.getExchangeData){
	 data = ChildDialogUtil.getExchangeData();
	 //printObject(data);
}
userDialogValid.valid = function(){
	return valid(setData());
}
$(document).ready(function() {
		loaddata(data);
		$("#dialog-modal").createDialog( {
			height : 300,
			width : 400,
			resizable : false,
			modal : true,
			close : function(event) {
				if (!event.data) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
					return;
				}
				if (event.data.type == DialogUtil.EVENT_OK) {
					    var result = getResult();   
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
					
				}
				else if (event.data.type == DialogUtil.EVENT_CANCEL) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
				}
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
		});
		
	});

//验证输入框是否为空
userDialogValid.valid = function(){
	var academicCode = $.trim($('#academicCode').val());
	var academicName = $.trim($('#academicName').val());
	var academicShort = $.trim($('#academicShort').val());
	if(academicCode == ""){
		jAlert("请输入部门代号!");
		return false;
	}
	if(academicName == ""){
		jAlert("请输入部门名称!");
		return false;
	}
	if(academicShort == ""){
		jAlert("请输入部门简称!");
		return false;
	}
}

function valid(dto){
	try{
		if(dto.roleName == '')
			//jAlert("请输入角色名称!");
			return false;
	}catch(e){
		return false;
	}
	return true;
}
//加载数据
function loaddata(data){
         $('#academicCode').val(data.academicCode);
		 $('#academicName').val(data.academicName);
		 $('#academicShort').val(data.academicShort != 'null' ? data.academicShort : "" );
		 loadacademicType(data.academicType);
}
//加载部门类型
function loadacademicType(academicType){
      CodeTableService.findMapByProperties(["codeTableCode","codeTableName"],"parentCode='BMType'","","",false,function(data){
      	var htmlString="";
      	$(data).each(function(index,val){
      	   if(val.codeTableCode==academicType){
      	   	htmlString+="  <option value='"+val.codeTableCode+"' selected='selected'> "+val.codeTableName+"</option>"; 
      	   }else{
      	   	htmlString+="  <option value='"+val.codeTableCode+"'>"+val.codeTableName+"</option>"; 
      	   }
      	   
      	});
        $("#academicType").append(htmlString);
      });
}
//获取最后结果
function getResult(){
	var academicCode = $.trim($('#academicCode').val());
	var academicName = $.trim($('#academicName').val());
	var academicShort = $.trim($('#academicShort').val());
	var academicType = $.trim($('#academicType').val());
	var data={};
	data.academicCode=academicCode;
	data.academicName=academicName;
	data.academicShort=academicShort;
	data.academicType= academicType;
	return data;
}