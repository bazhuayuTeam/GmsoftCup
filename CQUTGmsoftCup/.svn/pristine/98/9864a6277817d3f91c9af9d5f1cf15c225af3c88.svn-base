//<script src="js/util/JsonUtil.js"></script>

//当对象中存在自引用时（亦即对象中存在环时）,将会产生堆栈溢出错误,因为死循环.注意使用
//使用ShowInNewFrame函数时，可能会产生800a001c错误
//	eg:
//		var obj={};
//		obj.self = obj;
//大对象可嵌套多层
//主要的处部调用接中,可将js中的大对象alert出来

var JsonUtil = {};
JsonUtil.NEWLINESYMBOL = "\n";
JsonUtil.SPACER = "\t";
JsonUtil.alertJson = function(obj)
{
	alert(JsonUtil.jsonToStr(obj));
}
//将大对象的内容和类型处理为字符串
JsonUtil.jsonToStrByStep = function (obj,n,step)
{
//	for (i =0 ; i < numargs; i++)
//	{      // 获取参数内容。
//	   s += "  Arg " + i + " = " + arguments[i] + "\n";
//	}
	
	if(!n || typeof(n) != "number")
	{	
		n=0;
	}
	if(!step || typeof(step) != "number")
	{	
		step=1;
	}
	
	var tableStr = "";
	for ( var i = 0; i < n; i++)
	{
		tableStr += JsonUtil.SPACER;
	}
	
	var typeStr = typeof(obj);
	if(!obj)
	{
		return  tableStr+obj+JsonUtil.NEWLINESYMBOL;
	}
	else if (typeStr == "string" ||typeStr == "number"||typeStr=="boolean")
	{
		return tableStr+obj+JsonUtil.NEWLINESYMBOL;	
	}else if(typeStr == "function")
	{
		return  tableStr+typeStr+":"+JsonUtil.NEWLINESYMBOL+tableStr+"{"+JsonUtil.NEWLINESYMBOL+tableStr+JsonUtil.SPACER+obj+JsonUtil.NEWLINESYMBOL+tableStr+"}"+JsonUtil.NEWLINESYMBOL;
	}
	else if (typeStr != "object")
	{
		return  tableStr+typeStr+":"+JsonUtil.NEWLINESYMBOL+tableStr+"{"+JsonUtil.NEWLINESYMBOL+tableStr+JsonUtil.SPACER+obj+JsonUtil.NEWLINESYMBOL+tableStr+"}"+JsonUtil.NEWLINESYMBOL;
	} else
	{
		for ( var index in obj)
		{
			typeStr = typeof(obj[index]);
			jsonStr += tableStr+typeStr+":"+index+JsonUtil.NEWLINESYMBOL;
		}
		return jsonStr;
	}
	
}
JsonUtil.jsonToStr = function (obj, n,limit)
{
	
	if(!limit || typeof(limit) != "number")
	{
		limit = 4;
	}
	if(!n || typeof(n) != "number")
	{	
		n=0;
	}
	
	var tableStr = "";
	for ( var i = 0; i < n; i++)
	{
		tableStr += JsonUtil.SPACER;
	}
	if(n >= limit)
	{
		return tableStr+"超出深度限制"+JsonUtil.NEWLINESYMBOL;
	}
	var jsonStr = "";
	
	var typeStr = typeof (obj);
	if(!obj)
	{
		return  tableStr+obj+JsonUtil.NEWLINESYMBOL;
	}
	else if (typeStr == "string" ||typeStr == "number"||typeStr=="boolean")
	{
		return tableStr+obj+JsonUtil.NEWLINESYMBOL;	
	} else if (typeStr != "object")
	{
		return  tableStr+typeStr+":"+JsonUtil.NEWLINESYMBOL+tableStr+"{"+JsonUtil.NEWLINESYMBOL+tableStr+JsonUtil.SPACER+obj+JsonUtil.NEWLINESYMBOL+tableStr+"}"+JsonUtil.NEWLINESYMBOL;
	} else
	{
		for ( var index in obj)
		{
			typeStr = typeof(obj[index]);
			jsonStr += tableStr+typeStr+":"+index+JsonUtil.NEWLINESYMBOL+tableStr+"{"+JsonUtil.NEWLINESYMBOL+JsonUtil.jsonToStr(obj[index],n+1)+tableStr+"}"+JsonUtil.NEWLINESYMBOL;
		}
		return jsonStr;
	}
}

//此方法将obj用IFrame显示出业,一些细节未处理
JsonUtil.showInNewFrame = function(obj)
{
	var ow =window.open ('','newwindow','height=600,width=400,top=10,left=10,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=yes') ;
	ow.document.write("<BODY>")
	//var str = JsonUtil.jsonToStr(obj);
	//str = str.replace(/\n/g, "\n<br />", false);
	//str = str.replace(/\t/g, "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", false)
	JsonUtil.NEWLINESYMBOL = "\n<br />";
	JsonUtil.SPACER = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	var str = JsonUtil.jsonToStr(obj);
	JsonUtil.NEWLINESYMBOL = "\n";
	JsonUtil.SPACER = "\t";
	ow.document.write(str);
	ow.document.write("</BODY>");
	ow.document.close();
}

//此方法在浏览器上权限不足......//不能存在C盘
//将指定的对象的内容存储到本地文件上.
//因为,对象内容过长时,alert方法不能很好的帮助查看对象内容
//适用浏览器:暂时只支持ie
//如果不传path则默认为桌面
JsonUtil.saveToTxtFile = function(obj,path)
{
	if(path == undefined)
	{
		var wsh = new ActiveXObject("wscript.shell") ;
		var path = wsh.SpecialFolders("Desktop");
		path = path.replace("\\","\\\\",true);
		path += "\\json.txt";
		alert(path);
	}
//	创建一个FileSystemObject对象实例:fso,
	var fso = new ActiveXObject("Scripting.FileSystemObject");
//	使用CreateTextFile方法创建一个
	var file = fso.createtextfile(path,true);
	JsonUtil.NEWLINESYMBOL = "\r\n";
	JsonUtil.SPACER = "\t";
	var str = JsonUtil.jsonToStr(obj);
	JsonUtil.NEWLINESYMBOL = "\n";
	JsonUtil.SPACER = "\t";
	file.WriteLine(str);
	file.Close();
}