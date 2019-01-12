
<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign comment=model.tabComment>
<#assign system=vars.system>
<#assign subtables=model.subTableList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign pkType=func.getPkType(model)>
<#assign fkType=func.getFkType(model)>
package com.${system}.${package}.model;
<#if subtables?exists && subtables?size!=0>
import java.util.ArrayList;
import java.util.List;
</#if>

import java.math.BigDecimal;
import java.util.Date;
import org.lsmy.cloud.common.bean.BaseBean;

/**
 * 
 * <pre> 
 * @Description：${comment}实体对象
 <#if vars.developer?exists>
 * @author:${vars.developer}
 * @email:${vars.email}
 </#if>
 * @date:${date?string("yyyy-MM-dd HH:mm:ss")}
 * @copyright：${vars.company}
 * </pre>
 */
public class ${class} extends BaseBean{

    <#list model.columnList as col>
	/**
	* ${col.comment}
	*/
	<#if (col.colType=="Double")>
	protected BigDecimal ${func.convertUnderLine(col.columnName)};
	<#else>
	protected ${col.colType} ${func.convertUnderLine(col.columnName)};
	</#if>


	</#list>
	
	<#if subtables?exists && subtables?size!=0>
	<#list subtables as table>
	<#assign vars=table.variables>
	/**
	*${table.tabComment}列表
	*/
	protected List<${vars.class}> ${vars.classVar}List=new ArrayList<${vars.class}>(); 
	</#list>
	</#if>

<#list model.columnList as col>

	<#assign colName=func.convertUnderLine(col.columnName)>

	<#if (col.colType=="Double")>
	public void set${colName?cap_first}(BigDecimal ${colName}) {
		this.${colName} = ${colName};
	}

	/**
	 * 返回 ${col.comment}
	 * @return
	 */
	public BigDecimal get${colName?cap_first}() {
		return this.${colName};
	}

	<#else>
	public void set${colName?cap_first}(${col.colType} ${colName}) {
		this.${colName} = ${colName};
	}

	/**
	 * 返回 ${col.comment}
	 * @return
	 */
	public ${col.colType} get${colName?cap_first}() {
		return this.${colName};
	}
	</#if>



</#list>
<#if subtables?exists && subtables?size!=0>
<#list subtables as table>
<#assign vars=table.variables>

	public void set${vars.classVar?cap_first}List(List<${vars.class}> ${vars.classVar}List) {
		this.${vars.classVar}List = ${vars.classVar}List;
	}
	
	/**
	 * 返回 ${table.tabComment}列表
	 * @return
	 */
	public List<${vars.class}> get${vars.classVar?cap_first}List() {
		return this.${vars.classVar}List;
	}
</#list>
</#if>
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		<#list model.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		.append("${colName}", this.${colName}) 
		</#list>
		.toString();
	}
}