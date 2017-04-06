(function($) {
	var forData=[];
	var ulButton;
	var operateFlag = true;//操作标识
	var listMode = false;//列表模式，默认横排
	var showFileSize = true;//是否显示文件大小
	var imgSet = 'images/upload/extra.png';
	var successImg = 'images/upload/success.png';
	var loadingImg = 'images/index/loading.gif';
	var singleMode = false;//单文件上传模式
	
	$.fn.fileUpload = function(setting) {
		var curID =  new Date().getTime()+'-'+ Math.round(Math.random() * 10000);
		curID = 'file-uoload-div-'+curID;//创建ID
		
		var accept = 'gif|jpg|png|bmp|doc|docx|ppt|pptx|xls|xlsx|rar|zip|txt|pdf';
		if(setting && setting.accept && setting.accept != null){
			accept = setting.accept;
			accept.replace(/html/g,'').replace(/jsp/g,'').replace(/asp/g,'').replace(/exe/g,'').replace(/dll/g,'');
		}
		
		var uploadFlag = true;//是上传
		if(setting && setting.download != undefined && setting.download == true){
			//是下载
			uploadFlag = false; 
		}
		
		if(setting.singleMode != undefined){
			if(setting.singleMode)
				singleMode = true;
		}
		
		if(setting && setting.listMode == true){
			listMode = true;
		}
		
		if(setting && setting.showFileSize == false){
			showFileSize = false;
		}
		
		var showTitle = '添加附件';
		if(setting && setting.showTitle != null){
			showTitle = setting.showTitle;
		}
		
		
		var fileDiv = $('<div></div>').attr('id',curID);
		var waitingZone = $('<div/>').addClass('u-file-waiting');
		if(uploadFlag){
			var buttonZone = $('<div/>').mouseover(function(event){
				$(this).find("span:first").css('text-decoration','underline');
			}).mouseout(function(){
				$(this).find("span:first").css('text-decoration','none');
			}).addClass('u-file-btnzone').appendTo(waitingZone);
			var handView = $('<a href="javascript:void(0);" class="u-file-href-a"></a>');
			var fileInput = $('<input/>').attr('id', 'upload_file')
				.attr('type', 'file').attr('accept',accept)
				.css({'position':'absolute','float':'top','font-size':'118px','width':'100px','height':'100px','margin-top':'0','cursor': 'pointer','opacity':'0'})
				.appendTo(handView);
			handView.appendTo(buttonZone);
			$("<span class=\"u-file-href\" href=\"javascript:void(0)\"><img border=\"0\" src=\"images/upload/extra.png\" class=\"u-file-uploadedFile-img\"/>"+showTitle+"</span>").appendTo(buttonZone);
			ulButton = $('<img/>').addClass('ulButton').attr('src','images/upload/upload.png').attr('title','点击上传').css('display','none').click(upload).appendTo(waitingZone);
		}
		var fileInfoTable = $('<table id="upload_info_table" cellpadding="0" cellspacing="0" style="border-spacing:0;border-collapse:collapse;width:100%"></table>').appendTo(waitingZone);
		var fileInfoTableTr = $('<tr/>').addClass('upload_info_tr').appendTo(fileInfoTable);
		var fileInfoTableTd = $('<td/>').appendTo(fileInfoTableTr);
		var fileInfo = $('<div/>').attr('id', 'upload_info').attr('baseDiv',$(this).attr('id')).addClass('u-file-info').appendTo(fileInfoTableTd);//上传信息
		waitingZone.appendTo(fileDiv);
		
		var userSetting = {};
		userSetting.uploadkey = curID;
		userSetting.fileInfo = fileInfo;
		userSetting.upload = upload;
		userSetting.img = imgSet;
		userSetting.listMode = listMode;
//		userSetting.loadingImg = loadingImg;
		userSetting.singleMode = singleMode;
		userSetting.deleteMethod = deleteAll;
		userSetting.uploadedFiles = [];//记录已经上传的文件路径
		userSetting.parent = this;
		userSetting.waitingDiv = waitingZone;
		
		$(this).append(fileDiv).bind('delete',function(data,id){});//绑定删除事件
		$(this).bind('afterFun',function(){
			setting.uploadComplete&&setting.uploadComplete();
		});//绑定上传事件，上场完成后会调用该方法
		if(uploadFlag){
			fileInput.MultiFile({
				STRING: {
//					remove: '<img src="images/index/close.png" height="8" width="8" border="0" alt="删除"/>',
					denied:'您不能上传类型为$ext的文件.\n 请重新选择!'	
				}
			},userSetting);
		}
		return new ulPlugin(setting);
	}
	
	//客户端操作
	function ulPlugin(setting){
		return {
			checkStatus: function(){
				//是否可以上传
				return operateFlag;
			},
			waitingSum: function(){
				//是否有等待上传的
				var files = $(".MultiFile-wrap").children(":file[id^='upload_file'][value!='']");
				return files.length;//返回等待上传的数量
			},
			
			// 获取已上传的文件(返回[{id:1,fileName:战三},{id:45,fileName:李四}])
			getUploadedFiles: function() {
				var results = [];
				var files = $('#upload_info').find("span[id^='uploadedFile_']");
				if(files.length==0){
					return null;
				}
				files.each(function(i, cur) {
					cur  =$(cur);
					results[i] = {
						id : cur.attr("fileId"),
						fileName : cur.attr("fileName"),
						file : cur.data('file'),
						fileSize : cur.data('file').fileSize,
						fileType : cur.data('file').fileType,
						savePath : cur.data('file').savePath
					};
					if(cur.data('file').newTime){
						results[i].createTime = $(cur).data('file').newTime.getTime(); 
					}
				});
				return results;
			},
			
			// 获取已上传的文件ID
			getUploadedFilesId: function() {
				var results = [];
				var files = $('#upload_info').find("span[id^='uploadedFile_']");
				if(files.length==0){
					return null;
				}
				files.each(function(i, cur) {
							results[i] = $(cur).attr("fileId");
						});
				return results;
			},
			
			// 获得已上传的文件名
			getUploadedFilesName: function() {
				var results=[];
				var files = $('#upload_info').find("span[id^='uploadedFile_']");
				if(files.length==0){
					return null;
				}
				files.each(function(i, cur) {
							results[i] = $(cur).attr("fileName");
						});
				return results;
			},
			
			//传入ID显示之前上传过的文件,operate是否可以删除
			loadUploadedFiles: function(data, readOnly) {
				if(readOnly == undefined){
					readOnly = true;
				}
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
				if($('.u-file-waiting').data('history')== undefined){
						$('.u-file-waiting').data('history',[]);
				}
				SystemFileService.findMapByPropertiesQuick(['ID','fileName'],"id IN ("+condition+")",false,function(results){
					addFileShowInPage(results,readOnly);
				});
				var s=0;
			},
			
			//传入SystemFile对象数组，显示到列表中
			loadFiles:addFileShowInPage,
			emptyFiles:deleteAll,//删除文件
			emptyInfor:emptyInfor,//只删除显示部分
			emptyFile:deleteFile
		};
		
	}
	
	//上传文件
	function upload(key) {
		if(singleMode)
			deleteAll();
		var selectedFiles = [];
		var selectedFilesPath = [];
		var i = 0;
		var parentNode = $('#'+key);
		var files = parentNode.find(".MultiFile-wrap:first").children(":file[id^='upload_file'][value!='']");
		files.each(function(index, cur) {
			var curpath = cur.value;
			if (curpath != '') {
				selectedFilesPath[i] = curpath.match(/[^\/\\]+$/gi)[0];
				selectedFiles[i++] = cur;
			}
		});
		if ($(selectedFiles).length > 0) {
			$('.u-file-btnzone').mask();
			$.fn.MultiFile.disableEmpty();
			ulButton.unbind('click').removeAttr('onclick'); 
			ulButton.click(null);
			operateFlag = false;
			var id=new Date().getTime();
		
			SystemFileService.uploadFiles(selectedFiles, selectedFilesPath,function(data) {
				try{
					if(data!=null){
						$('.u-file-btnzone').unmask();
						
						//用于判断已经上传过的文件
						if($('.u-file-waiting').data('history')== undefined){
							$('.u-file-waiting').data('history',[]);
						}
						var temp = $('.u-file-waiting').data('history');
						for(var j=0;j<selectedFiles.length;j++){
							temp.push(selectedFiles[j].value);
						}
					    addFileShowInPage(data);
						ulButton.click(upload);
						operateFlag = true;
						$(".MultiFile-wrap").children(":file[id^='upload_file'][value!='']").each(
							function(i, cur) {
								$(cur).remove();
						});
						$('a .MultiFile-remove').click();
						$.fn.MultiFile.reEnableEmpty();
					}else{
						jAlert('上传失败!');
					}
				}catch(e){
					console.log(e);
				}
			});
		}
	}
	
	// 删除文件
	function deleteFile(id) {
			$('#upload_info').find("span[id^='uploadedFile_']").each(function(index,cur){
				if(cur.id == 'uploadedFile_'+id){
					var div = $('.u-file-waiting');
					var rel = [];
					var temp = div.data('history');
					for(var k=0;k<temp.length;k++){
						if(k!==index){
							rel.push(temp[k]);
						}
					}
					div.data('history',rel);
				}
			});
			SystemFileService.deleteFile(id, function(data) {
				$('#uploadedFile_' + id).remove();
					var temp = $('#upload_info');
					if($.trim(temp.text())==''){
						temp.css('display','none');
					}
			});
		/*
		 * confirm('确认要删除吗？',function(){
			
		});*/
	}
	
	// 删除所有
	function deleteAll(){
		$('.u-file-info').find("span[id^='uploadedFile_']").each(function(index,cur){
			var id = $(cur).attr('fileId');
			if(id){
				SystemFileService.deleteById(id,function(data){
					if(!data){
						jAlert('删除失败!');
					}
				});				
			}
		});
		emptyInfor();
	}
	
	//清空显示信息
	function emptyInfor(){
		var div = $('.u-file-waiting');
		var temp = div.data('history');
		if(undefined != temp){
			div.data('history',[]);//清空历史
		}
		$('.u-file-info').empty();//移除dom
	}
	
	// 下载文件
	function deloadFile(id) {
		SystemFileService.downloadFile(id, function(data) {
			if (data && data != null) {
				dwr.engine.openInDownload(data);
			} else {
				jAlert('文件不存在');
			}
		});
		return false;
	}
	
	//在页面里显示文件
	function addFileShowInPage(data, readOnly){
		if($('.u-file-waiting').data('history')== undefined){
			$('.u-file-waiting').data('history',[]);
		}
		
		if (data.length == 0)
			return;
		var fud = $('#upload_info');
		
		if(fud.css('display')!='block'){
			fud.css('display','block');
		}  
		fud.find("span[class='MultiFile-list-choose']").remove();
		$(data).each(function(i, cur){
			var img = $("<img border=\"0\" src=\""+imgSet+"\"/>").addClass('u-file-uploadedFile-img u-file-uploadedFile-imgCommon');
			var name = $('<a></a>').attr({'href':'javascript:void(0);','title':'点击下载 '+cur.fileName}).html(cur.fileName).addClass('u-file-href-a').addClass('u-file-uploadedFile-name').click(function(){deloadFile(cur.ID || cur.fileCode);});
			var filesize = $('<span/>').addClass('u-file-uploadedFile-filesize').html('&nbsp;&nbsp;('+fileSizeFormater(cur.fileSize)+')');
			var del = $('<a/>').attr({'href':'javascript:void(0)','title':'点击删除','id':'del-'+(cur.ID || cur.fileCode)}).css('margin-left','6px').addClass('u-file-href-a').addClass('u-file-href-deleteContent').html('删除').click(
        		function(){
        			deleteFile(cur.ID);
        			$(this).parent().parent().parent().parent().trigger('delete',cur.ID);
        		}
       		);
			var success = $('<img border="0" src="'+successImg+'"\>').addClass('u-file-uploadedFile-img').addClass('u-file-uploadedFile-delete').attr({'title':'上传完成','alt':'完成','align':'absmiddle'}).css('margin-left','6px');
			fileDiv = $('<span></span>').addClass('u-file-uploadedFile').attr({'id':'uploadedFile_'+cur.ID,'fileId':cur.ID,'fileName':cur.fileName}).data('file',cur);
			if(listMode){
				fileDiv.css('display','block');				
			} else {
				fileDiv.css('float','left');
			}
			if(showFileSize){
				fileDiv.append(img,name,filesize,success);				
			} else {
				fileDiv.append(img,name,success);
			}
			if(!readOnly){
				fileDiv.append(del);				
			}
			fud.append(fileDiv);
		});
		$('#'+fud.attr('baseDiv')).trigger('afterFun');
	}
	function fileSizeFormater(cellvalue){
		if (cellvalue && cellvalue < 1024)
			return (Math.round((cellvalue / 1024)*1000)/1000).toFixed(1) + "B";
		else if (cellvalue && cellvalue > 1024 && cellvalue < 1024 * 1024)
			return (Math.round((cellvalue / 1024) * 1000) / 1000).toFixed(1) + "KB";
		else if (cellvalue) {
			return (Math.round((cellvalue / (1024 * 1024)) * 1000) / 1000).toFixed(1) + "MB";
		} else {
			return "0";
		}
	}
})(jQuery);