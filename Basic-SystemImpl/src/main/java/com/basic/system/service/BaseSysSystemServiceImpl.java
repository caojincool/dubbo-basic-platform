package com.basic.system.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.system.dao.BaseSysSystemMapper;
import com.basic.system.model.BaseSysSystem;
import com.basic.system.service.BaseSysSystemService;
import java.util.List;
/**
 * 
 * <pre> 
 * 描述：外围系统管理 处理实现类
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 16:48:51
 * 版权：companyName
 * </pre>
 */
@Service("baseSysSystemService")
public class BaseSysSystemServiceImpl extends BaseServerImpl<Long, BaseSysSystem> implements BaseSysSystemService{
	@Resource
	BaseSysSystemMapper baseSysSystemMapper;

 	@Override
    public String getNamespace() {
        return BaseSysSystemMapper.class.getName();
    }

	/**
	 * 创建实体包含子表实体
	 */
	@Override
    public void create(BaseSysSystem baseSysSystem){
		Long gid = GidClientUtils.getInstance().getGidValue(com.basic.system.define.GidCodes.BASE_SYS_SYSTEM);
		baseSysSystem.setSysId( gid );
		baseSysSystemMapper.create(baseSysSystem);
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
    public List<BaseSysSystem> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}


	/**
	 * 获取实体
	 */
    @Override
    public BaseSysSystem get(Long entityId){
		BaseSysSystem baseSysSystem=baseSysSystemMapper.get(entityId);
    	return baseSysSystem;
    }

    /**
     * 更新实体同时更新子表记录
     */
    @Override
    public void update(BaseSysSystem baseSysSystem){
		baseSysSystemMapper.update(baseSysSystem);
	}
		
	@Override
	public BaseSysSystem getUnique(String column, Object value) {
		return super.getUniqueByColumn(column, value);

    }
	
	
	@Override
	public BaseSysSystem getBySyscode(String systemCode) {
		return baseSysSystemMapper.getBySyscode(systemCode);
	}


}
