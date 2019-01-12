package com.basic.oaas.dao;
import com.basic.oaas.model.PrivateAttr;
import java.util.List;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.api.baseMapper.Mapper;

/**
 * 
 * <pre> 
 * 描述：字段权限配置 DAO接口
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:08:26
 * 版权：companyName
 * </pre>
 */
public interface PrivateAttrMapper extends Mapper<Long, PrivateAttr> {

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

    @Override
    List<PrivateAttr> query(DefaultQueryFilter queryFilter);

    @Override
	PrivateAttr getUnique(String column, Object value);

    @Override
    List<PrivateAttr> getAll();

}
