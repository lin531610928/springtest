package com.zilin.springtest.ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zilin.springtest.entity.PrintDto;
import com.zilin.springtest.service.IPrintService;
import com.zilin.springtest.service.PrintS2;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.Map;

@Component
public class EchoServerHandler extends SimpleChannelInboundHandler<String> {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IPrintService printService;

    /*@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        System.out.println("server channelRead...; received" + msg);
        ctx.write(Unpooled.copiedBuffer("Netty rocks1", CharsetUtil.UTF_8));
    }*/

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        StringBuilder sb = null;
        Map<String, Object> result = null;
        PrintDto printDto = new PrintDto();
        try {
            // 报文解析处理
            sb = new StringBuilder();
            JSONObject jsonObject=JSON.parseObject(msg);
            System.out.println(jsonObject.getString("6"));
            printDto.setItemIs(jsonObject.getString("ItemIs"));
            printDto.setIsSmall(jsonObject.getString("IsSmall"));
            printDto.setItemSet(jsonObject.getString("ItemSet"));
            printDto.setItemGet(jsonObject.getString("ItemGet"));
            printDto.setNn(jsonObject.getString("N"));
            printDto.setS1(jsonObject.getString("1"));
            printDto.setS2(jsonObject.getString("2"));
            printDto.setS3(jsonObject.getString("3"));
            printDto.setS4(jsonObject.getString("4"));
            printDto.setS5(jsonObject.getString("5"));
            printDto.setS6(jsonObject.getString("6"));
            PrintS2 printS2=new PrintS2();
            int rrr = printS2.ServerSocketDemo(printDto);
            sb.append(""+rrr);
            sb.append("\n");
            ctx.writeAndFlush(sb);
        } catch (Exception e) {
            String errorCode = "-1\n";
            ctx.writeAndFlush(errorCode);
            log.error("报文解析失败: " + e.getMessage());
        }
        //printService.ServerSocketDemo(printDto);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        System.out.println("server channelReadComplete..");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        log.info("收到客户端[ip:" + clientIp + "]连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
        System.out.println("server occur exception:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
