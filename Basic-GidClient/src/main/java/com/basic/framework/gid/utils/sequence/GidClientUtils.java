/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月24日 下午5:45:17
 * @author lzj
 * @Description: GidClient
 * 
 */
package com.basic.framework.gid.utils.sequence;

/**
 *
 * @date 2015年12月24日 下午2:45:53
 * @author lzj
 * @Description: GidClient
 * 
 */
public class GidClientUtils {

	private static GidClientUtils inst = new GidClientUtils();
	
	private GidClientUtils(){
		
	}
	
	public static GidClientUtils getInstance(){
		return inst;
	}
	
	public Long getGidValue(String gidCode){
		return GidClientManager.getInstance().getGidValue(gidCode);
	}
	
	public static void main(String[] args){
		
		for(int i=0;i<10000;i++){
			long t0 = System.currentTimeMillis();
			Long gidValue = inst.getGidValue("WFE_SER_WORKFLOW_COMMAND_EXEC_SEQ");
			long t1 = System.currentTimeMillis();
			System.out.println("gidValue:"+gidValue+" cost:"+(t1-t0)+"ms");
			
		}
		
	}
}
