package com.basic.framework.workflowser.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflowser.dao.SerWorkflowCommandExecMapper;
import com.basic.framework.workflowser.model.SerWorkflowCommandExec;

@Service("serWorkflowCommandExecService")
public class SerWorkflowCommandExecServiceImpl implements SerWorkflowCommandExecService{

	private Logger logger = LoggerFactory.getLogger(SerWorkflowCommandExecServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private SerWorkflowCommandExecMapper serWorkflowCommandExecMapper;

	/* (non-Javadoc)
	 * @see com.basic.framework.workflowcli.service.SerWorkflowCommandExecService#qrySerWorkflowCommandExecList(com.basic.framework.workflowser.bean.WorkflowIbean)
	 */
	@Override
	public List<SerWorkflowCommandExec> qrySerWorkflowCommandExecList(WorkflowIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qrySerWorkflowCommandExecList WorkflowIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<SerWorkflowCommandExec> list = serWorkflowCommandExecMapper.selectSerWorkflowCommandExecList(ibean);
		return list;
	}


}
