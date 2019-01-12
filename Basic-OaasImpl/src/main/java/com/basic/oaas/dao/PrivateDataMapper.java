package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.bean.PrivateDataIbean;
import com.basic.oaas.model.PrivateData;

/**
 * 
 *
 * @date 2017年9月7日 下午2:20:13
 * @author Kevin
 * @Description: 数据权限表
 *
 */
public interface PrivateDataMapper {
	
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long dataId);

    /**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(PrivateData record);

    /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param dataId
     * @return
     *
     */
    public PrivateData selectByPrimaryKey(Long dataId);

    /**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(PrivateData record);
    
    /**
     * 
     * @Description:更新所有
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PrivateData record);
    
    /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateData> selectPrivateDataList(PrivateDataIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectPrivateDataListCount(PrivateDataIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 批量修改状态
     * @param dataIds
     * @return
     *
     */
    public int updateBatchByDataIds(Long[] dataIds);
    
    
    /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateData> selectPrivateDataByUserList(PrivateDataIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectPrivateDataByUserListCount(PrivateDataIbean ibean);
}