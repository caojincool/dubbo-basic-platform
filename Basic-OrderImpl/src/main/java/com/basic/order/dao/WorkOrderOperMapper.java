package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.WorkOrderOper;

public interface WorkOrderOperMapper {
    int deleteByPrimaryKey(Long operId);

    int insertSelective(WorkOrderOper record);

    WorkOrderOper selectByPrimaryKey(Long operId);

    int updateByPrimaryKeySelective(WorkOrderOper record);
    
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<WorkOrderOper> selectWorkOrderOperList(WorkOrderIbean ibean);
    
}