package com.example.toorbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class First extends Activity {
	static final String TAG_EXIT = "exit";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		//开了一个线程，2s跳转
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(First.this, Login.class));
                finish();
            }
        }, 2000);
		DemoApplication.getInstance().addActivity(this);}
	 @Override
	    protected void onNewIntent(Intent intent) {
	        super.onNewIntent(intent);
	        if (intent != null) {
	            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
	            if (isExit) {
	                this.finish();
	            }
	        }
	    }
	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub

	}

}
