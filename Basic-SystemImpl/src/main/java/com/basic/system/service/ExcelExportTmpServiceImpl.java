package com.basic.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.system.bean.ExcelExportTmpIBean;
import com.basic.system.dao.ExcelExportTmpMapper;
import com.basic.system.model.ExcelExportTmp;


/**
 * 
 *
 * @date 2017年7月3日 下午5:27:25
 * 
 * @Description: excel导出模板
 *
 */
@Service("excelExportTmpService")
public class ExcelExportTmpServiceImpl implements ExcelExportTmpService {

	private Logger logger = LoggerFactory.getLogger(ExcelExportTmpServiceImpl.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private ExcelExportTmpMapper excelExportTmpMapper;

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelExportTmpService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(String templateCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey templateCode:{}", templateCode);
		}

		return excelExportTmpMapper.deleteByPrimaryKey(templateCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelExportTmpService#createSelective(com.basic.system.model.ExcelExportTmp)
	 */
	@Override
	public int createSelective(ExcelExportTmp record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective ExcelExportTmp:{}", JSON_UTILS.objectToJson(record));
		}

		return excelExportTmpMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelExportTmpService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public ExcelExportTmp qryByPrimaryKey(String templateCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey templateCode:{}", templateCode);
		}

		return excelExportTmpMapper.selectByPrimaryKey(templateCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelExportTmpService#modifyByPrimaryKeySelective(com.basic.system.model.ExcelExportTmp)
	 */
	@Override
	public int modifyByPrimaryKeySelective(ExcelExportTmp record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective ExcelExportTmp:{}", JSON_UTILS.objectToJson(record));
		}
		
		return excelExportTmpMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelExportTmpService#qryExcelExportTmpList(com.basic.system.bean.ExcelExportTmpIBean)
	 */
	@Override
	public List<ExcelExportTmp> qryExcelExportTmpList(ExcelExportTmpIBean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryExcelExportTmpList ExcelExportTmpIBean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<ExcelExportTmp> list = excelExportTmpMapper.selectExcelExportTmpList(ibean);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelExportTmpService#qryExcelExportTmpListCount(com.basic.system.bean.ExcelExportTmpIBean)
	 */
	@Override
	public long qryExcelExportTmpListCount(ExcelExportTmpIBean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryExcelExportTmpListCount ExcelExportTmpIBean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		long count = excelExportTmpMapper.selectExcelExportTmpListCount(ibean);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.ExcelExportTmpService#createOrModify(com.basic.system.model.ExcelExportTmp)
	 */
	@Override
	public void createOrModify(ExcelExportTmp ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createOrModify ExcelExportTmp:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if("CREATE".equals(ibean.getPageDateType())){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
		
	}
	
}
