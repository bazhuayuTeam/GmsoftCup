function banBackSpace(e){     
    var ev = e || window.event;
    var obj = ev.target || ev.srcElement;     
    var t = obj.type || obj.getAttribute('type');
    var vReadOnly = obj.getAttribute('readonly');  
    var vEnabled = obj.getAttribute('enabled');  
    vReadOnly = (vReadOnly == null) ? false : vReadOnly;  
    vEnabled = (vEnabled == null) ? true : vEnabled;  
    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   
                && (vReadOnly==true || vEnabled!=true))?true:false;  
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")  
                ?true:false;          
    if(flag2){  
        return false;  
    }  
    if(flag1){     
        return false;     
    }     
}  
document.onkeypress=banBackSpace;  
document.onkeydown=banBackSpace;