package com.lxx.nettyserverdemo.Server;

import com.lxx.nettyserverdemo.factory.ServerKeyFactory;
import com.lxx.nettyserverdemo.handler.EchoServerHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by luox on 2018/7/23.
 */
public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port=port;
    }

    public void run() {
        // Server服务启动器
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // 设置PipelineHandler组装工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline p = Channels.pipeline();
                SSLEngine engine = ServerKeyFactory.getServerContext().createSSLEngine();
                engine.setUseClientMode(false);
                engine.setNeedClientAuth(false);
                //添加服务端SSL加密支持
                p.addLast("ssl",new SslHandler(engine));
                p.addLast("echoServerHandler", new EchoServerHandler());
                return p;
            }
        });
        // 绑定指定端口并开始监听请求
        bootstrap.bind(new InetSocketAddress(port));
        System.out.println("EchoServer Start!");
    }

}
