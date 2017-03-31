//�ϴ��ļ�
function upload() {
	var selectedFiles = [];
	var i = 0;
	var files = $(".MultiFile-wrap").children(":file[id^='upload_file'][value!='']");
	files.each(function(index, cur) {
				var curpath = cur.value;
				if (curpath != '') {
					selectedFiles[i++] = cur;
				}
			});
	if ($(selectedFiles).length > 0) {// ѡ�����ļ���
		$.fn.MultiFile.disableEmpty();
		$('#upload_info').html('�ϴ���...');
		SystemFileService.uploadFiles(selectedFiles, function(data) {
			addFileShowInPage(data);
			$('#upload_info').html('�ϴ����');
			$('input:file').MultiFile('reset');
			$(".MultiFile-wrap").children(":file[id^='upload_file'][value!='']").each(
							function(i, cur) {
								$(cur).remove();
							});
			$.fn.MultiFile.reEnableEmpty();
		});
	}
}
// ɾ���ļ�
function deleteFile(id) {
	SystemFileService.deleteFile(id, function(data) {
				if (data && data != null) {
					$('#uploadedFile_' + id).remove();
				}
			});
	return false;
}
// �����ļ�
function deloadFile(id) {
	SystemFileService.downloadFile(id, function(data) {
		console.info(data);
				if (data && data != null) {
					dwr.engine.openInDownload(data);
				} else {
					alert('�ļ�������');
				}
			});
	return false;
}
// ��ȡ���ϴ����ļ�(����[{id:1,fileName:ս��.elx},{id:45,fileName:����}])
function getUploadedFiles() {
	var results = [];
	var files = $('#file-uoloaded-div').find("[name='fileInfo']");
	files.each(function(i, cur) {
				results[i] = {
					id : $(cur).attr("id"),
					fileName : $(cur).html()
				};
			});
	return results;
}
function getUploadedFilesId() {
	var results = [];
	var files = $('#file-uoloaded-div').find("[name='fileInfo']");
	files.each(function(i, cur) {
				results[i] = $(cur).attr("id");
			});
	return results;
}

function getUploadedFilesName() {
	var results = [];
	var files = $('#file-uoloaded-div').find("[name='fileInfo']");
	files.each(function(i, cur) {
				results[i] = $(cur).html();
			});
	return results;
}

//��ȡ�Ѿ��ϴ����ļ�
function loadUploadedFiles(data) {
	var fileIds = data;
	var condition = '';
	if($.type(data)=='string'){
		fileIds = data.split(',');
	}
	if($(fileIds).length == 0){
		return;
	}
	$(fileIds).each(function(i,cur){
		if(i != 0){
			condition += ',';
		}
		condition += "'"+cur+"'";
	});
	
	SystemFileService.findMapByPropertiesQuick(['id','fileName'],"id IN ["+condition+"]",false,function(results){
		addFileShowInPage(results);
	});
}
//��ҳ������ʾ�ļ�
function addFileShowInPage(data){
	if ($(data).length = 0)
		return;
	var fud = $('#file-uoloaded-div');
	$(data).each(function(i, cur){
		fud.append("<div id='uploadedFile_"
				+ cur.id
				+ "' style='margin-left:20;float:left;'><a href='javascript:void(0);' onclick='deleteFile(\""
				+ cur.id
				+ "\");'>[ɾ��]</a><a id='"
				+ cur.id
				+ "' name='fileInfo' href='javascript:void(0);' onclick='deloadFile(\""
				+ cur.id
				+ "\");' style='padding-left:2px;padding-right:5px;'>"
				+ cur.fileName + "</a></div>");
	});
}