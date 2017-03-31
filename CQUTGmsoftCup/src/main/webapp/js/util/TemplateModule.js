  //数据定义   
    var Page={};
    Page.Data={};
    Page.CustomFlag={};
    Page.Data.exchangeData;
    Page.CustomFlag.disableLoadData=false;//是否阻止页面加载（全部由用户自加载）
    Page.CustomFlag.disableInitComponent=false;//是否阻止页面加载（全部由用户自加载）
    Page.Util={};
    Page.Util.callIndex=0;
    Page.Util.callOver=true;
    Page.Util.allCallFinish=true;
    Page.Util.CallFlagBuffer=[];
    Page.components={};
    Page.Data.f1_data=null;
    Page.Data.userType_datas=null;
    
    var UserPackage={};
	UserPackage.PageCall={};
	
    
    Page.Util.initCallService=function(service,params,callback){
    	Page.Util.callOver=false;
    	Page.Util.allCallFinish=false;	
    	var nowIndex=Page.Util.callIndex;
    	Page.Util.callIndex++;
    	Page.Util.CallFlagBuffer[nowIndex]=false;
    	params[params.length]=function(data){    		
    		callback.apply(Page,[data]);
    		Page.Util.CallFlagBuffer[nowIndex]=true;
    		if(Page.Util.callOver){
    			Page.Util.checkCallFinish();//检查所有调用是否已经返回
    		}
    	}
    	service.apply(Page,params);    	
    };
	Page.Util.checkCallFinish=function(){
		if(Page.Util.callOver&&!Page.Util.allCallFinish){//当所有调用都启动且尚未处理时开始检查    	  
    	  for(var i=0;i<Page.Util.CallFlagBuffer.length;i++){    		 
    		  if(!Page.Util.CallFlagBuffer[i]){//有调用尚未返回    			  
    			  return;
    		  }
    	  }    	 
    	  //所有调用都已返回
	   	  Page.Util.allCallFinish=false;
	      //开始加载数据
	  	  if(UserPackage.PageCall.beginLoadData){
	  		  UserPackage.PageCall.beginLoadData();
	  	  }
	  	  //调用页面加载
	  	  if(Page.loadData){    		
	  		  Page.loadData();
	  	  }
        }
    };