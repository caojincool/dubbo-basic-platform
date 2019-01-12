package com.basic.framework.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.basic.framework.demo.model.DemoCacheOrg;
import com.basic.framework.demo.model.DemoCacheUser;
import com.basic.framework.demo.model.DemoOrg;

/**
 * 演示緩存
 *
 * @date 2016年9月3日 下午12:58:38
 * @author lzj
 * @Description: 演示緩存
 *
 */
//@Service("demoCache")
public class DemoCacheImpl implements DemoCache {
	
	private Logger logger = LoggerFactory.getLogger(DemoCacheImpl.class);
	
	
	@Cacheable(value="oaasCache", key="#userId+#userbb+'_USER'")
	@Override
	public DemoCacheUser getUserById(Long userId, Long userbb) {
		
		logger.info("通过数据库获取用户");
		DemoCacheUser user = new DemoCacheUser();
		user.setUserId(1L);
		user.setUserName("userName1");
		return user;
	}
	
	@Cacheable(value="oaasCache", key="#orgId+'_ORG'")
	@Override
	public DemoCacheOrg getOrgById(Long orgId) {
		logger.info("通过数据库获取组织");
		DemoCacheOrg org = new DemoCacheOrg();
		org.setOrgId(1L);
		org.setOrgName("orgName1");
		return org;
	}
	
	@Cacheable(value="oaasCache", key="#orgId+'_ORG'")
	@Override
	public DemoCacheOrg getOrgByKey(Long orgId) {
		logger.info("通过数据库获取组织");
		DemoCacheOrg org = new DemoCacheOrg();
		org.setOrgId(1L);
		org.setOrgName("orgName1");
		return org;
	}
	
	@Override
    @CacheEvict(value="oaasCache", allEntries=true)
	public void clearCache(){
		logger.info("清空缓存");
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.demo.service.DemoCache#getOrgByBean(com.basic.framework.demo.model.DemoOrg)
	 */
	@Cacheable(value="oaasCache", key="#bean+'_ORG'")
	@Override
	public DemoCacheOrg getOrgByBean(DemoOrg bean) {
		logger.info("通过数据库获取组织");
		DemoCacheOrg org = new DemoCacheOrg();
		org.setOrgId(1L);
		org.setOrgName("orgName1");
		return org;
	}

	

	
}
