//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

/* 初始化 */

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoGridCheck = function() {

	var grid = null;

	return {
		init : function() {
			grid = new ruizhi.DataGrid("demo-demoGridCheck-grid1");
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
			var url = WEB_ROOT + '/demo/func/demoGridCheck/qryAll.do';
			var paramObj = {
				id : 1,
				userName : "world"
			}
			grid.loadData(url, paramObj);
		},
		
		setCheckedItems:function(){//设置所选行
			grid.setCheckedItems([0,1]);
		}
	}

}();

ruizhi.ExecWait(function(){
	ruizhi.demo.demoGridCheck.init();
});

// ////////////////////////////////////////
// function定义
