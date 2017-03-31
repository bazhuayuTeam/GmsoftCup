(function($){	
	$.fn.createDialog = function(options){
		if(!options.buttons){//如果用户未定义按钮
			options.buttons={};
			if(options.winMode&&options.winMode=='1'){//弹出浏览器模态窗口
				if(undefined != options.proceed && options.proceed == true){
					$.extend(options.buttons,{
						"保存并继续": function(event){
							event.data={};
							event.data.type=doOK();
							if(event.data.type==DialogUtil.EVENT_ERROR)return;//by helong 先进行框架的验证判断
							if(typeof(userDialogValid) != "undefined"){
								event.data.userValid = userDialogValid.valid();
								if(event.data.userValid == userDialogValid.VALID_FAILED)return;
							}
							options.continuefun();
						},
						"确定": function(event){
							event.data={};
							event.data.type=doOK();
							if(event.data.type==DialogUtil.EVENT_ERROR)return;
							if(typeof(userDialogValid) != "undefined"){
								event.data.userValid = userDialogValid.valid();
								if(event.data.userValid == userDialogValid.VALID_FAILED)return;
							}
							$( this ).modalDialog( "close" ,event);
							DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
						},
						"退出": function(event){
							event.data={};
							event.data.type=DialogUtil.EVENT_CANCEL;
							$( this ).modalDialog( "close" ,event);
							DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
						}
					});
				}else{
					$.extend(options.buttons,{
						"确定": function(event){
							event.data={};
							event.data.type=doOK();
							if(event.data.type==DialogUtil.EVENT_ERROR)return;
							if(typeof(userDialogValid) != "undefined"){
								event.data.userValid = userDialogValid.valid();
								if(event.data.userValid == userDialogValid.VALID_FAILED)return;
							}
							$( this ).modalDialog( "close" ,event);
							DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
						},
						"退出": function(event){
							event.data={};
							event.data.type=DialogUtil.EVENT_CANCEL;
							$( this ).modalDialog( "close" ,event);
							DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
						}
					});
				}
				
				$(this).modalDialog(options);
			}else{//弹出浮动窗口
				if(undefined != options.proceed && options.proceed == true){
						$.extend(options.buttons,{
							"保存并继续": function(event){
								event.data={};
								event.data.type=doOK();
								if(event.data.type==DialogUtil.EVENT_ERROR)return;//by helong 先进行框架的验证判断
								if(typeof(userDialogValid) != "undefined"){
									event.data.userValid = userDialogValid.valid();
									if(event.data.userValid == userDialogValid.VALID_FAILED)return;
								}
								options.continuefun();
							},
							"确定": function(event){
								event.data={};
								event.data.type=doOK();
								if(event.data.type==DialogUtil.EVENT_ERROR)return;//by helong 先进行框架的验证判断
								if(typeof(userDialogValid) != "undefined"){
									event.data.userValid = userDialogValid.valid();
									if(event.data.userValid == userDialogValid.VALID_FAILED)return;
								}
								$( this ).dialog( "close" ,event);
							},
							"退出": function(event){
								event.data={};
								event.data.type=DialogUtil.EVENT_CANCEL;
								$( this ).dialog( "close" ,event);
							}
						});			
				}else{
					$.extend(options.buttons,{
						"确定": function(event){
							event.data={};
							event.data.type=doOK();
							if(event.data.type==DialogUtil.EVENT_ERROR)return;//by helong 先进行框架的验证判断
							if(typeof(userDialogValid) != "undefined"){
								event.data.userValid = userDialogValid.valid();
								if(event.data.userValid == userDialogValid.VALID_FAILED)return;
							}
							$( this ).dialog( "close" ,event);
						},
						"退出": function(event){
							event.data={};
							event.data.type=DialogUtil.EVENT_CANCEL;
							$( this ).dialog( "close" ,event);
						}
					});			
				}
				this.dialog(options);
			}
		}
		else{//用户自定义了按钮
			if(options.winMode&&options.winMode=='1'){//弹出浏览器模态窗口
				$(this).modalDialog(options);
			}else{//弹出浮动窗口
				this.dialog(options);
			}
		}
		return this;
	};
	function doOK(){
		//FormUtil.createComponent(this,'');//创建组件
		var error=FormUtil.valid();
		if(error){
		  alert(error);
		  return DialogUtil.EVENT_ERROR;
		}else{//验证通过
//		  if(FormUtil.isModified()){//已被修改
//			  FormUtil.exportToData();
			  return DialogUtil.EVENT_OK;
//		  }else{
//			  return DialogUtil.EVENT_CANCEL;  //不需保存
//		  }
		}		
	};
}(jQuery));

(function( $, undefined ) {
	attrFn = $.attrFn || {
		val: true,
		css: true,
		html: true,
		text: true,
		data: true,
		width: true,
		height: true,
		offset: true,
		click: true
	};	
	
	$.widget("cqut.modalDialog", {
		options:{
			buttons:{}
		},
		_create: function(){
			window.title=this.element.attr('title');
			var body=$(document.body).attr('topmargin','0').attr('leftmargin','0');
			var obj=$("<table width='100%' height='100%' cellspacing='0' cellpadding='0'><tr><td valign=\"top\"></td></tr><tr><td height='35' align='right' style='border-top:solid 1 gray'></td></tr></table>");
			//得到相应对象
			var contentPane=obj[0].children[0].children[0].children[0];
		    var buttonPane=obj[0].children[0].children[1].children[0];
		    var self=this;
			//添加按钮
			$.each(self.options.buttons, function(name,props){
				props = $.isFunction( props )?
					{click:props,text:name}:
					props;
				var button = $('<button type="button"></button>')
					.click(function() {
						props.click.apply(self.element[0], arguments);
					}).appendTo(buttonPane);
				$.each( props, function( key, value){
					if ( key === "click" ) {
						return;
					}
					if ( key in attrFn){
						button[ key ](value);
					} else {
						button.attr( key, value);
					}
				});
				if ($.fn.button) {
					button.button();
				}
			});
			this.element.appendTo(contentPane);
			obj.appendTo(body);			
						
		},
		close:function(event){
			var self = this;				
		    if(false === self._trigger('close', event)){
			  return;
		    }
			window.close();
		}
	});
}(jQuery));