/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:20
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import com.basic.oaas.model.PrivateFuncCatalog;


/**
 *
 * @date 2017年8月8日 上午9:55:20
 * @author Kevin
 * @Description:功能按钮目录接口
 * 
 */
public interface PrivateFuncCatalogService {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param catalogId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long catalogId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(PrivateFuncCatalog record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param catalogId
	 * @return
	 *
	 */
	public PrivateFuncCatalog qryByPrimaryKey(Long catalogId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(PrivateFuncCatalog record);
	
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(PrivateFuncCatalog ibean);
    
    
    /**
     * 
     * @date 2017年8月8日 下午2:42:29
     * @author Kevin
     * @Description: 根据权限Id查找子目录
     * @param parentCatalogId
     * @return
     *
     */
    public List<PrivateFuncCatalog> qrySubFuncCatalog(Long parentCatalogId);
    
    /**
     * 
     * @date 2017年8月8日 下午3:11:16
     * @author Kevin
     * @Description: 查询是否存在子节点
     * @param parentCatalogId
     * @return
     *
     */
    public int qryExistSubFuncCatalog(Long parentCatalogId);
    
    
    /**
     * 
     * @date 2017年8月25日 上午9:37:27
     * @author Kevin
     * @Description: 修改所有该节点下的所有子节点状态
     * @param catalogId
     * @return
     *
     */
    public int modifyAllStateById(Long catalogId);
}
