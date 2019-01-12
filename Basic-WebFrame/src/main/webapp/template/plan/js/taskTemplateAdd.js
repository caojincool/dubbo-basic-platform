//////////////////////////////////////////
//Ruizhisoft corp. 2016-03-01
//Author :yu.xiao
//commits:表单
//////////////////////////////////////////

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.demo");

ruizhi.demo.taskTemplateAdd = function() {
	
	//私有成员
	var form1 = null;
	var _this = this;
	var THE_URL =  WEB_ROOT + '/demo/func/taskTemplateAdd.do';//当前窗口的URL
	//私有方法
	
	return{//公有成员
		/* 初始化 */
		init : function() {
			form1 = new ruizhi.FormExt("demo-taskTemplateAdd-form1");
			
		},
		
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			var valueArr = form1.formToArr();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueArr));
			
			var param = {};
			ruizhi.SubmitModalWin(THE_URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(THE_URL);
		},
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.demo.taskTemplateAdd.init()
});

// ////////////////////////////////////////
// function定义
