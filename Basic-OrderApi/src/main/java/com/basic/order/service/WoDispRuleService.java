package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.WoDispRuleIbean;
import com.basic.order.model.WoDispRule;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 任务派发规则
 *
 */
public interface WoDispRuleService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param ruleId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long ruleId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(WoDispRule record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param ruleId
	 * @return
	 *
	 */
	public WoDispRule qryByPrimaryKey(Long ruleId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(WoDispRule record);
	
    /**
     * 
     * @date 2017年8月21日 下午5:08:57
     * 
     * @Description: 根据环节ID查询有效的任务派发规则，一条数据
     * @param tacheId
     * @return
     *
     */
    public WoDispRule qryByTacheId(Long tacheId);
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<WoDispRule> qryWoDispRuleList(WoDispRuleIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryWoDispRuleListCount(WoDispRuleIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:20
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int modifyBatchStateByRuleIds(WoDispRule ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:16:04
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(WoDispRule ibean);
    
}
