package com.basic.system.service;

import java.util.List;

import com.basic.system.bean.GidServerIBean;
import com.basic.system.model.GidServer;

public interface GidServerService {
	/**
	 * 
	 * @date 2017年7月3日 下午4:12:19
	 * 
	 * @Description: 根据主键删除
	 * @param gidCode
	 * @return
	 *
	 */
	public int removeByPrimaryKey(String gidCode);

	/**
	 * 
	 * @date 2017年7月3日 下午4:45:58
	 * 
	 * @Description: 新增
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(GidServer record);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:12
	 * 
	 * @Description: 根据主键查询
	 * @param gidCode
	 * @return
	 *
	 */
	public GidServer qryByPrimaryKey(String gidCode);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:23
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(GidServer record);
	
	/**
	 * 
	 * @date 2017年7月4日 上午10:25:22
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<GidServer> qryGidServerList(GidServerIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 上午10:25:31
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryGidServerListCount(GidServerIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 下午2:37:56
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(GidServer ibean);	
    
    /**
     * 
     * @date 2017年10月12日 下午2:19:28
     * 
     * @Description: 根据gidCode获取gidValue
     * @param gidCode
     * @return
     *
     */
    public Long getGidValue(String gidCode);	
}
