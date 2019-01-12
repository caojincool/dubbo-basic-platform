/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据范围类型接口实现
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.oaas.dao.DataScopeTypeMapper;
import com.basic.oaas.model.DataScopeType;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据范围类型接口
 * 
 */
@Service("dataScopeTypeService")
public class DataScopeTypeServiceImpl implements DataScopeTypeService{

	private Logger logger = LoggerFactory.getLogger(DataScopeTypeServiceImpl.class);
	
	private static JsonUtils jsonUtils = JsonUtils.getInstance();
	
	@Autowired
	private DataScopeTypeMapper dataScopeTypeMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.DataScopeTypeService#removeByPrimaryKey(java.lang.String)
	 */
	@Override
	public int removeByPrimaryKey(String scopeType) {
		if(logger.isDebugEnabled()){
			logger.debug("removeByPrimaryKey scopeType:{}",scopeType);
		}
		return dataScopeTypeMapper.deleteByPrimaryKey(scopeType);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.DataScopeTypeService#createSelective(com.basic.oaas.model.DataScopeType)
	 */
	@Override
	public int createSelective(DataScopeType record) {
		if(logger.isDebugEnabled()){
			logger.debug("createSelective record:{}",jsonUtils.objectToJson(record));
		}
		return dataScopeTypeMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.DataScopeTypeService#qryByPrimaryKey(java.lang.String)
	 */
	@Override
	public DataScopeType qryByPrimaryKey(String scopeType) {
		if(logger.isDebugEnabled()){
			logger.debug("qryByPrimaryKey scopeType:{}",jsonUtils.objectToJson(scopeType));
		}
		return dataScopeTypeMapper.selectByPrimaryKey(scopeType);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.DataScopeTypeService#modifyByPrimaryKeySelective(com.basic.oaas.model.DataScopeType)
	 */
	@Override
	public int modifyByPrimaryKeySelective(DataScopeType record) {
		if(logger.isDebugEnabled()){
			logger.debug("modifyByPrimaryKeySelective record:{}",jsonUtils.objectToJson(record));
		}
		return dataScopeTypeMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.DataScopeTypeService#qryAllScopeTypeList()
	 */
	@Override
	public List<DataScopeType> qryAllScopeTypeList() {
		if(logger.isDebugEnabled()){
			logger.debug("qryAllScopeTypeList :{}");
		}
		return dataScopeTypeMapper.selectAllTypeList();
	}
	
    
}
