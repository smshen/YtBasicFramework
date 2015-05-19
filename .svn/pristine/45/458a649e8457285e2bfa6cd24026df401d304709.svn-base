package com.yitong.android.view.keyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.text.InputType;
import android.text.Selection;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.yitong.basic.R;
import com.yitong.logs.Logs;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * 键盘
 * 
 * @Description
 * @Author Lewis(lgs@yitong.com.cn) 2014年6月18日 下午8:21:07
 * @Class AllKeyBoard Copyright (c) 2014 Shanghai P&C Information Technology
 *        Co.,Ltd. All rights reserved.
 */
public class AllKeyBoard {

	private Activity activity;

	public boolean isupper = false;// 是否大写

	private EditText ed;

	private boolean isPwd, isWebKeyBoard;

	// 默认字母键盘
	private int keyFlag = 1;

	LinearLayout linearLayoutKeyBoard, keypad_abc, key_board_sign, keypad_num,
			linearLayoutSignBoardOne, linearLayoutSignBoardTwo;

	// 当前输入的数字长度
	private int currentLength = 0;
	// 输入长度限制
	private int inputMaxLength = 15;
	// 录入文本记录
	// public static String inputValue = "";
	public static StringBuffer inputValue = new StringBuffer();// 输入值值

	private Button[] btnFunctions = new Button[14];
	private Button[] btnNumbs = new Button[10];
	private Button[] btnAbcs = new Button[26];
	private Button[] btnSigns = new Button[32];

	private int[] keyFunctionIds = { R.id.btnBoardCancle,
			R.id.btnAbcBoardAbcChange, R.id.btnAbcBoardDel,
			R.id.btnAbcBoardChangeNum, R.id.btnAbcBoardSpace,
			R.id.btnAbcBoardChangeSign, R.id.btnAbcBoardOk,
			R.id.btnNumBoardChangeAbc, R.id.btnNumBoardPoint,
			R.id.btnNumBoardOk, R.id.btnSignBoardUp, R.id.btnSignBoardDown,
			R.id.btnSignBoardDel, R.id.btnSignBoardBack };

	// 数字按钮
	private int[] keyNumIds = { R.id.key_board_num_zero,
			R.id.key_board_num_one, R.id.key_board_num_two,
			R.id.key_board_num_three, R.id.key_board_num_four,
			R.id.key_board_num_five, R.id.key_board_num_six,
			R.id.key_board_num_seven, R.id.key_board_num_eight,
			R.id.key_board_num_nine };

	// 小写字母按钮
	private int[] keyAbcIds = { R.id.key_board_abc_a, R.id.key_board_abc_b,
			R.id.key_board_abc_c, R.id.key_board_abc_d, R.id.key_board_abc_e,
			R.id.key_board_abc_f, R.id.key_board_abc_g, R.id.key_board_abc_h,
			R.id.key_board_abc_i, R.id.key_board_abc_j, R.id.key_board_abc_k,
			R.id.key_board_abc_l, R.id.key_board_abc_m, R.id.key_board_abc_n,
			R.id.key_board_abc_o, R.id.key_board_abc_p, R.id.key_board_abc_q,
			R.id.key_board_abc_r, R.id.key_board_abc_s, R.id.key_board_abc_t,
			R.id.key_board_abc_u, R.id.key_board_abc_v, R.id.key_board_abc_w,
			R.id.key_board_abc_x, R.id.key_board_abc_y, R.id.key_board_abc_z };

	// 符号键盘
	private int[] keySignIds = { R.id.key_pad_sign_1, R.id.key_pad_sign_2,
			R.id.key_pad_sign_3, R.id.key_pad_sign_4, R.id.key_pad_sign_5,
			R.id.key_pad_sign_6, R.id.key_pad_sign_7, R.id.key_pad_sign_8,
			R.id.key_pad_sign_9, R.id.key_pad_sign_10, R.id.key_pad_sign_11,
			R.id.key_pad_sign_12, R.id.key_pad_sign_13, R.id.key_pad_sign_14,
			R.id.key_pad_sign_15, R.id.key_pad_sign_16, R.id.key_pad_sign_17,
			R.id.key_pad_sign_18, R.id.key_pad_sign_19, R.id.key_pad_sign_20,
			R.id.key_pad_sign_21, R.id.key_pad_sign_22, R.id.key_pad_sign_23,
			R.id.key_pad_sign_24, R.id.key_pad_sign_25, R.id.key_pad_sign_26,
			R.id.key_pad_sign_27, R.id.key_pad_sign_28, R.id.key_pad_sign_29,
			R.id.key_pad_sign_30, R.id.key_pad_sign_31, R.id.key_pad_sign_32 };

	String[] keyAbcStrsLower = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
			"w", "x", "y", "z" };

	String[] keyAbsStrsUpper = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	private boolean isInitUi = false;

	public void setCurrentLength(int currentLength) {
		this.currentLength = currentLength;
	}

	/**
	 * ** isPwd 是否密文 keyFlag 1 默认字母 2 默认数字 3默认符号
	 * 
	 * @Description
	 * @author 孙靖
	 * @version 1.0 2013-12-31
	 */
	public AllKeyBoard(Activity mActivity, EditText edit, boolean isPassword,
			int keyFlag, String keypadType) {
		this.activity = mActivity;
		this.ed = edit;
		this.isPwd = isPassword;
		this.keypadType = keypadType;
		this.keyFlag = keyFlag;
		// 设置弹出键盘事件
		this.ed.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (!isInitUi) {
					isInitUi = true;
					init();
				}
				ed.requestFocus();
				ed.requestFocusFromTouch();
				ed.setCursorVisible(true);// 显示光标
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (Build.VERSION.SDK_INT >= 11) {
						Method setShowSoftInputOnFocus = null;
						try {
							setShowSoftInputOnFocus = ed.getClass().getMethod(
									"setShowSoftInputOnFocus", boolean.class);
							setShowSoftInputOnFocus.setAccessible(true);
							setShowSoftInputOnFocus.invoke(ed, false);
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						// ed.setInputType(InputType.TYPE_NULL);
					}
					InputMethodManager imm = (InputMethodManager) activity
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					showKeyboard();
					return true;
				}
				return true;
			}
		});		
		
		
	}

	public void init() {
		// 获取控件对象
		findKeyBoardView();
		if (keyFlag == 1) {
			randomKeyAbc();
		} else if (keyFlag == 2) {
			randomKeyNum();
		} else if (keyFlag == 3) {
			randomKeySign();
		} else {
			randomKeyAbc();
		}

		// 让一个视图浮动在你的应用程序之上
		final WindowManager.LayoutParams params = new WindowManager.LayoutParams();

		// 类型
		params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
		// FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
		// 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		;
		// 不设置这个弹出框的透明遮罩显示为黑色
		params.format = PixelFormat.TRANSLUCENT;

		params.width = WindowManager.LayoutParams.MATCH_PARENT;
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;

		params.gravity = Gravity.BOTTOM;
		windowmanager.addView(boardView, params);
		if (isPwd) {
			ed.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		} else {
			ed.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
		}

		// 切换输入框时隐藏键盘
		this.ed.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard();
				}
			}
		});

		
	}

	/**
	 * isPwd 是否密文 keyFlag 1 默认字母 2 默认数字 3默认符号 此构造方法用于web调用键盘
	 * 
	 * @Description
	 * @author 孙靖
	 * @version 1.0 2013年12月29日
	 */
	private String keypadType;

	private Button btnNum_X, btnNumBoardChangeAbc, btnNumBoardPoint;

	private View bottomfirstview, dismissView;

	private Button btnSignBoardUp, btnSignBoardDown;

	private KeyBoardJsImpl jsimpl;

	public AllKeyBoard(Activity mActivity, boolean isPassword, int keyTypeFlag,
			KeyBoardJsImpl jsimpl, boolean isWebKeyBoard, String keypadType) {
		this.activity = mActivity;
		this.isPwd = isPassword;
		this.keypadType = keypadType;
		this.jsimpl = jsimpl;
		this.isWebKeyBoard = isWebKeyBoard;
		// application = (SZApplication) this.activity.getApplication();
		this.keyFlag = keyTypeFlag;

		// 获取控件对象
		findKeyBoardView();

		handler.post(new Runnable() {
			@Override
			public void run() {
				if (keyFlag == 1) {
					randomKeyAbc();
				} else if (keyFlag == 2) {
					randomKeyNum();
				} else if (keyFlag == 3) {
					randomKeySign();
				} else {
					randomKeyAbc();
				}
			}
		});

		// 让一个视图浮动在你的应用程序之上
//		final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
//
//		// 类型
//		params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
//		// FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
//		// 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
//		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//		;
//		// 不设置这个弹出框的透明遮罩显示为黑色
//		params.format = PixelFormat.TRANSLUCENT;
//
//		params.width = WindowManager.LayoutParams.MATCH_PARENT;
//		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//		params.gravity = Gravity.BOTTOM;
//		windowmanager.addView(boardView, params);
		
		dialog = new Dialog(activity, R.style.TransparentNoFrameDialogStyle);
		dialog.setContentView(boardView, new LayoutParams(
			LayoutParams.MATCH_PARENT,
			LayoutParams.WRAP_CONTENT));

		// 设置显示动画
		Window window = dialog.getWindow();
		window.setWindowAnimations(R.style.bottom_show_dialog_anim_style);
		WindowManager.LayoutParams params = window.getAttributes();
		params.width = ViewGroup.LayoutParams.MATCH_PARENT;
		params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		params.gravity = Gravity.BOTTOM;
		dialog.onWindowAttributesChanged(params);
		dialog.setCanceledOnTouchOutside(true);
	}


	View boardView;
	WindowManager windowmanager;
	Dialog dialog;

	private void findKeyBoardView() {

		boardView = activity.getLayoutInflater().inflate(
				R.layout.key_board_allkeyboard, null);
		windowmanager = (WindowManager) activity
				.getSystemService(Context.WINDOW_SERVICE);

		bottomfirstview = boardView.findViewById(R.id.bottomfirstview);
		btnNum_X = (Button) boardView.findViewById(R.id.btnNum_X);
		btnNum_X.setOnClickListener(keyBoardInputClickListener);
		btnNumBoardChangeAbc = (Button) boardView
				.findViewById(R.id.btnNumBoardChangeAbc);
		linearLayoutKeyBoard = (LinearLayout) boardView
				.findViewById(R.id.linearLayoutKeyBoard);

		keypad_abc = (LinearLayout) boardView.findViewById(R.id.keypad_abc);

		key_board_sign = (LinearLayout) boardView
				.findViewById(R.id.key_board_sign);

		btnNumBoardPoint = (Button) boardView
				.findViewById(R.id.btnNumBoardPoint);

		keypad_num = (LinearLayout) boardView.findViewById(R.id.keypad_num);

		linearLayoutSignBoardOne = (LinearLayout) boardView
				.findViewById(R.id.linearLayoutSignBoardOne);
		linearLayoutSignBoardTwo = (LinearLayout) boardView
				.findViewById(R.id.linearLayoutSignBoardTwo);
		btnSignBoardUp = (Button) boardView.findViewById(R.id.btnSignBoardUp);
		btnSignBoardDown = (Button) boardView
				.findViewById(R.id.btnSignBoardDown);

		for (int i = 0; i < btnFunctions.length; i++) {
			btnFunctions[i] = (Button) boardView
					.findViewById(keyFunctionIds[i]);
			if (btnFunctions[i] != null) {
				btnFunctions[i]
						.setOnClickListener(new keyBoardFunctionClickListener());
			}
		}
		btnSignBoardUp.setBackgroundResource(R.drawable.key_up_bga);
		btnSignBoardUp.setClickable(false);
		btnSignBoardDown.setBackgroundResource(R.drawable.key_down_bga3);
		btnSignBoardDown.setClickable(true);

		for (int i = 0; i < btnNumbs.length; i++) {
			btnNumbs[i] = (Button) boardView.findViewById(keyNumIds[i]);
			if (btnNumbs[i] != null) {
				// 金额键盘的监听
				if (null != keypadType && keypadType.equals("11")) {
					btnNumbs[i].setOnClickListener(digitPadClickListener);
				} else {
					btnNumbs[i].setOnClickListener(keyBoardInputClickListener);
				}
			}
		}

		for (int i = 0; i < btnAbcs.length; i++) {
			btnAbcs[i] = (Button) boardView.findViewById(keyAbcIds[i]);
			if (btnAbcs[i] != null) {
				btnAbcs[i].setOnClickListener(keyBoardInputClickListener);
			}
		}
		for (int i = 0; i < btnSigns.length; i++) {
			btnSigns[i] = (Button) boardView.findViewById(keySignIds[i]);
			if (btnSigns[i] != null) {
				btnSigns[i].setOnClickListener(keyBoardInputClickListener);
			}
		}
	}

	/**
	 * 赋值，两种赋值方式 web和native
	 * 
	 * @Description
	 * @version 1.0 2014-1-8
	 */
	private void setValue(String inputStr) {
		if (currentLength >= inputMaxLength) {
			return;
		}
		currentLength++;
		// 拼接录入值
		// web键盘
		if (isWebKeyBoard) {

			if (isPwd) {
				inputValue = CryptoKeyUtil.encrypt(inputValue, inputStr);
			} else {
				inputValue.append(inputStr);
			}

			jsimpl.updateDigitNumber(inputValue, currentLength);
		} else {
			// 本地键盘
			if (isPwd) {
				inputValue = CryptoKeyUtil.encrypt(inputValue, inputStr);
				StringBuffer str = new StringBuffer();
				for (int i = 0; i < currentLength; i++) {
					str.append("*");
				}
				// 模拟输入的长度，展示假值
				ed.setText(str.toString());
				// tag存入真正加密的值
				ed.setTag(inputValue.toString());
			} else {
				inputValue.append(inputStr);
				ed.setText(inputValue.toString());
			}

			Selection.setSelection(ed.getText(), ed.getText().length());// 移动光标到最右
		}
	}

	/**
	 * 删除
	 * 
	 * @param inputStr
	 * @Description
	 * @version 1.0 2014-1-8
	 */
	private void delValue() {
		if (currentLength <= 0) {
			return;
		}
		currentLength--;
		if (isWebKeyBoard) {
			// web键盘
			if (isPwd) {
				inputValue = CryptoKeyUtil.delDecrypt(inputValue);
			} else {
				inputValue.deleteCharAt(inputValue.length() - 1);
			}
			jsimpl.updateDigitNumber(inputValue, currentLength);
		} else {
			if (isPwd) {
				inputValue = CryptoKeyUtil.delDecrypt(inputValue);
				StringBuffer str = new StringBuffer();
				for (int i = 0; i < currentLength; i++) {
					str.append("*");
				}
				// 模拟输入的长度，展示假值
				ed.setText(str.toString());
				// tag存入真正加密的值
				ed.setTag(inputValue.toString());
			} else {
				inputValue.deleteCharAt(inputValue.length() - 1);
				ed.setText(inputValue.toString());
			}
			Selection.setSelection(ed.getText(), ed.getText().length());// 移动光标
		}
	}

	public void clear() {
		currentLength = 0;
		inputValue.setLength(0);
		if (isWebKeyBoard) {
			jsimpl.updateDigitNumber(inputValue, 0);
		} else {
			ed.setText("");
			ed.setTag("");
		}
	}

	// 金额键盘的监听
	private OnClickListener digitPadClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Button inputBtn = (Button) v;

			if ((inputBtn.getText().toString().equals(".") && inputValue
					.indexOf(".") != -1)
					|| (inputBtn.getText().toString().equals(".") && inputValue
							.length() == 0)) {
				// 如果按下的是 "." 并且原来值里已经有"."或者输入的第一个值为"."则返回
				return;
			} else {
				// 拼接录入值
				setValue(inputBtn.getText().toString());
			}
		}
	};

	// 输入监听
	private OnClickListener keyBoardInputClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Logs.v("TAG", "-------------isWebKeyBoard:" + isWebKeyBoard
					+ ",inputMaxLength:" + inputMaxLength);
			if (isWebKeyBoard) {
				// 录入值 没超过界限 继续录入
				if (currentLength < inputMaxLength) {
					Button inputBtn = (Button) v;
					// 拼接录入值
					setValue(inputBtn.getText().toString());
				}
			} else {
				Button inputBtn = (Button) v;
				// 拼接录入值
				setValue(inputBtn.getText().toString());
			}
		}
	};

	// 功能监听
	private class keyBoardFunctionClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			if (v.getId() == R.id.btnBoardCancle) {
				hideKeyboard();
			} else if (v.getId() == R.id.btnAbcBoardAbcChange) {
				changeKey();
				randomKeyAbc();
			} else if (v.getId() == R.id.btnAbcBoardDel) {
				delValue();
			} else if (v.getId() == R.id.btnAbcBoardChangeNum) {
				randomKeyNum();
			} else if (v.getId() == R.id.btnAbcBoardSpace) {
				setValue(" ");
			} else if (v.getId() == R.id.btnAbcBoardChangeSign) {
				randomKeySign();
			} else if (v.getId() == R.id.btnAbcBoardOk) {
				// 确定按钮事件
				hideKeyboard();
				if (isWebKeyBoard) {
					jsimpl.updateDigitNumber(inputValue, currentLength);
				}
			} else if (v.getId() == R.id.btnNumBoardChangeAbc) {
				randomKeyAbc();
			} else if (v.getId() == R.id.btnNumBoardPoint) {
				// 点赋值
				setValue(activity.getResources().getString(R.string.char_46));
			} else if (v.getId() == R.id.btnNumBoardOk) {
				delValue();
			} else if (v.getId() == R.id.btnSignBoardUp) {
				// 这里需要增加切换动画
				Animation animation = AnimationUtils.loadAnimation(activity,
						R.anim.dialog_enter);
				Animation animation2 = AnimationUtils.loadAnimation(activity,
						R.anim.dialog_enter_2);
				linearLayoutSignBoardOne.setVisibility(View.VISIBLE);
				linearLayoutSignBoardOne.startAnimation(animation);

				linearLayoutSignBoardTwo.setVisibility(View.GONE);
				linearLayoutSignBoardTwo.startAnimation(animation2);
				btnSignBoardUp.setBackgroundResource(R.drawable.key_up_bga);
				btnSignBoardUp.setClickable(false);
				btnSignBoardDown
						.setBackgroundResource(R.drawable.key_down_bga3);
				btnSignBoardDown.setClickable(true);
			} else if (v.getId() == R.id.btnSignBoardDown) {
				// 这里需要增加切换动画
				Animation animation1 = AnimationUtils.loadAnimation(activity,
						R.anim.dialog_exit);
				Animation animation3 = AnimationUtils.loadAnimation(activity,
						R.anim.dialog_exit_2);
				linearLayoutSignBoardOne.setVisibility(View.GONE);
				linearLayoutSignBoardOne.startAnimation(animation1);
				linearLayoutSignBoardTwo.setVisibility(View.VISIBLE);
				linearLayoutSignBoardTwo.startAnimation(animation3);
				btnSignBoardUp.setBackgroundResource(R.drawable.key_up_bga3);
				btnSignBoardUp.setClickable(true);
				btnSignBoardDown.setBackgroundResource(R.drawable.key_down_bga);
				btnSignBoardDown.setClickable(false);
			} else if (v.getId() == R.id.btnSignBoardDel) {
				delValue();
			} else if (v.getId() == R.id.btnSignBoardBack) {
				randomKeyAbc();
			}
		}
	}

	;

	/**
	 * 键盘大小写切换 这里需要进行加密处理
	 */
	@SuppressLint("DefaultLocale")
	private void changeKey() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (isupper) {// 大写切换小写
					isupper = false;
					for (int i = 0; i < btnAbcs.length; i++) {
						btnAbcs[i].setText(btnAbcs[i].getText().toString()
								.toLowerCase());
					}
					// 设置背景为大写 这里需要补充设置小写背景
					btnFunctions[1]
							.setBackgroundResource(R.drawable.key_shift_bg);
				} else {// 小写切换大写
					isupper = true;
					for (int i = 0; i < btnAbcs.length; i++) {
						btnAbcs[i].setText(btnAbcs[i].getText().toString()
								.toUpperCase());
					}
					// 设置背景为大写 这里需要补充设置大写背景
					btnFunctions[1]
							.setBackgroundResource(R.drawable.key_shift_bg2);
				}
			}
		});
	}

	/**
	 * @param inputLength
	 *            输入框最大长度
	 */
	public void showKeyboard(int inputLength) {
		this.inputMaxLength = inputLength;
		if (!isWebKeyBoard) {
			if (isPwd) {
				String str = (String) ed.getTag();
				if (StringUtils.isNotEmpty(str)) {
					this.inputValue = new StringBuffer(ed.getTag().toString());
				}
			} else {
				this.inputValue = new StringBuffer(ed.getText().toString());
			}

		}
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (keyFlag == 1) {
					randomKeyAbc();
				} else if (keyFlag == 2) {
					randomKeyNum();
				} else if (keyFlag == 3) {
					randomKeySign();
				} else {
					randomKeyAbc();
				}
				int visibility = linearLayoutKeyBoard.getVisibility();
				if (visibility == View.GONE || visibility == View.INVISIBLE) {
					Animation animation = AnimationUtils.loadAnimation(
							activity, R.anim.dialog_enter);
					linearLayoutKeyBoard.startAnimation(animation);
					linearLayoutKeyBoard.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	public void showKeyboard() {
		if (!isWebKeyBoard) {
			if (isPwd) {
				String str = (String) ed.getTag();
				if (StringUtils.isNotEmpty(str)) {
					this.inputValue = new StringBuffer(ed.getTag().toString());
				} else {
					this.inputValue = new StringBuffer();
				}
			} else {
				this.inputValue = new StringBuffer(ed.getText().toString());
			}
		}

		handler.post((new Runnable() {
			@Override
			public void run() {
				if (keyFlag == 1) {
					randomKeyAbc();
				} else if (keyFlag == 2) {
					randomKeyNum();
				} else if (keyFlag == 3) {
					randomKeySign();
				} else {
					randomKeyAbc();
				}
//				Animation animation = AnimationUtils.loadAnimation(activity,
//						R.anim.dialog_enter);
//				linearLayoutKeyBoard.startAnimation(animation);
				linearLayoutKeyBoard.setVisibility(View.VISIBLE);

				boardView.setVisibility(View.VISIBLE);
				dialog.show();
			}
		}));

	}

	public void hideKeyboard() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				boardView.setVisibility(View.GONE);
				dialog.dismiss();
			}
		});
	}

	/**
	 * 加密和随机数字键盘
	 * 
	 * @Description
	 * @author 孙靖
	 * @version 1.0 2013年12月30日
	 */
	private void randomKeyNum() {

		handler.post(new Runnable() {

			@Override
			public void run() {
				// 非密码 不随机
				if (!isPwd) {
					for (int i = 0; i < btnNumbs.length; i++) {
						btnNumbs[i] = (Button) boardView
								.findViewById(keyNumIds[i]);
						btnNumbs[i].setText(i + "");
						if (btnNumbs[i] != null) {
							// 金额键盘的监听
							if (null != keypadType && keypadType.equals("11")) {
								btnNumbs[i]
										.setOnClickListener(digitPadClickListener);
							} else {
								btnNumbs[i]
										.setOnClickListener(keyBoardInputClickListener);
							}
						}
					}

				} else {
					// 更新界面
					Random ran = new Random();
					java.util.List<Integer> list = new java.util.ArrayList<Integer>();
					while (list.size() < 10) {
						int n = ran.nextInt(10);
						if (!list.contains(n)) {
							list.add(n);// 如果n不包涵在list中，才添加
						}
					}
					for (int i = 0; i < keyNumIds.length; i++) {
						btnNumbs[i] = (Button) boardView
								.findViewById(keyNumIds[i]);

						btnNumbs[i].setText(list.get(i) + "");
						if (null != keypadType && keypadType.equals("11")) {
							btnNumbs[i]
									.setOnClickListener(digitPadClickListener);
						} else {
							btnNumbs[i]
									.setOnClickListener(keyBoardInputClickListener);
						}
					}
				}

				if (null != keypadType && keypadType.equals("9")) {
					showBoardByType(9);
				} else if (null != keypadType && keypadType.equals("10")) {
					showBoardByType(10);
				} else if (null != keypadType && keypadType.equals("7")) {
					showBoardByType(7);
				} else if (null != keypadType && keypadType.equals("11")) {
					showBoardByType(11);
				} else {
					// 缺少加密方法
					showBoardByType(0);
				}
				return;
			}
		});
	}

	/**
	 * @Description
	 * @author 孙靖
	 * @version 1.0 2013年12月30日
	 */
	private void randomKeyAbc() {
		handler.post(new Runnable() {

			@Override
			public void run() {
				if (true) {
					// 缺少加密方法
					showBoardByType(1);
					return;
				}
				// 随机暂不需要完成
			}
		});
	}

	/**
	 * 随机加密符号键盘
	 * 
	 * @Description
	 * @author 孙靖
	 * @version 1.0 2013年12月30日
	 */
	private void randomKeySign() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (true) {
					// 缺少加密方法
					showBoardByType(3);
					return;
				}
				// 随机暂不需要完成
			}
		});
	}

	Handler handler = new Handler();

	/**
	 * 根据参数显示某种键盘
	 * 
	 * @param flag
	 *            1=abc 2=数字 3=符号
	 * @Description
	 */
	private void showBoardByType(final int flag) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				keypad_abc.setVisibility(View.GONE);
				key_board_sign.setVisibility(View.GONE);
				keypad_num.setVisibility(View.GONE);
				if (flag == 0) {
					keypad_num.setVisibility(View.VISIBLE);
					bottomfirstview.setVisibility(View.VISIBLE);
					btnNumBoardPoint.setVisibility(View.VISIBLE);
					btnNum_X.setVisibility(View.GONE);
					btnNumBoardChangeAbc.setVisibility(View.VISIBLE);
				} else if (flag == 1) {
					keypad_abc.setVisibility(View.VISIBLE);
				} else if (flag == 2) {
					keypad_num.setVisibility(View.VISIBLE);
					bottomfirstview.setVisibility(View.GONE);
					btnNumBoardPoint.setVisibility(View.GONE);
					btnNum_X.setVisibility(View.VISIBLE);
					btnNumBoardChangeAbc.setVisibility(View.GONE);
					btnNum_X.setClickable(true);
					btnNum_X.setText("清 空");
					btnNum_X.setTextColor(activity.getResources().getColor(
							R.color.white));
					btnNum_X.setBackgroundResource(R.drawable.key_changenum_bg_select);
					btnNum_X.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							clear();
						}
					});
				} else if (flag == 3) {
					key_board_sign.setVisibility(View.VISIBLE);
				} else if (flag == 9 || flag == 7) {
					keypad_num.setVisibility(View.VISIBLE);
					bottomfirstview.setVisibility(View.GONE);
					btnNumBoardPoint.setVisibility(View.GONE);
					btnNum_X.setVisibility(View.VISIBLE);
					btnNum_X.setText("清 空");
					btnNum_X.setTextColor(activity.getResources().getColor(
							R.color.white));
					btnNum_X.setClickable(true);
					btnNum_X.setBackgroundResource(R.drawable.key_changenum_bg_select);
					btnNum_X.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							clear();
						}
					});
					btnNumBoardChangeAbc.setVisibility(View.GONE);
				} else if (flag == 11) {
					// 金融键盘
					keypad_num.setVisibility(View.VISIBLE);
					bottomfirstview.setVisibility(View.GONE);
					btnNumBoardPoint.setVisibility(View.GONE);
					btnNumBoardChangeAbc.setVisibility(View.GONE);
					btnNum_X.setVisibility(View.VISIBLE);
					btnNum_X.setText(".");
					btnNum_X.setTextColor(activity.getResources().getColor(
							R.color.black));
					btnNum_X.setClickable(true);
					btnNum_X.setBackgroundResource(R.drawable.key_bg_select);
					btnNum_X.setOnClickListener(digitPadClickListener);
				} else {
					keypad_abc.setVisibility(View.VISIBLE);
				}
			}
		});

	}
}