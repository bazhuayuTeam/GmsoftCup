;
var data = ChildDialogUtil.getExchangeData();
$(document).ready(function() {
	//创建控件
	$("#mainBody").createDialog( {
		height : 250,
		width : 350,
		resizable : false,
		modal : true,
		title : "上传头像",
		winMode : ChildDialogUtil.getWinMode(),
		close : function(event) {
			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL, {});
				return;
			}
			if (event.data.type == DialogUtil.EVENT_OK) {
				var result = getResultWithPhoto();
				ChildDialogUtil.doClose(DialogUtil.EVENT_OK, result);
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
				ChildDialogUtil.doClose(event.data.type, {});
			} else {
				ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
	initData();
});

function getResultWithPhoto() {
	var result = {};
	var systemFileID = getPhoto(data.ZGH);
	if(document.getElementById("photo").files.length != 0) {
		if(systemFileID != "") {
			deleteSystemFile(systemFileID);
		}
		var photo = uploadPhoto();
		result.systemFileID = getSystemFileID();
	}
	return result;
}
function initData() {
	var systemFileID = getPhoto(data.ZGH);
	$("#preview").attr("src", "common/image.action?imgId="+systemFileID+"");
}
