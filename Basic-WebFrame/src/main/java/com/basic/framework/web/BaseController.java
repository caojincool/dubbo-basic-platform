/**
 * 
 */
package com.basic.framework.web;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.DefaultFieldSort;
import com.basic.framework.common.api.query.Direction;
import com.basic.framework.common.api.query.FieldLogic;
import com.basic.framework.common.api.query.FieldRelation;
import com.basic.framework.common.api.query.FieldSort;
import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.model.DefaultFieldLogic;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.mybatis.support.DefaultPage;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.StringUtil;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.api.UserDetailService;
import com.basic.framework.web.api.UserPrivateService;
import com.basic.oaas.model.UserDetail;

//import com.basic.oaas.service.UserPrivateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author lzj 所有控制器的基类
 */
public abstract class BaseController implements ApplicationContextAware{

	
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
//	@Autowired
//	private UserSessionService userSessionService;
//	@Autowired
//	private OaasPrivateMenuService oaasPrivateMenuService;
//	@Autowired
//	private OaasPrivateFuncService oaasPrivateFuncService;
//	@Autowired
//	private OaasRoleService oaasRoleService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
//	private UserPrivateService userPrivateService;
//
//	public void setUserPrivateService(UserPrivateService userPrivateService) {
//		this.userPrivateService = userPrivateService;
//	}
	
//	@Autowired
//	private UserDetailService userDetailService;
	

	public static String DATE_FORMET = "yyyy-MM-dd HH:mm:ss";
//	public static GsonBuilder GSON_BUILDER = new GsonBuilder().setDateFormat(DATE_FORMET);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	
	/**
	 * 
	 * @date 2015年12月23日 下午12:48:31
	 * @author 杰
	 * @Description: (指定时间类型编辑器)
	 * @param binder
	 *
	 */
	@InitBinder    
    public void initBinder(WebDataBinder binder) {  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true)); 
     }
	
	/**
	 * 在控制器之前调用，暂时无用，用于后续扩展
	 * 用于菜单页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param paramObj
	 */
	protected void doBeforeMenuPageAction(HttpServletRequest request, HttpServletResponse response, Model model,
			Object paramObj) {

	}

	/**
	 * 暂时无用，用于后续扩展
	 * 用于菜单页面
	 * @param request
	 * @param response
	 * @param model
	 * @param paramObj
	 */
	protected void doAfterMenuPageAction(HttpServletRequest request, HttpServletResponse response, Model model,
			Object paramObj) {
		
		this.appendMenuData(request, model);
		
	}

	/**
	 * 在控制器之前调用，暂时无用，用于后续扩展
	 * 用于功能
	 * @param request
	 * @param response
	 * @param model
	 * @param paramObj
	 */
	protected void doBeforeFuncAction(HttpServletRequest request, HttpServletResponse response, Model model,
			Object paramObj) {

	}

	/**
	 * 暂时无用，用于后续扩展
	 * 用于功能
	 * @param request
	 * @param response
	 * @param model
	 * @param paramObj
	 */
	protected void doAfterFuncAction(HttpServletRequest request, HttpServletResponse response, Model model,
			Object paramObj) {

		
	}

	/**
	 * 在控制器之前调用，暂时无用，用于后续扩展
	 * 用于功能页面或模态窗口
	 * @param request
	 * @param response
	 * @param model
	 * @param paramObj
	 */
	protected void doBeforeFuncPageAction(HttpServletRequest request, HttpServletResponse response, Model model,
			Object paramObj) {

	}

	/**
	 * 暂时无用，用于后续扩展
	 * 用于功能页面或模态窗口
	 * @param request
	 * @param response
	 * @param model
	 * @param paramObj
	 */
	protected void doAfterFuncPageAction(HttpServletRequest request, HttpServletResponse response, Model model,
			Object paramObj) {

		
	}

	/**
	 * 从请求对象中获取知道名称的参数值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	protected String getParameterFromRequest(HttpServletRequest request, String name) {
		if (logger.isDebugEnabled()) {
			logger.debug("getParameterFromRequest name:{}", name);
		}
		String value = null;
		if (request.getParameter(name) != null) {
			value = request.getParameter(name).toString();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getParameterFromRequest name:{},value:{}", name, value);
		}

		return value;
	}
	
	/**
	 * 将结果集转为dataGrid使用的json
	 * @param page 当前第几页
	 * @param total 一共多少页
	 * @param records 一共多少条记录 
	 * @param dataList 数据
	 * @return
	 */
	protected String toDataGridJson(Integer page, Integer total, Integer records, List<?> dataList) {
//		Gson gson = new GsonBuilder().setDateFormat(DATE_FORMET).serializeNulls().create(); // 值为空时name:
		// ""，默认是没有这个name

		StringBuilder jsonStr = new StringBuilder("{");
		jsonStr.append("\"page\": ");
		jsonStr.append(page == null ? 1: page);
		jsonStr.append(", \"total\": ");
		jsonStr.append(total == null ? 1: total);
		jsonStr.append(", \"records\": ");
		jsonStr.append(records == null ? -1: records);
		jsonStr.append(", \"root\": ");
//		JsonUtils.toJsonString(obj)
		jsonStr.append(JSON_UTILS.objectToJson(dataList));
		jsonStr.append("}");

		return jsonStr.toString();

	}
	
	/**
	 * 获取session
	 * @param request
	 * @param
	 * @param
	 * @return
	 */
	protected UserDetail getSession(HttpServletRequest request) {

		UserDetailService userDetailService = applicationContext.getBean("userDetailService", UserDetailService.class);
		//UserDetail userDetail = userDetailService.qryUserDetailByRequest(request);
		return null;

//		logger.debug("getSession sessionKey:{}",SecurityCommon.SESSION_USER_KEY);
//		HttpSession session = request.getSession();
//
//		//userSession的信息在用户登录成功后获取并加入HttpSession
//		Object sessionObj = session.getAttribute(SecurityCommon.SESSION_USER_KEY);
//		if(logger.isDebugEnabled()){
//			logger.debug("getSession sessionKey:{},sessionObj:{}",SecurityCommon.SESSION_USER_KEY,JSON_UTILS.objectToJson(sessionObj));
//		}
//		if(null == sessionObj){//这种情况应该是免登录的情况
//
//			logger.debug("重新设置自定义session");
//
//			SecurityContext springSecurityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
//			SessionUser sessionUser = null;
//			if(springSecurityContext != null){
//				MyUserDetail  userDetail  = (MyUserDetail) springSecurityContext.getAuthentication().getPrincipal();
//				OaasUser user  = userDetail.getUser();
//				sessionUser = userSessionService.getUserSession(user.getUserId());
//				request.getSession().setAttribute(SecurityCommon.SESSION_USER_KEY,sessionUser);
//			}
//
//			return sessionUser;
//		}else{
//			return (SessionUser)sessionObj;
//		}


	}

	
//	/**
//	 * 将结果集转为dataGrid使用的json
//	 * @param page 当前第几页
//	 * @param total 一共多少页
//	 * @param records 一共多少条记录 
//	 * @param dataList 数据
//	 * @return
//	 */
//	protected String toDataGridTreeJson(Integer records, List<?> dataList) {
////		Gson gson = new GsonBuilder().setDateFormat(DATE_FORMET).serializeNulls().create(); // 值为空时name:
//		// ""，默认是没有这个name
//
//		StringBuilder jsonStr = new StringBuilder("{");
//		jsonStr.append("\"page\": ");
//		jsonStr.append(1);
//		jsonStr.append(", \"total\": ");
//		jsonStr.append(1);
//		jsonStr.append(", \"records\": ");
//		jsonStr.append(dataList.size());
//		jsonStr.append(", \"root\": ");
////		JsonUtils.toJsonString(obj)
//		jsonStr.append(JsonUtils.toJsonString(dataList));
//		jsonStr.append("}");
//
//		return jsonStr.toString();
//
//	}

	
	/**
	 * 从session中获取用户得到其所有权限信息，然后构建sidebar
	 * 
	 * @param model
	 * @param
	 */
	protected void appendMenuData(HttpServletRequest request, Model model) {
	  logger.debug("拼装菜单");
	//	UserPrivateService userPrivateService = applicationContext.getBean("userPrivateServiceWeb", UserPrivateService.class);
	//	userPrivateService.appendUserMenuData(request, model);
//		String currentPageUrl = request.getRequestURI();
//		this.appendMenuData(currentPageUrl, request, model);
		
//		logger.debug("拼装功能权限");
//		this.appendFuncData(currentPageUrl, request, model);
	}

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	/**
	 * 
	 * @date 2017年10月23日 上午9:46:07
	 * @author jie
	 * @Description: 返回页面的json封装
	 * @param returnCode
	 * @param returnMsg
	 * @param returnData
	 * @return
	 *
	 */
	protected String toPageJson(String returnCode, String returnMsg, Object returnData) {
		String json = null;
		DealResult dealResult = new DealResult();
		dealResult.setReturnCode(returnCode);
		dealResult.setReturnMsg(returnMsg);
		dealResult.setReturnData(returnData);
		json = JSON_UTILS.objectToJson(dealResult);
		return json;
	}

	/**
	 * 返回构建的QueryFilter
	 *
	 * @param jsonObject
	 * @return QueryFilter
	 * @exception
	 * @since 1.0.0
	 */




	protected DefaultQueryFilter getQueryFilter(JSONObject jsonObject){
		DefaultQueryFilter queryFilter = new DefaultQueryFilter();
		try{
			String defaultWhere= jsonObject.getString("defaultWhere");
			if(StringUtils.isNotEmpty(defaultWhere)) {
                queryFilter.addParamsFilter("defaultWhere", defaultWhere);
            }

			String pageSize = jsonObject.getString("page");
			// 页大小
			String rows = jsonObject.getString("rows");
			// 设置查询字段
//			FieldLogic andFieldLogic = RequestUtils.getFieldLogic(request);
			  Map<String,String>  map = new HashMap<>( );
			FieldLogic andFieldLogic = getFieldLogic( map, FieldRelation.AND);
//			queryFilter.setFieldLogic(null);
			// 设置分页
			if (StringUtils.isNotEmpty(pageSize) && StringUtils.isNotEmpty(rows)){
				DefaultPage page = new DefaultPage();
				page.setPage(Integer.parseInt(pageSize));
				page.setLimit(Integer.parseInt(rows));
				queryFilter.setPage(page);
			}else {
				queryFilter.setPage(null);
			}
			// 设置排序字段
			String sort = jsonObject.getString("sort");
			//sort = ConvertSortName(request, sort);
			String order = jsonObject.getString("order");
			if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(order)){
				List<FieldSort> fieldSorts = new ArrayList<FieldSort>();
				fieldSorts.add(new DefaultFieldSort(sort, Direction.fromString(order)));
				queryFilter.setFieldSortList(fieldSorts);
			}
		} catch (Exception e)
		{
		}
		return queryFilter;
	}
	/**
	 * 根据一串逗号分隔的长整型字符串取得长整形数组
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static Long[] getLongAryByStr(JSONObject jsonObject, String key) {
		String str = jsonObject.getJSONArray(key).toJSONString();
		if (StringUtil.isEmpty(str)) {
            return null;
        }
		str = str.replace("[", "");
		str = str.replace("]", "");
		String[] aryId = str.split(",");
		Long[] lAryId = new Long[aryId.length];
		for (int i = 0; i < aryId.length; i++) {
			lAryId[i] = Long.parseLong(aryId[i]);
		}
		return lAryId;
	}


	/**
	 * 数组转字符串
	 * @return
	 */
	public static String getStrByArray(Object[] objects) {  
		StringBuffer stringBuffer =new StringBuffer("");
		 if ( null != objects && objects.length > 0){
			for (Object o: objects){
				stringBuffer.append(o+",");
			}
		 }
		 if ( stringBuffer.length() > 0){
		 	return stringBuffer.substring(0,stringBuffer.length()-1);
		 }else{
			 return stringBuffer.toString();
		 }

	}


	/**
	 * 根据一串逗号分隔的字符串取得字符串形数组
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static String[] getStringAryByStr( JSONObject jsonObject,  String key) {
		String str = jsonObject.getString(key);
		if (StringUtil.isEmpty(str)) {
            return null;
        }
		str = str.replace("[", "");
		str = str.replace("]", "");
		String[] aryId = str.split(",");
		String[] lAryId = new String[aryId.length];
		for (int i = 0; i < aryId.length; i++) {
			lAryId[i] = (aryId[i].trim());
		}
		return lAryId;
	}

	/**
	 * 通过Param参数Map构建查询条件
	 * <pre>
	 * 	1.参数字段命名规则。
	 * 		Q^参数名称^参数类型
	 * 	2.在这里构建的逻辑都是and逻辑。
	 *
	 *  参数类型说明。
	 *
	 * 	S:字符串
	 * 	L:长整形
	 *  N:整形
	 *  BD:BigDecimal
	 *  FT:float
	 *  SN:short
	 *  DL:date begin
	 *  DG:date end
	 *  SL:字符串 模糊查询
	 *  SLL:字符串 左模糊查询
	 *  SLR:字符串 右模糊查询
	 * </pre>
	 * @param paramMap
	 * @param fieldRelation
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static FieldLogic getFieldLogic(Map paramMap, FieldRelation fieldRelation){
		if(BeanUtils.isEmpty(fieldRelation)){
			fieldRelation = FieldRelation.AND;
		}
		FieldLogic andFieldLogic=new DefaultFieldLogic(fieldRelation);
		Set keySet = paramMap.keySet();
		for (Object keyObj : keySet) {
			String key = keyObj.toString();
			if(!key.startsWith("Q^")) {
                continue;
            }

			Object valObj = paramMap.get(keyObj);
			if(BeanUtils.isEmpty(valObj)) {
                continue;
            }
			String value = valObj.toString();
			//如果object为数组，则取数组的值
			if(valObj.getClass().isArray()){
				String[] values = (String[]) valObj;
				value = values[0];
			}
			String[] aryParaKey = key.split("\\^");
			if (aryParaKey.length != 3&&aryParaKey.length != 2) {
                continue;
            }

			String paraName =aryParaKey.length==2?key.substring(2):key.substring(2, key.lastIndexOf("^"));// key.substring(2, key.lastIndexOf("^"));

			//Q^authorizeDesc则=aryParaKey.length== 2 为了自己能够在XML中动态构造SQL
			String type =aryParaKey.length== 2?"S": key.substring(key.lastIndexOf("^") + 1);
			//对日期的特殊处理，防止参数中Key名称一致 带有 ^DG的参数名修改为还 xxx^DG
			if("DG".equals(type)) {
                paraName=key.substring(2);
            }
			//使结束时间的查询字符串日期加1，且为日期类型
			if(aryParaKey.length== 2&&paraName.indexOf("_DG")>-1)
			{
				type=key.substring(key.lastIndexOf("_") + 1);
			}
//			andFieldLogic.getWhereClauses().add(new DefaultQueryField(paraName, getCompare(type), getObjValue(type,value)));

		}
		return andFieldLogic;
	}

}
