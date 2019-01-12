//////////////////////////////////////////
//Ruisoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoGrid = function(){
	
	var grid = null;
	
	return {
		init:function(){
			grid = new ruizhi.DataGrid("demo-demoGrid-grid1");
		},
		
		itemClick : function(rowId){//单击事件
			//alert('这是一个单击,rowId:'+rowId);
			console.info('这是一个单击,rowId:'+rowId);
			/*$("#demo-demoGrid-grid1 tr").click(function(){
				console.info($(this).attr("role"));
			});
			console.info($(this).next("tr"));*/
/*			$("#"+rowId).unbind('click').click(function () {
				var self=$(this);
			    var txt=self.next("tr").attr('id');
			    alert('数据:'+txt);
			});*/
		},
	
		addEditItem : function(){//增加编辑行
			grid.addEditItem();
		},
		
		getAllItems : function(){//获取所有行
			var item = grid.getAllItems();
			var itemStr = JSON.stringify(item);
			alert(itemStr);
		},
		
		delSelectedItem : function(){//获取所选行
			var item = grid.getSelectedItem();
			grid.removeItem(item.id);
		},
		
		loadData : function(){//加载数据
			var url = WEB_ROOT + '/demo/func/demoGrid/qryAll.do';
			var paramObj = {
				id:1,
				userName:"world"
			}
			
//			var paramObj = form.formToObject();
			
			grid.loadData(url,paramObj);
		},
		
		//年龄翻译
		ageTrans:function(cellvalue, options, rowObject){
			if(rowObject.age>20){
				return "成年";
			}else{
				return "未成年";
			}
			
		},
		
		//状态用字典表翻译
		stateTrans:function(cellvalue, options, rowObject){
			var talbeCode = "DEMO_TABLE";
			var colCode = "STATE";
			var colValue = rowObject.state;
			//var stateName = ruizhi.TableDictTrans(talbeCode,colCode,colValue);
			stateName = "翻译状态";
			return stateName;
		},
		
		//把图片Id转成图片
		imageIdTrans : function(cellvalue, options, rowObject){
			var imageId = rowObject.imageId;
			var downloadUrl = WEB_ROOT+"/file/getFile.do?fileInfoId="+imageId;
//			var imgUrl = "<img style='height:40px' src='"+downloadUrl+"'>";
			var imgUrl = "icon-bookmark";
			return imgUrl;
		},
		
		
		savesuccessfunc:function(){
			alert("保存事件！");
		}
	}
	
	
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.demo.demoGrid.init();
});