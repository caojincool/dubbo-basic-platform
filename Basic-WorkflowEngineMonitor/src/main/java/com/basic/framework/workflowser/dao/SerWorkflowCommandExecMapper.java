package com.basic.framework.workflowser.dao;

import java.util.List;

import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflowser.model.SerWorkflowCommandExec;

public interface SerWorkflowCommandExecMapper {
    int deleteByPrimaryKey(Long execId);

    int insert(SerWorkflowCommandExec record);

    int insertSelective(SerWorkflowCommandExec record);

    SerWorkflowCommandExec selectByPrimaryKey(Long execId);

    int updateByPrimaryKeySelective(SerWorkflowCommandExec record);

    int updateByPrimaryKey(SerWorkflowCommandExec record);
    
    /**
     * 
     * @date 2017年8月8日 下午4:34:53
     * 
     * @Description: 查询某个流程实例下的全部操作
     * @param ibean
     * @return
     *
     */
    public List<SerWorkflowCommandExec> selectSerWorkflowCommandExecList(WorkflowIbean ibean);
}