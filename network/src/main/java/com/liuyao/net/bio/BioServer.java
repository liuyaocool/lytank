package com.liuyao.net.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress("127.0.0.1", 8888));

            while (true) {
                Socket s = ss.accept();
                System.out.println("a client connect.");

                new Thread(() -> {
                    byte[] bytes = new byte[1024];
                    try {
                        int len = s.getInputStream().read(bytes);
                        System.out.println(new String(bytes, 0, len));

                        s.getOutputStream().write(bytes, 0 , len);
                        s.getOutputStream().flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
