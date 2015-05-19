package com.yitong.android.view.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.yitong.basic.R;


/**
 * 系统级客户端js接口实现
 * 
 */
public class SysClientJsImpl extends Activity {

	private Activity activity;
	private WebView webView;
	private AllKeyBoard allKeyBoard;
	private boolean isShow = false;

	public SysClientJsImpl(Activity _activity, WebView _webView) {
		this.activity = _activity;
		this.webView = _webView;
	}

	/*****
	 * 上下文键盘
	 * 
	 * @Description
	 * @param cfg
	 */
	private View contextview;
	private static boolean iscontextLoad = false;
	private ContentPad contentPad;

	@JavascriptInterface
	public void showContextPadFun(String cfg) {
		Log.d("TAG", "showContextPadFun().......cfg= " + cfg);
		if (null == contextview) {
			contentPad = new ContentPad(activity, webView);
			contextview = contentPad.setup();
			contentPad.initMenuAction(cfg);
			iscontextLoad = false;
		}
		showContextPad(cfg);
	}

	private void showContextPad(final String cfg) {
		Log.d("TAG", "showContextPad().......cfg= " + cfg);
		if (iscontextLoad) {
			activity.runOnUiThread(new Runnable() {
				public void run() {
					contentPad.initMenuAction(cfg);
					contextview.setVisibility(View.VISIBLE);
				}
			});
		} else {
			activity.runOnUiThread(new Runnable() {
				public void run() {
					contextPadHandler.sendMessage(new Message());
				}
			});
		}
	}

	private Handler contextPadHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			try {
				// 让一个视图浮动在你的应用程序之上
				WindowManager windowmanager = null;
				if (activity.getParent() != null) {
					windowmanager = (WindowManager) activity.getParent()
							.getSystemService(Context.WINDOW_SERVICE);
				} else {
					windowmanager = (WindowManager) activity
							.getSystemService(Context.WINDOW_SERVICE);
				}

				WindowManager.LayoutParams layoutparams = new WindowManager.LayoutParams(
						-1, -1, WindowManager.LayoutParams.FIRST_SUB_WINDOW,
						WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
						PixelFormat.RGBA_8888);
				layoutparams.gravity = Gravity.BOTTOM;
				contextview.findViewById(R.id.contentpdpanel).getBackground()
						.setAlpha(140);
				windowmanager.addView(contextview, layoutparams);
				iscontextLoad = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * 隐藏上下午键盘
	 * 
	 * @author 尹银川
	 * @date 2014-2-22
	 * @version V1.0
	 */
	public void hideContentPad() {
		if (null != activity && null != contextview) {
			activity.runOnUiThread(new Runnable() {
				public void run() {
					contextview.setVisibility(View.GONE);
				}
			});
		}
	}

//	/*************** 金额键盘 ***************************************************/
//	/**
//	 * 金额键盘
//	 * 
//	 * @author 尹银川
//	 * @date 2014-2-25
//	 * @param str
//	 *            后台提供的参数格式{id:
//	 *            '',text:'',left:'10',right:'2',min:'0',max:'',callback:'saveMoneyData',update:'saveMoneyDa
//	 *            t a ' ;
//	 * @version V1.0
//	 */
//	private View view;
//	private boolean isLoad = false;
//	private WebDigitKeyPad dk;
//	private String dk_str;
//	private String jecallback;
//	private String updateBack;
//
//	@JavascriptInterface
//	public void showdigitpad(String str) {
//		Log.d("TAG", "金额键盘被调用...." + str);
//		try {
//			JSONObject json = new JSONObject(str);
//			Log.d("TAG","json.getString(left) = "+json.getString("left"));
//			dk_str = json.getString("text").replace(".00", "").trim();
//			if (json.has("callback")) {
//				jecallback = json.getString("callback");
//			}
//			if (json.has("update")) {
//				updateBack = json.getString("update");
//			}
//			if (true) { 
//				if (null != allKeyBoard) {
//					allKeyBoard = null;
//				}
//				allKeyBoard = new AllKeyBoard(activity, false, 2, true,
//						"11");
//				allKeyBoard
//						.showKeyboard(Integer.valueOf(json.getString("left")));
//				return;
//			} else {
//				if (null == view) {
//					dk = new WebDigitKeyPad(activity,
//							Integer.valueOf(json.getString("left")));
//					view = dk.setup();
//				}
//				dk.initInputLable(dk_str);
//				showFakeTitleBar();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/****
//	 * 显示金额键盘
//	 */
//	private void showFakeTitleBar() {
//		isShow = true;
//		if (isLoad) {
//			activity.runOnUiThread(new Runnable() {
//				public void run() {
//					view.setVisibility(View.VISIBLE);
//				}
//			});
//		} else {
//			activity.runOnUiThread(new Runnable() {
//				public void run() {
//					handlerUpdateWebDigitKeyPad.sendMessage(new Message());
//				}
//			});
//		}
//	}
//
//	private Handler handlerUpdateWebDigitKeyPad = new Handler() {
//
//		public void handleMessage(android.os.Message msg) {
//			// 让一个视图浮动在你的应用程序之上
//			WindowManager windowmanager = (WindowManager) activity
//					.getSystemService(Context.WINDOW_SERVICE);
//			LayoutParams layoutparams = new LayoutParams(-1, -1,
//					WindowManager.LayoutParams.FIRST_SUB_WINDOW,
//					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//					PixelFormat.RGBA_8888);
//			layoutparams.gravity = Gravity.BOTTOM;
//			view.findViewById(R.id.digitkeypdpanel).getBackground()
//					.setAlpha(140);
//			try {
//				windowmanager.addView(view, layoutparams);
//				isLoad = true;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		};
//	};

	/****************** 测试部分 ********************************************/

	/**
	 * 提供给后台调用
	 * 
	 * @author 尹银川
	 * @date 2014-2-21
	 * @version V1.0
	 */
	@JavascriptInterface
	public void testMainJS() {
		webView.loadUrl("javascript:testjs()");
		Log.d("TAG", "testMainJS()被后台调用...");
	}

	/**
	 * 提供给后台调用
	 * 
	 * @author 尹银川
	 * @date 2014-2-21
	 * @version V1.0
	 */
	@JavascriptInterface
	public void testMainJS(String str) {
		webView.loadUrl("javascript:testjs()");
		Log.d("TAG", "testMainJS(str)被后台调用...str = " + str);
	}

	/**
	 * 显示提示内容
	 * 
	 * @author 尹银川
	 * @date 2014-2-25
	 * @param countent
	 * @version V1.0
	 */
	@JavascriptInterface
	public void showAlert(String countent) {
		Toast.makeText(activity, countent, Toast.LENGTH_LONG).show();
	}

}
