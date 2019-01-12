package com.basic.oaas.dao;


import java.util.List;
import java.util.Map;

import com.basic.oaas.bean.AreaIbean;
import com.basic.oaas.model.Area;

public interface AreaMapper {
	
	/**
	 * 
	 * @date 2017年7月5日 上午10:07:20
	 * @author Kevin
	 * @Description: 删除
	 * @param areaId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long areaId);

    /**
     * 
     * @date 2017年7月5日 上午10:08:19
     * @author Kevin
     * @Description: 增加
     * @param record
     * @return
     *
     */
   
	public int insertSelective(Area record);

    /**
     * 
     * @date 2017年7月5日 上午10:09:03
     * @author Kevin
     * @Description: 根据主键查询
     * @param areaId
     * @return
     *
     */
	public Area selectByPrimaryKey(Long areaId);

    /**
     * 
     * @date 2017年7月5日 上午10:09:47
     * @author Kevin
     * @Description: 更新
     * @param record
     * @return
     *
     */
	public int updateByPrimaryKeySelective(Area record);
	
	/**
	 * 
	 * @Description:更新所有字段
	 * @author lengzj
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(Area record);

    
   /**
    * 
    * @date 2017年8月7日 上午9:13:16
    * @author Kevin
    * @Description: 根据区域条件获取区域列表
    * @param iBean
    * @return
    *
    */
	public List<Area> selectByArea(AreaIbean iBean);
    
    /**
     * 
     * @date 2017年7月7日 下午4:51:29
     * @author Kevin
     * @Description: 获取当前区域下面的子区域
     * @param areaId
     * @return
     *
     */
	public List<Area> selectSubAreaById(Long areaId);
    
    /**
     * 
     * @date 2017年7月7日 下午5:02:47
     * @author Kevin
     * @Description: 查询当前区域下的所有子区域
     * @param areaId
     * @return
     *
     */
	public List<Area> selectAllSubAreaById(Area area);
    
    
    /**
     * 
     * @date 2017年7月17日 上午9:48:38
     * @author Kevin
     * @Description: 查询是否有子区域
     * @param areaId
     * @return
     *
     */
	public int selectExistSubAreaById(AreaIbean iBean);
	
	/**
	 * 
	 * @date 2017年8月11日 下午5:52:25
	 * @author Kevin
	 * @Description: 查询数据库是否存在区域相同的区域编码
	 * @param areaCode
	 * @return
	 *
	 */
	public int selectExistAreaCode(String areaCode);
	
	/**
	 * 
	 * @date 2017年8月14日 上午9:21:50
	 * @author Kevin
	 * @Description: 根据Id修改所有当前以及子状态
	 * @param areaId
	 * @return
	 *
	 */
	public int updateAllStateById(Long areaId);
	
	/**
	 * 
	 * @Description:根据参数统计区域数
	 * @author lengzj
	 * @param params
	 * @return
	 */
	public int countArea(Map<String,Object> params);
}