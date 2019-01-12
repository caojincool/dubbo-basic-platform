<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign comment=model.tabComment>
<#assign system=vars.system>
<#assign sub=model.sub>
<#assign foreignKey=func.convertUnderLine(model.foreignKey)>
<#assign pkType=func.getPkType(model)>
<#assign fkType=func.getFkType(model)>
package com.${system}.${package}.dao;
import com.${system}.${package}.model.${class};
import java.util.List;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.api.baseMapper.Mapper;

/**
 * 
 * <pre> 
 * @Description：${comment} DAO接口
 <#if vars.developer?exists>
 * @author:${vars.developer}
 * @email:${vars.email}
 </#if>
 * @date:${date?string("yyyy-MM-dd HH:mm:ss")}
 * @copyright：${vars.company}
 * </pre>
 */
public interface ${class}Mapper{

	/**
	 * 新增
	 */
    void insert(${class} entity);

	/**
	 * 更新
	 */
    void update(${class} entity);


	/**
	 * 删除
	 */
    void remove(${pkType} entityId);

	/**
	 * 获取单个对象
	 */
	${class} get(${pkType} entityId);


   /**
	 * 条件查询
	 */
    public List<${class}> getList(PersonWwSearchVO searchVo);




}
