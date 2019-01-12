package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.WoDispRuleInstIbean;
import com.basic.order.bean.WoDispRuleInstPageIbean;
import com.basic.order.model.WoDispRuleInst;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 任务派发规则实例
 *
 */
public interface WoDispRuleInstService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param ruleInstId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long ruleInstId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(WoDispRuleInst record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param ruleInstId
	 * @return
	 *
	 */
	public WoDispRuleInst qryByPrimaryKey(Long ruleInstId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(WoDispRuleInst record);
	
    /**
     * 
     * @date 2017年8月22日 上午9:50:01
     * 
     * @Description: 查询按区域派单的任务派发规则实例
     * @param ibean
     * @return
     *
     */
    public WoDispRuleInst qryForArea(WoDispRuleInstIbean ibean);
    
    /**
     * 
     * @date 2017年8月22日 上午9:50:01
     * 
     * @Description: 查询按组织派单的任务派发规则实例
     * @param ibean
     * @return
     *
     */
    public WoDispRuleInst qryForOrg(WoDispRuleInstIbean ibean);
    
    /**
     * 
     * @date 2017年8月22日 上午9:50:01
     * 
     * @Description: 查询按用户派单的任务派发规则实例
     * @param ibean
     * @return
     *
     */
    public WoDispRuleInst qryForUser(WoDispRuleInstIbean ibean);
    
    /**
     * 
     * @date 2017年8月22日 上午9:50:01
     * 
     * @Description: 查询按员工派单的任务派发规则实例
     * @param ibean
     * @return
     *
     */
    public WoDispRuleInst qryForStaff(WoDispRuleInstIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<WoDispRuleInst> qryWoDispRuleInstList(WoDispRuleInstPageIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryWoDispRuleInstListCount(WoDispRuleInstPageIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:20
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int modifyBatchStateByRuleInstIds(WoDispRuleInst ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:16:04
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(WoDispRuleInst ibean);
    
}
