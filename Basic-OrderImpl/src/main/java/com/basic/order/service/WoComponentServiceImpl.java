package com.basic.order.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.OrderComponetIbean;
import com.basic.order.dao.WoComponentMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.GidCodes;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.WoComponent;

/**
 * 
 *
 * @date 2017年9月1日 下午5:02:42
 * @author wangkui
 * @Description: 实现类
 *
 */
@Service("woComponentService")
public class WoComponentServiceImpl implements WoComponentService{

	private Logger logger = LoggerFactory.getLogger(WoComponentServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private WoComponentMapper woComponentMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoComponentService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long componentId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey componentId:{}", componentId);
		}
		return woComponentMapper.deleteByPrimaryKey(componentId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoComponentService#createSelective(com.basic.order.model.WoComponent)
	 */
	@Override
	public int createSelective(WoComponent record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective WoComponent:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_WO_COMPONENT_SEQ);
		record.setComponentId(gid);
		return woComponentMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoComponentService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public WoComponent qryByPrimaryKey(Long componentId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey componentId:{}", componentId);
		}
		return woComponentMapper.selectByPrimaryKey(componentId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoComponentService#modifyByPrimaryKeySelective(com.basic.order.model.WoComponent)
	 */
	@Override
	public int modifyByPrimaryKeySelective(WoComponent record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective WoComponent:{}", JSON_UTILS.objectToJson(record));
		}
		return woComponentMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoComponentService#qryByTacheId(java.lang.Long)
	 */
	@Override
	public List<WoComponent> qryByTacheId(Long tacheId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByTacheId tacheId:{}", tacheId);
		}
		List<WoComponent> list = null;
		if(tacheId != null){
			list = woComponentMapper.selectByTacheId(tacheId);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#createOrModify(com.basic.order.model.OrderPriority)
	 */
	@Override
	public void createOrModify(WoComponent ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify OrderPriority:{}", JSON_UTILS.objectToJson(ibean));
		}
//		Date now = DateUtils.now();
//		if(StringUtils.isBlank(ibean.getOrderPriority())){//新增
		if(PageDateTypeEnum.CREATE.getCode().equals(ibean.getPageDateType())){//新增
			ibean.setComponentState(BasicStateEnum.NORMAL.getCode());
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}	
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoComponentService#qryComponentList(com.basic.order.bean.OrderComponetIbean)
	 */
	@Override
	public List<WoComponent> qryComponentList(OrderComponetIbean ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("qryComponentList OrderComponetIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return woComponentMapper.selectComponentList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoComponentService#qryComponentListCount(com.basic.order.bean.OrderComponetIbean)
	 */
	@Override
	public long qryComponentListCount(OrderComponetIbean ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("qryComponentListCount OrderComponetIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return woComponentMapper.selectComponentListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoComponentService#modifyBatchStateByCompoentId(com.basic.order.model.WoComponent)
	 */
	@Override
	public int modifyBatchStateByCompoentId(WoComponent ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("modifyBatchStateByCompoentId WoComponent:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getComponentState()) 
				&& ibean.getComponentIds().length >0 ){
			count = woComponentMapper.updateBatchStateByCompoentId(ibean);
		}
		return count;
	}

}
