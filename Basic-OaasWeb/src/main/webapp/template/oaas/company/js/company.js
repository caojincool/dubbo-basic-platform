//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.company = function() {

	var companyGrid = null;
	var companyFrom = null;// 表单
	return {
		init : function() {
			companyFrom =  new ruizhi.FormExt("oaas-company-form1");
			
			companyGrid = new ruizhi.DataGrid("oaas-company-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/oaas/func/company/pagin.do'
			});
			
		},
		
		loadData : function(){//加载数据
			var url = WEB_ROOT + '/oaas/func/company/pagin.do';
			var params = companyFrom.formToObject();
			companyGrid.loadData(url,params );
		},
		
		/*添加公司信息*/
		create:function(){
			//重置表单
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/company/companyDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.company.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/company/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.company.loadData();
			});
		},
		
		
		modify:function(){
			var selItems =  companyGrid.getCheckedItems();
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/company/companyDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.company.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			if(!ruizhi.IsNull(selItems)){
				if(selItems.length==1){
					paramObj.company = selItems[0];
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
			var url = WEB_ROOT + "/oaas/func/company/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.company.loadData(param);
			});
		},
		
		//删除节点
		del:function (){
			var selItemIds =  companyGrid.getCheckedIds();
			var selItem = companyGrid.getSelectedItem();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的公司？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.companyIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/company/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.company.loadData(selItem);
					});
				} else {
					
				}
			})
			
		},
		companyTrans : function(cellvalue, options, rowObject){
			if(rowObject.sex == 1){
				return "男";
			}else if(rowObject.sex == 0){
				return "女";
			}
		},
		//同步Oa数据
		oaToErp : function(){
			var msg = "确定要同步OA组织及其用户吗？";
			bootbox.confirm(msg, function(result) {
				if(result || 'true' == result) {
					var params = {};
					var url = WEB_ROOT + "/oaApi/func/oaManager/oaToErp.do";
					ruizhi.InvokeMethodAsyn(url,params,function(msg){
						if("success" == msg.status){
							ruizhi.Alert("操作成功");
							ruizhi.buyManager.purYearPort.loadData();
						}else{
							ruizhi.Alert(msg.message);
						};
					})
				} 
			});
			
		},
		
		resetForm : function(){
			
			companyFrom.reset();
			
		}
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.company.init();
});

// ////////////////////////////////////////
// function定义
