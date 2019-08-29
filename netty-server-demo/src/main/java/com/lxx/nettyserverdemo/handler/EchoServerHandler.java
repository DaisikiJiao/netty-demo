package com.lxx.nettyserverdemo.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luox on 2018/7/23.
 */
public class EchoServerHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        // Send back the received message to the remote peer.
        ChannelBuffer buffer=(ChannelBuffer)e.getMessage();
        String str;
        str=isAllow(e.getRemoteAddress().toString())?"服务端：验证成功":"服务端：验证失败";
        buffer.writeBytes(str.getBytes("UTF-8"));
        e.getChannel().write(buffer);
        ctx.sendUpstream(e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("客户端"+e.getValue()+"连接");
        ctx.sendUpstream(e);
    }

    boolean isAllow(String address){
        List<String> whiteBoard = new ArrayList<>();
        whiteBoard.add("127.0.0.0");

        return whiteBoard.contains(address);
    }

}
