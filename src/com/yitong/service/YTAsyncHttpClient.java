package com.yitong.service;

import java.util.List;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;

import android.content.Context;

import com.yitong.http.AsyncHttpClient;
import com.yitong.http.ResponseHandlerInterface;
import com.yitong.logs.Logs;

/**
 * AsyncHttpClient异步请求封装
 * @author tongxu_li
 * Copyright (c) 2014 Shanghai P&C Information Technology Co., Ltd.
 */
public class YTAsyncHttpClient {
	private static final String LOG_TAG = "YTAsyncHttpClient";
	
	private AsyncHttpClient client = null;	
	private EncryptDelegate mEncryptDelegate = null;
	
	public YTAsyncHttpClient() {
		client = new AsyncHttpClient();
	}
	
	public YTAsyncHttpClient(int connectTime, int socketTime, int maxExecute) {
		client = new AsyncHttpClient();
		client.setConnectTimeout(connectTime);
		client.setResponseTimeout(socketTime);
		client.setThreadPool(Executors.newFixedThreadPool(maxExecute));
	}
	
	/**
	 * 设置ssl认证规则
	 */
	public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {		
		if (sslSocketFactory != null) {
			client.setSSLSocketFactory(sslSocketFactory);			
		}	
	}

	/**
	 * 获取 AsyncHttpClient
	 */
	public AsyncHttpClient getAsyncHttpClient() {
		return client;
	}
	
	/**
	 * 获取客户端cookie列表
	 */
	public List<Cookie> getCookieList() {
		AbstractHttpClient abClient = (AbstractHttpClient)client.getHttpClient();
		return abClient.getCookieStore().getCookies();
	}
	
	/**
	 * get提交方式(无context)
	 * 封装参数信息，包括参数，参数类型
	 */
	public void get(String url, YTRequestParams params, ResponseHandlerInterface responseHandler) {
		StringBuilder absoluteUrl = new StringBuilder();
		absoluteUrl.append(url);
		if(params != null) {
			String paramsString = params.getParamsString();
			if (paramsString != null 
					&& !paramsString.equals("")) {
				absoluteUrl.append("?");
				absoluteUrl.append(paramsString);
			}			
		}
		client.get(absoluteUrl.toString(), responseHandler);
	}
	
	/**
	 * post提交方式(无context)
	 * @param params 封装参数信息，包括参数，参数类型，以及加密规则。
	 */
	public void post(String url, YTRequestParams params, ResponseHandlerInterface responseHandler) {
		post(null, url, params, responseHandler);
	}

	/**
	 * post提交方式
	 * @param params 封装参数信息，包括参数，参数类型，以及加密规则。
	 */
	public void post(Context context, String url, YTRequestParams params, ResponseHandlerInterface responseHandler) {
		Logs.d("TAG", "url------>"+url);
		if (params == null) {
			post(context, url, null, null, responseHandler);
			return;
		}
		
		String paramsString = getEncyptString(params);
		if (params.getParamType() == YTRequestParams.PARAM_TYPE_FORM) {
			post(context, url, paramsString, "application/x-www-form-urlencoded", responseHandler);
		} else if (params.getParamType() == YTRequestParams.PARAM_TYPE_JSON) {
			post(context, url, paramsString, "application/json", responseHandler);
		} else {
			post(context, url, paramsString, null, responseHandler);
		}
	}
	
	/**
	 * post 提交方式
	 * 参数是String字符串，并且设置contentType
	 */
	public void post(Context context, String url, String params, String contentType, ResponseHandlerInterface responseHandler) {
		HttpEntity entity = APPRestParamsToEntity.paramsToEntity(params);
		client.post(context, url, entity, contentType, responseHandler);
	}

	/**
	 * 销毁activity使用
	 */
	public void cancelRequests(Context context) {
		client.cancelRequests(context, true);
	}
	
	/**
	 * 根据加密代理规则生成字符串，无代理则不加密。
	 */
	private String getEncyptString(YTRequestParams params) {
		if (params == null) {
			return "";
		}
		String paramsString = params.getParamsString();
		if (paramsString == null || paramsString.equals("")) {
			return "";
		}
		String encyptString = paramsString;
		EncryptDelegate encryptDelegate = params.getEncryptDelegate();
		if (encryptDelegate != null) {	// 设置了单个请求的加密规则
			encyptString = encryptDelegate.getEncryptString(paramsString);
		} else {	// 设置全局加密规则
			encryptDelegate = this.mEncryptDelegate;
			if (encryptDelegate != null) {
				encyptString = encryptDelegate.getEncryptString(paramsString);
			}
		}
		
		return encyptString;
	}
	
    public void setEncryptDelegate (EncryptDelegate delegate) {
    	this.mEncryptDelegate = delegate;
    }
	
    /**
     * 设置加密代理
     * @author tongxu_li
     * Copyright (c) 2014 Shanghai P&C Information Technology Co., Ltd.
     */	
	public interface EncryptDelegate {
    	/**
    	 * 对获取的密文进行处理
    	 * @param decryptString
    	 */		
		public String getEncryptString(String decryptString);
	}
}