<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader = new JQueryLoader("dwr,dialog,suggest,timepicker,util,date,validate");
String operator=(String)request.getSession().getAttribute("operatorId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow-x;hidden">
  <head>
  	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
    <base href="<%=basePath%>"/>
    <%=loader.getCssFilesStr()%>
    <%=loader.getJsFilesStr()%>
   <style type="text/css">
   		form {
   			padding : 10px;
   		}
   		fieldset {
   			min-height : 50px;
   			margin-bottom : 10px;
   			text-align : left;
   		}
   		
   		fieldset legend {
   			color : #2e8bd9;
   		}
   		
   		.buttonDiv {
   			position : absolute;
   			bottom : 10px;
   			right : 10px;
   		}
   		
   		.buttonDiv button {
   			width : 60px;
   			height : 30px;
   		}
   		.timeSelector {
   			width:120px;
   		}
   		.ui-datepicker-trigger{
   			margin-left:-3px;
   			padding-left:0px;
   		}
	</style>
	<script type="text/javascript">
      var basePath = "<%=basePath%>";
      var operator="<%=operator%>";
    </script>
	 <script type="text/javascript" src="js/util/DateUtil.js"></script>
	 <script type='text/javascript' src='dwr/interface/OperatorService.js'></script>
	 <script type='text/javascript' src='dwr/interface/GraduationProjectInfoService.js'></script>
 	 <script type='text/javascript' src='dwr/interface/TermService.js'></script>
  </head>
  
  <body style='height:90%;'>
    <form id="settingForm" class="entityForm" style='height:100%;'>
      <fieldset id="newsRecord">
      	<legend> 信息发送策略 </legend>
      	<div>
      		<span class="sm-nrd-content-div-span">策略类型：</span>
		    <input type='radio' value='1' name='sm_radio'/><span style='margin-right:10px;'>及时发送</span>
			<input type='radio' value='2' name='sm_radio'/><span style='margin-right:10px;'>定时发送</span>
			<input type='radio' value='3' name='sm_radio'/><span style='margin-right:10px;'>循环发送</span>
			<div id="divSendWay" >
			<br/>
			<span class="sm-nrd-content-div-span">发送方式：</span>
		    <input type='radio' value='1' name='way_radio'/><span style='margin-right:10px;'>一 对 一</span>
			<input type='radio' value='2' name='way_radio'/><span style='margin-right:10px;'>一 对 多</span><br/>
			</div>
			<div id = "showTimeChooser1" style = "margin-top:10px;display:none;float:left;">
				<span>发送时间:</span>
				<input type = "text" id = "timeChooser1" readonly="readonly" class = "timeSelector" style = "margin-left:10px;"/>
				<img title = "日期选择" class = "ui-datepicker-trigger" alt="日期选择" src = "images/calendar.gif"/>
			</div>
			<div id = "showTimeChooser2" style = "display:none;float:left;margin-top:10px;">
				<span style = "float:left;margin-top:10px;">开始发送时间:</span>
				<span style = "float:left;margin-top:10px;">
					<input type = "text" id = "timeChooser2" class = "timeSelector" style = "margin-left:10px;"/>
					<img title = "日期选择" class = "ui-datepicker-trigger" alt="日期选择" src = "images/calendar.gif"/>
				</span>
				<span style = "float:left;clear:left;margin-top:10px;">每隔：</span>
				<span style = "float:left;margin-top:10px;"><input type = "text" value="1" id = "circleIntervalTime" style = "width:20px;height:14px;margin-left:10px;"/></span>
				<span style = "float:left;margin-top:10px;">
					<select style = "float:left;margin-left:5px;" id = "circleIntervalUnit">
						<option value = "day">天</option>
						<option value = "hour">小时</option>
					</select>
				</span>
				<span style = "float:left;margin-top:10px;margin-left:3px;">发送一次</span>
				<span style = "float:left;clear:left;margin-top:10px;">循环次数:</span>
				<span style = "float:left;margin-top:10px;"><input id = "circleCount" type = "text" value="1" style = "width:20px;height:14px;margin-left:10px;"/></input></span>
				<span style = "float:left;margin-top:10px;margin-left:3px;">次</span>
			</div>
      	</div>
      </fieldset>
      <fieldset style='display: none;'>
          <legend> 信息发送内容</legend>
          <div id="checkBoxDiv">
           	<textarea id="Editor" style="width: 90%; height: 200px;"></textarea>
          </div>
       </fieldset>
	</form>
	<div class="buttonDiv">
		<button id="button_ok" onclick="okAndClose();">确定</button>
		<button id="button_cancel" onclick="cancelAndClose();">取消</button>
	</div>
	<script type="text/javascript">
		var pageData={}, 
		selectedRadioVal = 0,	//1:及时发送 2:定时发送 3:循环发送
		datetimeOption = {		//日期选择器参数
			hourGrid: 4,
			minuteGrid: 10,
			showButtonPanel: true,
			changeMonth: true,
			changeYear: true,
			buttonText: '日期选择' ,
			buttonImageOnly: true,
			yearRange: '2010:2050',
			closeText: '关闭',
			currentText: '今天',
			dateFormat: 'yy-mm-dd',
			dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
			dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
			gotoCurrent: true,
			monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			nextText: '下个月',
			prevText: '上个月',
			minDate: new Date(),
			minTime: new Date()
		};
		
		$(function(){
			$(".timeSelector").datetimepicker(datetimeOption);
			$(".ui-datepicker-trigger").click(function(event){
				var previousObj = $(this).prev();
				previousObj.focus();
			})
			if(ChildDialogUtil.getExchangeData){
				pageData = ChildDialogUtil.getExchangeData();
				initForm(pageData);
			}
			DialogUtil.getTopWindow().onblur=function(){
				this.self.focus();
			};
			//给radio加onclick事件
			$("input[name='sm_radio']").click(function(obj) {
				if(selectedRadioVal != $(this).val()) {
					selectedRadioVal = $(this).val();
					doClickRadio(selectedRadioVal);
				}
			}); 
		});
		
		//初始化页面数据
		function initForm(data){
			var type = data.strategyType;
			if(type){
				$("input[name='sm_radio'][value='"+data.strategyType+"']").attr("checked","checked");
				if(type == 2) {	//如果是定时发送
					$("#showTimeChooser1").css("display","block");
					if(data.timeTime) {
						$("#timeChooser1").val(data.timeTime);
					}
				}
				else if(type == 3) {	//如果是循环发送
					$("#showTimeChooser2").css("display","block");
					if(data.circleStartTime) {
						$("#timeChooser2").val(data.circleStartTime);
					}
					if(data.circleIntervalTime) {
						$("#circleIntervalTime").val(data.circleIntervalTime);
					}
					if(data.circleIntervalUnit) {
						$("#circleIntervalUnit>option[value='" + data.circleIntervalUnit + "']").attr("selected","selected");
					}
					if(data.circleCount) {
						$("#circleCount").val(data.circleCount);
					}
				}
				if(data.model == 2){
					if(data.sendWay){
						$("input[name='way_radio'][value='"+data.sendWay+"']").attr("checked","checked");
					}else{
						$("input[name='way_radio'][value='"+1+"']").attr("checked","checked");
					}
				}else{
					$("#divSendWay").hide();
				}
			}else{//默认选中第一个
				$("input[name='sm_radio'][value='"+1+"']").attr("checked","checked");
			}
		}
		
		//点击确定关闭窗口
		function okAndClose(){
			if(validateThisPage()) {
				var result = {};
				result.strategyType = $("input[name='sm_radio']:checked").val();//把选择的发送策略放在结果集中
				result.timeTime = $("#timeChooser1").val();	//定时发送的时间
				result.circleStartTime = $("#timeChooser2").val();	//循环发送开始时间
				result.circleIntervalTime = $("#circleIntervalTime").val();	//循环发送间隔时间
				result.circleIntervalUnit = $("#circleIntervalUnit").val();	//循环发送间隔时间单位
				result.circleCount = $("#circleCount").val();	//循环发送次数
				if(pageData.model == 2){
					result.sendWay = $("input[name='way_radio']:checked").val();//发送方式
				}
				DialogUtil.getTopWindow().close();
				ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
			}
		}
		
		//点击取消关闭窗口
		function cancelAndClose(){
			DialogUtil.getTopWindow().close();
			ChildDialogUtil.doClose(event.data.type, {});
		}
		
		//对本页面的数据进行验证
		function validateThisPage() {
			var curSelectedRadio = +selectedRadioVal;
			switch(curSelectedRadio) {
				case 1: break;
				case 2: 
					var timeTime = $("#timeChooser1").val();
					if(!timeTime) {
						alert("请输入发送时间！");
						$("#timeChooser1").focus();
						return false;
					}
					break;
				case 3: 
					var circleStartTime = $("#timeChooser2").val();
					if(!circleStartTime) {
						alert("请输入开始发送时间！");
						$("#timeChooser2").focus();
						return false;
					}
					var circleIntervalUnit = $("#circleIntervalUnit").val();
					var circleIntervalTime = $("#circleIntervalTime").val();
					var circleCount = $("#circleCount").val();
					if(!circleIntervalTime) {	//如果没有填间隔时间
						alert("请输入间隔时间！");
						$("#circleIntervalTime").focus();
						return false;
					}
					else {
						if(circleIntervalUnit == 'day' && !/^\d+$/.test(circleIntervalTime)) {
							alert("请输入正确的间隔时间！");
							$("#circleIntervalTime").focus();
							$("#circleIntervalTime").select();
							return false;
						}
						else if(circleIntervalUnit == 'hour' && !/^\d+$/.test(circleIntervalTime)) {
							alert("请输入正确的间隔时间！");
							$("#circleIntervalTime").focus();
							$("#circleIntervalTime").select();
							return false;
						}
					}
					if(!circleCount) {
						alert("请输入发送次数！");
						$("#circleCount").focus();
						return false;
					}
					else if(!/^\d+$/.test(circleCount)) {
						alert("请输入正确的发送次数！");
						$("#circleCount").focus();
						$("#circleCount").select();
						return false;
					}
					break;
			}
			return true;
		}
		
		//处理radio的onclick事件
		function doClickRadio(val) {
			val = +val;	//将val字符串转化为数字
			switch(val) {
				case 1: 
					$("#showTimeChooser1").css("display","none");
					$("#showTimeChooser2").css("display","none");
					break;
				case 2: 
					$("#showTimeChooser1").css("display","block");
					$("#showTimeChooser2").css("display","none");
					break;
				case 3:
					$("#showTimeChooser1").css("display","none");
					$("#showTimeChooser2").css("display","block");
					break;
			}
		}
	</script>
  </body>
</html>
