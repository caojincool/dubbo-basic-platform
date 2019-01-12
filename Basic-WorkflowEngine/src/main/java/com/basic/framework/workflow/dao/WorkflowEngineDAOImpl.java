package com.basic.framework.workflow.dao;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.workflow.model.WorkflowCommandExecBean;
import com.basic.framework.workflow.model.WorkflowNoticeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lzj on 2017/7/25.
 */
@Repository("workflowEngineDAO")
public class WorkflowEngineDAOImpl implements WorkflowEngineDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_WORKFLOW_COMMAND_EXEC_SQL = "INSERT INTO WFE_SER_WORKFLOW_COMMAND_EXEC (EXEC_ID,COMMAND_ID,SERIAL_NO,PROCESS_DEFINE_KEY,BUSI_ORDER_ID,TASK_ID,CREATE_TIME,PARAM,ERROR_CODE,COMMAND_TYPE,EXEC_FLAG,EXEC_RESULT,PROCESS_INSTANCE_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_WORKFLOW_NOTICE_SQL = "INSERT INTO WFE_SER_WORKFLOW_NOTICE (NOTICE_ID,CREATE_TIME,NOTICE_TYPE,BUSI_ORDER_ID,PROCESS_INSTANCE_ID,TASK_ID,TASK_DEFINITION_KEY,ERROR_CODE,SEND_FLAG,SEND_RESULT) VALUES (?,?,?,?,?,?,?,?,?,?)";

    @Override
    public void insertWorkflowCommandExec(final WorkflowCommandExecBean workflowCommandExecBean) {
        final JsonUtils jsonUtils = JsonUtils.getInstance();

        jdbcTemplate.update(INSERT_WORKFLOW_COMMAND_EXEC_SQL,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int index = 1;
                if(null != workflowCommandExecBean.getExecId()){
                    ps.setLong(index++, workflowCommandExecBean.getExecId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                if(null != workflowCommandExecBean.getCommandId()){
                    ps.setLong(index++, workflowCommandExecBean.getCommandId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                if(null != workflowCommandExecBean.getSerialNo()){
                    ps.setLong(index++, workflowCommandExecBean.getSerialNo());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                ps.setString(index++, workflowCommandExecBean.getProcessDefineKey());

                if(null != workflowCommandExecBean.getBusiOrderId()){
                    ps.setLong(index++, workflowCommandExecBean.getBusiOrderId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                ps.setString(index++, workflowCommandExecBean.getTaskId());

                if(null != workflowCommandExecBean.getCreateTime()){
                    ps.setTimestamp(index++, DateUtils.dateToTimeStamp(workflowCommandExecBean.getCreateTime()));
                }else{
                    ps.setNull(index++,java.sql.Types.DATE);
                }

                ps.setString(index++, jsonUtils.objectToJson(workflowCommandExecBean.getParamMap()));

                ps.setString(index++, workflowCommandExecBean.getErrorCode());
                ps.setString(index++, workflowCommandExecBean.getCommandType());
                ps.setString(index++, workflowCommandExecBean.getExecFlag());
                ps.setString(index++, workflowCommandExecBean.getExecResult());
                ps.setString(index++, workflowCommandExecBean.getProcessInstanceId());

            }
        });
    }

    @Override
    public void insertWorkflowNotice(final WorkflowNoticeBean workflowNoticeBean) {

        jdbcTemplate.update(INSERT_WORKFLOW_NOTICE_SQL,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int index = 1;

                if(null != workflowNoticeBean.getNoticeId()){
                    ps.setLong(index++, workflowNoticeBean.getNoticeId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                if(null != workflowNoticeBean.getCreateTime()){
                    ps.setTimestamp(index++, DateUtils.dateToTimeStamp(workflowNoticeBean.getCreateTime()));
                }else{
                    ps.setNull(index++,java.sql.Types.DATE);
                }

                ps.setString(index++, workflowNoticeBean.getNoticeType());

                if(null != workflowNoticeBean.getBusiOrderId()){
                    ps.setLong(index++, workflowNoticeBean.getBusiOrderId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                ps.setString(index++, workflowNoticeBean.getProcessInstanceId());
                ps.setString(index++, workflowNoticeBean.getTaskId());
                ps.setString(index++, workflowNoticeBean.getTaskDefinitionKey());

                ps.setString(index++, workflowNoticeBean.getErrorCode());
                ps.setString(index++, workflowNoticeBean.getSendFlag());
                ps.setString(index++, workflowNoticeBean.getSendResult());

            }
        });
    }


}
