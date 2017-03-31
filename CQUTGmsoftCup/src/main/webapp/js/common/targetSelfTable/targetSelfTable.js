/**
 * 指标自定义表格
 */
$(function(){
	var tableData=function(setting){
		this.defaults={
			id:"selfTable",
			condition:"1=1",
			colNames:["一级指标","二级指标","主要观察点","指标说明"],
			colValues:[{
				id:true,
				name:"firstTarget",
				operate:null,
				score:true
			},{
				id:true,
				name:"secondTarget",
				operator:null,
				score:true
			},{
				id:true,
				name:"threeOrFourTarget",
				operator:null,
				score:true
			},{
				id:true,
				name:"explain",
				operator:null
			}],
			firstTarget:[],
			secondTarget:[],
			threeTarget:[],
			fourTarget:[],
			allTarget:[]
		};
		this.tableSettings=$.extend(defaults,setting);
	};
	
	tableData.prototype={
		_id:null,
		_op:null,
		init:function(){
			this._id=this.tableSettings.id;
			_op=this;
			if(this.validation){
				//加载指标
				this.loadTarget();
				//初始表格		
				this.initElement();		
			}else{
				jAlert("colNames与colValues长度不等");
			}				
		},
		//加载数据
		loadTarget:function(){
			TargetService.findTargets([],this.tableSettings.condition,
			                                   'showOrder','asc',true,function(data){
									this.dealData(data);
											
			});
			
		},
		//处理数据
		dealData:function(data){
			if(data!=null&&data!=""){
				this.tableSettings.allTarget=data;
				for(var i=0;i<data.length;i++){
					var targetLevel=data[i]['targetLevel'];
					switch(targetLevel){
						case "1":this.tableSettings.firstTarget.push(data[i]);break;
						case "2":this.tableSettings.secondTarget.push(data[i]);break;
						case "3":this.tableSettings.threeTarget.push(data[i]);break;
						case "4":this.tableSettings.fourTarget.push(data[i]);break;
					}
				}				
			}
		},
		
		validation:function(){
			if(this.tableSettings.colNames.length==this.tableSettings.colValues.length){
				return true;
			}else{
				return false;
			}
		},
		initElement:function(){
			if(this.tableSettings.allTarget.length==0){
				var html="";    
			}else{
				var html="<table class='table-content'cellspacing='0px' border='0'><tr class='table-th-change'></tr></table>";				   
			}						
			 
			$("#"+this._id).empty().append(html);
		},			
	};

});
	