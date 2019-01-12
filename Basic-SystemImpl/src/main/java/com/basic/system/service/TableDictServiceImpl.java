package com.basic.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.system.bean.TableDictIbean;
import com.basic.system.dao.TableDictMapper;
import com.basic.system.define.GidCodes;
import com.basic.system.model.TableDict;

/**
 * 
 *
 * @date 2017年8月2日 下午2:37:52
 * 
 * @Description: 字典表
 *
 */
@Service("tableDictService")
public class TableDictServiceImpl implements TableDictService{

	private Logger logger = LoggerFactory.getLogger(TableDictServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private TableDictMapper tableDictMapper;

	/* (non-Javadoc)
	 * @see com.basic.system.service.TableDictService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long dictId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey dictId:{}", dictId);
		}
		return tableDictMapper.deleteByPrimaryKey(dictId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.TableDictService#createSelective(com.basic.system.model.TableDict)
	 */
	@Override
	public int createSelective(TableDict record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective TableDict:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PUB_TABLE_DICT_SEQ);
		record.setDictId(gid);
		
		return tableDictMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.TableDictService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public TableDict qryByPrimaryKey(Long dictId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey dictId:{}", dictId);
		}

		return tableDictMapper.selectByPrimaryKey(dictId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.TableDictService#modifyByPrimaryKeySelective(com.basic.system.model.TableDict)
	 */
	@Override
	public int modifyByPrimaryKeySelective(TableDict record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective TableDict:{}", JSON_UTILS.objectToJson(record));
		}
		
		return tableDictMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.TableDictService#qryTableDictList(com.basic.system.bean.TableDictIbean)
	 */
	@Override
	public List<TableDict> qryTableDictList(TableDictIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryTableDictList TableDictIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<TableDict> list = tableDictMapper.selectTableDictList(ibean);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.TableDictService#qryTableDictListCount(com.basic.system.bean.TableDictIbean)
	 */
	@Override
	public long qryTableDictListCount(TableDictIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryTableDictListCount TableDictIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		long count = tableDictMapper.selectTableDictListCount(ibean);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.TableDictService#createOrModify(com.basic.system.model.TableDict)
	 */
	@Override
	public void createOrModify(TableDict ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify TableDict:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getDictId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.TableDictService#removeBatchByDictIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByDictIds(Long[] dictIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByDictIds dictIds:{}", JSON_UTILS.objectToJson(dictIds));
		}
		
		int count = 0;
		if(dictIds != null && dictIds.length > 0){
			count = tableDictMapper.deleteBatchByDictIds(dictIds);
		}
		return count;
	}

}
