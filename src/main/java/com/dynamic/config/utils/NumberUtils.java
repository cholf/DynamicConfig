package com.dynamic.config.utils;

/**
 * Created by IntelliJ IDEA.
 * User: gangwen.xu
 * Date: 17-7-14
 * Time: 下午5:10
 * 类描述: 数字相关工具处理
 */
public class NumberUtils {

    /**
     * 字符转int--默认异常返回0
     * 
     * @param str 入参字符
     * @return int
     */
    public static int toInt(String str) {
        return toInt(str, 0);
    }

    /**
     * 字符转int -- 默认返回default值
     * 
     * @param str 入参字符
     * @param def 默认值
     */
    public static int toInt(String str, int def) {
        if (str == null)
            return def;
        try {
            return Integer.parseInt(str, 10);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
