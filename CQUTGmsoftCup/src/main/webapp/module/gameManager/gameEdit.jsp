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
			"jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,timepicker,date,util,file,commonStyle,addAndEdit,dropDownBox");
	ServiceLoader serviceLoader = new ServiceLoader(
			"CodeTableService,GameService,SystemFileService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>编辑</title>
		<link rel="stylesheet" type="text/css" href="js/plugins/fine-uploader/fine-uploader-gallery.css">
		<link rel="styleSheet" href="css/module/gameManager/gameAdd.css">
		<script type="text/javascript" src="js/util/StringUtil.js"></script>
	</head>
	<body>
		<div id="dialog-modal" title="编辑">
		<div style='border: solid 1 #AAAAAA; padding: 20px;'>
				<table width="90%">
					<tr>
						<td class="lableWord">竞赛名称</td>
						<td style="width: 170px;">
							<input class="inputEdit" type="text" id="gameName" field='modulename' placeholder="请输入竞赛名称" save='true'/>
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
					<tr class="forStage">
						<td class="lableWord">报名开始时间</td>
						<td>
							<input id="startTime" placeholder="请输入报名开始时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr class="forStage">
						<td class="lableWord">报名截止时间</td>
						<td>
							<input id="endTime" placeholder="请输入报名截止时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" value="" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr class="forStage">
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
							<button id="propagandaPath">点击上传</button>
						</td>
					</tr>				
				</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/gameManager/gameEdit.js'></script>
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
	<script type="text/template" id="imageGroupTemplate">
        <div class="panel-container-image">
            <header class="panel-title"><h3>上传宣传图片</h3></header>
            <div class="panel-content">
                <ul class="image-group-list">
                    <li>
                       	<img id="imageClick" src="images/uploadFile.png" width=100 height=80>
						<div id="imageUpload"></div>
                    </li>
                </ul>
            </div>
            <footer class="panel-footer">
                <button class="panel-confirm">确定</button>
                <button class="panel-cancel">取消</button>
            </footer>
        </div>
	</script>
	<script type="text/template" id="imageItemTemplate">
		<li>
             <img class="uploadImage" src="{{imageSrc}}" data-id="{{imageID}}" width=100 height=80>
             <ul class="image-options hidden">
		          <li class="options-selected" onclick="setIndexImage(this)">设为封面</li>
		          <li onclick="uploadAgain(this)">重新上传<div class="imageUploadAgain" id="imageUploadAgain{{uploadAgainID}}"></div></li>
		          <li onclick="removeImage(this)">删除图片</li>
		     </ul>
        </li>
	</script>
</html>