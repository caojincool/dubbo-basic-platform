package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.bean.PrivateIbean;
import com.basic.oaas.model.Private;

/**
 * 
 *
 * @date 2017年8月8日 上午10:56:41
 * @author Kevin
 * @Description: 权限表
 *
 */
public interface PrivateMapper {
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:56:56
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param privateId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long privateId);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:57:22
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int insertSelective(Private record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:57:57
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param privateId
	 * @return
	 *
	 */
	public Private selectByPrimaryKey(Long privateId);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:58:20
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int updateByPrimaryKeySelective(Private record);
	
	/**
	 * 
	 * @Description:更新所有字段
	 * @author lengzj
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(Private record);

	
	
	/**
	 * 
	 * @date 2017年8月8日 上午11:01:38
	 * @author Kevin
	 * @Description: 批量删除，正常情况下，别的模块只修改状态
	 * @param privateIds
	 * @return
	 *
	 */
	public int deleteBatchByPrivateIds(Long[] privateIds);
	
	/**
	 * 
	 * @date 2017年8月8日 上午11:02:46
	 * @author Kevin
	 * @Description: 批量修改状态
	 * @param privateIds
	 * @return
	 *
	 */
	public int updateBatchByPrivateIds(Long[] privateIds);
	
	/**
	 * 
	 * @date 2017年8月30日 上午11:56:50
	 * @author Kevin
	 * @Description: 根据菜单Id修改权限的状态
	 * @param menuIds
	 * @return
	 *
	 */
	public int updateBatchByMenuIds(Long[] menuIds);
	
	/**
	 * 
	 * @date 2017年8月30日 上午11:58:25
	 * @author Kevin
	 * @Description: 根据功能按钮修改权限状态
	 * @param funcIds
	 * @return
	 *
	 */
	public int updateBatchByFuncIds(Long[] funcIds);
	
	/**
	 * 
	 * @date 2017年9月4日 下午3:30:45
	 * @author Kevin
	 * @Description: 根据用户id 查询用户拥有的权限
	 * @param userId 用户Id
	 * @return
	 *
	 */
	public List<Private> selectPrivateByUserId(Long userId);
	
	/**
	 * 
	 * @date 2017年9月19日 下午5:21:27
	 * @author Kevin
	 * @Description: 根据路径查询是否拥有权限编码
	 * @param requestUrl
	 * @return
	 *
	 */
	public List<String> selectPrivateByRequest(String requestUrl);
	
	/**
	 * 
	 * @date 2017年9月19日 下午5:21:27
	 * @author Kevin
	 * @Description: 根据用户名查询权限
	 * @param username
	 * @return
	 *
	 */
	public List<String> selectPrivateByUsername(String username);
	
	/**
	 * 
	 * @date 2017年9月19日 下午5:33:42
	 * @author Kevin
	 * @Description: 查询所有权限
	 * @return
	 *
	 */
	public List<String> selectAllPrivate();
	
	/**
	 * 根据字段ID删除
	 * @param attrIds
	 * @return
	 */
	public int updateBatchByAttrIds(Long[] attrIds);
	
}