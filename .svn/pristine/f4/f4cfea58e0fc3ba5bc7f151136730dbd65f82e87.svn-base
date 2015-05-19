package com.yitong.logs;

/**
 * 日志管理工具类
 * 
 * @Description
 * @author 刘国山 lgs@yitong.com.cn
 * @version 1.0 2012-8-15
 * @class com.yitong.zjrc.mbank.android.logs.Logs
 */
public class Logs {

	private static final boolean isDebug = true;
	
	private static final boolean VERBOSE = true;
	private static final boolean DEBUG = true;
	private static final boolean INFO = true;
	private static final boolean WARN = true;
	private static final boolean ERROR = true;

	// public static final String TAG_PREFIX = "elife";

	public static void v(String tag, String msg) {
		if (VERBOSE&&isDebug) {
			android.util.Log.v(tag, msg);
		}
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (VERBOSE&&isDebug) {
			android.util.Log.v(tag, msg, tr);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG&&isDebug) {
			android.util.Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (DEBUG&&isDebug) {
			android.util.Log.d(tag, msg, tr);
		}
	}

	public static void i(String tag, String msg) {
		if (INFO&&isDebug) {
			android.util.Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (INFO&&isDebug) {
			android.util.Log.i(tag, msg, tr);
		}
	}

	public static void w(String tag, String msg) {
		if (WARN&&isDebug) {
			android.util.Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (WARN&&isDebug) {
			android.util.Log.w(tag, msg, tr);
		}
	}

	public static void w(String tag, Throwable tr) {
		if (WARN&&isDebug) {
			android.util.Log.w(tag, tr);
		}
	}

	public static void e(String tag, String msg) {
		if (ERROR&&isDebug) {
			android.util.Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (ERROR&&isDebug) {
			android.util.Log.e(tag, msg, tr);
		}
	}
}
