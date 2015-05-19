package com.yitong.android.view.keyboard;
//package com.yitong.android.view;
//import java.util.Random;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Typeface;
//import android.os.Handler;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnLongClickListener;
//import android.view.View.OnTouchListener;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.yitong.basic.R;
//
//
//@SuppressWarnings("deprecation")
//public class NumericKeyPad {
//	private Activity activity;
//
//	private String digitnum = "";// 输入值值
//
//	private LinearLayout keypad_num;// 数字键盘
//	private LinearLayout keypad_abc;// 字母键盘
//	// 数字按钮
//	private int[] num_int = { R.id.num_0, R.id.num_1, R.id.num_2, R.id.num_3,
//			R.id.num_4, R.id.num_5, R.id.num_6, R.id.num_7, R.id.num_8,
//			R.id.num_9 };
//
//	private Button[] num_btn = new Button[10];
//
//	// 小写字母按钮
//	private int[] abc_int = { R.id.abc_a, R.id.abc_b, R.id.abc_c, R.id.abc_d,
//			R.id.abc_e, R.id.abc_f, R.id.abc_g, R.id.abc_h, R.id.abc_i,
//			R.id.abc_j, R.id.abc_k, R.id.abc_l, R.id.abc_m, R.id.abc_n,
//			R.id.abc_o, R.id.abc_p, R.id.abc_q, R.id.abc_r, R.id.abc_s,
//			R.id.abc_t, R.id.abc_u, R.id.abc_v, R.id.abc_w, R.id.abc_x,
//			R.id.abc_y, R.id.abc_z };
//
//	private String[] abc_str = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
//			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
//			"w", "x", "y", "z" };
//
//	private Button[] abc_btn = new Button[26];
//
//	private String[] ABC_str = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
//			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
//			"W", "X", "Y", "Z" };
//	// 键盘标识 0：数字键盘 1:小写字母 2：大写字母
//	public int key_tag = 0;
//	private int length = 30;
//	// 是否密文显示 true:明文 false:密文
//	private boolean isPwd;
//	private boolean changed = true;
//	// private EditText keypad_word; // 输入值框
//	private Button digitkeypad_cancel; // 取消按钮
//	private Button digitkeypad_ok; // 确定按钮
//
//	private Button digitkeypad_cancel_abc; // 取消按钮
//	private Button digitkeypad_ok_abc; // 确定按钮
//	private Button numToAbc;// 数字转换字母键盘
//	private ImageView numDel;// 数字删除
//	private Button abcUP;// 字母变大小写
//	private ImageView abcDel;// 字母删除
//	private Button abcToNum;// 字母转换数字键盘
//	private View menuView;
//	private boolean isUP = false;
//
////	private SysClientJsImpl jsimpl;
//
//	private String KEY_PAD_TYPE = "";
//
//	private boolean isRandom = false;
//
//	private Button xiahuaxianButton;
//
//	private boolean isShowXHX;
//
//	private Typeface typebold;
//
//	/**
//	 * @param ctx
//	 *            内容
//	 * @param e
//	 *            EiditText控件
//	 * @param siPwd
//	 *            是否是单一的密码框
//	 * @param length
//	 *            编辑框的长度
//	 * @param changed
//	 *            是否需要切换大小写以及数字
//	 * @param max
//	 *            长度限制
//	 */
//	public NumericKeyPad(Activity activity, int length, Boolean isPwd,
//			boolean changed, String KEY_PAD_TYPE,//SysClientJsImpl jsimpl
//			boolean isRandom, boolean isShowXHX) {
//		super();
//		this.activity = activity;
//		this.isPwd = isPwd;
//		this.length = length;
//		this.changed = changed;
////		this.jsimpl = jsimpl;
//		this.isShowXHX = isShowXHX;
//		this.KEY_PAD_TYPE = KEY_PAD_TYPE;
//		this.isRandom = isRandom;
//		InputMethodManager im = (InputMethodManager) activity
//				.getSystemService(Context.INPUT_METHOD_SERVICE);
//		im.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
//				InputMethodManager.HIDE_NOT_ALWAYS);
//		typebold = Typeface.createFromAsset(activity.getAssets(),
//				"fonts/newfront.ttf");
//	}
//
//	public NumericKeyPad(Activity activity, int length, Boolean isPwd,
//			boolean changed, String KEY_PAD_TYPE,//, SysClientJsImpl jsimpl
//			boolean isRandom, String keyPadID, boolean isShowXHX) {
//		super();
//		this.activity = activity;
//		this.isPwd = isPwd;
//		this.length = length;
//		this.changed = changed;
//		this.isShowXHX = isShowXHX;
////		this.jsimpl = jsimpl;
//		this.KEY_PAD_TYPE = KEY_PAD_TYPE;
//		this.isRandom = isRandom;
//		this.keyPadID = keyPadID;
//		InputMethodManager im = (InputMethodManager) activity
//				.getSystemService(Context.INPUT_METHOD_SERVICE);
//		im.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
//				InputMethodManager.HIDE_NOT_ALWAYS);
//		typebold = Typeface.createFromAsset(activity.getAssets(),
//				"fonts/newfront.ttf");
//	}
//
//	private String keyPadUserName = "";
//
//	public NumericKeyPad(Activity activity, int length, Boolean isPwd,
//			boolean changed,  String KEY_PAD_TYPE,//SysClientJsImpl jsimpl,
//			String keyPadUserName, boolean isRandom, boolean isShowXHX) {
//		super();
//		this.activity = activity;
//		this.isPwd = isPwd;
//		this.length = length;
//		this.changed = changed;
//		this.isShowXHX = isShowXHX;
////		this.jsimpl = jsimpl;
//		this.KEY_PAD_TYPE = KEY_PAD_TYPE;
//		this.keyPadUserName = keyPadUserName;
//		this.isRandom = isRandom;
//		InputMethodManager im = (InputMethodManager) activity
//				.getSystemService(Context.INPUT_METHOD_SERVICE);
//		im.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
//				InputMethodManager.HIDE_NOT_ALWAYS);
//		typebold = Typeface.createFromAsset(activity.getAssets(),
//				"fonts/newfront.ttf");
//	}
//
//	private String keyPadID;
//
//	public NumericKeyPad(Activity activity, int length, Boolean isPwd,
//			boolean changed,  String KEY_PAD_TYPE,//SysClientJsImpl jsimpl,
//			String keyPadUserName, boolean isRandom, String keyPadID,
//			boolean isShowXHX) {
//		this.activity = activity;
//		this.isPwd = isPwd;
//		this.length = length;
//		this.changed = changed;
//		this.isShowXHX = isShowXHX;
////		this.jsimpl = jsimpl;
//		this.KEY_PAD_TYPE = KEY_PAD_TYPE;
//		this.keyPadUserName = keyPadUserName;
//		this.isRandom = isRandom;
//		this.keyPadID = keyPadID;
//		InputMethodManager im = (InputMethodManager) activity
//				.getSystemService(Context.INPUT_METHOD_SERVICE);
//		im.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
//				InputMethodManager.HIDE_NOT_ALWAYS);
//		typebold = Typeface.createFromAsset(activity.getAssets(),
//				"fonts/newfront.ttf");
//
//	}
//
//	Handler handler = new Handler() {
//		public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case 0:
//				initUper();
//				abcUP.setBackgroundDrawable(activity.getResources()
//						.getDrawable(R.drawable.up_s));
//				break;
//			case 1:
//				initLower();
//				abcUP.setBackgroundDrawable(activity.getResources()
//						.getDrawable(R.drawable.up_n));
//				break;
//			default:
//				break;
//			}
//		};
//	};
//
//	private Button dismissKeypad;
//	private TextView keyTitle;
//
//	public View initKeyPopupWindow() {
//		menuView = LayoutInflater.from(activity).inflate(
//				R.layout.cupd_keypadview, null);
//		digitkeypad_ok = (Button) menuView.findViewById(R.id.ritht_ok_btn);
//		// 确定按钮监听
//		digitkeypad_ok.setTypeface(typebold);
//		digitkeypad_ok.setOnClickListener(l);
//		keyTitle = (TextView) menuView.findViewById(R.id.textTitle);
//		keyTitle.setTypeface(typebold);
//		// 清空按钮监听
//		digitkeypad_cancel = (Button) menuView
//				.findViewById(R.id.left_clear_btn);
//		digitkeypad_cancel.setOnClickListener(l);
//		digitkeypad_cancel.setTypeface(typebold);
//		digitkeypad_ok_abc = (Button) menuView
//				.findViewById(R.id.ritht_ok_btn_abc);
//		// 确定按钮监听
//		digitkeypad_ok_abc.setOnClickListener(l);
//		// 清空按钮监听
//		digitkeypad_cancel_abc = (Button) menuView
//				.findViewById(R.id.left_clear_btn_abc);
//		digitkeypad_cancel_abc.setOnClickListener(l);
//		dismissKeypad = (Button) menuView.findViewById(R.id.dismissKeypad);
//		dismissKeypad.setOnClickListener(l);
//		digitkeypad_ok_abc.setTypeface(typebold);
//		digitkeypad_cancel_abc.setTypeface(typebold);
//		xiahuaxianButton = (Button) menuView.findViewById(R.id.xiahuaxian);
//		// 键盘视图
//		keypad_num = (LinearLayout) menuView.findViewById(R.id.keypad_num);
//		keypad_abc = (LinearLayout) menuView.findViewById(R.id.keypad_abc);
//		numDel = (ImageView) menuView.findViewById(R.id.num_del);
//		numDel.setOnClickListener(new KeypadBackOnClickListener());
//
//		abcDel = (ImageView) menuView.findViewById(R.id.abc_del);
//		abcDel.setOnClickListener(new KeypadBackOnClickListener());
//
//		numToAbc = (Button) menuView.findViewById(R.id.num_change);
//		if (!changed) {
//			if (KEY_PAD_TYPE.equals("9")) {
//				numToAbc.setText("X");
//			} else {
//				numToAbc.setVisibility(View.GONE);
//			}
//		}
//		numToAbc.setOnClickListener(l);
//		abcToNum = (Button) menuView.findViewById(R.id.abc_123);
//		abcToNum.setOnClickListener(l);
//		abcUP = (Button) menuView.findViewById(R.id.abc_up);
//		abcUP.setOnClickListener(l);
//		initNum();
//		initLower();
//		return menuView;
//	}
//
//	// 初始化随机密码键盘
//	private void initNum() {
//		KeypadOnClickListener keypad1listener = new KeypadOnClickListener();
//		if (isRandom) {
//			Random ran = new Random();
//			java.util.List<Integer> list = new java.util.ArrayList<Integer>();
//			while (list.size() < 10) {
//				int n = ran.nextInt(10);
//				if (!list.contains(n))
//					list.add(n);// 如果n不包涵在list中，才添加
//			}
//			for (int i = 0; i < num_btn.length; i++) {
//				num_btn[i] = (Button) menuView.findViewById(num_int[i]);
//				num_btn[i].setText(list.get(i) + "");
//				num_btn[i].setTypeface(typebold);
//				num_btn[i].setOnClickListener(keypad1listener);
//			}
//		} else {
//			for (int i = 0; i < num_btn.length; i++) {
//				num_btn[i] = (Button) menuView.findViewById(num_int[i]);
//				num_btn[i].setTypeface(typebold);
//				num_btn[i].setOnClickListener(keypad1listener);
//			}
//		}
//	}
//
//	// 初始化随机小写字母键盘
//	private void initLower() {
//		Keypad_UOnClickListener keypad1listener = new Keypad_UOnClickListener();
//		for (int i = 0; i < abc_btn.length; i++) {
//			abc_btn[i] = (Button) menuView.findViewById(abc_int[i]);
//			abc_btn[i].setText(abc_str[i]);
//			abc_btn[i].setTypeface(typebold);
//			abc_btn[i].setOnClickListener(keypad1listener);
//		}
//		if (isShowXHX) {
//			xiahuaxianButton.setVisibility(View.VISIBLE);
//			xiahuaxianButton.setOnClickListener(keypad1listener);
//		}
//	}
//
//	// 初始化随机大写字母键盘
//	private void initUper() {
//		Keypad_UOnClickListener keypad1listener = new Keypad_UOnClickListener();
//		for (int i = 0; i < abc_btn.length; i++) {
//			abc_btn[i] = (Button) menuView.findViewById(abc_int[i]);
//			abc_btn[i].setText(ABC_str[i]);
//			abc_btn[i].setTypeface(typebold);
//			abc_btn[i].setOnClickListener(keypad1listener);
//		}
//		xiahuaxianButton.setOnClickListener(keypad1listener);
//	}
//
//	OnTouchListener onTouchListener = new OnTouchListener() {
//
//		@Override
//		public boolean onTouch(View v, MotionEvent event) {
//			InputMethodManager imm = (InputMethodManager) activity
//					.getSystemService(Context.INPUT_METHOD_SERVICE);
//			imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//			return true;
//		}
//	};
//	/*
//	 * 事件监听器 *
//	 */
//	private OnClickListener l = new OnClickListener() {
//		public void onClick(View v) {
//			switch (v.getId()) {
//			case R.id.left_clear_btn_abc: // 清空
//				// jsimpl.hidePwdBoard();
//				digitnum = "";
////				jsimpl.updatePwdValue("", KEY_PAD_TYPE);
////				jsimpl.setPwdResultClear("", KEY_PAD_TYPE, keyPadUserName);
//				break;
//			case R.id.ritht_ok_btn_abc:// 确定;
//				if (isPwd) {
////					jsimpl.updatePwdValue(NoToX(digitnum), KEY_PAD_TYPE);
//				} else {
////					jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//				}
////				jsimpl.setPwdResultOk(digitnum, KEY_PAD_TYPE, keyPadUserName);
//				break;
//			case R.id.left_clear_btn: // 清空
//				// jsimpl.hidePwdBoard();
//				digitnum = "";
////				jsimpl.updatePwdValue("", KEY_PAD_TYPE);
////				jsimpl.setPwdResultClear("", KEY_PAD_TYPE, keyPadUserName);
//				break;
//			case R.id.ritht_ok_btn:// 确定;
//				if (isPwd) {
//					//jsimpl.updatePwdValue(NoToX(digitnum), KEY_PAD_TYPE);
//				} else {
//					//jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//				}
//				//jsimpl.setPwdResultOk(digitnum, KEY_PAD_TYPE, keyPadUserName);
//				break;
//			case R.id.dismissKeypad:// dismiss;
//				if (isPwd) {
////					jsimpl.updatePwdValue(NoToX(digitnum), KEY_PAD_TYPE);
//				} else {
////					jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//				}
////				jsimpl.setPwdResultOk(digitnum, KEY_PAD_TYPE, keyPadUserName);
//				break;
//			case R.id.num_change:// 切换按钮
//				if (!changed && KEY_PAD_TYPE.equals("9")) {
//					Button button = (Button) v.findViewById(R.id.num_change);
//					String number = (String) button.getText();
//					digitnum = digitnum + number;
//					if (isPwd) {
////						AppURL.setSaveNumber(keyPadID, digitnum);
////						jsimpl.updatePwdValue(NoToX(digitnum), KEY_PAD_TYPE);
//					} else {
////						jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//					}
//				} else {
//					changeview();
//				}
//				break;
//			case R.id.abc_123:// 切换按钮
//				changeview();
//				break;
//			case R.id.abc_up:// 切换按钮
//				changeABC();
//				break;
//			}
//		}
//	};
//
//	/**
//	 * 切换键盘视图
//	 */
//	private void changeview() {
//		switch (key_tag) {
//		case 0: // 数字->字母
//			keypad_num.setVisibility(View.GONE);
//			keypad_abc.setVisibility(View.VISIBLE);
//			key_tag = 1;
//			break;
//		case 1: // 字母->数字
//			keypad_num.setVisibility(View.VISIBLE);
//			keypad_abc.setVisibility(View.GONE);
//			key_tag = 0;
//			break;
//		}
//	}
//
//	/**
//	 * 切换大小写
//	 */
//	private void changeABC() {
//		if (isUP) {
//			isUP = false;
//		} else {
//			isUP = true;
//		}
//		if (isUP) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					menuView.post(new Runnable() {
//						@Override
//						public void run() {
//							initUper();
//							abcUP.setBackgroundDrawable(activity.getResources()
//									.getDrawable(R.drawable.up_s));
//						}
//					});
//				}
//			}).start();
//		} else {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					menuView.post(new Runnable() {
//						@Override
//						public void run() {
//							initLower();
//							abcUP.setBackgroundDrawable(activity.getResources()
//									.getDrawable(R.drawable.up_n));
//						}
//					});
//				}
//			}).start();
//		}
//	}
//
//	// 数字键盘按钮监听
//	private class KeypadOnClickListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//			if (digitnum.length() >= length) {
//				return;
//			}
//			int viewId = v.getId();
//			if (viewId == R.id.num_0 || viewId == R.id.num_1
//					|| viewId == R.id.num_2 || viewId == R.id.num_3
//					|| viewId == R.id.num_4 || viewId == R.id.num_5
//					|| viewId == R.id.num_6 || viewId == R.id.num_7
//					|| viewId == R.id.num_8 || viewId == R.id.num_9) {
//				Button button = (Button) v.findViewById(viewId);
//				String number = (String) button.getText();
//				digitnum = digitnum + number;
//				if (isPwd) {
//					//AppURL.setSaveNumber(keyPadID, digitnum);
//					//jsimpl.updatePwdValue(NoToX(digitnum), KEY_PAD_TYPE);
//				} else {
//					//jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//				}
//			}
//		}
//	}
//
//	/*****
//	 * 数字、字母转换成星号
//	 *
//	 * @param number
//	 * @return
//	 */
//	private String NoToX(String number) {
//		String X = "";
//		for (int i = 0; i < number.length(); i++) {
//			X = X + "*";
//		}
//		return X;
//	}
//
//	// 字母键盘按钮监听
//	private class Keypad_UOnClickListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//			if (digitnum.length() >= length) {
//				return;
//			}
//			int viewId = v.getId();
//			if (viewId == R.id.abc_a || viewId == R.id.abc_b
//					|| viewId == R.id.abc_c || viewId == R.id.abc_d
//					|| viewId == R.id.abc_e || viewId == R.id.abc_f
//					|| viewId == R.id.abc_g || viewId == R.id.abc_h
//					|| viewId == R.id.abc_i || viewId == R.id.abc_j
//					|| viewId == R.id.abc_k || viewId == R.id.abc_l
//					|| viewId == R.id.abc_m || viewId == R.id.abc_n
//					|| viewId == R.id.abc_o || viewId == R.id.abc_p
//					|| viewId == R.id.abc_q || viewId == R.id.abc_r
//					|| viewId == R.id.abc_s || viewId == R.id.abc_t
//					|| viewId == R.id.abc_u || viewId == R.id.abc_v
//					|| viewId == R.id.abc_w || viewId == R.id.abc_x
//					|| viewId == R.id.abc_y || viewId == R.id.abc_z) {
//				Button button = (Button) v.findViewById(viewId);
//				String number = (String) button.getText();
//				digitnum = digitnum + number;
//				if (isPwd) {
//					//AppURL.setSaveNumber(keyPadID, digitnum);
//					//jsimpl.updatePwdValue(NoToX(digitnum), KEY_PAD_TYPE);
//				} else {
//					//jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//				}
//			}
//			if (viewId == R.id.xiahuaxian) {
//				Button button = (Button) v.findViewById(viewId);
//				String number = (String) button.getText();
//				digitnum = digitnum + number;
//				if (isPwd) {
//					//AppURL.setSaveNumber(keyPadID, digitnum);
//					//jsimpl.updatePwdValue(NoToX(digitnum), KEY_PAD_TYPE);
//				} else {
//					//jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//				}
//			}
//		}
//	}
//
//	// 后退按钮监听
//	private class KeypadBackOnClickListener implements OnClickListener {
//		public void onClick(View v) {
//			if (isStringEmpty(digitnum) || digitnum.length() <= 1) {
//				digitnum = "";
//			} else {
//				digitnum = digitnum.substring(0, digitnum.length() - 1);
//			}
//			if (isPwd) {
////				AppURL.setSaveNumber(keyPadID, digitnum);
////				jsimpl.updatePwdValue(NoToX(digitnum), KEY_PAD_TYPE);
//
//			} else {
////				jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//			}
//		}
//	}
//
//	// 对删除按钮添加长按事件监听，清空用户密码
//	class KeyLongClickListener implements OnLongClickListener {
//
//		@Override
//		public boolean onLongClick(View v) {
//			digitnum = "";
////			AppURL.setSaveNumber(keyPadID, digitnum);
////			jsimpl.updatePwdValue(digitnum, KEY_PAD_TYPE);
//			return false;
//		}
//	}
//
//	public boolean isStringEmpty(String value) {
//		return !isStringNotEmpty(value);
//	}
//
//	/**
//	 * 判断字符串是否不为空
//	 *
//	 * @param value
//	 * @return 字符串不为空返回true，否则返回false
//	 */
//	public boolean isStringNotEmpty(String value) {
//		if (value != null && !"".equals(value)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public void initInputLable(String str) {
//		str = str.trim();
////		if (isPwd) {
////			if (StringTools.isNotEmpty(AppURL.getSaveNumber(keyPadID))) {
////				if (str.length() == AppURL.getSaveNumber(keyPadID).length()) {
////					digitnum = AppURL.getSaveNumber(keyPadID);
////				} else {
////					digitnum = "";
////				}
////			}
////		} else {
////			digitnum = str;
////		}
//		digitnum = str;
//	}
//}