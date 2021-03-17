package com.liuyao.utils;

public class IOUtil {

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable c: closeables){
            if (null != c) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
