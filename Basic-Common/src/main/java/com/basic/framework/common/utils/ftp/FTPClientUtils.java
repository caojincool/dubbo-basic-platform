/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2016年1月16日 下午3:31:37
 * @author liao.chunqiao
 * @Description: FTP工具类
 * 
 */
package com.basic.framework.common.utils.ftp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.model.FTPConfig;
import com.basic.framework.common.utils.FileUtils;

public class FTPClientUtils {

	private static Logger logger = LoggerFactory.getLogger(FTPClientUtils.class);

	public static final int FTP_PORT = 21;
	
	public static final String DIR_SPLIT = "/";
	public static final String FTP_TYPE = "FTP";
	public static final String LOCAL_TYPE = "LOCAL";
	
	/**
	 * @date 2016年1月17日 下午9:21:57
	 * @author liao.chunqiao
	 * @Description:上传文件
	 * @param input
	 * @param storeFileName
	 * @param ftpConfig
	 * @throws Exception 
	 * 
	 */
	public static void uploadFile(InputStream input, String storeFileName,FTPConfig ftpCfg) throws Exception {

		if(FTP_TYPE.equals(ftpCfg.getType())){//ftp
			FTPClient ftp = new FTPClient();
			int replyCode = 0;
			try {
				// 连接FTP服务端
				ftp.connect(ftpCfg.getIp(), FTP_PORT);
				replyCode = ftp.getReplyCode();
				if (replyCode != FTPReply.SERVICE_READY) {
					throw new Exception("连不上FTP服务端！");
				}

				// 登陆FTP服务端
				if (!ftp.login(ftpCfg.getUsername(), ftpCfg.getPassword())) {
					throw new Exception("登陆失败!");
				}
				logger.info("登陆FTP成功!");

				// 上传文件
				// 1、切换到上传的目录
				// 2、上传
	/*			try {
					if (null != ftpCfg.getWorkdir() && !ftpCfg.getWorkdir().equals("")) {

						int end = 0;
						if (ftpCfg.getWorkdir().charAt(0) == '/') {
							end = 1;
						}
						int begin = end;
						String curDir = "";
						while ((end = ftpCfg.getWorkdir().indexOf(DIR_SPLIT, begin)) != -1) {
							curDir = ftpCfg.getWorkdir().substring(begin, end);
							if (!ftp.changeWorkingDirectory(curDir)) {
								ftp.makeDirectory(curDir);
								ftp.changeWorkingDirectory(curDir);
							}
							begin = end + 1;
						}
						if (begin < ftpCfg.getWorkdir().length()) {
							curDir = ftpCfg.getWorkdir().substring(begin);
							if (!ftp.changeWorkingDirectory(curDir)) {
								ftp.makeDirectory(curDir);
								ftp.changeWorkingDirectory(curDir);
							}
						}
					}
				} catch (Exception ex) {
					throw new Exception("切换到指定的上传目录失败！");
				}*/
				if (!ftp.changeWorkingDirectory(ftpCfg.getWorkdir())) {
					throw new Exception("切换到指定目录失败！");
				}

				ftp.setFileType(FTP.BINARY_FILE_TYPE);// 以BINARY格式传送,解决乱码问题！
				boolean uploadRt = ftp.storeFile(storeFileName, input);

				if (!uploadRt) {
					throw new Exception("上传文件失败！");
				}
				if (logger.isInfoEnabled()) {
					logger.info("上传文件成功!");
				}

			} catch (Exception e) {
				logger.error("上传文件异常：", e);
				throw new Exception(e);
			} finally {
				// 退出登陆、断开与服务端的连接
				if (ftp.isConnected()) {
					try {
						ftp.logout();
						ftp.disconnect();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		}else if(LOCAL_TYPE.equals(ftpCfg.getType())){
//			String descFileName = fileConfig.getStoreDir() + "/" + targetFileName;
			String descDirName = ftpCfg.getWorkdir();
			FileUtils.createDirectory(descDirName);//创建目录
			//FileUtils.createFile(descFileName);

			OutputStream os = null;
			try {
				// 1K的数据缓冲
	            byte[] bs = new byte[1024];
	            // 读取到的数据长度
	            int length;
				os = new FileOutputStream(descDirName + File.separator + storeFileName);
	            // 开始读取
	            while ((length = input.read(bs)) != -1) {
	                os.write(bs, 0, length);
	            }
			} catch (IOException e) {
				logger.error("本地上传文件异常：", e);
				throw new Exception(e);
			} catch (Exception e) {
				logger.error("本地上传文件异常：", e);
				throw new Exception(e);
			} finally {
				// 完毕，关闭所有链接
				try {
					os.close();
					input.close();
				} catch (IOException e) {
					logger.error("本地上传文件异常：", e);
					throw new Exception(e);
				}
			}
		}
	}

	/**
	 * @date 2016年1月17日 下午11:16:07
	 * @author liao.chunqiao
	 * @Description: 从FTP下载文件写到指定的output流中
	 * @param output 将文件内容写入的指定的输出流
	 * @param fileName 要读取的文件名
	 * @param ftpConfig FTP需要的配置
	 * 
	 */
	public static void downloadFile(OutputStream output,String fileName,FTPConfig ftpCfg) {

		if(FTP_TYPE.equals(ftpCfg.getType())){//ftp
			FTPClient ftp = new FTPClient();
			int replyCode = 0;
			try {
				// 连接FTP服务端
				ftp.connect(ftpCfg.getIp(), FTP_PORT);
				replyCode = ftp.getReplyCode();
				if (replyCode != FTPReply.SERVICE_READY) {
					throw new Exception("连不上FTP服务端！");
				}

				// 登陆FTP服务端
				if (!ftp.login(ftpCfg.getUsername(), ftpCfg.getPassword())) {
					throw new Exception("登陆失败!");
				}
				logger.info("登陆FTP成功!");

				// 下载文件
				// 1、切换到下载目录
				// 2、下载
				if (!ftp.changeWorkingDirectory(ftpCfg.getWorkdir())) {
					throw new Exception("切换到指定目录失败！");
				}

				ftp.setFileType(FTP.BINARY_FILE_TYPE);// 以BINARY格式传送,解决乱码问题！
				boolean downloadRt = ftp.retrieveFile(fileName, output);
				if (!downloadRt) {
					throw new Exception("下载文件失败！");
				}
				if (logger.isInfoEnabled()) {
					logger.info("下载文件成功!");
				}

			} catch (Exception e) {
				logger.error("下载文件异常：", e);
				
			} finally {
				// 退出登陆、断开与服务端的连接
				if (ftp.isConnected()) {
					try {
						ftp.logout();
						ftp.disconnect();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		}else if(LOCAL_TYPE.equals(ftpCfg.getType())){
			FileInputStream fileIs = null;
			try {
				fileIs = new FileInputStream(ftpCfg.getWorkdir() + File.separator + fileName);
				int i = fileIs.available(); // 得到文件大小
				byte data[] = new byte[i];
				fileIs.read(data); // 读数据
				output.write(data); // 输出数据
				output.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					output.close();
					fileIs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * @date 2016年1月20日 上午12:09:59
	 * @author liao.chunqiao
	 * @Description:下载到指定的流中
	 * @param input 
	 * @param fileName
	 * @param ftpCfg
	 * 
	 */
	public static InputStream downloadFile(String fileName,FTPConfig ftpCfg) {

		if(FTP_TYPE.equals(ftpCfg.getType())){//ftp
			InputStream input = null;
			InputStream stream1 = null;
			FTPClient ftp = new FTPClient();
			int replyCode = 0;
			try {
				// 连接FTP服务端
				ftp.connect(ftpCfg.getIp(), 21);
				replyCode = ftp.getReplyCode();
				//if (replyCode != FTPReply.SERVICE_READY) {
				if (!FTPReply.isPositiveCompletion(replyCode)) {
					ftp.disconnect();
					logger.error("服务器拒绝连接，返回码：" + replyCode);
					throw new Exception("连不上FTP服务端！");
				}
				
				// 登陆FTP服务端
				if (!ftp.login(ftpCfg.getUsername(), ftpCfg.getPassword())) {
					throw new Exception("登陆失败!");
				}
				
				//ftp.setDefaultTimeout(60 * 1000);
				//ftp.setConnectTimeout(60 * 1000);
				ftp.setDataTimeout(60 * 1000);
				ftp.setSoTimeout(60 * 1000);
				
				logger.info("登陆FTP成功!");

				// 下载文件
				// 1、切换到下载目录
				// 2、下载
				if (!ftp.changeWorkingDirectory(ftpCfg.getWorkdir())) {
					throw new Exception("切换到指定目录失败！");
				}

				ftp.setFileType(FTP.BINARY_FILE_TYPE);// 以BINARY格式传送,解决乱码问题！
				
				//每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
				ftp.enterLocalPassiveMode();
				//ftp.enterRemotePassiveMode();
				input = ftp.retrieveFileStream(fileName);
				
				
				if (input != null) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] buffer = new byte[1024];  
					int len;  
					while ((len = input.read(buffer)) > -1 ) {  
						baos.write(buffer, 0, len);  
					}  
					baos.flush();
					
					stream1 = new ByteArrayInputStream(baos.toByteArray());
				}
				
				//app测试不知为何不关闭，在外面关闭也不行，执行完retrieveFileStream之后会卡死
				if (input != null) {
					input.close(); 
				}
				
				//ftp.completePendingCommand();
				// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
				//ftp.getReply();
				
				if (logger.isInfoEnabled()) {
					logger.info("下载文件成功!");
				}

				ftp.logout();
			} catch (Exception e) {
				logger.error("下载文件异常：", e);
			} finally {
				// 退出登陆、断开与服务端的连接
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
			return stream1;
		}else if(LOCAL_TYPE.equals(ftpCfg.getType())){
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(ftpCfg.getWorkdir() + File.separator + fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return inputStream;
		}
		return null;
	}	
	
	/**
	 * 
	 * @date 2017年4月25日 下午8:59:56
	 * 
	 * @Description: 把流写到文件里面去
	 * @param ins
	 * @param file
	 * @throws IOException
	 *
	 */
	public static void inputStreamToFile(InputStream ins, File file) throws IOException {
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
	}
	
	public static void main(String[] args) throws SocketException, IOException {
		FTPClient ftp = new FTPClient();
		int replyCode = 0;		
		// 连接FTP服务端
		//ftp.connect("120.24.251.230", 21);
		ftp.connect("112.74.79.74", 21);
		replyCode = ftp.getReplyCode();
		if (replyCode != FTPReply.SERVICE_READY) {
			System.out.println("连不上FTP服务端！");
		}
		//boolean b = ftp.login("ftpfile","RZftpfile");
		boolean b = ftp.login("ftpuser","ftpuser");
		System.out.println("login:"+b);
		
		System.out.println("列出用户登录后所在目录下的目录：");
		FTPFile[] ff = ftp.listDirectories();
		for (int i = 0; i < ff.length; i++) {
			System.out.println(ff[i].getName());
		}
		
		//ftp.changeWorkingDirectory("/home/ftpfile/ruizhifile");
		ftp.changeWorkingDirectory("/wy/ftpfile");
		System.out.println("after change workdir------------------：");
		FTPFile[] ff2 = ftp.listDirectories();
		for (int i = 0; i < ff2.length; i++) {
			System.out.println(ff2[i].getName());
		}
		ftp.logout();
	}
}
