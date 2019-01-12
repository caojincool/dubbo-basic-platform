//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoOpenWin = function() {

	return {
		init : function() {

		},

		openModalWin : function() {
			/*
			 * $.ajax({ url : WEB_ROOT + '/demo/win/demoModalWin.do', async :
			 * false, success : function(msg) { $("#modal-content").html(msg); }
			 * 
			 * }); $('#modal_theme_primary').modal('show');
			 */
			
			var url = WEB_ROOT + '/demo/funcPage/demoModalWin.do';
			var width = null;
			var heigth = null;
			var paramObj = {staffId:"1",staffName:'world'};//传给模态窗口的参数
			var submitFn = ruizhi.demo.demoOpenWin.doSomeThing;//模态窗口提交后回调方法

			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn);
			
		},
		
		doSomeThing :function(param){
			alert('abc');
			alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
		},
		
		openStaffSingleWin : function() {
			
			var url = WEB_ROOT + '/oaas/func/staff/selStaff.do';
			var width = null;
			var heigth = null;
			//var paramObj = {staffId:"1",staffName:'world'};//传给模态窗口的参数
			var paramObj = {};
			//组织标识，orgFlag（all所有组织，cur当前组织,child下级组织,curchild当前组织及下级组织,none不可选）,
			//postFlag(all所有职务，none不可选)
			paramObj.orgFlag = "all";
			paramObj.postFlag = "all";
			//paramObj.staffIds = "1,310,410";
			
			var submitFn = ruizhi.demo.demoOpenWin.openStaffSingleWinComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		
		openStaffSingleWinComeBack :function(param){
			//alert('abc');
			alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
		},
		
		openStaffMoreWin : function() {
			
			var url = WEB_ROOT + '/oaas/func/staff/selStaffs.do';
			var width = null;
			var heigth = null;
			//var paramObj = {staffId:"1",staffName:'world'};//传给模态窗口的参数
			var paramObj = {};
			//组织标识，orgFlag（all所有组织，cur当前组织,child下级组织,curchild当前组织及下级组织,none不可选）,
			//postFlag(all所有职务，none不可选)
			paramObj.orgFlag = "all";
			paramObj.postFlag = "all";
			paramObj.staffIds = "1,310,410";
			
			var submitFn = ruizhi.demo.demoOpenWin.openStaffMoreWinComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		
		openStaffMoreWinComeBack :function(param){
			//alert('abc');
			alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
		},
		
		openOrgSingleWin : function() {
			
			var url = WEB_ROOT + '/oaas/func/org/selOrg.do';
			var width = null;
			var heigth = null;
			//var paramObj = {staffId:"1",staffName:'world'};//传给模态窗口的参数
			var paramObj = {};
			//组织标识，orgFlag（all所有组织，cur当前组织,child下级组织,curchild当前组织及下级组织,none不可选）,
			paramObj.orgFlag = "all";
			
			var submitFn = ruizhi.demo.demoOpenWin.openOrgSingleWinComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		
		openOrgSingleWinComeBack :function(param){
			//alert('abc');
			alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
		},
		
		openOrgMoreWin : function() {
			
			var url = WEB_ROOT + '/oaas/func/org/selOrgs.do';
			var width = null;
			var heigth = null;
			//var paramObj = {staffId:"1",staffName:'world'};//传给模态窗口的参数
			var paramObj = {};
			//组织标识，orgFlag（all所有组织，cur当前组织,child下级组织,curchild当前组织及下级组织,none不可选）,
			paramObj.orgFlag = "all";
			paramObj.orgIds = "64,65,66";
			
			var submitFn = ruizhi.demo.demoOpenWin.openOrgMoreWinComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		
		openOrgMoreWinComeBack :function(param){
			//alert('abc');
			alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
		},

	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.demoOpenWin.init();
});
