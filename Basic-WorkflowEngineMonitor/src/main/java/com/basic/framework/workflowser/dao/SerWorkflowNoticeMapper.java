package com.basic.framework.workflowser.dao;

import java.util.List;

import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflowser.model.SerWorkflowNotice;

public interface SerWorkflowNoticeMapper {
    int deleteByPrimaryKey(Long noticeId);

    int insert(SerWorkflowNotice record);

    int insertSelective(SerWorkflowNotice record);

    SerWorkflowNotice selectByPrimaryKey(Long noticeId);

    int updateByPrimaryKeySelective(SerWorkflowNotice record);

    int updateByPrimaryKey(SerWorkflowNotice record);
    
    /**
     * 
     * @date 2017年8月8日 下午4:34:53
     * 
     * @Description: 查询某个流程实例下的全部操作
     * @param ibean
     * @return
     *
     */
    public List<SerWorkflowNotice> selectSerWorkflowNoticeList(WorkflowIbean ibean);
}