//////////////////////////////////////////
//Ruizhisoft corp. 2016-03-01
//Author :yu.xiao
//commits:表单
//////////////////////////////////////////

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoPlanAdd = function() {
	
	//私有成员
	var form1 = null;
	var _this = this;
	var THE_URL =  WEB_ROOT + '/demo/func/demoPlanAdd.do';//当前窗口的URL
	//私有方法
	
	return{//公有成员
		/* 初始化 */
		init : function() {
			form1 = new ruizhi.FormExt("demo-demoPlanAdd-form1");
			
		},
		
		/* 弹窗选择  */
		openModalWin:function(){
			/* 弹窗提交后动作  */
			var doSomeThing = function(paramObj){
				alert(ruizhi.ToJson(paramObj));
			}

			var url = WEB_ROOT + '/demo/win/demoFormModalWin.do';
			var width = null;
			var heigth = null;
			var paramObj = {staffId:"1",staffName:'world'};
			var submitFn = doSomeThing;
			var eleId = null;//'modal_theme_primary';
			var eleContentId = null;//'modal-content';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn);
		},
		
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			
			var valueObj = form1.formToObject();// 整个表单的值
			alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			var valueArr = form1.formToArr();// 整个表单的值
			alert("整个表单数据:"+ruizhi.ToJson(valueArr));
			
			var param = {};
			ruizhi.SubmitModalWin(THE_URL,param);//通过公用方法调用父页面的回调方法
			

		},
		
		doClose:function(){
			ruizhi.CloseModalWin(THE_URL);
		},
		
		taskTemplateAdd : function() {
			//ruizhi.OpenPage(WEB_ROOT + '/demo/func/demoPlanAdd.do');
			//window.location.href = WEB_ROOT + '/demo/func/demoPlanAdd.do';
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/demo/func/taskTemplateAdd.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var submitFn = ruizhi.demo.demoPlanAdd.taskTemplateAddComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		taskTemplateAddComeBack : function(){
			alert("执行添加回调方法");
		},
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.demo.demoPlanAdd.init()
});

// ////////////////////////////////////////
// function定义
