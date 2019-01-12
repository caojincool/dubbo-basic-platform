//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selPrivate = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/private/selPrivate.do';//当前窗口的URL
	
	var catalogTree;
	var grid1;
	var tab1;
	var form1;
	var privateArr = []; //权限集合
	var userId = null;
	var roleId = null; 
	var submitParam = {};
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			//获取用户权限
			userId = paramObj.userId;
			roleId = paramObj.roleId;
			if(!ruizhi.IsNull(userId)){
				privateArr = ruizhi.InvokeMethod(
						WEB_ROOT + '/oaas/func/userPrivate/qryUserPrivate.do',
						{userId : paramObj.userId}
				);
			}else if(!ruizhi.IsNull(roleId)){
				privateArr = ruizhi.InvokeMethod(
						WEB_ROOT + '/oaas/func/rolePrivate/qryRolePrivate.do',
						{roleId : paramObj.roleId}
				);
			}
			
			privateArr = privateArr ? privateArr : [];
			if(!ruizhi.IsSuperadminRole()){
				submitParam.userId = _SESSION.userId
				submitParam.authorize =true;
			}
	
			grid1 = new ruizhi.DataGrid("oaas-selPrivate-grid1",{
				postData : submitParam,
				url : WEB_ROOT + '/oaas/func/private/pagin.do',
				loadComplete : function(data){
					ruizhi.oaas.selPrivate.loadComplete(data);
				},
				onPaging:function(){ 
					ruizhi.oaas.selPrivate.setPrivateArr();
		        }, 
			});
			
			catalogTree = new ruizhi.ZTree("selPrivate-catalogTree-td",{
				url : WEB_ROOT+"/oaas/func/privateCatalog/qryTree.do",
				autoParam:["id=parentCatalogId"],
//				otherParam :{
//					userId : _SESSION.userId,
//					catalogType : paramObj.catalogType,
//				}
			});
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selPrivate-title").html(paramObj.title);
			}
			
		},
		
		catalogClick : function(treeNode) {// 组织单击
			//alert(rowId);
			
			ruizhi.oaas.selPrivate.setPrivateArr();
			var url = WEB_ROOT + '/oaas/func/private/pagin.do';
			submitParam.catalogId = treeNode.id;
			var paramObj =submitParam;
			grid1.loadData(url, paramObj);
			
		},
		
		
		//权限双击确定事件
		privateDbClick : function(rowId) {
			//alert(rowId);
			this.doSubmit();
		},

		doSubmit : function() {
			
			ruizhi.oaas.selPrivate.setPrivateArr();
			var param = null;
			if(!ruizhi.IsNull(userId)){
				param={
						userId : userId,
						userPrivates : privateArr
				}
			}
			else if (!ruizhi.IsNull(roleId)){
				param={
						roleId : roleId,
						rolePrivates : privateArr
				}
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
			for(var key in privateArr){
				rowIds.push(privateArr[key].privateId);
				grid1.setCell(privateArr[key].privateId,'empowerFlag',privateArr[key].empowerFlag);
				if(privateArr[key].empowerFlag == 1){
					$("#"+privateArr[key].privateId+"_checkBox").prop('checked',true);
				}
			}
			grid1.setCheckedItems(rowIds);
		},
		//设置权限数组
		setPrivateArr : function (){
			var allIds = grid1.getAllIds();
			var selIds = grid1.getCheckedIds();
			//需要删除的Id
			var removeIds = allIds.filter(x => !selIds.includes(x));
			
			var selItem = grid1.getCheckedItems();
			
			//添加或修改
			if(!ruizhi.IsNull(selItem)){
				
				selItem.forEach((value,index,array) => {
					privateArr.forEach(n =>{
						if(n.privateId == value.privateId ){
							n.empowerFlag = value.empowerFlag
						}
					});
					
					if(!privateArr.find(x => x.privateId == value.privateId)){
						var obj = {
								privateId:value.privateId,
								empowerFlag:value.empowerFlag,
						};
						if(!ruizhi.IsNull(userId)){
							obj.userId = userId;
						}else if(!ruizhi.IsNull(roleId)){
							obj.roleId = roleId;
						}
						privateArr.push(obj);
					}
					
				});
			}
			
			//删除
			for(var index=0;index<privateArr.length;index++){
				if(removeIds.includes(privateArr[index].privateId.toString())){
					privateArr.splice(index,1);
					index = index -1;
				}
			}
			
//			alert(ruizhi.ToJson(privateArr));
		},
		//显示授权checkbox
		checkBoxFormatter :function(cellvalue, options, rowObject){
			return "<input id=\""+rowObject.privateId+"_checkBox\" type=\"checkbox\"  value=\"1\" onClick=\"ruizhi.oaas.selPrivate.checkBoxClick(this) \">";
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
	ruizhi.oaas.selPrivate.init();
});