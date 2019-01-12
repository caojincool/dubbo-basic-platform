package com.basic.system.service;

import com.basic.system.model.FileInfo;

public interface FileInfoService {
	/**
	 * 
	 * @date 2017年7月3日 下午4:12:19
	 * 
	 * @Description: 根据主键删除
	 * @param fileInfoId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long fileInfoId);

	/**
	 * 
	 * @date 2017年7月3日 下午4:45:58
	 * 
	 * @Description: 新增
	 * @param record
	 * @return
	 *
	 */
	public Long createSelective(FileInfo record);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:12
	 * 
	 * @Description: 根据主键查询
	 * @param fileInfoId
	 * @return
	 *
	 */
	public FileInfo qryByPrimaryKey(Long fileInfoId);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:23
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(FileInfo record);
}
