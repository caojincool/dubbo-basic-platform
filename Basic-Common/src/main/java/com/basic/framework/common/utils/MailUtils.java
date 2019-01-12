package com.basic.framework.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.PropertiesUtils;

/**
 * 
 *
 * @date 2017年11月30日 上午11:12:39
 * 
 * @Description: 发送邮件
 *
 */
public class MailUtils {
	
	private static Logger logger = LoggerFactory.getLogger(MailUtils.class);

	private static final String EMAIL_PROPERTIES_PATH = "/config/properties/mailServer.properties";
	
	private static String host = "";
	private static String username = "";
	private static String password = "";
	private static boolean debug = false;
	private static String DEBUG_TRUE = "true";
	
	private static MimeMessage mimeMessage;
	private static Session session;
	private static Transport transport;
	
	private static MailUtils inst = new MailUtils();
	
	private MailUtils(){
		logger.info("Mail init......");
		
		try{
			init();
		}catch(Exception e){
			logger.error("Mail init ERROR:{}", e);
		}
		
		logger.info("Mail init success......");
	}	
	
	public static MailUtils getInstance() {
		return inst;
	}
	
	/**
	 * 
	 * @date 2016年9月22日 上午9:47:48
	 * @author 杰
	 * @Description: 初始化sms
	 * @throws Exception
	 *
	 */
	private void init() throws Exception{
		
		Properties properties = PropertiesUtils.loadProperties(EMAIL_PROPERTIES_PATH);//读取SMS配置
		
		host = properties.getProperty("mail.smtp.host");
		username = properties.getProperty("mail.sender.username");
		password = properties.getProperty("mail.sender.password");
		String debugStr = properties.getProperty("mail.debug");
		if(StringUtils.isNotBlank(debugStr)){
			if(DEBUG_TRUE.equals(debugStr)){
				debug = true;
			}else{
				debug = false;
			}
		}
		
        session = Session.getInstance(properties);//根据参数配置，创建会话对象
        session.setDebug(debug);//开启后是否有调试信息
        mimeMessage = new MimeMessage(session);//创建邮件对象
        
		if(StringUtils.isBlank(host) || StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			throw new Exception("Mail init ERROR");
		}else{
			logger.info("Mail init success......");
		}
	}

	/**
	 * 
	 * @date 2017年11月30日 下午3:35:16
	 * 
	 * @Description: 发送邮件
	 * @param subject 主题
	 * @param content 内容
	 * @param receiveUsers 接收人
	 * @param attachments 附件
	 * @throws Exception
	 *
	 */
	public void sendEmail(String subject, String content, String[] receiveUsers, List<File> files) throws Exception{
        try {
        	
        	if(StringUtils.isBlank(subject)){
    			throw new NullPointerException("subject主题不能为空："+subject);
    		}
        	if(StringUtils.isBlank(content)){
        		throw new NullPointerException("content内容不能为空："+subject);
        	}
    		if(receiveUsers == null || receiveUsers.length == 0){
    			throw new NullPointerException("receiveUsers接收人不能为空！");
    		}
    		
            //发件人
            InternetAddress from = new InternetAddress(username);
            mimeMessage.setFrom(from);

            // 收件人
            InternetAddress[] to = new InternetAddress[receiveUsers.length];
            for(int i=0;i<receiveUsers.length;i++){
            	to[i] = new InternetAddress(receiveUsers[i]);
            }
            mimeMessage.addRecipients(Message.RecipientType.TO, to);
            
            // 邮件主题
            mimeMessage.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            // 添加附件的内容
            if (files!=null&&files.size()>0){
	            for(File file : files){
		            if (file != null) {
		                BodyPart attachmentBodyPart = new MimeBodyPart();
		                DataSource source = new FileDataSource(file);
		                attachmentBodyPart.setDataHandler(new DataHandler(source));
		                
		                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
		                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
		                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
		                //attachmentBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
		                //MimeUtility.encodeWord可以避免文件名乱码
		                attachmentBodyPart.setFileName(MimeUtility.encodeText(file.getName(),"UTF-8", "B"));
		                multipart.addBodyPart(attachmentBodyPart);
		            }
	            }
            }
            
            // 将multipart对象放到message中
            mimeMessage.setContent(multipart);
            
            // 保存邮件
            mimeMessage.saveChanges();

            //获取邮件传输对象
            transport = session.getTransport();
            
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(host, username, password);
            
            // 发送
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        } catch (Exception e) {
            throw e;
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                	throw e;
                }
            }
        }
	}

	/**
	 * 
	 * @date 2017年11月30日 下午3:35:04
	 * 
	 * @Description: 测试方法
	 * @param args
	 *
	 */
	public static void main(String[] args){
		MailUtils mailUtils = MailUtils.getInstance();
		String subject = "测试发送邮件";
		String content = "这里是邮件内容，你收到了吗？";
		String[] receiveUsers = new String[2];
		receiveUsers[0] = "*********@qq.com";
		receiveUsers[1] = "*********@qq.com";
		List<File> files = new ArrayList<File>();
		File file1 = new File("D:\\jie\\桌面\\问题汇总.txt");
		File file2 = new File("D:\\jie\\桌面\\1.html.bak");
		files.add(file1);
		files.add(file2);
		try {
			mailUtils.sendEmail(subject, content, receiveUsers, files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
