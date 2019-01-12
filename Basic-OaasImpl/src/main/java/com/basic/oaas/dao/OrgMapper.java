package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.OrgIbean;
import com.basic.oaas.model.Org;

/**
 * 
 *
 * @date 2017年8月14日 上午10:20:51
 * @author Kevin
 * @Description: 部门表
 *
 */
public interface OrgMapper {
	
	/**
	 * 
	 * @date 2017年7月5日 上午10:07:20
	 * @author Kevin
	 * @Description: 删除
	 * @param orgId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long orgId);

    /**
     * 
     * @date 2017年7月5日 上午10:08:19
     * @author Kevin
     * @Description: 增加
     * @param record
     * @return
     *
     */
    public int insertSelective(Org record);

    /**
     * 
     * @date 2017年7月5日 上午10:09:03
     * @author Kevin
     * @Description: 根据主键查询
     * @param orgId
     * @return
     *
     */
    public Org selectByPrimaryKey(Long orgId);

    /**
     * 
     * @date 2017年7月5日 上午10:09:47
     * @author Kevin
     * @Description: 更新
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(Org record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(Org record);
    
    /**
     * 
     * @date 2017年8月7日 上午9:13:16
     * @author Kevin
     * @Description: 根据部门条件获取部门列表
     * @param ibean
     * @return
     *
     */
 	public List<Org> selectByOrg(OrgIbean ibean);
 	

 	
 	/**
     * 
     * @date 2017年7月7日 下午4:51:29
     * @author Kevin
     * @Description: 获取当前部门下面的子部门
     * @param orgId
     * @return
     *
     */
	public List<Org> selectSubOrgById(Long orgId);
	
	/**
	 * 
	 * @date 2017年11月20日22:16:57
	 * @author liu.decheng
	 * @Description: 获取当前部门下面的所有子部门
	 * @param orgId
	 * @return
	 *
	 */
	public List<Org> selectSubOrgByPathId(Long orgId);
	
	
	/**
	 * 
	 * @date 2017年8月11日 下午5:52:25
	 * @author Kevin
	 * @Description: 查询数据库是否存在部门相同的部门编码
	 * @param orgCode
	 * @return
	 *
	 */
	public int selectExistOrgCode(String orgCode);
	
	/**
	 * 
	 * @date 2017年8月14日 上午9:21:50
	 * @author Kevin
	 * @Description: 根据Id修改所有当前以及子状态
	 * @param orgId
	 * @return
	 *
	 */
	public int updateAllStateById(Long orgId);
	
	/**
	 * 
	 * @date 2017年8月25日 下午4:35:58
	 * @author Kevin
	 * @Description: 根据用户ID获取默认职位所在部门信息
	 * @return
	 *
	 */
	public Org selectOrgByUserId(Long userId);
	

	public List<Org> selectByParentOrgId(Long parentOrgId);
	/**
	 * 
	 * @date 2018年2月26日 下午7:16:48
	 * @author lhj
	 * @Description: 根据部门查询
	 * @param example
	 * @return
	 *
	 */
	public List<Org> selectOrgByOrg(Org example);
	/**
	 * 
	 * @date 2018年2月27日 上午9:29:33
	 * @author lhj
	 * @Description: 根据部门编码查询员工数量
	 * @param orgCode
	 * @return
	 *
	 */
	public int qryOrgStaffCount(String orgCode);
	/**
	 * 
	 * @date 2018年3月14日 上午10:50:28
	 * @author lhj
	 * @Description: 根据组织名称查询
	 * @param orgName
	 * @return
	 *
	 */
	public Org selectOrgByOrgCode(String orgCode);
	
	/**
     * 
     * @Description: 自行组装sql查询
     * @return
     *
     */
    public List<Org> query(DefaultQueryFilter queryFilter);
    
    /**
     * 
     * @Description: find in set
     * @return
     *
     */
    public List<Org> selectFindInSet(Map<String,Object> params);
    
    /**
     * 根据编码来检查，不管是否删除状态，主要用于数据从EIP同步的时候
     * @param orgCode
     * @return
     */
    public Org checkByOrgCode(String orgCode);
    
    /**
	 * 
	 * @Description:根据参数统计部门数
	 * @author lengzj
	 * @param params
	 * @return
	 */
	public int countOrg(Map<String,Object> params);

}