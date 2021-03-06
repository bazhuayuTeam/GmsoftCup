/*
 * 表单及相关控件
 */
(function($) {
	
	// **********************************************文本框控件
	$.fn.FieldText = function(options) {
		var defaultopt = {
			readOnly : false
		};
		$.extend(defaultopt, options);
		buildFieldText(defaultopt, this);
		return new zFieldText().init(this);
		
	};	
	function buildFieldText(setting, obj) {
		var readOnly = obj.attr('readOnly') || setting.readOnly;
		if (readOnly) {
			showReadOnly(obj);// 处理是否只读
		} else {	
			//处理编辑器初始化
			if(obj.attr("editor")){
				var KESetting =
				{
					id : obj.attr("id"),//指定应用kindEditor的TextArea的ID
					imageUploadJson : '../kindeditor/jsp/upload_json.jsp',
					resizeMode : 1,//允许更改高度和宽度
					fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
					allowFileManager : true,//允许访问服务器
					newlineTag : 'br',//shift+enter换行  	newlineTag : 'p',//回车换行
					syncType : '', //auto: 每次修改时都会同步; form:提交form时同步; 空:不会自动同步;
					pluginsPath : '../plugins/kindeditor/plugins/'
				};
				KE.show(KESetting);
	        }
			// 处理必须输入和参照
			var html = "";
			if (obj.attr("required")||obj.attr("isRequired")/* == "1"*/) {// 必填加入红心标识
				html = "<span id='"+obj.attr('id')+"_requiredIcon'><font color='red'>*</font></span>";
			}
			if (obj.attr('refer')) {// 有参照
				obj.attr("readOnly","readOnly");//文本框不可编辑
				html = "&nbsp;<span id='"+obj.attr('id')+"_referIcon' title='选择' style='cursor:hand;width:14px;height:16px;background:url("
						+ WinUtil.getBase()
						+ "images/ico.gif) no-repeat -9px -5px;display:-moz-inline-box; display:inline-block;zoom:1;'>&nbsp;</span>"
						+ html;
			}
			if (html != "") {
				obj.after(html);
			}
			//处理 智能完成功能
			if(obj.attr('suggesttable')){
				$.plugin('servers',{
			        files: ['dwr/interface/OrganizationService.js',
			        	'dwr/interface/'+obj.attr('suggesttable')+'Service.js'],
			        selectors: ['head'] 
				});
				$.plugin('servers',function(){
					suggestFun=eval(obj.attr('suggesttable')+"Service.findMapByPropertiesQuick");
					if(obj.attr('suggestcondition')){
						suggestcondition=obj.attr('suggestcondition');
					}
					else{
						suggestcondition='';
					}
					suggestFun([obj.attr('suggestcolumn')],suggestcondition,false,function(data){
						var suggestData=new Array();
						//list<map>类型data转换为array的suggestData
						for(var i in data){
							suggestData.push(data[i][obj.attr('suggestcolumn')]);
						}
			             obj.autocomplete({
							source: suggestData
						});
					});
				});
			}
		}
	}
	// 自定义的控件对象
	function zFieldText() {
		var oldValue = null;//初始值
		var nowText = null;//当前显示数据
		var nowValue = null;//当前值
		var modified = false;//是否修改过
		var setValueCalled = false;// 是否已经赋值过
		var obj;// 对应的html中的DOM对象
		var self = this;
		this.init = function(obj1) {
			obj = obj1;
			$(obj).data({'datas':[],'ids':[]}).bind('addData',function(event,ids,datas){//添加数据
				if(ids && ids[0]){
					var curIds = $(this).data('ids'),curDatas = $(this).data('datas'),flag = false;
					for(var i=0;i<ids.length;i++){
						flag = false;
						for(var j=0;j<curIds.length;j++){
							if(curIds[j] == ids[i]){
								flag = true;
								break;
							}
						}
						if(!flag){
							curIds.push(ids[i]);
							curDatas.push(datas[i]);
						}
					}
				}
			}).bind('removeData',function(event,id){//删除数据
				if(id){
					var curIds = $(this).data('ids');
					alert('delete');
					var temp = [];
					for(var i=0;i<curIds.length;i++){
						if(curIds[i] != id){
							temp.push(curIds[i]);	
						}
					}
					$(this).data('ids',temp);
				}
			}).bind('loadReady',function(event){}).bind('clearAll',function(event){
				$(this).data('ids',[]);
				$(this).data('datas',[]);
			});//数据加载完成
			if (obj.attr('refer')) {// 有参照
				var btn = $(obj.siblings('span')[0]);// 获取选择按钮
				$(obj).bind('click',function(){
					btn.click();
				});
				// 添加参照事件及相关处理
				btn.click(function(e) {
					//存在前置条件
					if(obj.attr("isCheck") && checkFieldFun && checkFieldFun()){
							alert(checkFieldFun());
					}else{//不存在前置条件
						if(obj.attr("multiSelect")){
							var result=doMultiSelect(obj.attr("referClass"),//获取返回的结果
							obj.attr("referCondition")
					   			);
							if(result){
								
							}
						}
						else{
							var result = null;
							if(obj.attr('isMuti')){
								result = doselect(obj.attr("referClass"),//获取返回的结果
									obj.attr("referCondition"),
									undefined,
									undefined,
									undefined,
									true
					   			);
							}else{
								result = doselect(obj.attr("referClass"),//获取返回的结果
									obj.attr("referCondition")
					   			);
							}
							if(result){
								if(obj.attr('isMuti')){
									$(obj).trigger('clearAll').trigger('addData',[result.ids,result.datas]).trigger('loadReady');
								}else{
									obj.attr("referData",result.enterpriseID);
									obj.attr("DataID",result.IDs);
									$(obj).data('result',result);
									self.setValue(result[obj.attr("refer")/*.split('_')[1]*/]);
									if(obj.val()!=result[obj.attr("referText")/*.split('_')[1]*/]){
										if(obj.get(0).onchange){
											obj.get(0).onchange();
										}
									}
									obj.val(result[obj.attr("referText")/*.split('_')[1]*/]);
									obj.focus();//焦点落到该组件
									if(isRoleAssign){//只有在角色分配时候用
										refresh(result);
									}
								}
							}
						}
					}
				});
			}
			//处理自定义函数
			if(obj.attr("formatter")){
				var functionName = obj.attr("formatter");
				//通过name获取function对象
				var formatter=eval(functionName);
				var result = formatter(oldValue,nowText);
			};
			obj.blur(function(e) {//失去焦点时将界面的值加入对象
				var temp = $.trim(obj.val());
				if (temp == "") {
					nowText = "";
				} else {
					nowText = temp;
				}
			});
			return this;
		};
		//绑定初始值
		this.bindData = function(data) {
			if (obj.attr("refer")) {// 有参照
				var text = data[obj.attr("referText").replace('.', '_')];
				nowText = text;//用参照的值来显示
				obj.val(text);
			}
			self.setValue(data[obj.attr("field")]);
		};
		this.setValue = function(value) {
			if (setValueCalled) {

			} else {
				oldValue = value;
				setValueCalled = true;
			}
			if (obj.attr("refer")) {

			} else {
				obj.val(value);
			}
			nowValue = value;
		};
		this.getValue = function() {
			if (obj.attr("refer")) {
				return nowValue;
			} else {
				return $.trim(obj.val());
			}
		};
		this.exportToData = function(data) {
			if (obj.attr("refer")) {
				data[obj.attr("field")] = nowValue;
				data[obj.attr("referText").replace('.', '_')] = nowText;
			} else {
				data[obj.attr("field")] = obj.val();
			}
		};
		this.isModified = function() {
			if (obj.attr("readOnly")) {
				return false;
			}
			if (oldValue != nowValue) {
				return true;
			}
			return false;
		};
		this.valid = function() {// 验证
			/*if (obj.attr("required")) {
				if (obj.attr("refer")) {
					if (!nowValue) {
						return "'" + obj.attr("showName") + "'不能为空！";
					}
				} else {
					if (!($.trim(obj.val()))) {
						return "'" + obj.attr("showName") + "'不能为空！";
					}
				}
			}*/
			//alert($(obj).css('background','red'));
			if(obj.attr("required")||obj.attr("isRequired")) {
				if ( (!$.trim(obj.val())) ){
					return "'" + obj.attr("showName") + "'不能为空！";
				}
			}
			if(obj.attr('')){
				
			}
			if(obj.attr()){
				
			}
			return "";
		};
		return this;
	}
	
	
	//**************************************************** 日期控件
	$.fn.FieldDate = function(options) {
		var defaultopt = {
			readOnly : false
		};
		$.extend(defaultopt, options);
		buildFieldDate(defaultopt, this);
		return new zFieldDate().init(this);
	};
	function buildFieldDate(setting, obj) {
		var readOnly = obj.attr('readOnly') || setting.readOnly;
		if (readOnly) {
			showReadOnly(obj);// 处理是否只读
		} else {
			//定义输入格式
			var option={
					
					showButtonPanel: true,
					changeMonth: true,
					changeYear: true,
					showOn: "button",
					buttonText: '日期选择' ,
					buttonImage: "images/calendar.gif",
					buttonImageOnly: true,
					yearRange: '1900:2050',
					autoSize: true,
					closeText: '关闭' ,
					currentText: '今天' ,
					dateFormat: 'yy-mm-dd',
					dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
					dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
					gotoCurrent: true,
					monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
					monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
					nextText: '下个月',
					prevText: '上个月' 
					
					};
			if(obj.attr('minDate')){
				 var strArray=obj.attr('minDate').split('-');
				option.minDate=new Date(strArray[0],parseInt(strArray[1]-1),strArray[2]);  
			}
			if(obj.attr('maxDate')){
				 var strArray=obj.attr('maxDate').split('-');
				option.maxDate=new Date(strArray[0],parseInt(strArray[1]-1),strArray[2]);  
			}
			if(obj.attr('handleEvent')&&dateEventFun){
				option[obj.attr('handleEvent')]=dateEventFun;
			}
			$(function(){
				obj.datepicker(option);
				});
			// 处理必须输入
			var html = "";
			if (obj.attr("required")||obj.attr("isRequired")) {// 必填加入红心标识
				html = "<span id='"+obj.attr('id')+"_requiredIcon'><font color='red'>*</font></span>";
			}
			if (html != "") {
				obj.after(html);
			}
		}
	}
	
	//自定义控件对象
	function zFieldDate() {
		var oldValue = null;//初始值
		var nowValue = null;//当前值
		var modified = false;//是否修改过
		var setValueCalled = false;// 是否已经赋值过
		var obj;// 对应的html中的DOM对象
		var self = this;
		var _dateReg=/^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
		this.init = function(obj1) {
			obj = obj1;
			obj.change(function(){
				var temp = $.trim(obj.val());
				if (temp == "") {
					nowValue = "";
				} else {
					nowValue = temp;
				}
				if(obj.attr('dateGroup')){
					var groupname=obj.attr('dateGroup');
					var objindex=obj.attr('dateIndex');
					var objdate=self.getValue();
					$("input[dategroup="+groupname+"]").each(function(index) {
						if(parseInt($(this).attr('dateIndex'))>objindex){//处理日期显示应在当前输入框之后的
							$(this).datepicker('option', 'minDate', objdate);
						} else if (parseInt($(this).attr('dateIndex'))<objindex){//处理日期显示应在当前输入框之前的
							$(this).datepicker('option', 'maxDate', objdate);
						}
					});
				}
			});
			return this;
		};
//		this.init = function(obj1) {
//			obj = obj1;
//			//输入框只读  by clj 2012-11-18
//			obj.blur(function(e) {//失去焦点时将界面的值加入对象并影响
//				var temp = $.trim(obj.val());
//				if (temp == "") {
//					nowValue = "";
//				} else {
//					nowValue = temp;
//				}
//			});
//			obj.change(function(){
//				alert("OK!");
//				var temp = $.trim(obj.val());
//				if(obj.attr('dateGroup')){
//					var groupname=obj.attr('dateGroup');
//					var objindex=obj.attr('dateIndex');
//					var objdate=new Date(obj.val().replace('-','/'));//by helong 支持IE8对日期的兼容
//					$("input[dategroup="+groupname+"]").each(function(index) {
//						if(parseInt($(this).attr('dateIndex'))>objindex){//处理时间显示应在当前输入框之后的
//							alert("OK!");
//							$(this).datepicker('option', 'minDate', objdate); 
//						}
//						else if(parseInt($(this).attr('dateIndex'))<objindex){//处理时间显示应在当前输入框之前的
//							$(this).datepicker('option', 'maxDate', objdate); 
//						}
//					  });
//				}
//				//格式不正确直接清空
//				if(temp!=''&&!_dateReg.test(temp)){
//					obj.val('');
//				}
//			});
//			
//			return this;
//		};
		//绑定初始值
		this.bindData = function(data) {
			if(data[obj.attr("field")]!=null&&data[obj.attr("field")]!=""){
				data[obj.attr("field")]=data[obj.attr("field")].format(obj.attr("format"));
			}
			self.setValue(data[obj.attr("field")]);
		};
		this.setValue = function(value) {
			if (setValueCalled) {

			} else {
				oldValue = value;
				setValueCalled = true;
			}

			obj.val(value);
			nowValue = value;
		};
		this.getValue = function() {
			return $.trim(obj.val());
		};
		this.exportToData = function(data) {
			//将文本框所得到的字符串格式转换为日期格式
			data[obj.attr("field")] = new Date(obj.val());

		};
		this.isModified = function() {
			if (obj.attr("readOnly")) {
				return false;
			}
			if (oldValue != nowValue) {
				return true;
			}
			return false;
		};
		this.valid = function() {// 验证
			if (obj.attr("required")||obj.attr("isRequired")) {//验证是否输入
				if (!($.trim(obj.val())) || $.trim(obj.val())=="请输入时间" ) {
					return "'" + obj.attr("showName") + "'不能为空！";
				}
			}
			return "";
		};
		return this;
	}
	
	//****************************************************时间控件
$.fn.FieldTime = function(options,callback) {
		var defaultopt = {
			readOnly : false
		};
		defaultopt.onClose=options.onClose==null?null:options.onClose;
		$.extend(defaultopt, options);
		buildFieldTime(defaultopt,callback, this);
		return new zFieldDateAndTime().init(this);
	};
	function buildFieldTime(setting,callback ,obj) {
		var readOnly = obj.attr('readOnly') || setting.readOnly;
//		if (readOnly) {
//			showReadOnly(obj);// 处理是否只读
//		} else {
          option = {		//日期选择器参数
					hourGrid: 4,
					minuteGrid: 10,
					secondGrid:10,
					showButtonPanel: true,
					changeMonth: true,
					changeYear: true,
					buttonText: '日期选择' ,
					buttonImageOnly: true,
					closeText: '关闭',
					dateFormat: 'yy-mm-dd',
	                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
	                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
	                gotoCurrent: true,
	                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
	                monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
	                nextText: '下个月',
	                prevText: '上个月',
	                showOn: "button",
	                buttonImage: "images/calendar.gif",
					buttonImageOnly: true,
					autoSize: true,
					timeText : '时间',
					hourText : '小时',
					minuteText : '分钟',
					secondText : '秒',
					currentText : '现在',
					closeText : '确定',
					ampm: false,//上午下午是否显示  
				    timeFormat: 'hh:mm:ss',//时间模式  
				    yearRange: '2016:2050',
				    stepHour: 1,//拖动时间时的间隔  
				    stepMinute: 5,//拖动分钟时的间隔
				    showHour: true,//是否显示小时，默认是true  
                    showMinute: true,//是否显示分钟，默认是true
                    showSecond:true//是否显示秒数，默认是true
};
			$(function(){
				obj.datetimepicker(option);
				callback();
			});
			// 处理必须输入
			var html = "";
			if (obj.attr("required")||obj.attr("isRequired")) {// 必填加入红心标识
				html = "<span id='"+obj.attr('id')+"_requiredIcon'><font color='red'>*</font></span>";
			}
			if (html != "") {
				obj.after(html);
			}
//		}
	}
	//自定义控件对象
	function zFieldDateAndTime() {
		var oldValue = null;//初始值
		var nowValue = null;//当前值
		var modified = false;//是否修改过
		var setValueCalled = false;// 是否已经赋值过
		var obj;// 对应的html中的DOM对象
		var self = this;
		this.init = function(obj1) {
			obj = obj1;
			obj.change(function(){
				var temp = $.trim(obj.val());
				if (temp == "") {
					nowValue = "";
				} else {
					nowValue = temp;
				}
				if(obj.attr('dateGroup')){
					var groupname=obj.attr('dateGroup');
					var objindex=obj.attr('dateIndex');
					var objdate=self.getValue();
					$("input[dategroup="+groupname+"]").each(function(index) {
						if(parseInt($(this).attr('dateIndex'))>objindex){//处理日期显示应在当前输入框之后的
							//$(this).datetimepicker('option', 'minDate', objdate);
						} else if (parseInt($(this).attr('dateIndex'))<objindex){//处理日期显示应在当前输入框之前的
							//$(this).datetimepicker('option', 'maxDate', objdate);
						}
					});
				}
			});
			return this;
		};
		//绑定初始值
		this.bindData = function(data) {
			if(data[obj.attr("field")]!=null&&data[obj.attr("field")]!=""){
				data[obj.attr("field")]=data[obj.attr("field")].format(obj.attr("format"));
			}
			self.setValue(data[obj.attr("field")]);
		};
		this.setValue = function(value) {
			if (setValueCalled) {

			} else {
				oldValue = value;
				setValueCalled = true;
			}
			obj.val(value);
			nowValue = value;
		};
		this.getValue = function() {
			return $.trim(obj.val());
		};
		this.exportToData = function(data) {

		};
		this.isModified = function() {
			if (obj.attr("readOnly")) {
				return false;
			}
			if (oldValue != nowValue) {
				return true;
			}
			return false;
		};
		this.valid = function() {// 验证
			if (obj.attr("required")||obj.attr("isRequired")) {//验证是否输入
				if (!($.trim(obj.val())) || $.trim(obj.val())=="请输入时间" ) {
					return "'" + obj.attr("showName") + "'不能为空！";
				}
			}
			return "";
		};
		return this;
	}	
	
	Date.prototype.format = function(format) {
	/*
	 * eg:format="yyyy-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
		// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
						- RegExp.$1.length));
	}

	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1
							? o[k]
							: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
	
	
	//****************************************************时间控件
//	$.fn.FieldTime = function(options) {
//		var defaultopt = {
//			readOnly : false
//		};
//		defaultopt.onClose=options.onClose==null?null:options.onClose;
//		$.extend(defaultopt, options);
//		buildFieldTime(defaultopt, this);
//		return new zFieldTime().init(this);
//	};
//	function buildFieldTime(setting, obj) {
//		var readOnly = obj.attr('readOnly') || setting.readOnly;
//		if (readOnly) {
//			showReadOnly(obj);// 处理是否只读
//		} else {
//			//定义输入格式
//			var option={
//					showButtonPanel: true,
//					timeOnly : true ,
//					timeOnlyTitle : '时间选择',
//					showOn: "button",
//					buttonText: '时间选择' ,
//					buttonImage: "images/time.png",
//					buttonImageOnly: true,
//					autoSize: true,
//					timeText : '时间',
//					hourText : '小时',
//					minuteText : '分钟',
//					secondText : '秒',
//					currentText : '现在',
//					closeText : '关闭',
//					onClose : setting.onClose
//					};
//			$(function(){
//				obj.datetimepicker(option);
//			});
//			// 处理必须输入
//			var html = "";
//			if (obj.attr("required")||obj.attr("isRequired")) {// 必填加入红心标识
//				html = "<span id='"+obj.attr('id')+"_requiredIcon'><font color='red'>*</font></span>";
//			}
//			if (html != "") {
//				obj.after(html);
//			}
//		}
//	}
	//自定义控件对象
//	function zFieldTime() {
//		var oldValue = null;//初始值
//		var nowValue = null;//当前值
//		var modified = false;//是否修改过
//		var setValueCalled = false;// 是否已经赋值过
//		var obj;// 对应的html中的DOM对象
//		var self = this;
//		this.init = function(obj1) {
//			obj = obj1;
//			var defaultTime=obj.attr("defaultTime")==null?"00:00":obj.attr("defaultTime");
//			var pattern=/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/;
//			obj.blur(function(e) {//失去焦点时将界面的值加入对象并影响
//				var temp = $.trim(obj.val());
//				if (temp == "") {
//					nowValue = "";
//				} else {
//					nowValue = temp;
//				}
//			});
//			obj.change(function(){
//				var temp = $.trim(obj.val());
//				if(temp==''){
//					nowValue = defaultTime;
//					obj.val(defaultTime);
//					return;
//				}
//				if(!pattern.test(temp)){
//					$(obj).val(oldValue);
//					nowValue=oldValue;
//				}
//			});
//			obj.focus(function(){
//				oldValue=$(obj).val();
//			});
//			return this;
//		};
//		//绑定初始值
//		this.bindData = function(data) {
//			if(data[obj.attr("field")]!=null&&data[obj.attr("field")]!=""){
//				data[obj.attr("field")]=data[obj.attr("field")].format(obj.attr("format"));
//			}
//			self.setValue(data[obj.attr("field")]);
//		};
//		this.setValue = function(value) {
//			if (setValueCalled) {
//
//			} else {
//				oldValue = value;
//				setValueCalled = true;
//			}
//
//			obj.val(value);
//			nowValue = value;
//		};
//		this.getValue = function() {
//			return $.trim(obj.val());
//		};
//		this.exportToData = function(data) {
//
//		};
//		this.isModified = function() {
//			if (obj.attr("readOnly")) {
//				return false;
//			}
//			if (oldValue != nowValue) {
//				return true;
//			}
//			return false;
//		};
//		this.valid = function() {// 验证
//			if (obj.attr("required")||obj.attr("isRequired")) {//验证是否输入
//				if (!($.trim(obj.val())) || $.trim(obj.val())=="请输入时间" ) {
//					return "'" + obj.attr("showName") + "'不能为空！";
//				}
//			}
//			return "";
//		};
//		return this;
//	}
	
	//**************************************************** 页面局部打印控件
	$.fn.FieldPrint = function(options) {
		var defaultopt = {
			readOnly : false
		};
		$.extend(defaultopt, options);
		//doPrint(this);
		return new zFieldPrint().init(this);
	};
	
	function zFieldPrint(){
		this.init = function(obj1) {
			obj = obj1;
			obj.click(function(){
				var printType = obj.attr('printType');
				if(printType == "grid"){
					var grid = $("#"+obj.attr('needPrintArea'));
					var tableName = obj.attr('tableName');
					var notPrint = new Array();
					if(null != obj.attr('notPrint')){
						var str = obj.attr('notPrint');
						notPrint = str.split(",");
					}
					var num = notPrint.length;
					var setting = printSetting(obj);
					var ids = grid.jqGrid('getDataIDs');
					if(ids.length==0){
						alert("无打印数据！");
						return;
					} else {
						var datas = [];
						var titles = grid.jqGrid("getGridParam").colNames;
						var modles = grid.jqGrid("getGridParam").colModel;
						if(num != 0){
							for(var i = 0;i<num;i++){
								for(var j = modles.length-1;j>=0;j--){
									if(modles[j].name == notPrint[i]){
										modles[j].name = "";
										titles[j] = "";
									}
								}
							}
						}
						var data = {};
						for(var i = 0;i<ids.length;i++){
							data = grid.jqGrid('getRowData',ids[i]);
							datas.push(data);
						}
						printValue(datas,titles,modles,tableName,setting,num);
					}
					if(null == basepath){
						alert("未设置'basepath',请在页面设置该变量");
						return;
					}else{
						window.open(basepath+"plugins/print/printPage.jsp?param=grid");
					}
				} else {
					var printHTML = $("#"+obj.attr('needPrintArea')).html();
					var setting = printSetting(obj);
					printForDiv(printHTML,setting);
				}
			});
			return this;
		};
	}
	
	function printSetting(element){
		var setting = {};
		var defultHeader = "";//页眉
		var defultFooter = "";//页脚
		var defultPortrait = 0;//横向-0、竖向-1 打印
		var defultLeftMargin = 0;//左边距
		var defultTopMargin = 0;//上边距
		var defultRightMargin = 0;//右边距
		var defultBottomMargin = 0;//下边距
		var defultPaperSize = "A4";//纸张
		if(null != element.attr('header')){
			defultHeader = element.attr('header');
		}
		setting.header = defultHeader;
		if(null != element.attr('footer')){
			defultFooter = element.attr('footer');
		}
		setting.footer = defultFooter;
		if(null != element.attr('portrait')){
			defultPortrait = element.attr('portrait');
		}
		setting.portrait = defultPortrait;
		if(null != element.attr('leftMargin')){
			defultLeftMargin = element.attr('leftMargin');
		}
		setting.leftMargin = defultLeftMargin;
		if(null != element.attr('topMargin')){
			defultTopMargin = element.attr('topMargin');
		}
		setting.topMargin = defultTopMargin;
		if(null != element.attr('rightMargin')){
			defultRightMargin = element.attr('rightMargin');
		}
		setting.rightMargin = defultRightMargin;
		if(null != element.attr('bottomMargin')){
			defultBottomMargin = element.attr('bottomMargin');
		}
		setting.bottomMargin = defultBottomMargin;
		if(null != element.attr('paperSize')){
			defultPaperSize = element.attr('paperSize');
		}
		setting.paperSize = defultPaperSize;
		return setting;
	}
	
	//************************************************************  数字控件
	$.fn.FieldNumber = function(options) {
		var defaultopt = {
			readOnly : false
		};
		$.extend(defaultopt, options);
		buildFieldNumber(defaultopt, this);
		return new zFieldNumber().init(this);
	};
	function buildFieldNumber(setting, obj) {
		var readOnly = obj.attr('readOnly') || setting.readOnly;
		if (readOnly) {
			showReadOnly(obj);// 处理是否只读
		} else {
			// 处理必须输入
			var html = "";
			if (obj.attr("required")||obj.attr("isRequired")) {// 必填加入红心标识
				html = "<span id='"+obj.attr('id')+"_requiredIcon'><font color='red'>*</font></span>";
			}
			//处理只能输入数字
			obj.attr("onkeyup","value=value.replace(/[^\\d]/g,'')");//
			html+="<font color='red'>只能输入数字</font>";
			if (html != "") {
				obj.after(html);
			}
		    //处理数字输入最大值
			if(obj.attr("maxNumber")||obj.attr("minNumber")){
				obj.attr("onkeyup","if(value&&(value>=$(this).attr('maxNumber')||value<=$(this).attr('minNumber'))){alert('你输入的数字超出范围');}");
				
			}
		}
	}
	//自定义控件对象
	function zFieldNumber() {
		var oldValue = null;//初始值
		var nowValue = null;//当前值
		var modified = false;//是否修改过
		var setValueCalled = false;// 是否已经赋值过
		var obj;// 对应的html中的DOM对象
		var self = this;
		this.init = function(obj1) {
			obj = obj1;
			obj.blur(function(e) {//失去焦点时将界面的值加入对象
				var temp = $.trim(obj.val());
				if (temp == "") {
					nowValue = "";
				} else {
					nowValue = temp;
				}
			});
			return this;
		};
		//绑定初始值
		this.bindData = function(data) {
			self.setValue(data[obj.attr("field")]);
		};
		this.setValue = function(value) {
			if (setValueCalled) {

			} else {
				oldValue = value;
				setValueCalled = true;
			}

			obj.val(value);
			nowValue = value;
		};
		this.getValue = function() {
			return $.trim(obj.val());
		};
		this.exportToData = function(data) {
			data[obj.attr("field")] = nowValue;
				
		};
		this.isModified = function() {
			if (obj.attr("readOnly")) {
				return false;
			}
			if (oldValue != nowValue) {
				return true;
			}
			return false;
		};
		this.valid = function() {// 验证
			//必填
			if (obj.attr("required")||obj.attr("isRequired")) {
				if (!($.trim(obj.val()))) {
					return "'" + obj.attr("showName") + "'不能为空！";
				}
			}
			//数字
			if(isNaN($.trim(obj.val()))){
				return "'"+obj.attr("showName")+"'只能输入数字";
			}
			return "";
		};
		return this;
	}
	//**************************************************** 下拉列表
	$.fn.FieldSelect = function(options) {
		var defaultopt = {
			readOnly : false
		};
		$.extend(defaultopt, options);
		buildFieldSelect(defaultopt, this);
		return new zFieldSelect().init(this);
		
	};
	function buildFieldSelect(setting, obj) {
		var readOnly = obj.attr('readOnly') || setting.readOnly;
		if (readOnly) {
			showReadOnly(obj);// 处理是否只读
		} else {
			if(obj.attr("parentCode")){//从码表为下拉列表初始化选项
				parentCode=obj.attr("parentCode");
				dwr.engine.setAsync(false);
				CodeTableService.findMapByPropertiesQuick(['CodeTableCode','CodeTableName'],"ParentCode='"+parentCode+"'",false,function(data){
					selectContainer=$(obj);		
			    	for ( var i in data){
			    	  var option = $('<option></option>').text(data[i].CodeTableName).val(data[i].CodeTableCode); 
			    	  selectContainer.append(option); 
			    	}	
				});
				dwr.engine.setAsync(true);
			}
			
			if(obj.attr("parentCodeLike")){//从码表为下拉列表初始化选项
				parentCodeLike=obj.attr("parentCodeLike");
				dwr.engine.setAsync(false);
				CodeTableService.findMapByPropertiesQuick(['CodeTableCode','CodeTableName'],"ParentCode like '"+parentCodeLike+"%'",false,function(data){
					selectContainer=$(obj);		
			    	for ( var i in data){
			    	  var option = $('<option></option>').text(data[i].CodeTableName).val(data[i].CodeTableCode); 
			    	  selectContainer.append(option); 
			    	}	
				});
				dwr.engine.setAsync(true);
			}
			// 处理必须输入
			var html = "";
			if (obj.attr("required")||obj.attr("isRequired")) {// 必填加入红心标识
				html = "<font color='red'>*</font>";
			}
			if (html != "") {
				obj.after(html);
			}
		}
	}
	// 自定义的控件对象
	function zFieldSelect() {
		var oldValue = null;//初始值
		var nowText = null;//当前显示数据
		var nowValue = null;//当前值
		var modified = false;//是否修改过
		var setValueCalled = false;// 是否已经赋值过
		var obj;// 对应的html中的DOM对象
		var self = this;
		this.init = function(obj1) {
			obj = obj1;
			obj.blur(function(e) {//失去焦点时将界面的值加入对象
				var temp = $.trim(obj.val());
				if (temp == "") {
					nowValue = "";
				} else {
					nowValue = temp;
				}
			});
			return this;
		};
		//绑定初始值
		this.bindData = function(data) {
			var fielddata=data[obj.attr('field')];//value值
			oldValue=fielddata;
		    $(obj).find("option[value="+fielddata+"]").attr("selected",true);//改变选中项
		    self.setValue($.trim(obj.val()));
		};
		this.setValue = function(value) {
			if (setValueCalled) {

			} else {
				oldValue = value;
				setValueCalled = true;
			}
			nowValue = value;
		};
		this.getValue = function() {
			if (obj.attr("refer")) {
				return nowValue;
			} else {
				return $.trim(obj.val());
			}
		};
		this.exportToData = function(data) {
			if (obj.attr("refer")) {
				data[obj.attr("field")] = nowValue;
			} else {
				data[obj.attr("field")] = obj.val();
			}
		};
		this.isModified = function() {
			if (obj.attr("readOnly")) {
				return false;
			}
			if (oldValue != nowValue) {
				return true;
			}
			return false;
		};
		this.valid = function() {// 验证
			if (obj.attr("required")||obj.attr("isRequired")) {
				if (!($.trim(obj.attr("value")))) {
					return "'" + obj.attr("showName") + "'不能为空！";
				}
			}
			return "";
		};
		return this;
	}
	// 控件只读
	function showReadOnly(obj) {
		if(obj[0].tagName=="SELECT"){
			obj.not(":selected").attr("disabled", "disabled");
			obj.css("border", "0");
		}else{
			obj.attr("readOnly", true);
			obj.css("border", "0");
		}
	}
}(jQuery));