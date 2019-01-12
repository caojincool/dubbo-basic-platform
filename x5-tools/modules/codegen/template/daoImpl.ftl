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
package com.creatson.${system}.persistence.dao.impl;

<#if sub?exists && sub>
import java.util.HashMap;
import java.util.List;
import java.util.Map;
</#if>
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.creatson.${system}.persistence.dao.${class}Dao;
import com.creatson.${system}.persistence.model.${class};

/**
 * 
 * <pre> 
 * @Description：${comment} DAO实现类
 <#if vars.developer?exists>
 * @author:${vars.developer}
 * @email:${vars.email}
 </#if>
 * @date:${date?string("yyyy-MM-dd HH:mm:ss")}
 * @copyright：${vars.company}
 * </pre>
 */
@Repository
public class ${class}DaoImpl extends MyBatisDaoImpl<${pkType}, ${class}> implements ${class}Dao{

    @Override
    public String getNamespace() {
        return ${class}.class.getName();
    }
<#if sub?exists && sub>
	/**
	 * 根据外键获取子表明细列表
	 * @param ${foreignKey}
	 * @return
	 */
	public List<${class}> getByMainId(${fkType} ${foreignKey}) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("${foreignKey}", ${foreignKey});
		return this.getByKey("get${class}List", params);
	}
	
	/**
	 * 根据外键删除子表记录
	 * @param ${foreignKey}
	 * @return
	 */
	public void delByMainId(${fkType} ${foreignKey}) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("${foreignKey}", ${foreignKey});
		this.deleteByKey("delByMainId", params);
	}
	
</#if>	
	
}

