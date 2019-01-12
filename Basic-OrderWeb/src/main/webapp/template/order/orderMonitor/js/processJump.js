//////////////////////////////////////////
//启动流程页面
ruizhi.Package("ruizhi.order");

ruizhi.order.processJump = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/orderMonitor/processJump.do';//当前窗口的URL
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var processJumpIbean = paramObj.processJumpIbean;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(orderType));
			
			form1 = new ruizhi.FormExt("order-processJump-form1");
			
			form1.objectToForm(processJumpIbean);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(ruizhi.IsNull(valueObj.orderId)){
				ruizhi.Alert("单据id为空！");
				return ;
			}
			if(ruizhi.IsNull(valueObj.targetTacheCode)){
				ruizhi.Alert("请选择目标环节！");
				return ;
			}
			if(ruizhi.IsNull(_UserDetail.userId) || ruizhi.IsNull(_UserDetail.userText)){
				ruizhi.Alert("当前账号为空！");
				return ;
			}else{
				valueObj.operUserId = _UserDetail.userId;//创建人
				valueObj.operUserText = _UserDetail.userText;//创建人名称
			}
			
			
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		//关闭窗口
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},

		//单选环节
		selTache : function(){
			var url = WEB_ROOT + '/order/funcPage/tache/selTache.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选环节";
			var submitFn = ruizhi.order.processJump.selTacheComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selTacheComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('tacheId', param.tacheId);
				form1.setValue('targetTacheCode', param.tacheCode);
				form1.setValue('tacheName', param.tacheName);
			}
		},
		
	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.order.processJump.init()
});