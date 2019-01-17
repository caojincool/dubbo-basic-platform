//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privateData = function() {

	var privateDataGrid = null;
	var privateDataFrom = null;// 表单
	return {
		init : function() {
			privateDataFrom =  new ruizhi.FormExt("oaas-privateData-form1");
			
			privateDataGrid = new ruizhi.DataGrid("oaas-privateData-grid1",{
				url : WEB_ROOT + '/oaas/func/privateData/pagin.do',
				postData :{
					
				}
			})
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/privateData/pagin.do';
			var paramObj = param;
			privateDataGrid.loadData(url, paramObj);
		},
		
		/*添加数据权限*/
		create:function(){
			//重置表单
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privateData/privateDataDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.privateData.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/privateData/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.privateData.loadData);
		},
		
		
		modify:function(){
			var selItems =  privateDataGrid.getCheckedItems();
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privateData/privateDataDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.privateData.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			if(!ruizhi.IsNull(selItems)){
				if(selItems.length==1){
					paramObj.privateData = selItems[0];
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
			var url = WEB_ROOT + "/oaas/func/privateData/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.privateData.loadData);
		},
		
		//删除节点
		del:function (){
			var selItemIds =  privateDataGrid.getCheckedIds();
			var selItem = privateDataGrid.getSelectedItem();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择数据权限');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的数据权限？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.dataIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/privateData/del.do";
					ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.privateData.loadData);
				} else {
					
				}
			})
			
		},
		itemClick:function(){
			var selItem = privateDataGrid.getSelectedItem();
			
			var paramObj = null;
			if(!ruizhi.IsNull(selItem)){
				
				paramObj = {
						dataId : selItem.dataId	
				}
			}
			
			ruizhi.oaas.privateDataInst.loadData(paramObj);
			console.log(paramObj);
			
		},
		qry :function(){
			var url = WEB_ROOT + '/oaas/func/privateData/pagin.do';
			var paramObj = privateDataFrom.formToObject();
			privateDataGrid.loadData(url, paramObj);
		},
//		privateDataTrans : function(cellvalue, options, rowObject){
//			if(rowObject.sex == 1){
//				return "男";
//			}else if(rowObject.sex == 0){
//				return "女";
//			}
//		},

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.privateData.init();
});

// ////////////////////////////////////////
// function定义
