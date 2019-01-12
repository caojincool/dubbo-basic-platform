ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.user = function() {

	var userGrid;
	var form;
	var tab;

	return {
		init : function() {
			var gridParam ={};
			gridParam.url = WEB_ROOT + '/oaas/func/user/pagin.do';
			gridParam.postData = {
					
			};
			userGrid = new ruizhi.DataGrid("oaas-user-grid",gridParam);
			form = new ruizhi.FormExt("oaas-user-form");
			tab = new ruizhi.Tab('oaas-user-tab');
		},
		
		itemDblClick : function(rowId) {// 双击事件
			alert('这是一个双击,rowId:' + rowId);
		},
		
		itemClick : function(userId){ //单击事件
			
			var param ={
					userId : userId
			};
			
			ruizhi.oaas.userRole.loadData(param);
			ruizhi.oaas.userPrivate.loadData(param);
			ruizhi.oaas.userDataGroup.loadData(param);
			ruizhi.oaas.userData.loadData(param);
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/oaas/func/user/pagin.do';
			var paramObj = form.formToObject();
			if(!ruizhi.IsNull(paramObj.beginExpireTime)){
				paramObj.beginExpireTime  = paramObj.beginExpireTime +" 00:00:00";
			}
			if(!ruizhi.IsNull(paramObj.endExpireTime)){
				paramObj.endExpireTime  = paramObj.endExpireTime+" 00:00:00";
			}
			userGrid.loadData(url, paramObj);
		},
		
		add : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/user/userDetail.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var submitFn = ruizhi.oaas.user.addComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		addComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			//console.log(param);
			params.params = param;
			var url = WEB_ROOT + "/oaas/func/user/userAddSave.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.user.loadData);
		},
		
		edit : function(){
			//判断选择行是否唯一
			if(!this.checkSelectUnique()){
				return;
			}
			
			var paramObj = {
					
			};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/user/userDetail.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var submitFn = ruizhi.oaas.user.editComeBack;//模态窗口提交后回调方法
			
			var modalClass = "modal-lg";
			var selItem = userGrid.getSelectedItem();//所选行
			if(selItem!=null){
				selItem.pageDateType = 'UPDATE';
				paramObj = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		editComeBack:function(param){ //修改回调
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			var url = WEB_ROOT + "/oaas/func/user/userEditSave.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.user.loadData);
		},
		//删除
		del : function(){
			var userIds = userGrid.getCheckedIds();//所选行的id数组
			if(!ruizhi.IsNull(userIds) && userIds.length > 0){
				userIds = userIds.join(",");
				var msg = "确定要删除吗？";
				bootbox.confirm(msg, function(result) {
					if(result || 'true' == result) {
						var params = {};
						params.userIds = userIds;
						//params.modifyUserId = _SESSION.userId;//修改人
						//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
						var url = WEB_ROOT + "/oaas/func/user/userDel.do";
						ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.user.loadData);
					} else {
						
					}
				});
			}else{
				ruizhi.Alert('请选择');
			}
		},
		//设置权限
		selPrivate : function(){
			//判断选择行是否唯一
			if(!this.checkSelectUnique()){
				return;
			}
			var selItem = userGrid.getSelectedItem();
			if(ruizhi.IsNull(selItem)){
				ruizhi.Alert("请选择！");
				return;
			}else{
				var url = WEB_ROOT + '/oaas/funcPage/private/selPrivate.do';
				var width = null;
				var heigth = null;
				var paramObj = {userId : selItem.userId};//传给模态窗口的参数
				paramObj.title = "选择权限";
				var submitFn = ruizhi.oaas.user.selPrivateComeBack;//模态窗口提交后回调方法
				var modalClass = 'modal-md';
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}
		},
		
		selPrivateComeBack :function(param){
			var params = {};
			var url = WEB_ROOT + '/oaas/func/userPrivate/create.do';
			params.params=ruizhi.ToJson(param);
			ruizhi.InvokeMethodAsyn(url,params);
		},
		//设置角色
		selRole : function(){
			//判断选择行是否唯一
			if(!this.checkSelectUnique()){
				return;
			}
			var selItem = userGrid.getSelectedItem();
			if(ruizhi.IsNull(selItem)){
				ruizhi.Alert("请选择！");
				return;
			}else{
				var url = WEB_ROOT + '/oaas/funcPage/role/selRole.do';
				var width = null;
				var heigth = null;
				var paramObj = {userId : selItem.userId};//传给模态窗口的参数
				paramObj.title = "选择角色";
				var submitFn = ruizhi.oaas.user.selRoleComeBack;//模态窗口提交后回调方法
				var modalClass = 'modal-md';
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}
		},
		
		selRoleComeBack :function(param){
			var params = {};
			var url = WEB_ROOT + '/oaas/func/userRole/create.do';
			params.params=ruizhi.ToJson(param);
			ruizhi.InvokeMethodAsyn(url,params);
		},
		//设置数据权限
		selDataInst : function(){
			//判断选择行是否唯一
			if(!this.checkSelectUnique()){
				return;
			}
			var selItem = userGrid.getSelectedItem();
			if(ruizhi.IsNull(selItem)){
				ruizhi.Alert("请选择！");
				return;
			}else{
				var url = WEB_ROOT + '/oaas/funcPage/user/selDataInst.do';
				var width = null;
				var heigth = null;
				var paramObj = {userId : selItem.userId};//传给模态窗口的参数
				paramObj.title = "选择数据实例";
				var submitFn = ruizhi.oaas.user.selDataInstComeBack;//模态窗口提交后回调方法
				var modalClass = 'modal-md';
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}
		},
		
		selDataInstComeBack :function(param){
			var params = {};
			var url = WEB_ROOT + '/oaas/func/userDataInst/create.do';
			params.params=ruizhi.ToJson(param);
			ruizhi.InvokeMethodAsyn(url,params);
		},
		//设置数据权限分组
		selDataGroup : function(){
			//判断选择行是否唯一
			if(!this.checkSelectUnique()){
				return;
			}
			var selItem = userGrid.getSelectedItem();
			if(ruizhi.IsNull(selItem)){
				ruizhi.Alert("请选择！");
				return;
			}else{
				var url = WEB_ROOT + '/oaas/funcPage/user/selDataGroup.do';
				var width = null;
				var heigth = null;
				var paramObj = {userId : selItem.userId};//传给模态窗口的参数
				paramObj.title = "选择数据权限分组";
				var submitFn = ruizhi.oaas.user.selDataGroupComeBack;//模态窗口提交后回调方法
				var modalClass = 'modal-md';
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}
		},
		
		selDataGroupComeBack :function(param){
			var params = {};
			var url = WEB_ROOT + '/oaas/func/userDataGroup/create.do';
			params.params=ruizhi.ToJson(param);
			ruizhi.InvokeMethodAsyn(url,params);
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
		//单选部门
		selOrg : function(){
			var url = WEB_ROOT + '/oaas/funcPage/org/selOrg.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			// TODO 权限功能完善需要回来改
			if(ruizhi.IsSuperadminRole() || _SESSION.hasPriv("oaas.special.allOrgQry") ){
				orgType ='ALLORG';
			}else{
				orgType ='CURORG';
			}
			paramObj.userId = _SESSION.userId;//用户标识（All的状态可选）
			paramObj.orgType = orgType;//查询范围
			paramObj.title = "单选部门";
			var submitFn = ruizhi.oaas.user.selOrgComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-sm';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selOrgComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			var params = {};
			if(param != null && param.length >0){
				for(var i = 0 ; i < param.length;i++){
					params.orgName = param[0].orgName;
					params.orgId = param[0].orgId;
					form.setValue('orgName',params.orgName);
					form.setValue('orgId',params.orgId);
				}
				
			}
		},
		resetPwd : function(){
			//判断选择行是否唯一
			if(!this.checkSelectUnique()){
				return;
			}
			var selItem = userGrid.getSelectedItem();
			if(ruizhi.IsNull(selItem)){
				ruizhi.Alert("请选择！");
				return;
			}else{
				
				var url = WEB_ROOT + "/oaas/func/user/resetPwd.do";
				var param = {userId : selItem.userId};
				var flag = ruizhi.InvokeMethod(url,param);
				
				if(flag == "SUCCESS"){
					ruizhi.Alert("密码重置成功!!! 密码为 :123456");
				}else{
					ruizhi.Alert("操作失败");
				}
				
			}
		},
		checkSelectUnique : function (){
			
			if(userGrid.getCheckedItems().length >1){
				ruizhi.Alert("请选择一条数据！")
				return  false;
			}
			
			return true;
			
		},
		resetForm : function(){
			form.reset();
		}
		
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.user.init();
});
