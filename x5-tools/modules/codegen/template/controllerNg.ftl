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
package com.creatson.${system}.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.base.api.model.ResultMessage;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.core.util.string.StringUtil;
import com.hotent.base.core.web.GenericController;
import com.hotent.base.core.web.RequestUtil;
import com.hotent.base.db.id.UniqueIdUtil;
import com.hotent.base.db.mybatis.domain.PageJson;
import com.hotent.base.db.mybatis.domain.PageList;
import com.creatson.${system}.persistence.manager.${class}Manager;
import com.creatson.${system}.persistence.model.${class};
import java.util.Date;
import com.hotent.sys.util.ContextUtil;
import java.io.IOException;

/**
 * 
 * <pre> 
 * 描述：${comment} 控制器类
 * 构建组：x5-bpmx-platform
 <#if vars.developer?exists>
 * 作者:${vars.developer}
 * 邮箱:${vars.email}
 </#if>
 * 日期:${date?string("yyyy-MM-dd HH:mm:ss")}
 * 版权：${vars.company}
 * </pre>
 */
@Controller
@RequestMapping("/${system}/${package}/${classVar}")
public class ${class}Controller extends GenericController{
	@Resource
	${class}Manager ${classVar}Manager;
	
	/**
	 * ${comment}列表(分页条件查询)数据
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@RequestMapping("listJson")
	public @ResponseBody PageJson listJson(HttpServletRequest request,HttpServletResponse reponse){
		QueryFilter queryFilter=getQueryFilter(request);
		PageList<${class}> ${classVar}List=(PageList<${class}>)${classVar}Manager.query(queryFilter);
		return new PageJson(${classVar}List);
	}
	
	
	
	/**
	 * ${comment}明细页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@RequestMapping("getJson")
	public @ResponseBody ${class} getJson(HttpServletRequest request,HttpServletResponse response) throws Exception{
		${pkType} id=RequestUtil.get${pkType}(request, "${pkVar}");
		if(StringUtil.isEmpty(id)){
			return new ${class}();
		}
		return ${classVar}Manager.get(id);
	}
	
	/**
	 * 保存${comment}信息
	 * @param request
	 * @param response
	 * @param ${classVar}
	 * @throws IOException 
	 * void
	 * @exception 
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request,HttpServletResponse response,@RequestBody ${class} ${classVar}) throws IOException{
		String resultMsg=null;
		${pkType} ${pkVar}=${classVar}.get${pkVar?cap_first}();
		try {
			${classVar}.setUpdateTime(new Date());
			${classVar}.setUpdateBy(ContextUtil.getCurrentUserId());
			if(StringUtil.isEmpty(${pkVar})){
				${classVar}.setId(UniqueIdUtil.getSuid());
				${classVar}.setCreateTime(new Date());
				${classVar}.setCreateBy(ContextUtil.getCurrentUserId());
				${classVar}.setIsDelete(0);
				${classVar}Manager.create(${classVar});
				resultMsg="添加${comment}成功";
			}else{
				${classVar}Manager.update(${classVar});
				resultMsg="更新${comment}成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.SUCCESS);
		} catch (Exception e) {
			resultMsg="对${comment}操作失败";
			writeResultMessage(response.getWriter(),resultMsg,e.getMessage(),ResultMessage.FAIL);
		}
	}
	
	/**
	 * 批量删除${comment}记录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * void
	 * @exception 
	 */
	@RequestMapping("remove")
	public void remove(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultMessage message=null;
		try {
			${pkType}[] aryIds=RequestUtil.get${pkType}AryByStr(request, "${pkVar}");
			${classVar}Manager.removeByIds(aryIds);
			message=new ResultMessage(ResultMessage.SUCCESS, "删除${comment}成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.FAIL, "删除${comment}失败");
		}
		writeResultMessage(response.getWriter(), message);
	}
}
