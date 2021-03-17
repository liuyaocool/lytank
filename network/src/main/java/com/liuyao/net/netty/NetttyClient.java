package com.liuyao.net.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NetttyClient {

    public static void main(String[] args) {
        // 线程池  client 设置1即可 处理connect
        EventLoopGroup group = new NioEventLoopGroup(1);
        // 辅助启动类
        Bootstrap b = new Bootstrap();

        try {
            ChannelFuture future = b.group(group)
                    .channel(NioSocketChannel.class) // 可以换成bio
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println(ch);

                        }
                    })
                    .connect("127.0.0.1", 8888);  // 异步方法

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture cf) throws Exception {
                    if (!cf.isSuccess()) {
                        System.out.println("connect faild");
                    } else {
                        System.out.println("connect success");
                    }
                }
            });
            future.sync(); // 等结束
            System.out.println("....");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅的结束
            group.shutdownGracefully();
        }

    }
}
