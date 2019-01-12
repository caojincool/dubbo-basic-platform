package com.basic.framework.workflow.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.basic.framework.workflow.busi.ProcessStartNotice;

@Service("processStartNotice")
public class ProcessStartNoticeDemoImpl implements ProcessStartNotice{

	private static final Logger logger = LoggerFactory.getLogger(ProcessStartNoticeDemoImpl.class);

	/* (non-Javadoc)
	 * @see com.basic.framework.workflow.busi.ProcessStartNotice#processStart(java.lang.Long, java.lang.String)
	 */
	@Override
	public void processStart(Long busiOrderId, String processInstanceId) {
		logger.debug("流程启动 busiOrderId:{}, processInstanceId:{}", busiOrderId, processInstanceId);
	}

}
