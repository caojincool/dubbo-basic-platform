//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.demo");

ruizhi.demo.switchOrgModalWin= function() {
	
	var URL =  WEB_ROOT + '/common/funcPage/user/switchOrg.do';//当前窗口的URL
	
	var form1 = null;
	return {
		// 公有成员

		// 公有方法
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			form1 = new ruizhi.FormExt("demo-switchOrg-form1");
			var html = "";
			var array = _UserDetail.jobOrgAreas;
			for(var i in array){
				html +='<div class="radio">'+
						  '<label>'+
						    '<input type="radio" name="orgIndex" id="optionsRadios1" value="'+i+'" >'+
						   	 array[i].areaName +'--'+array[i].orgName+'--'+array[i].jobName +
						  '</label>'+
						'</div>';
			}
			
			if(!ruizhi.IsNull(_UserDetail.jobOrgAreas)){
				form1.getFormJq().html(html);
				form1.setValue("orgIndex",this.getOrgIndex());
			}else{
				form1.getFormJq().html("暂无组织");
			}
		},

		
		doSubmit : function() {
			
			var params = form1.formToObject();
			ruizhi.SubmitModalWin(URL,params);//通过公用方法调用父页面的回调方法
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		getOrgIndex : function(){
			var array = _UserDetail.jobOrgAreas;
			for(var i in array ){
				if(array[i].areaId == _UserDetail.areaId 
						&& array[i].orgId == _UserDetail.orgId 
						&& array[i].jobId == _UserDetail.jobId){
					
					return i
				}
			}
		}
	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.switchOrgModalWin.init();
});
