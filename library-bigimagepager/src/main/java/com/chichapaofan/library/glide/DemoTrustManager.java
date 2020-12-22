package com.chichapaofan.library.glide;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

public class DemoTrustManager  implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        for (X509Certificate cert : chain) {
// Make sure that it hasn't expired.
            cert.checkValidity();
// Verify the certificate's public key chain.
            try {
                cert.verify( cert.getPublicKey());}
            catch (NoSuchAlgorithmException e) {}
            catch (InvalidKeyException e) {}
            catch (NoSuchProviderException e) {}
            catch (SignatureException e) {}
        }

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
