package com.basic.framework.workflow.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.basic.framework.workflow.busi.ProcessReturnFinishNotice;

@Service("processReturnFinishNotice")
public class ProcessReturnFinishNoticeDemoImpl implements ProcessReturnFinishNotice{

	private static final Logger logger = LoggerFactory.getLogger(ProcessReturnFinishNoticeDemoImpl.class);

	/* (non-Javadoc)
	 * @see com.basic.framework.workflow.busi.ProcessBackNotice#processBack(java.lang.Long, java.lang.String)
	 */
	@Override
	public void processReturnFinish(Long busiOrderId, String processInstanceId) {
		logger.debug("流程回退完成通知 busiOrderId:{}, processInstanceId:{}", busiOrderId, processInstanceId);
	}

}
