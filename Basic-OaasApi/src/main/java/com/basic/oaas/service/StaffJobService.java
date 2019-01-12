/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 员工接口
 * 
 */
package com.basic.oaas.service;



import java.util.List;

import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.oaas.bean.StaffJobIbean;
import com.basic.oaas.model.StaffJob;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 员工职位表
 * 
 */
public interface StaffJobService extends BaseServer<Long, StaffJob> {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param staffJobId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long staffJobId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(StaffJob record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param staffJobId
	 * @return
	 *
	 */
	public StaffJob qryByPrimaryKey(Long staffJobId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(StaffJob record);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<StaffJob> qryStaffJobList(StaffJobIbean ibean);
    
    /**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public  long qryStaffJobListCount(StaffJobIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(StaffJob ibean);
    
    /**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 删除员工默认职位
	 * @param staffJobId
	 * @return
	 *
	 */
    public int removeByStaffDefaultJob(Long staffId);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param staffJobIds
     * @return
     *
     */
    public int removeBatchByStaffJobIds(Long[] staffJobIds);
    
    /**
     * 
     * @date 2017年8月24日 上午9:11:36
     * @author Kevin
     * @Description: 查询判读该职位是否存在
     * @param ibean
     * @return
     *
     */
    public int qryExistStaffJob(StaffJobIbean ibean);
    
    /**
     * 根据员工ID删除所有岗位关联
     * @param staffId
     * @return
     */
    public int deleteByStaff(Long staffId);
    
}
