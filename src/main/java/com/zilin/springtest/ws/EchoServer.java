package com.zilin.springtest.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EchoServer {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${netty.port}")
    private int port;

    public void run(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(group)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new NettyServerInitializer());
            LOGGER.info("netty服务器在[{}]端口启动监听", port);
            ChannelFuture cf = sb.bind(port).sync();
            LOGGER.info("netty服务启动: [port:" + port + "]");
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.info("[出现异常] 释放资源");
            group.shutdownGracefully();
        }finally {
            group.shutdownGracefully();
        }
    }
}
