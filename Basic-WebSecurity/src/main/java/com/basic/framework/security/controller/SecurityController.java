//package com.basic.framework.security.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.session.SessionInformation;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.basic.framework.common.utils.datatype.JsonUtils;
//import com.basic.framework.security.model.OnlineUser;
//import com.basic.framework.security.model.SessionUser;
//import com.basic.framework.security.service.RedisSessionUser;
//import com.basic.framework.web.BaseController;
//
///**
// * 
// *
// * @date 2017年6月22日 下午6:08:50
// * 
// * @Description: 操作在线用户
// *
// */
//@Controller
//@RequestMapping(value = SecurityController.REQUEST_PATH)
//public class SecurityController extends BaseController {
//
//	protected static final String REQUEST_PATH = "/security";
//
//	private static Logger logger = LoggerFactory.getLogger(SecurityController.class);
//	private static final JsonUtils jsonUtils = JsonUtils.getInstance();
//	
//	@Autowired    
//	private SessionRegistry sessionRegistry;
//	@Autowired
//	private RedisSessionUser redisSessionUser;
//	
//
//	/**
//	 * 
//	 * @date 2017年6月22日 下午6:09:06
//	 * 
//	 * @Description: 在线用户页面
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 *
//	 */
//	@RequestMapping(value = "/menuPage/onlineUser")
//	public ModelAndView onlineUser(HttpServletRequest request,HttpServletResponse response, Model model) {
//				
//		if(logger.isDebugEnabled()){
//			logger.debug("请求参数  demoPage getParameterMap:{}", jsonUtils.objectToJson(request.getParameterMap()));
//		}
//		
//		this.doBeforeMenuPageAction(request, response, model, null);
//		
//		//业务逻辑开始
//		ModelAndView pageView = new ModelAndView("/security/onlineUser");
//		
//		//业务逻辑结束
//		this.doAfterMenuPageAction(request, response, model, null);
//		
//		return pageView;
//	}
//
//	/**
//	 * 
//	 * @date 2017年6月22日 下午6:09:38
//	 * 
//	 * @Description: 查询在线用户
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 *
//	 */
//	@RequestMapping(value = "/func/qryOnlineUser", produces = { "application/json;charset=UTF-8" })
//	@ResponseBody
//	public String qryOnlineUser(HttpServletRequest request,HttpServletResponse response, Model model) {
//				
//		if(logger.isDebugEnabled()){
//			logger.debug("请求参数  onlineUser getParameterMap:{}",jsonUtils.objectToJson(request.getParameterMap()));
//		}
//		
//		this.doBeforeFuncAction(request, response, model, null);
//		String json = null;
//		
//		
//		List<Object> slist = sessionRegistry.getAllPrincipals();
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        StringBuffer retVal=new StringBuffer("[");  
////        int k=0;  
////        for(int i=0;i<slist.size();i++){  
////            List<SessionInformation> sessionList = sessionRegistry.getAllSessions(slist.get(i),true);   
////            User user=(User)slist.get(i);  
////            for(SessionInformation t:sessionList){  
////                if(k!=0){  
////                    retVal.append(",");  
////                }  
////                retVal.append("{\"id\":\""+k+"\",\"userAccount\":\""+user.getUsername()+"\",\"sessionId\":\""+t.getSessionId()+"\",\"lastRequest\":\""+sdf.format(t.getLastRequest())+"\"}");  
////                k=k+1;  
////            }  
////        } 
////		retVal.append("]");
////		
////		StringBuilder jsonStr = new StringBuilder("{");
////		jsonStr.append("\"page\": ");
////		jsonStr.append(1);
////		jsonStr.append(", \"total\": ");
////		jsonStr.append(1);
////		jsonStr.append(", \"records\": ");
////		jsonStr.append(-1);
////		jsonStr.append(", \"root\": ");
////		jsonStr.append(retVal);
////		jsonStr.append("}");
////
////		json = jsonStr.toString();
//		
//		
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		WebAuthenticationDetails wauth = (WebAuthenticationDetails) auth.getDetails();
////		System.out.println("当前登录用户的ip：" + wauth.getRemoteAddress());
////		System.out.println("当前登录用户的sessionID：" + wauth.getSessionId()); //Principal可转换为User//这个id是服务端的sessionid，不可取
//				
//		List<OnlineUser> onlineUsers = new ArrayList<OnlineUser>();
//		for (int i = 0; i < slist.size(); i++) {
//			//第二个参数表示是否取单点登录时，超出限制而被弹出用户
//			List<SessionInformation> sessionList = sessionRegistry.getAllSessions(slist.get(i), true);//多处登录
//			User user = (User) slist.get(i);
//			for (SessionInformation t : sessionList) {
//				OnlineUser onlineUser = new OnlineUser();
//				onlineUser.setUserAccount(user.getUsername());
//				onlineUser.setSessionId(t.getSessionId());
//				onlineUser.setLastAccessedTime(t.getLastRequest());
//				onlineUser.setIsExpired(t.isExpired());
//				//强制退出1.将其限制，2.将其移除，使用第一种比较好
//				//sif.expireNow();
//				//sessionRegistry.removeSessionInformation(t.getSessionId());
//
//				onlineUsers.add(onlineUser);
//			}
//		}	
//		
///*		//改成从redis里面获取
//		List<SessionUser> sessionUsers = redisSessionUser.getAllSessions();*/
//		
//		Integer page = null;
//		Integer total = null;
//		Integer records = null;
//		json = this.toDataGridJson(page, total, records, onlineUsers);
//		
//		//业务逻辑结束
//
//		this.doAfterFuncAction(request, response, model, null);
//		return json;
//	}
//	
//	/**
//	 * 
//	 * @date 2017年6月22日 下午6:10:00
//	 * 
//	 * @Description: 踢出在线用户
//	 * @param onlineUser
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 *
//	 */
//	@RequestMapping(value = "/func/logoutUser", produces = { "application/json;charset=UTF-8" })
//	@ResponseBody
//	public String logoutUser(SessionUser sessionUser,HttpServletRequest request,HttpServletResponse response, Model model) {
//		
//		if(logger.isDebugEnabled()){
//			logger.debug("请求参数  onlineUser getParameterMap:{}",jsonUtils.objectToJson(request.getParameterMap()));
//		}
//		
//		this.doBeforeFuncAction(request, response, model, null);
//		String json = null;
//		
//		try {
//			if(sessionUser != null){
//				List<Object> slist = sessionRegistry.getAllPrincipals();
//				for (int i = 0; i < slist.size(); i++) {
//					//第二个参数表示是否取单点登录时，超出限制而被弹出用户
//					User user = (User) slist.get(i);
//					if(user.getUsername().equals(sessionUser.getUserAccount())){
//						List<SessionInformation> sessionList = sessionRegistry.getAllSessions(slist.get(i), true);//多处登录
//						for (SessionInformation t : sessionList) {
//							//强制退出1.将其限制，2.将其移除，使用第一种比较好
//							t.expireNow();
//							//sessionRegistry.removeSessionInformation(t.getSessionId());
//							
//							json = "success";
//						}
//					}
//				}
//				
///*				//删除redis上面的session
//				redisSessionUser.removeSession(sessionUser);
//				
//				
//				if(sessionRegistry.getAllPrincipals().size() >= 2){
//					System.out.println(sessionRegistry.getAllSessions(sessionRegistry.getAllPrincipals().get(0), true).get(0).getSessionId());
//					System.out.println(sessionRegistry.getAllSessions(sessionRegistry.getAllPrincipals().get(1), true).get(0).getSessionId());
//				}
//				//注销用户
//				String sessionId = sessionUser.getSessionId();
//				SessionInformation sessionInformation = sessionRegistry.getSessionInformation(sessionId);
//				System.out.println(request.getSession().getId());
//				sessionInformation.expireNow();*/
//				
//			}
//		}catch (Exception e) {
//			logger.error("踢出用户失败:{}", e);
//			json = "failure";
//		}
//		
//		//业务逻辑结束
//		
//		this.doAfterFuncAction(request, response, model, null);
//		return json;
//	}
//
//}
