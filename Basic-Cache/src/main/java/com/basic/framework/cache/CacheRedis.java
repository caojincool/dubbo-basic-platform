/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月7日 下午2:02:04
 * 
 * @Description: 操作redis缓存
 * 
 */
package com.basic.framework.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.basic.framework.cache.model.RedisCache;
import com.basic.framework.cache.model.RedisTemplateScopeConfig;
import com.basic.framework.common.utils.datatype.IntegerUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.common.utils.datatype.ObjectUtils;
import com.basic.framework.common.utils.datatype.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @date 2017年3月7日 下午2:02:04
 * 
 * @param <V>
 * @Description: 操作redis缓存
 * 
 */
@Service("redisService")
public class CacheRedis<T, V> implements Cache {
	
	private static final Logger LOG = LoggerFactory.getLogger(CacheRedis.class);
	private static final String SPLIT_STR = "-";//SCOPE分隔符
	private static final String SPLIT_CODE_KEY = "*";//key和value分隔符
	private Map<String,RedisTemplate<String, V>> redisTemplateMap;
	private Map<String, RedisTemplateScopeConfig<String, V>> redisTemplateScopeConfigMap;

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private CacheRedis(){
		
	}
	
	/**
	 * 
	 * @date 2017年3月7日 下午4:42:48
	 * 
	 * @Description: 初始化配置
	 * @param redisTemplateScopeConfigMap
	 *
	 */
	private CacheRedis( Map<String, RedisTemplateScopeConfig<String, V>> redisTemplateScopeConfigMap){
		
		LOG.info("=============>CacheRedis init start");
		
		this.redisTemplateScopeConfigMap = redisTemplateScopeConfigMap;
		this.redisTemplateMap = new HashMap<String,RedisTemplate<String, V>>();
		
		for(String cacheCode : redisTemplateScopeConfigMap.keySet()){
			LOG.debug("cacheCode :{}",cacheCode);
			RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = redisTemplateScopeConfigMap.get(cacheCode);
			Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
				
			for (String key : redisTemplateScope.keySet()) {
					String[] s = key.split(SPLIT_STR);
					int min = IntegerUtils.valueOf(s[0]);
					int max = IntegerUtils.valueOf(s[1]);
					RedisTemplate<String, V> redisTemplate = redisTemplateScope.get(key);
					
					for(int i=min;i<max;i++){
						redisTemplateMap.put(i+cacheCode, redisTemplate);
					}
					
				}
				
				LOG.info("=============>CacheRedis init success");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#getString(java.lang.String, java.lang.String)
	 */
//	@Override
//	public String getString(String cacheCode, String key) {
//		
//		return (String) this.get(cacheCode, key);
//	}

	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#getBytes(java.lang.String, java.lang.String)
	 */
//	@Override
//	public byte[] getBytes(String cacheCode, String key) {
//		
//		return (byte[]) this.get(cacheCode, key);
//	}

	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#setString(java.lang.String, java.lang.String, java.lang.String)
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public void setString(String cacheCode, String key, String value) {
//		
//		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
//			throw new NullPointerException("cacheCode or key or value can not be null");
//		}
//		this.set(cacheCode, key, (V) value);
//	}

	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#setBytes(java.lang.String, java.lang.String, byte[])
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public void setBytes(String cacheCode, String key, byte[] value) {
//		
//		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(key) || value == null || value.length <= 0) {
//			throw new NullPointerException("cacheCode or key or value can not be null");
//		}
//		
//		this.set(cacheCode, key, (V) value);
//	}
	
	private void set(String cacheCode,String key, V value) {
		
		RedisTemplate<String,V> redisTemplate = this.getRedisTemplate(key,cacheCode);
		ValueOperations<String, V> valueops = redisTemplate.opsForValue();
		
		//获取存活时间
		RedisTemplateScopeConfig<String, V> config = this.redisTemplateScopeConfigMap.get(cacheCode);
		Long liveTime = config.getLiveTime();
		String redisKey = getRedisKey(cacheCode, key);
		valueops.set(redisKey, (V) value, liveTime ,TimeUnit.SECONDS);
	}
	
	private void set(String templateKey,String cacheCode, String key, V value) {
		
		RedisTemplate<String,V> redisTemplate = this.getRedisTemplate(key,templateKey);
		ValueOperations<String, V> valueops = redisTemplate.opsForValue();
		
		//获取存活时间
		RedisTemplateScopeConfig<String, V> config = this.redisTemplateScopeConfigMap.get(templateKey);
		Long liveTime = config.getLiveTime();
		String redisKey = getRedisKey(cacheCode, key);
		valueops.set(redisKey, (V) value, liveTime ,TimeUnit.SECONDS);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#removeByKey(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeByKey(String cacheCode, String key) {
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(key)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		RedisTemplate<String,V> redisTemplate = this.getRedisTemplate(key,cacheCode);
		String redisKey = getRedisKey(cacheCode, key);
		redisTemplate.delete(redisKey);
		
	}
	
	public void removeByKey(String templateKey, String cacheCode,String key) {
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(templateKey) || StringUtils.isEmpty(key)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		RedisTemplate<String,V> redisTemplate = this.getRedisTemplate(key,templateKey);
		String redisKey = getRedisKey(cacheCode, key);
		redisTemplate.delete(redisKey);
		
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#removeByCacheCode(java.lang.String)
	 */
	@Override
	public void removeByCacheCode(String cacheCode) {
		if (StringUtils.isEmpty(cacheCode)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		//根据cacheCode获取所有的redisTemplate
		RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(cacheCode);
		List<RedisTemplate<String, V>> redisTemplates = new ArrayList<>();
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String key : redisTemplateScope.keySet()) {
			redisTemplateScope.get(key);
			redisTemplates.add(redisTemplateScope.get(key));
		}
		
		for (RedisTemplate<String, V> redisTemplate : redisTemplates) {
			redisTemplate.delete(redisTemplate.keys(cacheCode+"*"));
		}
	}
	
	public void removeByCacheCode(String templateKey,String cacheCode) {
		if (StringUtils.isEmpty(cacheCode)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		//根据cacheCode获取所有的redisTemplate
		RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(templateKey);
		List<RedisTemplate<String, V>> redisTemplates = new ArrayList<>();
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String key : redisTemplateScope.keySet()) {
			redisTemplateScope.get(key);
			redisTemplates.add(redisTemplateScope.get(key));
		}
		
		for (RedisTemplate<String, V> redisTemplate : redisTemplates) {
			redisTemplate.delete(redisTemplate.keys(cacheCode+"*"));
		}
	}
	
	/**
	 * 
	 * @date 2017年3月7日 下午5:17:34
	 * 
	 * @Description: 获取缓存值
	 * @param cacheCode
	 * @param key
	 * @return
	 *
	 */
	private V get(String cacheCode, String key){
		
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(key)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		RedisTemplate<String,V> redisTemplate = this.getRedisTemplate(key,cacheCode);
		ValueOperations<String, V> valueops = redisTemplate.opsForValue();
		String redisKey = getRedisKey(cacheCode, key);
		
		return valueops.get(redisKey);
	}
	
	private V get(String templateKey,String cacheCode, String key){
		
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(templateKey) || StringUtils.isEmpty(key)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		RedisTemplate<String,V> redisTemplate = this.getRedisTemplate(key,templateKey);
		ValueOperations<String, V> valueops = redisTemplate.opsForValue();
		String redisKey = getRedisKey(cacheCode, key);
		
		return valueops.get(redisKey);
	}
	
	/**
	 * 
	 * @date 2017年3月7日 下午4:51:18
	 * 
	 * @Description: 根据key%1024+cacheCode获取RedisTemplate
	 * @param key
	 * @param cacheCode
	 * @return
	 *
	 */
	private RedisTemplate<String,V> getRedisTemplate(String key,String cacheCode){
		int hashCode = Math.abs(key.hashCode());
		String scopeKey = hashCode % 1024+cacheCode;
		return redisTemplateMap.get(scopeKey);
	}
	


	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#qryByKeyForFuzzy(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> List<T> qryByKeyForFuzzy(String cacheCode, String key) {
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(key)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		List<T> list = new ArrayList<>();
		//根据cacheCode获取所有的redisTemplate
		RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(cacheCode);
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String item : redisTemplateScope.keySet()) {
			RedisTemplate<String, V> redisTemplate = redisTemplateScope.get(item);
			
			ValueOperations<String, V> valueops = redisTemplate.opsForValue();
			Set<String> keys = redisTemplate.keys(key);//拿到所有的key
			for(String keyItem : keys){
				V v = valueops.get(keyItem);
				list.add((T) v);
			}
		}
		
		return list;
	}
	
	
	public <T> List<T> getAllByCode(String templateKey,String cacheCode) {
		if (StringUtils.isEmpty(templateKey) || StringUtils.isEmpty(cacheCode)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		List<T> list = new ArrayList<>();
		//根据cacheCode获取所有的redisTemplate
		RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(templateKey);
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String item : redisTemplateScope.keySet()) {
			RedisTemplate<String, V> redisTemplate = redisTemplateScope.get(item);
			
			ValueOperations<String, V> valueops = redisTemplate.opsForValue();
			Set<String> keys = redisTemplate.keys(cacheCode+"*");//拿到所有的key
			for(String keyItem : keys){
				try {
					list.add((T) ObjectUtils.toObject((byte[])valueops.get(keyItem)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		/*RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(cacheCode);
		List<RedisTemplate<String, V>> redisTemplates = new ArrayList<>();
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String key : redisTemplateScope.keySet()) {
			redisTemplateScope.get(key);
			redisTemplates.add(redisTemplateScope.get(key));
		}
		
		for (RedisTemplate<String, V> redisTemplate : redisTemplates) {
			redisTemplate.delete(redisTemplate.keys(cacheCode+"*"));
		}*/
		
		return list;
	}
	public <T> List<T> getAllByCode(String cacheCode) {
		if (StringUtils.isEmpty(cacheCode)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		List<T> list = new ArrayList<>();
		//根据cacheCode获取所有的redisTemplate
		RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(cacheCode);
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String item : redisTemplateScope.keySet()) {
			RedisTemplate<String, V> redisTemplate = redisTemplateScope.get(item);
			
			ValueOperations<String, V> valueops = redisTemplate.opsForValue();
			Set<String> keys = redisTemplate.keys(cacheCode+"*");//拿到所有的key
			for(String keyItem : keys){
				try {
					list.add((T) ObjectUtils.toObject((byte[])valueops.get(keyItem)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public List<RedisCache> getAllByCodeAndKey(String cacheCode,String key) {
		if (StringUtils.isEmpty(cacheCode)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		List<RedisCache> list = new ArrayList<>();
		//根据cacheCode获取所有的redisTemplate
		RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(cacheCode);
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String item : redisTemplateScope.keySet()) {
			RedisTemplate<String, V> redisTemplate = redisTemplateScope.get(item);
			
			ValueOperations<String, V> valueops = redisTemplate.opsForValue();
			Set<String> keys = redisTemplate.keys(cacheCode+"*"+key);//拿到所有的key
			for(String keyItem : keys){
				try {
					V v = valueops.get(keyItem);
					byte[] bytes = (byte[]) v;
//					String str = JSON_UTILS.objectToJson(ObjectUtils.toObject(bytes));
					Object obj = null;
					String str = null;
					try {
						obj = ObjectUtils.toObject(bytes);
						str = JSON_UTILS.objectToJson(obj);
					} catch (Exception e) {
						LOG.error("ObjectUtils.toObject错误:{}", e);
						str = e.toString();
					}
					
					String[] codeKey = keyItem.split(SPLIT_CODE_KEY);
					String code0 = codeKey[0];
					String key1 = codeKey[1];
					
					RedisCache redisCache = new RedisCache();
					redisCache.setCacheCode(code0);
					redisCache.setKey(key1);
					redisCache.setValue(str);
					list.add(redisCache);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void setObject(String cacheCode,String key,Object obj) throws Exception{
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(key) ||  null == obj ) {
			throw new NullPointerException("cacheCode or key or obj can not be null");
		}
		byte[] bytes = ObjectUtils.toByteArray(obj);
		this.set(cacheCode,key,(V) bytes);
	}
	
	public void setObject(String templateKey,String cacheCode,String key,Object obj) throws Exception{
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(templateKey) || StringUtils.isEmpty(key) ||  null == obj ) {
			throw new NullPointerException("cacheCode or key or obj can not be null");
		}
		byte[] bytes = ObjectUtils.toByteArray(obj);
		this.set(templateKey,cacheCode,key,(V) bytes);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#getObject(java.lang.String, java.lang.String)
	 */
	@Override
	public Object getObject(String cacheCode, String key) throws Exception {
		byte[] bytes = (byte[]) this.get(cacheCode, key);
		Object obj = ObjectUtils.toObject(bytes);
		return obj;
	}
	
	public Object getObject(String templateKey,String cacheCode, String key) throws Exception {
		byte[] bytes = (byte[]) this.get(templateKey,cacheCode, key);
		Object obj = ObjectUtils.toObject(bytes);
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.cache.Cache#getAllByKey(java.lang.String, java.lang.String)
	 */
	@Override
	public List<RedisCache> getAllByKey(String cacheCode, String key) {
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(key)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		List<RedisCache> list = new ArrayList<RedisCache>();
		//根据cacheCode获取所有的redisTemplate
		RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(cacheCode);
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String item : redisTemplateScope.keySet()) {
			RedisTemplate<String, V> redisTemplate = redisTemplateScope.get(item);
			
			ValueOperations<String, V> valueops = redisTemplate.opsForValue();
			Set<String> keys = redisTemplate.keys(key);//拿到所有的key
			for(String keyItem : keys){
				try {
					V v = valueops.get(keyItem);
					byte[] bytes = (byte[]) v;
//					String str = JSON_UTILS.objectToJson(ObjectUtils.toObject(bytes));
					Object obj = null;
					String str = null;
					try {
						obj = ObjectUtils.toObject(bytes);
						str = JSON_UTILS.objectToJson(obj);
					} catch (Exception e) {
						LOG.error("ObjectUtils.toObject错误:{}", e);
						str = e.toString();
					}
					
					String[] codeKey = keyItem.split(SPLIT_CODE_KEY);
					String code0 = codeKey[0];
					String key1 = codeKey[1];
					
					RedisCache redisCache = new RedisCache();
					redisCache.setCacheCode(code0);
					redisCache.setKey(key1);
					redisCache.setValue(str);
					list.add(redisCache);
				} catch (Exception e) {
					LOG.error("取出redis错误 e:{}", e);
				}
			}
		}
		
		return list;
	}

	/**
	 * 
	 * @date 2017年7月21日 上午11:00:09
	 * 
	 * @Description: 返回redisKey：cacheCode||key
	 * @param cacheCode
	 * @param key
	 * @return
	 *
	 */
	private String getRedisKey(String cacheCode, String key){
		String redisKey = cacheCode+SPLIT_CODE_KEY+key;
		return redisKey;
	}

	
/*	public void qryRedisSession(String cacheCode, String key) {
		if (StringUtils.isEmpty(cacheCode) || StringUtils.isEmpty(key)) {
			throw new NullPointerException("cacheCode or key or value can not be null");
		}
		
		List<T> list = new ArrayList<>();
		//根据cacheCode获取所有的redisTemplate
		RedisTemplateScopeConfig<String, V> redisTemplateScopeConfig = this.redisTemplateScopeConfigMap.get(cacheCode);
		Map<String, RedisTemplate<String, V>> redisTemplateScope = redisTemplateScopeConfig.getRedisTemplateScope();
		for (String item : redisTemplateScope.keySet()) {
			RedisTemplate<String, V> redisTemplate = redisTemplateScope.get(item);
			
			Set<String> keys = redisTemplate.keys("spring:session*");
			for(String key_item : keys){
				DataType dataType = redisTemplate.type(key_item);
				if(DataType.NONE.equals(dataType)){
					
				}else if(DataType.STRING.equals(dataType)){
//					ValueOperations<String, V> valueops = redisTemplate.opsForValue();
//					V v = valueops.get(key_item);
				}else if(DataType.LIST.equals(dataType)){
					
				}else if(DataType.SET.equals(dataType)){
					
				}else if(DataType.ZSET.equals(dataType)){
					
				}else if(DataType.HASH.equals(dataType)){
					HashOperations<String, Object, Object> hvalueops = redisTemplate.opsForHash();
					List<Object> v = hvalueops.values(key_item);
					Map<Object, Object> map = hvalueops.entries(key_item);
					map.get("lastAccessedTime");
					Set<Object> keys1 = hvalueops.keys(key_item);
					Object v1 = hvalueops.get(key_item, "creationTime");
					Object v2 = hvalueops.get(key_item, "sessionAttr:sessionUser");
					Object v3 = hvalueops.get(key_item, "lastAccessedTime");
					Object v4 = hvalueops.get(key_item, "sessionAttr:SPRING_SECURITY_CONTEXT");
					Object v5 = hvalueops.get(key_item, "sessionAttr:SPRING_SECURITY_LAST_EXCEPTION");
					Object v6 = hvalueops.get(key_item, "maxInactiveInterval");
//					Map<Object, Object> map = hvalueops.entries(key_item);
					if(null != map){
						Object userObj = map.get("sessionAttr:sessionUser");

					}
				}else{
					
				}
			}
			
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
		}
	}*/
	
}
