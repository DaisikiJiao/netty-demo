package com.lxx.nettyclientdemo.factory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by luox on 2018/7/25.
 */
public class ClientKeyFactory {

    private static final String PROTOCLO = "SSL";
    private static final SSLContext CLIENT_CONTEXT;

    static {
        SSLContext clientContext=null;
        try {
            TrustManager[] trustAllCerts = new TrustManager[1];
            trustAllCerts[0] = new myTrustManager();

            clientContext = SSLContext.getInstance(PROTOCLO);
            clientContext.init(null,trustAllCerts,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CLIENT_CONTEXT = clientContext;
    }

    /**
     * 自定义信任管理类，忽略服务端证书
     */
    static class myTrustManager implements TrustManager, X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public static SSLContext getClientContext(){
        return CLIENT_CONTEXT;
    }

}
