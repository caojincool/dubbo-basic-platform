//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.userStaff = function() {

	var userGrid = null;
	var staffId = null;
	var tab2 = null;
	
	return {
		init : function() {
			userGrid = new ruizhi.DataGrid("oaas-userTab-grid");
			
			tab2 = new ruizhi.Tab('oaas-user-tab');
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/userStaff/pagin.do';
			staffId = param.staffId;
			var paramObj = {
				staffId : param.staffId
			}
			userGrid.loadData(url, paramObj);
		},
		
		/*添加员工账号*/
		create:function(){
			
			if(ruizhi.IsNull(staffId)){
				ruizhi.Alert('请选择员工');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/user/selUser.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.userStaff.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-sm";
			
			paramObj.title = "单选账号";
			paramObj.staffId =staffId;
			
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			param.staffId = staffId;
			param.defaultJob = 0;
			
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/userStaff/create.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.userStaff.loadData(param);
			});
			
		},
		
		//删除员工账号
		del:function (){
			var selItemIds =  userGrid.getCheckedIds();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择账号');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的账号？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.userIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/userStaff/del.do";
					ruizhi.InvokeMethod(url,params,function(){
						
						ruizhi.oaas.userStaff.loadData({staffId :staffId});
					});
				} else {
					
				}
			})
			
		},
		itemClick:function(){
			
		},
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.userStaff.init();
});
