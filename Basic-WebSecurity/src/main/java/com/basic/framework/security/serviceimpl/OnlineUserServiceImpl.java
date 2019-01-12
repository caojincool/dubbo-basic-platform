package com.basic.framework.security.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.basic.framework.security.api.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
//import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.security.model.OnlineUser;
import com.basic.framework.security.service.OnlineUserService;

/**
 * 
 *
 * @date 2017年6月28日 下午3:01:17
 * 
 * @Description: 在线用户操作
 *
 */
@Service("onlineUserService")
public class OnlineUserServiceImpl implements OnlineUserService{
    
	private Logger logger = LoggerFactory.getLogger(OnlineUserServiceImpl.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
//	@Autowired
//	private SessionRegistry sessionRegistry;
//	@Autowired
	@SuppressWarnings("rawtypes")
	@Resource(name = "redisTemplate1")
	private RedisTemplate redisTemplate;
	
	/* (non-Javadoc)
	 * @see com.basic.framework.security.service.OnlineUserService#qryAllOnlineUser()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<OnlineUser> qryAllOnlineUser(OnlineUser ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("qryAllOnlineUser OnlineUser:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<OnlineUser> onlineUsers = new ArrayList<OnlineUser>();
//		List<Object> slist = sessionRegistry.getAllPrincipals();
//		for (int i = 0; i < slist.size(); i++) {
//			//第二个参数表示是否取单点登录时，超出限制而被弹出用户
//			List<SessionInformation> sessionList = sessionRegistry.getAllSessions(slist.get(i), false);//多处登录
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
		
		String userAccount = "*";
		if(null != ibean && StringUtils.isNotBlank(ibean.getUserAccount())){
			userAccount = ibean.getUserAccount();
		}
		Set<String> userKeys = redisTemplate.keys("spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:"+userAccount);
		SetOperations setValueops = redisTemplate.opsForSet();
		HashOperations<String, Object, Object> hashValueops = redisTemplate.opsForHash();
		for(String userKey : userKeys){
			Set<String> sessionIds = setValueops.members(userKey);
			for(String sessionId : sessionIds){//一般来说只会有一个，排除允许多处登录的情况
				String sessionIdKey = "spring:session:sessions:"+sessionId;
				DataType dataType = redisTemplate.type(sessionIdKey);
				if(DataType.NONE.equals(dataType)){//该key不存在了
					
				}else if(DataType.STRING.equals(dataType)){
//					ValueOperations<String, V> valueops = redisTemplate.opsForValue();
//					V v = valueops.get(key_item);
				}else if(DataType.LIST.equals(dataType)){
					
				}else if(DataType.SET.equals(dataType)){
					
				}else if(DataType.ZSET.equals(dataType)){
					
				}else if(DataType.HASH.equals(dataType)){
					try {
						Map<Object, Object> map = hashValueops.entries(sessionIdKey);
//						Object v1 = hvalueops.get(key_item, "creationTime");
//						Object v2 = hvalueops.get(key_item, "sessionAttr:sessionUser");
//						Object v3 = hvalueops.get(key_item, "lastAccessedTime");
//						Object v4 = hvalueops.get(key_item, "sessionAttr:SPRING_SECURITY_CONTEXT");
//						Object v5 = hvalueops.get(key_item, "sessionAttr:SPRING_SECURITY_LAST_EXCEPTION");
//						Object v6 = hvalueops.get(key_item, "maxInactiveInterval");
						if(null != map){
							Object userObj = map.get("sessionAttr:sessionUser");
							if(null != userObj){
								User user = (User) userObj;
								if(null != user){
									OnlineUser onlineUser = new OnlineUser();
//								String sessionId = StringUtils.substringAfter(sessionIdKey, "spring:session:sessions:");
									onlineUser.setSessionId(sessionId);
									onlineUser.setUserName(user.getUserName());
									onlineUser.setUserAccount(user.getUserAccount());
									long creationTime = (long) map.get("creationTime");
									onlineUser.setCreationTime(new Date(creationTime));
									long lastAccessedTime = (long) map.get("lastAccessedTime");
									onlineUser.setLastAccessedTime(new Date(lastAccessedTime));
									String springSecurityLastException = (String) map.get("sessionAttr:SPRING_SECURITY_LAST_EXCEPTION");
									onlineUser.setSpringSecurityLastException(springSecurityLastException);
									Integer maxInactiveInterval = (Integer) map.get("maxInactiveInterval");
									onlineUser.setMaxInactiveInterval(maxInactiveInterval);
									onlineUser.setcIp(user.getcIp());
									onlineUser.setcHostName(user.getcHostName());
									onlineUser.setcBrowserInfo(user.getcBrowserInfo());
									onlineUser.setcMacAddress(user.getcMacAddress());
									
									onlineUsers.add(onlineUser);
								}
							}
						}
					}catch (Exception e){
						logger.debug("redis session get map exception :{}", e);
					}
				}else{
					
				}				
			}
		}
		
//		Set<String> keys = redisTemplate.keys("spring:session:sessions:*");
//		HashOperations<String, Object, Object> hashValueops = redisTemplate.opsForHash();
//		for(String key : keys){
//			DataType dataType = redisTemplate.type(key);
//			if(DataType.NONE.equals(dataType)){//该key不存在了
//				
//			}else if(DataType.STRING.equals(dataType)){
////				ValueOperations<String, V> valueops = redisTemplate.opsForValue();
////				V v = valueops.get(key_item);
//			}else if(DataType.LIST.equals(dataType)){
//				
//			}else if(DataType.SET.equals(dataType)){
//				
//			}else if(DataType.ZSET.equals(dataType)){
//				
//			}else if(DataType.HASH.equals(dataType)){
//				Map<Object, Object> map = hashValueops.entries(key);
////				Object v1 = hvalueops.get(key_item, "creationTime");
////				Object v2 = hvalueops.get(key_item, "sessionAttr:sessionUser");
////				Object v3 = hvalueops.get(key_item, "lastAccessedTime");
////				Object v4 = hvalueops.get(key_item, "sessionAttr:SPRING_SECURITY_CONTEXT");
////				Object v5 = hvalueops.get(key_item, "sessionAttr:SPRING_SECURITY_LAST_EXCEPTION");
////				Object v6 = hvalueops.get(key_item, "maxInactiveInterval");
//				if(null != map){
//					Object userObj = map.get("sessionAttr:sessionUser");
//					if(null != userObj){
//						User user = (User) userObj;
//						if(null != user){
//							OnlineUser onlineUser = new OnlineUser();
//							onlineUser.setUserName(user.getUserName());
//							onlineUser.setUserAccount(user.getUserAccount());
//							long creationTime = (long) map.get("creationTime");
//							onlineUser.setCreationTime(new Date(creationTime));
//							long lastAccessedTime = (long) map.get("lastAccessedTime");
//							onlineUser.setLastAccessedTime(new Date(lastAccessedTime));
//							String springSecurityLastException = (String) map.get("sessionAttr:SPRING_SECURITY_LAST_EXCEPTION");
//							onlineUser.setSpringSecurityLastException(springSecurityLastException);
//							Integer maxInactiveInterval = (Integer) map.get("maxInactiveInterval");
//							onlineUser.setMaxInactiveInterval(maxInactiveInterval);
//							
//							onlineUsers.add(onlineUser);
//						}
//					}
//				}
//			}else{
//				
//			}
//		}
		
//			HashOperations<String, Object, Object> hvalueops = redisTemplate.opsForHash();
//			SetOperations<String, V> svalueops = redisTemplate.opsForSet();
//			Set<String> mkeys = redisTemplate.keys("spring:session:sessions*");//拿到所有的key
//			Set<String> skeys = redisTemplate.keys("spring:session:expirations*");//拿到所有的key
//			redisTemplate.type("spring:session:sessions:f65e0165-06f2-4984-90a1-70b216beaee9");
//			System.out.println("-----------------"+JSON_UTILS.objectToJson(redisTemplate.getHashKeySerializer()));
//			for(String key_item : mkeys){
//				List<Object> v = hvalueops.values(key_item);
//				System.out.println("-----------------"+JSON_UTILS.objectToJson(v));
//			}
//			for(String key_item : skeys){
//				Set<V> v = svalueops.members(key_item);
//				System.out.println("-----------------"+JSON_UTILS.objectToJson(v));
//			}
		
		return onlineUsers;
	}
	/* (non-Javadoc)
	 * @see com.basic.framework.security.service.OnlineUserService#removeOnlineUser(java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void removeOnlineUser(OnlineUser ibean) {
		
		if(null == ibean || StringUtils.isBlank(ibean.getUserAccount())){
			logger.debug("userAccount is empty OnlineUser:{}", JSON_UTILS.objectToJson(ibean));
			throw new NullPointerException("userAccount is empty");
		}
		
//		List<Object> slist = sessionRegistry.getAllPrincipals();
//		for (int i = 0; i < slist.size(); i++) {
//			//第二个参数表示是否取单点登录时，超出限制而被弹出用户
//			User user = (User) slist.get(i);
//			if(user.getUsername().equals(userAccount)){
//				List<SessionInformation> sessionList = sessionRegistry.getAllSessions(slist.get(i), false);//多处登录
//				for (SessionInformation t : sessionList) {
//					//强制退出1.将其限制，2.将其移除，使用第一种比较好
//					t.expireNow();
////					sessionRegistry.removeSessionInformation(t.getSessionId());
//				}
//			}
//		}
		
		Set<String> userKeys = redisTemplate.keys("spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:"+ibean.getUserAccount());
		SetOperations setValueops = redisTemplate.opsForSet();
		for(String userKey : userKeys){
			Set<String> sessionIds = setValueops.members(userKey);
			for(String sessionId : sessionIds){//一般来说只会有一个，排除允许多处登录的情况
				String sessionIdKey = "spring:session:sessions:"+sessionId;
				redisTemplate.delete(sessionIdKey);
			}
			redisTemplate.delete(userKey);
		}
				
//		String key = "spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:"+ibean.getUserAccount();
//		Boolean flag = redisTemplate.hasKey(key);
//		if(flag){
//			redisTemplate.delete(key);
//		}
//		for(String userKey : userKeys){
//			Set<String> sessionIds = setValueops.members(userKey);
//			for(String sessionId : sessionIds){//一般来说只会有一个，排除允许多处登录的情况
//				
//			}
//		}
		
//		if(null == ibean || StringUtils.isBlank(ibean.getSessionId())){
//			logger.debug("sessionId is empty OnlineUser:{}", JSON_UTILS.objectToJson(ibean));
//			throw new NullPointerException("sessionId is empty");
//		}
		
		//这些不能拿，应该从redis里面操作
//		SessionInformation sessionInformation = sessionRegistry.getSessionInformation(ibean.getSessionId());
//		sessionInformation.expireNow();
//		sessionRegistry.removeSessionInformation(ibean.getSessionId());
		
	}

}
