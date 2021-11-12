package com.lph.exam_web.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Salt {

    //pwd是需要加密的字符，salt是盐值（自定义），hashNum是加密次数，次数越多越安全
    public String getMD5(String pwd){
//        盐,不可更改!!!
        String salt = "I_AM_LPH";
        //加密类型
        String hashName = "MD5";
        //加密次数
        int hashNum = 1024;
        return salt = new SimpleHash(hashName, pwd, salt, hashNum).toHex();
        /**
         * 获取密码
         * MD5.getMD5（pwd,salt）.toString();
         */
    }
}
