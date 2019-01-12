package com.basic.framework.workflowcli.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflowcli.dao.CliWorkflowNoticeExecMapper;
import com.basic.framework.workflowcli.model.CliWorkflowNoticeExec;

@Service("cliWorkflowNoticeExecService")
public class CliWorkflowNoticeExecServiceImpl implements CliWorkflowNoticeExecService{

	private Logger logger = LoggerFactory.getLogger(CliWorkflowNoticeExecServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private CliWorkflowNoticeExecMapper cliWorkflowNoticeExecMapper;

	/* (non-Javadoc)
	 * @see com.basic.framework.workflowcli.service.CliWorkflowNoticeExecService#qryCliWorkflowNoticeExecList(com.basic.framework.workflowser.bean.WorkflowIbean)
	 */
	@Override
	public List<CliWorkflowNoticeExec> qryCliWorkflowNoticeExecList(WorkflowIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryCliWorkflowNoticeExecList WorkflowIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<CliWorkflowNoticeExec> list = cliWorkflowNoticeExecMapper.selectCliWorkflowNoticeExecList(ibean);
		return list;
	}


}
