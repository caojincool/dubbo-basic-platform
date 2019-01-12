package com.basic.framework.demo.service;

import com.basic.framework.demo.model.DemoCacheOrg;
import com.basic.framework.demo.model.DemoCacheUser;
import com.basic.framework.demo.model.DemoOrg;

/**
 * 演示緩存
 *
 * @date 2016年9月3日 下午12:57:06
 * @author lzj
 * @Description: 演示緩存
 *
 */
public interface DemoCache {

	/**
	 * 根据userId查询用户详情
	 * @date 2016年9月3日 下午12:58:55
	 * @author lzj
	 * @Description: 根据userId查询用户详情
	 * @param userId
	 * @return
	 *
	 */
	public DemoCacheUser getUserById(Long userId, Long userbb);
	
	/**
	 * 根据orgId查询
	 * @date 2016年9月3日 下午3:39:10
	 * @author lzj
	 * @Description: 根据orgId查询
	 * @param orgId
	 * @return
	 *
	 */
	public DemoCacheOrg getOrgById(Long orgId);
	
	/**
	 * 
	 * @date 2016年10月18日 下午2:47:59
	 * @author 杰
	 * @Description: 根据bean查询
	 * @param bean
	 * @return
	 *
	 */
	public DemoCacheOrg getOrgByBean(DemoOrg bean);
	
	/**
	 * 根据orgId查询
	 * @date 2016年9月3日 下午3:39:10
	 * @author lzj
	 * @Description: 根据orgId查询
	 * @param orgId
	 * @return
	 *
	 */
	public DemoCacheOrg getOrgByKey(Long orgId);
	/**
	 * 清空缓存
	 * @date 2016年9月3日 下午1:39:11
	 * @author lzj
	 * @Description: 清空缓存
	 *
	 */
	public void clearCache();
}
