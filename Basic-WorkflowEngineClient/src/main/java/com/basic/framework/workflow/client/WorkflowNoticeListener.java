package com.basic.framework.workflow.client;

import com.basic.framework.common.utils.Exceptions;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.messagequeue.service.MessageHandler;
import com.basic.framework.workflow.busi.ProcessReturnFinishNotice;
import com.basic.framework.workflow.busi.ProcessCompleteNotice;
import com.basic.framework.workflow.busi.ProcessStartNotice;
import com.basic.framework.workflow.busi.TaskCreateNotice;
import com.basic.framework.workflow.dao.WorkflowClientDAO;
import com.basic.framework.workflow.define.GidCodes;
import com.basic.framework.workflow.define.NoticeExecFlag;
import com.basic.framework.workflow.define.NoticeType;
import com.basic.framework.workflow.define.WorkflowError;
import com.basic.framework.workflow.model.WorkflowNoticeBean;
import com.basic.framework.workflow.model.WorkflowNoticeExecBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 侦听流程通知
 * Created by lzj on 2017/7/26.
 */
@Component("workflowNoticeListener")
public class WorkflowNoticeListener implements MessageHandler{

    private static final Logger logger = LoggerFactory.getLogger(WorkflowNoticeListener.class);
    private static final int EXEC_RESULT_LENGTH = 256;
    
    private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

    @Autowired
    private WorkflowClientDAO workflowClientDAO;

    @Autowired
    private ProcessCompleteNotice processCompleteNotice;

    @Autowired
    private ProcessStartNotice processStartNotice;

    @Autowired
    private TaskCreateNotice taskCreateNotice;
    
    @Autowired
    private ProcessReturnFinishNotice processReturnFinishNotice;

    public void noticeExec(WorkflowNoticeBean workflowNoticeBean){
        String noticeType = workflowNoticeBean.getNoticeType();
        WorkflowNoticeExecBean bean = this.makeWorkflowNoticeExecBean(workflowNoticeBean);
        String beanStr = JsonUtils.getInstance().objectToJson(bean);

        try{
            logger.debug("开始执行通知,bean:{}", beanStr);

            if(NoticeType.PROCESS_START.getCode().equals(noticeType)){
                processStartNotice.processStart(bean.getBusiOrderId(),bean.getProcessInstanceId());
            }else if(NoticeType.TASK_CREATE.getCode().equals(noticeType)){
                taskCreateNotice.taskCreate(bean.getTaskId(),bean.getTaskDefinitionKey(),bean.getBusiOrderId());
            }else if(NoticeType.PROCESS_COMPLETE.getCode().equals(noticeType)){
                processCompleteNotice.processComplete(bean.getBusiOrderId(),bean.getProcessInstanceId());
            }else if(NoticeType.PROCESS_RETURN_FINISH.getCode().equals(noticeType)){
            	processReturnFinishNotice.processReturnFinish(bean.getBusiOrderId(),bean.getProcessInstanceId());
            }

            bean.setExecFlag(NoticeExecFlag.SUCCESS.getCode());

            logger.info("通知执行完成，beanStr:{}",beanStr);

        }catch (Exception e){
            logger.error("通知执行异常,beanStr:{}",beanStr,e);
            bean.setExecFlag(NoticeExecFlag.FAILURE.getCode());
            bean.setErrorCode(WorkflowError.CLIENT_ERROR_20001.getCode());
            bean.setExecResult(Exceptions.getStackTraceAsString(e,EXEC_RESULT_LENGTH));
        }finally {
            workflowClientDAO.insertWorkflowNoticeExec(bean);
        }
    }

    private WorkflowNoticeExecBean makeWorkflowNoticeExecBean(WorkflowNoticeBean workflowNoticeBean){
        WorkflowNoticeExecBean workflowNoticeExecBean = new WorkflowNoticeExecBean();
        workflowNoticeExecBean.setExecId(GidClientUtils.getInstance().getGidValue(GidCodes.WFC_CLI_WORKFLOW_NOTICE_EXEC_SEQ));
        workflowNoticeExecBean.setNoticeId(workflowNoticeBean.getNoticeId());
        workflowNoticeExecBean.setCreateTime(DateUtils.now());
        workflowNoticeExecBean.setNoticeType(workflowNoticeBean.getNoticeType());
        workflowNoticeExecBean.setBusiOrderId(workflowNoticeBean.getBusiOrderId());
        workflowNoticeExecBean.setProcessInstanceId(workflowNoticeBean.getProcessInstanceId());
        workflowNoticeExecBean.setTaskId(workflowNoticeBean.getTaskId());
        workflowNoticeExecBean.setTaskDefinitionKey(workflowNoticeBean.getTaskDefinitionKey());
        return workflowNoticeExecBean;
    }

	/* (non-Javadoc)
	 * @see com.basic.framework.messagequeue.service.MessageHandler#handle(java.lang.String)
	 */
	@Override
	public void handle(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug("handle message:{}", message);
		}
		
		WorkflowNoticeBean bean = JSON_UTILS.jsonToObject(message, WorkflowNoticeBean.class);
		noticeExec(bean);
	}

}
