//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.menuCatalog = function() {

	var catalogTree = null;// 目录树

	return {
		init : function() {
			catalogTree = new ruizhi.ZTree("oaas-menuCatalogTree-td",{
				url : WEB_ROOT+"/oaas/func/menuCatalog/qryTree.do",
				autoParam:["id=parentCatalogId"],
			});
			
		},
		treeClick : function(treeNode){
			var url = WEB_ROOT + '/oaas/func/menu/pagin.do';
			var paramObj = {
				catalogId : treeNode.catalogId,
			}
			ruizhi.oaas.menu.loadData(treeNode);
			
		},
		loadData : function(){//加载数据
			var url = WEB_ROOT+"/oaas/func/menuCatalog/qryTree.do";
			var paramObj = {
				userId : _SESSION.userId,
			}
			catalogTree.loadData(url,paramObj);
		},
		/*添加菜单目录*/ 
		create:function(){
			var treeNode =  catalogTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/menuCatalog/catalogDetail.do';
			var width = null;
			var heigth = null;
			var menuCatalogCatalog = {};
			if(!ruizhi.IsNull(treeNode)){
				paramObj.menuCatalog={
					parentCatalogId :treeNode.parentCatalogId
				}
			}
			var submitFn = ruizhi.oaas.menuCatalog.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		createComeBack :function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			var url = WEB_ROOT + "/oaas/func/menuCatalog/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.menuCatalog.loadData();
			});
		},
		/*添加子菜单目录*/
		createSubCatalog:function(form){
			var treeNode =  catalogTree.getSelectedNodes()[0];
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择目录');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/menuCatalog/catalogDetail.do';
			var width = null;
			var heigth = null;
			var menuCatalogCatalog = {};
			paramObj.menuCatalog={
					parentCatalogId :treeNode.catalogId
			}
			
			var submitFn = ruizhi.oaas.menuCatalog.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		
		modity:function(){
			var treeNode = catalogTree.getSelectedNodes()[0];
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择目录');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/menuCatalog/catalogDetail.do';
			var width = null;
			var heigth = null;
			var menuCatalogCatalog = {};
			paramObj.menuCatalog=treeNode;
			
			var submitFn = ruizhi.oaas.menuCatalog.modityComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		modityComeBack :function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			var url = WEB_ROOT + "/oaas/func/menuCatalog/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.menuCatalog.loadData();
			});
			
		},
		
		//删除菜单目录
		del:function (){
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
					var url = WEB_ROOT+"/oaas/func/menuCatalog/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.menuCatalog.loadData();
					});
				}
				else{
					
				}
			})
		},
		getSelectedNodes : function(){
			
			return catalogTree.getSelectedNodes();
		}
		
		
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.menuCatalog.init();
});

// ////////////////////////////////////////
// function定义
