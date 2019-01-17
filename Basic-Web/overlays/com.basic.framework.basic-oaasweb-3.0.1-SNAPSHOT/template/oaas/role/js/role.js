//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.role = function() {

	var grid1 = null;// 右边列表
	var form1 = null;
	return {
		init : function() {
			form1 = new ruizhi.FormExt("oaas-role-form1");
			grid1 = new ruizhi.DataGrid("oaas-role-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/oaas/func/role/pagin.do'
			});
		},
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/role/pagin.do';
			var paramObj = param;
			grid1.loadData(url,paramObj);
		},
		
		/*添加菜单*/
		create:function(){
			var treeNode =  ruizhi.oaas.roleCatalog.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/role/roleDetail.do';
			var width = null;
			var heigth = null;
			
			//目录
			paramObj.catalog=treeNode;
			var submitFn = ruizhi.oaas.role.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		createComeBack :function(param){
			var params = {};
			params.params =  ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/role/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.role.loadData(param);
			});
		},
		
		/**
		 * 修改菜单
		 */
		modify:function(){
			var selItems =  grid1.getCheckedItems();
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/role/roleDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.role.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			if(!ruizhi.IsNull(selItems)){
				if(selItems.length==1){
					paramObj.role = selItems[0];
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
			var url = WEB_ROOT + "/oaas/func/role/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.role.loadData(param);
			});
		},
		
		del :function(){
			var selItemIds =  grid1.getCheckedIds();
			var selItem = grid1.getCheckedItems();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择菜单');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的菜单？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.roleIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/role/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.role.loadData(selItem[0]);
					});
				} else {
					
				}
			})
		},
//		typeTrans : function(cellvalue, options, rowObject){
//			if(rowObject.roleType == 'MENU'){
//				return "菜单";
//			}else if(rowObject.roleType == 'FUNC'){
//				return "功能按钮";
//			}else if(rowObject.roleType =='SPECIAL'){
//				return "特殊菜单"
//			}
//		},
		stateTrans : function(cellvalue, options, rowObject){
			if(rowObject.state == '10A'){
				return "有效";
			}else {
				return "无效";
			}
		},
		itemClick : function(){ //单击事件
			var selItem = grid1.getSelectedItem();
			
			if(ruizhi.IsNull(selItem)){
				return;
			}
			var paramObj = {
				roleId : selItem.roleId	
			}
			
			ruizhi.oaas.rolePrivate.loadData(paramObj);
		},
		qry:function (){
			var param = form1.formToObject();
			ruizhi.oaas.role.loadData(param);
		},
		resetForm : function (){
			form1.reset();
		}
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.role.init();
});

// ////////////////////////////////////////
// function定义
