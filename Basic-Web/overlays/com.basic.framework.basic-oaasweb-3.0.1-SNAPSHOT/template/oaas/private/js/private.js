//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.private = function() {

	var catalogTree = null;// 目录树
	var grid1 = null;// 右边列表
	var form1 = null ;

	return {
		init : function() {
			form1 = new ruizhi.FormExt("oaas-private-form1");
			catalogTree = new ruizhi.ZTree("oaas-catalogTree-td",{
				url : WEB_ROOT+"/oaas/func/privateCatalog/qryTree.do",
				autoParam:["id=parentCatalogId"],
			});
			
			grid1 = new ruizhi.DataGrid("oaas-private-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/oaas/func/private/pagin.do'
			});
		},
		treeClick : function(treeNode){
			
			var url = WEB_ROOT + '/oaas/func/private/pagin.do';
			var paramObj = treeNode
			grid1.loadData(url,paramObj);
			
		},
		loadData : function(){//加载数据
			var url = WEB_ROOT+"/oaas/func/privateCatalog/qryTree.do";
			var paramObj = {
				userId : _SESSION.userId,
			}
			catalogTree.loadData(url,paramObj);
		},
		//grid 列表加载数据
		gridLoadData : function(param){
			var url = WEB_ROOT + '/oaas/func/private/pagin.do';
			var paramObj = param;
			grid1.loadData(url,paramObj);
		},
		
		/*添加权限目录*/
		createCatalog:function(){
			var treeNode =  catalogTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privateCatalog/catalogDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			if(!ruizhi.IsNull(treeNode)){
				paramObj.privateCatalog={
					parentCatalogId :treeNode.parentCatalogId
				}
			}
			var submitFn = ruizhi.oaas.private.createCatalogComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		createCatalogComeBack :function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			var url = WEB_ROOT + "/oaas/func/privateCatalog/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.private.loadData();
			});
			
		},
		/*添加子权限目录*/
		createSubCatalog:function(form){
			var treeNode =  catalogTree.getSelectedNodes()[0];
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择目录');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privateCatalog/catalogDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			paramObj.privateCatalog={
					parentCatalogId :treeNode.catalogId
			}
			
			var submitFn = ruizhi.oaas.private.createCatalogComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		
		editCatalog:function(){
			var treeNode = catalogTree.getSelectedNodes()[0];
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择目录');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privateCatalog/catalogDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			paramObj.privateCatalog=treeNode;
			
			var submitFn = ruizhi.oaas.private.createCatalogComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//删除节点
		delCatalog:function (){
			var treeNode = catalogTree.getSelectedNodes()[0];
			var ids = catalogTree.getChildNodes(treeNode);
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择目录');
				return;
			}
			
			var msg ="";
			if(treeNode.isParent){
				msg ="是否删除该目录，以及该目录下的所有目录?";
			}else{
				msg ="是否删除该目录？";
			}
			ruizhi.Confirm(msg,function(result){
				if(result || 'true' == result) {
					
					var params = {};
					params.catalogId = treeNode.catalogId;
//					debugger;
					var url = WEB_ROOT+"/oaas/func/privateCatalog/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.private.loadData();
					});
				}
				else{
					
				}
			})
		},
		
		/*添加权限*/
		createPrivate:function(){
			var treeNode =  catalogTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/private/privateDetail.do';
			var width = null;
			var heigth = null;
			
			//目录
			paramObj.catalog=treeNode;
			var submitFn = ruizhi.oaas.private.createPrivateComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		createPrivateComeBack :function(param){
			var params = {};
			params.params =  ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/private/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.private.gridLoadData(param);
			});
		},
		
		/**
		 * 修改权限
		 */
		modifyPrivate:function(){
			var selItems =  grid1.getCheckedItems();
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/private/privateDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.private.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			if(!ruizhi.IsNull(selItems)){
				if(selItems.length==1){
					paramObj.private = selItems[0];
					ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
				}else{
					ruizhi.Alert('请选择一条记录');
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		modifyComeBack :function(param){
			var params = {};
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/private/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.private.gridLoadData(param);
			});
		},
		
		delPrivate :function(){
			var selItemIds =  grid1.getCheckedIds();
			var selItem = grid1.getCheckedItems();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择权限');
				return;
			}
			
			for(var key in selItem){
				if(selItem[key].privateType != 'SPECIAL'){
					ruizhi.Alert('只能删除特殊权限，请重新选择！');
					return;
				}
			}
			
			ruizhi.Confirm("是否删除选择的权限？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.privateIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/private/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.private.gridLoadData(selItem[0]);
					});
				} else {
					
				}
			})
		},
		typeTrans : function(cellvalue, options, rowObject){
			if(rowObject.privateType == 'MENU'){
				return "菜单";
			}else if(rowObject.privateType == 'FUNC'){
				return "功能按钮";
			}else if(rowObject.privateType =='SPECIAL'){
				return "特殊权限"
			}
		},
		stateTrans : function(cellvalue, options, rowObject){
			if(rowObject.state == '10A'){
				return "有效";
			}else {
				return "无效";
			}
		},
		qry:function (){
			var param = form1.formToObject();
			ruizhi.oaas.private.treeClick(param);
		},
		resetForm : function (){
			form1.reset();
		}
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.private.init();
});

// ////////////////////////////////////////
// function定义
