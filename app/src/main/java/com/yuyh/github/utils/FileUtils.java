package com.yuyh.github.utils;

import com.yuyh.github.GithubApp;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class FileUtils {

    public static String readAssetsFile(String fileName) {
        StringBuffer buffer = new StringBuffer();
        try {
            InputStream is = GithubApp.getsInstance().getAssets().open(fileName);
            byte[] read = new byte[1024];
            while (is.read(read) != -1) {
                buffer.append(new String(read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }


}
