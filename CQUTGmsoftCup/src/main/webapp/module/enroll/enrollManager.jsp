<%@ page language="java" import="java.util.*,com.cqut.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	JQueryLoader loader=new JQueryLoader("dwr");
	ServiceLoader serviceLoader=new ServiceLoader("TeamService,GameStepService");

	String userName = (String) request.getSession().getAttribute("operatorName");
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	Integer roleType = (Integer) request.getSession().getAttribute(OperatorUtil.OPERATOR_TYPE);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <title>在线报名</title>
    <link rel="stylesheet" href="css/common.css"/>
	<base href="<%=basePath%>"/>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
	<link rel="stylesheet" href="css/jquery-labelauty.css"/>
	<script src="js/module/enroll/jquery-1.8.3.min.js"></script>
	<script src="js/module/enroll/jquery-labelauty.js"></script>
	<style type="text/css">
		p{
			text-indent:2em
		}
	</style>
  </head>
 	<body>
		<div class="header">
		    <h1>大赛报名</h1>
		    <hr class="title"></hr>
		</div>
<div class="center" style="height:400px">
    <form class="form-f">
        <ul>
            <li>
                <label>参赛方式：</label>
                <ul class="dowebok">
                    <li><input type="radio" value="0" name="mode" data-labelauty="团队" Checked=""/></li>
                    <li><input type="radio" value="1" name="mode" data-labelauty="个人"/></li>
                </ul>
            </li>
            <li>
                <label>竞赛类别：</label>
                <select id="classification"></select>
            </li>
            <li>
                <label>团队名称：</label>
                <input name="teamName" type="text" class="input" maxlength="10"/>
                <div class="warn" id="warn-name"></div>
            </li>
            <li>
                <label>参赛题目：</label>
                <input name="teamTitle" type="text" class="input" maxlength="30"/>
                <div class="warn" id="warn-title"></div>
            </li>
        </ul>

        <div class="commit">
            <input type="button" value="确定" class="submit bule" onclick="enroll()"/>
            <input type="button" value="取消" class="submit xxx" onclick="quit()"/>
        </div>
    </form>

    <div class="word" style="width:300px;">
        <h1 style="color:red;font-size: 18px;">特别提示（报名之前请认真阅读）：</h1>
        <p>1、创建小组时，创建者默认为团队组长且无法更改;</p>
        <p>2、在同一阶段，一个人只能报名一个团队。但能同时报名一个团队和个人（如：您已在创意阶段担任组长或其他组的组员，您将不能再创建创意阶段的小组。但是您还能报名创意阶段的个人赛）；</p>
        <p>3、创建团队后仅提供解散团队功能,报名时请慎重填写参赛信息；另外管理员审核通过之后，无法再解散团队；</p>
        <p>4、小组中，只有组长能进行添加组员等相关操作。组员不能自行退出小组，如需退组，请自行和组长商议。</p>
    </div>
</div>

<script src="js/module/enroll/enroll.js" />
<script type="text/javascript">
	var userName = '<%=userName%>';
	var operatorId = '<%=operatorId%>';
	var roleType = '<%=roleType%>';
	if(roleType=="1"){
		alert("只有学生能参赛！");
		window.parent.childLink("enrollPass");
	}
</script>
</body>
</html>
