package com.basic.oaas.service;

import com.basic.oaas.model.PrivateAttr;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import java.util.List;
/**
 * 
 * <pre> 
 * 描述：字段权限配置 处理接口
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:08:26
 * 版权：companyName
 * </pre>
 */
public interface PrivateAttrService extends BaseServer<Long, PrivateAttr>{
	 @Override
    void create(PrivateAttr entity);

    @Override
    void update(PrivateAttr entity);

    @Override
    void remove(Long entityId);

    @Override
    PrivateAttr get(Long entityId);

    @Override
    List<PrivateAttr> getByIds(List<Long> entityIds, String idColumn);

    @Override
    void removeByIds(Long... ids);


    PageJson queryPage(DefaultQueryFilter queryFilter);

    @Override
    List<PrivateAttr> getAll();
}
