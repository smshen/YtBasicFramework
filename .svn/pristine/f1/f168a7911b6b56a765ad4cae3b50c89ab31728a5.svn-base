package com.yitong.android.view.keyboard;
//package com.yitong.android.view;
//
//import java.text.DecimalFormat;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.yitong.basic.R;
//import com.yitong.utils.CommonUtil;
//import com.yitong.utils.StringTools;
//
///**
// * 金额键盘
// */
//@SuppressLint("ViewConstructor")
//@SuppressWarnings("unused")
//public class WebDigitKeyPad extends View {
//
//	private Context ctx = null;
//	private View v;
//	private String inputnum = "";
//	private String digitnum = "";
//	private Button digitkeypad_1;
//	private Button digitkeypad_2;
//	private Button digitkeypad_3;
//	private Button digitkeypad_4;
//	private Button digitkeypad_5;
//	private Button digitkeypad_6;
//	private Button digitkeypad_7;
//	private Button digitkeypad_8;
//	private Button digitkeypad_9;
//	private Button digitkeypad_0;
//	private Button digitkeypad_;
//	private Button digitkeypad_clear;
//	private ImageView digitkeypad_del;
//	private Button digitkeypad_ok;
//	private EditText digitkeypad_edittext;
//	private View keypad;
////	private SysClientJsImpl jsimpl;
//	private DecimalFormat fmt = new DecimalFormat("##,###,###,###.##");
//	private int left;// 整数长度
//	private Button dismissKeypad;
////	private Typeface typebold;  
//	private TextView keyTitle;
//
//	public WebDigitKeyPad(Context ctx, int left) {//SysClientJsImpl jsimpl,
//		super(ctx);
//		this.ctx = ctx;
//		keypad = this;
//		this.left = left;
////		this.jsimpl = jsimpl;
////		typebold = Typeface.createFromAsset(ctx.getAssets(),
////				"fonts/newfront.ttf");
//	}
//
//	@Override
//	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
//
//	}
//
//	public View setup() {
//		LayoutInflater lif = LayoutInflater.from(ctx);
//		v = lif.inflate(R.layout.control_digitkeypad, null);
//		// 初始化 对象
//		digitkeypad_1 = (Button) v.findViewById(R.id.digitkeypad_1);
//		digitkeypad_2 = (Button) v.findViewById(R.id.digitkeypad_2);
//		digitkeypad_3 = (Button) v.findViewById(R.id.digitkeypad_3);
//		digitkeypad_4 = (Button) v.findViewById(R.id.digitkeypad_4);
//		digitkeypad_5 = (Button) v.findViewById(R.id.digitkeypad_5);
//		digitkeypad_6 = (Button) v.findViewById(R.id.digitkeypad_6);
//		digitkeypad_7 = (Button) v.findViewById(R.id.digitkeypad_7);
//		digitkeypad_8 = (Button) v.findViewById(R.id.digitkeypad_8);
//		digitkeypad_9 = (Button) v.findViewById(R.id.digitkeypad_9);
//		digitkeypad_0 = (Button) v.findViewById(R.id.digitkeypad_0);
//		digitkeypad_ = (Button) v.findViewById(R.id.digitkeypad_point);
//		digitkeypad_del = (ImageView) v.findViewById(R.id.digitkeypad_del);
//		digitkeypad_clear = (Button) v.findViewById(R.id.digitkeypad_clear);
//		digitkeypad_ok = (Button) v.findViewById(R.id.digitkeypad_ok);
//		digitkeypad_edittext = (EditText) v.findViewById(R.id.digitpadedittext);
//		keyTitle = (TextView) v.findViewById(R.id.textTitle);
////		keyTitle.setTypeface(typebold);
////		digitkeypad_1.setTypeface(typebold);
////		digitkeypad_2.setTypeface(typebold);
////		digitkeypad_3.setTypeface(typebold);
////		digitkeypad_4.setTypeface(typebold);
////		digitkeypad_5.setTypeface(typebold);
////		digitkeypad_6.setTypeface(typebold);
////		digitkeypad_7.setTypeface(typebold);
////		digitkeypad_8.setTypeface(typebold);
////		digitkeypad_9.setTypeface(typebold);
////		digitkeypad_0.setTypeface(typebold);
////
////		digitkeypad_.setTypeface(typebold);
////		digitkeypad_clear.setTypeface(typebold);
////		digitkeypad_ok.setTypeface(typebold);
////		digitkeypad_edittext.setTypeface(typebold);
//		// 添加点击事件
//		DigitKeypadOnClickListener dkol = new DigitKeypadOnClickListener();
//		digitkeypad_1.setOnClickListener(dkol);
//		digitkeypad_2.setOnClickListener(dkol);
//		digitkeypad_3.setOnClickListener(dkol);
//		digitkeypad_4.setOnClickListener(dkol);
//		digitkeypad_5.setOnClickListener(dkol);
//		digitkeypad_6.setOnClickListener(dkol);
//		digitkeypad_7.setOnClickListener(dkol);
//		digitkeypad_8.setOnClickListener(dkol);
//		digitkeypad_9.setOnClickListener(dkol);
//		digitkeypad_0.setOnClickListener(dkol);
//		digitkeypad_.setOnClickListener(dkol);
//		digitkeypad_del.setOnClickListener(dkol);
//		digitkeypad_clear.setOnClickListener(dkol);
//		digitkeypad_ok
//				.setOnClickListener(new DigitKeypadFinshOnClikcListener());
//		dismissKeypad = (Button) v.findViewById(R.id.dismissKeypad);
//		dismissKeypad.setOnClickListener(new DigitKeypadFinshOnClikcListener());
//		digitkeypad_edittext.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before,
//					int count) {
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
////				jsimpl.updateFake(digitnum);
//			}
//		});
//
//		return v;
//	}
//
//	private class DigitKeypadFinshOnClikcListener implements OnClickListener {
//
//		public void onClick(View v) {
//			int viewId = v.getId();
//
//			if (viewId == R.id.digitkeypad_ok || viewId == R.id.dismissKeypad) {
//				// 点击完成
//				// 设置值回页面
//				// 隐藏自己View
//				String result = "";
//				if (digitnum.contains(",")) {
//					String[] str = digitnum.split(",");
//					for (int i = 0; i < str.length; i++) {
//						result = result + str[i];
//					}
//				} else {
//					result = digitnum;
//				}
////				jsimpl.hideFake(result.trim());
//
//			}
//		}
//	}
//
//	public void initInputLable(String str) {
//		str = str.trim();
//		digitnum = str;
//		v.post(new Runnable() {
//			@Override
//			public void run() {
//				digitkeypad_edittext.setText(digitnum);
//				try {
//					if (null == digitnum || "".equals(digitnum)) {
//						digitkeypad_edittext.setText("");
//					} else {
//						String endNum = "";
//						// 有小数点
//						if (digitnum.endsWith(".00") || digitnum.endsWith(".0")) {
//							endNum = digitnum.substring(digitnum.indexOf("."),
//									digitnum.length());
//						}
//						// digitnum =
//						// fmt.format(fmt.parse(digitnum).doubleValue());
//						digitnum = digitnum + endNum;
//						digitkeypad_edittext.setText(digitnum);
//					}
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				digitkeypad_edittext.setSelection(digitkeypad_edittext
//						.getText().length());
//				digitnum = digitkeypad_edittext.getText().toString();
////				jsimpl.updateFake(digitnum);
//
//			}
//		});
//	}
//
//	private class DigitKeypadOnClickListener implements OnClickListener {
//
//		public void onClick(View v) {
//			int viewId = v.getId();
//			if ((viewId != R.id.digitkeypad_point
//					&& viewId != R.id.digitkeypad_del && R.id.digitkeypad_clear != viewId)
//					&& (digitnum.indexOf(".") != -1 && digitnum.indexOf(".") == digitnum
//							.length() - 3)) {
//				return;
//			}
//			// 长度限制
//			if (R.id.digitkeypad_del != viewId
//					&& R.id.digitkeypad_clear != viewId) {
//				if (digitnum.replace(",", "").indexOf(".") > -1) {
//					if ((digitnum.replace(".", "")).replace(",", "").length() >= left + 2) {
//						return;
//					}
//				} else if (digitnum.replace(",", "").length() >= left) {
//					return;
//				}
//			}
//			if (viewId == R.id.digitkeypad_clear) {
//				// digitnum = "";
////				jsimpl.hideFake();
//			}
//			if (viewId == R.id.digitkeypad_1) {
//				digitnum = getDecimalFormat(1);
//			}
//			if (viewId == R.id.digitkeypad_2) {
//				digitnum = getDecimalFormat(2);
//			}
//			if (viewId == R.id.digitkeypad_3) {
//				digitnum = getDecimalFormat(3);
//			}
//			if (viewId == R.id.digitkeypad_4) {
//				digitnum = getDecimalFormat(4);
//			}
//			if (viewId == R.id.digitkeypad_5) {
//				digitnum = getDecimalFormat(5);
//			}
//			if (viewId == R.id.digitkeypad_6) {
//				digitnum = getDecimalFormat(6);
//			}
//			if (viewId == R.id.digitkeypad_7) {
//				digitnum = getDecimalFormat(7);
//			}
//			if (viewId == R.id.digitkeypad_8) {
//				digitnum = getDecimalFormat(8);
//			}
//			if (viewId == R.id.digitkeypad_9) {
//				digitnum = getDecimalFormat(9);
//			}
//			if (viewId == R.id.digitkeypad_0) {
//				digitnum = getDecimalFormat(0);
//			}
//			if (viewId == R.id.digitkeypad_del) {
//				// 删除一位
//				getDelFormat();
//			}
//			if (viewId == R.id.digitkeypad_point) {
//				if (StringTools.isNotEmpty(digitnum) && !digitnum.contains(".")) {
//					digitnum = digitnum + ".";
//				}
//			}
//			// 格式化 数据
//			digitkeypad_edittext.setText(digitnum);
//			digitkeypad_edittext.setSelection(null != digitnum ? digitnum
//					.length() : 0);
////			jsimpl.updateFake(digitnum);
//		}
//	}
//
//	public String getDecimalFormat(int input) {
//
//		// String oriNum = digitnum.replace(",", "");
//		String oriNum = digitnum;
//		if (oriNum.endsWith(".") || oriNum.endsWith(".0")) {
//			digitnum += input + "";
//		} else if (oriNum.endsWith(".00")
//				|| (oriNum.contains(".") && oriNum.length()
//						- oriNum.indexOf(".") > 2)) {
//			// 使用原始值
//		} else {
//			oriNum += input + "";
//			// digitnum = fmt.format(Double.parseDouble(oriNum));
//			digitnum = oriNum;
//		}
//		return digitnum;
//	}
//
//	public String getDelFormat() {
//		// String oriNum = digitnum.replace(",", "");
//		String oriNum = digitnum;
//		if (CommonUtil.isStringEmpty(oriNum) || oriNum.length() <= 1) {
//			digitnum = "";
//		} else {
//
//			digitnum = oriNum.substring(0, oriNum.length() - 1);
//			String endNum = "";
//			// 有小数点
//			if (digitnum.endsWith(".00") || digitnum.endsWith(".0")) {
//				endNum = digitnum.substring(digitnum.indexOf("."),
//						digitnum.length());
//			}
//			// digitnum = fmt.format(Double.parseDouble(digitnum));
//			digitnum = digitnum + endNum;
//			// Log.d("DigitKeyPad", "数据："+digitnum);
//		}
//		return digitnum;
//	}
//}