//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selArea = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/area/selArea.do';//当前窗口的URL
	
	var zTree1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			//var orgFlag = paramObj.orgFlag;
			
			//组织
			var url = WEB_ROOT + '/oaas/func/area/qryAreaTree.do';
			zTree1 = new ruizhi.ZTree("oaas-area-zTree1",{
				url:url,
				autoParam:["id=parentAreaId"],
				otherParam :{
					userId : _SESSION.userId,
					areaType : paramObj.areaType
				}
			});
//			var qryParams = {};
//			var param = ruizhi.ToJson(paramObj);
//			qryParams.params = param;
//			qryParams.userId= paramObj.userId;
//			qryParams.areaType = paramObj.areaType;
//			zTree1.loadData(url,qryParams);
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selArea-title").html(paramObj.title);
			}
			
		},

		doSubmit : function() {

			var selItem = zTree1.getSelectedNodes();//所选行
			if(selItem!=null){
				ruizhi.SubmitModalWin(URL,selItem);//通过公用方法调用父页面的回调方法
			}else{
				ruizhi.Alert('请选择');
			}
			
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
	}
	
}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.oaas.selArea.init();
});