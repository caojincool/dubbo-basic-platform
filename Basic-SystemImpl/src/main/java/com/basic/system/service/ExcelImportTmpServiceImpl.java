package com.basic.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.system.bean.ExcelImportTmpIBean;
import com.basic.system.dao.ExcelImportTmpMapper;
import com.basic.system.model.ExcelImportTmp;


/**
 * 
 *
 * @date 2017年7月3日 下午5:27:25
 * 
 * @Description: excel上传模板
 *
 */
@Service("excelImportTmpService")
public class ExcelImportTmpServiceImpl implements ExcelImportTmpService {

	private Logger logger = LoggerFactory.getLogger(ExcelImportTmpServiceImpl.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private ExcelImportTmpMapper excelImportTmpMapper;

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelImportTmpService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(String templateCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey templateCode:{}", templateCode);
		}

		return excelImportTmpMapper.deleteByPrimaryKey(templateCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelImportTmpService#createSelective(com.basic.system.model.ExcelImportTmp)
	 */
	@Override
	public int createSelective(ExcelImportTmp record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective ExcelImportTmp:{}", JSON_UTILS.objectToJson(record));
		}

		return excelImportTmpMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelImportTmpService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public ExcelImportTmp qryByPrimaryKey(String templateCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey templateCode:{}", templateCode);
		}

		return excelImportTmpMapper.selectByPrimaryKey(templateCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelImportTmpService#modifyByPrimaryKeySelective(com.basic.system.model.ExcelImportTmp)
	 */
	@Override
	public int modifyByPrimaryKeySelective(ExcelImportTmp record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective ExcelImportTmp:{}", JSON_UTILS.objectToJson(record));
		}
		
		return excelImportTmpMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelImportTmpService#qryExcelImportTmpList(com.basic.system.bean.ExcelImportTmpIBean)
	 */
	@Override
	public List<ExcelImportTmp> qryExcelImportTmpList(ExcelImportTmpIBean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryExcelImportTmpList ExcelImportTmpIBean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<ExcelImportTmp> list = excelImportTmpMapper.selectExcelImportTmpList(ibean);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelImportTmpService#qryExcelImportTmpListCount(com.basic.system.bean.ExcelImportTmpIBean)
	 */
	@Override
	public long qryExcelImportTmpListCount(ExcelImportTmpIBean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryExcelImportTmpListCount ExcelImportTmpIBean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		long count = excelImportTmpMapper.selectExcelImportTmpListCount(ibean);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelImportTmpService#createOrModify(com.basic.system.model.ExcelImportTmp)
	 */
	@Override
	public void createOrModify(ExcelImportTmp ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createOrModify ExcelImportTmp:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if("CREATE".equals(ibean.getPageDateType())){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}
	
}
