/*
 * ztree.pluins.so  1.0版本
 * 谌毅
 * 时间：2012/1/20
 * 插件功能
 *  1.支持DWR
 *  2.支持多种数据混合使用
 *  3.支持对各种数据进行自定义
 */
(function($){
	_mutliDataProxyHandler = function(proxys){
		var _proxys = tools.clone(proxys);
		//传入节点数据和proxy名称
		function getNodes(proxyName, data){
			var _index = $.inArray(proxyName, _proxys);
			if(_index != -1){
				_proxys[_index] = data;
			}
			
			var _nodes = [];
			while(_proxys.length > 0 && tools.isArray(_proxys[0])){
				$.merge(_nodes,_proxys.shift());
			}
			
			if(_nodes.length > 0){
				var _result = {success:false,nodes:_nodes};
				if(_proxys.length == 0){
					_result.success = true;
				}
				return _result;
			}else{
				return null;
			}
		}
		
		return getNodes;
	},
	_tools = {
		uniq : function() {  
	        var temp = {}, len = this.length;
	
	        for(var i=0; i < len; i++)  {  
	            if(typeof temp[this[i]] == "undefined") {
	                temp[this[i]] = 1;
	            }  
	        }  
	        this.length = 0;
	        len = 0;
	        for(var i in temp) {  
	            this[len++] = i;
	        }  
	        return this;  
	    }  
	},
	_view = {
		//使用dwr方式异步加载数据
		asyncNode: function(setting, node, isSilent, callback) {
			var i, l;
			if (node && !node.isParent) {
				tools.apply(callback);
				return false;
			} else if (node && node.isAjaxing) {
				return false;
			} else if (tools.apply(setting.callback.beforeAsync, [setting.treeId, node], true) == false) {
				tools.apply(callback);
				return false;
			}
			if (node) {
				node.isAjaxing = true;
				var icoObj = $("#" + node.tId + consts.id.ICON);
				icoObj.attr({"style":"", "class":"ico_loading"});
			}
		
			var tmpParam = "";
			for (i = 0, l = setting.async.autoParam.length; node && i < l; i++) {
				var pKey = setting.async.autoParam[i].split("="), spKey = pKey;
				if (pKey.length>1) {
					spKey = pKey[1];
					pKey = pKey[0];
				}
				tmpParam += (tmpParam.length > 0 ? "&": "") + spKey + "=" + node[pKey];
			}
			if (tools.isArray(setting.asyncParamOther)) {
				for (i = 0, l = setting.asyncParamOther.length; i < l; i += 2) {
					tmpParam += (tmpParam.length > 0 ? "&": "") + setting.async.otherParam[i] + "=" + setting.async.otherParam[i + 1];
				}
			} else {
				for (var p in setting.async.otherParam) {
					tmpParam += (tmpParam.length > 0 ? "&" : "") + p + "=" + setting.async.otherParam[p];
				}
			}
			
			//dwr加载代码---------------------------------
			//判断选择器
			if(!setting.dataProxySelecter){
				alert("Error:没有数据加载选择器");
				return;
			}
			//获取数据加载代理的名称
			var proxyNames = setting.dataProxySelecter(node.proxyName,node);
			if(proxyNames==undefined || proxyNames==null){
				alert("Error:数据加载选择器返回错误");
				return;
			}	
			
			if (!tools.isArray(proxyNames)) {//不是一个数组
				proxyNames = [proxyNames];
			}else{
				tools.uniq(proxyNames);//去掉重复的
			}
			
			//加载数据
			var proxy = null;
			if(proxyNames.length == 1){//单个数据
				proxy = setting.dataProxys[proxyNames[0]];
				view.dwrDataLoader(proxy, proxyNames[0], null, setting, node, isSilent, callback);
			}else{
				var handler = new _mutliDataProxyHandler(proxyNames);
				$(proxyNames).each(function(index, curProxyName){
					proxy = setting.dataProxys[curProxyName];
					view.dwrDataLoader(proxy, curProxyName, handler, setting, node, isSilent, callback);
				});
			}
			//结束------------------------------------------------------------------------------
			return true;
		},
		//dwr加载器
		dwrDataLoader: function(proxy, curProxyName, mutliDataProxyHandler, setting, node, isSilent, callback){
			if(proxy!=undefined && proxy!=null && proxy.dwrFun){
				//处理查询条件
				var condition = proxy.condition!=undefined && proxy.condition!=null ? proxy.condition : '';
				//加载前处理用户自定义的查询条件
				if(setting.beforeLoad){
					var userCondition = setting.beforeLoad(node, curProxyName);
					if(userCondition && userCondition != ''){
						condition = userCondition;
						if(proxy.condition!=undefined && proxy.condition!=null && proxy.condition != ''){//加载器中有条件
							condition += ' AND ' + proxy.condition;
						}
					}
				}
				//执行数据加载
				proxy.dwrFun(proxy.properties, condition, 
					proxy.sortField, proxy.order, proxy.needLink, //不知为何在这里加载就会出错
					function(msg) {
						//组织节点数据
						var newNodes = [];
						try {
							if (!msg || msg.length == 0) {
								newNodes = [];
							} else if (typeof msg == "string") {
								newNodes = eval("(" + msg + ")");
							} else {
								newNodes = msg;
							}
						} catch(err) {}
						
						//向节点加入扩展数据
						for (i = 0; i < newNodes.length; i++) {
							newNodes[i].proxyName = curProxyName;
							newNodes[i].nameKey = proxy.nameKey;//显示用的属性
							newNodes[i].titleKey = proxy.titleKey;//显示提示用的属性
							
							//判断是否是叶子节点
							if(setting.isLeaf){
								newNodes[i].isParent = !setting.isLeaf(newNodes[i]);
							}else{
								newNodes[i].isParent = true;
							}
						}
						if (node) node.isAjaxing = null;
						if(mutliDataProxyHandler == null){
							view.setNodeLineIcos(setting, node);
							if (newNodes && newNodes != "") {
								newNodes = tools.apply(setting.async.dataFilter, [setting.treeId, node, newNodes], newNodes);
								view.addNodes(setting, node, tools.clone(newNodes), !!isSilent);
							} else {
								view.addNodes(setting, node, [], !!isSilent);
							}
							setting.treeObj.trigger(consts.event.ASYNC_SUCCESS, [setting.treeId, node, msg]);
							tools.apply(callback);
						}else{
							var curNodes = mutliDataProxyHandler(curProxyName, newNodes);
							if(curNodes != null){
								newNodes = curNodes.nodes;
								view.setNodeLineIcos(setting, node);
								if (newNodes && newNodes != "") {
									newNodes = tools.apply(setting.async.dataFilter, [setting.treeId, node, newNodes], newNodes);
									view.addNodes(setting, node, tools.clone(newNodes), !!isSilent);
								} else {
									view.addNodes(setting, node, [], !!isSilent);
								}
								
								if(curNodes.success){//判断是否加载完毕
									setting.treeObj.trigger(consts.event.ASYNC_SUCCESS, [setting.treeId, node, msg]);
									tools.apply(callback);
								}
							}
						}
//					},
//					function(XMLHttpRequest, textStatus, errorThrown) {
//						view.setNodeLineIcos(setting, node);
//						if (node) node.isAjaxing = null;
//						setting.treeObj.trigger(consts.event.ASYNC_ERROR, [setting.treeId, node, XMLHttpRequest, textStatus, errorThrown]);
					}
				);
			}
		},
		setNodeName: function(setting, node) {
			var nameKey = setting.data.key.name,
			titleKey = data.getTitleKey(setting),
			nObj = $("#" + node.tId + consts.id.SPAN);
			
			//用于自定义显示
			if(node.nameKey){
				if(node.nameKey){
					nameKey = node.nameKey;
				}
				if(node.titleKey){
					titleKey = node.titleKey;
				}
			}
			
			nObj.empty();
			if (nObj.nameIsHTML == true) {
				nObj.html(view._nameFormatter(setting, node, nameKey));
			} else if (nObj.nameIsHTML == false && setting.view.nameIsHTML) {
				nObj.html(view._nameFormatter(setting, node, nameKey));
			} else {
				nObj.text(view._nameFormatter(setting, node, nameKey));
			}
			
			if (tools.apply(setting.view.showTitle, [setting.treeId, node], setting.view.showTitle)) {
				var aObj = $("#" + node.tId + consts.id.A);
				aObj.attr("title", view._titleFormatter(setting, node, titleKey));
			}
		},
		appendNodes: function(setting, level, nodes, parentNode, initFlag, openFlag) {
			if (!nodes) return [];
			var html = [],
			childKey = setting.data.key.children,
			nameKey = setting.data.key.name,
			titleKey = data.getTitleKey(setting);
			
			
			for (var i = 0, l = nodes.length; i < l; i++) {
				var node = nodes[i],
				tmpPNode = (parentNode) ? parentNode: data.getRoot(setting),
				tmpPChild = tmpPNode[childKey],
				isFirstNode = ((tmpPChild.length == nodes.length) && (i == 0)),
				isLastNode = (i == (nodes.length - 1));
				
				//用于自定义显示
				nameKey = setting.data.key.name;
				titleKey = data.getTitleKey(setting);
				if(node.nameKey){
					if(node.nameKey){
						nameKey = node.nameKey;
					}
					if(node.titleKey){
						titleKey = node.titleKey;
					}
				}
				
				if (initFlag) {
					data.initNode(setting, level, node, parentNode, isFirstNode, isLastNode, openFlag);
					data.addNodeCache(setting, node);
				}
		
				var childHtml = [];
				if (node[childKey] && node[childKey].length > 0) {
					//make child html first, because checkType
					childHtml = view.appendNodes(setting, level + 1, node[childKey], node, initFlag, openFlag && node.open);
				}
				if (openFlag) {
					var url = view.makeNodeUrl(setting, node),
					fontcss = view.makeNodeFontCss(setting, node),
					fontStyle = [];
					for (var f in fontcss) {
						fontStyle.push(f, ":", fontcss[f], ";");
					}
					html.push("<li id='", node.tId, "' class='level", node.level,"' treenode>",
						"<button type='button' hidefocus='true'",(node.isParent?"":"disabled")," id='", node.tId, consts.id.SWITCH,
						"' title='' class='", view.makeNodeLineClass(setting, node), "' treeNode", consts.id.SWITCH,"></button>");
					data.getBeforeA(setting, node, html);
					html.push("<a id='", node.tId, consts.id.A, "' class='level", node.level,"' treeNode", consts.id.A," onclick=\"", (node.click || ''),
						"\" ", ((url != null && url.length > 0) ? "href='" + url + "'" : ""), " target='",view.makeNodeTarget(node),"' style='", fontStyle.join(''),
						"'");
					if (tools.apply(setting.view.showTitle, [setting.treeId, node], setting.view.showTitle)) {html.push("title='", view._titleFormatter(setting, node, titleKey).replace(/'/g,"&#39;"),"'");}
					html.push(">");
					data.getInnerBeforeA(setting, node, html);
					var name = setting.view.nameIsHTML ? view._nameFormatter(setting, node, nameKey) : view._nameFormatter(setting, node, nameKey).replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
					html.push("<button type='button' hidefocus='true' id='", node.tId, consts.id.ICON,
						"' title='' treeNode", consts.id.ICON," class='", view.makeNodeIcoClass(setting, node), "' style='", view.makeNodeIcoStyle(setting, node), "'></button><span id='", node.tId, consts.id.SPAN,
						"'>",name,"</span>");
					data.getInnerAfterA(setting, node, html);
					html.push("</a>");
					data.getAfterA(setting, node, html);
					if (node.isParent && node.open) {
						view.makeUlHtml(setting, node, html, childHtml.join(''));
					}
					html.push("</li>");
					data.addCreatedNode(setting, node);
				}
			}
			return html;
		},
		_nameFormatter:function(setting, node, nameKey){
			if(node.proxyName){
				var nf = setting.dataProxys[node.proxyName].nameFormatter;
				return typeof nf == 'function' ? nf(node[nameKey], node) : node[nameKey];
			}else{
				return node[nameKey];
			}
		},
		_titleFormatter:function(setting, node, titleKey){
			if(node.proxyName){
				var tf = setting.dataProxys[node.proxyName].titleFormatter;
				return typeof tf == 'function' ? tf(node[titleKey], node) : node[titleKey];
			}else{
				return node[titleKey];
			}
		}
	}
		
	_z = {
		tools: _tools,
		view: _view
	};
	$.extend(true, $.fn.zTree._z, _z);

	var zt = $.fn.zTree,
	tools = zt._z.tools,
	consts = zt.consts,
	view = zt._z.view,
	data = zt._z.data,
	event = zt._z.event;

//	var _asyncNode = view.asyncNode;
//	view.asyncNode = function(setting, level, nodes, parentNode) {
//		if (setting.dwr && _asyncNode) _asyncNode.apply(setting, node, isSilent, callback);
//	}
})(jQuery);