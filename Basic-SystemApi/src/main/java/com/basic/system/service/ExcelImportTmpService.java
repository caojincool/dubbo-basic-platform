package com.basic.system.service;

import java.util.List;

import com.basic.system.bean.ExcelImportTmpIBean;
import com.basic.system.model.ExcelImportTmp;

public interface ExcelImportTmpService {
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
	public int createSelective(ExcelImportTmp record);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:12
	 * 
	 * @Description: 根据主键查询
	 * @param templateCode
	 * @return
	 *
	 */
	public ExcelImportTmp qryByPrimaryKey(String templateCode);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:23
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(ExcelImportTmp record);
	
	
	/**
	 * 
	 * @date 2017年7月4日 上午10:25:22
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<ExcelImportTmp> qryExcelImportTmpList(ExcelImportTmpIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 上午10:25:31
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryExcelImportTmpListCount(ExcelImportTmpIBean ibean);
    
    /**
     * 
     * @date 2017年7月4日 下午2:50:49
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(ExcelImportTmp ibean);
}
