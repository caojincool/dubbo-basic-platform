package com.basic.system.dao;

import java.util.List;

import com.basic.system.bean.GidServerIBean;
import com.basic.system.model.GidServer;

public interface GidServerMapper {
    int deleteByPrimaryKey(String gidCode);

    int insertSelective(GidServer record);

    GidServer selectByPrimaryKey(String gidCode);

    int updateByPrimaryKeySelective(GidServer record);
    
	/**
	 * 
	 * @date 2017年7月4日 上午10:25:22
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<GidServer> selectGidServerList(GidServerIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 上午10:25:31
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectGidServerListCount(GidServerIBean ibean);
}