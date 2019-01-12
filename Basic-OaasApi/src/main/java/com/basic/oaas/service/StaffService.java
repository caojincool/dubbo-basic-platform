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
import java.util.Map;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.StaffIbean;
import com.basic.oaas.model.Staff;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 员工
 * 
 */
public interface StaffService extends BaseServer<Long, Staff> {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param staffId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long staffId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(Staff record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param staffId
	 * @return
	 *
	 */
	public Staff qryByPrimaryKey(Long staffId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(Staff record);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<Staff> qryStaffList(StaffIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:05:44
     * @author Kevin
     * @Description: 根据参数查询总数
     * @param ibean
     * @return
     *
     */
    public long qryStaffListCount(StaffIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(Staff ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param staffIds
     * @return
     *
     */
    public int removeBatchByStaffIds(Long[] staffIds);
    
    /**
     * 
     * @date 2017年8月16日 下午6:04:10
     * @author Kevin
     * @Description: 检查是否存在区域编码
     * @param jobCode
     * @return
     *
     */
    public int qryExistStaffNumber(String staffNumber);	
    
    /**
     * 
     * @date 2017年8月25日 下午4:29:39
     * @author Kevin
     * @Description: 根据用户ID获取员工信息
     * @param userId
     * @return
     *
     */
    public Staff qryStaffByUserId(Long userId);

    /**
     * 获取分页数据
     * @param queryFilter
     * @return
     */
	public PageJson queryPage(DefaultQueryFilter queryFilter);
   
	/**
     * 
     * @Description: 查询员工帐号信息，用于外围系统对接
     * @param userId
     * @return
     *
     */
	List<Staff> queryAccountByOrgIds(Map<String, Object> params);
	
	/**
	 * 物流系统分页查询操作员角色信息
	 * @param sqlKey
	 * @param queryFilter
	 * @return
	 */
	public PageJson queryUserByOrgIds(String sqlKey,QueryFilter queryFilter);
}
