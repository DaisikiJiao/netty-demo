package com.lxx.nettyserverdemo.config;

import com.lxx.nettyserverdemo.Server.EchoServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luox on 2018/7/23.
 */
@Configuration
public class EchoServerConfig {

    @Bean(initMethod = "run")
    public EchoServer echoServer() {
        int port=8888;
        return new EchoServer(port);
    }

}
