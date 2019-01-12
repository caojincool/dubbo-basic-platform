package com.basic.framework.workflow.busi;

/**
 * 
 *
 * @date 2017年8月16日 下午3:44:45
 * 
 * @Description:流程回退完成通知
 *
 */
public interface ProcessReturnFinishNotice {

	/**
	 * 
	 * @date 2017年8月16日 下午3:45:27
	 * 
	 * @Description: 流程回退完成通知
	 * @param busiOrderId
	 * @param processInstanceId
	 *
	 */
    void processReturnFinish(Long busiOrderId,String processInstanceId);
}
