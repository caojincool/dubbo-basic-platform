package com.basic.framework.workflow.busi;

/**
 * Created by lzj on 2017/7/26.
 */
public interface TaskCreateNotice {

    /**
     * 任务创建
     * @param taskId 任务标识
     * @param taskDefinitionKey 环节编码
     * @param busiOrderId 业务单标识
     */
    void taskCreate(String taskId,String taskDefinitionKey,Long busiOrderId);




}
