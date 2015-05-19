/*
 * 作者		刘国山 lgs@yitong.com.cn
 * 开发环境	Win7 Eclipse3.5 JDK1.6
 * 开发日期	2012-7-18
 */
package com.yitong.exception;

import static com.yitong.logs.Logs.e;
/**
 * 异常
 * @Description 
 * @author 刘国山 lgs@yitong.com.cn
 * @version 1.0 2012-7-18
 * @class com.yitong.zjrc.mbank.android.exception.TakeOutException
 */
public class MBankException extends Exception {
	/**
	 * @Description 
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-7-18
	 */
	private static final long serialVersionUID = 7633894647653066893L;
	
	private int statusCode = -1;
	
	private String tag = this.getClass().getName();

    public MBankException(String msg) {
        super(msg);
        e(tag, msg);
    }

    public MBankException(Exception cause) {
        super(cause);
        e(tag, cause.getMessage());
    }

    public MBankException(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;
        e(tag, msg);
    }

    public MBankException(String msg, Exception cause) {
        super(msg, cause);
        e(tag, msg);
    }

    public MBankException(String msg, Exception cause, int statusCode) {
        super(msg, cause);
        this.statusCode = statusCode;
        e(tag, msg);
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
