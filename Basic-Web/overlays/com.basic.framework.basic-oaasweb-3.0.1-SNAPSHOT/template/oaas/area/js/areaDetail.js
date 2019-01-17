//知识体系详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.areaDetail = function() {
	
	//私有成员
	var form1;
	var URL =  WEB_ROOT + '/oaas/funcPage/area/areaDetail.do';//当前窗口的URL
	var paramObj;
	//私有方法
	var checkCode;
	return{
		/* 初始化 */
		init : function() {
			paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var area = paramObj.Area;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(org));
			
			form1 = new ruizhi.FormExt("oaas-areaDetail-form1");
			
			
			var areaGrade = ruizhi.TableDictGetValueList("OAAS_AREA","AREA_GRADE");
			form1.selOptionAddAll('areaGrade',areaGrade,'colText','colValue',true)
			form1.setValue("areaGrade",1);
			form1.objectToForm(area);
			if(form1.getValue("areaId")){
				form1.getObject("areaCode").prop("disabled",true);
			};
			
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.areaId == ''){
				valueObj.createUserId = _SESSION.userId;//创建人
				
			}else{
				valueObj.modifyUserId = _SESSION.userId;//修改人
			}
			if(valueObj.parentAreaId == ''){
				valueObj.parentAreaId = -1;
			}
			if(ruizhi.IsNull(valueObj.state)){
				valueObj.state = '10A'
			}
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			

		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		//单选区域
		selArea : function(){
			var url = WEB_ROOT + '/oaas/funcPage/area/selArea.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.areaId = _SESSION.areaId;//用户标识（All的状态可选）
			paramObj.displayRange = "ALL";//查询范围
			paramObj.title = "单选区域";
			var submitFn = ruizhi.oaas.areaDetail.selAreaComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-sm';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selAreaComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			var params = {};
			if(param != null && param.length >0){
				for(var i = 0 ; i < param.length;i++){
					params.areaName = param[0].areaName;
					params.areaId = param[0].areaId;
					form1.setValue('parentAreaName',params.areaName);
					form1.setValue('parentAreaId',params.areaId);
//					$("#parentAreaName").val(params.areaName);//赋值
//					$("#parentAreaId").val(params.areaId);
				}
				
			}
		},
		areaCode : function(){
			if(checkCode == 'true'){
			var url = WEB_ROOT + '/oaas/func/areaCode.do';;
			var areaCode = form1.getValue('areaCode');
			var params = {};
			params.areaCode = areaCode;
			var valueObj = ruizhi.InvokeMethod(url,params);
			$("#areaInfo").css("color","red").text(valueObj.info);
			}
		}
		
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.areaDetail.init()
});

// ////////////////////////////////////////
// function定义
