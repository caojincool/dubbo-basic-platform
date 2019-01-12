package com.basic.framework.stateengine.statemachine.dao;

import com.basic.framework.common.utils.datatype.LongUtils;
import com.basic.framework.stateengine.statemachine.model.StateMachineStateBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lzj on 2017/8/1.
 */
@Repository("stateMachineDAO")
public class StateMachineDAOImpl implements StateMachineDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_SE_STATE_MACHINE_STATE_BY_MODEL_AND_STATE_SQL = "SELECT STATE_ID,MODEL_ID,MODEL_CODE,STATE_CODE,STATE_NAME,TARGET_STATE,BUSI_CODE,CONDITION_CODE FROM SE_STATE_MACHINE_STATE SSMS WHERE SSMS.MODEL_CODE=? AND SSMS.STATE_CODE=? AND (SSMS.CONDITION_CODE=? OR SSMS.CONDITION_CODE IS NULL)";

    @Override
    public StateMachineStateBean qryStateByModelAndState(final String modelCode,final String stateCode,final String conditionCode) {

        List<StateMachineStateBean> list = jdbcTemplate.query(SELECT_SE_STATE_MACHINE_STATE_BY_MODEL_AND_STATE_SQL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int index = 1;
                ps.setString(index++, modelCode);
                ps.setString(index++, stateCode);
                ps.setString(index++, conditionCode);
            }
        }, new RowMapper<StateMachineStateBean>() {
            @Override
            public StateMachineStateBean mapRow(ResultSet rs, int paramInt) throws SQLException {
                StateMachineStateBean bean = new StateMachineStateBean();
                bean.setStateId(LongUtils.valueOf(rs.getObject("STATE_ID")));
                bean.setModelId(LongUtils.valueOf(rs.getObject("MODEL_ID")));
                bean.setModelCode(rs.getString("MODEL_CODE"));
                bean.setStateCode(rs.getString("STATE_CODE"));
                bean.setStateName(rs.getString("STATE_NAME"));
                bean.setTargetState(rs.getString("TARGET_STATE"));
                bean.setBusiCode(rs.getString("BUSI_CODE"));
                bean.setConditionCode(rs.getString("CONDITION_CODE"));
                return bean;
            }
        });

        if(list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
   }

    @Override
    public List<StateMachineStateBean> qryStateByModel(String modelCode) {
        return null;
    }

    @Override
    public List<StateMachineStateBean> qryAllState() {
        return null;
    }
}
