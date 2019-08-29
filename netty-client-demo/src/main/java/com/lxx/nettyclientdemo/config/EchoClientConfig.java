package com.lxx.nettyclientdemo.config;

import com.lxx.nettyclientdemo.Server.EchoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luox on 2018/7/23.
 */
@Configuration
public class EchoClientConfig {

    @Bean(initMethod = "run")
    public EchoClient echoClient() {
        String remoteAdress = "127.0.0.1";
        int port=8888;
        return new EchoClient(remoteAdress,port);
    }

}
