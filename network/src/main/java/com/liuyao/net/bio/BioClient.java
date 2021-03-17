package com.liuyao.net.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioClient {

    public static void main(String[] args) {
        try {
            Socket s = new Socket();
            s.getOutputStream().write("hello".getBytes());
            s.getOutputStream().flush();

            System.out.println("write over, wating back...");

            byte[] bytes = new byte[1024];
            int len = s.getInputStream().read(bytes);
            System.out.println(new String(bytes, 0, len));
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
