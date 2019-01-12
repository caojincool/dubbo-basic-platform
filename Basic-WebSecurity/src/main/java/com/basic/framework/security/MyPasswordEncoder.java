/**
 * 
 */
package com.basic.framework.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.codec.Utf8;

/**
 * 
 *
 * @date 2017年11月8日 下午5:40:52
 * 
 * @Description: 可定义自己的加密规则
 * ps：返回true即为验证通过
 *
 */
public class MyPasswordEncoder extends Md5PasswordEncoder {
	/***
	 * encPass 加密后的密码
	 * rawPass 原生的密码
	 */
	@Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {

		try {
			String pass1 = "" + encPass;
			String pass2 = encodePassword(rawPass, salt);// ERP的MD5加密
			boolean erp = equals(pass1, pass2);
			if (erp) {
				return true;
			} else if(equals(encPass, rawPass)) {
				return true;
			}else {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte digest[] = md.digest(rawPass.getBytes("UTF-8"));
				String oaPass = new String(Base64.encodeBase64(digest));
				return equals(pass1, oaPass);
			}
		} catch (Exception e) {
			return false;
		}
	}
    
   public boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
        int actualLength = actualBytes == null ? -1 : actualBytes.length;
        if (expectedLength != actualLength) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedLength; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }
   
   private  byte[] bytesUtf8(String s) {
       if(s == null) {
           return null;
       }

       return Utf8.encode(s);
   }
    
   public  void main() {
	   String rawPass = "1234";
	   String pass1 = "A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=";
	   MessageDigest md;
	try {
		md = MessageDigest.getInstance("SHA-256");
		byte digest[] = md.digest(rawPass.getBytes("UTF-8"));
		String oaPass = new String(Base64.encodeBase64(digest));
		System.out.println(oaPass);
		System.out.println(equals(pass1, oaPass));
	} catch (Exception e) {
		e.printStackTrace();
	}
		
}
}
