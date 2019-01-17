//////////////////////////////////////////
//Ruizhisoft corp. 2016-03-01
//Author :yu.xiao
//commits:表单
//////////////////////////////////////////

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoForm = function() {
	
	//私有成员
	var form1 = null;
	var _this = this;
	
	//私有方法
	
	return{//公有成员
		/* 初始化 */
		init : function() {
			form1 = new ruizhi.FormExt("demo-demoForm-form1");
			
//			//设置待选项
			var list = [];
			list.push({value:'1',text:'选项1'});
			list.push({value:'2',text:'选项2'});
			form1.selOptionAddAll('selectNormal2',list,'text','value',true);
			form1.setValue('selectNormal2','2');
		},
		
		/* 设置初始值 */
		setInitValue : function() {
			var obj = {
				textNormal : 'textNormal',
				selectNormal : 'opt1',
				selectMuti : ['2','1']
			};
			form1.objectToForm(obj);
			var object = form1.getObject("selectMuti");
			alert("我得到了一个对象："+ruizhi.ToJson(object.attr("name")));
			object.change();
			//$("#demo-demoForm-form1").find(':input[name="selectMuti"]').change();
		},
		/* 设置待选项与初始值 */
		setSelectOption : function(){
			var list = [];
			list.push({value:'1',text:'选项1'});
			list.push({value:'2',text:'选项2'});
			list.push({value:'3',text:'选项3'});
			list.push({value:'4',text:'选项4'});
			
			form1.selOptionAddAll('selectNormal2',list,'text','value',true);
			form1.setValue('selectNormal2','2');
		},
		/* 设置 */
		setSelectOptionMuti : function(){
			var list = [];
			list.push({value:'1',text:'选项1'});
			list.push({value:'2',text:'选项2'});
			list.push({value:'3',text:'选项3'});
			list.push({value:'4',text:'选项4'});
			form1.selOptionAddAll('selectMuti2',list,'text','value',true);
			var obj = {
					textNormal : 'textNormal',
					selectMuti2 : ['2','1']
			};
			form1.objectToForm(obj);
		},
		
		/* 弹窗选择  */
		openModalWin:function(){
			/* 弹窗提交后动作  */
			var doSomeThing = function(paramObj){
				alert(ruizhi.ToJson(paramObj));
			}

			var url = WEB_ROOT + '/demo/win/demoFormModalWin.do';
			var width = null;
			var heigth = null;
			var paramObj = {staffId:"1",staffName:'world'};
			var submitFn = doSomeThing;
			var eleId = null;//'modal_theme_primary';
			var eleContentId = null;//'modal-content';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,eleId,eleContentId);
		},
		
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			
			var valueObj = form1.formToObject();// 整个表单的值
			alert("整个表单数据:"+ToJson(valueObj));
			var valueArr = form1.formToArr();// 整个表单的值
			alert("整个表单数据:"+ToJson(valueArr));

			var textNorma2 = form1.getValue("textNorma2");//获取某一元素的值
			alert(textNorma2);
			
			var selectNormal = form1.getValue("selectNormal");//获取某一元素的值
			alert(selectNormal);

			var selectNormalText = form1.getSelText("selectNormal");// 获取某一元素的文本
			alert(selectNormalText);

			var selectMuti = form1.getValue("selectMuti");//获取某一元素的值
		 	alert(ToJson(selectMuti));

			var selectMutiText = form1.getSelText("selectMuti");// 获取某一元素的值
			alert(ToJson(selectMutiText));
			
			

		},
		
		//单选框触发事件
		change1 : function(name){
			var value = form1.getValue(name);
			alert("你选择选项的值是："+ value);
			var text = form1.getSelText(name);
			alert("你选择选项的文本是："+ text);
		},
		
		//多选框触发事件
		change2 : function(name){
			var value = form1.getValue(name);
			alert("你选择选项的值是："+ value);
			var text = form1.getSelText(name);
			alert("你选择选项的文本是："+ text);
		},
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.demo.demoForm.init()
});

// ////////////////////////////////////////
// function定义
