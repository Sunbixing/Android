package com.example.toorbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class Tab3 extends Fragment {

	private GridView gview;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	private Context context;
	// 图片封装为一个数组
	private int[] icon = { R.drawable.kz, R.drawable.lc, R.drawable.szs,
			R.drawable.mzd, R.drawable.dxp, R.drawable.lsm, R.drawable.jjs,
			R.drawable.wzt, R.drawable.lb, R.drawable.df, R.drawable.hy,
			R.drawable.lx };
	private String[] iconName = { "孔子", "刘彻", "孙中山", "毛泽东", "邓小平", "李世民",
			"蒋介石", "武则天", "李白", "杜甫", "韩愈", "鲁迅" };
	private String[] urls = {
			"http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv",
			"http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv",
			"http://www.1905.com/vod/play/335605.shtml?__hz=55a7cf9c71f1c9c4&ref=baidu1905com",
			"毛泽东", "邓小平", "李世民", "蒋介石", "武则天", "李白", "杜甫", "韩愈", "鲁迅" };

	public Tab3(Context context) {

		this.context = context;
	}

	public List<Map<String, Object>> getData() {
		// cion和iconName的长度是相同的，这里任选其一都可以
		for (int i = 0; i < icon.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", icon[i]);
			map.put("text", iconName[i]);
			data_list.add(map);
		}

		return data_list;
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab3, container, false);

		gview = view.findViewById(R.id.videotab);

		// 新建List
		data_list = new ArrayList<Map<String, Object>>();
		// 获取数据
		getData();
		// 新建适配器
		String[] from = { "image", "text" };
		int[] to = { R.id.videoimg, R.id.videoitem };
		sim_adapter = new SimpleAdapter(context, data_list, R.layout.videotab,
				from, to);
		// 配置适配器
		gview.setAdapter(sim_adapter);
		gview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				switch (arg2) {
				case 0:

					intent.setClassName("com.example.toorbar",
							"com.example.toorbar.PLVideoTextureActivity");
					intent.putExtra("url", urls[0]);
					startActivity(intent);
					break;

				case 1:

					intent.setClassName("com.example.toorbar",
							"com.example.toorbar.PLVideoTextureActivity");
					intent.putExtra("url", urls[1]);
					startActivity(intent);
					break;
				case 2:

					intent.setClassName("com.example.toorbar",
							"com.example.toorbar.PLVideoTextureActivity");
					intent.putExtra("url", urls[2]);
					startActivity(intent);
					break;
				}

			}
		});
		return view;

		/*
		 * ImageView sta = view.findViewById(R.id.stat);
		 * sta.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * Intent intent = new Intent();
		 * intent.setClassName("com.example.toorbar",
		 * "com.example.toorbar.PLVideoTextureActivity"); startActivity(intent);
		 * 
		 * } });
		 */

	}

}
