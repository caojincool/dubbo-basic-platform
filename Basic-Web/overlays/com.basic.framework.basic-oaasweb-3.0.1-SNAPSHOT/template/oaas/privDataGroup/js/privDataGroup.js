//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privDataGroup = function() {

	var dataGroupGrid = null;
	var From = null;// 表单
	var tab = null;
	return {
		init : function() {
			
			tab = new ruizhi.Tab('oaas-privDataGroup-tab');
			dataGroupGrid = new ruizhi.DataGrid("oaas-privDataGroup-grid1",{
				url : WEB_ROOT + '/oaas/func/privDataGroup/pagin.do',
				postData :{
					
				}
			})
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/privDataGroup/pagin.do';
			var paramObj = param;
			dataGroupGrid.loadData(url, paramObj);
		},
		
		/*添加数据权限分组*/
		create:function(){
			//重置表单
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privDataGroup/privDataGroupDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.privDataGroup.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/privDataGroup/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.privDataGroup.loadData);
			
		},
		
		
		modify:function(){
			var selItems =  dataGroupGrid.getCheckedItems();
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privDataGroup/privDataGroupDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.privDataGroup.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			if(!ruizhi.IsNull(selItems)){
				if(selItems.length==1){
					paramObj.privDataGroup = selItems[0];
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
			var url = WEB_ROOT + "/oaas/func/privDataGroup/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.privDataGroup.loadData(param);
			});
		},
		
		//删除节点
		del:function (){
			var selItemIds =  dataGroupGrid.getCheckedIds();
			var selItem = dataGroupGrid.getSelectedItem();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择数据权限');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的数据权限？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.dataGroupIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/privDataGroup/del.do";
					ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.privDataGroup.loadData);
				} else {
					
				}
			})
			
		},
		itemClick:function(){
			var selItem = dataGroupGrid.getSelectedItem();
			
			var paramObj = null;
			if(!ruizhi.IsNull(selItem)){
				
				paramObj = {
						dataGroupId : selItem.dataGroupId	
				}
			}
			
			ruizhi.oaas.privDataGroupInst.loadData(paramObj);
			
		},
		qry :function(){
			var url = WEB_ROOT + '/oaas/func/privDataGroup/pagin.do';
			var paramObj = privDataGroupFrom.formToObject();
			dataGroupGrid.loadData(url, paramObj);
		},
//		privDataGroupTrans : function(cellvalue, options, rowObject){
//			if(rowObject.sex == 1){
//				return "男";
//			}else if(rowObject.sex == 0){
//				return "女";
//			}
//		},

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.privDataGroup.init();
});

// ////////////////////////////////////////
// function定义
