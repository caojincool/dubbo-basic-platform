package com.basic.framework.workflow.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.basic.framework.workflow.busi.ProcessCompleteNotice;

@Service("processCompleteNotice")
public class ProcessCompleteNoticeDemoImpl implements ProcessCompleteNotice{

	private static final Logger logger = LoggerFactory.getLogger(ProcessCompleteNoticeDemoImpl.class);

	/* (non-Javadoc)
	 * @see com.basic.framework.workflow.busi.ProcessCompleteNotice#processComplete(java.lang.Long, java.lang.String)
	 */
	@Override
	public void processComplete(Long busiOrderId, String processInstanceId) {
		logger.debug("流程报竣 busiOrderId:{}, processInstanceId:{}", busiOrderId, processInstanceId);
	}

}
