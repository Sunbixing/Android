package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {
	protected Context mcontext;
	protected List<T> mdata;
	protected int mid;
	
	public CommonAdapter(Context context, List<T> data, int id) {
		mcontext = context;
		mdata = data;
		mid = id;
	}

	@Override
	public CharSequence[] getAutofillOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mdata.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=ViewHolder.getHolder(mcontext, convertView, mid, parent, position);
		convert(holder, position);
		return holder.getView();
	}
public abstract void convert(ViewHolder holder,int pos);
}
