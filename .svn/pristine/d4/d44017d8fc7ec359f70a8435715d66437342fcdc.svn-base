package com.yitong.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;

/**
 * 各种参数封装成HttpEntity，支持加密和编码设置
 * @author tongxu_li
 * Copyright (c) 2014 Shanghai P&C Information Technology Co., Ltd.
 */
public class APPRestParamsToEntity {
	private static String encoding = "UTF-8";
	
	/**
	 * 将map转成json字符串，然后生成HttpEntity
	 * @param params
	 */
    public static HttpEntity paramsToJsonEntity(Map<String, Object> params) {
    	HttpEntity httpEntity = null;

    	Gson gson = new Gson();
    	String paramsString = gson.toJson(params);
    	
        try {       	
        	httpEntity = new StringEntity(paramsString, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    	return httpEntity;
    }
    
	/**
	 * string 生成 HttpEntity 
	 * @param paramsString
	 */
	public static HttpEntity paramsToEntity(String paramsString) {
		HttpEntity httpEntity = null;
		if (paramsString == null || paramsString.equals("")) {
			return httpEntity; 
		}
		
		try {
			httpEntity = new StringEntity(paramsString, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpEntity;
	}
		
	/**
	 * 设置发送请求数据编码格式
	 * @param encoding
	 */
	public static void setEncoding(String encoding) {
		APPRestParamsToEntity.encoding = encoding;
	}
}