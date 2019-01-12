package com.basic.oaas.controller;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.Area;
import com.basic.oaas.model.User;
import com.basic.oaas.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.StringUtil;
import java.util.Collections;
import java.util.List;

/**
 *
 * <pre>
 * 描述：区域控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-21 17:19:12
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/area/")
public class AreaController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	AreaService areaService;
	@Autowired
	UserUtils userUtils;

	
	/**
     * 获取区域树
     * @param jsonObject
     * @return
     * @throws Exception
     * HttpEntity
     * @exception
     */
    @RequestMapping("queryTree")
    @ResponseBody
    public HttpEntity queryTree(@RequestBody JSONObject jsonObject){
	    try{
	    	DefaultQueryFilter queryFilter = new DefaultQueryFilter();
	    	String parentId = jsonObject.getString("parentId");
	    	if (StringUtils.isNotEmpty(parentId)) {
				queryFilter.addFilter("AREA.PARENT_AREA_ID", parentId, QueryOP.EQUAL);
			}
			List<Area> list  =  areaService.query(queryFilter);
			if (BeanUtils.isNotEmpty(list)) {
				return new HttpEntity(HttpStatus.OK,true,"请求成功",BeanUtils.listToTree(list));
			}else {
				return new HttpEntity(HttpStatus.OK,true,"请求成功",Collections.EMPTY_LIST);
			}
	    }catch (Exception e){
	    	return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
    	}
    }
    
	/**
	 * 区域列表(分页条件查询)数据
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("queryPage")
	@ResponseBody
    public HttpEntity listPageJson(@RequestBody JSONObject jsonObject){
        DefaultQueryFilter queryFilter =getQueryFilter(jsonObject);
        try{
			PageJson pageJson  =  areaService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 区域列表数据(不分页)
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("queryList")
	@ResponseBody
	public HttpEntity listJson(@RequestBody JSONObject jsonObject){
		DefaultQueryFilter queryFilter =getQueryFilter(jsonObject);
		try{
			List<Area> list = areaService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 区域明细页面
	 * @param  jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 * @since  1.0.0
	 */
	@RequestMapping("get")
	@ResponseBody
	public HttpEntity get(@RequestBody JSONObject jsonObject){
		Area area=null;
 		try{
			Long areaId=jsonObject.getLong("id");
			area=areaService.qryAreaById(areaId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",area);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 区域路径信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		Area area = JSONObject.toJavaObject(jsonObject,Area.class);
		String resultMsg=null;
		try {
			Assert.notNull(area, "区域对象不能为空");
			Assert.notNull(area.getAreaAbsName(), "区域简称不能为空");
			Assert.notNull(area.getAreaName(), "区域名称不能为空");
			if (StringUtil.isEmpty(area.getParentAreaId())) {
				area.setParentAreaId(-1L);
			}
			User currentUser = userUtils.getUser();
			if (currentUser!=null) {
				if (BeanUtils.isEmpty(area.getAreaId())) {
					area.setCreateUserId(currentUser.getUserId());
				}else {
					area.setModifyUserId(currentUser.getUserId());
				}
			}
			area = areaService.createOrModify(area);
			resultMsg="保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,area);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除区域记录
	 * @param jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("remove")
	@ResponseBody
	public HttpEntity remove(@RequestBody JSONObject jsonObject){
		String resultMsg=null;
		try {
			Long[] aryIds = getLongAryByStr(jsonObject, "ids");
			if (BeanUtils.isNotEmpty(aryIds)) {
				for (int i = 0; i < aryIds.length; i++) {
					areaService.removeByPrimarykey(aryIds[i]);
				}
			}
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
