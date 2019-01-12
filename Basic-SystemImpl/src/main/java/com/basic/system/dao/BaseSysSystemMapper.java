package com.basic.system.dao;
import com.basic.system.model.BaseSysSystem;
import java.util.List;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.api.baseMapper.Mapper;

/**
 * 
 * <pre> 
 * 描述：外围系统管理 DAO接口
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:12:03
 * 版权：companyName
 * </pre>
 */
public interface BaseSysSystemMapper extends Mapper<Long, BaseSysSystem> {

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

    @Override
    List<BaseSysSystem> query(DefaultQueryFilter queryFilter);

    @Override
	BaseSysSystem getUnique(String column, Object value);

    @Override
    List<BaseSysSystem> getAll();

    /**
     * 根据系统编码获取唯一系统
     * @param systemCode
     * @return
     */
	BaseSysSystem getBySyscode(String systemCode);

}
