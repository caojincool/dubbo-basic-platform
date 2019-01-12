/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月21日 下午3:27:51
 * 
 * @Description: 业务逻辑接口
 * 
 */
package com.basic.framework.election.intf;

/**
 *
 * @date 2017年3月21日 下午3:27:51
 * 
 * @Description: 业务逻辑接口
 * 
 */
public interface ElectionHandle {

	/**
	 * 
	 * @date 2017年3月21日 下午3:28:43
	 * 
	 * @Description: 是leader时执行的业务逻辑
	 *
	 */
	void isLeader();
	
	/**
	 * 
	 * @date 2017年3月21日 下午3:28:58
	 * 
	 * @Description: 不是leader时执行的业务逻辑
	 *
	 */
	void notLeader();
}
