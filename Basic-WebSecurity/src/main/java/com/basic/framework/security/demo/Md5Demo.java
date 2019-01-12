package com.basic.framework.security.demo;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * Created by lzj on 2017/6/5.
 */
public class Md5Demo {


    public static void main(String args[]){
        MessageDigestPasswordEncoder md5 = new MessageDigestPasswordEncoder("MD5");
        String code = md5.encodePassword("1234",null);
        System.out.println(code);
    }
}
