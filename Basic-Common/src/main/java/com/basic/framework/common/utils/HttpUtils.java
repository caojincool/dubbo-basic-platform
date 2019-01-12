package com.basic.framework.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @date 2017年8月7日 下午3:14:33
 * 
 * @Description: http工具类
 *
 */
public class HttpUtils {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	/**
	 * 
	 * @date 2017年8月7日 下午3:14:48
	 * 
	 * @Description: 通过url访问并取得返回信息
	 * @param url
	 * @param urlParam
	 * @return
	 * @throws Exception
	 *
	 */
	public static String request(String url, String urlParam) throws Exception {
		// 创建HttpClient实例
		String getURL = url + urlParam;
		//String result = null;
		StringBuilder result = new StringBuilder();
		URL getUrl = null;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		try {
			getUrl = new URL(getURL);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;

			while ((lines = reader.readLine()) != null) {
				result.append(lines+"\r\n");
			}
		} catch (IOException e) {
			logger.error("connect error serverUrl:{}, urlParam:{}", url, urlParam, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("close reader error serverUrl:{}, urlParam:{}", url, urlParam, e);
				}
			}

			if (connection != null) {
				connection.disconnect();
			}
		}
		return result.toString();
	}
	
	
	
	
	/**
	 * 获取访问者IP 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 获取来访者的浏览器版本
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestBrowserInfo(HttpServletRequest request) {
		String browserVersion = null;
		String header = request.getHeader("user-agent");
		if (header == null || "".equals(header)) {
			return "";
		}
		if (header.indexOf("MSIE") > 0) {
			browserVersion = "IE";
		} else if (header.indexOf("Firefox") > 0) {
			browserVersion = "Firefox";
		} else if (header.indexOf("Chrome") > 0) {
			browserVersion = "Chrome";
		} else if (header.indexOf("Safari") > 0) {
			browserVersion = "Safari";
		} else if (header.indexOf("Camino") > 0) {
			browserVersion = "Camino";
		} else if (header.indexOf("Konqueror") > 0) {
			browserVersion = "Konqueror";
		}
		return browserVersion;
	}

	/**
	 * 获取系统版本信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestSystemInfo(HttpServletRequest request) {
		String systenInfo = null;
		String header = request.getHeader("user-agent");
		if (header == null || "".equals(header)) {
			return "";
		}
		// 得到用户的操作系统
		if (header.indexOf("NT 6.0") > 0) {
			systenInfo = "Windows Vista/Server 2008";
		} else if (header.indexOf("NT 5.2") > 0) {
			systenInfo = "Windows Server 2003";
		} else if (header.indexOf("NT 5.1") > 0) {
			systenInfo = "Windows XP";
		} else if (header.indexOf("NT 6.0") > 0) {
			systenInfo = "Windows Vista";
		} else if (header.indexOf("NT 6.1") > 0) {
			systenInfo = "Windows 7";
		} else if (header.indexOf("NT 6.2") > 0) {
			systenInfo = "Windows Slate";
		} else if (header.indexOf("NT 6.3") > 0) {
			systenInfo = "Windows 9";
		} else if (header.indexOf("NT 5") > 0) {
			systenInfo = "Windows 2000";
		} else if (header.indexOf("NT 4") > 0) {
			systenInfo = "Windows NT4";
		} else if (header.indexOf("Me") > 0) {
			systenInfo = "Windows Me";
		} else if (header.indexOf("98") > 0) {
			systenInfo = "Windows 98";
		} else if (header.indexOf("95") > 0) {
			systenInfo = "Windows 95";
		} else if (header.indexOf("Mac") > 0) {
			systenInfo = "Mac";
		} else if (header.indexOf("Unix") > 0) {
			systenInfo = "UNIX";
		} else if (header.indexOf("Linux") > 0) {
			systenInfo = "Linux";
		} else if (header.indexOf("SunOS") > 0) {
			systenInfo = "SunOS";
		}
		return systenInfo;
	}

	/**
	 * 获取来访者的主机名称
	 * 
	 * @param ip
	 * @return
	 */
	public static String getHostName(String ip) {
		InetAddress inet;
		try {
			inet = InetAddress.getByName(ip);
			return inet.getHostName();
		} catch (UnknownHostException e) {
			//e.printStackTrace();
		}
		return "";
	}

	/**
	 * 命令获取mac地址
	 * 
	 * @param cmd
	 * @return
	 */
	private static String callCmd(String[] cmd) {
		String result = "";
		String line = "";
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * 
	 * 
	 * @param cmd
	 *            第一个命令
	 * 
	 * @param another
	 *            第二个命令
	 * 
	 * @return 第二个命令的执行结果
	 * 
	 */

	private static String callCmd(String[] cmd, String[] another) {
		String result = "";
		String line = "";
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			proc.waitFor(); // 已经执行完第一个命令，准备执行第二个命令
			proc = rt.exec(another);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * 
	 * 
	 * @param ip
	 *            目标ip,一般在局域网内
	 * 
	 * @param sourceString
	 *            命令处理的结果字符串
	 * 
	 * @param macSeparator
	 *            mac分隔符号
	 * 
	 * @return mac地址，用上面的分隔符号表示
	 * 
	 */

	private static String filterMacAddress(final String ip, final String sourceString, final String macSeparator) {
		String result = "";
		String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(sourceString);
		while (matcher.find()) {
			result = matcher.group(1);
			if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
				break; // 如果有多个IP,只匹配本IP对应的Mac.
			}
		}
		return result;
	}

	/**
	 * @param ip
	 *            目标ip
	 * @return Mac Address
	 * 
	 */

	private static String getMacInWindows(final String ip) {
		String result = "";
		try {
			String[] cmd = { "cmd", "/c", "ping " + ip };
			String[] another = { "cmd", "/c", "arp -a" };
			String cmdResult = callCmd(cmd, another);
			result = filterMacAddress(ip, cmdResult, "-");
		}catch (Exception e){
			result = "";
		}

		return result;
	}

	/**
	 * 
	 * @param ip
	 *            目标ip
	 * @return Mac Address
	 * 
	 */
	private static String getMacInLinux(final String ip) {
		String result = "";
		try {
			String[] cmd = { "/bin/sh", "-c", "ping " + ip + " -c 2 && arp -a" };
			String cmdResult = callCmd(cmd);
			result = filterMacAddress(ip, cmdResult, ":");
		}catch (Exception e){
			result = "";
		}
		return result;
	}

	/**
	 * 获取MAC地址
	 * 
	 * @return 返回MAC地址
	 */
	public static String getMacAddress(String ip) {
		String macAddress = "";
		macAddress = getMacInWindows(ip).trim();
		if (macAddress == null || "".equals(macAddress)) {
			macAddress = getMacInLinux(ip).trim();
		}
		return macAddress;
	}
         
	/**
	 * 
	 * @date 2017年11月20日 上午11:08:15
	 * 
	 * @Description: 获取本机的外网ip
	 * @return
	 *
	 */
	public static String getExtranetIp(){
		String ip = "";
		String url = "http://ip.chinaz.com";
		String urlParam = "";
		String regex = "\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>";
		try {
			String html = request(url, urlParam);
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(html);
			if(m.find()){
				String ipstr = m.group(1);
				ip = ipstr;
			}
		} catch (Exception e) {
			logger.error("getExtranetIp error url:{}, urlParam:{}, e:{}", url, urlParam, e);
		}
		return ip;
	}
	
}
