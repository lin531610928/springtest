package com.zilin.springtest.ws;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class NettyServerListener implements ServletContextListener {

    @Autowired
    private EchoServer nettyServer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread thread = new Thread(new NettyServerThread());
        thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private class NettyServerThread implements Runnable {
        @Override
        public void run() {
            nettyServer.run();
        }
    }
}
