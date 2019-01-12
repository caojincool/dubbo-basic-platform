package com.basic.system.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.system.dao.BaseSysSysLogMapper;
import com.basic.system.model.BaseSysSysLog;
import com.basic.system.service.BaseSysSysLogService;
import java.util.List;
/**
 * 
 * <pre> 
 * 描述：记录与外围系统接口调用的日志 处理实现类
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 16:48:51
 * 版权：companyName
 * </pre>
 */
@Service("baseSysSysLogService")
public class BaseSysSysLogServiceImpl extends BaseServerImpl<Long, BaseSysSysLog> implements BaseSysSysLogService{
	@Resource
	BaseSysSysLogMapper baseSysSysLogMapper;

 	@Override
    public String getNamespace() {
        return BaseSysSysLogMapper.class.getName();
    }

	/**
	 * 创建实体包含子表实体
	 */
	@Override
    public void create(BaseSysSysLog baseSysSysLog){
		Long gid = GidClientUtils.getInstance().getGidValue(com.basic.system.define.GidCodes.BASE_SYS_SYSLOG);
		baseSysSysLog.setLogId(gid);
		baseSysSysLogMapper.create(baseSysSysLog);
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
    public List<BaseSysSysLog> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}


	/**
	 * 获取实体
	 */
    @Override
    public BaseSysSysLog get(Long entityId){
		BaseSysSysLog baseSysSysLog=baseSysSysLogMapper.get(entityId);
    	return baseSysSysLog;
    }

    /**
     * 更新实体同时更新子表记录
     */
    @Override
    public void update(BaseSysSysLog baseSysSysLog){
		baseSysSysLogMapper.update(baseSysSysLog);
	}
		
	@Override
	public BaseSysSysLog getUnique(String column, Object value) {
		return super.getUniqueByColumn(column, value);

    }
	
	@Override
	public void insertAll(List<BaseSysSysLog> sysThridLogList){
		baseSysSysLogMapper.insertAll(sysThridLogList);
	}


}
