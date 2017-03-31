/*
 * 一般用于顶层框架中，管理弹出显示窗口
 */
var WindowManager={};
WindowManager.cwins={};
WindowManager.frameCreated=false;
WindowManager.addWindow=function(key,win){
	WindowManager.cwins[key]=win;
};
WindowManager.getWindow=function(key){
	return WindowManager.cwins[key];
};
WindowManager.removeWindow=function(key){
	delete WindowManager.cwins[key];
};
WindowManager.doWinFunction=function(key,method,event_flag,para){	
	var win=WindowManager.getWindow(key);
	try{		
	  if(win.eval(method)){		  
		  win.eval(method).apply(win,[{event:event_flag,data:para}]);
	  }else{				  
		 alert(key+"."+method+"不存在！");
	  }
	}catch(e){				
	  alert(e.message);
	  alert(key+"."+method+"不存在！");
	}
};
WindowManager.getExchangeData=function(key){
  var win=WindowManager.getWindow(key);
  if(win && win.DialogUtil){
	  return win.DialogUtil.getBufferData(key);
  }
  return null;
};
WindowManager.hiddenFlowWindow=function(){	
	if(WindowManager.frameCreated){
		//floatWindowFrame.location="about:blank";
		$(document.getElementById('floatWindowFrame')).remove();
	}
};
WindowManager.showFlowWindow=function(path,key,caller){	
	WindowManager.addWindow(key,caller);
	if($.browser.mozilla){//兼容firefox浏览器
		if(typeof HTMLElement!="undefined" && !HTMLElement.prototype.insertAdjacentElement)
		 {
		      HTMLElement.prototype.insertAdjacentElement = function(where,parsedNode)
		      {
		         switch (where)
		         {
		             case 'beforeBegin':
		                 this.parentNode.insertBefore(parsedNode,this)
		                 break;
		             case 'afterBegin':
		                 this.insertBefore(parsedNode,this.firstChild);
		                 break;
		             case 'beforeEnd':
		                 this.appendChild(parsedNode);
		                 break;
		             case 'afterEnd':
		                 if (this.nextSibling) this.parentNode.insertBefore(parsedNode,this.nextSibling);
		                     else this.parentNode.appendChild(parsedNode);
		                 break;
		          }
		      }
		
		     HTMLElement.prototype.insertAdjacentHTML = function (where,htmlStr)
		      {
		          var r = this.ownerDocument.createRange();
		          r.setStartBefore(this);
		          var parsedHTML = r.createContextualFragment(htmlStr);
		          this.insertAdjacentElement(where,parsedHTML)
		      }
		
		     HTMLElement.prototype.insertAdjacentText = function (where,txtStr)
		      {
		          var parsedText = document.createTextNode(txtStr)
		          this.insertAdjacentElement(where,parsedText)
		      }
		 }
	}

	if(!WindowManager.frameCreated){
	  WindowManager.frameCreated=true;
	  var html="<iframe id='floatWindowFrame' frameborder='2' allowTransparency='true' style='width:100%;height:100%;position:absolute;left:0;top:0;z-index:500' src='"+path+"'></iframe>"
	  document.body.insertAdjacentHTML("beforeEnd",html);
	}else{
	  var html="<iframe id='floatWindowFrame' frameborder='2' allowTransparency='true' style='width:100%;height:100%;position:absolute;left:0;top:0;z-index:500' src='"+path+"'></iframe>"
	  document.body.insertAdjacentHTML("beforeEnd",html);
		/*
	  var obj=document.getElementById('floatWindowFrame');
	  obj.style.left="0";
	  obj.style.top="0";
	  obj.style.width="800px";
	  obj.style.height="600px";
	  obj.style.display='block';
	  if(path.indexOf("?")==-1){
		  path+=Math.random();
	  }else{
		  path+="&ssnumber="+Math.random();
	  }
	  floatWindowFrame.location=path;
	  floatWindowFrame.location.reload();
	  */
	}
	
};
/* Start 兼容代码 让火狐兼容insertAdjacentHTML 属性 */
if (typeof HTMLElement != "undefined" && !HTMLElement.prototype.insertAdjacentElement) {
 HTMLElement.prototype.insertAdjacentElement = function (where, parsedNode) {
  switch (where) {
    case "beforeBegin":
   this.parentNode.insertBefore(parsedNode, this);
   break;
    case "afterBegin":
   this.insertBefore(parsedNode, this.firstChild);
   break;
    case "beforeEnd":
   this.appendChild(parsedNode);
   break;
    case "afterEnd":
   if (this.nextSibling) {
    this.parentNode.insertBefore(parsedNode, this.nextSibling);
   } else {
    this.parentNode.appendChild(parsedNode);
   }
   break;
  }
 };
 HTMLElement.prototype.insertAdjacentHTML = function (where, htmlStr) {
  var r = this.ownerDocument.createRange();
  r.setStartBefore(this);
  var parsedHTML = r.createContextualFragment(htmlStr);
  this.insertAdjacentElement(where, parsedHTML);
 };
 HTMLElement.prototype.insertAdjacentText = function (where, txtStr) {
  var parsedText = document.createTextNode(txtStr);
  this.insertAdjacentElement(where, parsedText);
 };
}
/* End 兼容代码 让火狐兼容insertAdjacentHTML 属性 */

