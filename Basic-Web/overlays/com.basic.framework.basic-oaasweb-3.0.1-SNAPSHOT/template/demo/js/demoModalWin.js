//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoModalWin = function() {
	
	var URL =  WEB_ROOT + '/demo/funcPage/demoModalWin.do';//当前窗口的URL
	
	// 私有成员定义;
	var grid1 = null;
	var grdi2 = null;

	// 私有方法定义
	var sayHi = function() {
	}

	return {
		// 公有成员

		// 公有方法
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));

			var obj = {
				url : WEB_ROOT + '/demo/func/demoGrid/qryAll.do',
				postData : {
					id : 1,
					userName : "world"
				}
			}
			grid1 = new ruizhi.DataGrid("demo-demoModalWin-grid1",obj);
			grid2 = new ruizhi.DataGrid("demo-demoModalWin-grid2");
			
		

		},
		
		/**
		 * 
		 */
		add : function() {
			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				var id = selItem.id;
				var flag = grid2.isExist(id);
				if(!flag){
					grid2.addItem(id,selItem);
				}else{
					ruizhi.Alert('数据已存在');
				}
				
			}else{
				ruizhi.Alert('请选择');
			}
			
			
		},

		del : function() {
			var selItem = grid2.getSelectedItem();//所选行
			if(selItem!=null){
				grid2.removeItem(selItem.id);
			}else{
				ruizhi.Alert('请选择');
			}
		},

		doSubmit : function() {

			var allItems = grid2.getAllItems();
			if(allItems!=null && allItems.length>0){
				ruizhi.SubmitModalWin(URL,allItems);//通过公用方法调用父页面的回调方法
			}else{
				ruizhi.Alert('请选择');
			}
			
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
		openWin:function(){

			var url = WEB_ROOT + '/demo/funcPage/demoModalWin1.do';
			var width = null;
			var heigth = null;
			var paramObj = {staffId:"2",staffName:'world2'};//传给模态窗口的参数
			var submitFn = ruizhi.demo.demoModalWin.doSomeThing;//模态窗口提交后回调方法
			var modalClass = '';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		doSomeThing :function(param){
			alert('abc');
			alert("从模态窗口1返回的参数："+ruizhi.ToJson(param));
		}

	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.demoModalWin.init();
});
