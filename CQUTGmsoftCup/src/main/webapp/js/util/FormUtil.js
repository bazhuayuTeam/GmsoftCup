/*
 *处理字段加载等 
 */
var FormUtil = {};

FormUtil.coms = new Array();
FormUtil.createComponentFinished= false;

FormUtil.bindData=function(data){
	FormUtil.data=data;			
	for(var i=0;i<FormUtil.coms.length;i++){
	   FormUtil.coms[i].bindData(data);		
	}
};

FormUtil.exportToData=function(data){//把表单控件的值导出到对象
	if(!data){
		data=FormUtil.data;
	}	
	
	for(var i=0;i<FormUtil.coms.length;i++){
		
		FormUtil.coms[i].exportToData(data);	
	}
};

FormUtil.valid=function(){//验证
	var err="";
	var tmpStr="";
	for(var i=0;i<FormUtil.coms.length;i++){
		tmpStr=FormUtil.coms[i].valid();
		if(tmpStr){
			err+=tmpStr+"\n";
		}
	}
	return err;
};

FormUtil.isModified=function(){//判断是否修改
	for(var i=0;i<FormUtil.coms.length;i++){
       if(FormUtil.coms[i].isModified()){
    	   return true;
       }		
	}
	return false;
};

FormUtil.createComponent=function(ids,options,callback){ //创建组件，ids-创建的ID数组
	this.createComponentFinished= false;
	var obj;
	/*var i,j,k,temp;
	var n=ids.length;
	//实现通过focusIndex属性来排序
	for(k=0;k<n-1;k++){
		if(!$("#"+ids[k]).attr("focusIndex")){
			$("#"+ids[k]).attr("focusIndex",9999);
		}
	}
	for(i=0;i<n-1;i++){
		temp=ids[i+1];
		j=i;
		while(j>-1&&$("#"+temp).attr("focusIndex")<$("#"+ids[j]).attr("focusIndex")){
			ids[j+1]=ids[j];
			j--;
		}
		ids[j+1]=temp;
	}*/
	this.sortedHtmlTagArrayByField(ids, "focusIndex");
	
	for(var i=0;i<ids.length;i++){
		obj=$("#"+ids[i]);
		if(obj&&obj[0]){
			if(obj.attr("fieldType")=="number"){//数值类型
				FormUtil.coms[i]=obj.FieldNumber(options);
			}else if(obj.attr("fieldType")=="date"){//日期类型
				FormUtil.coms[i]=obj.FieldDate(options);
			}else if(obj.attr("fieldType")=="timepicker"){//时间类型
				if(!callback){
					callback=function (){
						obj.val("");
					};
				}
				FormUtil.coms[i]=obj.FieldTime(options,callback);
			}else if(obj.attr("fieldType")=="print"){//打印
				FormUtil.coms[i]=obj.FieldPrint(options);
			}else if(obj[0].tagName.toLowerCase()=="select"){//下拉框
				FormUtil.coms[i]=obj.FieldSelect(options);
			}else if(obj[0].tagName.toLowerCase()=="input" || obj[0].tagName.toLowerCase()=="textarea"){//普通文本框
				FormUtil.coms[i]=obj.FieldText(options);
			}
		}
	}	
	//doFocus(ids);
	this.createComponentFinished= true;
};

FormUtil.sortedHtmlTagArrayByField = function (array, field) {
	var i,j,k,temp;
	var n=array.length;
	//实现通过focusIndex属性来排序
	for(k=0;k<n-1;k++){
		if(!$("#"+array[k]).attr(field)){
			$("#"+array[k]).attr(field, 9999);
		}
	}
	for(i=0;i<n-1;i++){
		temp=array[i+1];
		j=i;
		while(j>-1&&$("#"+temp).attr(field)<$("#"+array[j]).attr(field)){
			array[j+1]=array[j];
			j--;
		}
		array[j+1]=temp;
	}
	
	return array;
};

function doFocus(array){
	var compoIndexArr=array;
	//var currentObj;//当前焦点所在JQuery对象
	//处理第一个焦点
	focusOne();
	
	//监听按键，实现焦点调转
	document.onkeydown=doKeyDown;
	//焦点定位到第一个组件
	function focusOne(){
		for(var i in compoIndexArr){
			var obj=$('#'+compoIndexArr[i]);
			//有readonly属性的标签或者textArea不放置焦点
			if(obj.attr("readonly")||obj[0].tagName.toLowerCase()=="textArea"){
				continue;
			}
			else{
				obj.focus();
				//currentObj=obj;
				break;
			}
		}
	}
	//捕捉按键
	function doKeyDown(){
		if(!Array.indexOf) 
		{ 
		    Array.prototype.indexOf = function(obj) 
		    {                
		        for(var i=0; i<this.length; i++) 
		        { 
		            if(this[i]==obj) 
		            { 
		                return i; 
		            } 
		        } 
		        return -1; 
		    } 
		} 
	    if (event.keyCode==13){//点击回车
	        if (event.srcElement.tagName.toLowerCase()!='textarea'){//textArea中不监听
	        	toNext();
	        }  
	    }
	    else if (event.keyCode==13&&event.ctrlKey){//点击Ctrl+回车
	        	toNext();
	    }
	    else if(event.keyCode==81&&event.ctrlKey){//点击Ctrl+q
	    	focusOne();
	    }
	    else if(event.keyCode==40){//点击向下
	    	//如果不是textArea或者select就跳到下一个组件
	    	if(event.srcElement.tagName.toLowerCase()!='textarea'&&event.srcElement.tagName.toLowerCase()!='select'){
	    		toNext();
	    	}
	    }
	    else if(event.keyCode==38){//点击向上
	    	//如果不是textArea或者select就跳到上一个组件
	    	if(event.srcElement.tagName.toLowerCase()!='textarea'&&event.srcElement.tagName.toLowerCase()!='select'){
	    		toFormer();
	    	}
	    }
	    else if(event.keyCode==27){//点击Esc
	    	window.close();
	    }
	}
	//跳转到下一个控件
	function toNext(){
		var currentId=event.srcElement.id;
		var currentIndex=compoIndexArr.indexOf(currentId);
		var nextObj=$("#"+compoIndexArr[currentIndex+1]);//需要跳转到的对象
		//下一个对象有弹窗
		if(nextObj.attr("referClass")){
			nextObj.focus();
			$(nextObj.siblings('span')[0]).click();
			
		}
		else{
			nextObj.focus();
		}
		
	}
	//跳转到上一个控件
	function toFormer(){
		var currentId=event.srcElement.id;
		var currentIndex=compoIndexArr.indexOf(currentId);
		var formerObj=$("#"+compoIndexArr[currentIndex-1]);
		//下一个对象有弹窗
		if(formerObj.attr("referClass")){
			var result=doselect(nextObj.attr("referClass"),//弹出对话框
				nextObj.attr("regerCondition"));
		}
		else{
			formerObj.focus();
		}
	}
}

