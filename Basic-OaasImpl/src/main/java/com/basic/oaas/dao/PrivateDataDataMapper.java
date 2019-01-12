package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.oaas.bean.PrivateDataDataIbean;
import com.basic.oaas.model.PrivateDataData;

public interface PrivateDataDataMapper {
	
	/**
	 * 
	 * @date 2017年9月8日 上午10:46:05
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataDataId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long dataDataId);

    /**
     * 
     * @date 2017年9月8日 上午10:46:17
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(PrivateDataData record);

    /**
     * 
     * @date 2017年9月8日 上午10:46:37
     * @author Kevin
     * @Description: 根据主键查询
     * @param dataDataId
     * @return
     *
     */
    public PrivateDataData selectByPrimaryKey(Long dataDataId);

    /**
     * 
     * @date 2017年9月8日 上午10:46:56
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(PrivateDataData record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PrivateDataData record);
    
    /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateDataData> selectPrivateDataDataList(PrivateDataDataIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectPrivateDataDataListCount(PrivateDataDataIbean ibean);
    
    /**
     * 根据源数据删除相关数据
     * @param params
     * @return
     */
    public int deleteBySource(Map<String,Object> params);

    /**
     * 
     * @Description:根据源数据ID获取数据权限
     * @author lengzj
     * @param params
     * @return
     */
	public PrivateDataData getBySource(Map<String, Object> params);

}