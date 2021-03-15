package com.liuyao.design_patterns.adapter;

import java.io.*;

public class MainAdapter {

    /**
     * adapter/warpper
     * 包装器/接口转换器
     *
     * 例子
     *  电压转接头
     *
     * 误区
     *  常见的***Adapter类反而不是Adapter
     *      如：WindowAdapter  KeyAdapter
     *      只是为了方便编程
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("c:/test.text");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr); // br为包装器
        String line = br.readLine();
        while (line != null && !line.equals("")) {
            System.out.println(line);
        }
        br.close();


    }
}
