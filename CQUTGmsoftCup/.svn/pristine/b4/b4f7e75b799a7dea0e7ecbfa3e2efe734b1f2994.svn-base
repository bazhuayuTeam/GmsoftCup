function doselect(tablename, condition, columnName, colModelStr, searchList,
		isMuti) {
	var result = null;
	
	DialogUtil.openModalWindow(basePath + "module/util/select.jsp", {
		tablename : tablename,
		condition : condition,
		columnName : columnName,
		colModelStr : colModelStr,
		searchList : searchList,
		isMuti : isMuti
	}, {
		EVENT_OK : function(param) {
			result = param;
		}
	}, 730, 344);
	
	return result;
}
/**
 * 封装了不同table所需的jsFile,dwrClass,dwrFun,dwrCountFun,colNames,condition,colModel,searchList
 */

var selectTable={
	enterprise:{
	        jsFile:'EnterpriseService.js',
	        dwrClass:'EnterpriseService',
	        dwrFun:'findMapByPropertiesWithLimit',
	   		dwrCountFun:'findCountByProperties',
	   		colNames:['ID','公司名字'],
	   		condition:'true=true',
	   		searchCondition:"true=true",
	   		selectCondition:"true=true",
	   		colModel:[{
			   			name:'enterpriseID',
			   			index:'enterpriseID',
			   			key:true,
			   			hidden:true,
			   			sortable:false
			   		},{
			   			name:'enterpriseName',
			   			index:'enterpriseName',
			   			sortable:false
			   		}],
	   		searchList:[{text:'公司名字',value:'enterpriseName'}]
	},
	album:{
	        jsFile:'AlbumService.js',
	        dwrClass:'AlbumService',
	        dwrFun:'findMapByPropertiesWithLimit',
	   		dwrCountFun:'findCountByProperties',
	   		colNames:['ID','专辑','歌手','发行日期'],
	   		condition:'1=1',
	   		needLink:true, 
	   		searchCondition:"1=1",
	   		selectCondition:"1=1",
	   		colModel:[{
			   			name:'albumId',
			   			index:'albumId',
			   			key:true,
			   			hidden:true,
			   			sortable:false
			   		},{
			   			name:'albumName',
			   			index:'albumName',
			   			sortable:false
			   		},{
			   			name:'singer_singerName',
			   			index:'singer_singerName',
			   			sortable:false
			   		},{
						name : 'issueDate',
						index : 'issueDate',
						sortable : true,
						formatter : 'date',
						formatoptions : {
							srcformat : 'Y-m-d',
							newformat : 'Y年m月d日'
						}
					}],
	   		searchList:[{text:'专辑名称',value:'albumName'},{text:'歌手名称',value:'singerName'}]
	},
	
	videoDetail:{
	        jsFile:'VideoDetailService.js',
	        dwrClass:'VideoDetailService',
	        dwrFun:'findMapByPropertiesWithLimit',
	   		dwrCountFun:'findCountByProperties',
	   		colNames:['ID','明细名称','videoID','视频名称','类型','创建时间'],
	   		condition:'1=1',
	   		needLink:true, 
	   		searchCondition:"1=1",
	   		selectCondition:"1=1",
	   		colModel:[{
			   			name:'me_videoDetailId',
			   			index:'me_videoDetailId',
			   			key:true,
			   			hidden:true,
			   			sortable:false
			   		},{
			   			name:'me_videoDetailName',
			   			index:'me_videoDetailName',
			   			sortable:false,
			   			hidden:true
			   		},{
			   			name:'me_videoId',
			   			index:'me_videoId',
			   			sortable:false,
			   			hidden:true
			   		},{
			   			name:'video_videoName',
			   			index:'video_videoName',
			   			sortable:false
			   		},{
			   			name:'vk_codeTableName',
			   			index:'vk_codeTableName',
			   			sortable:false
			   		},{
						name : 'video_createTime',//视频的创建时间
						index : 'video_createTime',
						sortable : true,
						formatter : 'date',
						formatoptions : {
							srcformat : 'Y-m-d',
							newformat : 'Y年m月d日'
						}
					}],
	   		searchList:[{text:'视频名称',value:'Video.videoName'}]
	},
	personSelect:{
		jsFile:'OperatorService.js',
        dwrClass:'OperatorService',
        height:'auto',
		width:'auto',
        dwrFun:'findMapByPropertiesWithLimit',
   		dwrCountFun:'findCountByProperties',
   		colNames:['账号','姓名','operatorID'],
   		condition:'true=true',
   		needLink:true, 
   		searchCondition:"true=true",
   		selectCondition:"true=true",
   		colModel:[{
		   			name:'zgh',
		   			index:'zgh',
		   			key:true,
		   			hidden:false,
		   			sortable:false,
		   			align:'center'
		   		},{
		   			name:'name',
		   			index:'name',
		   			sortable:false,
		   			align:'center'
		   		},{
		   			name:'operatorID',
		   			index:'operatorID',
		   			sortable:false,
		   			align:'center',
		   			hidden:true
		   		}],
   		searchList:[{text:'账号',value:'zgh'},{text:'姓名',value:'name'}]		
	},
	appClass:{
		jsFile:'AuditCourseService.js',
        dwrClass:'AuditCourseService',
        dwrFun:'findNewMapByPropertiesWithLimit',
   		dwrCountFun:'findCountByProperties',
   		colNames:['考核ID','考堂号','课程名称','课堂','班级'],
   		condition:"1=1",
   		sortname:'cl.kcmc',
		sortable:true,
		order:'asc',
   		needLink:true, 
   		colModel:[{
   			name:'auditCourseID',
   			index:'auditCourseID',
   			key:true,
   			hidden:true
   		},{
			name:'classRoomID',
			index:'classRoomID',
			hidden:true
	    },{
   			name:'cl_kcmc',
   			index:'cl_kcmc'
   		},{
   			name:'cl_jsxm',
   			index:'cl_jsxm',
   			width:100,
   			formatter:function(data){
   				return data+"课堂";
   			}
   		},{
   			name:'cl_BJMC',
   			index:null
   		}],
   		searchList:[{text:'课程名称',value:'cl.kcmc'}]		
	},
	newExam:{
		jsFile:'OperatorService.js',
        dwrClass:'OperatorService',
        dwrFun:'findMapByPropertiesWithLimitAudit',
   		dwrCountFun:'findCountByPropertieNewAudit',
   		colNames:['课堂ID','学期','课程名称','教师姓名','班级'],
   		condition:"1=1",
   		sortname:'kcmc',
		sortable:true,
		order:'asc',
   		needLink:true, 
   		colModel:[{
   			name:'auditCourseID',
   			index:'auditCourseID',
   			key:true,
   			hidden:true
   		},{
   			name:'semesterID',
   			index:'semesterID',
   			hidden:true
   		},{
   			name:'kcmc',
   			index:'kcmc'
   		},{
   			name:'jsxm',
   			index:'jsxm',
   			width:100,
   			formatter:function(data){
   				return data+"课堂";
   			}
   		},{
   			name:'BJMC',
   			index:'BJMC'
   		}],
   		searchList:[{text:'课程名称',value:'kcmc'},{text:'教师姓名',value:'jsxm'}]
	},
	roomSelectedClass:{
		jsFile:'OperatorService.js',
        dwrClass:'OperatorService',
        dwrFun:'findMapByJXCDXXB',
   		dwrCountFun:'findCountByJXCDXXB',
   		colNames:['教室编号','教室名称'],
   		condition:"1=1",
   		needLink:false, 
   		searchCondition:"1=1",
   		selectCondition:"1=1",
	colModel : [ {
		//教室编号
		key : true,
		name : "JSBH",
		index : "JSBH",
		width : 20,
		align : 'center'
	}, {
		//教室名称
		name : "JSMC",
		index : "JSMC",
		width : 40,
		align : 'center'
	} ],
	searchList:[{text:'教室编号',value:'JSBH'},{text:'教室名称',value:'JSMC'}]
	}
	
}