package com.yitong.service;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.yitong.service.YTAsyncHttpClient.EncryptDelegate;

/**
 * 请求参数对象
 * @author tongxu_li
 * Copyright (c) 2014 Shanghai P&C Information Technology Co., Ltd.
 */
public class YTRequestParams {

    public final static int PARAM_TYPE_JSON = 0;

    public final static int PARAM_TYPE_FORM = 1;
    
    private int mParamType;
    private Map<String, Object> mParams;
    private EncryptDelegate mEncryptDelegate = null;
    
    public YTRequestParams(int paramType) {
    	this(paramType, new HashMap<String, Object>());
    }
    
    public YTRequestParams(int paramType, Map<String, Object> params) {
    	setParamType(paramType);
    	mParams = params;
    }
    
	public int getParamType() {
		return mParamType;
	}

	public void setParamType(int mParamType) {
		this.mParamType = mParamType;
	}
	
    public void put(String key, Object value) {
    	mParams.put(key, value);
    }
    
    public Map<String, Object> getParams() {
    	return mParams;
    }
    
    public String getParamsString() {
    	String paramsString = "";  	
    	if (mParams.size() > 0) {
    		StringBuilder sb = new StringBuilder();
    		if (mParamType == PARAM_TYPE_FORM) {
    	        for (Map.Entry<String, Object> entry : mParams.entrySet()) {
    	            if (sb.length() > 0)
    	            	sb.append("&");

    	            sb.append(entry.getKey());
    	            sb.append("=");
    	            sb.append(entry.getValue());
    	        }
    		} else if (mParamType == PARAM_TYPE_JSON) {
    	    	Gson gson = new Gson();
    	    	sb.append(gson.toJson(mParams));  				
    		}
    		paramsString = sb.toString();
        }

        return paramsString;
    }

	public EncryptDelegate getEncryptDelegate() {
		return mEncryptDelegate;
	}
	
	/**
	 * 设置请求级别加密代理，设置此加密代理后，会忽略全局加密代理设置，使用此加密代理进行加密
	 * @param mEncryptDelegate
	 */
	public void setEncryptDelegate(EncryptDelegate mEncryptDelegate) {
		this.mEncryptDelegate = mEncryptDelegate;
	}
}