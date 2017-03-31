//保存用户桌面
				function save_deskTop() {
					var widgets = $('#content').children('div');
					if(widgets.length>1){
					var widgetIds="";
					$(widgets).each(function(){
					if($(this).attr('id')!='' && $(this).attr('id')!=null&&$(this).css('display')!='none'){
						widgetIds=widgetIds+","+$(this).attr('id')
					}});
					var operatorCode=$("#operatorId").html();
					var deskTop={};
					deskTop.deskTopId=widgetIds.substring(1);
					DesktopService.updateEntity(deskTop,"operatorId = '"+operatorCode+"'",function(data){
									if(data == true){
									//alert("保存成功");
									}else{	
									//alert('修改失败');
									}
					   	   		});
					}
				}