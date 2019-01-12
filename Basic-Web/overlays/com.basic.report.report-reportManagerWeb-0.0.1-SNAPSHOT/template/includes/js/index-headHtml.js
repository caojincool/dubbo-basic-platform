//////////////////////////////////////////
//RuizhiSoft corp. 2017-6-28 17:59:25
//Author :zheng.zhijie
//commits:主页
//////////////////////////////////////////

//类定义
ruizhi.Package("ruizhi.includes");

ruizhi.includes.index_head = function() {
	
	//私有成员
	var _this = this;
	var session = null;
	
	
	
	return{
		/* 初始化 */
		init : function() {
			
			session = ruizhi.GetSession();
			
			
			/**
			 * 获取用户详细信息
			 */
			if(!ruizhi.IsNull(session)){
				var userId = session.userId;
				ruizhi.GetObj("includes-menu_navbar-displayUserName").html("您好，"+session.userName);
				var url = WEB_ROOT + "/common/user/qryUserDetail.do";
				var paramObj = {};
				paramObj.userId = userId;
				_UserDetail = null;
				var msg = ruizhi.InvokeMethod(url,paramObj);
				ruizhi.includes.index_head.userDetailComeBack(msg);
			}
			
		},
		
		userDetailComeBack : function(obj) {
			if(!ruizhi.IsNull(obj)){
				_UserDetail = obj;
			   $("#menu_userName").text(_UserDetail.staffName);
				
			　　   var companys = _UserDetail.companys;
			    var company = _UserDetail.company;
			    $("#companysTab ul").remove();
			    
			    $("#companysTab a").html("<i class='icon-user-plus'></i>"+company.chineseName);
			    if(ruizhi.IsNull(companys)){
			    	return;
			    }
			    
			 	$("#companysTab").append("<ul id='companyTab'></ul>");
			 	for(var i=0;i<companys.length;i++){
			 		if(companys[i]!= null){
			 			$("#companyTab").append("<li><a href='javascript:void(0)' onclick='ruizhi.includes.index_head.switchCompany("+companys[i].companyId+")'>"+companys[i].chineseName+"</a></li>");
			 		}
			 		
			 	}
			 	$("#companysTab").hover(function(){
			 		$("#companyTab").show();
			 	},function(){
			 		$("#companyTab").hide();
			 	});
			 	
			}else{
				ruizhi.Alert("获取用户详细信息失败！");
			}
		},
		switchOrg :function (){
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/common/funcPage/user/switchOrg.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.includes.index_head.switchOrgComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-sm";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		switchOrgComeBack : function(param){
			if(!ruizhi.IsNull(param.orgIndex)){
				
				var orgObj = _UserDetail.jobOrgAreas[param.orgIndex];
				
				_UserDetail.areaCode = orgObj.areaCode;
				_UserDetail.areaCodePath = orgObj.areaCodePath
				_UserDetail.areaId = orgObj.areaId
				_UserDetail.areaName = orgObj.areaName
				_UserDetail.areaNamePath = orgObj.areaNamePath
				_UserDetail.orgCode = orgObj.orgCode
				_UserDetail.orgCodePath = orgObj.orgCodePath
				_UserDetail.orgId = orgObj.orgId
				_UserDetail.orgName = orgObj.orgName
				_UserDetail.orgNamePath = orgObj.orgNamePath
				_UserDetail.jobCode = orgObj.jobCode
				_UserDetail.jobId = orgObj.jobId
				_UserDetail.jobName = orgObj.jobName
				
				ruizhi.Alert("操作成功!")
			}
			//ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.user.loadData);
		},
		switchCompany:function(companyId){
			var msg = ruizhi.InvokeMethod(WEB_ROOT+"/common/switchCompany.do",{
				"companyId":companyId,
				"userId":_UserDetail.userId
			});
			 ruizhi.includes.index_head.userDetailComeBack(msg);
		},

	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.includes.index_head.init();
	$.support.cors = true;
});