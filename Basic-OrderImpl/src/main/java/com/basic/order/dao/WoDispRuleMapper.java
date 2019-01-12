package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.WoDispRuleIbean;
import com.basic.order.model.WoDispRule;

public interface WoDispRuleMapper {
    int deleteByPrimaryKey(Long ruleId);

    int insertSelective(WoDispRule record);

    WoDispRule selectByPrimaryKey(Long ruleId);

    int updateByPrimaryKeySelective(WoDispRule record);
    
    /**
     * 
     * @date 2017年8月21日 下午5:08:57
     * 
     * @Description: 根据环节ID查询有效的任务派发规则，一条数据
     * @param tacheId
     * @return
     *
     */
    public WoDispRule selectByTacheId(Long tacheId);
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<WoDispRule> selectWoDispRuleList(WoDispRuleIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectWoDispRuleListCount(WoDispRuleIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:20
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int updateBatchStateByRuleIds(WoDispRule ibean);
    
}