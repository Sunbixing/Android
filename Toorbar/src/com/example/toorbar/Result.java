package com.example.toorbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends Activity {
	private static int[] icon = { R.drawable.tou1, R.drawable.tou2,
		R.drawable.tou3, R.drawable.tou4, R.drawable.tou5, R.drawable.tou6,
		R.drawable.tou7, R.drawable.tou8, R.drawable.lb, R.drawable.df,
		R.drawable.hy, R.drawable.lx };
	private TextView resulttitle,resulttit,resultcontent,resultcomments;
	private ImageView resultimg;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		resultimg=findViewById(R.id.resultimg);
		resulttitle=findViewById(R.id.resulttitle);
		resulttit=findViewById(R.id.resulttit);
		resultcontent=findViewById(R.id.resultcontent);
		resultcomments=findViewById(R.id.resultcomment);
		Intent intent=getIntent();
		resulttitle.setText(intent.getStringExtra("title"));
		resulttit.setText(intent.getStringExtra("title"));
		resultcontent.setText(intent.getStringExtra("content"));
		resultcomments.setText(intent.getStringExtra("comments"));
		Toast.makeText(getApplicationContext(),intent.getStringExtra("comments"), Toast.LENGTH_SHORT).show();
		resultimg.setImageResource(icon [intent.getIntExtra("id", R.drawable.ic_launcher)]);
		DemoApplication.getInstance().addActivity(this);}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub

	}

}
