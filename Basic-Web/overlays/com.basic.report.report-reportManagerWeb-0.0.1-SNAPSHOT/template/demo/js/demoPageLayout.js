//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

/* 初始化 */

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.demo");
ruizhi.demo.demoPageLayout = function() {

	var orgTree = null;// 组织树
	var gridList = null;// 右边树

	return {
		init : function() {
			orgTree = new ruizhi.DataGrid("demo-demoPageLayout-grid1", {
				postData : {
					id : 1,
					userName : "world"
				},
				url : WEB_ROOT+"/demo/func/demoGridTree/qryTree.do"
			});

			gridList = new ruizhi.DataGrid("demo-demoPageLayout-grid2");
			
			
//			var parentEle = $("#demo-demoPageLayout-grid1").parent();
//			
//			parentEle.resize(function(e) {
//				var width =parentEle.width();
//				alert("宽度col1：" + width);
//				orgTree.setGridWidth(width);
//			});
//			
//			$("#demo-demoPageLayout-col2").resize(function(e) {
//				var width = $("#demo-demoPageLayout-col2").width();
//				alert("宽度col2：" + width);
//				gridList.setGridWidth(width);
//			});
		},
		
		loadData : function() {
			var url = WEB_ROOT + '/demo/func/demoGridTree/qryTree.do';
			var paramObj = {// 没有用，测试后台能不能读到
				id : 1,
				userName : "world"
			}
			orgTree.loadData(url, paramObj);
		},

		orgClick : function(rowId) {// 组织单击
			var url = WEB_ROOT + '/demo/func/demoGridPage/qryPage.do';
			var paramObj = {
				id : 1,
				userName : "world"
			}
			gridList.loadData(url, paramObj);
		},
		
		/* 填加人员 */
		addStaff:function(){
			var url = WEB_ROOT + '/demo/win/demoFormModalWin.do';
			var width = null;
			var heigth = null;
			var paramObj = {staffId:"1",staffName:'world'};
			var submitFn = ruizhi.demo.demoPageLayout.orgClick();//刷新数据
			var eleId = null;//'modal_theme_primary';
			var eleContentId = null;//'modal-content';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,eleId,eleContentId);
		},
		
		advancedSearch:function(form){
			var url = WEB_ROOT + '/demo/win/demoSearchWin.do';
			var width = null;
			var heigth = null;
			var paramObj = $("#"+form).serialize();
			var submitFn = ruizhi.demo.demoPageLayout.orgClick();//刷新数据
			var eleId = 'modal_theme_primary';
			var eleContentId = 'modal-content';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,eleId,eleContentId);
		}

	}

}();
ruizhi.ExecWait(function(){
	ruizhi.demo.demoPageLayout.init();// 页面初始化
});

// ////////////////////////////////////////
// function定义
