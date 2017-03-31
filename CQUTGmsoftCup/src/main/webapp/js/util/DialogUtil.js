/*
 *浮动窗口显示及通信相关 
 */
var DialogUtil={};
DialogUtil.winKey='';
DialogUtil.EVENT_OK='EVENT_OK';
DialogUtil.EVENT_CANCEL='EVENT_CANCEL';
DialogUtil.EVENT_ERROR='EVENT_ERROR';
DialogUtil.DataExchangeBuffer={};//与子窗口的数据交换缓冲
DialogUtil.ListenerBuffer={};//子窗口事件缓冲
DialogUtil.param = null;	// 兼容谷歌的传参

DialogUtil.getBufferData=function(key){
	return DialogUtil.DataExchangeBuffer[key];	
};
DialogUtil.removeBufferData=function(key){
	delete DialogUtil.DataExchangeBuffer[key];	
};
DialogUtil.getBufferListener=function(key){
	return DialogUtil.ListenerBuffer[key];	
};
DialogUtil.removeBufferListener=function(key){
	delete DialogUtil.ListenerBuffer[key];	
};
/*
 *打开浮动窗口 
 */
 DialogUtil.openFloatWindow=function(path,param,listeners){
   var tw=DialogUtil.getTopWindow();
   var key="key"+(new Date()).getTime();
   if(path.indexOf("#")==-1){
	 path+="#parentKey="+key;
   }else{
	  path+="&parentKey="+key;
   }
   path+="&winMode=0";
   //唯一标识某一个窗口
   DialogUtil.winKey=key;
//   if(param&&param!=null){
	if(param!=null){   
	  DialogUtil.DataExchangeBuffer[key]=param;  
   }
   if(listeners){
	  DialogUtil.ListenerBuffer[key]=listeners;
  }
  //这一段代码不知道有什么用
  if(tw.WindowManager){
	  tw.WindowManager.showFlowWindow(path,key,window);
  }
};
/*
 * 打开浏览器模态窗口
 */
 DialogUtil.openModalWindow=function(path,param,listeners,width,height){
	 if(path.indexOf("#")==-1){
		 path+="#winMode=1"; //winMode-0:浮动窗口,1-浏览器模态窗口
	 }else{
		path+="&winMode=1";
	 }
	 
	 DialogUtil.param = param;
	 var data = null;
	 
	 if(window.showModalDialog){
		 data =  window.showModalDialog(path,param,"dialogHeight="+height+"px;dialogWidth="+width+"px");
	 }else{
		 var win = window.open(path,"","height=" + height + ",width=" + width + ",status=yes,toolbar=no,menubar=no,location=no");
		 getDataForChrome(win,listeners);
		 return;
	}
	//for chrome
/*	if (!data) {
	    data = window.data;
	}*/
	
	applyData(data,listeners);
 };
 
 function applyData(data,listeners){
	 //apply方法不是很熟悉
	 if(data!=null){
		 if(listeners&&listeners[DialogUtil.EVENT_OK]){
			 listeners[DialogUtil.EVENT_OK].apply(this,[data]);
		 }
	 }else{
		 if(listeners&&listeners[DialogUtil.EVENT_CANCEL]){
			 listeners[DialogUtil.EVENT_CANCEL].apply(this,[data]);
		 }
	 }
 }
 
 // 在chrome下使用window.open 获取值
 function getDataForChrome(win,listeners){
	  wTimer=window.setInterval(function(){
		   if(win.closed){  
		    //alert(w.returnValue);//子窗体返回值  
		    //这里可以做赋值操作  
		    window.clearInterval(wTimer);  
		    //console.info(window.ChildDialogUtil.returnValue);
		    applyData(window.ChildDialogUtil.returnValue,listeners);
  			}  
	  },500);  
}  
  

/*
 * 子窗口关闭回调
 */
DialogUtil.doEvent=function(param){
	var listeners;
	var listener;
	listeners=DialogUtil.getBufferListener(DialogUtil.winKey);
	if(listeners){
		listener=listeners[param.event];
		if(listener){
			listener.apply(listener,[param.data]);
		}			
	}	
};

//子窗口调用
var ChildDialogUtil={};
//得到父窗口Key
ChildDialogUtil.getParentKey=function(){
	if(ChildDialogUtil.parentKey){
		return ChildDialogUtil.parentKey;
	}else{
		ChildDialogUtil.parentKey=DialogUtil.getParameter("parentKey");
		return ChildDialogUtil.parentKey;
	}
};
//得到窗口模式，0-浮动窗口打开，1-浏览器模态窗口
ChildDialogUtil.getWinMode=function(){
	if(ChildDialogUtil.winMode){
		return ChildDialogUtil.winMode;
	}else{
		ChildDialogUtil.winMode=DialogUtil.getParameter("winMode");
		return ChildDialogUtil.winMode;
	}
};
/*
 * 关闭子窗口
 */
ChildDialogUtil.doClose=function(event_flag,param){
  var winMode=ChildDialogUtil.getWinMode();
  if(winMode=='0'){
	  var tw=DialogUtil.getTopWindow();
	  if(tw.WindowManager){
		  try{
			  tw.WindowManager.doWinFunction(ChildDialogUtil.getParentKey(),"DialogUtil.doEvent",event_flag,param);
			  tw.WindowManager.removeWindow(ChildDialogUtil.getParentKey());
			  DialogUtil.removeBufferData(ChildDialogUtil.getParentKey());
			  DialogUtil.removeBufferListener(ChildDialogUtil.getParentKey());
			  //tw.WindowManager.hiddenFlowWindow();
		  }catch(e){
			  alert(e);
		  }
	  }  
  }else{
	  returnValue=param;
	  
	  if(window && window.opener &&window.opener.window)
		  window.opener.window.ChildDialogUtil.returnValue=param;	// for chrome
  }
  
  DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
};

/*
 * 直接调用父窗口事件
 */
ChildDialogUtil.doOk = function(event_flag,param){
	var tw = DialogUtil.getTopWindow();
	if(tw.WindowManager){
		  try{
			  tw.WindowManager.doWinFunction(ChildDialogUtil.getParentKey(),"DialogUtil.doEvent",event_flag,param);
		  }catch(e){
			  alert(e);
		  }
	  }  
};

/*
 * 得到父亲窗口传递的参数
 */
ChildDialogUtil.getExchangeData = function(){
	var winMode=ChildDialogUtil.getWinMode();
	if(winMode=='1'){
	  //return arguments;//改了
		return  window.showModalDialog ? dialogArguments : window.opener.window.DialogUtil.param;
	}else{
		var tw=DialogUtil.getTopWindow();
		if(tw.WindowManager){
			return tw.WindowManager.getExchangeData(ChildDialogUtil.getParentKey());	
		}	
		return null;	
	}	
};

/*
 * 得到最上层Window
 */
DialogUtil.getTopWindow=function(){
  var pw = window;
  while(pw.parent&&pw.parent!=pw){
	 pw=pw.parent;
  }
  return pw;
};
/*
 * 从URL中取得参数
 */
DialogUtil.getParameter=function(key,win){
  if(!win)win=window;
  var urlStr=win.location.toString();
  var arrUrl=urlStr.split("#");
  var arrTmp;
  if(arrUrl&&arrUrl.length==2){
	 arrUrl=arrUrl[1].split("&");
	 for(var i=0;i<arrUrl.length;i++){
		arrTmp=arrUrl[i].split("=");
		if(arrTmp[0].toLowerCase()==key.toLowerCase()){
			return arrTmp[1];
		}
	 }
  }
  return null;
};
/*
 * 弹出提示窗口
 */
DialogUtil.alert=function(){
	
};
 