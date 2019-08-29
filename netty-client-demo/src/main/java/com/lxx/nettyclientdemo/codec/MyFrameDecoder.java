package com.lxx.nettyclientdemo.codec;

import com.lxx.nettyclientdemo.entity.MyMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import java.nio.charset.Charset;

/**
 * Created by luox on 2018/7/30.
 */
public class MyFrameDecoder extends FrameDecoder {

    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        if (channelBuffer.readableBytes() < 4) {
            return null;
        }
        return new MyMessage(channelBuffer.toString(Charset.forName("UTF-8")));
    }

}
