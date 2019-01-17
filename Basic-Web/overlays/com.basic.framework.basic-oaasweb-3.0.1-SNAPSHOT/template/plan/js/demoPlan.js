//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

/* 初始化 */

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoPlan = function() {

	var grid;
	var form;

	return {
		init : function() {
			var gridParam ={};
			gridParam.url = WEB_ROOT + '/demo/func/demoPlan/qryPage.do';
			gridParam.postData = {
					id : 1,
					userName : "world"
			};
			grid = new ruizhi.DataGrid("demo-demoPlan-grid1",gridParam);
			form = new ruizhi.FormExt("demo-demoPlan-form1");
		},
		itemDblClick : function(rowId) {// 单击事件
			alert('这是一个双击,rowId:' + rowId);
		},

		getSelectedItem : function() {// 获取所选行
			var item = grid.getSelectedItem();
			var itemStr = JSON.stringify(item);
			alert(itemStr);
		},

		getCheckedItems : function() {// 获取所选行
			var items = grid.getCheckedItems();
			var itemsStr = JSON.stringify(items);
			alert(itemsStr);
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/demo/func/demoPlan/qryPage.do';
			var paramObj = {
				id : 1,
				userName : "world"
			}
			grid.loadData(url, paramObj);
		},
		
		add : function() {
			//ruizhi.OpenPage(WEB_ROOT + '/demo/func/demoPlanAdd.do');
			//window.location.href = WEB_ROOT + '/demo/func/demoPlanAdd.do';
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/demo/func/demoPlanAdd.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var submitFn = ruizhi.demo.demoPlan.addComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		addComeBack : function(){
			alert("执行添加回调方法");
		},
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.demo.demoPlan.init();
});

// ////////////////////////////////////////
// function定义
