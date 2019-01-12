package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.bean.UserStaffIbean;
import com.basic.oaas.model.UserStaff;

public interface UserStaffMapper {
	
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param userId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long userId);

    /**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */

    public int insertSelective(UserStaff record);

    /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param userId
     * @return
     *
     */
    public UserStaff selectByPrimaryKey(Long userId);

    /**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(UserStaff record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(UserStaff record);
    
    /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<UserStaff> selectUserStaffList(UserStaffIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectUserStaffListCount(UserStaffIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * 
     * @Description: 批量删除
     * @param userIds
     * @return
     *
     */
    public int deleteBatchByUserIds(Long[] userIds);
    /**
     * 
     * @date 2018年2月26日 下午5:31:10
     * @author lhj
     * @Description: 删除
     * @param example
     *
     */
	public void deleteByUser(UserStaff example);


}