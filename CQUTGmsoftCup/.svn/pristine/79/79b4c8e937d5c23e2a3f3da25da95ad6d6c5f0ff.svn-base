;
/*
 *该JS用于生成项目所需的公共下拉框请求数据 
 *author zx
 *实际功能：一个公共查询后台数据，并显示到下拉框
 *（1）学年下拉框
  （2）学期下拉框  
  （3）专业下拉框
  （4）专业类别下拉框
  （5）专家类别下拉框
  （6）学历下拉框
  （7）体系下拉框
  （8）指标版本下拉框
  （9）任务下拉框
  （10）学院下拉框
 *
 */
var DropDownBox = {};

// 判断是否初始化
DropDownBox.isInitialization = false;


DropDownBox.loadDropDownBox = function(dropDownBoxSelector, optionValueField, optionContentField, optionDatas, notHasAll) {
	if(!dropDownBoxSelector || !optionValueField || !optionContentField) {
		jAlert('参数错误！请至少传dropDownBoxSelector, optionValueField, optionContentField, optionDatas四个参数',function(){},"错误");
		return ;
	}
	
	if(!optionDatas) {
		jAlert('加载失败！请您重试',function(){},"错误");
		return ;
	}
	
	this.isInitialization = true;
	var thisObj = this;
	$(dropDownBoxSelector).each(function(index, element) {
		$(element).empty();
		$(element).html(thisObj.getOptionsStr(optionValueField, optionContentField, optionDatas, notHasAll));
	});
	this.isInitialization = false;
	/*var oDropDownBox =$("#" + dropDownBoxSelector);
	oDropDownBox.empty();	// 先清空
	oDropDownBox.html(this.getOptionsStr(optionValueField, optionContentField, optionDatas));
	*/
};

DropDownBox.getOptionsStr = function(optionValueField, optionContentField, optionDatas, notHasAll) {
	if(!this.isInitialization)
		return;

	var optionsStr = "";
	if(!notHasAll)	//不需要"全部"传入true
		optionsStr="<option value=''>全部</option>";
	
	else if(notHasAll && (!optionDatas || optionDatas.length == 0))
		return "<option value=''>无</option>";
		
	for(var i in optionDatas) {
		optionsStr+= "<option value='"
			+ optionDatas[i][optionValueField] + "'>"
			+ optionDatas[i][optionContentField] + "</option>";
	}
	
	return optionsStr;
};


// 加载学年下拉框
DropDownBox.loadSchoolYear = function(dropDownBoxSelector, notHasAll) {
	if(!dropDownBoxSelector)
		return;

	var thisObj = this;
	SchoolYearService.findMapByProperties(
			[ 'schoolYearID', 'schoolYearName','startYear'], '1=1', 'startYear', 'desc',
			false, function(data) {

				thisObj.loadDropDownBox(dropDownBoxSelector, "schoolYearID", "schoolYearName", data, notHasAll);
				// 默认为当前学期
				if(notHasAll) // 如果不需要'全部'则默认为当前学年
					SchoolYearService.getCurrentSemesterAndSchoolYear(function(data) {
						if(data) {
							$(dropDownBoxSelector).val(data.pro_schoolYearID);
						}
					});
				
			});
};

// 加载startYear
//加载学年下拉框
DropDownBox.loadSchoolYearForStartYear = function(dropDownBoxSelector, notHasAll) {
	if(!dropDownBoxSelector)
		return;

	SchoolYearService.findMapByProperties(
			[ 'schoolYearID', 'schoolYearName','startYear'], '1=1', 'startYear', 'desc',
			false, function(optionDatas) {
					if(!optionDatas)
						return;
					var optionsStr = "";
					if(!notHasAll)	//不需要"全部"传入true
						optionsStr="<option value=''>全部</option>";
					
					for(var i in optionDatas) {
						optionsStr+= "<option value='"
							+ optionDatas[i]["startYear"] + "'>"
							+ optionDatas[i]["startYear"] + "年</option>";
					}
					
					$(dropDownBoxSelector).each(function(index, element) {
						$(element).empty();
						$(element).html(optionsStr);
					});
					// 默认为当前学期
					if(notHasAll) // 如果不需要'全部'则默认为当前学年
						SchoolYearService.getCurrentSemesterAndSchoolYear(function(data) {
							if(data) {
								$(dropDownBoxSelector).val(data.pro_startYear);
							}
						});
			});
};
// 加载学年名
//加载学年下拉框
DropDownBox.loadSchoolYearForSchoolYearName = function(dropDownBoxSelector, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	var thisObj = this;
	SchoolYearService.findMapByProperties(
			[ 'schoolYearID', 'schoolYearName','startYear'], '1=1', 'startYear', 'desc',
			false, function(data) {
					thisObj.loadDropDownBox(dropDownBoxSelector, "schoolYearName", "schoolYearName", data, notHasAll);
					// 默认为当前学期
					if(notHasAll) // 如果不需要'全部'则默认为当前学年
						SchoolYearService.getCurrentSemesterAndSchoolYear(function(data) {
							if(data) {
								$(dropDownBoxSelector).val(data.pro_schoolYearName);
							}
						});
			});
};

// 学期下拉框  
DropDownBox.loadSemester = function(dropDownBoxSelector, schoolYearId, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	// 根据学年id查询学期
	var conditon = " 1=1" + schoolYearId ? " and pro.schoolYearID='"+ schoolYearId +"'" : "";
	
	var thisObj = this;
	SemesterService.findMapByProperties(
			['semesterID', 'pro_schoolYearID', 'semesterName'], conditon , 'pro.startYear asc,orderNumber', "asc",
			true, function(data) {
				thisObj.loadDropDownBox(dropDownBoxSelector, "semesterID", "semesterName", data, notHasAll);
				if(notHasAll)// 默认为当前学年学期
					SchoolYearService.getCurrentSemesterAndSchoolYear(function(data) {
						if(data) {
							$(dropDownBoxSelector).val(data.me_semesterID);
						}
					});
			});
};

//学院下拉框
DropDownBox.loadAcademic = function(dropDownBoxSelector, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	AcademicService.findMapByPropertiesQuick(
			["academicID","academicName"],"academicType='BMType0001'",
		 false,function(data){
				thisObj.loadDropDownBox(dropDownBoxSelector, "academicID", "academicName", data, notHasAll);
	    });
};

DropDownBox.loadAcademicByTaskId = function(dropDownBoxSelector, taskId, notHasAll) {
	if(!dropDownBoxSelector || !taskId)
		return;
	
	var thisObj = this;
	AcademicService.findMapByPropertiesQuick(
			["me_academicID","me_academicName","me_academicShort","dis_academicID","td_taskID","ta_taskID"],
			"me.academicType='BMType0001' AND ta.taskID='"+ taskId +"'",true,function(data){
				thisObj.loadDropDownBox(dropDownBoxSelector, "me_academicID", "me_academicName", data, notHasAll);
			});
};

// 专业下拉框
/*DropDownBox.loadMajorByAcademicId = function() {
	
};*/
// 根据任务和学院id加载下拉框
DropDownBox.loadMajorByTaskIdAndAcademicId = function(dropDownBoxSelector, taskId, academicId, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	var con = " 1=1 ";
	con += taskId ? " AND ta.taskID='" + taskId + "'" : "";
	con += academicId ? " AND academicID='" + academicId + "'" : "";
	
	DisciplineService.findMapByProperties( 
			[ "disciplineID", "disciplineShort","disciplineName", "taskd_taskDetailID", "ta_taskID" ],
	        con, "", "", true, function(data) {
					thisObj.loadDropDownBox(dropDownBoxSelector, "disciplineID", "disciplineName", data, notHasAll);
	 });	
		
};

// 专业类别下拉框
DropDownBox.loadDisciplineType = function(dropDownBoxSelector, notHasAll) {
	this.loadForCodeTable(dropDownBoxSelector, "parentCode ='MajorType'", notHasAll);
};

// 专家类别下拉框
DropDownBox.loadExpertType = function(dropDownBoxSelector) {
	if(!dropDownBoxSelector)
		return;
	
	var optionsStr = "<option value='0' selected='true'>校内</option>" +
			"<option value='1'>校外</option>";
	$("#" + dropDownBoxSelector).html(optionsStr);
};

// 学历下拉框
DropDownBox.loadEducationBackground = function(dropDownBoxSelector, notHasAll) {
	this.loadForCodeTable(dropDownBoxSelector, "parentCode ='EducationType'", notHasAll);
};

DropDownBox.loadForCodeTable = function(dropDownBoxSelector, condition, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	//var condition = parentCode ? "parentCode='"+ parentCode + "'" : "parentCode IS NULL";
	CodeTableService.findMapByProperties(["codeTableCode","codeTableName"],
			condition,"codeTableCode","",
			false, function(data){
				thisObj.loadDropDownBox(dropDownBoxSelector, "codeTableCode", "codeTableName", data, notHasAll);
			});
};


// 体系下拉框
DropDownBox.loadTargetSystem = function(dropDownBoxSelector, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	
	TargetSystemService.findMapByPropertiesQuick(['targetSystemID','targetSystemName'],"1=1",
            false,function(data){
				thisObj.loadDropDownBox(dropDownBoxSelector, "targetSystemID", "targetSystemName", data, notHasAll);
            });
};

// 指标版本下拉框
DropDownBox.loadTargetSysVersionByTargetSystemId = function(dropDownBoxSelector, targetSystemID, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	
	var condition=" 1=1 ";
	if(targetSystemID !=null && targetSystemID != ""){
		condition = " targetSysID='" + targetSystemID + "'";
	}
	
	TargetSysVersionService.findMapByProperties(['targetSysVersionID','targetVersionName'],condition,
            'targetSysVersionOrder',
            'desc',
            false,function(data){
				thisObj.loadDropDownBox(dropDownBoxSelector, "targetSysVersionID", "targetVersionName", data, notHasAll);
            });
};

DropDownBox.loadTargetSysVersionByTaskIdAndTargetSystemId = function(dropDownBoxSelector, taskId, targetSystemID, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	var con = " 1=1 ";
	con += /*taskId ?*/ " And detail.taskID='" + taskId + "'";// : "";
	con += targetSystemID ? " And me.targetSysID='" + targetSystemID + "'" : "";
	TargetSysVersionService.findMapByPropertiesQuick(["me_targetSysVersionID", "me_targetVersionName", "detail_taskID"],
			con, true, function(data) {
				thisObj.loadDropDownBox(dropDownBoxSelector, "me_targetSysVersionID", "me_targetVersionName", data, notHasAll);
		});
};

// 任务下拉框
DropDownBox.loadTask = function(dropDownBoxSelector, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	
	TaskService.findMapByPropertiesQuick(["taskID", "taskName"], "1=1 order by taskStartDate", 
			false, function(data) {
				thisObj.loadDropDownBox(dropDownBoxSelector, "taskID", "taskName", data, notHasAll);
				if(notHasAll) {
					TaskService.getCurTask(function(data) {
						if(data)
						$(dropDownBoxSelector).val(data.taskID);
					});
				}
			});	
};

// 职称下拉框
DropDownBox.loadJobTitle = function(dropDownBoxSelector,notHasAll){
	
	if(!dropDownBoxSelector)
		return;
	var thisObj = this;
	
	CodeTableService.findMapByPropertiesQuick(["codeTableCode","codeTableName"],"parentCode='JobTitleType' order by codeTableCode",
			false,function(data){
		thisObj.loadDropDownBox(dropDownBoxSelector, "codeTableCode", "codeTableName", data,notHasAll);
	});
};

//学位下拉框
DropDownBox.loadDegree = function(dropDownBoxSelector,notHasAll){
	
	if(!dropDownBoxSelector)
		return;
	var thisObj = this;
	
	CodeTableService.findMapByPropertiesQuick(["codeTableCode","codeTableName"],"parentCode='DegreeType' order by codeTableCode",
			false,function(data){
		thisObj.loadDropDownBox(dropDownBoxSelector, "codeTableCode", "codeTableName", data,notHasAll);
	});
};

//奖项下拉框
DropDownBox.loadAwardGrade = function(dropDownBoxSelector,notHasAll){
	
	if(!dropDownBoxSelector)
		return;
	var thisObj = this;
	
	CodeTableService.findMapByPropertiesQuick(["codeTableCode","codeTableName"],"parentCode='AwardGradeType' order by codeTableCode",
			false,function(data){
		thisObj.loadDropDownBox(dropDownBoxSelector, "codeTableCode", "codeTableName", data,notHasAll);
	});
};

//项目级别下拉框
DropDownBox.loadProjectGrade = function(dropDownBoxSelector,notHasAll){
	
	if(!dropDownBoxSelector)
		return;
	var thisObj = this;
	
	CodeTableService.findMapByPropertiesQuick(["codeTableCode","codeTableName"],"parentCode='RankType' order by codeTableCode",
			false,function(data){
		thisObj.loadDropDownBox(dropDownBoxSelector, "codeTableCode", "codeTableName", data,notHasAll);
	});
};

//大赛名称下拉框
DropDownBox.loadChampion = function(dropDownBoxSelector, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	GameService.findMapByPropertiesQuick(
			["gameID","gameName"],'state=1',
		 false,function(data){
				thisObj.loadDropDownBox(dropDownBoxSelector, "gameID", "gameName", data, notHasAll);
	    });
};

//竞赛类别下拉框
DropDownBox.loadRate = function(dropDownBoxSelector, notHasAll) {
	if(!dropDownBoxSelector)
		return;
	
	var thisObj = this;
	GameStepService.findMapByPropertiesQuick(
			["gameStepID","mo_codeTableName"],'state=1',
		 true,function(data){
				thisObj.loadDropDownBox(dropDownBoxSelector, "gameStepID", "mo_codeTableName", data, notHasAll);
	    });
};
