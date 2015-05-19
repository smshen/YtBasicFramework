package com.yitong.service;


public class ServiceUrlManager {

	// 接口服务器请求地址
	private static String SERVICE_BASE_URL = "http://172.30.76.1:8080/fgjx";
	
	// 资源服务器请求地址
	private static String RESOURCE_BASE_URL = "";
	
	public static String getServiceBaseUrl() {
		return SERVICE_BASE_URL;
	}
	
	public static void SetServiceBaseUrl(String url) {
		SERVICE_BASE_URL = url;
	}
	
	public static String getResourceBaseUrl() {
		return RESOURCE_BASE_URL;
	}
	
	public static void SetResourceBaseUrl(String url) {
		RESOURCE_BASE_URL = url;
	}
	
	/**
	 * 获取网络请求基地址
	 */
	public static String getAbsoluteUrl(String relativeUrl) {
		return SERVICE_BASE_URL + relativeUrl;
	}  
}
