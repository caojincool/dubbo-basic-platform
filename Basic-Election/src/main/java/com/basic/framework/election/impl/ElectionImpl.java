/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月21日 下午2:39:29
 * 
 * @Description: 选举实现
 * 
 */
package com.basic.framework.election.impl;

import java.util.Properties;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.PropertiesUtils;
import com.basic.framework.common.utils.datatype.StringUtils;
import com.basic.framework.election.intf.ElectionHandle;
import com.basic.framework.election.intf.Election;
import com.basic.framework.election.listener.ConnectionStateListenerImpl;
import com.basic.framework.election.listener.LeaderLatchListenerImpl;

/**
 *
 * @date 2017年3月21日 下午2:39:29
 * 
 * @Description: 选举实现
 * 
 */
public class ElectionImpl implements Election {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ElectionImpl.class);
	private static final String FILE_PATH = "/config/properties/zookeeper.properties";
	private static Properties PROPERTIES = null;
	
	
	static {
		PROPERTIES = PropertiesUtils.loadProperties(FILE_PATH);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.framework.election.intf.Election#addInstance(java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	public void addInstance(String clusterCode, ElectionHandle business) throws Exception {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("========>addInstance begin");
		}
		if (StringUtils.isEmpty(clusterCode)) {
			throw new NullPointerException("clusterCode can not be null");
		}
		
		String connectString = PROPERTIES.getProperty(clusterCode);
		CuratorFramework client=CuratorFrameworkFactory.newClient(connectString, new ExponentialBackoffRetry(1000,3));
		String latchPath = "/"+clusterCode+"/leader";
		LeaderLatch leaderLatch = new LeaderLatch(client, latchPath);
		leaderLatch.addListener(new LeaderLatchListenerImpl(business));
		client.getConnectionStateListenable().addListener(new ConnectionStateListenerImpl());
		client.start();
		leaderLatch.start();
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("========>addInstance success");
		}
	}

}
