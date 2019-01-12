package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.OrderFollowUser;

public interface OrderFollowUserMapper {
    int deleteByPrimaryKey(Long followUserId);

    int insertSelective(OrderFollowUser record);

    OrderFollowUser selectByPrimaryKey(Long followUserId);

    int updateByPrimaryKeySelective(OrderFollowUser record);
    
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<OrderFollowUser> selectOrderFollowUserList(WorkOrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月8日 下午2:25:28
     * 
     * @Description: 批量插入
     * @param list
     * @return
     *
     */
    public int insertBatchOrderFollowUser(List<OrderFollowUser> list);
    
    /**
     * 
     * @date 2017年9月8日 下午2:25:48
     * 
     * @Description: 批量删除
     * @param followUserIds
     * @return
     *
     */
    public int deleteBatchByFollowUserIds(Long[] followUserIds);

}