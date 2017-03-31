var stateUtil = {};
stateUtil.transfor = function(item, key){
	return stateUtil.getValue(item , key );
};

stateUtil.getValue = function(item , key){
	var value = "";
	var tempList = stateUtil.stateMap[item];
	for(var index in tempList){
		var map = tempList[index];
		if(map["key"] == key){
			value = map["value"];
			break;
		}
	}
	return value;
};
stateUtil.getStateByEnName = function(item , enName){
	var key = "";
	var tempList = stateUtil.stateMap[item];
	for(var index in tempList){
		var map = tempList[index];
		if(map["enName"] == enName){
			key = map["key"];
			break;
		}
	}
	return key;
}

stateUtil.getEnNameByKey = function(item , key){
	var enName = "";
	var tempList = stateUtil.stateMap[item];
	for(var index in tempList){
		var map = tempList[index];
		if(map["key"] == key){
			enName = map["enName"];
			break;
		}
	}
	return enName;
}

/**
 * 获取查询报表状态
 * item 要取的数据， itemType要取的类型, 1代表正常的， 0代表不正常的，非流程的
 * @return {TypeName} 
 * 例：stateUtil.getCensusState("case_state", 1);
 */
stateUtil.getCensusState = function(item, itemType) {
	var tempList = stateUtil.stateMap[item];
	var stateString = "";
	if(itemType == 1) {
		for(var index in tempList){
			var map = tempList[index];
			if(map["key"] >= 0){
				stateString += map["key"] + ", ";
			}
		}
	}else if(itemType == 0){
		for(var index in tempList){
			var map = tempList[index];
			if(map["key"] < 0){
				stateString += map["key"] + ", ";
			}
		}
	}
	return stateString.substring(0,stateString.length-2);
}

stateUtil.stateMap = {
	connect : [
		{key:-1,value:'连接失败',enName:"connectFailed"},
		{key:0,value:'连接成功但未发现主控',enName:"NoFoundControl"},
		{key:1,value:'连接成功',enName:"connectSuccess"}
	],
   	case_operationtype:[
   		 {key:-6,value:'接受报案请求',enName:"receiveCase"},
   	    {key:-5,value:'撤销报案单',enName:"cancel"},
	    {key:-4,value:'处理督办',enName:"processSupervision"},
	    {key:-3,value:'接受转移申请',enName:"acceptTransfer"},
	    {key:-2,value:'申请转移',enName:"applicaitonTransfer"},
	    {key:-1,value:'作废报案单',enName:"invalidSingle"},
	    {key:0,value:'新增报案单',enName:"newReport"},
	    {key:1,value:'转为暂存',enName:"temporaryStorage"},
	    {key:2,value:'修改报案单',enName:"revision"},
	    {key:3,value:'提交报案单',enName:"submit"},
	    {key:4,value:'进行调度',enName:"scheduling"},
	    {key:5,value:'指派给技师  ',enName:"appointDriver"},
	    {key:6,value:'指派给供应商  ',enName:"appointSupplier"},
	    {key:7,value:'拒绝接受调度',enName:"refuseSchedul"},
	    {key:8,value:'接受调度',enName:"acceptSchedul"},
	    {key:9,value:'开始服务',enName:"startServe"},
	    {key:10,value:'到达事故地点',enName:"arriveAccident"},
	    {key:11,value:'到达目的地',enName:"arriveTermini"},
	    {key:12,value:'计价选择',enName:"selectValuation "},
	    {key:13,value:'账务管理',enName:"financialManagement"},
	    {key:14,value:'进行结案',enName:"achieveCase"}
   	]
}

stateUtil.getMapByRange = function(item,range){
	var list = new Array();
	var tempList = stateUtil.stateMap[item];
	if(range == ""){
		return tempList;
	}
	else if(range.indexOf("-") != -1){//用的范围表示方式
		var min = range.split('-')[0];
		var max = range.split('-')[1];
		for(var index in tempList){
			var map = tempList[index];
			if(map["key"]<min || map["key"]>max){
				continue ;
			}
			else{
				list.push(map);
			}
		}
	}
	
	else if(range.indexOf(",") != -1){//用的列举表示
		for(var index in tempList){
			var map = tempList[index];
			if(range.indexOf(map["enName"]) != -1){
				list.push(map);
			}
		}
	}
	
	else if(range.indexOf("!") != -1){//“非”表示
		for(var index in tempList){
			var map = tempList[index];
			if(range.indexOf(map["enName"]) == -1){
				list.push(map);
			}
		}
	}
	
	
	else{
		
	}
	
	return list;
};

