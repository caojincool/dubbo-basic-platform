package com.basic.system.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.system.dao.BaseSysInterfaceMapper;
import com.basic.system.model.BaseSysInterface;
import com.basic.system.service.BaseSysInterfaceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * <pre> 
 * 描述：外围系统接口 处理实现类
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 16:48:51
 * 版权：companyName
 * </pre>
 */
@Service("baseSysInterfaceService")
public class BaseSysInterfaceServiceImpl extends BaseServerImpl<Long, BaseSysInterface> implements BaseSysInterfaceService{
	@Resource
	BaseSysInterfaceMapper baseSysInterfaceMapper;

 	@Override
    public String getNamespace() {
        return BaseSysInterfaceMapper.class.getName();
    }

	/**
	 * 创建实体包含子表实体
	 */
	@Override
    public void create(BaseSysInterface baseSysInterface){
		Long gid = GidClientUtils.getInstance().getGidValue(com.basic.system.define.GidCodes.BASE_SYS_INTERFACE);
		baseSysInterface.setSysInterfaceId( gid );
		baseSysInterfaceMapper.create(baseSysInterface);
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
	}

 	@Override
    public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        QueryFilter queryFilter1 = queryFilter;
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<BaseSysInterface> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}


	/**
	 * 获取实体
	 */
    @Override
    public BaseSysInterface get(Long entityId){
		BaseSysInterface baseSysInterface=baseSysInterfaceMapper.get(entityId);
    	return baseSysInterface;
    }

    /**
     * 更新实体同时更新子表记录
     */
    @Override
    public void update(BaseSysInterface baseSysInterface){
		baseSysInterfaceMapper.update(baseSysInterface);
	}
		
	@Override
	public BaseSysInterface getUnique(String column, Object value) {
		return super.getUniqueByColumn(column, value);

    }
	
	@Override
	public List<BaseSysInterface>  getByTypeAndeCode(String type,String systemCode){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sysCode", systemCode);
		params.put("type", type);
		return baseSysInterfaceMapper.getByTypeAndCode(params);
	}

}
