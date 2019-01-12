package com.basic.framework.workflowcli.dao;

import java.util.List;

import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflowcli.model.CliWorkflowNoticeExec;

public interface CliWorkflowNoticeExecMapper {
    int deleteByPrimaryKey(Long execId);

    int insert(CliWorkflowNoticeExec record);

    int insertSelective(CliWorkflowNoticeExec record);

    CliWorkflowNoticeExec selectByPrimaryKey(Long execId);

    int updateByPrimaryKeySelective(CliWorkflowNoticeExec record);

    int updateByPrimaryKey(CliWorkflowNoticeExec record);
    
    /**
     * 
     * @date 2017年8月8日 下午4:34:53
     * 
     * @Description: 查询某个流程实例下的全部操作
     * @param ibean
     * @return
     *
     */
    public List<CliWorkflowNoticeExec> selectCliWorkflowNoticeExecList(WorkflowIbean ibean);
    
}