package com.basic.system.dao;

import com.basic.system.model.FileInfo;

public interface FileInfoMapper {
	
	/**
	 * 
	 * @date 2017年7月3日 下午4:47:34
	 * 
	 * @Description: 根据主键删除
	 * @param fileInfoId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long fileInfoId);

	/**
	 * 
	 * @date 2017年7月3日 下午4:47:44
	 * 
	 * @Description: 新增
	 * @param record
	 * @return
	 *
	 */
	public int insertSelective(FileInfo record);

	/**
	 * 
	 * @date 2017年7月3日 下午4:47:58
	 * 
	 * @Description: 根据主键查询
	 * @param fileInfoId
	 * @return
	 *
	 */
	public FileInfo selectByPrimaryKey(Long fileInfoId);

	/**
	 * 
	 * @date 2017年7月3日 下午4:48:12
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int updateByPrimaryKeySelective(FileInfo record);
}