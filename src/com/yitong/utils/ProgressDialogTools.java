package com.yitong.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 进度条生成帮助类
 * @Description 
 * @Author Lewis(lgs@yitong.com.cn) 2014年8月13日 上午9:57:12
 * @Class ProgressDialogTools
 * Copyright (c) 2014 Shanghai P&C Information Technology Co.,Ltd. All rights reserved.
 */
public abstract class ProgressDialogTools {

	private ProgressDialogTools() throws IllegalAccessException {
		throw new IllegalAccessException("工具类无法实例化!");
	}

	public static ProgressDialog createProgress(Context context, String msg) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setMessage(msg);
		dialog.setIndeterminate(false);
		dialog.setCancelable(true);
		return dialog;
	}

}
