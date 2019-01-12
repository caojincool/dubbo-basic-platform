/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月21日 下午3:23:27
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.election.listener;

import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

import com.basic.framework.election.intf.ElectionHandle;

/**
 *
 * @date 2017年3月21日 下午3:23:27
 * 
 * @Description: 选举监听类
 * 
 */
public class LeaderLatchListenerImpl implements LeaderLatchListener {

	
	private ElectionHandle business;
	
	public LeaderLatchListenerImpl(ElectionHandle business){
		this.business = business;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.curator.framework.recipes.leader.LeaderLatchListener#isLeader()
	 */
	@Override
	public void isLeader() {
		business.isLeader();
		
	}

	/* (non-Javadoc)
	 * @see org.apache.curator.framework.recipes.leader.LeaderLatchListener#notLeader()
	 */
	@Override
	public void notLeader() {
		business.notLeader();
		
	}

}
