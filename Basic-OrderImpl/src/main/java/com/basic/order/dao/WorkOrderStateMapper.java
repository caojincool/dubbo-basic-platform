package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.WorkOrderStateIbean;
import com.basic.order.model.WorkOrderState;

public interface WorkOrderStateMapper {
    int deleteByPrimaryKey(String workOrderState);

    int insertSelective(WorkOrderState record);

    WorkOrderState selectByPrimaryKey(String workOrderState);

    int updateByPrimaryKeySelective(WorkOrderState record);
    
    /**
     * 
     * @date 2017年8月30日 下午4:09:59
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<WorkOrderState> selectWorkOrderStateList(WorkOrderStateIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:10:08
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectWorkOrderStateListCount(WorkOrderStateIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:18:13
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int updateBatchStateByWorkOrderStates(WorkOrderState	 ibean);
}