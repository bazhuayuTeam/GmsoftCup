/*
 * Copyright (c) 2007 Josh Bush (digitalbush.com)
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:

 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE. 
 */

/*
 * Version: Beta 1
 * Release: 2007-06-01
 */
(function($) {
	var map = new Array();
	$.Watermark = {
		ShowAll : function() {
			for ( var i = 0; i < map.length; i++) {
				if (map[i].obj.val() == "") {
					map[i].obj.val(map[i].text);
					map[i].obj.css("color", map[i].WatermarkColor);
				} else {
					map[i].obj.css("color", map[i].DefaultColor);
				}
			}
		},
		HideAll : function() {
			for ( var i = 0; i < map.length; i++) {
				if (map[i].obj.val() == map[i].text) {
					map[i].obj.val("");
				}
			}
		}
	}

	$.fn.Watermark = function(text, color) {
		if (!color) {
			color = "#aaa";
		}

		/*
		 * 此处为修复IE8 BUG所改，修改人：lijun 联系：8427003@qq.com
		 * BUG描述：当input的css属性font-family为英文字体时,在value值"全为英"与"含中文",
		 * INPUT会向上下移动1xp，整个布局可能会出现轻微波动 BUG原因: 不同字符集下，默认字体不同，
		 * 英文字体在控制中文字符时，会引发BUG 解决方法：这里设置font-family 为 "中文字体" font-size：改为1.0em
		 */
		// bug修改开始
		$(this).css({
			//"font-family" : "宋体",
			"font-size" : "1.0em",
			///"padding-top" : "2px",
			//"padding-bottom" : "2px"
		});
		// bug修改结束
		return this.each(function(index, cur) {
			var input = $(cur);
			var defaultColor = input.css("color");
			map[map.length] = {
				text : text,
				obj : input,
				DefaultColor : defaultColor,
				WatermarkColor : color
			};
			function clearMessage() {
				if (input.val() == text) {
					input.val("");
				}
				input.css("color", defaultColor);
			}

			function insertMessage() {
				if ((input.val().length == 0) || (input.val() == text)) {
					input.val(text);
					input.css("color", color);
				} else {
					input.css("color", defaultColor);
				}
			}

			input.focus(clearMessage);
			input.blur(insertMessage);
			input.change(insertMessage);

			insertMessage();
		});
	};

	$.fn.watermark = function(text, color) {
		// alert("dsfsdf");
		if (!color) {
			color = "#aaa";
		}
		var input = $(this);
		var defaultColor = input.css("color");
		function clearMessage() {
			if (input.val() == text) {
				input.val("");
			}
			input.css("color", defaultColor);
		}
		function insertMessage() {
			if ((input.val().length == 0) || (input.val() == text)) {
				input.val(text);
				input.css("color", color);
			} else {
				input.css("color", defaultColor);
			}
		}
		input.css('overflow', 'hidden');
		input.focus(clearMessage);
		input.blur(insertMessage);
		input.change(insertMessage);
		insertMessage();
	};
})(jQuery);