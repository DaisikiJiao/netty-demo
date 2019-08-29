package com.lxx.nettyclientdemo.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;

/**
 * Created by luox on 2018/7/30.
 */
public class MyFrameEncoder extends ReplayingDecoder {

    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, Enum anEnum) throws Exception {
        return null;
    }

}
