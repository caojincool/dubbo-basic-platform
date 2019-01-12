package com.basic.system.service;

import com.basic.system.model.BaseSysInterface;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import java.util.List;
/**
 * 
 * <pre> 
 * 描述：外围系统接口 处理接口
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:12:03
 * 版权：companyName
 * </pre>
 */
public interface BaseSysInterfaceService extends BaseServer<Long, BaseSysInterface>{
	 @Override
    void create(BaseSysInterface entity);

    @Override
    void update(BaseSysInterface entity);

    @Override
    void remove(Long entityId);

    @Override
    BaseSysInterface get(Long entityId);

    @Override
    List<BaseSysInterface> getByIds(List<Long> entityIds, String idColumn);

    @Override
    void removeByIds(Long... ids);


    PageJson queryPage(DefaultQueryFilter queryFilter);

    @Override
    List<BaseSysInterface> getAll();

    /**
     * 根据类型和系统编码获取接口
     * @param type
     * @param systemCode
     * @return
     */
	List<BaseSysInterface> getByTypeAndeCode(String type, String systemCode);
}
