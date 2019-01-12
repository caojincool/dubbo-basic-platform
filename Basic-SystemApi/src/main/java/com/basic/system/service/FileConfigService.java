package com.basic.system.service;

import com.basic.framework.web.model.VerifyUniqueCode;
import com.basic.system.model.FileConfig;

public interface FileConfigService {
	/**
	 * 
	 * @date 2017年7月3日 下午4:12:19
	 * 
	 * @Description: 根据主键删除
	 * @param fileConfigId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long fileConfigId);

	/**
	 * 
	 * @date 2017年7月3日 下午4:45:58
	 * 
	 * @Description: 新增
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(FileConfig record);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:12
	 * 
	 * @Description: 根据主键查询
	 * @param fileConfigId
	 * @return
	 *
	 */
	public FileConfig qryByPrimaryKey(Long fileConfigId);

	/**
	 * 
	 * @date 2017年7月3日 下午4:46:23
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(FileConfig record);
	
	/**
	 * 
	 * @date 2017年7月4日 下午2:58:21
	 * 
	 * @Description: 根据模块编码查询
	 * @param moduleCode
	 * @return
	 *
	 */
    public FileConfig qryByModuleCode(String moduleCode);
    
    /**
     * 
     * @date 2017年8月23日 上午9:47:26
     * 
     * @Description: 查询某个编码的数量，看是否已存在
     * @param ibean
     * @return
     *
     */
    public int qryCodeCount(VerifyUniqueCode ibean);
    
}
