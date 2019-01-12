/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 部门接口
 * 
 */
package com.basic.oaas.service;

import java.util.List;
import java.util.Map;

import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.OrgIbean;
import com.basic.oaas.model.Org;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 部门
 * 
 */
public interface OrgService extends BaseServer<Long, Org>{
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:11
	 * @author Kevin
	 * @Description: 新增
	 * @param org
	 * @return
	 *
	 */
	public int createSelective(Org org);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:31
	 * @author Kevin
	 * @Description: 主键查询
	 * @param orgId
	 * @return
	 *
	 */
	public Org qryOrgById(Long orgId);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:51
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param org
	 * @return
	 *
	 */
	public int modifyByPrimaryKey(Org org);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:07
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param orgId
	 * @return
	 *
	 */
	public int removeByPrimarykey(Long orgId);
	
	/**
	 * 
	 * @date 2017年8月7日 上午9:48:53
	 * @author Kevin
	 * @Description: 创建获取修改
	 * @param iBean
	 * @return
	 *
	 */
	public Org createOrModify(Org org);
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:49
	 * @author Kevin
	 * @Description: 获取当前用户的部门
	 * @param userId
	 * @return
	 *
	 */
    public List<Org> qryByOrg(OrgIbean ibean);
    
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
     * @date 2017年7月7日 下午5:20:12
     * @author Kevin
     * @Description: 获取当前部门下的子部门
     * @param orgId
     * @return
     *
     */
    public List<Org> qrySubOrgById(Long orgId);
    
    /**
     * 
     * @date 2017年7月7日 下午5:20:45
     * @author Kevin
     * @Description: 获取当前部门下的所有子部门
     * @param org
     * @return
     *
     */
    public List<Org> qryAllSubOrgById(Org org);
	
    /**
     * 
     * @date 2017年8月11日 下午5:58:13
     * @author Kevin
     * @Description: 查询部门编码是否存在
     * @param orgCode
     * @return
     *
     */
    public int qryExistOrgCode(String orgCode);
    
    /**
     * 
     * @date 2017年8月25日 下午4:51:31
     * @author Kevin
     * @Description: 根据用户ID获取默认职位所在部门信息
     * @param userId
     * @return
     *
     */
    public Org qryOrgByUserId(Long userId);
    
    /**
     * 
     * @date 2017年11月4日 下午5:12:57
     * @author lhj
     * @Description: 按父部门查询
     * @param parentOrgId
     * @return
     *
     */
	public List<Org> qryParentOrgId(Long parentOrgId);

	public List<Org> selectOrgByOrg(Org org);

	/**
     * 
     * @Description: 自行组装sql查询
     * @return
     *
     */
    @Override
    public List<Org> query(DefaultQueryFilter queryFilter);
    
    /**
     * find in set
     * @param params
     * @return
     */
    public List<Org> selectFindInSet(String value,String column);
	
}
