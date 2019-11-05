package com.snail.framework.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomCodeUtil {

    /**
     * java生成随机数字和字母组合10位数
     * @param length [生成随机数的长度]
     * @return
     */
    public static String generateRandomCode(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 随机大小写
//                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // 固定大写
                int choice = 65;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static String generateApplyCode(String prefix) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
        SimpleDateFormat format = new SimpleDateFormat("ddHHmm");

        int min = 10000;
        int max = 99999;
        Random random = new Random();
        return prefix + format.format(new Date()) + "0000" + (random.nextInt(max) % (max - min + 1) + min);
    }

}
