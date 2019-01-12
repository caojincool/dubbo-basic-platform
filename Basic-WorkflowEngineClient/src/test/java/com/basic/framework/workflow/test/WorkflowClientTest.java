package com.basic.framework.workflow.test;

import com.basic.framework.common.BeanFactoryUtils;
import com.basic.framework.workflow.client.WorkflowClient;
import com.basic.framework.workflow.exception.WorkflowException;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @date 2017年8月1日 下午3:44:56
 * 
 * @Description: 测试客户端的流程
 *
 */
public class WorkflowClientTest {

    public void testWorkflowClient() throws WorkflowException {
    	WorkflowClient workflowClient = BeanFactoryUtils.getBeanByName("workflowClient",WorkflowClient.class);
    	Long serialNo = 1L;
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("key", "value");
    	paramMap.put("1", "zzj");
    	paramMap.put("status", "finsh");
    	
//      workflowClient.processStart(serialNo, "testProcess01", 88L, paramMap);//启动流程-->task01
//      workflowClient.taskComplete(serialNo, "192501", "192508", paramMap);//任务完成-->task02
      workflowClient.taskRollback(serialNo, "192501", "192512", paramMap);//任务回退-->task01
//      workflowClient.processJump(serialNo, "170001", "170011", "task02", paramMap);//流程跳转-->task02
//    	workflowClient.taskComplete(serialNo, "170001", "170013", paramMap);//任务完成-->task03
//    	workflowClient.taskComplete(serialNo, "170001", "170015", paramMap);//任务完成-->task04
//    	workflowClient.taskComplete(serialNo, "170001", "170017", paramMap);//流程报竣
    }

    public static void main(String args[]) throws WorkflowException {
        WorkflowClientTest workflowClientTest = new WorkflowClientTest();
        workflowClientTest.testWorkflowClient();

    }


}
