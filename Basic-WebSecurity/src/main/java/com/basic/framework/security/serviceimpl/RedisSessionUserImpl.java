//package com.basic.framework.security.serviceimpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.basic.framework.cache.CacheRedis;
//import com.basic.framework.common.utils.datatype.ObjectUtils;
//import com.basic.framework.security.model.SessionUser;
//import com.basic.framework.security.service.RedisSessionUser;
//
///**
// * 
// *
// * @date 2017年6月23日 下午5:22:20
// * 
// * @Description: 操作redis上面的session
// *
// */
//@Service("redisSessionUser")
//public class RedisSessionUserImpl implements RedisSessionUser{
//    
//	private Logger logger = LoggerFactory.getLogger(RedisSessionUserImpl.class);
//	private static final String SESSION_USER_CACHE = "sessionUserCache";//redis缓存的cacheCode
//	private static final String REDIS_SESSION_PREFIX = "redis-springSecurity-session";//redis缓存的key的前缀
//	private static final String REDIS_SESSION_ALL = "*redis-springSecurity-session*";//查询所有redis缓存的session的key
//	
//	@Autowired
//	private CacheRedis<?, ?> cacheRedis;
//	
//	/* (non-Javadoc)
//	 * @see com.basic.framework.security.service.RedisSessionUser#saveSession(com.basic.framework.security.model.SessionUser)
//	 */
//	@Override
//	public void saveSession(SessionUser sessionUser) {
//        if (sessionUser == null || StringUtils.isBlank(sessionUser.getSessionId())){
//        	throw new NullPointerException("session is empty");		
//        }
//        
//        String sessionId = sessionUser.getSessionId();
//        try {
//			byte[] sessionUserBytes = ObjectUtils.toByteArray(sessionUser);
//			cacheRedis.setBytes(SESSION_USER_CACHE, REDIS_SESSION_PREFIX+sessionId, sessionUserBytes);
//		} catch (Exception e) {
//			logger.error("存储session异常:{}", e);
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see com.basic.framework.security.service.RedisSessionUser#removeSession(com.basic.framework.security.model.SessionUser)
//	 */
//	@Override
//	public void removeSession(SessionUser sessionUser) {
//        if (sessionUser == null || StringUtils.isBlank(sessionUser.getSessionId())){
//        	throw new NullPointerException("session is empty");		
//        }
//        
//        String sessionId = sessionUser.getSessionId();
//        cacheRedis.removeByKey(SESSION_USER_CACHE, REDIS_SESSION_PREFIX+sessionId);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.basic.framework.security.service.RedisSessionUser#getSession(com.basic.framework.security.model.SessionUser)
//	 */
//	@Override
//	public SessionUser getSession(SessionUser sessionUser) {
//        if (sessionUser == null || StringUtils.isBlank(sessionUser.getSessionId())){
//        	throw new NullPointerException("session is empty");		
//        }
//        
//        String sessionId = sessionUser.getSessionId();
//		byte[] cacheBytes = cacheRedis.getBytes(SESSION_USER_CACHE, REDIS_SESSION_PREFIX+sessionId);
//		SessionUser item = null;
//		try {
//			item = (SessionUser) ObjectUtils.toObject(cacheBytes);
//		} catch (Exception e) {
//			logger.error("获取session异常:{}", e);
//		}
//		return item;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.basic.framework.security.service.RedisSessionUser#getAllSessions()
//	 */
//	@Override
//	public List<SessionUser> getAllSessions() {
//		List<SessionUser> list = new ArrayList<>();
//		List<byte[]> bytes = cacheRedis.qryByKeyForFuzzy(SESSION_USER_CACHE, REDIS_SESSION_ALL);
//		if(bytes != null && bytes.size() > 0){
//			for(byte[] item : bytes){
//				try {
//					SessionUser sessionUser = (SessionUser) ObjectUtils.toObject(item);
//					if(sessionUser != null){
//						list.add(sessionUser);
//					}
//				} catch (Exception e) {
//					logger.error("转换session异常:{}", e);
//				}
//			}
//		}
//		return list;
//	}
//
//}
