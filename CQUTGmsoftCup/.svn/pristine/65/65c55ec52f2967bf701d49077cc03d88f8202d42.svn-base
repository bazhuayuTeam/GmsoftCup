// 全局对象
var BindUtil = {};
// 用于存放页面初始化的表单数据
BindUtil.pageData = {};
// 表单数据对象中的属性数组
BindUtil.propertiesArray = [];
// 绑定函数
BindUtil.bind = function(oldData, properties) {
	/*
	 * oldData为传入的初始化数据（是一个对象） properties为对象中的属性数组
	 */
	BindUtil.pageData = oldData;
	BindUtil.propertiesArray = properties;
}
// 判断是否改变
BindUtil.isChanged = function(newData) {
	/*
	 * newData为传入的新的数据 对象
	 */
	var length = BindUtil.propertiesArray.length;
	if (length > 0) {
		for (var index = 0; index < length; index++) {
			var property = BindUtil.propertiesArray[index];
			if (BindUtil.pageData[property] != newData[property]) {
				return true;
			}
		}
		return false;
	}
}
// 获得改变的参数元素
BindUtil.getChangedParams = function(newData) {
	/*
	 * newData为传入的新的数据 对象
	 */
	var changedParams=[];
	var length = BindUtil.propertiesArray.length;
	if (length > 0) {
		for (var index = 0; index < length; index++) {
			var property = BindUtil.propertiesArray[index];
			if (BindUtil.pageData[property] != newData[property]) {
				changedParams.push(property);
			}
		}
	}
}