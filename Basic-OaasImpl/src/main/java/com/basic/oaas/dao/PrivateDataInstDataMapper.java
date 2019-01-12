package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.bean.PrivateDataInstDataIbean;
import com.basic.oaas.model.PrivateDataInstData;
import com.basic.oaas.model.UserPrivate;

public interface PrivateDataInstDataMapper {
	
	/**
	 * 
	 * @date 2017年9月8日 上午10:46:05
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataInstDataId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long dataInstDataId);

    /**
     * 
     * @date 2017年9月8日 上午10:46:17
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(PrivateDataInstData record);

    /**
     * 
     * @date 2017年9月8日 上午10:46:37
     * @author Kevin
     * @Description: 根据主键查询
     * @param dataInstDataId
     * @return
     *
     */
    public PrivateDataInstData selectByPrimaryKey(Long dataInstDataId);

    /**
     * 
     * @date 2017年9月8日 上午10:46:56
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(PrivateDataInstData record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PrivateDataInstData record);

    /**
     * 
     * @date 2017年9月8日 上午10:48:34
     * @author Kevin
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<PrivateDataInstData> selectInstDataList(PrivateDataInstDataIbean ibean);
    
    /**
     * 
     * @date 2017年9月8日 上午10:48:34
     * @author Kevin
     * @Description: 根据参数查询记录数
     * @param ibean
     * @return
     *
     */
    public long selectInstDataListCount(PrivateDataInstDataIbean ibean);
    
    /**
     * 
     * @date 2017年9月8日 上午11:23:49
     * @author Kevin
     * @Description: 批量删除数据权限实例数据
     * @param dataInstDataIds
     * @return
     *
     */
    public int deleteBatchByDataInstDataIds(Long[] dataInstDataIds);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:08
     * @author Kevin
     * @Description: 批量插入
     * @param record
     * @return
     *
     */
    public int insertBatchDataInstData(List<PrivateDataInstData> record);
    
    /**
     * 
     * @date 2017年9月8日 上午11:23:49
     * @author Kevin
     * @Description: 根据数据实例Id删除数据
     * @param dataInstId
     * @return
     *
     */
    public int deleteByDataInstId(Long dataInstId);
    
    
}