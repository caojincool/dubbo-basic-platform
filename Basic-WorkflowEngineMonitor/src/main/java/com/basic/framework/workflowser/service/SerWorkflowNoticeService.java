package com.basic.framework.workflowser.service;

import java.util.List;

import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflowser.model.SerWorkflowNotice;

public interface SerWorkflowNoticeService {

    /**
     * 
     * @date 2017年8月8日 下午4:34:53
     * 
     * @Description: 查询某个流程实例下的全部操作
     * @param ibean
     * @return
     *
     */
    public List<SerWorkflowNotice> qrySerWorkflowNoticeList(WorkflowIbean ibean);
    
    /**
     * 
     * @date 2017年8月9日 下午4:37:18
     * 
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int createSelective(SerWorkflowNotice record);
}
