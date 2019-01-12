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
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.PrivateMenuIbean;
import com.basic.oaas.model.PrivateMenu;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 菜单接口
 * 
 */
public interface PrivateMenuService extends BaseServer<Long, PrivateMenu> {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param menuId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long menuId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(PrivateMenu record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param menuId
	 * @return
	 *
	 */
	public PrivateMenu qryByPrimaryKey(Long menuId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(PrivateMenu record);
	
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     * @return 
     *
     */
    public PrivateMenu createOrModify(PrivateMenu ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param menuIds
     * @return
     *
     */
    public int removeBatchByPrivateMenuIds(Long[] menuIds);
    
    /**
	 * 
	 * @date 2017年9月20日 下午7:18:52
	 * @author Kevin
	 * @Description: 根据编码获取菜单
	 * @param codes
	 * @return
	 *
	 */
	public List<PrivateMenu> qryMenuByCode(List<String> codes);

	/**
	 * 分页查询
	 * @param queryFilter
	 * @return
	 */
	PageJson queryPage(DefaultQueryFilter queryFilter);
	
	/**
     * 获取有权限的菜单
     * @param params
     * @return
     */
	List<PrivateMenu> getPrivateMenus(Long userId, Long menuId);

	/**
	 * 
	 * @Description:初始化
	 * @author lengzj
	 * @param children
	 */
	void getChildren(JSONArray children);
}
