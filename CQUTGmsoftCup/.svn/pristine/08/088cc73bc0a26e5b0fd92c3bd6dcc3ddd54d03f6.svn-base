/**
 * 
 * 导出报表
 * @author lulin
 * @param {Object} title
 * @param {Object} flag
 * @param {Object} tableId
 * @param {Object} isAddedFoot
 */
var ExportTable= function (title, flag, tableId, isAddedFoot) {
	try {
		var $curTb = null;
        if (flag != '') {
			$curTb = $("#" + flag + "_" + tableId);
		} else {
    		$curTb = $("#" + tableId);
		}
		var curTbl = $curTb[0];
		var Lenc = curTbl.rows(0).cells.length;
		var dateTime = $("#" + flag + "_txtBeginDateCtl").val();
		var year = new Date().getYear();
		var ds = null;
		if (dateTime.indexOf('-') >= 0) {
			ds = dateTime.split('-');
    		year = ds[0];
		} else if (dateTime.indexOf('/') >= 0) {
			ds = dateTime.split('/');
    		year = ds[0];
		}
		var tbTitle = "<table id='tbTitle'><tr><td  style='font-weight:bold;text-align:left'>" + title;
		tbTitle += "(" + year + "-" + curTbl.rows(0).cells(1).innerText + " 到 " + year + "-" + curTbl.rows(0).cells(Lenc - 1).innerText + ")</td></tr>";
		tbTitle += "</table>";
		$curTb.prepend(tbTitle);

		if (isAddedFoot) {
    		var tbFoot = "<table id='tbColor'  cellspacing='0' align='Left'><tr><td></td></td>" +
    					 	"<tr><td style='text-align:left;' width='60'>颜色说明</td>" +
								"<td style='background-color:#7f7f7f;text-align:center;' width='100'>时间已过</td>" +
								"<td  width='10'></td>" +
								"<td style='background-color:#604a7b;text-align:center;' width='100'>已审查</td>" +
								"<td  width='10'></td>" +
								"<td style='background-color:#31859c;text-align:center;' width='100'>已审核</td>" +
								"<td  width='10'></td>" +
								"<td style='background-color:#4f6228;text-align:center;' width='100'>已审阅</td>" +
								"<td  width='10'></td>" +
								"<td style='background-color:red;text-align:center;' width='100'>冲突数据</td>" +
							"</tr> " +
						 "</table>";

    		$curTb.append(tbFoot);
		}
		var oXL = new ActiveXObject("Excel.Application");
		var oWB = oXL.Workbooks.Add();
		var oSheet = oWB.ActiveSheet;
		var sel = document.body.createTextRange();
		sel.moveToElementText(curTbl);
		sel.select();
		sel.execCommand("Copy");
		oSheet.Paste();

		var sheetR = oSheet.UsedRange.Rows.Count;
		var sheetC = oSheet.UsedRange.Columns.Count;
		oSheet.Range(oSheet.Cells(1, 1), oSheet.Cells(sheetR, sheetC)).Borders.LineStyle = 1;
		oSheet.Range(oSheet.Cells(1, 1), oSheet.Cells(sheetR, sheetC)).Font.Size = 9;
		
		oSheet.Range(oSheet.Cells(1, 1), oSheet.Cells(1, sheetC)).Merge();
		
		oSheet.Cells(2, 1).HorizontalAlignment = 2;
		oSheet.Cells(2, 1).VerticalAlignment = 2;
		oSheet.Cells(2, 1).Font.Bold = true;
		oSheet.Cells(2, 1).Font.Size = 12;
		oSheet.Rows(2 + ":" + 2).RowHeight = 30;
		oSheet.Range(oSheet.Cells(2, 1), oSheet.Cells(2, sheetC)).Merge();
		if (isAddedFoot) {
		    oSheet.Range(oSheet.Cells(sheetR - 1, 1), oSheet.Cells(sheetR - 1, sheetC)).Merge();
		}
		oXL.Visible = true;
		$("#tbTitle").remove();
		$("#tbColor").remove();

		alert("导出成功!");
	} catch(e) {
	    alert("导出失败!");
	} finally {
	    window.setInterval("CleanUp();", 1);
	}
}

var CleanUp = function () {
	window.clearInterval(idTmr);
	CollectGarbage();
}