//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

/* 初始化 */

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.plan");

ruizhi.plan.planBase = function() {

	var grid1;
	var form1;

	return {
		init : function() {
/*			var gridParam ={};
			gridParam.url = WEB_ROOT + '/plan/func/planBase/pagin.do';
			gridParam.postData = {
					//id : 1,
					//userName : "world"
			};
			grid1 = new ruizhi.DataGrid("plan-planBase-grid1",gridParam);*/
			grid1 = new ruizhi.DataGrid("plan-planBase-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/plan/func/planBase/pagin.do'
			});
			form1 = new ruizhi.FormExt("plan-planBase-form1");
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/plan/func/planBase/pagin.do';
			var paramObj = form1.formToObject();
			//alert(ruizhi.ToJson(paramObj));
			grid1.loadData(url, paramObj);
			//alert(form1.getValue("planName"));
		},
		
		create : function() {
			//ruizhi.OpenPage(WEB_ROOT + '/plan/func/planAdd.do');
			//window.location.href = WEB_ROOT + '/plan/func/planAdd.do';
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/plan/func/planBase/planDetail.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var submitFn = ruizhi.plan.planBase.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/plan/func/planBase/createSave.do";
			ruizhi.InvokeMethod(url,params);
			ruizhi.plan.planBase.loadData();
/*			var json = ruizhi.InvokeMethod(url,params);
			alert(json);
			if(json == 'success'){
				ruizhi.Alert("操作成功！");
			}else{
				ruizhi.Alert("操作失败！");
			}*/
		},
		
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/plan/func/planBase/planDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.plan.planBase.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				paramObj.planBaseItem = selItem;
				//paramObj.planId = selItem.planId;
				//alert(selItem.planId);
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		modifyComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/plan/func/planBase/createSave.do";
			ruizhi.InvokeMethod(url,params);
			ruizhi.plan.planBase.loadData();
			/*			var json = ruizhi.InvokeMethod(url,params);
			alert(json);
			if(json == 'success'){
				ruizhi.Alert("操作成功！");
			}else{
				ruizhi.Alert("操作失败！");
			}*/
		},
		
		del : function(){
			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				if(selItem.planId != null && selItem.planId != '' && selItem.planId != 'undefined'){
					var params = {};
					params.planId = selItem.planId;
					params.modifyStaff = _SESSION.staffId;//修改人
					//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
					var url = WEB_ROOT + "/plan/func/planBase/del.do";
					ruizhi.InvokeMethod(url,params);
					ruizhi.plan.planBase.loadData();
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		//下达
		issued : function(){
			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				if(selItem.planId != null && selItem.planId != '' && selItem.planId != 'undefined'){
					var params = {};
					params.planId = selItem.planId;
					params.modifyStaff = _SESSION.staffId;//修改人
					//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
					var url = WEB_ROOT + "/plan/func/planBase/issued.do";
					ruizhi.InvokeMethod(url,params);
					ruizhi.plan.planBase.loadData();
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		situation : function(){
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/plan/func/planBase/planSituation.do';
			var width = null;
			var heigth = null;
			//var submitFn = ruizhi.plan.planBase.createComeBack;//模态窗口提交后回调方法
			var submitFn = null;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				paramObj.planBaseItem = selItem;
				//paramObj.planId = selItem.planId;
				//alert(selItem.planId);
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		//计划任务状态翻译
		stateTranslate : function(cellvalue, options, rowObject){
			var talbeCode = "PLAN_BASE";
			var colCode = "STATE";
			var colValue = rowObject.state;
			var colText = ruizhi.TableDictTrans(talbeCode,colCode,colValue);
			return colText;
		},
		
		//格式化时间
		dateTimeTranslate : function(cellvalue, options, rowObject){
			//alert(cellvalue);
			if(cellvalue != null && cellvalue != '' && cellvalue != 'undefined'){
				var time = ruizhi.StringToDate(cellvalue);
				cellvalue = time.format('yyyy-MM-dd HH:mm');
			}else{
				cellvalue = '';
			}
			return cellvalue;
		},
		
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.plan.planBase.init();
});

// ////////////////////////////////////////
// function定义
