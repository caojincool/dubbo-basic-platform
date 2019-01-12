package com.basic.system.service;

import com.basic.system.model.BaseSysSysLog;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import java.util.List;
/**
 * 
 * <pre> 
 * 描述：记录与外围系统接口调用的日志 处理接口
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:12:03
 * 版权：companyName
 * </pre>
 */
public interface BaseSysSysLogService extends BaseServer<Long, BaseSysSysLog>{
	 @Override
    void create(BaseSysSysLog entity);

    @Override
    void update(BaseSysSysLog entity);

    @Override
    void remove(Long entityId);

    @Override
    BaseSysSysLog get(Long entityId);

    @Override
    List<BaseSysSysLog> getByIds(List<Long> entityIds, String idColumn);

    @Override
    void removeByIds(Long... ids);


    PageJson queryPage(DefaultQueryFilter queryFilter);

    @Override
    List<BaseSysSysLog> getAll();

    /**
     * 批量插入调用日志
     * @param sysThridLogList
     */
	void insertAll(List<BaseSysSysLog> sysThridLogList);
}
