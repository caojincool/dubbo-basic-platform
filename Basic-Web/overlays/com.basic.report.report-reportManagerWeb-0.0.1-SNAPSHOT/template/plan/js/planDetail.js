//计划任务
ruizhi.Package("ruizhi.plan");

ruizhi.plan.planDetail = function() {
	
	//私有成员
	var form1 = null;
	var tab1;
	var _this = this;
	var URL =  WEB_ROOT + '/plan/func/planBase/planDetail.do';//当前窗口的URL
	var grid_taskTemplate2;
	var grid_terminalObject2;
	var grid_area1;
	//私有方法
	
	return{//公有成员
		/* 初始化 */
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var planBaseItem = paramObj.planBaseItem;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(planBaseItem));
			
			form1 = new ruizhi.FormExt("plan-planDetail-form1");
			tab1 = new ruizhi.Tab('plan-planDetail-tab1');
			tab1.setHide('area');
			
			form1.objectToForm(planBaseItem);
			var planId = form1.getValue("planId");
			//alert(planId);
			grid_taskTemplate2 = new ruizhi.DataGrid("plan-planDetail-grid1", {
				postData : {
					planId : planId
					//userName : "world"
				},
				url : WEB_ROOT+"/taskTemplate/func/taskTemplate/queryByPlanId.do"
			});
			
			grid_terminalObject2 = new ruizhi.DataGrid("plan-planDetail-grid2", {
				postData : {
					planId : planId
					//userName : "world"
				},
				url : WEB_ROOT+"/terminal/func/terminalObject/queryByPlanId.do"
			});
			
			grid_area1 = new ruizhi.DataGrid("plan-planDetail-grid3", {
				postData : {
					planId : planId
					//userName : "world"
				},
				url : WEB_ROOT+"/plan/func/planArea/queryByPlanId.do"
			});
		},
		
		//单选人员窗口
		openStaffSingleWin : function() {
			
			var url = WEB_ROOT + '/oaas/func/staff/selStaff.do';
			var width = null;
			var heigth = null;
			//var paramObj = {staffId:"1",staffName:'world'};//传给模态窗口的参数
			var paramObj = {};
			//组织标识，orgFlag（all所有组织，cur当前组织,child下级组织,curchild当前组织及下级组织,none不可选）,
			//postFlag(all所有职务，none不可选)
			paramObj.orgFlag = "all";
			paramObj.postFlag = "all";
			//paramObj.staffIds = "1,310,410";
			
			var submitFn = ruizhi.plan.planDetail.openStaffSingleWinComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		//单选人员回调方法
		openStaffSingleWinComeBack :function(param){
			//alert('abc');
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
			form1.setValue("execStaff",param.staffId);
			form1.setValue("execStaffName",param.staffName);
		},
		
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			//var planId = form1.getValue("planId");
			//alert(planId);
			var valueObj = form1.formToObject();// 整个表单的值
			//不能为空，不然后台转为对象的时候类型不匹配
			if(valueObj.planId == ''){
				valueObj.planId = null;
			}
			if(valueObj.execStaff == ''){
				valueObj.execStaff = null;
			}
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			//var valueArr = form1.formToArr();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueArr));
			var taskTemplates = grid_taskTemplate2.getAllItems();
			var terminalObjects = grid_terminalObject2.getAllItems();
			var areas = grid_area1.getAllItems();
			for(var i in taskTemplates){
				var item = taskTemplates[i];
				if(item.planTaskTemplateId == ''){
					item.planTaskTemplateId = null;
				}
			}
			for(var i in terminalObjects){
				var item = terminalObjects[i];
				if(item.planObjectId == ''){
					item.planObjectId = null;
				}
			}
			for(var i in areas){
				var item = areas[i];
				if(item.planAreaId == ''){
					item.planAreaId = null;
				}
			}
			valueObj.taskTemplates = taskTemplates;
			valueObj.terminalObjects = terminalObjects;
			valueObj.areas = areas;
			valueObj.createStaff = _SESSION.staffId;//创建人
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			

		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
		taskTemplateAdd : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/taskTemplate/func/taskTemplate/selTaskTemplates.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			//将当前窗口的表格的所有数据传到下一窗口
			var allItems = grid_taskTemplate2.getAllItems();
			paramObj.allItems = allItems;
			var submitFn = ruizhi.plan.planDetail.taskTemplateAddComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		taskTemplateAddComeBack : function(param){
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
			//grid_taskTemplate2.addItem(param.taskTemplateId,param);
			if(param != null && param.length > 0){
				for(var i in param){
					var taskTemplate = param[i];
					//alert(ruizhi.ToJson(taskTemplate));
					if(taskTemplate != null){
						var taskTemplateId = taskTemplate.taskTemplateId;
						var flag = grid_taskTemplate2.isExist(taskTemplateId);
						if(!flag){
							grid_taskTemplate2.addItem(taskTemplateId,taskTemplate);
						}else{
							ruizhi.Alert('数据已存在');
						}
						
					}
				}
			}
		},
		
		taskTemplateDel : function() {
			var selItem = grid_taskTemplate2.getSelectedItem();//所选行
			if(selItem != null){
				//alert(selItem.planTaskTemplateId);
				grid_taskTemplate2.removeItem(selItem.taskTemplateId);
				if(selItem.planTaskTemplateId != null && selItem.planTaskTemplateId != '' && selItem.planTaskTemplateId != 'undefined'){
					var params = {};
					params.planTaskTemplateId = selItem.planTaskTemplateId;
					params.modifyStaff = _SESSION.staffId;//修改人
					//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
					var url = WEB_ROOT + "/plan/func/planTaskTemplate/delPlanTaskTemplate.do";
					ruizhi.InvokeMethod(url,params);
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		terminalObjectAdd : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/terminal/func/terminalObject/selTerminalObjects.do';
			var width = null;
			var heigth = null;
			//将当前窗口的表格的所有数据传到下一窗口
			var allItems = grid_terminalObject2.getAllItems();
			paramObj.allItems = allItems;
			var submitFn = ruizhi.plan.planDetail.terminalObjectAddComeBack;//模态窗口提交后回调方法
			
			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		terminalObjectAddComeBack : function(param){
			
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
			//grid_taskTemplate2.addItem(param.taskTemplateId,param);
			if(param != null && param.length > 0){
				for(var i in param){
					var terminalObject = param[i];
					//alert(ruizhi.ToJson(terminalObject));
					
					terminalObject = recursionTemplate(terminalObject, terminalObject.parentId);
					
					if(terminalObject != null){
						//alert(terminalObject.taskTemplateId);
						if(terminalObject.taskTemplateId == null || terminalObject.taskTemplateId == 'undefined'){
							ruizhi.Alert(terminalObject.objectName+'没有找到匹配模板，请先添加任务模板！');
							tab1.setTabDisplay('0');
							break;
						}
						var objectId = terminalObject.objectId;
						var flag = grid_terminalObject2.isExist(objectId);
						if(!flag){
							grid_terminalObject2.addItem(objectId,terminalObject);
						}else{
							ruizhi.Alert('数据已存在');
						}
					}
				}
			}
			
			//递归查找匹配的任务模板，从当前终端类型开始，没有就循环父类型
			function recursionTemplate(terminalObject, parentId){
				//所有的任务模板
				var taskTemplateAllItems = grid_taskTemplate2.getAllItems();
				
				for(var t in taskTemplateAllItems){
					var taskTemplate = taskTemplateAllItems[t];
					if(taskTemplate.terminalClassId == terminalObject.terminalClassId){
						terminalObject.taskTemplateName = taskTemplate.taskTemplateName;
						terminalObject.taskTemplateId = taskTemplate.taskTemplateId;
						//alert(terminalObject.taskTemplateName);
						return terminalObject;
					}
				}
				
				if(parentId != '-1'){
/*					$.ajax({
	 	  				url: WEB_ROOT + "/terminal/func/terminalClass/getTerminalClass", 
	 	  				async: false, 
	 	  				type: 'POST', 
	 	  				data: {terminalClassId:parentId},
	 	  				dataType: 'json', 
	 	  				success: function(terminalClass){
	 	  					if(terminalClass != null && terminalClass != '' && terminalClass != undefined) {
	 	  						recursionTemplate(terminalObject, terminalClass.parentId);
	 	  					} else {
	 	  						
	 	  					}
	 	  				}
	 	  			});*/
					var url = WEB_ROOT + "/terminal/func/terminalClass/getTerminalClass.do";
					var params = {terminalClassId:parentId};
					var terminalClass = ruizhi.InvokeMethod(url,params);
					recursionTemplate(terminalObject, terminalClass.parentId);
					//alert(ruizhi.ToJson(terminalClass));
				}
				return terminalObject;
			}
		},
		
		terminalObjectDel : function() {
			var selItem = grid_terminalObject2.getSelectedItem();//所选行
			if(selItem!=null){
				grid_terminalObject2.removeItem(selItem.objectId);
				if(selItem.planObjectId != null && selItem.planObjectId != '' && selItem.planObjectId != 'undefined'){
					var params = {};
					params.planObjectId = selItem.planObjectId;
					params.modifyStaff = _SESSION.staffId;//修改人
					//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
					var url = WEB_ROOT + "/plan/func/planObject/delPlanObject.do";
					ruizhi.InvokeMethod(url,params);
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		planAreaAdd : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/func/area/selAreaAndCount.do';
			var width = null;
			var heigth = null;
			//将当前窗口的表格的所有数据传到下一窗口
			//var allItems = grid_area1.getAllItems();
			//paramObj.allItems = allItems;
			var submitFn = ruizhi.plan.planDetail.planAreaAddComeBack;//模态窗口提交后回调方法
			
			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		planAreaAddComeBack : function(param){
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
			var area = param;
			if(area != null){
				var areaId = area.areaId;
				var flag = grid_area1.isExist(areaId);
				if(!flag){
					grid_area1.addItem(areaId,area);
				}else{
					ruizhi.Alert('数据已存在');
				}
			}
		
		},
		
		planAreaDel : function() {
			var selItem = grid_area1.getSelectedItem();//所选行
			if(selItem!=null){
				grid_area1.removeItem(selItem.areaId);
				if(selItem.planAreaId != null && selItem.planAreaId != '' && selItem.planAreaId != 'undefined'){
					var params = {};
					params.planAreaId = selItem.planAreaId;
					params.modifyStaff = _SESSION.staffId;//修改人
					//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
					var url = WEB_ROOT + "/plan/func/planArea/delPlanArea.do";
					ruizhi.InvokeMethod(url,params);
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		//计划类型改变触发事件
		planTypeChange : function(name){
			var value = form1.getValue(name);
			//alert("你选择选项的值是："+ value);
			//var text = form1.getSelText(name);
			//alert("你选择选项的文本是："+ text);
			if(value == 'AREA'){
				tab1.setHide('terminal');
				tab1.setShow('area');
			}
			if(value == 'TERMINAL'){
				tab1.setHide('area');
				tab1.setShow('terminal');
			}
		},
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.plan.planDetail.init()
});

// ////////////////////////////////////////
// function定义
