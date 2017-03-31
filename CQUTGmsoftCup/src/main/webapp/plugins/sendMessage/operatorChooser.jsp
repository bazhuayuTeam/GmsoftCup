<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,dialog,dwr,ztree3.0,watermark,util");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<title>选择人员</title>
		<meta http-equiv="X-UA-Compatible" content="IE=100"/> <!-- IE8 mode -->
<style type="text/css">
fieldset .removeDiv a:HOVER{text-decoration:underline;color:red;}
#clearAll a:HOVER{text-decoration:underline;color:red;}
.rowChange{background: red;}
</style>
<script type='text/javascript' src='dwr/interface/OrganizationService.js'></script>
<script type='text/javascript' src='dwr/interface/OperatorService.js'></script>
<script type='text/javascript' src='dwr/interface/CodeTableService.js'></script>
<script type="text/javascript">
	var selectedrowid = new Array();
	var peopleNameAry = new Array();
	var phoneNumberAry = new Array();
	var title 			= "选择人员";
	var opCondition     ="1=1";
	var signleSelect 	= false;
	var resizable = false;
	var size= {};
	size.height = 534;
	size.width = 840;
	var allSelect = false;
	var hasSelectAll = false;
	var mytree = null;
	
	//回车事件监听
	function checkkey(){
		if (event.keyCode==13)
			quickSearch();
	}
	
	$(document).ready(function()
	{
		if(ChildDialogUtil.getExchangeData)
		{
			Page.Data.exchangeData = ChildDialogUtil.getExchangeData();//获得父窗口中传入值
			if(Page.Data.exchangeData)
			{
				if(Page.Data.exchangeData.name)
				{
					peopleNameAry	= Page.Data.exchangeData.name.concat();
				}
				if(Page.Data.exchangeData.id)
				{
					selectedrowid	= Page.Data.exchangeData.id.concat();
				}
				if(Page.Data.exchangeData.phoneNumber)
				{
					phoneNumberAry = Page.Data.exchangeData.phoneNumber.concat();
				}
				if(Page.Data.exchangeData.title)
				{
					title = Page.Data.exchangeData.title;
				}
				if(Page.Data.exchangeData.signleSelect)
				{
					signleSelect = Page.Data.exchangeData.signleSelect;
				}
				if(Page.Data.exchangeData.allSelect)
				{
					allSelect = Page.Data.exchangeData.allSelect;
				}
				if(Page.Data.exchangeData.resizable)
				{
					resizable = Page.Data.exchangeData.resizable;
				}
				if(Page.Data.exchangeData.size)
				{
					if(Page.Data.exchangeData.size.height)
					{
						size.height = Page.Data.exchangeData.height;
					}
					if(Page.Data.exchangeData.size.width)
					{
						size.width = Page.Data.exchangeData.width;
					}
				}
				if(Page.Data.exchangeData.condition)
				{
					opCondition=Page.Data.exchangeData.condition;
				}
			}
		}
		if(UserPackage.PageCall.beginReady)
		{
			UserPackage.PageCall.beginReady();
		}//创建控件
		$("#addReceiverComponent").createDialog(
		{
			height : 650,
			width : 800,
			resizable : resizable ,//允许更改大小
			modal : true ,// 模式
			title:title,
			winMode : ChildDialogUtil.getWinMode(),
			close : function(event)
			{
				if(!event.data)
				{
					ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL , {});
					return;
				}
				if(event.data.type == DialogUtil.EVENT_OK)
				{
					//这里处理返回值
					if(Page.Data.exchangeData)
					{
						Page.Data.exchangeData.name  =	peopleNameAry.concat();
						Page.Data.exchangeData.id   =	selectedrowid.concat();
						Page.Data.exchangeData.phoneNumber = phoneNumberAry.concat();
					}
					ChildDialogUtil.doClose(event.data.type , Page.Data.exchangeData);
				}
				else
				{
					ChildDialogUtil.doClose(event.data.type , {});
				}
			}
		});
		
		if(UserPackage.PageCall.beginInitComponents)
		{
			UserPackage.PageCall.beginInitComponents();
		}//控件初始化		
		
		if(Page.initComponent)
		{
			Page.initComponent();
		}
		
		//检查所有调用是否返回并开始加载数据
		Page.Util.checkCallFinish();
		//根椐父窗口传入值,重新显示已添加人员
		iniTree();
		iniJqGrid();
		reAddPeople();
		if(allSelect)
		{
			$("#allSelect").css("display","inline");
		}
		$("#operatorSearch").Watermark("请输入搜索条件","#369");
		
		$(".ui-jqgrid-bdiv").css("overflow","auto");
		//添加回车事件
		document.getElementById("searchField").onkeydow = checkkey;
		document.getElementById("operatorSearch").onkeydow = checkkey;
	});
	FormUtil.valid = function()
	{
		var executeValid = false;
		if(selectedrowid && selectedrowid.length == 0)
		{
			executeValid = true;
		}
		if(executeValid)
		{
			return !confirm("你还未选择人员，确定退出？")
		}else
		{
			return false;
		}
	}
	
	//初始化树
	function iniTree()
	{
		var treeSetting={
			async : {
				enable : true
			},
			check : {
				enable : false
			},
			dwr : true,
	 	    dataProxys : {
				organization : {
					titleKey : "organizationName",
					nameKey : "organizationName",
					properties : ['organizationCode','parentCode','organizationName','hasChild', 'level0'],
					dwrFun : OrganizationService.findMapByProperties,
					needLink : true,
					condition : 'parentCode IS NULL',
					sortField : '',
					order : ''
				}
			},
	 	   	dataProxySelecter : function(proxyName, node) { //混合数据选择器，选择当前需要使用哪一种数据
				if (proxyName) { //这里会返回点击的节点的类型，这个类型就是dataProxy中的属性
					return 'organization';
				} else {
					return 'organization'; //点击根节点的情况
				}
			},
	 	   	isLeaf : function(node){//是否是叶节点，不写则所有的节点都为非叶节点
	 	   		if(!node.hasChild){
					return true;
				}else {
					return false;			
				}
	 	   	},  
	 	   	beforeLoad : function(node, curProxyName){//加载时的判断条件，每次加载前会调用
	 	 	    if(curProxyName == 'organization'){
		 	   		if(node["organizationCode"] == null){
		 	 	   		return null;
		 	 	   	}else{
		 	 	   		var level0 = node.level0;
		 	 	   		this.dataProxys.organization.condition = "";
		 	 	   		return "level0>"+level0+" AND parentCode=" + node["organizationCode"];
		 	 	   	}
	 	 	    }else{
	 	 	    	return "parentCode IS NULL OR LENGTH(parentCode)=0";
	 	 	    }
	 	   	},
	 	   	callback : {
	 	   		onClick : function(event, treeId, treeNode){
						var condition = "";
						if(treeNode['organizationCode'] && treeNode['organizationCode'] != null)
						{
							condition = "organization LIKE '%" + treeNode['organizationCode'] + "%'";
						}
						jQuery("#operatorTable").setGridParam(
						{
							page:1,
							selectCondition : condition
						}).trigger("reloadGrid");
	 	   		},
	 	   		onAsyncSuccess: doAsyncSuccess
	 	   	}
		};
		var zNodes = [{
			name:"所有部门",
			open : true,
			isParent : true
		}];
		mytree = $.fn.zTree.init($("#operatorTree"), treeSetting, zNodes);
		var otRoot = mytree.getNodes()[0];
		mytree.expandNode(otRoot, true);
	}
	
	//将树的第二层展开
	function doAsyncSuccess(){
		var childNodes = mytree.transformToArray(mytree.getNodes()[0]);	//得到所有子节点
		if(!childNodes[1].children && !childNodes[1].open) {
			mytree.expandNode(childNodes[1], true);
		}
	}
	
	function iniJqGrid()
	{
		var gridSetting =
		{
			height : '231',
			datatype : "json" ,
			dwrFun : OperatorService.findMapByPropertiesWithLimit ,
			dwrCountFun : OperatorService.findCountByProperties ,
			condition : opCondition,
			searchCondition : '' ,
			selectCondition : '' ,
			needLink : true ,
			dwr : true ,
			autowidth : true ,
			scroll:true,
			colNames : [ '账号' , '姓名' ,'电话', '上级的部门名称'] ,
			colModel : [
			{
				key : true ,
				name : 'operatorCode' ,
				index : 'operatorCode',
				hidden:true,
				width:80
			} ,
			{
				name : 'operatorName' ,
				index : 'operatorName' ,
				width:95
			} ,
			{
				name : 'mobileNumber' ,
				index : 'mobileNumber' ,
				sortable : false,
				width:100
			},
			{
				name : 'org_organizationName' ,
				index : 'org_organizationName' ,
				sortable : false,
				width:150
			}] ,
			rowNum : 20,
			rowList : [30],
			pager : '#operatorPager',
			viewrecords : true ,
			rownumbers : false,
			loadComplete:function()
			{
				setSelectedRow();
				$(window).trigger('resize');
			},
			jsonReader :
			{
				root : "rows" ,
				page : "page" ,
				total : "total" ,
				records : "records" ,
				repeatitems : false
			} ,
			beforeSelectRow:function(rowid, e)
			{
				if(allSelect && hasSelectAll)
				{
					return false;
				}else
				{
					return true;
				}
				
			},
			onSelectRow:function(rowid,status)
			{
				if(status)
				{
					selectPeople(rowid);
				}else
				{
					unselectPeople(rowid);
				}
			},
			onSelectAll:function(aRowids,status)
			{
				if(allSelect && hasSelectAll)
				{
					return;
				}
				if(status)
				{
					selectCurPagePeople(aRowids);
				}else
				{
					unselectCurPagePeople(aRowids);
				}
			},
			multiselect :  signleSelect==false,
			multiboxonly : signleSelect

		};
		jQuery("#operatorTable").jqGrid(gridSetting);
		jQuery("#operatorTable").jqGrid('navGrid' , '#operatorPager' ,
		{
			edit : false ,
			add : false ,
			del : false ,
			search : false
		});
		$(window).bind('resize', function()
		{
			$("#operatorTable").setGridWidth($("#addReceiverComponent").width()*0.75 - 3);
		}).trigger('resize');
	}
	
	function quickSearch()
	{
		var curValue = $("#operatorSearch").val();
		if(curValue != '' && curValue != '请输入搜索条件')
		{
			curValue = $("#searchField").val() + " LIKE '%" + curValue + "%'";
			jQuery("#operatorTable").setGridParam(
			{
				page:1,
				searchCondition : curValue
			}).trigger("reloadGrid");
		}
		else{
			jQuery("#operatorTable").setGridParam(
			{
				page:1,
				searchCondition :""
			}	
			).trigger("reloadGrid");
		}
	}
	
	function isSelected(rowid)
	{
		var exist = false;
		for( var i in selectedrowid)
		{
			if(selectedrowid[i] == rowid)
			{
				exist = true;
				break;
			}
		}
		return exist;
	}
	
	function selectPeople(rowid)
	{
		if(selectedrowid.length == 0)
		{
		}
		var recevier = jQuery('#operatorTable').getRowData(rowid);
		if(isSelected(rowid) == false)
		{
			var newPeople = getFieldSetNodeStr(recevier['operatorCode'],recevier['operatorName']);
			if(signleSelect)
			{
				selectedrowid[0] = rowid;
				peopleNameAry[0] = recevier['operatorName'];
				phoneNumberAry[0] = recevier['mobileNumber'];	
				document.getElementById("selectedPeople").innerHTML = newPeople;
			}else
			{
				selectedrowid[selectedrowid.length] = rowid;
				peopleNameAry[peopleNameAry.length] = recevier['operatorName'];
				phoneNumberAry[phoneNumberAry.length] = recevier['mobileNumber'];	
				document.getElementById("selectedPeople").innerHTML += newPeople;
			}
		}
	}
	
	function unselectPeople(rowid)
	{
		$("#selectedPeople fieldset").remove("#"+rowid);
		unSelectPeopleAry(rowid);
		$("#operatorTable").jqGrid("resetSelection",rowid);
	}
	
	function unSelectPeopleAry(rowid)
	{
		for( var i in selectedrowid)
		{
			if(selectedrowid[i] == rowid)
			{
				var deleteid = selectedrowid.splice(i , 1);
				peopleNameAry.splice(i , 1);
				phoneNumberAry.splice(i , 1);
				if(deleteid =="00000000")
				{
					hasSelectAll = false;
				}
			}
		}
	}
	
	function unSelectFromFieldset(rowid)
	{
		unselectPeople(rowid);
		$("#operatorTable").jqGrid("resetSelection",rowid);
	}
	
	function deleteAll()
	{
		selectedrowid.length = 0;
		peopleNameAry.length = 0;
		phoneNumberAry.length = 0;
		$("#operatorTable").jqGrid("resetSelection");
		setSelectedRow();
		document.getElementById("selectedPeople").innerHTML ="";
	}
	
	function selectCurPagePeople(aRowids)
	{
		if(selectedrowid.length == 0)
		{
		}
		var html = "";
		for(var index in aRowids)
		{
			var rowid = aRowids[index];
			if(isSelected(rowid) == false)
			{
				var recevier = jQuery('#operatorTable').getRowData(rowid);
				var newPeople = getFieldSetNodeStr(recevier['operatorCode'],recevier['operatorName']);
				selectedrowid[selectedrowid.length] = rowid;
				peopleNameAry[peopleNameAry.length] = recevier['operatorName'];
				phoneNumberAry[phoneNumberAry.length] = recevier['mobileNumber'];
				html += newPeople;
			}
		}
		document.getElementById("selectedPeople").innerHTML  += html;
	}
	
	function unselectCurPagePeople(aRowids)
	{
		for(var index in aRowids)
		{
			var rowid = aRowids[index];
			if(isSelected(rowid))
			{
				unselectPeople(rowid);	
			}
		}
	}
	
	function reAddPeople()
	{
		if(!selectedrowid || selectedrowid.length == 0)
		{
			return ;
		}
		for(i in selectedrowid)
		{
			var rowid = selectedrowid[i];
			if(rowid == "00000000" && allSelect == true)
			{
				hasSelectAll = true;
			}
			var newPeople = getFieldSetNodeStr(selectedrowid[i],peopleNameAry[i]);
			document.getElementById("selectedPeople").innerHTML += newPeople;
		}
	}
	
	//添加全体人
	function addAllPeople()
	{
		if(allSelect == false)
		{
			return ;
		}
		if(selectedrowid.length!=0)
		{
			selectedrowid.length=0;
			peopleNameAry.length=0;
			phoneNumberAry.length=0
		}
		if(hasSelectAll == false)
		{
			hasSelectAll = true;
			var newPeople = getFieldSetNodeStr("00000000","全体人员");
			document.getElementById("selectedPeople").innerHTML = newPeople;
			selectedrowid[0] = "00000000";
			peopleNameAry[0] = "全体人员";
		}else
		{
			hasSelectAll = false;
		}
		setSelectedRow();
	}
	
	//让某行被选中
	function setSelectedRow()
	{
		for( var i in selectedrowid)
		{
			$("#operatorTable").jqGrid("setSelection",selectedrowid[i],false);
		}
	}
	
	function getFieldSetNodeStr(id,name)
	{
		return "<fieldset  style = \"width:140px;float:left;border:1px solid #D5DCEE;margin-left:10px;background-color:#F8F8F8;\" id='"+id+"' title='"+name+"' ><div style=\"float:left;cursor:default;width:100px;overflow:hidden;\"><font color='#2E8BD9'>"+name+"<font></div><div style=\"float:right;cursor:pointer;\" onclick=\"unSelectFromFieldset('"+id+"');\" title=\"取消该人员\" class=\"removeDiv\"><a>[删除]</a></div></fieldset>";
	}
</script>
	</head>
	<body style="background: transparent;overflow:auto">
		<div id="addReceiverComponent" title="选择人员" style="display: block;width:810px;margin:0px;padding:0px;">
			<table border="0px">
				<tr valign="top">
					<td style="width:15%;">
						<div style="height:100%;width:200px;max-width: 200px;max-height: 295px;overflow:auto;">
							<ul id="operatorTree" class="ztree">
								
							</ul>
						</div>
					</td>
					<td id="showMainPage">
						<div>
							<div style="font-size: 12px;">
								<label for ="operatorSearch" style="color:#000;">快速搜索：</label>
								<select id="searchField">
									<option value="operatorName" style="font-family: 宋体">
										姓名
									</option>
									<option value="mobileNumber"  style="font-family: 宋体">
										电话
									</option>
								</select>
								<input type="text" id="operatorSearch"/>
								<button onclick="quickSearch();">
									搜索
								</button>
								<button onclick="addAllPeople();" id ="allSelect" style="display: none;">
									选择全体人员
								</button>
							</div>
							<div id="MainArea" style="width: 100%;overflow:auto">
								<table id="operatorTable"></table>
								<div id="operatorPager"></div>
							</div>
						</div>
					</td>
				</tr>
				<tr valign="top" >
					<td colspan="2" height="30%">
						<fieldset id = "selectedFieldset" style="min-height: 56px;border:1px solid #7EB2FD;">
							<legend style="font-size:13px; cursor:default"><font color="#2E8BD9">已选择人员：</font>
								<label id="clearAll" style="font-size:13px;cursor:pointer" onclick="deleteAll();" >
								<a style="color:#FB891F;">
								[清空]
								</a>
								</label>
							</legend>
							<div id="selectedPeople" title="已选择人员" style="padding:0px;height:88px;overflow-x:hidden;overflow-y:auto;font-size:13px">
							</div>
						</fieldset>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>