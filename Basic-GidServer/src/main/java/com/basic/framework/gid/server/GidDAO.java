/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月5日 下午4:05:27
 * @author lzj
 * @Description: 数据库操作
 * 
 */
package com.basic.framework.gid.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.basic.framework.common.utils.datatype.JsonUtils;


/**
 *
 * @date 2015年12月5日 下午4:05:27
 * @author lzj
 * @Description: 数据库操作
 * 
 */
@Repository
public class GidDAO {

	private Logger logger = LoggerFactory.getLogger(GidDAO.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 查询所有
	 * @date 2015年12月5日 下午4:35:54
	 * @author lzj
	 * @Description: 查询所有
	 * @return
	 *
	 */
	public List<GidServerVo> selectAllGid() {
		String sql = "SELECT GID_CODE,GID_NAME,IS_USE,CURR_VALUE,CACHE_SIZE,INCREAMENT_BY,CLIENT_CACHE_SIZE FROM PUB_GID_SERVER WHERE IS_USE = 1 ";
		List<GidServerVo> list = this.jdbcTemplate.query(sql, new RowMapper<GidServerVo>() {
			@Override
			public GidServerVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				GidServerVo vo = new GidServerVo();
				vo.setGidCode(rs.getString("GID_CODE"));
				vo.setGidName(rs.getString("GID_NAME"));
				vo.setIsUse(rs.getInt("IS_USE"));
				vo.setCurrValue(rs.getLong("CURR_VALUE"));
				vo.setCacheSize(rs.getInt("CACHE_SIZE"));
				vo.setIncreamentBy(rs.getInt("INCREAMENT_BY"));
				vo.setClientCacheSize(rs.getInt("CLIENT_CACHE_SIZE"));
				return vo;
			}
		});
		return list;
	}
	
	/**
	 * 
	 * @date 2017年9月30日 下午3:04:49
	 * 
	 * @Description: 查询所有的数据，包括有效无效的
	 * @return
	 *
	 */
	public List<GidServerVo> selectAll() {
		String sql = "SELECT GID_CODE,GID_NAME,IS_USE,CURR_VALUE,CACHE_SIZE,INCREAMENT_BY,CLIENT_CACHE_SIZE FROM PUB_GID_SERVER ";
		List<GidServerVo> list = this.jdbcTemplate.query(sql, new RowMapper<GidServerVo>() {
			@Override
			public GidServerVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				GidServerVo vo = new GidServerVo();
				vo.setGidCode(rs.getString("GID_CODE"));
				vo.setGidName(rs.getString("GID_NAME"));
				vo.setIsUse(rs.getInt("IS_USE"));
				vo.setCurrValue(rs.getLong("CURR_VALUE"));
				vo.setCacheSize(rs.getInt("CACHE_SIZE"));
				vo.setIncreamentBy(rs.getInt("INCREAMENT_BY"));
				vo.setClientCacheSize(rs.getInt("CLIENT_CACHE_SIZE"));
				return vo;
			}
		});
		return list;
	}
	
	/**
	 * 根据编码列表查询
	 * @date 2015年12月5日 下午4:36:23
	 * @author lzj
	 * @Description: 根据编码列表查询
	 * @return
	 *
	 */
	public List<GidServerVo> selectByCodes(List<String> gidCodeList){
		
		String[] gidCodes = (String[])gidCodeList.toArray(new String[gidCodeList.size()]);
		
		StringBuffer sql = new StringBuffer("SELECT GID_CODE,GID_NAME,IS_USE,CURR_VALUE,CACHE_SIZE,INCREAMENT_BY,CLIENT_CACHE_SIZE"
				+ " FROM PUB_GID_SERVER WHERE GID_CODE IN (");
		
		int length = gidCodes.length;
		String[] params = new String[length];
		for(int i=0;i<length;i++){
			params[i] = "?";
		}
		sql.append(StringUtils.join(params)).append(")");
		
		String sqlStr = sql.toString();
		
		if(logger.isDebugEnabled()){
			logger.debug("selectByCodes sql:{},gidCodes:{}",sqlStr, JSON_UTILS.objectToJson(gidCodes));
		}

		List<GidServerVo> list = this.jdbcTemplate.query(sqlStr, gidCodes, new RowMapper<GidServerVo>() {  
            @Override  
            public GidServerVo mapRow(ResultSet rs, int rowNum) throws SQLException {  
            	GidServerVo vo = new GidServerVo();
				vo.setGidCode(rs.getString("GID_CODE"));
				vo.setGidName(rs.getString("GID_NAME"));
				vo.setIsUse(rs.getInt("IS_USE"));
				vo.setCurrValue(rs.getLong("CURR_VALUE"));
				vo.setCacheSize(rs.getInt("CACHE_SIZE"));
				vo.setIncreamentBy(rs.getInt("INCREAMENT_BY"));
				vo.setClientCacheSize(rs.getInt("CLIENT_CACHE_SIZE"));
				return vo;
            }  
        });
		
		return list;
	}
	
	/**
	 * 根据gidCode查询
	 * @date 2015年12月10日 下午3:24:06
	 * @author lzj
	 * @Description: 根据gidCode查询
	 * @param gidCode
	 * @return
	 *
	 */
	public GidServerVo selectByCode(String gidCode){
		
		String sqlStr = "SELECT GID_CODE,GID_NAME,IS_USE,CURR_VALUE,CACHE_SIZE,INCREAMENT_BY,CLIENT_CACHE_SIZE FROM PUB_GID_SERVER WHERE GID_CODE =?";

		GidServerVo gidVo = this.jdbcTemplate.queryForObject(sqlStr, new Object[]{gidCode}, new RowMapper<GidServerVo>() {  
            @Override  
            public GidServerVo mapRow(ResultSet rs, int rowNum) throws SQLException {  
            	GidServerVo vo = new GidServerVo();
				vo.setGidCode(rs.getString("GID_CODE"));
				vo.setGidName(rs.getString("GID_NAME"));
				vo.setIsUse(rs.getInt("IS_USE"));
				vo.setCurrValue(rs.getLong("CURR_VALUE"));
				vo.setCacheSize(rs.getInt("CACHE_SIZE"));
				vo.setIncreamentBy(rs.getInt("INCREAMENT_BY"));
				vo.setClientCacheSize(rs.getInt("CLIENT_CACHE_SIZE"));
				return vo;
            }  
        });
		
		return gidVo;
	}
	
	
	
	
	/**
	 * 更新--乐观锁
	 * @date 2015年12月5日 下午4:55:23
	 * @author lzj
	 * @Description: 更新--乐观锁
	 * @param currValue
	 * @param gidCode
	 *
	 */
	public int updateCurrValue(Long targetValue,Long currValue,String gidCode ){
		logger.debug("updateCurrValue targetValue:{},currValue:{},gidCode:{}",targetValue,currValue,gidCode);
		String sql = "UPDATE PUB_GID_SERVER SET CURR_VALUE = ? WHERE GID_CODE = ? AND CURR_VALUE = ?";
		int count = this.jdbcTemplate.update(sql, targetValue,gidCode,currValue);
		return count;
	}
}
