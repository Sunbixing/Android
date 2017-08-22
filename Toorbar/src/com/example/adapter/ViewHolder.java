package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	private SparseArray<View> mviews;
	@SuppressWarnings("unused")
	private Context mcontext;
	private View mview;
	@SuppressWarnings("unused")
	private int mpos;

	public ViewHolder(Context context, int id, ViewGroup parent, int pos) {
		mview = LayoutInflater.from(context).inflate(id, parent, false);
		mviews = new SparseArray<View>();
		mpos = pos;
		mview.setTag(this);
	}

	public static ViewHolder getHolder(Context context, View view, int id,
			ViewGroup parent, int pos) {
		if (view == null) {
			return new ViewHolder(context, id, parent, pos);
		} else {
			ViewHolder holder = (ViewHolder) view.getTag();
			holder.mpos = pos;
			return holder;
		}
	}

	public View getView() {
		return mview;
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int id) {
		View view = mviews.get(id);
		if (view == null) {
			view = mview.findViewById(id);
			mviews.put(id, view);
		}
		return (T)view;
	}

	public ViewHolder setText(int id, String text) {
		TextView tv = getView(id);
		tv.setText(text);
		return this;
	}

	public ViewHolder setImage(int id, int resid) {
		ImageView iv = getView(id);
		iv.setImageResource(resid);
		return this;
	}

	public ViewHolder setBitmap(int id, Bitmap map) {
		ImageView iv = getView(id);
		iv.setImageBitmap(map);
		return this;
	}
}
