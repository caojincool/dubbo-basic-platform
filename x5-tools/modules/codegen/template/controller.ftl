<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign comment=model.tabComment>
<#assign subtables=model.subTableList>
<#assign classVar=model.variables.classVar>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign pkType=func.getPkType(model)>
package com.${system}.${package}.controller;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSON;

import org.lsmy.cloud.common.util.web.ThreadLocalUtil;
import org.lsmy.cloud.common.web.WebResult;
import com.${system}.${package}.model.${class};
import com.${system}.${package}.service.${class}Service;

/**
 * 
 * <pre> 
 * @Description：${comment} 控制器类
 <#if vars.developer?exists>
 * @author:${vars.developer}
 * @email:${vars.email}
 </#if>
 * @date:${date?string("yyyy-MM-dd HH:mm:ss")}
 * @copyright：${vars.company}
 * </pre>
 */
@Order
@Path("/api/${system}")
@Controller
@Consumes(
{ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces(
{ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class ${class}Controller {

    private static final Logger logger = LoggerFactory.getLogger(${class}Controller.class);

	@Autowired
	${class}Service ${classVar}Service;


    @POST
	@Path("/demo")
	@OperInfo(description = "描述信息", operationClassName = ${class}Controller.class)
    public  WebResult  list(@RequestBody ${class} ${classVar}){
        WebResult result = new WebResult();
        result.setResult(true);
        try{
			 result.setContent("");
	 	}catch (Exception e){
    		 result.setResult(false);
             result.setMsg("系统异常");
		}
         return result;

	}
	

}
