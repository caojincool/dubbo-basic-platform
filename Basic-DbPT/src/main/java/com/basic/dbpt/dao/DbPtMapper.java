package com.basic.dbpt.dao;

import com.basic.dbpt.model.DbPt;

public interface DbPtMapper {
	/**
	 * 
	 * @date 2017年8月11日 上午10:11:02
	 * 
	 * @Description: 新增一条数据，需要数据库表名
	 * @param record
	 * @return
	 *
	 */
    public int insert(DbPt record);
    
    /**
     * 
     * @date 2017年8月11日 上午10:25:45
     * 
     * @Description: 查看某个数据库下的表是否存在
     * @param record
     * @return
     *
     */
    public long selectIsExistTable(DbPt record);
    
    /**
     * 
     * @date 2017年8月11日 上午10:26:25
     * 
     * @Description: 删除表
     * @param record
     *
     */
    public void deleteTable(DbPt record);
    
    /**
     * 
     * @date 2017年8月11日 上午10:27:13
     * 
     * @Description: 创建表
     * @param record
     *
     */
    public void createTable(DbPt record);
    
}