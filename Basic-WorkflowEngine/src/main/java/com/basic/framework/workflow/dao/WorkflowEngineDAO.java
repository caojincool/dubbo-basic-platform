package com.basic.framework.workflow.dao;

import com.basic.framework.workflow.model.WorkflowCommandExecBean;
import com.basic.framework.workflow.model.WorkflowNoticeBean;

/**
 * Created by lzj on 2017/7/25.
 */
public interface WorkflowEngineDAO {

    /**
     * 写入 WFE_WORKFLOW_COMMAND_EXEC 表
     * @param workflowCommandExecBean
     */
    void insertWorkflowCommandExec(WorkflowCommandExecBean workflowCommandExecBean);

    /**
     * 写入WFE_WORKFLOW_NOTICE 表
     * @param workflowNoticeBean
     */
    void insertWorkflowNotice(WorkflowNoticeBean workflowNoticeBean);
}
