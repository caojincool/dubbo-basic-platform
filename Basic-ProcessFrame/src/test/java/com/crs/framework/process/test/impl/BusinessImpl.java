/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月21日 下午3:41:53
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.process.test.impl;

import com.basic.framework.election.intf.ElectionHandle;

/**
 *
 * @date 2017年3月21日 下午3:41:53
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
public class BusinessImpl implements ElectionHandle{

	/* (non-Javadoc)
	 * @see com.basic.framework.election.intf.Business#isLeader()
	 */
	@Override
	public void isLeader() {
		System.out.println("===========>实例1成为leader,正在执行isLeader方法");
		
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.election.intf.Business#notLeader()
	 */
	@Override
	public void notLeader() {
		System.out.println("===========>实例1失去leader,正在执行notLeader方法");
		
	}

}
