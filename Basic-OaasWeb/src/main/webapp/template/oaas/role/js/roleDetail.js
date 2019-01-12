//知识体系详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.roleDetail = function() {
	
	//私有成员
	var form1;
	var _this = this;
	var URL =  WEB_ROOT + '/oaas/funcPage/role/roleDetail.do';//当前窗口的URL
	//私有方法
	var checkCode;
	return{
		/* 初始化 */
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			var func = paramObj.role;
			
			var catalog = paramObj.catalog;
			
			form1 = new ruizhi.FormExt("oaas-roleDetail-form1");
			
			//新增
			if(ruizhi.IsNull(func)){
				form1.objectToForm(catalog);
			}
			//修改
			else{
				form1.getObject('roleCode').prop("disabled",true);
				form1.objectToForm(func);
			}
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.roleId == ''){
				valueObj.roleId = null;
				valueObj.createUserId = _SESSION.userId;//创建人
			}else{
				valueObj.modifyUserId = _SESSION.userId;//修改人
			}
			valueObj.state ='10A';
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			

		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		//单选目录
		selCatalog : function(){
			var url = WEB_ROOT + '/oaas/funcPage/roleCatalog/selCatalog.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			var submitFn = ruizhi.oaas.roleDetail.selCatalogComeBack;//模态窗口提交后回调方法
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
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.roleDetail.init()
});

// ////////////////////////////////////////
// function定义
