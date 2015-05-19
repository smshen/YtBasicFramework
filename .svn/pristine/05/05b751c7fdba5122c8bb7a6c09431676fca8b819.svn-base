package com.yitong.service;

import java.util.List;

import org.apache.http.cookie.Cookie;

import android.content.Context;

import com.yitong.http.AsyncHttpClient;
import com.yitong.http.ResponseHandlerInterface;
import com.yitong.service.YTAsyncHttpClient.EncryptDelegate;

/**
 * 异步网络请求接口
 * @Author lewis(lgs@yitong.com.cn) 2014-1-17 下午1:19:23
 * Copyright (c) 2014 Shanghai P&C Information Technology Co.,Ltd.
 */
public class APPRestClient {
	private static final String LOG_TAG = "APPRestClient";
	
	private static YTAsyncHttpClient client;

	static {
		client = new YTAsyncHttpClient(20000, 30000, 3);
		getAsyncHttpClient().addHeader("user-agent", "android");
	}
	
	/**
	 * 设置单证书认证
	 * @param context
	 * @param cert 必须存放在assert目录下的证书文件
	 */	
	public static void setTrustSingleCertificate(Context context, String cert) {

		client.setSSLSocketFactory(
				YTSSLSocketFactory.getTrustSingleSSLFactory(context, cert));
	}
	
	/**
	 * 设置单向认证所有证书
	 */
	public static void setTrustAllCertificate() {
		client.setSSLSocketFactory(
				YTSSLSocketFactory.getTrustAllSSLFactory());
	}

	/**
	 * 获取 AsyncHttpClient
	 */
	public static AsyncHttpClient getAsyncHttpClient() {
		return client.getAsyncHttpClient();
	}
	
	/**
	 * 获取客户端cookie列表
	 */
	public static List<Cookie> getCookieList() {
		return client.getCookieList();
	}
	
	/**
	 * 表单get提交方式(无context)
	 * @param params 保存key:value的对象
	 */
	public static void get(String url, YTRequestParams params, ResponseHandlerInterface responseHandler) {
		client.get(url, params, responseHandler);
	}
	
	/**
	 * 表单post提交方式(无context)
	 * @param params 保存key:value的对象
	 */
	public static void post(String url, YTRequestParams params, ResponseHandlerInterface responseHandler) {
		client.post(url, params, responseHandler);
	}

	/**
	 * 表单post提交方式
	 * @param params 保存key:value的对象
	 */
	public static void post(Context context, String url, YTRequestParams params, ResponseHandlerInterface responseHandler) {
		client.post(context, url, params, responseHandler);
	}
	
	/**
	 * 字符串post提交方式(无context)
	 * @param params String字符串
	 */
	public static void post(String url, String params, ResponseHandlerInterface responseHandler) {
		client.post(null, url, params, null, responseHandler);
	}	

	/**
	 * 字符串post提交方式
	 * @param params String字符串
	 */
	public static void post(Context context, String url, String params, ResponseHandlerInterface responseHandler) {
		client.post(context, url, params, null, responseHandler);
	}

	/**
	 * 销毁activity使用
	 */
	public static void cancelRequests(Context context) {
		client.cancelRequests(context);
	}
	
	/**
	 * 设置加密代理
	 * @param delegate
	 */
	public static void setEncryptDelegate (EncryptDelegate delegate) {
		client.setEncryptDelegate(delegate);
	}
}