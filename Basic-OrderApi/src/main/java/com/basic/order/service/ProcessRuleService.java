package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.ProcessRuleIbean;
import com.basic.order.model.ProcessRule;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 流程适配规则
 *
 */
public interface ProcessRuleService {
	
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
	public int createSelective(ProcessRule record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param ruleId
	 * @return
	 *
	 */
	public ProcessRule qryByPrimaryKey(Long ruleId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(ProcessRule record);
	
    /**
     * 
     * @date 2017年8月17日 下午5:23:32
     * 
     * @Description: 根据单据id获取流程适配规则
     * @param orderId
     * @return
     *
     */
    public ProcessRule qryByOrderId(Long orderId);

	/**
	 * @date 2017年9月7日 下午9:00:37
	 * @author lihaijun
	 * @Description: 批量删除流程适配
	 * @param ibean
	 * 
	 */
	public int modifyBatchStateByRuleIds(ProcessRule ibean);

	/**
	 * @date 2017年9月7日 下午9:01:45
	 * @author lihaijun
	 * @Description: 修改或更新
	 * @param bean
	 * 
	 */
	public void createOrModify(ProcessRule bean);

	/**
	 * @date 2017年9月7日 下午9:03:00
	 * @author lihaijun
	 * @Description: 查询全部流程适配
	 * @param processRulebean
	 * @return
	 * 
	 */
	public List<ProcessRule> qryProcessRuleList(ProcessRuleIbean processRulebean);

	/**
	 * @date 2017年9月7日 下午9:04:06
	 * @author lihaijun
	 * @Description: 查询总数
	 * @param processRulebean
	 * @return
	 * 
	 */
	public long qryProcessRuleListCount(ProcessRuleIbean processRulebean);
}
