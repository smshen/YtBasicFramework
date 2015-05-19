package com.yitong.android.view.keyboard;
//package com.yitong.android.view;
//
//import java.util.Random;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Handler;
//import android.text.InputType;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnLongClickListener;
//import android.view.View.OnTouchListener;
//import android.view.ViewGroup.LayoutParams;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.yitong.basic.R;
//
//@SuppressWarnings("unused")
//public class PwdKeyBoard {
//	private Context ctx;
//	// 将要使用自定义键盘的EditText控件
//	private EditText e;
//	private String digitnum = "";// 输入值值
//	private PopupWindow popup;
//	private LinearLayout keypad_num;// 数字键盘
//	private LinearLayout keypad_abc;// 字母键盘
//	// 数字按钮
//	private int[] num_int = { R.id.num_0, R.id.num_1, R.id.num_2, R.id.num_3,
//			R.id.num_4, R.id.num_5, R.id.num_6, R.id.num_7, R.id.num_8,
//			R.id.num_9 };
//	private Button[] num_btn = new Button[10];
//	// 小写字母按钮
//	private int[] abc_int = { R.id.abc_a, R.id.abc_b, R.id.abc_c, R.id.abc_d,
//			R.id.abc_e, R.id.abc_f, R.id.abc_g, R.id.abc_h, R.id.abc_i,
//			R.id.abc_j, R.id.abc_k, R.id.abc_l, R.id.abc_m, R.id.abc_n,
//			R.id.abc_o, R.id.abc_p, R.id.abc_q, R.id.abc_r, R.id.abc_s,
//			R.id.abc_t, R.id.abc_u, R.id.abc_v, R.id.abc_w, R.id.abc_x,
//			R.id.abc_y, R.id.abc_z };
//	private String[] abc_str = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
//			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
//			"w", "x", "y", "z" };
//	private Button[] abc_btn = new Button[26];
//	private String[] ABC_str = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
//			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
//			"W", "X", "Y", "Z" };
//	// 键盘标识 0：数字键盘 1:小写字母 2：大写字母
//	public int key_tag = 0;
//	private int length = 30;
//	// 是否密文显示 true:明文 false:密文
//	private boolean isPwd;
//
//	private Button xiaHuaXianButton;
//
//	private boolean changed = true;
//	// private EditText keypad_word; // 输入值框
//	private Button digitkeypad_cancel; // 取消按钮
//	private Button digitkeypad_ok; // 确定按钮
//	private Button digitkeypad_cancel_abc; // 取消按钮
//	private Button digitkeypad_ok_abc; // 确定按钮
//	private String defValue = "";
//	private Button numToAbc;// 数字转换字母键盘
//	private ImageView numDel;// 数字删除
//	private Button abcUP;// 字母变大小写
//	private ImageView abcDel;// 字母删除
//	private Button abcToNum;// 字母转换数字键盘
//	private View menuView;
//	private boolean isUP = false;
//	private boolean isRandom = false;
//
//	private Button dismissKeypad;
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
//	 * */
//
//	private Typeface typebold;
//
//	private boolean isShowXHX;
//
//	public PwdKeyBoard(Context ctx, EditText e, int length, Boolean isPwd,
//			boolean changed, boolean isRandom, boolean isShowXHX) {
//		super();
//		this.ctx = ctx;
//		this.isPwd = isPwd;
//		this.isShowXHX = isShowXHX;
//		this.e = e;
//		this.length = length;
//		this.changed = changed;
//		this.isRandom = isRandom;
//		e.setOnTouchListener(touchListener);
//		typebold = Typeface.createFromAsset(ctx.getAssets(),
//				"fonts/newfront.ttf");
//	}
//
//	// 监听密码键盘
//	private OnTouchListener touchListener = new OnTouchListener() {
//
//		@Override
//		public boolean onTouch(View v, MotionEvent event) {
//			InputMethodManager imm = (InputMethodManager) ctx
//					.getSystemService(Context.INPUT_METHOD_SERVICE);
//			imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//			if (popup != null && popup.isShowing()) {
//				return true;
//			}
//			v = initKeyPopupWindow();
//
//			defValue = e.getText().toString();
//			digitnum = defValue;
//			popup.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
//					0, 0);
//			e.setFocusable(true);
//			e.setFocusableInTouchMode(true);
//			e.requestFocus();
//			e.setSelection(null != defValue ? defValue.length() : 0);
//			return true;
//		}
//	};
//
//	Handler handler = new Handler() {
//		@SuppressWarnings("deprecation")
//		public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case 0:
//				initUper();
//				abcUP.setBackgroundDrawable(ctx.getResources().getDrawable(
//						R.drawable.up_s));
//				break;
//			case 1:
//				initLower();
//				abcUP.setBackgroundDrawable(ctx.getResources().getDrawable(
//						R.drawable.up_n));
//				break;
//
//			default:
//				break;
//			}
//		};
//	};
//	private TextView keyTitle;
//
//	@SuppressWarnings("deprecation")
//	private View initKeyPopupWindow() {
//		menuView = LayoutInflater.from(ctx)
//				.inflate(R.layout.new_keyboard, null);
//		// keypad_word = (EditText) menuView.findViewById(R.id.inputstr);
//		e.setInputType(InputType.TYPE_CLASS_TEXT
//				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
//		// e.setOnTouchListener(onTouchListener);
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
//
//		xiaHuaXianButton = (Button) menuView.findViewById(R.id.xiahuaxian);
//
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
//		numToAbc.setTypeface(typebold);
//		if (changed) {
//			numToAbc.setOnClickListener(l);
//		} else {
//			numToAbc.setText("");
//			numToAbc.setVisibility(View.GONE);
//		}
//
//		abcToNum = (Button) menuView.findViewById(R.id.abc_123);
//		abcToNum.setTypeface(typebold);
//		abcToNum.setOnClickListener(l);
//		abcUP = (Button) menuView.findViewById(R.id.abc_up);
//		abcUP.setOnClickListener(l);
//		initNum();
//		initLower();
//		// initUper(menuView);
//		this.popup = new PopupWindow(menuView, LayoutParams.MATCH_PARENT,
//				LayoutParams.WRAP_CONTENT);
//		this.popup.setAnimationStyle(R.style.menushow);
//		this.popup.setBackgroundDrawable(new BitmapDrawable());
//		this.popup.setFocusable(true);
//		this.popup.update();
//		if (isPwd) {
//			e.setInputType(InputType.TYPE_CLASS_TEXT
//					| InputType.TYPE_TEXT_VARIATION_PASSWORD);
//		} else {
//			e.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//		}
//		return menuView;
//	}
//
//	// 初始化随机密码键盘
//	private void initNum() {
//		// Log.w("当前键盘", "initNum");
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
//				num_btn[i].setOnClickListener(keypad1listener);
//				num_btn[i].setTypeface(typebold);
//			}
//		} else {
//			for (int i = 0; i < num_btn.length; i++) {
//				num_btn[i] = (Button) menuView.findViewById(num_int[i]);
//				num_btn[i].setTypeface(typebold);
//				num_btn[i].setOnClickListener(keypad1listener);
//			}
//		}
//
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
//			xiaHuaXianButton.setVisibility(View.VISIBLE);
//			xiaHuaXianButton.setOnClickListener(keypad1listener);
//		}
//
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
//	}
//
//	private OnTouchListener onTouchListener = new OnTouchListener() {
//
//		@Override
//		public boolean onTouch(View v, MotionEvent event) {
//			InputMethodManager imm = (InputMethodManager) ctx
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
//			int id = v.getId();
//			if (id == R.id.left_clear_btn || id == R.id.left_clear_btn_abc) { // 清空
//				e.setText("");
//				digitnum = "";
//				// popup.dismiss();
//			}
//			if (id == R.id.ritht_ok_btn || id == R.id.ritht_ok_btn_abc
//					|| id == R.id.dismissKeypad) {// 确定
//				e.setFocusable(true);
//				e.setFocusableInTouchMode(true);
//				e.requestFocus();
//				e.setText(digitnum);
//				e.setSelection(null != digitnum ? digitnum.length() : 0);
//				popup.dismiss();
//			}
//			if (id == R.id.num_change) {// 切换按钮
//				changeview();
//			}
//			if (id == R.id.abc_123) {// 切换按钮
//				changeview();
//			}
//			if (id == R.id.abc_up) {// 切换按钮
//				changeABC();
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
//			handler.sendEmptyMessage(0);
//		} else {
//			handler.sendEmptyMessage(1);
//		}
//	}
//
//	// 数字键盘按钮监听
//	private class KeypadOnClickListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//			String inputValue = e.getText().toString();
//			if (inputValue.length() == length) {
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
//				e.setText(digitnum);
//				e.setTextColor(ctx.getResources().getColor(R.color.black));
//				e.setSelection(null != digitnum ? digitnum.length() : 0);
//			}
//		}
//	}
//
//	// 字母键盘按钮监听
//	private class Keypad_UOnClickListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//			String inputValue = e.getText().toString();
//			if (inputValue.length() == length) {
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
//				e.setText(digitnum);
//				e.setTextColor(ctx.getResources().getColor(R.color.black));
//				e.setSelection(null != digitnum ? digitnum.length() : 0);
//			}
//			if (viewId == R.id.xiahuaxian) {
//				Button button = (Button) v.findViewById(viewId);
//				String number = (String) button.getText();
//				digitnum = digitnum + number;
//				e.setText(digitnum);
//				e.setTextColor(ctx.getResources().getColor(R.color.black));
//				e.setSelection(null != digitnum ? digitnum.length() : 0);
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
//
//			e.setText(digitnum);
//			e.setTextColor(ctx.getResources().getColor(R.color.black));
//			e.setSelection(null != digitnum ? digitnum.length() : 0);
//		}
//	}
//
//	// 对删除按钮添加长按事件监听，清空用户密码
//	private class KeyLongClickListener implements OnLongClickListener {
//
//		@Override
//		public boolean onLongClick(View v) {
//			digitnum = "";
//			e.setText(digitnum);
//			e.setTextColor(ctx.getResources().getColor(R.color.black));
//			e.setSelection(null != digitnum ? digitnum.length() : 0);
//			return false;
//		}
//
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
//}