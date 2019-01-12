package com.basic.framework.workflow.busi;

/**
 * Created by lzj on 2017/7/26.
 */
public interface ProcessCompleteNotice {

    /**
     * 流程报竣
     * @param busiOrderId 业务单标识
     * @param processInstanceId 流程实例标识
     */
    void processComplete(Long busiOrderId,String processInstanceId);
}
