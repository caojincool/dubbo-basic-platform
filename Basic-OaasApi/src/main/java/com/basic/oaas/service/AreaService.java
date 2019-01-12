/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 区域接口
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.AreaIbean;
import com.basic.oaas.model.Area;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 区域
 * 
 */
public interface AreaService extends BaseServer<Long, Area> {
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:11
	 * @author Kevin
	 * @Description: 新增
	 * @param area
	 * @return
	 *
	 */
	public int createSelective(Area area);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:31
	 * @author Kevin
	 * @Description: 主键查询
	 * @param areaId
	 * @return
	 *
	 */
	public Area qryAreaById(Long areaId);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:51
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param area
	 * @return
	 *
	 */
	public int modifyByPrimaryKey(Area area);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:07
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param areaId
	 * @return
	 *
	 */
	public int removeByPrimarykey(Long areaId);
	
	/**
	 * 
	 * @date 2017年8月7日 上午9:48:53
	 * @author Kevin
	 * @Description: 创建获取修改
	 * @param iBean
	 * @return
	 *
	 */
	public Area createOrModify(Area area);
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:49
	 * @author Kevin
	 * @Description: 获取当前用户的区域
	 * @param userId
	 * @return
	 *
	 */
    public List<Area> qryByArea(AreaIbean ibean);
	
    /**
     * 
     * @date 2017年7月7日 下午5:20:12
     * @author Kevin
     * @Description: 获取当前区域下的子区域
     * @param areaId
     * @return
     *
     */
    public List<Area> qrySubAreaById(Long areaId);
    
    /**
     * 
     * @date 2017年7月7日 下午5:20:45
     * @author Kevin
     * @Description: 获取当前区域下的所有子区域
     * @param area
     * @return
     *
     */
    public List<Area> qryAllSubAreaById(Area area);
	
    /**
     * 
     * @date 2017年7月17日 上午9:52:24
     * @author Kevin
     * @Description: 查询是否存在子区域
     * @param userId
     * @param parentAreaId
     * @return
     *
     */
    public int qryExistSubAreaById(AreaIbean ibean);
    
    /**
     * 
     * @date 2017年8月11日 下午5:58:13
     * @author Kevin
     * @Description: 查询区域编码是否存在
     * @param areaCode
     * @return
     *
     */
    public int qryExistAreaCode(String areaCode);
    

	PageJson queryPage(DefaultQueryFilter queryFilter);
	
}
