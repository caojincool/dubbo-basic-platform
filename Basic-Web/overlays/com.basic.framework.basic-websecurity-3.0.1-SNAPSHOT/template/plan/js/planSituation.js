//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.plan");

ruizhi.plan.planSituation = function() {
	
	//私有成员
	var _this = this;
	var URL =  WEB_ROOT + '/plan/func/planBase/planSituation.do';//当前窗口的URL
	var grid1;
	//私有方法
	
	return{//公有成员
		/* 初始化 */
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var planBaseItem = paramObj.planBaseItem;
			var planId = planBaseItem.planId;
			//alert(planId);
			
			grid1 = new ruizhi.DataGrid("plan-planSituation-grid1", {
				postData : {
					planId : planId
					//userName : "world"
				},
				url : WEB_ROOT+"/plan/func/planObject/queryByPlanId.do"
			});
		},

		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.plan.planSituation.init()
});

// ////////////////////////////////////////
// function定义
