package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.OrderComponetIbean;
import com.basic.order.model.WoComponent;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 任务组件
 *
 */
public interface WoComponentService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param componentId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long componentId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(WoComponent record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param componentId
	 * @return
	 *
	 */
	public WoComponent qryByPrimaryKey(Long componentId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(WoComponent record);
	
    /**
     * 
     * @date 2017年8月22日 上午10:25:14
     * 
     * @Description: 根据环节标识查询有效的组件，按升序
     * @param tacheId
     * @return
     *
     */
    public List<WoComponent> qryByTacheId(Long tacheId);

	/**
	 * @date 2017年9月1日 下午4:22:37
	 * @author wangkui
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @param ibean
	 * 
	 */
    public void createOrModify(WoComponent ibean);

	/**
	 * @date 2017年9月4日 上午10:56:22
	 * @author wangkui
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @param ibean
	 * @return
	 * 
	 */
	public List<WoComponent> qryComponentList(OrderComponetIbean ibean);

	/**
	 * @date 2017年9月4日 上午10:56:31
	 * @author wangkui
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @param ibean
	 * @return
	 * 
	 */
	public long qryComponentListCount(OrderComponetIbean ibean);

	/**
	 * @date 2017年9月5日 下午3:37:05
	 * @author wangkui
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @param ibean
	 * 
	 */
	public int modifyBatchStateByCompoentId(WoComponent ibean);
    
}
