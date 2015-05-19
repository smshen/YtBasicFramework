package com.yitong.android.view.keyboard;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.yitong.basic.R;


/***
 * 上下文键盘
 * 
 */
public class ContentPad extends View {

	private Context ctx = null;

	private View v;

//	private SysClientJsImpl jsimpl;

	private WebView webView;

	LinearLayout menuRoot = null;

	//private Typeface typebold;

	public ContentPad(Context ctx, WebView webView) {//SysClientJsImpl jsimpl,
		super(ctx);
		this.ctx = ctx;
//		this.jsimpl = jsimpl;
		this.webView = webView;
		//typebold = Typeface.createFromAsset(ctx.getAssets(),
				//"fonts/newfront.ttf");
	}

	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	public View setup() {
		LayoutInflater lif = LayoutInflater.from(ctx);
		v = lif.inflate(R.layout.control_contentpad, null);
		menuRoot = (LinearLayout) v.findViewById(R.id.menuRoot);
		return v;
	}
	
	/**
	 * 
	 * @author 尹银川
	 * @date   2014-2-25
	 * @version V1.0
	 */
	public void initMenuAction(String contextPadConfig){
		try{
			final JSONObject json = new JSONObject(contextPadConfig);
			Button btn = creatButton();
			btn.setText(json.getString("name"));
			btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					try {
//						jsimpl.hideContentPad();
						webView.loadUrl("javascript:"
								+ json.getString("func"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			menuRoot.addView(btn);
			
			Button btn1 = creatButton();
			btn1.setBackgroundResource(R.drawable.btn_big_black_bg);
			btn1.setText("取消");
			btn1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
//					jsimpl.hideContentPad();
				}
			});
			menuRoot.addView(btn1);
		}catch(JSONException jsone){
			jsone.printStackTrace();
		}
	}

//	public void initMenuAction(String contextPadConfig) {
//		try {
//			menuRoot.removeAllViews();
//
//			JSONArray cfg = new JSONArray(contextPadConfig);
//			for (int i = 0; i < cfg.length(); i++) {
//				final JSONObject json = cfg.getJSONObject(i);
//
//				Button btn = creatButton();
//				// btn.setTypeface(typebold);
//				btn.setText(json.getString("name"));
//				/*
//				 * btn.setTextColor(ctx.getResources().getColor(
//				 * R.color.addview_left_textcolor));
//				 */
//				btn.setOnClickListener(new OnClickListener() {
//
//					public void onClick(View v) {
////						try {
////							jsimpl.hideContentPad();
////							webView.loadUrl("javascript:"
////									+ json.getString("func"));
////						} catch (JSONException e) {
////							e.printStackTrace();
////						}
//					}
//				});
//				menuRoot.addView(btn);
//			}
//
//			Button btn = creatButton();
//			// btn.setTypeface(typebold);
//			btn.setBackgroundResource(R.drawable.btn_big_black_bg);
//			/*
//			 * btn.setTextColor(ctx.getResources().getColor(
//			 * R.color.addview_left_textcolor));
//			 */
//			btn.setText("取消");
//			btn.setOnClickListener(new OnClickListener() {
//
//				public void onClick(View v) {
//					//jsimpl.hideContentPad();
//				}
//			});
//			menuRoot.addView(btn);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//	}

	private Button creatButton() {
		// Button btn = new Button(ctx);
		Button btn = (Button) LayoutInflater.from(ctx).inflate(
				R.layout.contentpadbutton, null);

		//btn.setTypeface(typebold);
		/*
		 * btn.setTextColor(ctx.getResources().getColor(
		 * R.color.addview_left_textcolor)); btn.setTextSize(17f);
		 */
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER;
		layoutParams.topMargin = 10;
		layoutParams.bottomMargin = 10;
		btn.setLayoutParams(layoutParams);
		btn.setBackgroundResource(R.drawable.btn_big_bg);
		return btn;
	}
}