package com.yitong.service;

import org.apache.http.Header;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yitong.http.TextHttpResponseHandler;
import com.yitong.logs.Logs;

/**
 * 为服务端js代理向服务器发送请求，数据转发
 * @author tongxu_li
 * Copyright (c) 2014 Shanghai P&C Information Technology Co., Ltd.
 */
public abstract class AppJSResponseHandler extends TextHttpResponseHandler {	
	private static final String LOG_TAG = "AppJSResponseHandler";
				
	// 如果返回接口是密文，设置解密代理
	private DecryptDelegate mDecryptDelegate = null;
	
	public String successFunc = "";
	public String failureFunc = "";

    /**
     * 创建一个默认编码为utf-8对象
     */
    public AppJSResponseHandler() {
    	this(DEFAULT_CHARSET);
    }

    /**
     * 创建一个默认编码为指定编码的对象,该对象的回调结果集为指定泛型
     */
    public AppJSResponseHandler(String encoding) {
    	super(encoding);
    }
    
    /**
     * 
     * @param result
     * @param successFunc
     */
    public abstract void onSuccess(String result, String successFunc);

    /**
     * 
     * @param errorCode
     * @param failureFunc
     */
    public abstract void onFailure(int errorCode, String failureFunc);

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
    	Logs.d(LOG_TAG, "接口返回"+responseString);    	

    	// 返回结果解密
    	if (mDecryptDelegate != null) {			
			JsonObject jsonObject = new JsonParser().parse(responseString).getAsJsonObject();
			if (jsonObject.has("RSP")) {
				JsonElement jsonElement = jsonObject.get("RSP");
				responseString = jsonElement.getAsString();			
			}
    		responseString = mDecryptDelegate.getDecryptString(responseString);
    		Logs.d(LOG_TAG, "接口解密返回"+responseString);
    	}
    	
    	onSuccess(responseString, successFunc);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
    	onFailure(statusCode, failureFunc);
    }
        
    public void setDecryptDelegate (DecryptDelegate delegate) {
    	this.mDecryptDelegate = delegate;
    }
    
    /**
     * 设置解密代理
     * @author tongxu_li
     * Copyright (c) 2014 Shanghai P&C Information Technology Co., Ltd.
     */
    public interface DecryptDelegate {
    	/**
    	 * 对获取的密文进行处理
    	 * @param encryptString
    	 * @return
    	 */
    	public String getDecryptString(String encryptString); 
    }
}
