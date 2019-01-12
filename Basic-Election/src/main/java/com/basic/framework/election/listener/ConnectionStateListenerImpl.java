/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月22日 上午9:40:03
 * 
 * @Description: 连接状态监听
 * 
 */
package com.basic.framework.election.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @date 2017年3月22日 上午9:40:03
 * 
 * @Description: 连接状态监听
 * 
 */
public class ConnectionStateListenerImpl implements ConnectionStateListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionStateListenerImpl.class);
	
	/* (non-Javadoc)
	 * @see org.apache.curator.framework.state.ConnectionStateListener#stateChanged(org.apache.curator.framework.CuratorFramework, org.apache.curator.framework.state.ConnectionState)
	 */
	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		
		if (newState.equals(ConnectionState.LOST) || newState.equals(ConnectionState.SUSPENDED)) {
			
			LOGGER.error("connect lost or suspended...client is close");
		}
		
	}

}
