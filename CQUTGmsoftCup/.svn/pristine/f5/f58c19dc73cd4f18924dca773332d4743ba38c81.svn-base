<%@ page language="java" import="java.util.*,com.cqut.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader("dwr");
	ServiceLoader serviceLoader=new ServiceLoader("UserService,GameStepService");
	
	String userName = (String) request.getSession().getAttribute(OperatorUtil.OPERATOR_NAME);
	Integer roleType = (Integer) request.getSession().getAttribute(OperatorUtil.OPERATOR_TYPE);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>"/>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
<title>2016年重庆理工大学“大家杯”软件创新、创意大赛</title>
<meta name=”renderer” content=”webkit”>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>

<div id="container">
    <div class="header-wrap">
        <div class="logo"><img src="images/logo.png" /></div>
        <div class="login"><img src="images/login.png"/>
            <div class="info"><a id="login-info" href="<%=basePath%>module/common.jsp?info=login">登录</a><span></span><a id="logins" href="<%=basePath%>module/common.jsp?info=register">注册</a><lable class="go-out" style="display:none;">注销</lable></div></div>
    </div>

    <div id="content">
        <section id="news">
            <h1>最新动态</h1>
     		<h2 class="title">倒计时</h2><br><br>
     		<p id="timeWarn1"></p>
     		<p id="timeWarn2"></p>
     		<br/>
            <h2 class="title">创意阶段决赛</h2><br><br>
            <p>截止5 月19 日，重庆理工大学“大家杯”软件创意、创新大赛的创意阶段作品决赛19 强名单终于
				诞生了，即将在第13 周迎来终极PK，让我们拭目以待！另外决赛文档提交截止时间是5月25日晚10点！具体内容请查看
				<a target="_blank" class="page-nav prize-btn" href="<%=basePath%>details.html">更多详情</a>
			</p>
           	<h2 class="title">作品阶段</h2><br><br>
            <p>可演示的软件作品和提交项目使用说明书。最后注册、提交时间2016年5月29日下午6点整，决赛作品提交时间为2016年6月26日下午6点整。</p>
            <h2 class="title">关于报名</h2><br><br>
            <p>网上报名完成后，需要提交网络报名表的纸质打印材料到大赛组委会处，才算报名完成。</p>
        </section>
        <section id="intro">
            <h1>大赛宗旨</h1>
            <p>“梦想总是要有的，万一实现了呢？”（马云）。每个同学都有梦想，都怀着青春之梦踏进了美丽的重庆理工大学。今天，在这里借此比赛，我们号召同学们继续“梦想”，让我们借助这项大赛载体，帮助你们踏上梦想的“实现”之路。同学们，来吧！</p>
            <p>“软件定义世界”已成为当今社会发展趋势，工业化、信息化、现代化都离不开软件。在我校校友及其企业“重庆港澳大家软件产业有限公司”的大力支持下，通过学校教育发展基金会，由教务处主办、计算机科学与工程学院承办的2016年“大家杯”软件创意创新大赛现已正式开始。</p>
            <p>本次赛事致力于引导我校学生积极参加基于软件载体的创新活动，增强学生们的自我创新能力和实际动手能力，为同学们进一步参与市级、国家级和国际级比赛，以及其他“双创”活动储备项目、储备人才或储备团队。</p>
        </section>

        <section id="schedule">
            <h1>大赛流程</h1>
            <h2>参赛对象</h2>
            <p> 本项大赛面向我校所有在校本专科学生和研究生，鼓励跨专业、跨年级组队。</p>
            <h2>作品要求</h2>
            <p>创意阶段：提交软件项目策划书。</p>
            <p>作品阶段：可演示的软件作品，提交项目使用说明书。</p>
            <h2>竞赛详情</h2>
            <p>每项子赛都分为初赛和决赛2个阶段，初赛以网评为主，决赛以网评为辅、现场答辩为主。准许决赛阶段参赛团队提交更新后的成果参赛，但不能更改题目和实现目标。</p>
            <p>决赛队伍及其比赛时间，至少提前一周发出通知。</p>
        </section>

        <section id="prize">
            <h1>大赛奖项</h1>
            <h2 class="title">创意阶段</h2><br><br>
            <table class="table">
                <thead>
               <tr>
                    <th>奖项</th>
                    <th>奖励</th>
                    <th>获奖数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>特等奖</td>
                    <td>3000元、获奖证书</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>一等奖</td>
                    <td>1000元、获奖证书</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>二等奖</td>
                    <td>300元、获奖证书</td>
                    <td>10-12</td>
                </tr>
                <tr>
                    <td>三等奖</td>
                    <td>获奖证书</td>
                    <td>以参赛队伍的总数为基数，以获奖队伍达到参赛队伍总数的30%左右为标准，予以计算</td>
                </tbody>
            </table>
            <h2 class="title">作品阶段</h2><br><br>
            <table class="table">
                <thead>
                <tr>
                    <th>奖项</th>
                    <th>奖励</th>
                    <th>获奖数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>特等奖</td>
                    <td>10000元、获奖证书</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>一等奖</td>
                    <td>5000元、获奖证书</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>二等奖</td>
                    <td>1000元、获奖证书</td>
                    <td>8-10</td>
                </tr>
                <tr>
                    <td>三等奖</td>
                    <td>获奖证书</td>
                    <td>以参赛队伍的总数为基数，以获奖队伍达到参赛队伍总数的30%左右为标准，予以计算</td>
                </tbody>
            </table>
            <h2 class="title">特别说明</h2><br><br>
            <p>本次大赛得到重庆港澳大家软件公司的大力支出，全部奖金由重庆港澳大家软件公司通过重庆理工大学教育发展基金会赞助！</p>
            <p>重理工计算机学院及其大赛组委会为最终解释单位，本大赛的裁判终审权属单位为本大赛的组委会。</p>
        </section>
        
		 <section id="fileManage">
            <h1>相关文档</h1>
            <h2>大家杯网络平台使用说明</h2>
            <p><a class="loadFile" onclick="loadFile('aboutWeb.docx')">下载大家杯网络平台使用说明文档 </a></p>
            <h2>大赛报名表</h2>
            <p><a class="loadFile" onclick="loadFile('07145927_.doc')">2016年重庆理工大学‘大家杯’软件创意创新大赛报名表 </a></p>
        </section>
        
        <section id="contact">
            <h1>大赛组织</h1>
            <table class="table">
                <tr>
                    <th>咨询方式</th>
                    <td>比赛群QQ号：431549692(让梦想找家)</td>
                </tr>
                <tr>
                    <th>组织单位</th>
                    <td>教务处</td>
                </tr>
                <tr>
                    <th>主办单位</th>
                    <td>计算机科学与工程学院、重庆理工大学教育发展基金会</a>
                    </td>
                </tr>
                <tr>
                    <th>赞助单位</th>
                    <td><a href="http://www.gmsoft.com.cn/Chinese/index.asp">重庆港澳大家软件产业有限公司</a></td>
                </tr>
                <tr>
                    <th>协办单位</th>
                    <td>计算机科学与工程学院学生会科协、重理工星云智慧空间、软件工程系、期刊社</td>
                </tr>
                <tr>
                    <th>大赛组委会</th>
                    <td>由主办单位、承办单位和赞助单位等机构组成，计算机学院负责具体的比赛推动工作和组委会筹建。评委会专家包含校内、校外的IT专家、营销专家、策划专家。</td>
                </tr>

            </table>
        </section>
    </div>
</div>

</body>
<script src="js/jquery.js"></script>
<script src="js/juicer.js"></script>
<script type="text/javascript">
	var userName = '<%=userName%>';
	var type = '<%=roleType%>';
	var basePath = '<%=basePath%>';
</script>
<script src="js/index.js"></script>
</html>
