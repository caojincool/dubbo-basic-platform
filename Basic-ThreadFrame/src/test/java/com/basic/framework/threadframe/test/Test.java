package com.basic.framework.threadframe.test;

import com.basic.framework.common.utils.datatype.DoubleUtils;

/**
 * Created by lzj on 2017/7/1.
 */
public class Test {
    public static void main(String[] args){
        String s = "536596.978708136";
        long l = DoubleUtils.valueOf(s).longValue();
        System.out.println(l);
    }
}
