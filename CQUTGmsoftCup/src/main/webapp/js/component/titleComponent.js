(function($) {
	$.fn.initTitle = function(setting){
		var _setting={title:'标题',buttons:[]};
		$.extend(_setting, setting);
		
		var tb = $('<div/>').attr('id', 'Title_bar').appendTo(this);
		
		var tbh = $('<div/>').attr('id', 'Title_bar_Head').appendTo(tb);
		$('<div/>').attr('id', 'Title_Head').appendTo(tbh);
		
		var title_div = $('<div/>').attr('id', 'Title').appendTo(tbh);
		$('<img/>').attr('border', '0').attr('width', 13).attr('height', 13)
			.attr('src', 'images/plugins/components/title_arrow.gif').appendTo(title_div);
		$('<span/>').html(_setting.title).appendTo(title_div);
		
		$('<div/>').attr('id', 'Title_End').appendTo(tbh);
		$('<div/>').attr('id', 'Title_bar_bg').appendTo(tbh);
		
		
		var tbt = $('<div/>').attr('id', 'Title_bar_Tail').appendTo(tb);
		var tfb = $('<div/>').attr('id', 'Title_FuncBar').appendTo(tbt);
		var ul = null;
		
		//添加按钮
		var btnss = _setting.buttons.length > 1 ? _setting.buttons.reverse() : _setting.buttons;
		$(btnss).each(function(index, cur){
			cur = $(cur)[0];
			if(index == 0){
				ul = $('<ul/>').attr('id', 'btn_ul').appendTo(tfb);
				$('<li/>').addClass('line').appendTo(ul);
			}
			if(cur.hidden==true){
				
			}
			else{
				$('<div/>').addClass('Btn').html(cur.text).appendTo($('<a/>').click(cur.click).appendTo($('<li/>').addClass('title').appendTo(ul)));
				$('<li/>').addClass('line').appendTo(ul);
			}
			
		});
	}
	
})(jQuery);