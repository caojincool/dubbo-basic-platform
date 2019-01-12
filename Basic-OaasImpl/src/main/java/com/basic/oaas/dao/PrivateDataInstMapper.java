package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.bean.PrivateDataInstIbean;
import com.basic.oaas.model.PrivateDataInst;
/**
 * 
 *
 * @author Kevin
 * @Description: 数据权限实例映射接口
 *
 */
public interface PrivateDataInstMapper {

	/**
	 * 
	 * @date 2017�?8�?16�? 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataInstId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long dataInstId);

    /**
     * 
     * @date 2017�?8�?16�? 上午11:48:13
     * @author Kevin
     * @Description: 新增�?条数�?
     * @param record
     * @return
     *
     */
    public int insertSelective(PrivateDataInst record);

    /**
     * 
     * @date 2017�?8�?16�? 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param dataInstId
     * @return
     *
     */
    public PrivateDataInst selectByPrimaryKey(Long dataInstId);

    /**
     * 
     * @date 2017�?8�?16�? 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(PrivateDataInst record);
    
    /**
	 * 
	 * @date 2017�?8�?2�? 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateDataInst> selectPrivateDataInstList(PrivateDataInstIbean ibean);
    
    /**
     * 
     * @date 2017�?8�?2�? 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的�?�数
     * @param ibean
     * @return
     *
     */
    public long selectPrivateDataInstListCount(PrivateDataInstIbean ibean);
    
    
    /**
     * 
     * @date 2017�?8�?2�? 下午3:38:12
     * @author Kevin
     * @Description: 批量删除
     * @param dataInstIds
     * @return
     *
     */
    public int deleteBatchByDataInstIds(Long[] dataInstIds);
}