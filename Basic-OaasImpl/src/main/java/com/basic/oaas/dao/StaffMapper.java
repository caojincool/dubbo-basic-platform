package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.StaffIbean;
import com.basic.oaas.model.Staff;

/**
 * 
 *
 * @date 2017年8月18日 下午4:19:59
 * @author Kevin
 * @Description: 员工表
 *
 */
public interface StaffMapper {
	
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param staffId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long staffId);

	/**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
	public int insertSelective(Staff record);

	/**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param staffId
     * @return
     *
     */
	public Staff selectByPrimaryKey(Long staffId);

	/**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
	public int updateByPrimaryKeySelective(Staff record);
	
	/**
	 * 
	 * @Description:更新所有字段
	 * @author lengzj
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(Staff record);
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<Staff> selectStaffList(StaffIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectStaffListCount(StaffIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 批量修改状态
     * @param staffIds
     * @return
     *
     */
    public int updateBatchByStaffIds(Long[] staffIds);
    
    /**
     * 
     * @date 2017年8月21日 下午12:02:45
     * @author Kevin
     * @Description: 查询是否存在相同的员工号编码
     * @param staffNumber
     * @return
     *
     */
    public int selectExistStaffNumber(String staffNumber);
    
    /**
     * 
     * @date 2017年8月25日 下午4:23:04
     * @author Kevin
     * @Description: 根据用户Id获取员工信息
     * @param userId
     * @return
     *
     */
    public Staff selectStaffByUserId(Long userId);
    
    /**
     * 
     * @Description: 自行组装sql查询
     * @param userId
     * @return
     *
     */
    public List<Staff> query(DefaultQueryFilter queryFilter);
    /**
     * 
     * @Description: 查询员工帐号信息，用于外围系统对接
     * @param userId
     * @return
     *
     */
	public List<Staff> queryAccountByOrgIds(Map<String,Object> params);
	
	/**
	 * 根据员工信息获取
	 * @Description:
	 * @author lengzj
	 * @param staff
	 * @return
	 */
	public Staff checkByStaff(Staff staff);
	
	/**
	 * 物流系统查询操作员角色信息
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryUserByOrgIds(Map<String,Object> params);
	
	/**
	 * 
	 * @Description:根据参数统计
	 * @author lengzj
	 * @param params
	 * @return
	 */
	public int countStaff(Map<String,Object> params);
}