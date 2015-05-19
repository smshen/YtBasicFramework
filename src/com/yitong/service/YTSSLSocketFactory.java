package com.yitong.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.conn.ssl.SSLSocketFactory;
import android.content.Context;
import com.yitong.http.MySSLSocketFactory;

public class YTSSLSocketFactory {
	
	/**
	 * 返回SSLSocketFactory，信任指定证书
	 */    
    public static SSLSocketFactory getTrustSingleSSLFactory(Context context, String certName) {
        SSLSocketFactory socketFactory = null;
        InputStream cerInput = null;
        KeyStore keyStore = null;
        try {
        	cerInput = context.getAssets().open(certName);
        	keyStore = MySSLSocketFactory.getKeystoreOfCA(cerInput);
        	if (keyStore != null) {
        		try {
					socketFactory = new SSLSocketFactory(keyStore);
				} catch (Exception e) {
					e.printStackTrace();
		        }        		
        	}
			
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            try {
                if (cerInput != null) {
                	cerInput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return socketFactory; 
    }
    
	/**
	 * 返回SSLSocketFactory，信任所有证书
	 */
    public static SSLSocketFactory getTrustAllSSLFactory() {
    	return MySSLSocketFactory.getFixedSocketFactory();
    }
}
