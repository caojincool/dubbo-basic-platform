package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.ButtonIbean;
import com.basic.order.model.Button;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 按钮
 *
 */
public interface ButtonService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param buttonId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long buttonId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(Button record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param buttonId
	 * @return
	 *
	 */
	public Button qryByPrimaryKey(Long buttonId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(Button record);
	
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询单据按钮
     * @param ibean
     * @return
     *
     */
    public List<Button> qryOrderButtonList(ButtonIbean ibean);
    
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询任务按钮
     * @param ibean
     * @return
     *
     */
    public List<Button> qryWoButtonList(ButtonIbean ibean);
    
}
