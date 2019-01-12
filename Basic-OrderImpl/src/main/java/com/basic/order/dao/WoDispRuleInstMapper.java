package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.WoDispRuleInstIbean;
import com.basic.order.bean.WoDispRuleInstPageIbean;
import com.basic.order.model.WoDispRuleInst;

public interface WoDispRuleInstMapper {
    int deleteByPrimaryKey(Long ruleInstId);

    int insertSelective(WoDispRuleInst record);

    WoDispRuleInst selectByPrimaryKey(Long ruleInstId);

    int updateByPrimaryKeySelective(WoDispRuleInst record);
    
    /**
     * 
     * @date 2017年8月22日 上午9:50:01
     * 
     * @Description: 查询按区域派单的任务派发规则实例
     * @param ibean
     * @return
     *
     */
    public WoDispRuleInst selectForArea(WoDispRuleInstIbean ibean);
    
    /**
     * 
     * @date 2017年8月22日 上午9:50:01
     * 
     * @Description: 查询按组织派单的任务派发规则实例
     * @param ibean
     * @return
     *
     */
    public WoDispRuleInst selectForOrg(WoDispRuleInstIbean ibean);
    
    /**
     * 
     * @date 2017年8月22日 上午9:50:01
     * 
     * @Description: 查询按用户派单的任务派发规则实例
     * @param ibean
     * @return
     *
     */
    public WoDispRuleInst selectForUser(WoDispRuleInstIbean ibean);
    
    /**
     * 
     * @date 2017年8月22日 上午9:50:01
     * 
     * @Description: 查询按员工派单的任务派发规则实例
     * @param ibean
     * @return
     *
     */
    public WoDispRuleInst selectForStaff(WoDispRuleInstIbean ibean);
    
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<WoDispRuleInst> selectWoDispRuleInstList(WoDispRuleInstPageIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectWoDispRuleInstListCount(WoDispRuleInstPageIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:20
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int updateBatchStateByRuleInstIds(WoDispRuleInst ibean);
}