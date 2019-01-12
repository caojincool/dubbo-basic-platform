//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.area = function() {

	var areaTree = null;// 组织树
	var gridList = null;// 右边树
	var areaFrom = null;// 表单
	var buttonGroup ={
		id : "oaas-area-button1",
		buttons : [
			"oaas.areaMmana.func.addArea",
			"oaas.areaMmana.func.addSubArea",
			"oaas.areaMmana.func.modifyArea",
			"oaas.areaMmana.func.delArea"
			],
	}
	var areaType = "";
	return {
		init : function() {
			//生成按钮
			ruizhi.GenButton(buttonGroup);
			
			// TODO 权限功能完善需要回来改
//			if(ruizhi.IsSuperadminRole() || _SESSION.hasPriv("oaas.special.allAreaQry") ){
			if(ruizhi.IsSuperadminRole() ){
				areaType ='ALLAREA';
			}else{
				areaType ='CURAREA';
			}
			
			
			areaFrom =  new ruizhi.FormExt("area-areaMana-from");
			areaTree = new ruizhi.ZTree("area-areaTree-td",{
				url : WEB_ROOT+"/oaas/func/area/qryAreaTree.do",
				autoParam:["id=parentAreaId"],
				otherParam :{
					userId : _SESSION.userId,
					areaType : areaType
				}
			});
		},
		
		loadData : function(){//加载数据
			var url = WEB_ROOT+"/oaas/func/area/qryAreaTree.do";
			var paramObj = {
				userId : _SESSION.userId,
				areaType : areaType,
			}
			areaTree.loadData(url,paramObj);
		},
		/*区域点击事件*/
		treeClick : function(treeNode) {
			areaFrom.reset();
			var talbeCode = "OAAS_AREA";
			var colCode = "AREA_GRADE";
			var colValue = treeNode.areaGrade;
			treeNode.areaGrade = ruizhi.TableDictTrans(talbeCode,colCode,colValue);
			areaFrom.objectToForm(treeNode);
		},
		
		/*添加区域*/
		createArea:function(){
			//重置表单
//			areaFrom.reset();
			var treeNode =  areaTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/area/areaDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			if(!ruizhi.IsNull(treeNode)){
				paramObj.Area={
					parentAreaId :treeNode.parentAreaId,
				}
			}
			var submitFn = ruizhi.oaas.area.createAreaComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createAreaComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			var url = WEB_ROOT + "/oaas/func/area/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.area.loadData();
			});
		},
		
		/*添加子区域*/
		createSubArea:function(form){
			var treeNode =  areaTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/area/areaDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择区域');
				return;
			}
			paramObj.Area={
					parentAreaId :treeNode.areaId,
			}
			var submitFn = ruizhi.oaas.area.createAreaComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		
		editArea:function(){
			var treeNode =  areaTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/area/areaDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择区域');
				return;
			}
			paramObj.Area=treeNode;
			var submitFn = ruizhi.oaas.area.createAreaComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//删除节点
		delArea:function (){
			var treeNode = areaTree.getSelectedNodes()[0];
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择区域');
				return;
			}
			
			var msg ="";
			if(treeNode.isParent){
				msg ="是否删除该区域，以及该区域下的所有区域?";
			}else{
				msg ="是否删除该区域？";
			}
			
			ruizhi.Confirm(msg,function(result){
				if(result || 'true' == result) {
					var params = {};
					params.areaIds = treeNode.areaId;
//					debugger;
					var url = WEB_ROOT+"/oaas/func/area/areaDel.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.area.loadData();
					});
				} else {
					
				}
			})
			
//			var url = WEB_ROOT+"/oaas/func/areaDel.do";
//			ruizhi.InvokeMethod(url,{areaIds:selectNode.id});
		}
		
		
		
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.area.init();
});

// ////////////////////////////////////////
// function定义
