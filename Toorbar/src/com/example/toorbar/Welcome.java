package com.example.toorbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		DemoApplication.getInstance().addActivity(this); 
		 new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                startActivity(new Intent(Welcome.this, MainActivity.class));
	                finish();
	            }
	        }, 2000);
		 
		
		 }
		
	
	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub

	}

}
