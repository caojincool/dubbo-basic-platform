package com.basic.framework.workflow.test;

import com.basic.framework.common.BeanFactoryUtils;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * 
 *
 * @date 2017年8月8日 下午2:25:46
 * 
 * @Description: 流程引擎测试
 *
 */
public class WorkflowEngineTest {

	
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
	
	private void qryByKeyOrId(String key, String id){
		ProcessEngine processEngine = (ProcessEngine) BeanFactoryUtils.getBeanByName("processEngine");
		
		//查询流程实例
		List<HistoricProcessInstance> list1 = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processDefinitionKey(key).orderByProcessInstanceStartTime().asc().list();
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
		List<HistoricProcessInstance> list2 = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(id).list();
		if(list2 != null && list2.size() > 0){
			for(HistoricProcessInstance pd : list2){
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
//		test.qry();
		test.qryByKeyOrId("testProcess01", "165025");
	}

}
