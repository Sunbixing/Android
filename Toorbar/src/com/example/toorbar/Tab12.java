package com.example.toorbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.adapter.StaticPagerAdapter;
import com.example.domen.Good;
import com.example.service.SearchService;
import com.example.view.OnItemClickListener;
import com.example.view.RollPagerView;
import com.example.view.ScrollBanner;

@SuppressLint("InflateParams")
public class Tab12 extends View implements OnClickListener {
	private static String[] mStrs = { "���������ƺ�֮ˮ����������������������",
			"�������������������׷���������˿ĺ��ѩ", "���������뾡����Īʹ���׿ն���", "�����Ҳı����ã�ǧ��ɢ��������",
			"������ţ��Ϊ�֣�����һ�����ٱ�", "᯷��ӣ��������������ƣ���Īͣ" };
	private static int[] icon = { R.drawable.tou1, R.drawable.tou2,
			R.drawable.tou3, R.drawable.tou4, R.drawable.tou5, R.drawable.tou6,
			R.drawable.tou7, R.drawable.tou8, R.drawable.lb, R.drawable.df,
			R.drawable.hy, R.drawable.lx };
	private static List<Good> gooddata;
	private static int[][] rss = {
			{ R.drawable.i11, R.drawable.i12, R.drawable.i13, R.drawable.i14 },
			{ R.drawable.i21, R.drawable.i22, R.drawable.i23, R.drawable.i24 },
			{ R.drawable.i31, R.drawable.i32, R.drawable.i33, R.drawable.i34 },
			{ R.drawable.i41, R.drawable.i42, R.drawable.i43, R.drawable.i44 } };
	private static int[] rs = { R.drawable.i1, R.drawable.i2, R.drawable.i3,
			R.drawable.i4 };
	private static String[][] ss = { { "������1", "������2", "������3", "������4" },
			{ "������5", "������6", "������7", "������8" },
			{ "�羰��1", "�羰��2", "�羰��3", "�羰��4" },
			{ "������1", "������2", "������3", "������4" } };
	private static ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	private static RollPagerView mViewPager;
	private static GridView gridView;
	private static Context context;
	private static LinearLayout multiple;

	@SuppressWarnings("static-access")
	public Tab12(Context context) {
		super(context);
		this.context = context;
	}

	@SuppressWarnings("deprecation")
	public static View getView(LayoutInflater inflater) throws Exception {
		LayoutParams para;

		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();

		View view = inflater.inflate(R.layout.tab12, null);

		gooddata = SearchService.getGood();

		multiple = view.findViewById(R.id.multiple);
		for (int i = 0; i < 4; i++) {
			View child = inflater.inflate(R.layout.multiplexing, null);
			child.setId(i);
			ImageView childimg = child.findViewById(R.id.childimg);
			childimg.setBackgroundResource(rs[i]);
			childimg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					ViewGroup viewGroup = (ViewGroup) v.getParent();

					for (int j = 0; j < 4; j++) {

						if (viewGroup.getId() == j) {
							Log.e("�����", "1");
							Intent intent = new Intent();
							intent.setClassName("com.example.toorbar",
									"com.example.toorbar.Goods");

							intent.putExtra("id", gooddata.get(j).getId());
							intent.putExtra("name", gooddata.get(j).getName());
							intent.putExtra("univalent", gooddata.get(j)
									.getUnivalent());
							intent.putExtra("quantity", gooddata.get(j)
									.getQuantity());
							intent.putExtra("gooddata", (Serializable) gooddata);

							context.startActivity(intent);
						}
					}
				}
			});
			TextView child1 = child.findViewById(R.id.child1);
			child1.setText(ss[i][0]);
			child1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					ViewGroup viewGroup = (ViewGroup) v.getParent().getParent()
							.getParent();

					for (int j = 0; j < 4; j++) {

						if (viewGroup.getId() == j) {
							Log.e("�����", "2");
							Intent intent = new Intent();
							Log.e("�����", "2");
							intent.setClassName("com.example.toorbar",
									"com.example.toorbar.Goods");
							Log.e("�����", "2");
							intent.putExtra("id", gooddata.get(j + 4).getId());
							Log.e("�����", "2");
							intent.putExtra("name", gooddata.get(j + 4)
									.getName());
							Log.e("�����", "2");
							intent.putExtra("univalent", gooddata.get(j + 4)
									.getUnivalent());
							Log.e("�����", "2");
							intent.putExtra("quantity", gooddata.get(j + 4)
									.getQuantity());
							Log.e("�����", gooddata.get(j + 4).getName());
							intent.putExtra("gooddata", (Serializable) gooddata);
							Log.e("�����", gooddata.get(j + 4).getName());
							context.startActivity(intent);
						}
					}
				}
			});
			ImageView childimg1 = child.findViewById(R.id.childimg1);
			childimg1.setBackgroundResource(rss[i][0]);
			para = childimg1.getLayoutParams();
			para.width = display.getWidth() / 2;
			childimg1.setLayoutParams(para);
			childimg1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					ViewGroup viewGroup = (ViewGroup) v.getParent().getParent()
							.getParent();

					for (int j = 0; j < 4; j++) {

						if (viewGroup.getId() == j) {
							Log.e("�����", "3");
							Intent intent = new Intent();
							intent.setClassName("com.example.toorbar",
									"com.example.toorbar.Goods");
							intent.putExtra("id", gooddata.get(j + 8).getId());
							intent.putExtra("name", gooddata.get(j + 8)
									.getName());
							intent.putExtra("univalent", gooddata.get(j + 8)
									.getUnivalent());
							intent.putExtra("quantity", gooddata.get(j + 8)
									.getQuantity());
							intent.putExtra("gooddata", (Serializable) gooddata);
							context.startActivity(intent);
						}
					}
				}
			});

			TextView child3 = child.findViewById(R.id.child3);
			child3.setText(ss[i][2]);
			ImageView childimg3 = child.findViewById(R.id.childimg3);
			childimg3.setBackgroundResource(rss[i][2]);
			para = childimg3.getLayoutParams();
			para.width = display.getWidth() / 2;
			childimg3.setLayoutParams(para);

			TextView child2 = child.findViewById(R.id.child2);
			child2.setText(ss[i][1]);
			ImageView childimg2 = child.findViewById(R.id.childimg2);
			childimg2.setBackgroundResource(rss[i][1]);
			para = childimg2.getLayoutParams();
			para.width = display.getWidth() / 2;

			childimg2.setLayoutParams(para);

			TextView child4 = child.findViewById(R.id.child4);
			child4.setText(ss[i][3]);
			ImageView childimg4 = child.findViewById(R.id.childimg4);
			childimg4.setBackgroundResource(rss[i][3]);
			para = childimg4.getLayoutParams();
			para.width = display.getWidth() / 2;
			childimg4.setLayoutParams(para);

			multiple.addView(child, i);
		}
		mViewPager = view.findViewById(R.id.view_pager);
		mViewPager.setAdapter(new ImageNormalAdapter());
		mViewPager.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(int position) {
				Intent intent = new Intent();
				intent.setClassName("com.example.toorbar",
						"com.example.toorbar.Goods");
				intent.putExtra("id", gooddata.get(position).getId());
				intent.putExtra("name", gooddata.get(position).getName());
				intent.putExtra("univalent", gooddata.get(position)
						.getUnivalent());
				intent.putExtra("quantity", gooddata.get(position)
						.getQuantity());
				intent.putExtra("gooddata", (Serializable) gooddata);
				context.startActivity(intent);

			}
		});

		ScrollBanner sb_demographic;
		sb_demographic = (ScrollBanner) view.findViewById(R.id.sb_demographic);
		List<String> demographicsList = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			demographicsList.add(mStrs[i]);
		}
		sb_demographic.setList(demographicsList);
		sb_demographic.startScroll();
		gridView = view.findViewById(R.id.grid);

		for (int i = 0; i < 8; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", icon[gooddata.get(i).getId()]);
			map.put("name", gooddata.get(i).getName());
			list.add(map);
		}
		;

		SimpleAdapter adapter = new SimpleAdapter(context, list,
				R.layout.gridtab12, new String[] { "id", "name" }, new int[] {
						R.id.gridimg, R.id.griditem });
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.setClassName("com.example.toorbar",
						"com.example.toorbar.Goods");
				intent.putExtra("id", gooddata.get(arg2).getId());
				intent.putExtra("name", gooddata.get(arg2).getName());
				intent.putExtra("univalent", gooddata.get(arg2).getUnivalent());
				intent.putExtra("quantity", gooddata.get(arg2).getQuantity());
				intent.putExtra("gooddata", (Serializable) gooddata);
				context.startActivity(intent);

			}
		});
		return view;
	}

	static class ImageNormalAdapter extends StaticPagerAdapter {
		int[] imgs = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e, };

		@Override
		public View getView(ViewGroup container, int position) {
			ImageView view = new ImageView(container.getContext());
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));
			view.setImageResource(imgs[position]);
			return view;
		}

		@Override
		public int getCount() {
			return imgs.length;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
