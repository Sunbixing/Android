package com.example.toorbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.adapter.CeleAdapter;
import com.example.domen.Cele;
import com.example.service.SearchService;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressLint("InflateParams")
public class Tab14 extends View {
	private static GridView gview;
	private static List<Map<String, Object>> data_list;
	private static SimpleAdapter sim_adapter;
	private static Context context;
	private static ListView mylist;
	private static CeleAdapter searchAdapter;
	private static List<Cele> resultdata;
	// ͼƬ��װΪһ������
	private static int[] icon = { R.drawable.kz, R.drawable.lc, R.drawable.szs,
			R.drawable.mzd, R.drawable.dxp, R.drawable.lsm, R.drawable.jjs,
			R.drawable.wzt, R.drawable.lb, R.drawable.df, R.drawable.hy,
			R.drawable.lx };
	private static String[] iconName = { "����", "����", "����ɽ", "ë��", "��Сƽ",
			"������", "����ʯ", "������", "���", "�Ÿ�", "����", "³Ѹ" };

	public Tab14(Context context) {
		super(context);
		Tab14.context = context;
	}

	public static View getView(LayoutInflater inflater) throws Exception {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		View view = inflater.inflate(R.layout.tab14, null);
		gview = view.findViewById(R.id.gridtab);
		mylist = view.findViewById(R.id.listgrid);
		resultdata = SearchService.getCele();
		searchAdapter = new CeleAdapter(context, resultdata,
				R.layout.searchitem);
		mylist.setAdapter(searchAdapter);

		// �½�List
		data_list = new ArrayList<Map<String, Object>>();
		// ��ȡ����
		getData();
		// �½�������
		String[] from = { "image", "text" };
		int[] to = { R.id.gridimg, R.id.griditem };
		sim_adapter = new SimpleAdapter(context, data_list, R.layout.gridtab12,
				from, to);
		// ����������
		gview.setAdapter(sim_adapter);

		return view;
	}

	public static List<Map<String, Object>> getData() {
		// cion��iconName�ĳ�������ͬ�ģ�������ѡ��һ������
		for (int i = 0; i < icon.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", icon[i]);
			map.put("text", iconName[i]);
			data_list.add(map);
		}

		return data_list;
	}
}
