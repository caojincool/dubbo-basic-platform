//知识体系详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privateDetail = function() {
	
	//私有成员
	var form1;
	var _this = this;
	var URL =  WEB_ROOT + '/oaas/funcPage/private/privateDetail.do';//当前窗口的URL
	//私有方法
	var checkCode;
	return{
		/* 初始化 */
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			var catalog = paramObj.catalog;
			
			var private = paramObj.private;
			
			form1 = new ruizhi.FormExt("oaas-privateDetail-form1");
			
			//新增
			if(ruizhi.IsNull(private)){
				if(!ruizhi.IsNull(catalog)){
					
					form1.setValue('catalogId',catalog.catalogId);
					form1.setValue('catalogName',catalog.catalogName);
				}
			}
			//修改
			else{
				form1.getObject('privateCode').prop("disabled",true);
				//修改特殊权限
				if(private.privateType == 'SPECIAL'){
					
				}
				else{
					form1.getObject('privateName').prop("disabled",true);
				}
				form1.objectToForm(private);
			}
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.privateId == ''){
				valueObj.privateId = null;
				valueObj.createUserId = _SESSION.userId;//创建人
			}else{
				valueObj.modifyUserId = _SESSION.userId;//修改人
			}
			valueObj.privateType=form1.getValue('privateType');
			valueObj.state ='10A';
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			

		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		//单选区域
		selCatalog : function(){
			var url = WEB_ROOT + '/oaas/funcPage/private/selCatalog.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			var submitFn = ruizhi.oaas.privateDetail.selCatalogComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-sm';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selCatalogComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			var params = {};
			if(param != null && param.length >0){
				for(var i = 0 ; i < param.length;i++){
					form1.setValue('catalogId',param[0].catalogId);
					form1.setValue('catalogName',param[0].catalogName);
				}
				
			}
		},
		areaCode : function(){
			if(checkCode == 'true'){
				var url = WEB_ROOT + '/oaas/func/areaCode.do';;
				var areaCode = form1.getValue('areaCode');
				var params = {};
				params.areaCode = areaCode;
				var valueObj = ruizhi.InvokeMethodAsyn(url,params,function(){
					
					$("#areaInfo").css("color","red").text(valueObj.info);
				});
			}
		}
		
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.privateDetail.init()
});

// ////////////////////////////////////////
// function定义
