package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.bean.StaffJobIbean;
import com.basic.oaas.model.StaffJob;

/**
 * 
 *
 * @date 2017年8月21日 下午2:30:50
 * @author Kevin
 * @Description: 员工职位表
 *
 */
public interface StaffJobMapper {
	
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param staffId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long staffJobId);

    /**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(StaffJob record);

    /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param staffId
     * @return
     *
     */
    public StaffJob selectByPrimaryKey(Long staffJobId);

    /**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(StaffJob record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(StaffJob record);
    
    /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<StaffJob> selectStaffJobList(StaffJobIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectStaffJobListCount(StaffJobIbean ibean);
    
    /**
     * 
     * @date 2017年8月23日 下午2:40:29
     * @author Kevin
     * @Description: 删除员工默认职位
     * @return
     *
     */
    public int deleteByStaffDefaultJob(Long staffId);
    
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 批量删除员工职位
     * @param staffJobIds
     * @return
     *
     */
    public int deleteBatchByStaffJobIds(Long[] staffJobIds);
    
    /**
     * 
     * @date 2017年8月24日 上午9:11:36
     * @author Kevin
     * @Description: 查询判读该职位是否存在
     * @param ibean
     * @return
     *
     */
    public int selectExistStaffJob(StaffJobIbean ibean);
    
    /**
     * 根据员工ID删除所有岗位关联
     * @param staffId
     * @return
     */
    public int deleteByStaff(Long staffId);

}