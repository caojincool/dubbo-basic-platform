package com.basic.framework.workflow.dao;

import com.basic.framework.workflow.model.WorkflowCommandBean;
import com.basic.framework.workflow.model.WorkflowNoticeExecBean;

/**
 * Created by lzj on 2017/7/21.
 */
public interface WorkflowClientDAO {

    /**
     * 写入WFC_WORKFLOW_COMMAND(流程请求)
     * @param workflowCommandBean
     */
    void insertWorkflowCommand(WorkflowCommandBean workflowCommandBean);


    /**
     * 写入 WFC_WORKFLOW_NOTICE_EXEC
     * @param workflowNoticeExecBean
     */
    void insertWorkflowNoticeExec(WorkflowNoticeExecBean workflowNoticeExecBean);


}
