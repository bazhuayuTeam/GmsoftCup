/**
 * 只是模板，复制过去自己添加内容
 */
function isIECheck() {
	var isIE = !!window.ActiveXObject;
	var isIE6 = isIE && !window.XMLHttpRequest;
	var isIE8 = isIE && !!document.documentMode;
	var isIE7 = isIE && !isIE6 && !isIE8;
	
	if (isIE) {//ie
		if (isIE6) {//ie6
			
		} else if (isIE7) {//ie7

		} else if (isIE8) {//ie8

		}
	} else {//ff,chrome so on
			
	}
}