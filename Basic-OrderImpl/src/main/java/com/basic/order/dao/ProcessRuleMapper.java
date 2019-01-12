package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.ProcessRuleIbean;
import com.basic.order.model.ProcessRule;

public interface ProcessRuleMapper {
    int deleteByPrimaryKey(Long ruleId);

    int insertSelective(ProcessRule record);

    ProcessRule selectByPrimaryKey(Long ruleId);

    int updateByPrimaryKeySelective(ProcessRule record);
    
    /**
     * 
     * @date 2017年8月17日 下午5:23:32
     * 
     * @Description: 根据单据id获取流程适配规则
     * @param orderId
     * @return
     *
     */
    public ProcessRule selectByOrderId(Long orderId);

	/**
	 * @date 2017年9月8日 下午2:31:42
	 * @author lihaijun
	 * @Description: 查询全部流程适配
	 * @param processRulebean
	 * @return
	 * 
	 */
	List<ProcessRule> selectProcessRuleList(ProcessRuleIbean processRulebean);

	/**
	 * @author lihaijun
	 * @Description: 查询总数
	 * @param processRulebean
	 * @return
	 * 
	 */
	long selectProcessRuleListCount(ProcessRuleIbean processRulebean);

	/**
	 * @date 2017年9月8日 下午2:55:10
	 * @author lihaijun
	 * @Description: 批量更新
	 * @param ibean
	 * @return
	 * 
	 */
	int updateBatchStateByRuleIds(ProcessRule ibean);
}