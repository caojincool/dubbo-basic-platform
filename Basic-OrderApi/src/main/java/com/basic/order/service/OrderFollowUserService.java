package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.OrderFollowUser;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 流程关注人
 *
 */
public interface OrderFollowUserService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param followUserId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long followUserId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(OrderFollowUser record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param followUserId
	 * @return
	 *
	 */
	public OrderFollowUser qryByPrimaryKey(Long followUserId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(OrderFollowUser record);
	
    
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<OrderFollowUser> qryOrderFollowUserList(WorkOrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月8日 下午2:25:28
     * 
     * @Description: 批量插入
     * @param list
     * @return
     *
     */
    public int createBatchOrderFollowUser(List<OrderFollowUser> list);
    
    /**
     * 
     * @date 2017年9月8日 下午2:25:48
     * 
     * @Description: 批量删除
     * @param followUserIds
     * @return
     *
     */
    public int removeBatchByFollowUserIds(Long[] followUserIds);
}
