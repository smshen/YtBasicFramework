package com.yitong.service;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.yitong.http.TextHttpResponseHandler;
import com.yitong.logs.Logs;
import com.yitong.utils.StringTools;

/**
 * 处理网络请求回调，可根据指定的对象泛型，进行反射填充对象。
 * @Description 
 * @Author lewis(lgs@yitong.com.cn) 2014-1-17 下午1:44:43
 * @Class APPResponseHandler
 * Copyright (c) 2014 Shanghai P&C Information Technology Co.,Ltd. All rights reserved.
 */
public abstract class APPResponseHandler<T> extends TextHttpResponseHandler {	
	private static final String LOG_TAG = "APPResponseHandler";
	
	// 网络连接错误
	private static final String ERROR_CODE_NET = "-999";
	// Json字符串转对象错误
	private static final String ERROR_CODE_FROM_JSON_TO_OBJECT = "-888";
	// Json字符串解析错误
	private static final String ERROR_CODE_JSON_PARSE = "-777";
	// 结果字段key不匹配
	private static final String ERROR_CODE_RESULT_KEY_NOT_CORRECT = "-666";

	
	// 指定结果集泛型
	private Class<T> classOfT;			
	// 设置结果集是否为空
	private boolean isResultNull = true;		
	// 返回结果管理器
	private ServiceResultManager mServiceResultManager;
	// 如果返回接口是密文，设置解密代理
	private DecryptDelegate mDecryptDelegate = null;

	/**
     * 创建一个默认编码为utf-8对象,该对象的回调结果集为空
     */
    public APPResponseHandler() {
    	super();
    	isResultNull = true;
    	mServiceResultManager = new DefaultServiceResultManager();
    }

    /**
     * 创建一个默认编码为utf-8对象,该对象的回调结果集为指定泛型
     */
    public APPResponseHandler(Class<T> classOfT) {
        this(classOfT, DEFAULT_CHARSET);
    }

    /**
     * 创建一个默认编码为指定编码的对象,该对象的回调结果集为指定泛型
     */
    public APPResponseHandler(Class<T> classOfT, String encoding) {
    	super(encoding);
    	this.classOfT = classOfT;
    	isResultNull = false;
    	mServiceResultManager = new DefaultServiceResultManager();
    }
    
    /**
     * Called when request succeeds
     *
     * @param result
     */
    public abstract void onSuccess(T result);

    /**
     * Called when request fails
     *
     * @param errorCode
     * @param errorMsg
     */
    public abstract void onFailure(String errorCode, String errorMsg);

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
    	Logs.d("TAG", "接口返回"+responseString);    	

    	// 返回结果解密
    	if (mDecryptDelegate != null) {
    		responseString = mDecryptDelegate.getDecryptString(responseString);
    		Logs.d(LOG_TAG, "接口解密返回"+responseString);
    	}

		String status = "";
		String message = "";
		
		try {
			Gson gson = new Gson();
			
			JsonObject jsonObject = new JsonParser().parse(responseString).getAsJsonObject();
			
			String keyStatus = mServiceResultManager.getStatusKey();
			if (jsonObject.has(keyStatus)) {
				JsonElement  errorCodeJsonElement = jsonObject.get(keyStatus);
				status = errorCodeJsonElement.getAsString();			
			}
			
			String keyMessage = mServiceResultManager.getMessgaeKey();
			if (jsonObject.has(keyMessage)) {
				JsonElement errorMsgJsonElement = jsonObject.get(keyMessage);
				if (errorMsgJsonElement.isJsonNull()) {
					message = "";
				} else {
					message = errorMsgJsonElement.getAsString();	
				}				
			}
			
			if (isResultNull) {
				if (status.equals(mServiceResultManager.getSuccessStatus())) {
					onSuccess((T)null);
				} else {
					onFailure(status, message);
				}			
			} else {
				String keyResult = mServiceResultManager.getResultKey();
				if (status.equals(mServiceResultManager.getSuccessStatus())) {
					//key常量不为空
					if (!StringTools.isEmpty(keyResult)) {						
						if(jsonObject.has(keyResult)) {	//json结果中包含结果key
							JsonElement resultJsonElement = jsonObject.get(keyResult);
							try {
								T result = gson.fromJson(resultJsonElement, classOfT);
								onSuccess(result);
							} catch (JsonSyntaxException e) {
								onFailure(ERROR_CODE_FROM_JSON_TO_OBJECT, 
										getErrorMsg(ERROR_CODE_FROM_JSON_TO_OBJECT));
								return;
							}	
						} else {	//json结果中包含结果不匹配
							onFailure(ERROR_CODE_RESULT_KEY_NOT_CORRECT,
									getErrorMsg(ERROR_CODE_RESULT_KEY_NOT_CORRECT));
						}
					}else{
						try {
							T result = gson.fromJson(jsonObject, classOfT);
							onSuccess(result);
						} catch (JsonSyntaxException e) {
							onFailure(ERROR_CODE_FROM_JSON_TO_OBJECT, 
									getErrorMsg(ERROR_CODE_FROM_JSON_TO_OBJECT));
							return;
						}
					}	
				}else {
					onFailure(status, message);
				}
			}			
		} catch (JsonSyntaxException e) {
			onFailure(ERROR_CODE_JSON_PARSE, 
					getErrorMsg(ERROR_CODE_JSON_PARSE));			
		} catch (IllegalStateException e) {
			onFailure(ERROR_CODE_JSON_PARSE, 
					getErrorMsg(ERROR_CODE_JSON_PARSE));
		} catch (Exception e) {
			onFailure(ERROR_CODE_JSON_PARSE, 
					getErrorMsg(ERROR_CODE_JSON_PARSE));
		}
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
    	throwable.printStackTrace();
    	onFailure(ERROR_CODE_NET, getErrorMsg(ERROR_CODE_NET));
    }
    
    private String getErrorMsg(String errorCode) {
    	String errorMsg = "";
    	
    	if (errorCode.equals(ERROR_CODE_NET)) {
    		errorMsg = "网络连接失败，请稍后重试";
		} else if (errorCode.equals(ERROR_CODE_FROM_JSON_TO_OBJECT)){
			errorMsg = "对象转换失败";
		} else if (errorCode.equals(ERROR_CODE_JSON_PARSE)){
			errorMsg = "对象解析失败";
		} else if (errorCode.equals(ERROR_CODE_RESULT_KEY_NOT_CORRECT)) {
			errorMsg = "结果字段key不匹配";
		} else {
			errorMsg = "未知错误";
		}
    	return errorMsg;
    }
    
    public void setServiceResultManager (ServiceResultManager manager) {
    	this.mServiceResultManager = manager;
    }
    
    public void setDecryptDelegate (DecryptDelegate delegate) {
    	this.mDecryptDelegate = delegate;
    }
    
    /**
     * 服务器结果设置
     * @author tongxu_li
     * Copyright (c) 2014 Shanghai P&C Information Technology Co., Ltd.
     */
    public interface ServiceResultManager {
    	/**
    	 * 设置状态码key
    	 */
    	public String getStatusKey();
    	/**
    	 * 设置状态信息key
    	 */
    	public String getMessgaeKey();
    	/**
    	 * 设置结果集key
    	 */
    	public String getResultKey();
    	/**
    	 * 设置成功状态码
    	 */
    	public String getSuccessStatus();
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
    	 */
    	public String getDecryptString(String encryptString); 
    }
}
