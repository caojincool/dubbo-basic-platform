package com.basic.framework.gid.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.gid.utils.sequence.GidClientUtils;


public class GidTest {

	private static Logger logger = LoggerFactory.getLogger(GidTest.class);
	
	public static void main(String args[]){
		for(int i=0;i<10000;i++){
			Long gid = GidClientUtils.getInstance().getGidValue("APP_ORDER_SEQ");
			logger.info("gidValue:{}", gid);
		}
	}

}



