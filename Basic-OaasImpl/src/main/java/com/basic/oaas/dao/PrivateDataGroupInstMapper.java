package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.oaas.model.PrivateDataGroupInst;
/**
 * 
 *
 * @date 2017年9月13日 下午2:50:01
 * @author Kevin
 * @Description: 数据权限分组实例
 *
 */
import com.basic.oaas.model.PrivateDataInstData;
public interface PrivateDataGroupInstMapper {
	
	/**
	 * 
	 * @date 2017年9月13日 上午10:25:34
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataGrpInstId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long dataGrpInstId);
    

    /**
     * 
     * @date 2017年9月13日 下午2:50:39
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(PrivateDataGroupInst record);

    /**
     * 
     * @date 2017年9月13日 下午2:50:49
     * @author Kevin
     * @Description: 根据主键查询
     * @param dataGrpInstId
     * @return
     *
     */
    public PrivateDataGroupInst selectByPrimaryKey(Long dataGrpInstId);

    /**
     * 
     * @date 2017年9月13日 下午2:51:06
     * @author Kevin
     * @Description:根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(PrivateDataGroupInst record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PrivateDataGroupInst record);
    
    /**
     * 
     * @date 2017年9月14日 下午11:05:17
     * @author Kevin
     * @Description: 批量删除
     * @param dataGrpInstId
     * @return
     *
     */
    public int deleteBatchByDataGrpInstId(Long[] dataGrpInstId);
    
    /**
     * 根据分组ID删除关联分组实例
     * @param groupId
     * @return
     */
    public int deleteByDataGroupId(Long groupId);
    
    /**
     * 批量插入
     * @param record
     * @return
     */
    public int insertBatchDataInstData(List<PrivateDataGroupInst> record);
    
    /**
     * 
     * @Description:查询是否已经存在
     * @author lengzj
     * @param params
     * @return
     */
    public int checkExist(Map<String,Object> params);
    
}