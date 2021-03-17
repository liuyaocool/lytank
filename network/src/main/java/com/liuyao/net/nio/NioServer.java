package com.liuyao.net.nio;

import com.liuyao.utils.IOUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) {
        try {
            // 双向封装
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
            ssc.configureBlocking(false);

            System.out.println("server started, listing " + ssc.getLocalAddress());
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    handle(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handle(SelectionKey key) {
        if (key.isAcceptable()) {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            try {
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(key.selector(), SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (key.isReadable()) {
            SocketChannel sc = null;
            try {
                sc = (SocketChannel) key.channel();
                ByteBuffer buf = ByteBuffer.allocate(512);
                buf.clear();
                int len = sc.read(buf);

                if (len != -1) {
                    System.out.println(new String(buf.array(), 0, len));
                }
                ByteBuffer bufToWrite = ByteBuffer.wrap("nio readable back".getBytes());
                sc.write(bufToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtil.close(sc);
            }


        }
    }


}
