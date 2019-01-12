/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.PrivateIbean;
import com.basic.oaas.model.Private;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 权限接口
 * 
 */
public interface PrivateService extends BaseServer<Long, Private> {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param privateId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long privateId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(Private record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param privateId
	 * @return
	 *
	 */
	public Private qryByPrimaryKey(Long privateId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(Private record);
	
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(Private ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param privateIds
     * @return
     *
     */
    public int removeBatchByPrivateIds(Long[] privateIds);
    
    /**
     * 
     * @date 2017年8月30日 下午12:04:16
     * @author Kevin
     * @Description: 根据菜单id批量修改权限状态
     * @param menuIds
     * @return
     *
     */
    public int removeBatchByMenuIds(Long[] menuIds);
    
    
    /**
     * 
     * @date 2017年8月30日 下午12:04:16
     * @author Kevin
     * @Description: 根据功能按钮id批量修改权限状态
     * @param funcIds
     * @return
     *
     */
    public int removeBatchByFuncIds(Long[] funcIds);
    
    /**
     * 
     * @date 2017年9月4日 下午3:42:43
     * @author Kevin
     * @Description: 根据用户Id获取拥有的权限
     * @param userId
     * @return
     *
     */
    public List<Private> qryPrivateByUserId(Long userId);
    
	/**
	 * 
	 * @date 2017年9月19日 下午5:21:27
	 * @author Kevin
	 * @Description: 根据路径查询是否拥有权限
	 * @param requestUrl
	 * @return
	 *
	 */
	public List<String> qryPrivateByRequest(String requestUrl);
	
	/**
	 * 
	 * @date 2017年9月19日 下午5:21:27
	 * @author Kevin
	 * @Description: 根据用户名查询权限
	 * @param username
	 * @return
	 *
	 */
	public List<String> qryPrivateByUsername(String username);
	
	/**
	 * 
	 * @date 2017年9月19日 下午5:33:42
	 * @author Kevin
	 * @Description: 查询所有权限
	 * @return
	 *
	 */
	public List<String> qryAllPrivate();

	/**
	 * 根据字段ID删除字段权限
	 * @param attrIds
	 * @return
	 */
	int removeBatchByAttrIds(Long[] attrIds);

	/**
	 * 分页查询
	 * @param queryFilter
	 * @return
	 */
	PageJson queryPage(DefaultQueryFilter queryFilter);

	/**
	 * 获取权限明细
	 * @Description:
	 * @author lengzj
	 * @param userId
	 * @param menuId
	 * @return
	 */
	JSONObject getPrivates(Long userId, Long menuId);

}
