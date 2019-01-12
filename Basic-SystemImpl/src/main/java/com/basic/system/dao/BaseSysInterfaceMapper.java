package com.basic.system.dao;
import com.basic.system.model.BaseSysInterface;
import java.util.List;
import java.util.Map;

import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.api.baseMapper.Mapper;

/**
 * 
 * <pre> 
 * 描述：外围系统接口 DAO接口
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:12:03
 * 版权：companyName
 * </pre>
 */
public interface BaseSysInterfaceMapper extends Mapper<Long, BaseSysInterface> {

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

    @Override
    List<BaseSysInterface> query(DefaultQueryFilter queryFilter);

    @Override
	BaseSysInterface getUnique(String column, Object value);

    @Override
    List<BaseSysInterface> getAll();

    /**
     * 根据类型和系统编码获取接口
     * @param type
     * @param systemCode
     * @return
     */
	List<BaseSysInterface> getByTypeAndCode(Map<String,Object> params);

}
