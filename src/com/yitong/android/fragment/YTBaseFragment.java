package com.yitong.android.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yitong.android.activity.YTFragmentActivity;
import com.yitong.logs.Logs;

/**
 * 基础Fragment
 * 
 * @author tongxu_li Copyright (c) 2014 Shanghai P&C Information Technology Co.,
 *         Ltd.
 */
public abstract class YTBaseFragment extends Fragment {
	protected String tag = this.getClass().getName();
	protected View contentView = null;
	protected YTFragmentActivity activity = null;
	protected Fragment fragment = null;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (YTFragmentActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentView = inflater.inflate(getContentLayout(), container, false);
		return contentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initAction();
		initData();
	}

	/**
	 * 设置布局文件
	 * 
	 */
	public abstract int getContentLayout();

	/**
	 * 控件初始化
	 * 
	 */
	protected void initView() {
	};

	/**
	 * 事件监听
	 */
	protected void initAction() {
	};

	/**
	 * 数据处理
	 */
	protected void initData() {
	};

	/**
	 * 当点击返回键时触发 返回false，事件继续传递 返回true，事件终止
	 */
	public boolean onBackPressed() {
		return false;
	}

	/**
	 * 后退
	 */
	public void finish() {
		activity.popBack();
	}

	/**
	 * 查找控件
	 */
	public View findViewById(int id) {
		View v = null;
		if (contentView != null) {
			v = contentView.findViewById(id);
		}
		return v;
	}

	/**
	 * 启动Fragment
	 * 
	 * @param cls
	 *            需要启动Fragment的Class
	 * @param params
	 *            需要向启动Fragment传递的参数
	 */
	public boolean startFragment(Class<?> cls, Bundle params) {
		boolean isSuccess = false;
		try {
			Fragment fragment = (Fragment) cls.newInstance();
			if (params != null) {
				fragment.setArguments(params);
			}
			activity.changeFragment(fragment, true);
			isSuccess = true;
		} catch (java.lang.InstantiationException e) {
			e.printStackTrace();
			isSuccess = false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * 启动Activity
	 */
	public void startActivity(Intent intent) {
		activity.startActivity(intent);
	}

	@SuppressLint("ShowToast")
	public void toastShort(String message) {
		if (activity != null) {
			Toast.makeText(activity, message, Toast.LENGTH_SHORT);
		}
	}

	@SuppressLint("ShowToast")
	public void toastShort(int resId) {
		if (activity != null) {
			Toast.makeText(activity, resId, Toast.LENGTH_SHORT);
		}
	}
	@SuppressLint("ShowToast")
	public void toastLong(String message){
		if(activity!=null){
			Toast.makeText(activity, message, Toast.LENGTH_LONG);
		}
	}
	@SuppressLint("ShowToast")
	public void toastLong(int resId){
		if(activity!=null){
			Toast.makeText(activity, resId, Toast.LENGTH_LONG);
		}
	}

	private ProgressDialog progress;
	/**
	 * 显示加载对话框
	 */
	public void showProgressDialog() {
		try {
			if (progress == null) {
				progress = new ProgressDialog(activity);
			}
			progress.setCanceledOnTouchOutside(false);
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setMessage("努力加载中...");
			progress.show();
		} catch (Exception e) {
			e.printStackTrace();
			Logs.d("progress", "等待层启动失败");
		}
	}

	/**
	 * @Description
	 * @param msg
	 * @Author Lewis(lgs@yitong.com.cn) 2014年6月26日 上午12:16:09
	 */
	public void showProgressDialog(String msg) {
		if (progress == null)
			progress = new ProgressDialog(activity);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setMessage(msg);
		progress.show();
	}

	/**
	 * 取消加载对话框
	 */
	public void dismissProgressDialog() {
		if (progress != null && progress.isShowing())
			progress.dismiss();
	}
}
