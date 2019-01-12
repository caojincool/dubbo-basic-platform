package com.basic.framework.common.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import sun.net.util.IPAddressUtil;


/**
 * 
 * @author lengzj
 *
 */

public class IpUtil {
	/**
	 * 获取登录用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}

   /**
	* 获取本地IP列表（针对多网卡情况）
	*
	* @return
	*/
	public static List<String> getLocalIPList() {
       List<String> ipList = new ArrayList<String>();
       try {
           Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
           NetworkInterface networkInterface;
           Enumeration<InetAddress> inetAddresses;
           InetAddress inetAddress;
           String ip;
           while (networkInterfaces.hasMoreElements()) {
               networkInterface = networkInterfaces.nextElement();
               inetAddresses = networkInterface.getInetAddresses();
               while (inetAddresses.hasMoreElements()) {
                   inetAddress = inetAddresses.nextElement();
                   if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                       ip = inetAddress.getHostAddress();
                       ipList.add(ip);
                       //System.out.println("获取本机IP："+ip);
                   }
               }
           }
       } catch (SocketException e) {
           e.printStackTrace();
       }
       return ipList;
	}
	
	/**
	 * 判断内外网IP
	 * @param request
	 * @return
	 */
	public static boolean internalIp(HttpServletRequest request){
		String ip = getIpAddr(request);
		byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
		final byte b0 = addr[0];
	    final byte b1 = addr[1];
	    //10.x.x.x/8
	    final byte section1 = 0x0A;
	    //172.16.x.x/12
	    final byte section2 = (byte) 0xAC;
	    final byte section3 = (byte) 0x10;
	    final byte section4 = (byte) 0x1F;
	    //192.168.x.x/16
	    final byte section5 = (byte) 0xC0;
	    final byte section6 = (byte) 0xA8;
	    //127.x.x.x
	    final byte section7 = (byte) 127;
	    switch (b0) {
	        case section1:
	            return true;
	        case section2:
	            if (b1 >= section3 && b1 <= section4) {
	                return true;
	            }
	        case section5:
	            switch (b1) {
	                case section6:
	                    return true;
					default:
	            }
	        case section7:
	        	return true;
	        default:
	            return false;
	    }
		
	}
}
