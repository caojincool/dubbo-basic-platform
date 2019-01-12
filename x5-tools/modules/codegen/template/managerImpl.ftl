<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign system=vars.system>
<#assign comment=model.tabComment>
<#assign subtables=model.subTableList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign pkType=func.getPkType(model)>
<#assign fkType=func.getFkType(model)>
package com.${system}.${package}.service;


import com.${system}.${package}.dao.${class}Mapper;
import com.${system}.${package}.model.${class};
import com.${system}.${package}.service.${class}Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * <pre> 
 * @Description：${comment} 实现类
 <#if vars.developer?exists>
 * @author:${vars.developer}
 * @email:${vars.email}
 </#if>
 * @date:${date?string("yyyy-MM-dd HH:mm:ss")}
 * @copyright：${vars.company}
 * </pre>
 */
@Service("${classVar}Service")
public class ${class}ServiceImpl implements ${class}Service{

   private static final Logger logger = LoggerFactory.getLogger(${class}ServiceImpl.class);

	@Autowired
	${class}Mapper ${classVar}Mapper;





}
