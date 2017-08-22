package com.example.toorbar;

import com.example.service.RegisterService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {
	private EditText name, pwd, pwdtwo;
	private Button register;
	private TextView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		name = findViewById(R.id.register_edtId);
		pwd = findViewById(R.id.register_edtPwd);
		pwdtwo = findViewById(R.id.register_edtPwdtwo);
		register = findViewById(R.id.register_btnLogin);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		back = findViewById(R.id.back);
		register.setOnClickListener(this);
		
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Register.this, Login.class);
				startActivity(intent);
			}
		});
		name.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					if (name.getText().toString().trim().length() < 4) {
						Toast.makeText(getApplicationContext(),
								R.string.app_back, Toast.LENGTH_SHORT).show();
					}
				}

			}
		});
		pwd.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					if (pwd.getText().toString().trim().length() < 6) {
						Toast.makeText(getApplicationContext(),
								R.string.app_back, Toast.LENGTH_SHORT).show();
					}
				}

			}
		});
		pwdtwo.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					if (!pwdtwo.getText().toString().trim()
							.equals(pwd.getText().toString().trim())) {
						Toast.makeText(getApplicationContext(),
								R.string.app_back, Toast.LENGTH_SHORT).show();
					}
				}

			}
		});
		DemoApplication.getInstance().addActivity(this);}

	protected boolean check() {
		if (name.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), R.string.app_back,
					Toast.LENGTH_SHORT).show();
		} else if (pwd.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), R.string.app_back,
					Toast.LENGTH_SHORT).show();
		} else if (!pwdtwo.getText().toString().trim()
				.equals(pwd.getText().toString().trim())) {
			Toast.makeText(getApplicationContext(), R.string.app_back,
					Toast.LENGTH_SHORT).show();
		} else {
			return true;
		}
		return false;
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		if (v == register) {
			String nameString = name.getText().toString().trim();
			String pwdString = pwd.getText().toString().trim();
			if (!check()) {
				return;
			}
			boolean result = RegisterService.save(nameString, pwdString);
			if (result) {
				Toast.makeText(getApplicationContext(), R.string.app_back,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Register.this, Login.class);
				intent.putExtra("name", nameString);
				intent.putExtra("pwd", pwdString);
				intent.putExtra("from", "register");
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(), R.string.app_back,
						Toast.LENGTH_SHORT).show();
			}

		}

	}

}
