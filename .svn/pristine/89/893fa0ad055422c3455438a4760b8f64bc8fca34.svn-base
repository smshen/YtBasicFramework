package com.yitong.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.yitong.logs.Logs;

/**
 * 基础activity
 * 
 * @Description
 * @Author lewis(lgs@yitong.com.cn) 2014-4-29 上午9:07:51
 * @Class BaseActivity Copyright (c) 2014 Shanghai P&C Information Technology
 *        Co.,Ltd. All rights reserved.
 */
public abstract class YTBaseActivity extends Activity {

	protected Activity activity;

	@Override
	protected void onCreate(Bundle savedactivityState) {
		super.onCreate(savedactivityState);
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (getContentLayout() != 0) {
			setContentView(getContentLayout());
		}

		activity = this;

		initGui();
		initAction();
		initData();
	}
	
	/**
	 * 设置布局文件
	 */
	protected abstract int getContentLayout();

	/**
	 * 初始化UI
	 * 
	 * @Description
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2013-6-18
	 */
	protected abstract void initGui();

	/**
	 * 初始化事件
	 * 
	 * @Description
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2013-6-18
	 */
	protected abstract void initAction();

	/**
	 * 初始化数据
	 * 
	 * @Description
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2013-6-18
	 */
	protected abstract void initData();

	/**
	 * 根据资源id获取值
	 * 
	 * @Description
	 * @param activity
	 * @param resId
	 * @return
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-7-18
	 */
	protected String getResString(int resId) {
		return activity.getResources().getString(resId);
	}

	/**
	 * 长提示
	 * 
	 * @Description
	 * @param context
	 * @param msg
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-7-18
	 */
	protected void toastLong(String msg) {
		Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * 长提示
	 * 
	 * @Description
	 * @param activity
	 *            activity实例
	 * @param resId
	 *            文本资源ID
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-7-19
	 */
	protected void toastLong(int resId) {
		Toast.makeText(activity, getResString(resId), Toast.LENGTH_LONG).show();
	}

	/**
	 * 短提示
	 * 
	 * @Description
	 * @param context
	 * @param msg
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-7-18
	 */
	protected void toastShort(String msg) {
		Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 短提示
	 * 
	 * @Description
	 * @param activity
	 *            activity实例
	 * @param resId
	 *            文本资源ID
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-7-19
	 */
	protected void toastShort(int resId) {
		Toast.makeText(activity, getResString(resId), Toast.LENGTH_SHORT)
				.show();
	}

	private long lastClickTime;

	/**
	 * 判断事件出发时间间隔是否超过预定值
	 * 
	 * @Description
	 * @return
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2013年7月22日
	 */
	public boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 1000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	@Override
	public void startActivity(Intent intent) {
		// 防止连续点击
		if (isFastDoubleClick()) {
			Logs.d("BaseActivity", "startActivity() 重复调用");
			return;
		}
		super.startActivity(intent);
	}

}
