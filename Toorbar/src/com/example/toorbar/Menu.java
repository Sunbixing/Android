package com.example.toorbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class Menu extends FragmentActivity implements OnClickListener {
	private static PopupWindow pop = null;
	private final Activity activity;
	private TextView menuHome;
	private TextView menuMenu;
	private TextView menuSet;
	private TextView menuSearch;
	private ViewPager viewPager;
	private PagerAdapter adapter;
	private View view;
	private List<View> views = new ArrayList<View>();
	private GridView gridView;
	private List<String> list;

	public Menu(Activity activity) {
		this.activity = activity;
	}

	public PopupWindow getMenu(OnTouchListener touchListener,
			OnKeyListener keyListener) {
		view = activity.getLayoutInflater().inflate(R.layout.menu, null); // layout_custom_menu菜单的布局文件
		pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		// pop.setAnimationStyle(R.style.pop_anim_style);
		// pop.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.pop_menu_bg));//
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		pop.setFocusable(true);
		pop.setTouchable(true);
		view.setFocusableInTouchMode(true);
		pop.setTouchInterceptor(touchListener);
		view.setOnKeyListener(keyListener);

		pop.setOutsideTouchable(true);
		pop.setBackgroundDrawable(new ColorDrawable(
				android.graphics.Color.TRANSPARENT));
		initView();
		initEvent();
		return pop;
	}

	private void initEvent() {
		menuHome.setBackgroundColor(ContextCompat.getColor(view.getContext(),
				R.color.dark));
		menuHome.setOnClickListener(this);
		menuMenu.setOnClickListener(this);
		menuSet.setOnClickListener(this);
		menuSearch.setOnClickListener(this);
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				int item = viewPager.getCurrentItem();
				resetColor();
				switch (item) {
				case 0:
					menuHome.setBackgroundColor(ContextCompat.getColor(
							view.getContext(), R.color.dark));
					break;
				case 1:
					menuMenu.setBackgroundColor(ContextCompat.getColor(
							view.getContext(), R.color.dark));
					break;

				case 2:
					menuSet.setBackgroundColor(ContextCompat.getColor(
							view.getContext(), R.color.dark));
					break;

				case 3:
					menuSearch.setBackgroundColor(ContextCompat.getColor(
							view.getContext(), R.color.dark));
					break;

				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initView() {
		list = new ArrayList<String>();
		list.add("设置");
		list.add("关于");
		list.add("我们");
		list.add("个人");
		list.add("数据");
		list.add("退出");
		viewPager = view.findViewById(R.id.menupager);
		menuHome = view.findViewById(R.id.menuhome);
		menuMenu = view.findViewById(R.id.menumenu);
		menuSet = view.findViewById(R.id.menuset);
		menuSearch = view.findViewById(R.id.menusearch);
		LayoutInflater inflater = LayoutInflater.from(view.getContext());
		View menu1 = inflater.inflate(R.layout.menu1, null);
		View menu2 = inflater.inflate(R.layout.menu2, null);
		View menu3 = inflater.inflate(R.layout.menu3, null);
		View menu4 = inflater.inflate(R.layout.menu4, null);
		gridView = menu4.findViewById(R.id.gridview);
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 6; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", list.get(i));
			data.add(map);
		}
		SimpleAdapter gridadapter = new SimpleAdapter(activity, data,
				R.layout.griditem, new String[] { "name" },
				new int[] { R.id.griditem });
		gridView.setAdapter(gridadapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == 5) {
					pop.dismiss();
					Intent intent = new Intent();
					intent.setClassName("com.example.toorbar",
							"com.example.toorbar.Login");
					intent.putExtra("from", "menu");
					activity.startActivity(intent);
					/*
					 * 
					 */
				}

			}
		});
		views.add(menu1);
		views.add(menu2);
		views.add(menu3);
		views.add(menu4);
		adapter = new PagerAdapter() {

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = views.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(views.get(position));
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}
		};
		viewPager.setAdapter(adapter);
	}

	private void resetColor() {
		menuHome.setBackgroundColor(ContextCompat.getColor(view.getContext(),
				R.color.black));
		menuMenu.setBackgroundColor(ContextCompat.getColor(view.getContext(),
				R.color.black));
		menuSet.setBackgroundColor(ContextCompat.getColor(view.getContext(),
				R.color.black));
		menuSearch.setBackgroundColor(ContextCompat.getColor(view.getContext(),
				R.color.black));

	}

	@Override
	public void onClick(View v) {
		resetColor();
		switch (v.getId()) {
		case R.id.menuhome:
			viewPager.setCurrentItem(0);
			menuHome.setBackgroundColor(ContextCompat.getColor(
					view.getContext(), R.color.dark));
			break;
		case R.id.menumenu:
			viewPager.setCurrentItem(1);
			menuMenu.setBackgroundColor(ContextCompat.getColor(
					view.getContext(), R.color.dark));
			break;
		case R.id.menuset:
			viewPager.setCurrentItem(2);
			menuSet.setBackgroundColor(ContextCompat.getColor(
					view.getContext(), R.color.dark));
			break;
		case R.id.menusearch:
			viewPager.setCurrentItem(3);
			menuSearch.setBackgroundColor(ContextCompat.getColor(
					view.getContext(), R.color.dark));
			break;
		}
	}

}
