package com.basic.framework.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basic.framework.web.api.GidService;
import com.basic.framework.web.api.UserDetailService;
import com.basic.framework.web.api.VerifyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.model.DateNow;
import com.basic.framework.web.model.VerifyUniqueCode;
import com.basic.oaas.model.UserDetail;

/**
 * 
 *
 * @date 2017年6月28日 下午4:56:27
 * 
 * @Description: 前端页面获取后台数据的公共方法
 *
 */
@Controller
@RequestMapping(value = CommonController.REQUEST_PATH)
public class CommonController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	protected static final String REQUEST_PATH = "/common";
	
//	@Autowired
	private UserDetailService userDetailService;
//	@Autowired
	private GidService gidService;
	
	private VerifyService verifyService;
	
	public void setUserDetailService(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	public void setGidService(GidService gidService) {
		this.gidService = gidService;
	}

	public void setVerifyService(VerifyService verifyService) {
		this.verifyService = verifyService;
	}

	/**
	 * 
	 * @date 2017年6月28日 下午5:07:00
	 * 
	 * @Description: 查询账号的详细信息
	 * @param userId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/user/qryUserDetail")
	@ResponseBody
	public String qryUserDetail(Long userId,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("request parameter qryUserDetail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		String json = null;
		DealResult dealResult = new DealResult();
		UserDetail userDetail = null;
		if(userId != null){
			Long companyId = (Long) request.getSession().getAttribute("companyId");
			if(companyId != null){
				userDetail = userDetailService.qryUserDetail(userId,companyId);
			}else{
				userDetail = userDetailService.qryUserDetail(userId,null);
				if(userDetail.getCompany()!=null){
					request.getSession().setAttribute("companyId",userDetail.getCompany().getCompanyId());
				}
				
				
			}
			dealResult.setReturnCode(DealResult.QRYSUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(userDetail);

		}
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		if(logger.isDebugEnabled()){
			logger.debug("return json qryUserDetail getParameterMap:{}", json);
		}
		
		return json;
	}
	
	/**
	 * 
	 * @date 2017年6月28日 下午5:20:01
	 * 
	 * @Description: 根据gidCode获取gid
	 * @param gidCode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/gid/getGidValue")
	@ResponseBody
	public String getGidValue(String gidCode,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("request parameter getGidValue getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		String json = null;
		DealResult dealResult = new DealResult();
		Map<String, Long> gidMap = new HashMap<>();
		Long gidValue = null;
		
		if(StringUtils.isNotBlank(gidCode)){
			gidValue = gidService.getGidValue(gidCode);
		}
		
		gidMap.put("gidValue", gidValue);
		
		dealResult.setReturnCode(DealResult.QRYSUCCESS);
		dealResult.setReturnMsg(null);
		dealResult.setReturnData(gidMap);
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		if(logger.isDebugEnabled()){
			logger.debug("return json getGidValue getParameterMap:{}", json);
		}
		
		return json;
	}
	
	/**
	 * 
	 * @date 2017年6月28日 下午5:50:41
	 * 
	 * @Description: 获取系统当前时间
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/date/getNow")
	@ResponseBody
	public String getNow(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("request parameter getNow getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		String json = null;
		DealResult dealResult = new DealResult();
		
		DateNow dateNow=new DateNow();
		Date now = DateUtils.now();
		String dateStr = DateUtils.defaultFormatDate(now);
		String[] str = dateStr.split(" ");
		dateNow.setNow(now);
		dateNow.setMillisecond(now.getTime());
		dateNow.setYmd(str[0]);
		dateNow.setHms(str[1]);
		
		dealResult.setReturnCode(DealResult.QRYSUCCESS);
		dealResult.setReturnMsg(null);
		dealResult.setReturnData(dateNow);
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		if(logger.isDebugEnabled()){
			logger.debug("return json getNow getParameterMap:{}", json);
		}
		
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月22日 下午5:25:14
	 * 
	 * @Description: 验证某个表字段的值是否表内唯一
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/verify/verifyUniqueCode")
	@ResponseBody
	public String verifyUniqueCode(VerifyUniqueCode ibean, HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("request parameter verifyUniqueCode getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		String json = null;
		
		String colValue = null;
		@SuppressWarnings("rawtypes")
		Map map = request.getParameterMap();
		@SuppressWarnings("rawtypes")
		Set keys = map.keySet();
        for(Object key: keys.toArray()){
        	if(!"tableCode".equals(key) && !"colCode".equals(key)){
        		colValue =  ((String[])map.get(key))[0];
			}
        }
        ibean.setColValue(colValue);
		
		int count = verifyService.verifyUniqueCode(ibean);
		json = "true";
		if(count != 0){
			json = "false";
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("return json verifyUniqueCode getParameterMap:{}", json);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/funcPage/user/switchOrg")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  detail getParameterMap:{}",
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		this.doBeforeFuncPageAction(request, response, model, null);

		// 业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/switchOrgModalWin");

		// 业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);

		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年8月22日 下午5:25:14
	 * 
	 * @Description: 验证某个表字段的值是否表内唯一
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/switchCompany")
	@ResponseBody
	public String switchCompany(Long companyId,Long userId,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("request parameter qryUserDetail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		String json = null;
		DealResult dealResult = new DealResult();
		UserDetail userDetail = null;
		request.getSession().setAttribute("companyId",companyId);
		if(userId != null){
			if(companyId != null){
				userDetail = userDetailService.qryUserDetail(userId,companyId);
			}
			dealResult.setReturnCode(DealResult.QRYSUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(userDetail);

		}
		json = JSON_UTILS.objectToJson(dealResult);
		if(logger.isDebugEnabled()){
			logger.debug("return json qryUserDetail getParameterMap:{}", json);
		}
		return json;
	}
	
	
	


	
	
}
