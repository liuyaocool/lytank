package com.liuyao.net.httplike;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class NIOHTTPServer {

    private IHandler handler;
    private ServerSocketChannel ssc;

    private AtomicInteger count = new AtomicInteger(0);

    public NIOHTTPServer(int port) {

        try {
            this.ssc = ServerSocketChannel.open();
            this.ssc.bind(new InetSocketAddress(port));
            this.ssc.configureBlocking(false);

            Selector selector = Selector.open();
            // 第二个参数代表所有操作
            this.ssc.register(selector, ssc.validOps(), null);

            // buffer 读数据 16k
            ByteBuffer buf = ByteBuffer.allocate(1024 * 16);

            // 比while（true） 少一次判断
            for (;;) {
                int keyNum = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        // 如果有连接 建立通道 注册到selector
                        SocketChannel channel = this.ssc.accept();
                        if (null == channel) {
                            continue;
                        }

                        // 通道非阻塞 kernel -> mmap(bufer) -> channel -> user(buffer)
                        channel.configureBlocking(false);
                        // 通道注册
                        channel.register(selector, SelectionKey.OP_READ);

                    } else if (key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();

                        // ---------
                        //        P(position 指向下一个可写位置)
                        //        L
                        // 把P位置清零 让buffer可从头写 并不清空数据
                        buf.clear();

                        channel.read(buf);
                        String req = new String(buf.array());
                        System.out.println(req);
                        // logic

                        buf.clear();
                        buf.put("HTTP/1.1 200 ok\n\nHeool NIO!".getBytes());
                        // HTTP/1...!--
                        //           P(L)
                        // P         L
                        // P 恢复到上行位置 channel.write(buf) 才能读取到数据
                        // 否则 将从L位置开始读 L后边为空
                        buf.flip();

                        channel.write(buf);

                        channel.close();
                    }


                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        new NIOHTTPServer(8888);
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
