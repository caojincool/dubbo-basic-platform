//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selRole = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/role/selRole.do';//当前窗口的URL
	
	var catalogTree;
	var grid1;
	var tab1;
	var form1;
	var roleArr = []; //权限集合
	var userId = null;
	var submitParam = {};
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			//获取用户权限
			userId = paramObj.userId;
			roleArr = ruizhi.InvokeMethod(
				WEB_ROOT + '/oaas/func/userRole/qyrUserRole.do',
				{userId : paramObj.userId}
			);
			
			roleArr = roleArr ? roleArr : [];
			//判断是否管理员
			if(!ruizhi.IsSuperadminRole()){
				submitParam.userId = _SESSION.userId
				submitParam.authorize =true;
			}
	
			grid1 = new ruizhi.DataGrid("oaas-selRole-grid1",{
				postData : submitParam,
				url : WEB_ROOT + '/oaas/func/role/pagin.do',
				loadComplete : function(data){
					ruizhi.oaas.selRole.loadComplete(data);
				},
				onPaging:function(){ 
					ruizhi.oaas.selRole.setRoleArr();
		        },  
			});
			
			catalogTree = new ruizhi.ZTree("selRole-catalogTree-td",{
				url : WEB_ROOT+"/oaas/func/roleCatalog/qryTree.do",
				autoParam:["id=parentCatalogId"],
//				otherParam :{
//					userId : _SESSION.userId,
//					catalogType : paramObj.catalogType,
//				}
			});
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selRole-title").html(paramObj.title);
			}
			
		},
		
		catalogClick : function(treeNode) {// 组织单击
			//alert(rowId);
			
			ruizhi.oaas.selRole.setRoleArr();
			var url = WEB_ROOT + '/oaas/func/role/pagin.do';
			submitParam.catalogId = treeNode.id;
			var paramObj =submitParam;
//			var paramObj = {
//				catalogId : treeNode.id,
//				roleName : null,
//				catalogName : null
//			}
			grid1.loadData(url, paramObj);
			
		},
		
		
		//权限双击确定事件
		roleDbClick : function(rowId) {
			//alert(rowId);
			this.doSubmit();
		},

		doSubmit : function() {
			
			ruizhi.oaas.selRole.setRoleArr();
			var param={
					userId : userId,
					userRoles : roleArr
			}
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		//DataGrid 完成时回调 设置grid 数据
		loadComplete : function(data){
			
			var rowIds = [];
			var allIds = grid1.getAllIds();
			//设置一个默值
			allIds.forEach(x =>{grid1.setCell(x,'empowerFlag',"0")} );
			for(var key in roleArr){
				rowIds.push(roleArr[key].roleId);
				grid1.setCell(roleArr[key].roleId,'empowerFlag',roleArr[key].empowerFlag);
				if(roleArr[key].empowerFlag == 1){
					$("#"+roleArr[key].roleId+"_checkBox").prop('checked',true);
				}
			}
			grid1.setCheckedItems(rowIds);
		},
		//设置权限数组
		setRoleArr : function (){
			var allIds = grid1.getAllIds();
			var selIds = grid1.getCheckedIds();
			//需要删除的Id
			var removeIds = allIds.filter(x => !selIds.includes(x));
			
			var selItem = grid1.getCheckedItems();
			
			//添加或修改
			if(!ruizhi.IsNull(selItem)){
				
				selItem.forEach((value,index,array) => {
					roleArr.forEach(n =>{
						if(n.roleId == value.roleId ){
							n.empowerFlag = value.empowerFlag
						}
					});
					
					if(!roleArr.find(x => x.roleId == value.roleId)){
						roleArr.push({roleId:value.roleId,empowerFlag:value.empowerFlag,userId:userId});
					}
					
				});
			}
			
			//删除
			for(var index=0;index<roleArr.length;index++){
				if(removeIds.includes(roleArr[index].roleId.toString())){
					roleArr.splice(index,1);
					index = index -1;
				}
			}
			
//			alert(ruizhi.ToJson(roleArr));
		},
		//显示授权checkbox
		checkBoxFormatter :function(cellvalue, options, rowObject){
			return "<input id=\""+rowObject.roleId+"_checkBox\" type=\"checkbox\"  value=\"1\" onClick=\"ruizhi.oaas.selRole.checkBoxClick(this) \">";
		},
		//点击授权触发
		checkBoxClick : function(obj){
			var rowId =  $(obj).parents("tr").attr("id");
			if($(obj).prop("checked")){
				grid1.setCell(rowId,'empowerFlag',1);
			}else{
				grid1.setCell(rowId,'empowerFlag',0);
			}
		}
		
		
		
	}
	
}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.selRole.init();
});