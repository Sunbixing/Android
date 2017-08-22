package com.example.toorbar;

import java.util.ArrayList;
import java.util.List;

import com.example.domen.Good;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class Goods extends Activity implements OnClickListener {

	private ViewPager viewPager;
	private PagerAdapter adapter;
	private List<View> views = new ArrayList<View>();
	private TextView name1;
	private TextView name2;
	private TextView name3;
	private TextView name4;
	private static List<Good> gooddata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		try {
			initView();
			initEvent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DemoApplication.getInstance().addActivity(this);
	}

	@SuppressWarnings("unchecked")
	private void initView() throws Exception {
		viewPager = findViewById(R.id.pagername);
		name1 = findViewById(R.id.name1);
		name2 = findViewById(R.id.name2);
		name3 = findViewById(R.id.name3);
		name4 = findViewById(R.id.name4);

		Intent intent = getIntent();
		int id = intent.getIntExtra("id", 0);
		gooddata = (List<Good>) intent.getSerializableExtra("gooddata");
		Log.e("Ëï±ÏÐË", gooddata.get(0).getName());
		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		Log.e("Ëï±ÏÐË", gooddata.get(0).getName() + id);
		View tab1 = new Tab(getApplicationContext(), gooddata.get(id - 1))
				.getView(inflater);
		View tab2 = new Tab(getApplicationContext(), gooddata.get(id))
				.getView(inflater);
		View tab3 = new Tab(getApplicationContext(), gooddata.get(id + 1))
				.getView(inflater);
		View tab4 = new Tab(getApplicationContext(), gooddata.get(id + 2))
				.getView(inflater);

		views.add(tab1);
		views.add(tab2);
		views.add(tab3);
		views.add(tab4);
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

	private void initEvent() {
		viewPager.setCurrentItem(0);
		name1.setBackgroundColor(ContextCompat.getColor(
				getApplicationContext(), R.color.dark));
		name1.setOnClickListener(this);
		name2.setOnClickListener(this);
		name3.setOnClickListener(this);
		name4.setOnClickListener(this);
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				int item = viewPager.getCurrentItem();
				resetColor();
				switch (item) {
				case 0:
					name1.setBackgroundColor(ContextCompat.getColor(
							getApplicationContext(), R.color.dark));
					break;
				case 1:
					name2.setBackgroundColor(ContextCompat.getColor(
							getApplicationContext(), R.color.dark));
					break;

				case 2:
					name3.setBackgroundColor(ContextCompat.getColor(
							getApplicationContext(), R.color.dark));
					break;

				case 3:
					name4.setBackgroundColor(ContextCompat.getColor(
							getApplicationContext(), R.color.dark));
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

	private void resetColor() {
		name1.setBackgroundColor(ContextCompat.getColor(
				getApplicationContext(), R.color.black));
		name2.setBackgroundColor(ContextCompat.getColor(
				getApplicationContext(), R.color.black));
		name3.setBackgroundColor(ContextCompat.getColor(
				getApplicationContext(), R.color.black));
		name4.setBackgroundColor(ContextCompat.getColor(
				getApplicationContext(), R.color.black));
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {

		resetColor();
		switch (v.getId()) {
		case R.id.name1:
			viewPager.setCurrentItem(0);
			name1.setBackgroundColor(ContextCompat.getColor(
					getApplicationContext(), R.color.dark));
			break;
		case R.id.name2:
			viewPager.setCurrentItem(1);
			name2.setBackgroundColor(ContextCompat.getColor(
					getApplicationContext(), R.color.dark));
			break;
		case R.id.name3:
			viewPager.setCurrentItem(2);
			name3.setBackgroundColor(ContextCompat.getColor(
					getApplicationContext(), R.color.dark));
			break;
		case R.id.name4:
			viewPager.setCurrentItem(3);
			name4.setBackgroundColor(ContextCompat.getColor(
					getApplicationContext(), R.color.dark));
			break;
		}

	}

}
