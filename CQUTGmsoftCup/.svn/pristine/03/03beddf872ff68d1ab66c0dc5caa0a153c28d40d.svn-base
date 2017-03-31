function printObject(obj){	
	alert(objectToString(obj));
}
function objectToString(obj){
	var str="";
	for (var i in obj){
	  str+=i+"="+obj[i]+"   ";
	}
	return str;
}
var WinUtil={};
WinUtil.base="";
//得到工程路径
WinUtil.getBase=function(){
  if(WinUtil.base==""){
	 var url=window.location.toString();
	 var index=7;
	 var pre="http://";
	 if(url.indexOf("https")==0){
	   index=8;
       pre="https://";
	 }
	 url=url.substring(index);
	 var arr=url.split("/");
	 WinUtil.base=pre+arr[0]+"/"+arr[1]+"/";
  }
  return WinUtil.base;
};
//JqGrid的常用功能操作
//删除一条记录
/* 
 * dwrService为要调用的dwr Service对象
 * gridName表明从哪个表里删除一条数据
 * [confirm_msg]为删除时要弹出的确认信息  可选   默认是"确定删除这条数据？"
 * [condition]为删除记录后表格执行刷新的重置条件  是可选的   默认为 ""
 * [error_msg]为删除失败时的提示信息  可选  默认是"删除失败，请重试！"
 * */
	function deleteFun(params,rowId) {
		var dwrService=params.dwrService;
		var gridName=params.gridName;
		var confirm_msg=params.confirm_msg ? params.confirm_msg:"确定删除这条数据？";
		var condition=params.condition ? params.condition:"";
		var error_msg=params.error_msg ? params.error_msg:"删除失败，请重试！";
		var confirm = window.confirm(confirm_msg);
		if (rowId && confirm) {
			dwrService.deleteById(rowId, function(data) {
						if (data) {
							if(!condition){
								jQuery("#"+gridName).setGridParam({
									}).trigger("reloadGrid");
							}else{
								jQuery("#"+gridName).setGridParam({
										condition : condition
									}).trigger("reloadGrid");

							}
						} else {
							alert(error_msg);
						}
					})
		}
}