<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,timepicker,date,util,commonStyle,addAndEdit,dropDownBox");
	ServiceLoader serviceLoader = new ServiceLoader(
			"CodeTableService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>新增</title>
		<link rel="stylesheet" type="text/css" href="js/plugins/fine-uploader/fine-uploader-gallery.css">
		<link rel="styleSheet" href="css/module/gameManager/gameAdd.css">
		<script type="text/javascript" src="js/util/StringUtil.js"></script>
		<script src="js/plugins/fine-uploader/fine-uploader.js"></script>
	</head>
	<body>
		<div id="dialog-modal" title="新增">
		<div style='border: solid 1 #AAAAAA; padding: 20px;'>
				<table width="90%">
					<tr>
						<td class="lableWord">竞赛名称</td>
						<td style="width: 170px;">
							<input class="inputEdit" type="text" id='name' field='modulename' placeholder="请输入竞赛名称" save='true'/>
						</td>
					</tr>
					<tr>
						<td class="lableWord">是否多阶段</td>
						<td>
							<select id="isMultiStage">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="lableWord">报名开始时间</td>
						<td>
							<input id="startTime" placeholder="请输入报名开始时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td class="lableWord">报名截止时间</td>
						<td>
							<input id="endTime" placeholder="请输入报名截止时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" value="" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td class="lableWord">参赛形式</td>
						<td>
							<select id="competitionType">
								<option value="0">团队</option>
								<option value="1">个人</option>
							</select>
							<div class="teamLimit">
								<input type="text" id="leastNumbe"> 人  到
								<input type="text" id="maxNumber"> 人
							</div>
						</td>
					</tr>
					<tr>
						<td class="lableWord">主办单位</td>
						<td>
							<input type="text" id="hostUnitName" placeholder="点击输入主办单位" readonly>
						</td>
					</tr>
					<tr>
						<td class="lableWord">承办单位</td>
						<td>
							<input type="text" id="secondUnitName" placeholder="点击输入承办单位" readonly>
						</td>
					</tr>
					<tr>
						<td class="lableWord">类型</td>
						<td>
							<select id="gameType">
								<option value="" selected>请选择类型</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="lableWord">级别</td>
						<td>
							<select id="level">
								<option value="" selected>请选择级别</option>
								<option value="0">国家级</option>
								<option value="1">省部级</option>
								<option value="2">校级</option>
								<option value="3">院级</option>
								<option value="4">其他</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="lableWord">上传活动封面</td>
						<td>
							<div id="fine-uploader-gallery"></div>
						</td>
					</tr>				
				</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/gameManager/gameAdd.js'></script>
	</body>
	<script type="text/template" id="hostUnitTemplate">
        <div class="panel-container">
            <header class="panel-title"><h3>新增单位</h3></header>
            <div class="panel-content">
                <ul>
                    <li>
                        <label>单位1:</label>
                        <input class="hostUnit" placeholder="请输入单位名称" style="margin-left: -3px;">
                        <button class="remove-item" title="删除" style="margin-left: -4px;">X</button>
                    </li>
                </ul>
                <button class="addNew">新增</button>
            </div>
            <footer class="panel-footer">
                <button class="panel-confirm">确定</button>
                <button class="panel-cancel">取消</button>
            </footer>
        </div>
	</script>
	<script type="text/template" id="qq-template">
    <div class="qq-uploader-selector qq-uploader" qq-drop-area-text="Drop files here">
        <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
            <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
        </div>
        <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
            <span class="qq-upload-drop-area-text-selector"></span>
        </div>
        <div class="qq-upload-button-selector qq-upload-button">
            <button>Upload a file</button>
        </div>
            <span class="qq-drop-processing-selector qq-drop-processing">
                <span>Processing dropped files...</span>
                <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
            </span>
        <ul class="qq-upload-list-selector qq-upload-list" aria-live="polite" aria-relevant="additions removals">
            <li>
                <div class="qq-progress-bar-container-selector">
                    <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
                </div>
                <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
                <span class="qq-upload-file-selector qq-upload-file"></span>
                <span class="qq-edit-filename-icon-selector qq-edit-filename-icon" aria-label="Edit filename"></span>
                <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="text">
                <span class="qq-upload-size-selector qq-upload-size"></span>
                <button type="button" class="qq-btn qq-upload-cancel-selector qq-upload-cancel">Cancel</button>
                <button type="button" class="qq-btn qq-upload-retry-selector qq-upload-retry">Retry</button>
                <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">Delete</button>
                <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
            </li>
        </ul>

        <dialog class="qq-alert-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">Close</button>
            </div>
        </dialog>

        <dialog class="qq-confirm-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">No</button>
                <button type="button" class="qq-ok-button-selector">Yes</button>
            </div>
        </dialog>

        <dialog class="qq-prompt-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <input type="text">
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">Cancel</button>
                <button type="button" class="qq-ok-button-selector">Ok</button>
            </div>
        </dialog>
    </div>
</script>
</html>