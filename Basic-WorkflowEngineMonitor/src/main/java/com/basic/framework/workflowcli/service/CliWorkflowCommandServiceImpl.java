package com.basic.framework.workflowcli.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflow.define.GidCodes;
import com.basic.framework.workflowcli.dao.CliWorkflowCommandMapper;
import com.basic.framework.workflowcli.model.CliWorkflowCommand;

@Service("cliWorkflowCommandService")
public class CliWorkflowCommandServiceImpl implements CliWorkflowCommandService{

	private Logger logger = LoggerFactory.getLogger(CliWorkflowCommandServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private CliWorkflowCommandMapper cliWorkflowCommandMapper;

	/* (non-Javadoc)
	 * @see com.basic.framework.workflowcli.service.CliWorkflowCommandService#qryCliWorkflowCommandList(com.basic.framework.workflowser.bean.WorkflowIbean)
	 */
	@Override
	public List<CliWorkflowCommand> qryCliWorkflowCommandList(WorkflowIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryCliWorkflowCommandList WorkflowIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<CliWorkflowCommand> list = cliWorkflowCommandMapper.selectCliWorkflowCommandList(ibean);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.workflowcli.service.CliWorkflowCommandService#createSelective(com.basic.framework.workflowcli.model.CliWorkflowCommand)
	 */
	@Override
	public int createSelective(CliWorkflowCommand record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective CliWorkflowCommand:{}", JSON_UTILS.objectToJson(record));
		}
		
		GidClientUtils gidClientUtils = GidClientUtils.getInstance();
		Long commandId = gidClientUtils.getGidValue(GidCodes.WFC_CLI_WORKFLOW_COMMAND_SEQ);
		record.setCommandId(commandId);
		Date now = DateUtils.now();
		record.setCreateTime(now);
		return cliWorkflowCommandMapper.insertSelective(record);
	}


}
