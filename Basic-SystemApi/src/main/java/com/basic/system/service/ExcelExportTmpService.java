package com.basic.system.service;

import java.util.List;

import com.basic.system.bean.ExcelExportTmpIBean;
import com.basic.system.model.ExcelExportTmp;

public interface ExcelExportTmpService {
	/**
	 * 
	 * @date 2017年7月3日 下午4:12:19
	 * 
	 * @Description: 根据主键删除
	 * @param templateCode
	 * @return
	 *
	 */
	public int removeByPrimaryKey(String templateCode);

	/**
	 * 
	 * @date 2017年7月3日 下午4:45:58
	 * 
	 * @Description: 新增
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(ExcelExportTmp record);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:12
	 * 
	 * @Description: 根据主键查询
	 * @param templateCode
	 * @return
	 *
	 */
	public ExcelExportTmp qryByPrimaryKey(String templateCode);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:23
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(ExcelExportTmp record);
	
	/**
	 * 
	 * @date 2017年7月4日 上午10:25:22
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<ExcelExportTmp> qryExcelExportTmpList(ExcelExportTmpIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 上午10:25:31
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryExcelExportTmpListCount(ExcelExportTmpIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 下午2:37:56
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(ExcelExportTmp ibean);	
}
