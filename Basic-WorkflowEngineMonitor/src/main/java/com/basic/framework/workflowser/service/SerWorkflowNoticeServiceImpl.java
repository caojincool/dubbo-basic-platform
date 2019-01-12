package com.basic.framework.workflowser.service;

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
import com.basic.framework.workflowser.dao.SerWorkflowNoticeMapper;
import com.basic.framework.workflowser.model.SerWorkflowNotice;

@Service("serWorkflowNoticeService")
public class SerWorkflowNoticeServiceImpl implements SerWorkflowNoticeService{

	private Logger logger = LoggerFactory.getLogger(SerWorkflowNoticeServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private SerWorkflowNoticeMapper serWorkflowNoticeMapper;

	/* (non-Javadoc)
	 * @see com.basic.framework.workflowcli.service.SerWorkflowNoticeService#qrySerWorkflowNoticeList(com.basic.framework.workflowser.bean.WorkflowIbean)
	 */
	@Override
	public List<SerWorkflowNotice> qrySerWorkflowNoticeList(WorkflowIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qrySerWorkflowNoticeList WorkflowIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<SerWorkflowNotice> list = serWorkflowNoticeMapper.selectSerWorkflowNoticeList(ibean);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.workflowser.service.SerWorkflowNoticeService#createSelective(com.basic.framework.workflowser.model.SerWorkflowNotice)
	 */
	@Override
	public int createSelective(SerWorkflowNotice record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective SerWorkflowNotice:{}", JSON_UTILS.objectToJson(record));
		}
		
		GidClientUtils gidClientUtils = GidClientUtils.getInstance();
		Long noticeId = gidClientUtils.getGidValue(GidCodes.WFE_SER_WORKFLOW_NOTICE_SEQ);
		record.setNoticeId(noticeId);
		Date now = DateUtils.now();
		record.setCreateTime(now);
		return serWorkflowNoticeMapper.insertSelective(record);
	}


}
