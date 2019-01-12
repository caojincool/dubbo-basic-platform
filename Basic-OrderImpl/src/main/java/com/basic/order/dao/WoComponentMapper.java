package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.OrderComponetIbean;
import com.basic.order.model.WoComponent;

public interface WoComponentMapper {
    int deleteByPrimaryKey(Long componentId);

    int insertSelective(WoComponent record);

    WoComponent selectByPrimaryKey(Long componentId);

    int updateByPrimaryKeySelective(WoComponent record);
    
    /**
     * 
     * @date 2017年8月22日 上午10:25:14
     * 
     * @Description: 根据环节标识查询有效的组件，按升序
     * @param tacheId
     * @return
     *
     */
    public List<WoComponent> selectByTacheId(Long tacheId);

	/**
	 * @date 2017年9月4日 下午5:25:41
	 * @author wangkui
	 * @Description: 根据查询条件查出记录
	 * @param ibean
	 * @return
	 * 
	 */
    public List<WoComponent> selectComponentList(OrderComponetIbean ibean);

	/**
	 * @date 2017年9月5日 上午9:36:57
	 * @author wangkui
	 * @Description: 查询数总记录数
	 * @param ibean
	 * @return
	 * 
	 */
    public long selectComponentListCount(OrderComponetIbean ibean);

	/**
	 * @date 2017年9月5日 下午3:42:23
	 * @author wangkui
	 * @Description: 根据主键单个或者批量删除记录
	 * @param ibean
	 * @return
	 * 
	 */
    public int updateBatchStateByCompoentId(WoComponent ibean);
}