package com.liuyao.net.httplike;

import com.liuyao.utils.IOUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class HTTPServer implements Runnable {

    private IHandler handler;
    private ServerSocket serverSocket;
    private AtomicInteger count = new AtomicInteger(0);

    public HTTPServer(int port, IHandler handler) {
        try {
            this.handler = handler;
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println();
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                final Socket s = socket;
                System.out.println("--- a socket connected.");
                new Thread(() -> this.handler(s), "Accept-" + count.incrementAndGet()).start();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    private void handler(Socket socket) {
        Request req = new Request(socket);
        Response resp = new Response(socket);

        this.handler.handler(req, resp);
    }


    public static void main(String[] args) {

        new HTTPServer(8888, (req, resp) -> {
            threadPrint(req.getMethod());
            resp.send("<h1>hello world!</h1>");
        }).run();
    }

    public static void threadPrint(String msg) {
//        new Thread(() ->
                System.out.println(Thread.currentThread().getName()
                        + "(" + System.currentTimeMillis() + ") → "
                        + msg)
//        ).start()
        ;
    }
}
