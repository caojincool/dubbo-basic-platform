package com.basic.oaas.dao;

import com.basic.oaas.bean.PrivateDataGroupIbean;
import com.basic.oaas.model.PrivateDataGroup;
import java.util.List;

/**
 * 
 *
 * @date 2017年9月13日 上午10:25:10
 * @author Kevin
 * @Description: 数据权限分组
 *
 */
public interface PrivateDataGroupMapper {
	/**
	 * 
	 * @date 2017年9月13日 上午10:25:34
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataGroupId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long dataGroupId);

    /**
     * 
     * @date 2017年9月13日 上午10:25:40
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(PrivateDataGroup record);

    /**
     * 
     * @date 2017年9月13日 上午10:25:44
     * @author Kevin
     * @Description: 根据主键查询
     * @param dataGroupId
     * @return
     *
     */
    public PrivateDataGroup selectByPrimaryKey(Long dataGroupId);

    /**
     * 
     * @date 2017年9月13日 上午10:25:47
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(PrivateDataGroup record);
    
    /**
     * 
     * @Description:更新所有
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PrivateDataGroup record);
    
    /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateDataGroup> selectPrivateDataGroupList(PrivateDataGroupIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectPrivateDataGroupListCount(PrivateDataGroupIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 批量修改状态
     * @param dataGroupIds
     * @return
     *
     */
    public int deleteBatchByDataGroupIds(Long[] dataGroupIds);

}