//////////////////////////////////////////
//workflowEngine
ruizhi.Package("ruizhi.workflow");

ruizhi.workflow.workflowEngine = function() {

	var grid1;
	var form1;
	var tab1;
	var grid2;
	var grid3;
	var grid4;
	var grid5;

	return {
		//初始化
		init : function() {

			grid1 = new ruizhi.DataGrid("workflow-workflowEngine-grid1"/*,{
				postData : {
					
				},
				url : WEB_ROOT + '/workflow/func/workflowEngine/pagin.do'
			}*/);
			form1 = new ruizhi.FormExt("workflow-workflowEngine-form1");
			
			tab1 = new ruizhi.Tab('workflow-workflowEngine-tab1');
			
			grid2 = new ruizhi.DataGrid("workflow-workflowEngine-grid2");
			grid3 = new ruizhi.DataGrid("workflow-workflowEngine-grid3");
			grid4 = new ruizhi.DataGrid("workflow-workflowEngine-grid4");
			grid5 = new ruizhi.DataGrid("workflow-workflowEngine-grid5");
		},

		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/workflow/func/workflowEngine/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//单击事件
		onItemClick : function(rowId) {
			var url2 = WEB_ROOT + '/workflow/func/workflowEngine/qryCliWorkflowCommandList.do';
			var url3 = WEB_ROOT + '/workflow/func/workflowEngine/qrySerWorkflowCommandExecList.do';
			var url4 = WEB_ROOT + '/workflow/func/workflowEngine/qrySerWorkflowNoticeList.do';
			var url5 = WEB_ROOT + '/workflow/func/workflowEngine/qryCliWorkflowNoticeExecList.do';
			var item = grid1.getSelectedItem();
			if(!ruizhi.IsNull(item)){
				var paramObj = {};
				paramObj.processInstanceId = item.id;
				paramObj.processDefineKey = item.processDefinitionKey;
				
				grid2.loadData(url2, paramObj);
				grid3.loadData(url3, paramObj);
				grid4.loadData(url4, paramObj);
				grid5.loadData(url5, paramObj);
			}
		},
		
		//客户端命令重新执行
		rerunCliWorkflowCommand : function() {
			var url = WEB_ROOT + '/workflow/func/workflowEngine/rerunCliWorkflowCommand.do';
			var selItem = grid2.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				ruizhi.InvokeMethodAsyn(url,selItem, ruizhi.workflow.workflowEngine.onItemClick);
				//ruizhi.workflow.workflowEngine.onItemClick();
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		//服务端通知重新执行
		rerunSerWorkflowNotice : function() {
			var url = WEB_ROOT + '/workflow/func/workflowEngine/rerunSerWorkflowNotice.do';
			var selItem = grid4.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				ruizhi.InvokeMethodAsyn(url,selItem, ruizhi.workflow.workflowEngine.onItemClick);
				//ruizhi.workflow.workflowEngine.onItemClick();
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.workflow.workflowEngine.init();
});