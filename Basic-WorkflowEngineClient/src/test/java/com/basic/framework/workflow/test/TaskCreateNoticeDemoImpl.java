package com.basic.framework.workflow.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.basic.framework.workflow.busi.TaskCreateNotice;

@Service("taskCreateNotice")
public class TaskCreateNoticeDemoImpl implements TaskCreateNotice{

	private static final Logger logger = LoggerFactory.getLogger(TaskCreateNoticeDemoImpl.class);

	/* (non-Javadoc)
	 * @see com.basic.framework.workflow.busi.TaskCreateNotice#taskCreate(java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public void taskCreate(String taskId, String taskDefinitionKey, Long busiOrderId) {
		logger.debug("任务创建 taskId:{}, taskDefinitionKey:{}, busiOrderId:{}", taskId, taskDefinitionKey, busiOrderId);
	}

}
