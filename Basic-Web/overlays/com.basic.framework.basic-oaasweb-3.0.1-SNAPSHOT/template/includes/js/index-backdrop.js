//////////////////////////////////////////
//RuizhiSoft corp. 2017-6-28 17:59:25
//Author :zheng.zhijie
//commits:主页
//////////////////////////////////////////

//类定义
ruizhi.Package("ruizhi.includes");

ruizhi.includes.index_backdrop = function() {
	
	//私有成员
	var _this = this;
	var loadingModalObj = ruizhi.GetObj("includes-index_backdrop-loadingModal");
	return{
		/* 初始化 */
		init : function() {
			ruizhi.includes.index_backdrop.loadingModalInit();
		},
		
		loadingModalInit : function() {
			//使点击空白处遮罩层不会消失 
			//loadingModalObj.modal({backdrop:'static'});
			//按Tab键遮罩层不会消失 ，默认值为true 
			//loadingModalObj.modal({keyboard:false});
			 
			//也可以一起运用
			//backdrop 为 static 时，点击模态对话框的外部区域不会将其关闭。
			//keyboard 为 false 时，按下 Esc 键不会关闭 Modal。
			//loadingModalObj.modal({backdrop: 'static', keyboard: false});
		},

		
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.includes.index_backdrop.init();
});