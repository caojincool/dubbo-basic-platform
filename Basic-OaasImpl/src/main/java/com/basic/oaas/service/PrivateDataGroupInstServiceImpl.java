/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.PrivateDataGroupInstIbean;
import com.basic.oaas.dao.PrivateDataGroupInstMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateDataGroupInst;
import com.basic.oaas.model.PrivateDataInstData;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据权限分组接口
 * 
 */
@Service("privateDataGroupInstService")
public class PrivateDataGroupInstServiceImpl extends BaseServerImpl<Long, PrivateDataGroupInst> implements PrivateDataGroupInstService {

	private Logger logger = LoggerFactory.getLogger(PrivateDataGroupInstServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateDataGroupInstMapper privateDataGroupInstMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupInstService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long dataGrpInstId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey dataGrpInstId:{}", dataGrpInstId);
		}
		
		return privateDataGroupInstMapper.deleteByPrimaryKey(dataGrpInstId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupInstService#createSelective(com.basic.oaas.model.PrivateDataGroupInst)
	 */
	@Override
	public int createSelective(PrivateDataGroupInstIbean record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		
		CopyOnWriteArrayList<PrivateDataGroupInst> list = new CopyOnWriteArrayList<PrivateDataGroupInst>(record.getPrivateDataGroupInstDatas());
		if (BeanUtils.isNotEmpty(list)) {
			for ( PrivateDataGroupInst inst : list) {
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("dataGroupId", inst.getDataGroupId());
				params.put("dataInstId", inst.getDataInstId());
				if (privateDataGroupInstMapper.checkExist(params)==0) {
					Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_DATA_GRP_INST_SEQ);
					inst.setDataGrpInstId(gid);
				}else {
					list.remove(inst);
				}
			}
			if (BeanUtils.isNotEmpty(list)) {
				return privateDataGroupInstMapper.insertBatchDataInstData(list);
			}
		}
		return 0;
		
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupInstService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateDataGroupInst qryByPrimaryKey(Long dataGrpInstId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey dataGrpInstId:{}", JSON_UTILS.objectToJson(dataGrpInstId));
		}
		
		return privateDataGroupInstMapper.selectByPrimaryKey(dataGrpInstId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupInstService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateDataGroupInst)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateDataGroupInst record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		return privateDataGroupInstMapper.updateByPrimaryKey(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupInstService#removeBatchByDataGrpInstId(java.lang.Long[])
	 */
	@Override
	public int removeBatchByDataGrpInstId(Long[] dataGrpInstIds) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByDataGrpInstId dataGrpInstIds:{}", JSON_UTILS.objectToJson(dataGrpInstIds));
		}
		return privateDataGroupInstMapper.deleteBatchByDataGrpInstId(dataGrpInstIds);
	}

	@Override
	@Deprecated
	public void create(PrivateDataGroupInst entity) {
	}

	@Override
	public void update(PrivateDataGroupInst entity) {
		modifyByPrimaryKeySelective(entity);
	}

	@Override
	public String getNamespace() {
		return PrivateDataGroupInstMapper.class.getName();
	}
	
	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<PrivateDataGroupInst> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}
}
