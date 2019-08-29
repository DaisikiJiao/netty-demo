package com.lxx.nettyclientdemo.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.DefaultHttpMessage;
import org.jboss.netty.handler.codec.http.HttpMessage;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * Created by luox on 2018/7/23.
 */
public class EchoClientHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        // Send back the received message to the remote peer.
        ChannelBuffer channelBuffer=(ChannelBuffer)e.getMessage();
        String message=channelBuffer.toString(Charset.forName("UTF-8"));
        System.out.println(message);
        ctx.sendUpstream(e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("成功连接服务器.");
        String message = "hello,I'm Client";
        ChannelBuffer buffer = ChannelBuffers.buffer(message.length());
        buffer.writeBytes(message.getBytes("UTF-8"));
        e.getChannel().write(buffer);
        ctx.sendUpstream(e);
    }

}
