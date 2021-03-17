package com.liuyao.net.nio;

import com.liuyao.utils.IOUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

public class NioPoolServer {

    static ExecutorService pool = new ThreadPoolExecutor(
            50, 50,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

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
            // 相当于remove 位运算写法
            key.interestOps(key.interestOps() & (-SelectionKey.OP_READ));
            pool.execute(new NioNotAcceptHandler(key));
        }
    }


}

class NioNotAcceptHandler implements Runnable {

    SelectionKey key;
    public NioNotAcceptHandler(SelectionKey key) {
        this.key = key;
    }

    @Override
    public void run() {
        SocketChannel channel = (SocketChannel) this.key.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            int size = 0;
            while ((size = channel.read(buf)) > 0) {
                buf.flip();
                baos.write(buf.array(), 0, size);
                buf.clear();
            }

            byte[] content = baos.toByteArray();
            ByteBuffer writeBuf = ByteBuffer.allocate(content.length);
            writeBuf.put(content);
            writeBuf.flip();
            channel.write(writeBuf);
            if (-1 == size) {
                channel.close();
            } else {
                this.key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                this.key.selector().wakeup();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(baos);
        }

    }
}
