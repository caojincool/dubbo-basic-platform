package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.oaas.bean.PrivateMenuIbean;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.PrivateMenu;

public interface PrivateMenuMapper {
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:56:56
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param menuId
	 * @return
	 *
	 */
    int deleteByPrimaryKey(Long menuId);

    /**
	 * 
	 * @date 2017年8月8日 上午10:57:22
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
    int insertSelective(PrivateMenu record);

    /**
   	 * 
   	 * @date 2017年8月8日 上午10:57:57
   	 * @author Kevin
   	 * @Description: 根据主键查询
   	 * @param menuId
   	 * @return
   	 *
   	 */
    PrivateMenu selectByPrimaryKey(Long menuId);

    /**
	 * 
	 * @date 2017年8月8日 上午10:58:20
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
    int updateByPrimaryKeySelective(PrivateMenu record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    int updateByPrimaryKey(PrivateMenu record);

	
	/**
	 * 
	 * @date 2017年8月8日 上午11:01:38
	 * @author Kevin
	 * @Description: 批量删除，正常情况下，别的模块只修改状态
	 * @param menuIds
	 * @return
	 *
	 */
	public int deleteBatchByMenuIds(Long[] menuIds);
	
	/**
	 * 
	 * @date 2017年8月8日 上午11:02:46
	 * @author Kevin
	 * @Description: 批量修改状态
	 * @param menuIds
	 * @return
	 *
	 */
	public int updateBatchByMenuIds(Long[] menuIds);
	
	/**
	 * 
	 * @date 2017年9月20日 下午7:18:52
	 * @author Kevin
	 * @Description: 根据编码获取菜单
	 * @param codes
	 * @return
	 *
	 */
	public List<PrivateMenu> selectMenuByCode(List<String> codes);
	
	/**
     * 
     * @Description: find in set
     * @return
     *
     *
     */
    public List<PrivateMenu> selectFindInSet(Map<String,Object> params);
    
    /**
     * 获取有权限的菜单
     * @param params
     * @return
     */
    public List<PrivateMenu> getPrivateMenus(Map<String,Object> params);
}