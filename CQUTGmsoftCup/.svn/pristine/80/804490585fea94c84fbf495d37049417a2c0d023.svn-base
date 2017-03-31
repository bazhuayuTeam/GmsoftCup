
var tablePager = {
	curPage : 1,
	limit : 10,
	totalSize : 0,
	totalPages: 0,
	currentScope: '0-0',
	initPager : function(pageIndex){
		tablePager.totalSize = pageIndex;
		tablePager.curPage = 1;
		tablePager.totalPages = Math.ceil(this.totalSize/this.limit);
		
		getPageData();	
		initPageHtml();
		showPagerData();
		loadData();
	},
	updatePager : function(pageIndex) {
		tablePager.totalSize = pageIndex;
		tablePager.curPage = 1;
		tablePager.totalPages = Math.ceil(this.totalSize/this.limit);
		
		getPageData();
		showPagerData();
		loadData();
	}
};

//显示静态代码
function initPageHtml(){
	var htmlCode="<div class='pageContent'><div id='centerPage'><div class='pageCenter'><img id='firstPageBtn' onclick='firstPage();' src='"+basePath+"/images/pager/firstPage.png' /><img id='previousPageBtn' src='"+basePath+"/images/pager/previousPage.png' onclick='previousPage();'/><input id='currentPage' onkeydown='javascript:if(event.keyCode==13){changePageIndex();}' maxlength='3'/><span id='totalPage'>共1页</span><img id='nextPageBtn' onclick='nextPage();' src='"+basePath+"/images/pager/nextPage.png' /><img id='lastPageBtn' onclick='lastPage();' src='"+basePath+"/images/pager/lastPage.png' /><select id='limitSelect' onchange='changePageLimit();'><option>10</option><option>20</option><option>30</option></select></div></div><span id='totalScope'>共0条</span><span id='currentScope'>1-10</span></div>";
	$(".turnPage").html(htmlCode);
}

//计算数据
function getPageData(){
	if(tablePager.curPage*tablePager.limit>tablePager.totalSize){
		tablePager.currentScope = (((tablePager.curPage-1)*tablePager.limit+1)+"-"+tablePager.totalSize);
	}else{
		tablePager.currentScope = (((tablePager.curPage-1)*tablePager.limit+1)+"-"+(tablePager.curPage*tablePager.limit));
	}
}

//显示总共的页数、当前范围、总条数
function showPagerData(){
	$("#totalPage").html(("共"+tablePager.totalPages+"页"));
	$("#currentScope").html(tablePager.currentScope);
	$("#totalScope").html(("共"+tablePager.totalSize+"条"));
	$("#currentPage").val(tablePager.curPage);
}

//输入框指定页数跳转
function changePageIndex() {
	var page = $("#currentPage").val();
	var g = /^[1-9]*[1-9][0-9]*$/;
	//验证是否为正整数和是否超出上限
	if(!g.test(page)){
		alert("请输入正确的页码！");
	}else if(page>tablePager.totalPages){
		alert("当前页码超出总页码！");
	}
	
	tablePager.curPage = page;
	$("#currentPage").val(tablePager.curPage);
	loadData();
}

//修改每页展示条数
function changePageLimit() {
	//获取select 选中的 text :
	tablePager.limit=$("#limitSelect").find("option:selected").text();
	tablePager.curPage = 1;
	tablePager.totalPages = Math.ceil(tablePager.totalSize/tablePager.limit);
	getPageData();
	$("#currentPage").val(tablePager.curPage);
	loadData();
}

function previousPage() {
	if(tablePager.curPage==1){
	}else{
		tablePager.curPage --;
		$("#currentPage").val(tablePager.curPage);
		loadData();
	}
}

function nextPage() {
	if(tablePager.curPage==tablePager.totalPages){
	}else{
		tablePager.curPage ++;
		$("#currentPage").val(tablePager.curPage);
		loadData();
	}
}

function firstPage(){
	if(tablePager.curPage != 1){
		tablePager.curPage = 1;
		$("#currentPage").val(tablePager.curPage);
		loadData();
	}
}

function lastPage(){
	if(Math.ceil(this.totalSize/this.limit)>1){
		tablePager.curPage = Math.ceil(this.totalSize/this.limit);
		$("#currentPage").val(tablePager.curPage);
		loadData();
	}
}
