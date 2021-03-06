
var pageData={
	time1:0,
	time2:0,
	time3:0,
	time4:0,
	time5:"1464184800000"
}

$(function(){
	showStyle();
	
	
	dwr.engine.setAsync(false);
	GameStepService.checkTime(function(data){
    	if(data){
    		$(".register").show();
    	}else{
    		$(".register").hide();
    	}
    });
	dwr.engine.setAsync(true);
	if(type=="1"){
		$(".register").hide();
	}
    var $body = $('body');
    $('.page-index a').addClass('page-nav');
    $('.page-nav').live('click' , function(){
        var self = $(this);
        var href = self.attr('href');
        var targets = $(href);
        if(targets.length > 0){
            $body.animate({scrollTop: targets.offset().top}, 500);
            if($.browser.safari){
                return false;
            }
        }else if(href == '#'){
            $body.animate({scrollTop: 0}, 500);
            if($.browser.safari){
                return false;
            }
        }else{
            $(".page-index").hide();
            $('.openPage').css('display','inline-block');
        }
    });
    $('.openPage').live('click', function(){
        $(".page-index").show();
        $('.openPage').css('display','none');
    });
    
    getTime();
    showTime();
    setInterval(showTime,0);
    if(userName == null || userName == "" || userName == "null"){
    	$("#login-info").html("登录");
    	$("#login-info").attr("href",basePath + "module/common.jsp?info=login");
    	$("#logins").show();
    	$('.go-out').hide();
    }else{
    	$("#login-info").html(userName + "");
    	$("#login-info").attr("href",basePath + "module/personalCenter/personalManager.jsp?info=userInfo");
    	$("#logins").hide();
    	$('.go-out').show();
    }
    
 });

function showStyle(){
	  var tpl = "<div class=\"page page-index-box\">"
        +"<span class=\"nav-title\">导航</span>"
        +"<ul class=\"page-index\">"
        +    "{@each links as l}"
            +"<li>"
             +   "<a href=\"#${l.id}\" title=\"${l.title}\">${l.title} </a>"
           + "</li>"
            +"{@/each}"
            +"<li class=\"to-top\">"
               + "<a href=\"#\">返回顶部</a>"
            +"</li>"
            +"<li class=\"close-page-index\">"
                +"<a href=\"javascript:;\">关闭</a>"
            +"</li>"
       +" </ul>"
        +"<span class=\"openPage\">展开</span>"
    +"</div>"
    +"<a href=\""+basePath+"module/common.jsp?info=enroll\"><img src=\"images/register.png\" class=\"register\"/></a>";
    var data = (function(){
        var exports = {
            links: []
        };
        $('section').each(function(){
            var self = $(this);
            var id = self.attr('id');
            var title = self.find('h1').text();
            exports.links.push({
                id: id,
                title: title
            });
        });
        return exports;
    })();
    $('#container').append($(juicer(tpl, data)));
    
    $(".go-out").click(function(){
    	UserService.quit(function(data){
    		location.href = "";
    	});
    });
}

function loadFile(url){
	window.open(basePath+"file/"+url);
}

function showTime(){
	var show1=setWarnTime(pageData.time1);
	var show2=setWarnTime(pageData.time2);
	var show3=setWarnTime(pageData.time3);
	var show4=setWarnTime(pageData.time4);
	var show5=setWarnTime(pageData.time5);
	//alert(pageData.time1+"  "+pageData.time2)
	if(show1!=""){
		document.getElementById("timeWarn1").innerHTML="<h2>创意阶段</h2><p>报名截止时间还有<span class='timeWarn'>&nbsp;"+show1+"</span>,文档提交截止时间还有<span class='timeWarn'> &nbsp;"+show2+"</span></p>";
	}
	else if(show2!=""){
		document.getElementById("timeWarn1").innerHTML="<h2>创意阶段</h2><p>报名已截止,文档提交截止时间还有<span class='timeWarn'> &nbsp;"+show2+"</span></p>";
	}
	if(show3!=""){
		document.getElementById("timeWarn2").innerHTML="<h2>作品阶段</h2><p>报名截止时间还有<span class='timeWarn'> &nbsp;"+show3+"</span>,文档提交截止时间还有<span class='timeWarn'> &nbsp;"+show4+"</span></p>";
	}
	else if(show2!=""){
		document.getElementById("timeWarn2").innerHTML="<h2>作品阶段</h2><p>报名已截止,文档提交截止时间还有<span class='timeWarn'> &nbsp;"+show4+"</span></p>";
	}
	if(show5!=""){
		document.getElementById("timeWarn1").innerHTML="<h2>创意阶段决赛</h2><p>决赛文档提交截止时间还有<span class='timeWarn'> &nbsp;"+show5+"</span></p>";
	}
}

function getTime(){
	dwr.engine.setAsync(false);
	GameStepService.getTime(function(data){
		for(var i=0;i<data.length;i++){
	
			pageData.time1=data[i].Time1;
			pageData.time2=data[i].Time2;
		
			pageData.time3=data[i].Time3;
			pageData.time4=data[i].Time4;
			
		}
	})
	dwr.engine.setAsync(true);
}

function setWarnTime(time){
	var nowTime=new Date().getTime();
	var t =time - nowTime;
	var d=Math.floor(t/1000/60/60/24);
    var h=Math.floor(t/1000/60/60%24);
    var m=Math.floor(t/1000/60%60);
    var s=Math.floor(t/1000%60);
    var showTime="";
    if(d>0){
    	showTime+=d+"天"+h+"时"+m+"分"+s+"秒";
    	
    }
    else if(h>0){
    	showTime+=h+"时"+m+"分"+s+"秒";
    }
    else if(m>0){
    	showTime+=m+"分"+s+"秒";
    }
    else if(s>0){
    	showTime+=s+"秒";
    }
 	return showTime;
}
