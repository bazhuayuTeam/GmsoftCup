		var curRoleId = null;
		var rolePermissions = null;
		//用于标志此角色是否已被分配模块 
		var hasAssginedModules = false;
		//角色树
		var roleTreeObj;
		var roleRootNode = [ {
			roleID : null,
			roleName : "全部角色",
			isParent : true
		} ];
		var roleTreeSetting = {
			nameCol : "roleName",
			async : true,
			asyncUrl : "null",
			dwr : true,
			dwrFun : RoleService.findMapByProperties,
			needLink : true,
			properties : [ 'roleID', 'roleName' ],
			condition : '',
			sortField : '',
			order : '',
			treeNodeKey : 'roleID',
			callback : {
				click : function(event, treeId, treeNode) {
					curRoleId = treeNode['roleID'];
					updateRolePermission();
				}
			},
			nameFormatter : function(name, node) {//处理显示数据，暂时不支持html
				return name;
			},
			loadConditionFun : function(node) {//加载时的判断条件，每次加载前会调用
				return null;
			},
			isLeaf : function(node) {//是否是叶节点，不写则所有的节点都为非叶节点
				return true;
			}
		};
		
		//模块权限树
		var permissionTreeObj;
		var permissionRootNode = [ {
			modulecode : null,
			modulename : "全部模块",
			isParent : true
		} ];
		var permissionTreeSetting = {
			checkable : true,
			nameCol : "modulename",
			async : true,
			asyncUrl : "null",
			dwr : true,
			dwrFun : ModuleService.findMapByProperties,
			needLink : true,
			properties : ['modulecode','modulename','haschild','level0'],
			condition : "moduleType='ModuleType0001' AND (icon IS NULL or icon ='')",
			sortField : 'haschild',
			order : 'desc',
			treeNodeKey : 'modulecode',
			callback : {
				asyncSuccess : 
					function(event, treeId, treeNode, msg) {//异步加载调用成功
						updateRolePermission();//选中状态切换
						if(treeNode.modulename=='全部模块'){
							$(document.body).unmask();
						}
					},
				change : 
					function(event, treeId, treeNode){
						if(treeNode.checked){
							if(curRoleId && curRoleId != null){
								$(document.body).mask("权限更新中...");
								addData(rolePermissions,treeNode.modulecode);
								var temp = treeNode.parentNode;
								while(temp!=null){
									addData(rolePermissions,temp.modulecode);
									temp = temp.paretNode;
								}
								
								PermissionAssignService.addPermissions(treeNode.modulecode,curRoleId,function(data){
									$(document.body).unmask();
									if(!data){
										jAlert("权限更新失败!");
									}
								});
							}
						}
						else{
							if(curRoleId && curRoleId != null){
								$(document.body).mask("权限更新中...");
								PermissionAssignService.deletePermission(treeNode.modulecode,curRoleId,function(data){
									$(document.body).unmask();
									if(!data){
										jAlert("权限更新失败!");
									}
								});
						
							}
						}
					}
		},
		loadConditionFun : 
			function(node) {//加载时的判断条件，每次加载前会调用
				if (node["modulecode"] == null) {
					return "(parent IS NULL or parent='' or length(parent)=0)";//+ condition;
				} else {
					return "parent='" + node.modulecode + "'";//+ condition;
				}
			},
		isLeaf : 
			function(node) {//是否是叶节点，不写则所有的节点都为非叶节点
				if(!node.haschild)
					return true;
				else
					return false;
			}
		};
		//更新权限选中
		function updateCheckedStatus(isLoad) {
			if (isLoad) {//如果是点击角色树，那么首先将树的选中状态清空
				permissionTreeObj.checkAllNodes(false);
			}
			if (rolePermissions.length > 0) {
				hasAssginedModules = true;
				$(rolePermissions).each(function(i, cur) {
					//处理模块节点
					moduleNode = permissionTreeObj.getNodesByParam(
							"modulecode", cur.moduleId);
					var curModuleCode = cur.moduleId;
					if (moduleNode != null && moduleNode.length > 0) {
						moduleNode = $(moduleNode)[0];
						if (moduleNode.checked == false) {
							moduleNode.checked = true;
							moduleNode.check_True_Full = false;
							permissionTreeObj.updateNode(moduleNode, false);
						}
					}
				});
			}
		}
		//更新角色的权限
		function updateRolePermission() {
			if (curRoleId && curRoleId != null) {
				//获取角色权限
				PermissionAssignService.findMapByProperties( [
						'moduleId' ], "roleID='" + curRoleId
						+ "'", '', '', false,
						function(data) {
							rolePermissions = data;
							updateCheckedStatus(true);
						});
			}
		}
		
		//页面加载执行----------------------------------------------------------------------
		$(document).ready(function() {
			$(document.body).mask("Waiting...");
			//加载树
			roleTreeObj = $("#roleTree").zTree(roleTreeSetting, roleRootNode);
			roleTreeObj.expandNode(roleRootNode[0], true, false);
			//加载树
			permissionTreeObj = $("#permissionTree").zTree(permissionTreeSetting,
					permissionRootNode);
			permissionTreeObj.expandNode(permissionRootNode[0], true, false);
		});
		//保存角色权限分配
		function savePermissionAssign() {
			var roleNode = roleTreeObj.getSelectedNode();
			var moduleNodes = permissionTreeObj.getCheckedNodes(true);
			var length = moduleNodes.length;
			if (!roleNode) {
				jAlert("请选择角色!");
			} else if (!(!hasAssginedModules && length == 0)) {
				var modulecodes = [];
				for ( var i = 0; i < length; i++) {
					if (moduleNodes[i].modulecode) {
						modulecodes.push(moduleNodes[i].modulecode);
					}
				}
			}
		}
		function addData(collection,data){
			try{
				if(data&&null!=data){
					for(var i=0;i<collection.length;i++){
						if(data == collection[i].moduleId)
							return;
					}
					data = {permissioncode2:data};
					collection.push(data);
				}
			}catch(e){
			}
		}
		
		// 获取节点数组 ,datas 为{permissioncode2:data}这种类型的json数组
		function getMoudles(datas){
			var array = new Array();
			for(var i=0;i<datas.length;i++)
				array.push(datas[i].permissioncode2);
			
			return array;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		