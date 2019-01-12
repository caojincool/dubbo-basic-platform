/**
 * companyName
 * copyright 2015-2020
 *
 * @date 2017年1月3日 下午3:36:40
 * @author lzj
 * @Description: 流程引擎事件侦听
 */
package com.basic.framework.workflow.engine;

import java.util.Map;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.workflow.define.GidCodes;
import com.basic.framework.workflow.define.NoticeType;
import com.basic.framework.workflow.model.WorkflowNoticeBean;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.datatype.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @date 2017年1月3日 下午3:36:40
 * @author lzj
 * @Description: 流程事件侦听
 *
 */
@Service("workflowEventListener")
public class WorkflowEventListener implements ActivitiEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowEventListener.class);

    @Autowired
    private WorkflowNoticeSender workflowNoticeSender;

    static {
        logger.error("WorkflowEventListener init....................");
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.engine.delegate.event.ActivitiEventListener#
     * isFailOnException()
     */
    @Override
    public boolean isFailOnException() {
        // The logic in the onEvent method of this listener is not critical,
        // exceptions
        // can be ignored if logging fails...
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.
     * activiti.engine.delegate.event.ActivitiEvent)
     */
    @Override
    public void onEvent(ActivitiEvent event) {
        logger.debug("onEvent:{}", event.getType());

        switch (event.getType()) {
            case TASK_CREATED:// 任务创建
                logger.debug("TASK_CREATED");
                this.taskCreate(event);
                break;
            case JOB_EXECUTION_SUCCESS:
                logger.debug("JOB_EXECUTION_SUCCESS");
                break;

            case JOB_EXECUTION_FAILURE:
                logger.debug("JOB_EXECUTION_FAILURE");
                break;

            case PROCESS_COMPLETED:// 流程结束
                logger.debug("PROCESS_COMPLETED");
                this.processComplete(event);
                break;
            default:
                logger.error("default");
        }
    }

    /**
     *
     * @date 2017年1月12日 下午4:56:31
     * @author lzj
     * @Description: 任务创建
     * @param event
     *
     */
    private void taskCreate(ActivitiEvent event) {
        ActivitiEntityEventImpl eventImpl = (ActivitiEntityEventImpl) event;
        TaskEntity taskEntity = (TaskEntity) eventImpl.getEntity();
        String processInstanceId = taskEntity.getProcessInstanceId();
        String taskId = taskEntity.getId();
        String taskDefinitionKey = taskEntity.getTaskDefinitionKey();
        Map<String, Object> map = taskEntity.getActivityInstanceVariables();
        Long busiOrderId = LongUtils.valueOf(map.get("BUSI_ORDER_ID"));

        logger.debug("环节任务生成 taskCreate,processInstanceId:{},taskId:{},taskDefinitionKey:{},busiOrderId:{}", processInstanceId, taskId,taskDefinitionKey,busiOrderId);


        WorkflowNoticeBean bean = new WorkflowNoticeBean();
        bean.setNoticeId(GidClientUtils.getInstance().getGidValue(GidCodes.WFE_SER_WORKFLOW_NOTICE_SEQ));
        bean.setCreateTime(DateUtils.now());
        bean.setProcessInstanceId(processInstanceId);
        bean.setTaskDefinitionKey(taskDefinitionKey);
        bean.setTaskId(taskId);
        bean.setNoticeType(NoticeType.TASK_CREATE.getCode());
        bean.setBusiOrderId(busiOrderId);
        workflowNoticeSender.sendNotice(bean);

        logger.info("任务生成通知发送：bean:{}", JsonUtils.getInstance().objectToJson(bean));
        return;

    }

    /**
     * 流程结束
     *
     * @date 2017年1月12日 下午4:57:25
     * @author lzj
     * @Description: 流程结束
     * @param event
     *
     */
    private void processComplete(ActivitiEvent event) {
        ActivitiEntityEventImpl eventImpl = (ActivitiEntityEventImpl) event;
        String processInstanceId = eventImpl.getProcessInstanceId();
        ExecutionEntity entity = (ExecutionEntity)eventImpl.getEntity();

        Map<String, Object> map = entity.getVariables();
        Long busiOrderId = LongUtils.valueOf(map.get("BUSI_ORDER_ID"));
        logger.debug("流程实例完成 processComplete,processInstanceId:{},busiOrderId:{}", processInstanceId, busiOrderId);

        WorkflowNoticeBean bean = new WorkflowNoticeBean();
        bean.setNoticeId(GidClientUtils.getInstance().getGidValue(GidCodes.WFE_SER_WORKFLOW_NOTICE_SEQ));
        bean.setCreateTime(DateUtils.now());
        bean.setProcessInstanceId(processInstanceId);
        bean.setBusiOrderId(busiOrderId);
        bean.setNoticeType(NoticeType.PROCESS_COMPLETE.getCode());

        workflowNoticeSender.sendNotice(bean);
        logger.info("流程实例完成通知发送, bean:{}", JsonUtils.getInstance().objectToJson(bean));

    }
}
