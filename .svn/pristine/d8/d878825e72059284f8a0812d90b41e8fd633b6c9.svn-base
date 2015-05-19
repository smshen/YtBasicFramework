package com.yitong.service;

import org.apache.http.Header;

import com.yitong.http.TextHttpResponseHandler;

/**
 * 直接获取返回报文，不解析
 * 
 * @author flueky
 * 
 */
public abstract class APPResponseContent extends TextHttpResponseHandler {

	// 网络连接错误
	private static final String ERROR_CODE_NET = "-999";
	// Json字符串转对象错误
	private static final String ERROR_CODE_FROM_JSON_TO_OBJECT = "-888";
	// Json字符串解析错误
	private static final String ERROR_CODE_JSON_PARSE = "-777";
	// 结果字段key不匹配
	private static final String ERROR_CODE_RESULT_KEY_NOT_CORRECT = "-666";

	public abstract void getResponseContent(String response);

	public abstract void onFailure(String errorCode, String errorMsg);

	@Override
	public void onFailure(int statusCode, Header[] headers,
			String responseString, Throwable throwable) {
		throwable.printStackTrace();
		onFailure(ERROR_CODE_NET, getErrorMsg(ERROR_CODE_NET));
	}

	@Override
	public void onSuccess(int arg0, Header[] arg1, String arg2) {
		// TODO Auto-generated method stub
		getResponseContent(arg2);
	}

	private String getErrorMsg(String errorCode) {
		String errorMsg = "";

		if (errorCode.equals(ERROR_CODE_NET)) {
			errorMsg = "网络连接失败，请稍后重试";
		} else if (errorCode.equals(ERROR_CODE_FROM_JSON_TO_OBJECT)) {
			errorMsg = "对象转换失败";
		} else if (errorCode.equals(ERROR_CODE_JSON_PARSE)) {
			errorMsg = "对象解析失败";
		} else if (errorCode.equals(ERROR_CODE_RESULT_KEY_NOT_CORRECT)) {
			errorMsg = "结果字段key不匹配";
		} else {
			errorMsg = "未知错误";
		}
		return errorMsg;
	}
}
