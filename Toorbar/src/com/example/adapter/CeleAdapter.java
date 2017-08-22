package com.example.adapter;

import java.util.List;

import android.content.Context;

import com.example.domen.Cele;
import com.example.toorbar.R;

public class CeleAdapter extends CommonAdapter<Cele> {
	private static int[] icon = { R.drawable.kz, R.drawable.lc, R.drawable.szs,
			R.drawable.mzd, R.drawable.dxp, R.drawable.lsm, R.drawable.jjs,
			R.drawable.wzt, R.drawable.lb, R.drawable.df, R.drawable.hy,
			R.drawable.lx,R.drawable.lx };

	public CeleAdapter(Context context, List<Cele> data, int id) {
		super(context, data, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHolder holder, int pos) {
		holder.setImage(R.id.searchimg, icon[mdata.get(pos).getId()])
				.setText(
						R.id.searchtitle,
						mdata.get(pos).getId() + "."
								+ mdata.get(pos).getTitle())
				.setText(R.id.searchcontent, mdata.get(pos).getContent())
				.setText(R.id.searchcomment, mdata.get(pos).getComments());

	}

}
