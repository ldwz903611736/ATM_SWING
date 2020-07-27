package com.tyut.atm.util;

import java.util.UUID;

/**
 * @author by 李大娃子
 * @classname Util
 * @description TODO
 * @date 2020/7/24 15:23
 */
public class Util {

    /**
     * 随机生成16位银行卡号
     */
    public static String getUUID(){
        //随机生成一位整数
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%015d", hashCode);
        return value;
    }


}
