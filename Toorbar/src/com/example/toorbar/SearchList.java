package com.example.toorbar;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.SearchAdapter;
import com.example.domen.Result;
import com.example.service.SearchService;
import com.example.view.SearchView;
import com.example.view.SearchView.SearchViewListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchList extends Activity implements SearchViewListener {
	private ListView listView;
	private SearchView searchView;
	private ArrayAdapter<String> hintAdapter;
	private ArrayAdapter<String> autoAdapter;
	private SearchAdapter resultAdapter;
	private List<Result> data;
	private List<String> hintdata;
	private List<String> autodata;
	private List<Result> results;
	private static int HINT_SIZE = 4;
	private static int hintSize = HINT_SIZE;

	public static void sethintSize(int hintSize) {
		SearchList.hintSize = hintSize;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchlist);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		try {
			initData();
			initViews();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DemoApplication.getInstance().addActivity(this);}

	private void initViews() {
		listView = findViewById(R.id.listView);
		searchView = findViewById(R.id.searchView);
		searchView.setSearchViewListener(this);
		searchView.sethintadapter(hintAdapter);
		searchView.setautoadapter(autoAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.setClassName("com.example.toorbar", "com.example.toorbar.Result");
				intent.putExtra("id", results.get(arg2).getId());
				intent.putExtra("title", results.get(arg2).getTitle());
				intent.putExtra("content", results.get(arg2).getContent());
				intent.putExtra("comments", results.get(arg2).getComments());
				startActivity(intent);	
			}
		});

	}

	private void initData() throws Exception {
		getDbData();
		getHintData();
		getAutoData(null);
		getResultData(null);

	}

	private void getDbData() throws Exception {
		data = SearchService.getLast();
	}

	private void getHintData() {
		hintdata = new ArrayList<String>(hintSize);
		for (int i = 0; i <= hintSize; i++) {
			hintdata.add("Ëï±ÏÐË");
		}
		hintAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, hintdata);
	}

	private void getAutoData(String text) {
		if (autodata == null) {
			autodata = new ArrayList<String>(hintSize);
		} else {
			autodata.clear();
			for (int i = 0, count = 0; i < data.size() && count < hintSize; i++) {
				if (data.get(i).getTitle().contains(text.trim())) {
					autodata.add(data.get(i).getTitle());
					count++;
				}
			}
		}
		if (autoAdapter == null) {
			autoAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, autodata);
		} else {
			autoAdapter.notifyDataSetChanged();
		}
	}

	private void getResultData(String text) {
		if (results == null) {
			results = new ArrayList<Result>();
		} else {
			results.clear();
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).getTitle().contains(text.trim())) {
					results.add(data.get(i));
				}
			}
		}
		if (resultAdapter == null) {
			resultAdapter = new SearchAdapter(this, results,
					R.layout.searchitem);
		} else {
			resultAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefreshAutoComplete(String text) {
		getAutoData(text);

	}

	@Override
	public void onSearch(String text) {
		getResultData(text);
		listView.setVisibility(View.VISIBLE);
		if (listView.getAdapter() == null) {
			listView.setAdapter(resultAdapter);
		} else {
			resultAdapter.notifyDataSetChanged();
		}
	}

}
