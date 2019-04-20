package com.example.bbzn.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 生成验证码
 */
public class DataGeneratorUtil {

    // 随机UUID
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    // 生成6位随机数字验证码
    public static String generateSmsCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    // 生成指定长度的随机数字串
    public static String generateRandomStr(int length) {
        return RandomStringUtils.random(length, new char[]{'0', '1', '2',
                '3', '4', '5', '6', '7', '8', '9'});
    }

    // 生成隐藏部分字符串的字符串
    public static String generateReplaceStr(int iPreLen, int iPosLen, String strRePlace, String strObj) {

        return strObj.substring(0, iPreLen) + strRePlace + strObj.substring(strObj.length() - iPosLen, strObj.length());

    }

    // 生成指定范围的随机数
    public static int generateRandomNumWithMinMax(final int min, final int max) {

        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    // 生成指定长度的字符和数字组合的字符串
    public static String generateRandomStrAndNum(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数  
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字  
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    //返回当期日期的字符串(如:190410103335)
    public static String timeString(){
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = dFormat.format(new Date());
        String delStr = "";
        char delChar = '-';
        for (int i = 0; i < formatDate.length(); i++) {
            if(formatDate.charAt(i) != delChar){
                delStr += formatDate.charAt(i);
            }
        }
        String delStr1 = "";
        delChar = ':';
        for (int i = 0; i < delStr.length(); i++) {
            if(delStr.charAt(i) != delChar){
                delStr1 += delStr.charAt(i);
            }
        }
        String delStr2 = "";
        delChar = ' ';
        for (int i = 0; i < delStr1.length(); i++) {
            if(delStr1.charAt(i) != delChar){
                delStr2 += delStr1.charAt(i);
            }
        }
        delStr2 = delStr2.substring(2,14);
        return delStr2;
    }
}
