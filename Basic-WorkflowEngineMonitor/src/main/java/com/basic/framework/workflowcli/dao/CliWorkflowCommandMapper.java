package com.basic.framework.workflowcli.dao;

import java.util.List;

import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflowcli.model.CliWorkflowCommand;

public interface CliWorkflowCommandMapper {
    int deleteByPrimaryKey(Long commandId);

    int insert(CliWorkflowCommand record);

    int insertSelective(CliWorkflowCommand record);

    CliWorkflowCommand selectByPrimaryKey(Long commandId);

    int updateByPrimaryKeySelective(CliWorkflowCommand record);

    int updateByPrimaryKey(CliWorkflowCommand record);
    
    /**
     * 
     * @date 2017年8月8日 下午4:34:53
     * 
     * @Description: 查询某个流程实例下的全部操作
     * @param ibean
     * @return
     *
     */
    public List<CliWorkflowCommand> selectCliWorkflowCommandList(WorkflowIbean ibean);
}