package com.liuyao.net.httplike;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RawHTTPServer {

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(8888);

            while (true) {
                System.out.println();
                System.out.println("--- wating for connect...");
                Socket socket = ss.accept();
                System.out.println("--- a socket connected.");

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(dis));

                StringBuilder sb = new StringBuilder();

                String line;

                while (!(line = br.readLine()).isBlank()) {
                    sb.append(line).append("\n");
                    System.out.println(line);
                }

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write("HTTP/1.1 200 ok\n\nHello World!\n");
                bw.flush();
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
