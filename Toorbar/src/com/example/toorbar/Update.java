package com.example.toorbar;

import com.example.service.RegisterService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Update extends Activity {
	private static int[] icon = { R.drawable.tou1, R.drawable.tou2,
			R.drawable.tou3, R.drawable.tou4, R.drawable.tou5, R.drawable.tou6,
			R.drawable.tou7, R.drawable.tou8, R.drawable.lb, R.drawable.df,
			R.drawable.hy, R.drawable.lx, R.drawable.lx };
	private EditText resulttit, resultcontent, resultcomments;
	private ImageView resultimg;
	private Button updatebtn;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
		updatebtn = findViewById(R.id.updatebtn);
		resultimg = findViewById(R.id.updateimg);
		resulttit = findViewById(R.id.updatetit);
		resultcontent = findViewById(R.id.updatecontent);
		resultcomments = findViewById(R.id.updatecomment);
		final Intent intent = getIntent();
		resulttit.setText(intent.getStringExtra("title"));
		resultcontent.setText(intent.getStringExtra("content"));
		resultcomments.setText(intent.getStringExtra("comments"));
		Toast.makeText(getApplicationContext(),
				intent.getStringExtra("comments"), Toast.LENGTH_SHORT).show();
		resultimg.setImageResource(icon[intent.getIntExtra("id",
				R.drawable.ic_launcher)]);
		updatebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String i = String.valueOf(intent.getIntExtra("id",
						R.drawable.ic_launcher));
				String t = resulttit.getText().toString();
				String con = resultcontent.getText().toString();
				String com = resultcomments.getText().toString();
				
				Log.e("Ëï±ÏÐË", i);
				Log.e("Ëï±ÏÐË", t);
				Log.e("Ëï±ÏÐË", con);
				Log.e("Ëï±ÏÐË", com);
				boolean it = RegisterService.update(i, t, con, com);
				if (it) {
					Intent intent = new Intent();
					intent.setClassName("com.example.toorbar",
							"com.example.toorbar.MainActivity");
					startActivity(intent);
				}
			}
		});
		DemoApplication.getInstance().addActivity(this);}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub

	}

}
