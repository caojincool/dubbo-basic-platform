ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.rolePrivate = function() {

	var rolePrivateGrid = null;
	var tab = null ;
	var roleId = null;
	return {
		init : function() {
			var gridParam ={};
			tab = new ruizhi.Tab("oaas-rolePrivate-tab");
			rolePrivateGrid = new ruizhi.DataGrid("oaas-rolePrivate-grid");
		},
		

		loadData : function(param) {// 加载数据
			var url = WEB_ROOT + '/oaas/func/private/pagin.do';
			roleId = param.roleId
			if(!ruizhi.IsNull(roleId)){
				var paramObj = {
					roleId :roleId,
				}
				
				rolePrivateGrid.loadData(url, paramObj);
			}
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
		create : function(){
			if(ruizhi.IsNull(roleId)){
				ruizhi.Alert("请选择！");
				return;
			}else{
				var url = WEB_ROOT + '/oaas/funcPage/private/selPrivate.do';
				var width = null;
				var heigth = null;
				var paramObj = {roleId : roleId};//传给模态窗口的参数
				paramObj.title = "选择权限";
				var submitFn = ruizhi.oaas.rolePrivate.createComeBack;//模态窗口提交后回调方法
				var modalClass = 'modal-md';
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}
		},
		createComeBack :function(param){
			var params = {};
			var url = WEB_ROOT + '/oaas/func/rolePrivate/create.do';
			params.params=ruizhi.ToJson(param);
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.rolePrivate.loadData({roleId :roleId});
			});
		},
		del : function(){
			var selItemIds =  rolePrivateGrid.getCheckedIds();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择权限');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的权限？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.rolePrivateIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/rolePrivate/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.rolePrivate.loadData({roleId :roleId});
					});
					
				} else {
					
				}
			})
		}
		
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.rolePrivate.init();
});
