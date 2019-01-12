/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月21日 下午3:48:33
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.election.test.impl;

import com.basic.framework.election.impl.ElectionImpl;
import com.basic.framework.election.intf.Election;

/**
 *
 * @date 2017年3月21日 下午3:48:33
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
public class TestMainThree {
	public static void main(String[] args) throws Exception {
		
		Election election = new ElectionImpl();
		BusinessImpl businessImpl = new BusinessImpl();
		election.addInstance("busi1",businessImpl);
		System.out.println("end");
		System.in.read();
	}
}
