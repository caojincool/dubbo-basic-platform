package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.WorkOrder;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 流程任务单
 *
 */
public interface WorkOrderService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param workOrderId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long workOrderId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(WorkOrder record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param workOrderId
	 * @return
	 *
	 */
	public WorkOrder qryByPrimaryKey(Long workOrderId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(WorkOrder record);
	
    /**
     * 
     * @date 2017年8月18日 下午5:19:15
     * 
     * @Description: 根据单据id（orderId）查询所有的10I、10G状态下的任务
     * @param orderId
     * @return
     *
     */
    public List<WorkOrder> qryByOrderId(Long orderId);
    
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<WorkOrder> qryWorkOrderList(WorkOrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryWorkOrderListCount(WorkOrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月11日 上午11:42:38
     * 
     * @Description: 根据任务id查询任务
     * @param workOrderId
     * @return
     *
     */
    public WorkOrder qryByWorkOrderId(Long workOrderId);
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询当前操作人员待办任务
     * @param ibean
     * @return
     *
     */
    public List<WorkOrder> qryPartyWorkOrder(WorkOrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询当前操作人员待办任务的总数
     * @param ibean
     * @return
     *
     */
    public long qryPartyWorkOrderCount(WorkOrderIbean ibean);
    /**
     * 
     * @date 2018年1月19日 下午4:21:02
     * @author lhj
     * @Description: 根据流程单据ID个节点编码查询
     * @param workOrder
     *
     */
	public List<WorkOrder> selectWorkOrderByTargetCode(WorkOrder workOrder);
}
