package com.basic.system.service;

import com.basic.system.model.BaseSysSystem;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import java.util.List;
/**
 * 
 * <pre> 
 * 描述：外围系统管理 处理接口
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:12:03
 * 版权：companyName
 * </pre>
 */
public interface BaseSysSystemService extends BaseServer<Long, BaseSysSystem>{
	 @Override
    void create(BaseSysSystem entity);

    @Override
    void update(BaseSysSystem entity);

    @Override
    void remove(Long entityId);

    @Override
    BaseSysSystem get(Long entityId);

    @Override
    List<BaseSysSystem> getByIds(List<Long> entityIds, String idColumn);

    @Override
    void removeByIds(Long... ids);


    PageJson queryPage(DefaultQueryFilter queryFilter);

    @Override
    List<BaseSysSystem> getAll();

    /**
     * 根据系统编码获取系统
     * @param systemCode
     * @return
     */
	BaseSysSystem getBySyscode(String systemCode);
}
