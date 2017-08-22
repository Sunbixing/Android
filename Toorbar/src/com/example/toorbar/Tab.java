package com.example.toorbar;

import com.example.domen.Good;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InflateParams") public class Tab extends View {
	private Good good;
	private Context context;
	private static int[] icon = { R.drawable.tou1, R.drawable.tou2,
			R.drawable.tou3, R.drawable.tou4, R.drawable.tou5, R.drawable.tou6,
			R.drawable.tou7, R.drawable.tou8, R.drawable.lb, R.drawable.df,
			R.drawable.hy, R.drawable.lx };
	private TextView goodtitle, goodname, goodunivalent, goodquantity;
	private ImageView goodimg;

	public Tab(Context context,Good good) {
		super(context);
		this.good=good;
		this.context = context;
	}

	public View getView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.good, null);
		goodimg = view.findViewById(R.id.goodimg);
		goodtitle = view.findViewById(R.id.goodtitle);
		goodname = view.findViewById(R.id.goodname);
		goodunivalent = view.findViewById(R.id.goodunivalent);
		goodquantity = view.findViewById(R.id.goodquantity);

		goodtitle.setText(good.getName());
		goodname.setText(good.getName());
		goodunivalent.setText(good.getUnivalent() + "ิช");
		goodquantity.setText(good.getQuantity() + "ผþ");
		Toast.makeText(context, good.getName() + "ผþ", Toast.LENGTH_SHORT)
				.show();
		goodimg.setImageResource(icon[good.getId()]);

		return view;
	}

}
