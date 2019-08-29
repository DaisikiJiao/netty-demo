package com.lxx.nettyserverdemo.factory;

import org.jboss.netty.util.internal.SystemPropertyUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * Created by luox on 2018/7/25.
 */
public class ServerKeyFactory {

    private static final String PROTOCLO = "SSL";
    private static final SSLContext SERVER_CONTEXT;
    private static final String SERVER_KEY_STORE="\\ssl\\sslserverkeys";
    private static final String SERVER_KEY_PASSWORD = "dragon";

    static {
        String algorithm = SystemPropertyUtil.get("ssl.KeyManagerFactory.algorithm");
        if (algorithm == null) {
            algorithm = "SunX509";
        }
        SSLContext serverContext=null;
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(SERVER_KEY_STORE),SERVER_KEY_PASSWORD.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
            kmf.init(ks,SERVER_KEY_PASSWORD.toCharArray());
            serverContext = SSLContext.getInstance(PROTOCLO);
            serverContext.init(kmf.getKeyManagers(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SERVER_CONTEXT = serverContext;
    }

    public static SSLContext getServerContext(){
        return SERVER_CONTEXT;
    }

}
