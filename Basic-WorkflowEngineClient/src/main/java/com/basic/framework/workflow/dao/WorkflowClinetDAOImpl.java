package com.basic.framework.workflow.dao;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.workflow.model.WorkflowCommandBean;
import com.basic.framework.workflow.model.WorkflowNoticeExecBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lzj on 2017/7/24.
 */
@Repository("workflowClinetDAO")
public class WorkflowClinetDAOImpl implements WorkflowClientDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_WORKFLOW_COMMAND_SQL = "INSERT INTO WFC_CLI_WORKFLOW_COMMAND (COMMAND_ID,SERIAL_NO,PROCESS_DEFINE_KEY,BUSI_ORDER_ID,TASK_ID,CREATE_TIME,PARAM,SEND_FLAG,SEND_RESULT,ERROR_CODE,COMMAND_TYPE,PROCESS_INSTANCE_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_WORKFLOW_NOTICE_EXEC_SQL = "INSERT INTO WFC_CLI_WORKFLOW_NOTICE_EXEC (EXEC_ID,NOTICE_ID,CREATE_TIME,NOTICE_TYPE,BUSI_ORDER_ID,PROCESS_INSTANCE_ID,TASK_ID,TASK_DEFINITION_KEY,ERROR_CODE,EXEC_FLAG,EXEC_RESULT) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * 写入库表 WFC_WORKFLOW_COMMAND
     * @param workflowCommandBean
     */
    @Override
    public void insertWorkflowCommand(final WorkflowCommandBean workflowCommandBean) {

        final JsonUtils jsonUtils = JsonUtils.getInstance();
        jdbcTemplate.update(INSERT_WORKFLOW_COMMAND_SQL,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int index = 1;
                if(null != workflowCommandBean.getCommandId()){
                    ps.setLong(index++, workflowCommandBean.getCommandId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                if(null != workflowCommandBean.getSerialNo()){
                    ps.setLong(index++, workflowCommandBean.getSerialNo());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                ps.setString(index++, workflowCommandBean.getProcessDefineKey());

                if(null != workflowCommandBean.getBusiOrderId()){
                    ps.setLong(index++, workflowCommandBean.getBusiOrderId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                ps.setString(index++, workflowCommandBean.getTaskId());

                if(null != workflowCommandBean.getCreateTime()){
                    ps.setTimestamp(index++, DateUtils.dateToTimeStamp(workflowCommandBean.getCreateTime()));
                }else{
                    ps.setNull(index++,java.sql.Types.DATE);
                }

                ps.setString(index++, jsonUtils.objectToJson(workflowCommandBean.getParamMap()));
                ps.setString(index++, workflowCommandBean.getSendFlag());
                ps.setString(index++, workflowCommandBean.getSendResult());
                ps.setString(index++, workflowCommandBean.getErrorCode());
                ps.setString(index++, workflowCommandBean.getCommandType());
                ps.setString(index++, workflowCommandBean.getProcessInstanceId());



            }
        });
     }

    @Override
    public void insertWorkflowNoticeExec(final WorkflowNoticeExecBean workflowNoticeExecBean) {
        jdbcTemplate.update(INSERT_WORKFLOW_NOTICE_EXEC_SQL,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int index = 1;

                if(null != workflowNoticeExecBean.getExecId()){
                    ps.setLong(index++, workflowNoticeExecBean.getExecId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                if(null != workflowNoticeExecBean.getNoticeId()){
                    ps.setLong(index++, workflowNoticeExecBean.getNoticeId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                if(null != workflowNoticeExecBean.getCreateTime()){
                    ps.setTimestamp(index++, DateUtils.dateToTimeStamp(workflowNoticeExecBean.getCreateTime()));
                }else{
                    ps.setNull(index++,java.sql.Types.DATE);
                }

                ps.setString(index++, workflowNoticeExecBean.getNoticeType());

                if(null != workflowNoticeExecBean.getBusiOrderId()){
                    ps.setLong(index++, workflowNoticeExecBean.getBusiOrderId());
                }else{
                    ps.setNull(index++,java.sql.Types.NUMERIC);
                }

                ps.setString(index++, workflowNoticeExecBean.getProcessInstanceId());
                ps.setString(index++, workflowNoticeExecBean.getTaskId());
                ps.setString(index++, workflowNoticeExecBean.getTaskDefinitionKey());

                ps.setString(index++, workflowNoticeExecBean.getErrorCode());
                ps.setString(index++, workflowNoticeExecBean.getExecFlag());
                ps.setString(index++, workflowNoticeExecBean.getExecResult());

            }
        });
    }
}
