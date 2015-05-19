package com.yitong.android.activity;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;

import com.yitong.android.fragment.YTBaseFragment;
import com.yitong.basic.R;

public abstract class YTFragmentActivity extends FragmentActivity {
	
	private List<YTBaseFragment> notifyBackPressList = new ArrayList<YTBaseFragment>();
		
	@Override
	protected void onCreate(Bundle savedactivityState) {
		super.onCreate(savedactivityState);
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (getContentLayout() != 0) {
			setContentView(getContentLayout());
		}

		initView();
		initData();
		initAction();
	}
	
	protected abstract int getContentLayout();
	protected abstract void initView();
	protected abstract void initData();
	protected abstract void initAction();

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	public void changeFragment(Fragment fragment, boolean backStackFlag) {
		changeFragment(R.id.fragments_contain, fragment, backStackFlag);
	}
	
	public void changeFragment(int containId, Fragment fragment, boolean backStackFlag) {
		notifyBackPressList.clear();
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(containId, fragment);
		if (backStackFlag) {
			ft.addToBackStack(null);
		}
		ft.commit();		
	}

	public void popBack() {
		if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
			getSupportFragmentManager().popBackStack();
		}
	}
	
	public void clearPopBackStack() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (notifyBackPressList != null) {
				boolean isHook = false;
				for (int i=0; i<notifyBackPressList.size(); i++) {
					isHook = notifyBackPressList.get(i).onBackPressed();
				}
				if (isHook) {
					return true;
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void addNotifyBackPressList(YTBaseFragment fragment) {
		notifyBackPressList.clear();
		notifyBackPressList.add(fragment);
	}
}
