package com.qufu.utils;

public class NameUtil {
    /**
     * 首字母转大写
     *
     * @param varName
     * @return
     */
    public static String firstUpper(String varName) {
        StringBuilder varSb = new StringBuilder();
        String firstAlp = varName.substring(0, 1).toUpperCase();
        varSb.append(firstAlp);
        //追加其他字母
        varSb.append(varName.substring(1, varName.length()));
        return varSb.toString();
    }
}
