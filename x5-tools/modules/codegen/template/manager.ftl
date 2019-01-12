<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign comment=model.tabComment>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign system=vars.system>
<#assign pkType=func.getPkType(model)>
package com.${system}.${package}.service;

import com.${system}.${package}.model.${class};

/**
 * 
 * <pre> 
 * @Description：${comment} 接口
 <#if vars.developer?exists>
 * @author:${vars.developer}
 * @email:${vars.email}
 </#if>
 * @date:${date?string("yyyy-MM-dd HH:mm:ss")}
 * @copyright：${vars.company}
 * </pre>
 */
public interface ${class}Service{

}
