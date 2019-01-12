package com.basic.system.dao;

import java.util.List;

import com.basic.system.bean.ExcelExportTmpIBean;
import com.basic.system.model.ExcelExportTmp;

public interface ExcelExportTmpMapper {
	/**
	 * 
	 * @date 2017年7月3日 下午5:03:57
	 * 
	 * @Description: 根据主键删除
	 * @param templateCode
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(String templateCode);

	/**
	 * 
	 * @date 2017年7月3日 下午5:04:14
	 * 
	 * @Description: 新增
	 * @param record
	 * @return
	 *
	 */
	public int insertSelective(ExcelExportTmp record);

	/**
	 * 
	 * @date 2017年7月3日 下午5:04:20
	 * 
	 * @Description: 根据主键查询
	 * @param templateCode
	 * @return
	 *
	 */
	public ExcelExportTmp selectByPrimaryKey(String templateCode);

	/**
	 * 
	 * @date 2017年7月3日 下午5:04:30
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int updateByPrimaryKeySelective(ExcelExportTmp record);
	
	/**
	 * 
	 * @date 2017年7月4日 上午10:25:22
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<ExcelExportTmp> selectExcelExportTmpList(ExcelExportTmpIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 上午10:25:31
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectExcelExportTmpListCount(ExcelExportTmpIBean ibean);
}