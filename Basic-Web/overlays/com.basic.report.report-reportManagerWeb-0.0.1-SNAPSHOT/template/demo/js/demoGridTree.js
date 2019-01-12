//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

/* 初始化 */

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.demo");
ruizhi.demo.demoGridTree = function(){
	
	var tmpId = 100100;
	var grid1;
	var grid2;
	var grid3;
	
	return {
		init:function(){
			grid1 = new ruizhi.DataGrid("demo-demoGridTree-grid1");
			grid2 = new ruizhi.DataGrid("demo-demoGridTree-grid2");
			grid3 = new ruizhi.DataGrid("demo-demoGridTree-grid3");
		},
		itemDblClick : function(rowId){//单击事件
			alert('这是一个双击,rowId:'+rowId);
		},
	
		getSelectedItem : function(){//获取所选行
			var item = grid1.getSelectedItem();
			var itemStr = JSON.stringify(item);
			alert(itemStr);
		},

		getCheckedItems : function(){//获取所选行
			var items = grid1.getCheckedItems();
			var itemsStr = JSON.stringify(items);
			alert(itemsStr);
		},

		loadData : function(){//加载数据
			var url = WEB_ROOT + '/demo/func/demoGridTree/qryTree.do';
			var paramObj = {
				id:1,
				userName:"world"
			}
			grid1.loadData(url,paramObj);
			
		},
		
		loadData2 : function(){//加载数据
			var url = WEB_ROOT + '/demo/func/demoGridTree/qryTreeAll.do';
			var paramObj = {
				id:1,
				userName:"world"
			}
			grid2.loadData(url,paramObj);
		},
		
		
		loadData3: function(){//加载数据
			var url = WEB_ROOT + '/demo/func/demoGridTree/qryTreeAll2.do';
			var paramObj = {
				id:1,
				userName:"world"
			}
			grid3.loadData(url,paramObj);
		},
		
		addChild:function(){
			
			var item = grid1.getSelectedItem();
			var parentId = item.orgId;
			var orgId = tmpId++;
			var obj = {
					orgCode:'newOrg',
					orgName:'新增的组织',
					namePath:'新增的组织',
					orgId:orgId,
					isLeaf:true,
					expanded:false,
					level:1,
					parent:parentId
			};
			
			grid1.addChildItem(orgId,parentId,obj);
		},
		
		removeChild:function(){
			var item = grid1.getSelectedItem();
			grid1.removeItemChild(item.orgId);
		},

		getChildItemsByRowId:function(){
			
			var item = grid1.getSelectedItem();
			var list = grid1.getChildItemsByRowId(item.orgId);
			var listStr = JSON.stringify(list);
			alert(listStr);
		},
		
		getAllItmes:function(){
			var items = grid1.getAllItems();
			var listStr = JSON.stringify(items);
			alert(listStr);
		}

		
	}
	
}();

ruizhi.ExecWait(function() {
	ruizhi.demo.demoGridTree.init();
});

//////////////////////////////////////////
//function定义