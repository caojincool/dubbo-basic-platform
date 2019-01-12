package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.WorkOrder;

public interface WorkOrderMapper {
    int deleteByPrimaryKey(Long workOrderId);

    int insertSelective(WorkOrder record);

    WorkOrder selectByPrimaryKey(Long workOrderId);

    int updateByPrimaryKeySelective(WorkOrder record);
    
    /**
     * 
     * @date 2017年8月18日 下午5:19:15
     * 
     * @Description: 根据单据id（orderId）查询所有的10I、10G状态下的任务
     * @param orderId
     * @return
     *
     */
    public List<WorkOrder> selectByOrderId(Long orderId);
    
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<WorkOrder> selectWorkOrderList(WorkOrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectWorkOrderListCount(WorkOrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月11日 上午11:42:38
     * 
     * @Description: 根据任务id查询任务
     * @param workOrderId
     * @return
     *
     */
    public WorkOrder selectByWorkOrderId(Long workOrderId);
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询当前操作人员待办任务
     * @param ibean
     * @return
     *
     */
    public List<WorkOrder> selectPartyWorkOrder(WorkOrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询当前操作人员待办任务的总数
     * @param ibean
     * @return
     *
     */
    public long selectPartyWorkOrderCount(WorkOrderIbean ibean);
    /**
     * 
     * @date 2018年1月19日 下午4:22:52
     * @author lhj
     * @Description: 根据流程单据ID个节点编码查询
     * @param workOrder
     * @return
     *
     */
    List<WorkOrder> selectWorkOrderByTargetCode(WorkOrder workOrder);
    /**
     * 
     * @date 2018年2月26日 下午2:49:57
     * @author lhj
     * @Description: 根据用户Id查询是否有未完成流程单据
     * @param string
     * @param userId
     * @return
     *
     */
	List<WorkOrder> selectOfWorkOrder(WorkOrderIbean workOrderIbean);

    
}