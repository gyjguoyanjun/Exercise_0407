package com.gyj.exercise_0407.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * data:2017/4/5
 * author:郭彦君(Administrator)
 * function:封装io流读取方法
 */
public class StreamUtils {
    public static String jsonToString(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, len);
            }
            return arrayOutputStream.toString("utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
