;

//上传图片 传入文件输入框id,职工号,回调函数参数必须是data(后端返回的file对象)
function uploadPhoto(imgInputID, ZGH, fun) {
	var systemFileID = getPhoto(ZGH);
	if(document.getElementById(imgInputID).files.length != 0) {
		if(systemFileID && systemFileID != "null") {
			deleteSystemFile(systemFileID);
		}
		var i = 0;
		var selectedFilesPath = [];
		var selectedFiles = [];
		var files = $("#" + imgInputID);
		files.each(function(index, cur) {
			var curpath = cur.value;
			if (curpath != '') {
				selectedFilesPath[i] = curpath.match(/[^\/\\]+$/gi)[0];
				selectedFiles[i++] = cur;
			}
		});
		
		if(i > 0) {
			SystemFileService.uploadImg(selectedFiles[selectedFiles.length-1], selectedFilesPath[selectedFilesPath.length-1], fun);
		}
	}
}
/*function uploadPhoto() {
	var reback ={};
	var i = 0;
	var selectedFilesPath = [];
	var selectedFiles = [];
	var files = $("#photo");
	files.each(function(index, cur) {
		var curpath = cur.value;
		if (curpath != '') {
			selectedFilesPath[i] = curpath.match(/[^\/\\]+$/gi)[0];
			selectedFiles[i++] = cur;
		}
	});
	
	if(i > 0) {
		reback = uploadOne(selectedFiles[selectedFiles.length-1], selectedFilesPath[selectedFilesPath.length-1]);
		
	}
	
	return reback;
}

//上传
//function uploadSome(selectedFiles, selectedFilesPath) {
//	var reback = [];
//	dwr.engine.setAsync(false);
//	SystemFileService.uploadFiles(selectedFiles, selectedFilesPath,function(data) {
//		reback = data;
//	});
//	dwr.engine.setAsync(true);
//	return reback;
//}

function uploadOne(file, fileName) {
	var reback = {};
	SystemFileService.uploadImg(file, fileName,function(data) {
		reback = data;
		console.info(reback);
	});
	dwr.engine.setAsync(true);
	return reback;
}
*/
function previewImage(value) {
	var imgObjPreview=document.getElementById("preview");
	if(value.files &&value.files[0]) {
		//火狐下，直接设img属性
		imgObjPreview.style.display = 'block';
		//imgObjPreview.style.width = '400px';
		//imgObjPreview.style.height = '180px'; 
		//imgObjPreview.src = value.files[0].getAsDataURL();
		 
		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
		imgObjPreview.src = window.URL.createObjectURL(value.files[0]);
	}else {
		//IE下，使用滤镜
		value.select();
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag");
		//必须设置初始大小
		//localImagId.style.width = "400px";
		//localImagId.style.height = "180px";
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try{
			localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		}catch(e){
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
}

//根据操作人员得到systemfileID
function getPhoto(ZGH) {
	var systemFileID = "";
	dwr.engine.setAsync(false);
	OperatorService.findMapByPropertiesQuick(["systemFileID"], "ZGH='" + ZGH + "'",false, function(data) {
		if(data && data.length > 0) {
			systemFileID = data[0].systemFileID;
		}
	});
	dwr.engine.setAsync(true);
	return systemFileID;
}

//得到id
function getSystemFileID() {
	var systemFileID = "";
	dwr.engine.setAsync(false);
	SystemFileService.findMapByPropertiesQuick(["MAX(id)"], "1=1",false, function(data) {
		systemFileID = data[0]['MAX(id)'];
	});
	dwr.engine.setAsync(true);
	return systemFileID;
}

//删除头像
function deleteSystemFile(systemFileID) {
	var isOk = false;
	dwr.engine.setAsync(false);
	SystemFileService.deleteFile(systemFileID, function(data) {
		if(data != null && data != "") {
			isOk = true;
		}
	});
	dwr.engine.setAsync(true);
	return isOk;
}

function deleteByCondition(condition) {
	var isOk = true;
	dwr.engine.setAsync(false);
	SystemFileService.deleteByCondition(condition, function(data) {
			isOk = data;
	});
	dwr.engine.setAsync(true);
	return isOk;
}

//删除多个头像
function deleteSystemFiles(systemFileIDs) {
	var isOk = false;
	dwr.engine.setAsync(false);
	SystemFileService.deleteFile(systemFileIDs, function(data) {
		if(data != null && data != "") {
			isOk = true;
		}
	});
	dwr.engine.setAsync(true);
	return isOk;
}

//得到in（）合适的结构
function getINData(list) {
	var data = "(";
	for(var i = 0; i < list.length; i++) {
		if(i != list.length - 1) {
			data += "'" + list[i] + "',";
		}else {
			data += "'" + list[i] + "')";
		}
	}
	return data;
} 