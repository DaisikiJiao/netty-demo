package com.lxx.nettyclientdemo.Server;

import com.lxx.nettyclientdemo.factory.ClientKeyFactory;
import com.lxx.nettyclientdemo.handler.EchoClientHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by luox on 2018/7/23.
 */
public class EchoClient {

    private String remoteAdress;
    private int port;

    public EchoClient(String remoteAdress,int port) {
        this.remoteAdress = remoteAdress;
        this.port = port;
    }

    public void run() {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline p = Channels.pipeline();
                SSLEngine engine = ClientKeyFactory.getClientContext().createSSLEngine();
                engine.setUseClientMode(true);
                //添加客户端SSL加密支持
                p.addLast("ssl",new SslHandler(engine));
                p.addLast("echoClientHandler",new EchoClientHandler());

                return p;
            }
        });
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        bootstrap.connect(new InetSocketAddress(remoteAdress, port));

    }

}
