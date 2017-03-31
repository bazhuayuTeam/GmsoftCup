$(function(data){
	//创建控件
	$("#dialog-modal").createDialog({
		height : 400,
		width : 600,
		resizable : false,
		modal : true,
		winMode : ChildDialogUtil.getWinMode(),
		
		close : function(event) {

			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
				
			if (event.data.type == DialogUtil.EVENT_OK) {
					
				
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,{});
					
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					return;
			} else {
					ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});

});

