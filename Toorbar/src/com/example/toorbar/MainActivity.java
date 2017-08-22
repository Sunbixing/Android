package com.example.toorbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends FragmentActivity implements OnClickListener,
		OnMenuItemClickListener {
	
	private Toolbar toolbar;
	private LinearLayout home;
	private LinearLayout menu;
	private LinearLayout set;
	private LinearLayout search;
	private ImageButton homebutton;
	private ImageButton menubutton;
	private ImageButton setbutton;
	private ImageButton searchbutton;
	private Fragment tab1;
	private Fragment tab2;
	private Fragment tab3;
	private Fragment tab4;
	private PopupWindow pop;
	@SuppressWarnings("unused")
	private boolean flag;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Menu cm = new Menu(this);
		pop = cm.getMenu(touchListener, keyListener);
		setContentView(R.layout.activity_main);

		toolbar = findViewById(R.id.toolbar);
		toolbar.inflateMenu(R.menu.main);
		toolbar.setOnMenuItemClickListener(this);
		initView();
		initEvent();
		DemoApplication.getInstance().addActivity(this);
	}

	private void initView() {

		home = findViewById(R.id.menu_home);
		menu = findViewById(R.id.menu_menu);
		set = findViewById(R.id.menu_set);
		search = findViewById(R.id.menu_search);
		homebutton = findViewById(R.id.btn_home);
		menubutton = findViewById(R.id.btn_menu);
		setbutton = findViewById(R.id.btn_set);
		searchbutton = findViewById(R.id.btn_search);
		setSelect(0);

	}

	private void initEvent() {
		home.setOnClickListener(this);
		menu.setOnClickListener(this);
		set.setOnClickListener(this);
		search.setOnClickListener(this);

	}

	private void setSelect(int i) {
		//管理器
		FragmentManager manager = getSupportFragmentManager();
		//事务
		FragmentTransaction transaction = manager.beginTransaction();
		
		hideFragment(transaction);
		switch (i) {
		case 0:
			if (tab1 == null) {
				tab1 = new Tab1();
				transaction.add(R.id.frame, tab1);
			} else {
				transaction.show(tab1);
			}
			homebutton.setImageResource(R.drawable.tab_weixin_pressed);
			break;
		case 1:
			if (tab2 == null) {
				tab2 = new Tab2(this);
				transaction.add(R.id.frame, tab2);
			} else {
				transaction.show(tab2);
			}
			menubutton.setImageResource(R.drawable.tab_address_pressed);
			break;
		case 2:
			if (tab3 == null) {
				tab3 = new Tab3(this);
				transaction.add(R.id.frame, tab3);
			} else {
				transaction.show(tab3);
			}
			setbutton.setImageResource(R.drawable.tab_settings_pressed);
			break;
		case 3:
			if (tab4 == null) {
				tab4 = new Tab4(this);
				transaction.add(R.id.frame, tab4);
			} else {
				transaction.show(tab4);
			}
			searchbutton.setImageResource(R.drawable.tab_find_frd_pressed);
			break;
		}
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		if (tab1 != null) {
			transaction.hide(tab1);
		}
		if (tab2 != null) {
			transaction.hide(tab2);
		}
		if (tab3 != null) {
			transaction.hide(tab3);
		}
		if (tab4 != null) {
			transaction.hide(tab4);
		}

	}

	private void resetImg() {
		homebutton.setImageResource(R.drawable.tab_weixin_normal);
		menubutton.setImageResource(R.drawable.tab_address_normal);
		setbutton.setImageResource(R.drawable.tab_settings_normal);
		searchbutton.setImageResource(R.drawable.tab_find_frd_normal);

	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		resetImg();
		switch (v.getId()) {
		case R.id.menu_home:
			setSelect(0);
			break;
		case R.id.menu_menu:
			setSelect(1);
			break;
		case R.id.menu_set:
			setSelect(2);
			break;
		case R.id.menu_search:
			setSelect(3);
			break;
		}
	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		// TODO Auto-generated method stub
		super.onAttachFragment(fragment);
		if (tab1 == null && fragment instanceof Tab1) {
			tab1 = (Tab1) fragment;
		} else if (tab2 == null && fragment instanceof Tab2) {
			tab2 = (Tab2) fragment;
		} else if (tab3 == null && fragment instanceof Tab3) {
			tab3 = (Tab3) fragment;
		} else if (tab4 == null && fragment instanceof Tab4) {
			tab4 = (Tab4) fragment;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (pop.isShowing()) {
				pop.dismiss();
			} else {
				pop.showAtLocation(findViewById(R.id.linear), Gravity.BOTTOM,
						0, 0);

			}
		}

		return super.onKeyDown(keyCode, event);
	}

	private OnTouchListener touchListener = new OnTouchListener() {

		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
				pop.dismiss();
				return true;
			}
			return false;
		}
	};
	private OnKeyListener keyListener = new OnKeyListener() {

		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& keyCode == KeyEvent.KEYCODE_MENU) {
				pop.dismiss();
				return true;
			}
			return false;
		}
	};

	@Override
	public boolean onMenuItemClick(MenuItem arg0) {
		if (arg0.getItemId() == R.id.search) {
			Intent intent = new Intent(MainActivity.this, SearchList.class);
			startActivity(intent);
		} else if (arg0.getItemId() == R.id.set) {
			Toast.makeText(getApplicationContext(), R.string.set,
					Toast.LENGTH_SHORT).show();
		}
		return false;
	}

}