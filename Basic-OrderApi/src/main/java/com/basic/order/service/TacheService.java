package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.TacheIbean;
import com.basic.order.model.Tache;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 环节
 *
 */
public interface TacheService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param tacheId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long tacheId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(Tache record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param tacheId
	 * @return
	 *
	 */
	public Tache qryByPrimaryKey(Long tacheId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(Tache record);
	
    /**
     * 
     * @date 2017年8月21日 下午5:18:28
     * 
     * @Description: 根据环节编码查询有效的一条数据
     * @param tacheCode
     * @return
     *
     */
    public Tache qryByTacheCode(String tacheCode);
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<Tache> qryTacheList(TacheIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryTacheListCount(TacheIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:20
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int modifyBatchStateByTacheIds(Tache ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:16:04
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(Tache ibean);
    
}
