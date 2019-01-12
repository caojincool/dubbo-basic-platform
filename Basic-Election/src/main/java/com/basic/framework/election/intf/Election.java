/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月21日 下午2:35:39
 * 
 * @Description: 选举接口
 * 
 */
package com.basic.framework.election.intf;

/**
 *
 * @date 2017年3月21日 下午2:35:39
 * 
 * @Description: 选举接口
 * 
 */
public interface Election {

	/**
	 * 
	 * @date 2017年3月22日 上午9:24:13
	 * 
	 * @Description: 通过集群编码实例化一个zk客户端
	 * @param clusterCode 集群编码
	 * @param electionHandle 业务逻辑实现
	 * @throws Exception
	 *
	 */
	void addInstance(String clusterCode, ElectionHandle electionHandle) throws Exception;
}
