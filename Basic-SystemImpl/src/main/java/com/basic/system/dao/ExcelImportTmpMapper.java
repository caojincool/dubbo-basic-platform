package com.basic.system.dao;

import java.util.List;

import com.basic.system.bean.ExcelImportTmpIBean;
import com.basic.system.model.ExcelImportTmp;

public interface ExcelImportTmpMapper {
	
	/**
	 * 
	 * @date 2017年7月3日 下午5:04:46
	 * 
	 * @Description: 根据主键删除
	 * @param templateCode
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(String templateCode);

    /**
     * 
     * @date 2017年7月3日 下午5:05:07
     * 
     * @Description: 新增
     * @return
     *
     */
	public int insertSelective(ExcelImportTmp record);

    /**
     * 
     * @date 2017年7月3日 下午5:05:14
     * 
     * @Description: 根据主键查询
     * @param templateCode
     * @return
     *
     */
	public ExcelImportTmp selectByPrimaryKey(String templateCode);

    /**
     * 
     * @date 2017年7月3日 下午5:05:23
     * 
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
	public int updateByPrimaryKeySelective(ExcelImportTmp record);
	
	/**
	 * 
	 * @date 2017年7月4日 上午10:25:22
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<ExcelImportTmp> selectExcelImportTmpList(ExcelImportTmpIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 上午10:25:31
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectExcelImportTmpListCount(ExcelImportTmpIBean ibean);
}