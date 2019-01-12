package com.basic.framework.workflow.engine;

import com.basic.framework.common.utils.Exceptions;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.messagequeue.impl.MessageSenderActiveMQ;
import com.basic.framework.workflow.dao.WorkflowEngineDAO;
import com.basic.framework.workflow.define.NoticeQueueCode;
import com.basic.framework.workflow.define.NoticeSendFlag;
import com.basic.framework.workflow.define.WorkflowError;
import com.basic.framework.workflow.model.WorkflowNoticeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lzj on 2017/7/24.
 */
@Component("workflowNoticeSender")
public class WorkflowNoticeSender {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowNoticeSender.class);
    private static final int SEND_RESULT_LENGTH = 256;

    private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
    
    @Autowired
    private WorkflowEngineDAO workflowEngineDAO;

	@Autowired
	private MessageSenderActiveMQ messageSenderActiveMQ;
	
    /**
     * 发送通知
     * @param workflowNoticeBean
     */
    public void sendNotice(WorkflowNoticeBean workflowNoticeBean){

        try{
            //将消息发送到队列
            workflowNoticeBean.setSendFlag(NoticeSendFlag.SUCCESS.getCode());
            
            String key = workflowNoticeBean.getBusiOrderId().toString();
            String message = JSON_UTILS.objectToJson(workflowNoticeBean);
            messageSenderActiveMQ.sendMessage(key, NoticeQueueCode.WORKFLOW_NOTICE_SENDNOTICE_QUEUE.getCode(), message);
            
        }catch (Exception e){
            String str = JsonUtils.getInstance().objectToJson(workflowNoticeBean);
            logger.error("通知发送异常,workflowNoticeBean:{}",str,e);
            workflowNoticeBean.setSendFlag(NoticeSendFlag.FAILURE.getCode());
            workflowNoticeBean.setErrorCode(WorkflowError.SEND_NOTICE_ERROR_12001.getCode());
            workflowNoticeBean.setSendResult(Exceptions.getStackTraceAsString(e,SEND_RESULT_LENGTH));
        }finally {
            workflowEngineDAO.insertWorkflowNotice(workflowNoticeBean);
        }

    }


}
