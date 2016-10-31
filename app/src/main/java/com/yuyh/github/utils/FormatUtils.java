package com.yuyh.github.utils;

import java.text.DecimalFormat;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public class FormatUtils {

    public static String formatTosepara(float data) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(data);
    }
}
