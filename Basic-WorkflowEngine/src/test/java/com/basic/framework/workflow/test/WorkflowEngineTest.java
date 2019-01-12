/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年1月18日 下午11:17:04
 * @author lzj
 * @Description: 流程引擎测试
 * 
 */
package com.basic.framework.workflow.test;


import com.basic.framework.workflow.engine.WorkflowEngine;

import com.basic.framework.common.BeanFactoryUtils;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @date 2017年1月18日 下午11:17:04
 * @author lzj
 * @Description: 流程引擎测试
 * 
 */
public class WorkflowEngineTest {

	private static final Logger logger = LoggerFactory.getLogger(WorkflowEngine.class);

	public void testProcessStart() throws Exception {
		WorkflowEngine workflowEngine = BeanFactoryUtils.getBeanByName("workflowEngine", WorkflowEngine.class);
		String processDefineKey = "workflowdemo01";
		Long busiOrderId = 1L;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		workflowEngine.processStart(processDefineKey,busiOrderId,paramMap);

		//assertEquals(true, true);
		//环节任务生成taskCreate,processInstanceId:10001,taskInstanceId:10005,tacheCodev:tacheCode1,busiOrderId:1
	}

	private void queryProcessDefine(){
		String processDefineKey = "testProcessNormal";
		RepositoryService repositoryService = BeanFactoryUtils.getBeanByName("repositoryService",RepositoryService.class);
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefineKey);
		List<ProcessDefinition> list = query.orderByProcessDefinitionVersion().asc().list();
		for(ProcessDefinition processDefinition:list){
			logger.info("processDefineId:{},processDefineKey:{},processDefineName:{},processDefineVersion:{}",processDefinition.getId(),processDefinition.getKey(),processDefinition.getName(),processDefinition.getVersion());
		}
		ProcessDefinition processDefinition = query.latestVersion().singleResult();
		logger.info("last version.......");
		logger.info("processDefineId:{},processDefineKey:{},processDefineName:{},processDefineVersion:{}",processDefinition.getId(),processDefinition.getKey(),processDefinition.getName(),processDefinition.getVersion());
	}

	/**
	 * 查询所有同节点
	 */
	private void findTaskListByKey() {
		TaskService taskService = BeanFactoryUtils.getBeanByName("taskService",TaskService.class);

		String processInstanceId = "65001";
		String taskDefinitionKey="tacheCodeA1";

		List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();

		for (Task task:list){
			logger.info("taskName:{},taskId:{}",task.getName(),task.getId());
		}

	}

	private void findActivity(){
		RepositoryService repositoryService = BeanFactoryUtils.getBeanByName("repositoryService",RepositoryService.class);
		String processDefineKey = "testProcessIf";

		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefineKey).singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(definition.getId());

		List<ActivityImpl> activitiList = processDefinition.getActivities();

		for (ActivityImpl activity : activitiList) {
			logger.info("activityId:{}",activity.getId());

		}
	}

	private void jump(String taskId){
		WorkflowJumpTest workflowJumpTest = BeanFactoryUtils.getBeanByName("workflowJumpTest", WorkflowJumpTest.class);
		workflowJumpTest.taskRollBack(taskId);

	}
	
	
	private void qry(){
		ProcessEngine processEngine = (ProcessEngine) BeanFactoryUtils.getBeanByName("processEngine");
		
		//查询流程定义
		List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
		if(list != null && list.size() > 0){
			for(ProcessDefinition pd : list){
                System.out.println("流程定义ID:"+pd.getId());//流程定义的key+版本+随机生成数  
                System.out.println("流程定义的名称:"+pd.getName());//对应helloworld.bpmn文件中的name属性值  
                System.out.println("流程定义的key:"+pd.getKey());//对应helloworld.bpmn文件中的id属性值  
                System.out.println("流程定义的版本:"+pd.getVersion());//当流程定义的key值相同的相同下，版本升级，默认1  
                System.out.println("资源名称bpmn文件:"+pd.getResourceName());  
                System.out.println("资源名称png文件:"+pd.getDiagramResourceName());  
                System.out.println("部署对象ID："+pd.getDeploymentId());  
                System.out.println("#########################################################");  
			}
		}
		
		
		//查询流程实例
		List<HistoricProcessInstance> list1 = processEngine.getHistoryService().createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().asc().list();
		if(list1 != null && list1.size() > 0){
			for(HistoricProcessInstance pd : list1){
				System.out.println(pd.getBusinessKey());
				System.out.println(pd.getClass());
				System.out.println(pd.getDeleteReason());
				System.out.println(pd.getDeploymentId());
				System.out.println(pd.getDescription());
				System.out.println(pd.getDurationInMillis());
				System.out.println(pd.getEndActivityId());
				System.out.println(pd.getEndTime());
				System.out.println(pd.getId());//流程实例id
				System.out.println(pd.getName());
				System.out.println(pd.getProcessDefinitionId());//流程定义的key+版本+随机生成数  
				System.out.println(pd.getProcessDefinitionKey());//流程编码
				System.out.println(pd.getProcessDefinitionName());//流程名称
				System.out.println(pd.getProcessDefinitionVersion());//版本
				System.out.println(pd.getProcessVariables());//参数
				System.out.println(pd.getStartActivityId());
				System.out.println(pd.getStartTime());//开始时间
				System.out.println(pd.getStartUserId());
				System.out.println(pd.getSuperProcessInstanceId());
				System.out.println(pd.getTenantId());
                System.out.println("#########################################################");  
			}
		}
	
	}

	public static void main(String[] args) throws Exception {
		WorkflowEngineTest test = new WorkflowEngineTest();
		test.qry();
//		test.testProcessStart();
//		test.testTaskComplete("52502");
//		test.findTaskListByKey();
//		test.testProcessStart();
//		test.jump("7501","task02");
//		test.jump("47502");
	}

}
