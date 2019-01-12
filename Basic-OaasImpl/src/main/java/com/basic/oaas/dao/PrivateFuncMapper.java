package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.bean.PrivateFuncIbean;
import com.basic.oaas.model.PrivateFunc;

/**
 * 
 *
 * @date 2017年8月30日 下午2:57:00
 * @author Kevin
 * @Description: 功能按钮表
 *
 */
public interface PrivateFuncMapper {
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:56:56
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param menuId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long funcId);

    /**
	 * 
	 * @date 2017年8月8日 上午10:57:22
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
    public int insertSelective(PrivateFunc record);

    /**
   	 * 
   	 * @date 2017年8月8日 上午10:57:57
   	 * @author Kevin
   	 * @Description: 根据主键查询
   	 * @param menuId
   	 * @return
   	 *
   	 */
    public PrivateFunc selectByPrimaryKey(Long funcId);

    /**
	 * 
	 * @date 2017年8月8日 上午10:58:20
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
    public int updateByPrimaryKeySelective(PrivateFunc record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PrivateFunc record);
    
	/**
	 * 
	 * @date 2017年8月8日 上午11:01:38
	 * @author Kevin
	 * @Description: 批量删除，正常情况下，别的模块只修改状态
	 * @param funcIds
	 * @return
	 *
	 */
	public int deleteBatchByFuncIds(Long[] funcIds);
	
	/**
	 * 
	 * @date 2017年8月8日 上午11:02:46
	 * @author Kevin
	 * @Description: 批量修改状态
	 * @param funcIds
	 * @return
	 *
	 */
	public int updateBatchByFuncIds(Long[] funcIds);
	
	/**
	 * 
	 * @date 2017年9月22日 下午3:07:51
	 * @author Kevin
	 * @Description: 根据功能编码，查询功能按钮
	 * @param funcCode
	 * @return
	 *
	 */
	public List<PrivateFunc> selectFuncByFuncCode (String[] funcCode);

}