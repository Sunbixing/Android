package com.example.adapter;

import java.util.List;

import android.content.Context;

import com.example.domen.Result;
import com.example.toorbar.R;

public class SearchAdapter extends CommonAdapter<Result> {
	private static int[] icon = { R.drawable.tou1, R.drawable.tou2,
			R.drawable.tou3, R.drawable.tou4, R.drawable.tou5, R.drawable.tou6,
			R.drawable.tou7, R.drawable.tou8, R.drawable.lb, R.drawable.df,
			R.drawable.hy, R.drawable.lx ,R.drawable.lx};

	public SearchAdapter(Context context, List<Result> data, int id) {
		super(context, data, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHolder holder, int pos) {
		holder.setImage(R.id.searchimg, icon[pos])
				.setText(R.id.searchtitle, mdata.get(pos).getTitle())
				.setText(R.id.searchcontent, mdata.get(pos).getContent())
				.setText(R.id.searchcomment, mdata.get(pos).getComments());

	}

}
