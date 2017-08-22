package com.example.toorbar;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.SearchAdapter;
import com.example.domen.Result;
import com.example.service.RegisterService;
import com.example.service.SearchService;
import com.example.toorbar.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

@SuppressLint("InflateParams")
public class Tab1 extends Fragment implements OnClickListener {

	private ViewPager viewPager;
	private PagerAdapter adapter;
	private View view;
	private List<View> views = new ArrayList<View>();
	private TextView txthome;
	private TextView txtmenu;
	private TextView txtset;
	private TextView txtsearch;
	private List<Result> resultdata;
	private ListView listhome;
	private SearchAdapter searchAdapter;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tab1, container, false);
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
		return view;

	}

	@SuppressWarnings("static-access")
	private void initView() throws Exception {
		resultdata = SearchService.getLast();
		viewPager = view.findViewById(R.id.pager);
		txthome = view.findViewById(R.id.txt_home);
		txtmenu = view.findViewById(R.id.txt_menu);
		txtset = view.findViewById(R.id.txt_set);
		txtsearch = view.findViewById(R.id.txt_search);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View tab1 = inflater.inflate(R.layout.tab11, null);
		View tab2 = new Tab12(getContext()).getView(inflater);
		View tab3 = new Tab13(getContext()).getView(inflater);
		View tab4 = new Tab14(getContext()).getView(inflater);
		listhome = tab1.findViewById(R.id.listhome);
		searchAdapter = new SearchAdapter(getContext(), resultdata,
				R.layout.searchitem);
		listhome.setAdapter(searchAdapter);
		listhome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.setClassName("com.example.toorbar",
						"com.example.toorbar.Result");
				intent.putExtra("id", arg2);
				arg1.setId(arg2);
				intent.putExtra("title", resultdata.get(arg2).getTitle());
				intent.putExtra("content", resultdata.get(arg2).getContent());
				intent.putExtra("comments", resultdata.get(arg2).getComments());
				startActivity(intent);
			}
		});
		this.registerForContextMenu(listhome);
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

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = new MenuInflater(getContext());
		inflater.inflate(R.menu.detele, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Log.e("Ëï±ÏÐË", String.valueOf(menuInfo.position + 1));
		switch (item.getItemId()) {
		case R.id.deletemenu:
			boolean it = RegisterService.delete(String
					.valueOf(menuInfo.position + 1));
			if (it) {
				try {
					resultdata = SearchService.getLast();
					searchAdapter = new SearchAdapter(getContext(), resultdata,
							R.layout.searchitem);
					listhome.setAdapter(searchAdapter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getContext(), R.string.app_back,
						Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.updatemenu:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();

			View viewold = info.targetView;
			TextView titleview = viewold.findViewById(R.id.searchtitle);
			TextView contentview = viewold.findViewById(R.id.searchcontent);
			TextView commentview = viewold.findViewById(R.id.searchcomment);
			Log.e("Ëï±ÏÐË", String.valueOf(info.position + 1));
			Log.e("Ëï±ÏÐË", titleview.getText().toString());
			Log.e("Ëï±ÏÐË", contentview.getText().toString());
			Log.e("Ëï±ÏÐË", commentview.getText().toString());
			Intent intent = new Intent();
			intent.setClassName("com.example.toorbar",
					"com.example.toorbar.Update");

			intent.putExtra("id", info.position + 1);
			intent.putExtra("title", titleview.getText().toString());
			intent.putExtra("content", contentview.getText().toString());
			intent.putExtra("comments", commentview.getText().toString());
			startActivity(intent);

			Toast.makeText(getContext(), R.string.app_back, Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void initEvent() {
		viewPager.setCurrentItem(1);
		txtmenu.setBackgroundColor(ContextCompat.getColor(getContext(),
				R.color.dark));
		txthome.setOnClickListener(this);
		txtmenu.setOnClickListener(this);
		txtset.setOnClickListener(this);
		txtsearch.setOnClickListener(this);
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				int item = viewPager.getCurrentItem();
				resetColor();
				switch (item) {
				case 0:
					txthome.setBackgroundColor(ContextCompat.getColor(
							getContext(), R.color.dark));
					break;
				case 1:
					txtmenu.setBackgroundColor(ContextCompat.getColor(
							getContext(), R.color.dark));
					break;

				case 2:
					txtset.setBackgroundColor(ContextCompat.getColor(
							getContext(), R.color.dark));
					break;

				case 3:
					txtsearch.setBackgroundColor(ContextCompat.getColor(
							getContext(), R.color.dark));
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
		txthome.setBackgroundColor(ContextCompat.getColor(getContext(),
				R.color.black));
		txtmenu.setBackgroundColor(ContextCompat.getColor(getContext(),
				R.color.black));
		txtset.setBackgroundColor(ContextCompat.getColor(getContext(),
				R.color.black));
		txtsearch.setBackgroundColor(ContextCompat.getColor(getContext(),
				R.color.black));
	}

	@Override
	public void onClick(View v) {
		resetColor();
		switch (v.getId()) {
		case R.id.txt_home:
			viewPager.setCurrentItem(0);
			txthome.setBackgroundColor(ContextCompat.getColor(getContext(),
					R.color.dark));
			break;
		case R.id.txt_menu:
			viewPager.setCurrentItem(1);
			txtmenu.setBackgroundColor(ContextCompat.getColor(getContext(),
					R.color.dark));
			break;
		case R.id.txt_set:
			viewPager.setCurrentItem(2);
			txtset.setBackgroundColor(ContextCompat.getColor(getContext(),
					R.color.dark));
			break;
		case R.id.txt_search:
			viewPager.setCurrentItem(3);
			txtsearch.setBackgroundColor(ContextCompat.getColor(getContext(),
					R.color.dark));
			break;
		}

	}

}
