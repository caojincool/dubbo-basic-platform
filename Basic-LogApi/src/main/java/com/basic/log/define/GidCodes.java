package com.basic.log.define;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年7月3日 下午5:36:55
 * 
 * @Description: 系统日志，获取表主键的gidCode的定义
 *
 */
public class GidCodes implements Serializable{

	private static final long serialVersionUID = -2702218103682478827L;
	
	public static final String PUB_SYSTEM_CONFIG_SEQ = "PUB_SYSTEM_CONFIG_SEQ";//系统日志配置
	public static final String PUB_SYSTEM_LOG_SEQ = "PUB_SYSTEM_LOG_SEQ";//系统日志
	
}
