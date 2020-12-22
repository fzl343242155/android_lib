package com.chichapaofan.library.glide;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author 工藤
 * @email 18883840501@163.com
 * cc.shinichi.library.glide.progress
 * create at 2018/11/2  15:55
 * description:
 */
public class SSLSocketClient {

    public static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static TrustManager[] getTrustManager() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        for (X509Certificate cert : chain) {
// Make sure that it hasn't expired.
                            try {
                                cert.checkValidity();
                            } catch (CertificateExpiredException e) {
                                e.printStackTrace();
                            } catch (CertificateNotYetValidException e) {
                                e.printStackTrace();
                            }
// Verify the certificate's public key chain.
                            try {
                                cert.verify(cert.getPublicKey());
                            } catch (NoSuchAlgorithmException e) {
                            } catch (InvalidKeyException e) {
                            } catch (NoSuchProviderException e) {
                            } catch (SignatureException e) {
                            } catch (CertificateException e) {
                            }
                        }
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    public static X509TrustManager geX509tTrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
                for (X509Certificate cert : chain) {
// Make sure that it hasn't expired.
                    try {
                        cert.checkValidity();
                    } catch (CertificateExpiredException e) {
                        e.printStackTrace();
                    } catch (CertificateNotYetValidException e) {
                        e.printStackTrace();
                    }
// Verify the certificate's public key chain.
                    try {
                        cert.verify(cert.getPublicKey());
                    } catch (NoSuchAlgorithmException e) {
                    } catch (InvalidKeyException e) {
                    } catch (NoSuchProviderException e) {
                    } catch (SignatureException e) {
                    } catch (CertificateException e) {
                    }
                }
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
    }

    public static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                if("Demohostname".equals(hostname)){
                    return true;
                } else {
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    return hv.verify(hostname, session);
                }
            }
        };
    }
}
