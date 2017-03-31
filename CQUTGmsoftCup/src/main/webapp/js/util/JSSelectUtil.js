/*
 *
	js操作select相关方法
	* 有一些错误,暂时只供参考
	
	1.判断select选项中 是否存在Value="paraValue"的Item
	2.向select选项中 加入一个Item
	3.从select选项中 删除一个Item
	4.修改select选项中 value="paraValue"的text为"paraText"
	5.设置select中text="paraText"的第一个Item为选中
	6.设置select中value="paraValue"的Item为选中
	7.得到select的当前选中项的value
	8.得到select的当前选中项的text
	9.得到select的当前选中项的Index
	10.清空select的项
	11.判断select选项是否为空
 */

//1.判断select选项中 是否存在Value="paraValue"的Item  
function jsSelectIsExitItem(objSelect,objItemValue)  
{  
    var isExit = false;  
    for(var i=0;i<objSelect.options.length;i++)  
    {  
        if(objSelect.options[i].value == objItemValue)  
        {  
            isExit = true;  
            break;  
        }  
    }       
    return isExit;  
}  
//1.判断select选项中 是否存在Text="Text"的Item  
function jsSelectIsExitItem(objSelect,objItemText)  
{  
    var isExit = false;  
    for(var i=0;i<objSelect.options.length;i++)  
    {  
        if(objSelect.options[i].Text == objItemText)  
        {  
            isExit = true;  
            break;  
        }  
    }       
    return isExit;  
}  
  
//2.向select选项中 加入一个Item  
function jsAddItemToSelect(objSelect,objItemText,objItemValue)  
{  
    //判断是否存在  
    if(jsSelectIsExitItem(objSelect,objItemValue))  
    {  
        //alert("该Item的Value值已经存在");  
    }  
    else  
    {  
        var varItem = new Option(objItemText,objItemValue);  
//      objSelect.options[objSelect.options.length] = varItem;  
        objSelect.options.add(varItem);  
//        alert("成功加入");  
    }     
}  
  
//3.从select选项中 删除一个Item  
function jsRemoveItemFromSelect(objSelect,objItemValue)  
{  
    //判断是否存在  
    if(jsSelectIsExitItem(objSelect,objItemValue))  
    {  
        for(var i=0;i<objSelect.options.length;i++)  
        {  
            if(objSelect.options[i].value == objItemValue)  
            {  
                objSelect.options.remove(i);  
                break;  
            }  
        }         
//        alert("成功删除");             
    }  
    else  
    {  
//        alert("该select中 不存在该项");  
    }     
}  
  
//4.修改select选项中 value="paraValue"的text为"paraText"  
function jsUpdateItemToSelect(objSelect,objItemText,objItemValue)  
{  
    //判断是否存在  
    if(jsSelectIsExitItem(objSelect,objItemValue))  
    {  
        for(var i=0;i<objSelect.options.length;i++)  
        {  
            if(objSelect.options[i].value == objItemValue)  
            {  
                objSelect.options[i].text = objItemText;  
                break;  
            }  
        }         
//        alert("成功修改");             
    }  
    else  
    {  
//        alert("该select中 不存在该项");  
    }     
}  
         
//5.设置select中text="paraText"的第一个Item为选中  
function jsSelectItemByValue(objSelect,objItemText)  
{     
    //判断是否存在  
    var isExit = false;  
    for(var i=0;i<objSelect.options.length;i++)  
    {  
        if(objSelect.options[i].text == objItemText)  
        {  
            objSelect.options[i].selected = true;  
            isExit = true;  
            break;  
        }  
    }       
  return isExit;   
}  
  
//6.设置select中value="paraValue"的Item为选中  
//document.all.objSelect.value = objItemValue;  
function setSelectItem(objSelect,objItemValue)  
{  
	var isExit = false;  
    for(var i=0;i<objSelect.options.length;i++)  
    {  
        if(objSelect.options[i].value == objItemValue)  
        {  
            objSelect.options[i].selected = true;  
            isExit = true;  
            break;  
        }  
    }
	     
    return isExit;
}  
  
//7.得到select的当前选中项的value  
//var currSelectValue = document.all.objSelect.value;  
function getSeletedItemValue(objSelect)
{
	var currSelectValue = document.all.objSelect.value;  
	return currSelectValue;
}
  
//8.得到select的当前选中项的text  
//var currSelectText = document.all.objSelect.options[document.all.objSelect.selectedIndex].text;  
function getSeletedItemText(objSelect)
{
	var currSelectText = document.all.objSelect.options[document.all.objSelect.selectedIndex].text;  
	return currSelectText;
}
  
//9.得到select的当前选中项的Index  
//var currSelectIndex = document.all.objSelect.selectedIndex;  
function getSelectedIndex(objSelect)
{
	var currSelectIndex = document.all.objSelect.selectedIndex;  
	return currSelectIndex;
}
  
//10.清空select的项  
// document.all.objSelect.options.length = 0;  
function deleteAllSelectItem(objSelect)
{
	document.all.objSelect.options.length = 0;  
}
//11.判断select选项是否为空
function selectOpitonIsEmpty(objSelect)
{
	return document.all.objSelect.options.length == 0; 
}