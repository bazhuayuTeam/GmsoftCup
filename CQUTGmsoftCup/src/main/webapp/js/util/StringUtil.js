/**
 * 字符操作相关
 */
/**
 * 全局替换
 * @author lulin
 * @param {Object} str 原字符串
 * @param {Object} src 要被替换的字符串
 * @param {Object} dest 用来替换的字符串
 * @return {TypeName} 返回替换后的字符串
 */
function replace(str, src, dest) {
	re = new RegExp(src, "g");
	var newStr = str.replace(re, dest);
	return newStr;
}

/**
 * 邮箱验证
 * @author lulin
 * @param {string} val 传入输入的邮箱字符串
 * @return {boolean} 通过： true,不通过: false
 */
function isEmail(val,flag) {
	//var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/;
	if(flag&&''==val)
		return true;
	var emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
	if(emailReg.test(val))
		return true;
	alert("请填写正确的邮箱信息！");
	return false;
}

/**
 * 手机，电话号码的验证
 * @author lulin
 * @param {string} val 需要验证的手机号码 flag 是否可以为空
 * @return {boolean} 通过： true,不通过: false
 */
function isMobile(val,flag){
	//兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)
	if(flag&&''==val)
		return true;
	var telReg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	//验证手机号
	var mobileReg = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|15[0-9]\d{8}$/;
	if(mobileReg.test(val) || telReg.test(val))
		return true;
	alert("请填写正确的电话信息！");
	return false;
}

/**
 * 整数验证
 * @author lulin
 * @param {string} val 需要验证的数字字符串
 * @param {boolean} isEqualsZero 是否包含0
 * @param {string} str 根据业务确定
 * @return {boolean} 通过：true, 不通过：false 
 */
function isThanInteger(val, isEqualsZero, str) {
	var reg;
	if(isEqualsZero) {//验证一个输入是否是正整数，包含零
		reg = /^(0|([1-9]\d*))$/; 		
	} else {//验证一个输入是否是正整数，不包含零
		reg = /^([1-9]\d*)$/;	
	}
  	var temp = $.trim(val);
  	if(reg.test(temp))
	  	return true;
  	alert("\"" + str + "\"请填写大于0的数字！");
	return false;
}

function getNumberBySemesterName(semesterName){
	//学期的第二个汉字
	var foo = semesterName.charAt(1);
	console.debug(foo);
	switch(foo){
		case "一":return 1;
		case "二":return 2;
		case "三":return 3;
	}
	return 1;
}









