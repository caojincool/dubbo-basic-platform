package com.basic.oaas.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.dao.PrivateAttrMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.define.PrivateType;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.Private;
import com.basic.oaas.model.PrivateAttr;
import com.basic.oaas.service.PrivateAttrService;

import java.util.Date;
import java.util.List;
/**
 * 
 * <pre> 
 * 描述：字段权限配置 处理实现类
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:08:26
 * 版权：companyName
 * </pre>
 */
@Service("privateAttrService")
public class PrivateAttrServiceImpl extends BaseServerImpl<Long, PrivateAttr> implements PrivateAttrService{
	@Resource
	PrivateAttrMapper privateAttrMapper;
	@Autowired
	private PrivateService privateService;

 	@Override
    public String getNamespace() {
        return PrivateAttrMapper.class.getName();
    }

	/**
	 * 创建实体包含子表实体
	 */
 	@Override
	public void create(PrivateAttr privateAttr){

		Private ibean = new Private();
		ibean.setPrivateName(privateAttr.getAttrName());
		ibean.setPrivateCode(privateAttr.getAttrCode());
		ibean.setPrivateType(PrivateType.ATTR.getCode());
		ibean.setCreateTime(new Date());
		ibean.setCreateUserId(privateAttr.getCreateUserId());
		ibean.setState("10A");
		ibean.setRemarks(privateAttr.getRemarks());
		privateService.createSelective(ibean);
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_ATTR_SEQ);
		privateAttr.setAttrId(gid);
		privateAttr.setCreateTime(DateUtils.now());
		privateAttr.setPrivateId(ibean.getPrivateId());
		if (privateAttr.getOpenStatus()==null || privateAttr.getOpenStatus() == 1) {
			privateAttr.setOpenStatus(1);
			privateAttr.setOpenDate(new Date());
		}else if(privateAttr.getOpenStatus() == 0) {
			privateAttr.setStopDate(new Date());
		}
		
		
		privateAttrMapper.create(privateAttr);
    }

	/**
	 * 删除记录包含子表记录
	 */
 	@Override
	public void remove(Long entityId){
		super.remove(entityId);
	}

	/**
	 * 批量删除包含子表记录
	 */
	@Override
	public void removeByIds(Long[] entityIds){
		for(Long id:entityIds){
			this.remove(id);
		}
		privateService.removeBatchByAttrIds(entityIds);
	}

	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<PrivateAttr> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}


	/**
	 * 获取实体
	 */
	@Override
    public PrivateAttr get(Long entityId){
    	PrivateAttr privateAttr=privateAttrMapper.get(entityId);
    	return privateAttr;
    }

    /**
     * 更新实体同时更新子表记录
     */
	@Override
    public void update(PrivateAttr privateAttr){
    	privateAttr.setModifyTime(new Date());
    	PrivateAttr db = privateAttrMapper.get(privateAttr.getAttrId());
		if (db!=null) {
			//与数据库不一致才修改
			if ((privateAttr.getOpenStatus()==null || privateAttr.getOpenStatus() == 1)
					&& db.getOpenStatus()==0) {
				privateAttr.setOpenStatus(1);
				privateAttr.setOpenDate(new Date());
			}else if(privateAttr.getOpenStatus()!=null
					&& privateAttr.getOpenStatus() == 0 
					&& db.getOpenStatus()==1) {
				privateAttr.setStopDate(new Date());
			}
		}
		
		Private priv = privateService.qryByPrimaryKey(privateAttr.getPrivateId());
		if (priv!=null) {
			priv.setModifyUserId(privateAttr.getModifyUserId());
			priv.setPrivateName(privateAttr.getAttrName());
			privateService.modifyByPrimaryKeySelective(priv);
		}
		
		privateAttrMapper.update(privateAttr);
		
	}


}
