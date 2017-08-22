package com.example.toorbar;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.domen.Person;
import com.example.service.LoginService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
	private EditText name, pwd;
	private Button login;
	private TextView register, tui;
	List<Person> persons;
	LoginService loginService;
	private CheckBox remmber, auto;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		name = (EditText) findViewById(R.id.login_edtId);
		pwd = (EditText) findViewById(R.id.login_edtPwd);
		remmber = findViewById(R.id.remmber);
		auto = findViewById(R.id.auto);
		tui = findViewById(R.id.tui);
		tui.setOnClickListener(this);
		sp = this.getPreferences(Context.MODE_PRIVATE);
		loginService = new LoginService(this);
		Map<String, String> use = loginService.Preferences();

		Intent intent = getIntent();
		if (intent != null) {
			String from = intent.getStringExtra("from");
			if (from != null) {
				if (from.equals("register")) {
					String na = intent.getStringExtra("name");
					String pw = intent.getStringExtra("pwd");
					name.setText(na);
					pwd.setText(pw);
				} else if (from.equals("menu")) {
					name.setText(use.get("name"));
					pwd.setText(use.get("pwd"));
					remmber.setChecked(true);
					sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
				}
			} else {
				name.setText(use.get("name"));
				if (sp.getBoolean("ISCHECK", false)) {
					remmber.setChecked(true);
					pwd.setText(use.get("pwd"));
					if (sp.getBoolean("AUTO_ISCHECK", false)) {
						auto.setChecked(true);
						onClick(login);
					}
				}

			}

		}
		login = (Button) findViewById(R.id.login_btnLogin);
		register = findViewById(R.id.register);

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login.this, Register.class);
				startActivity(intent);
			}
		});
		login.setOnClickListener(this);
		name.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					if (name.getText().toString().trim().length() < 4) {
						Toast.makeText(getApplicationContext(),
								R.string.namemin, Toast.LENGTH_SHORT).show();
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
								R.string.pwdmin, Toast.LENGTH_SHORT).show();
					}
				}

			}
		});
		remmber.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (remmber.isChecked()) {
					sp.edit().putBoolean("ISCHECK", true).commit();
				} else {
					sp.edit().putBoolean("ISCHECK", false).commit();
				}

			}
		});
		auto.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (auto.isChecked()) {
					sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
				} else {
					sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
				}

			}
		});
		DemoApplication.getInstance().addActivity(this);}

	protected boolean check() {
		if (name.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), R.string.namenull,
					Toast.LENGTH_SHORT).show();
		} else if (pwd.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), R.string.pwdnull,
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
		if (v == login) {
			String nameString = name.getText().toString().trim();
			String namePwd = pwd.getText().toString().trim();
			if (!check()) {
				return;
			}
			loginService.save(nameString, namePwd);
			try {
				persons = LoginService.getLast();
				boolean a = false;
				for (Person person : persons) {
					if (nameString.equals(person.getName())) {
						a = true;
						if (namePwd.equals(person.getPwd())) {
							Intent intent = new Intent(Login.this,
									Welcome.class);
							startActivity(intent);
						} else {
							Toast.makeText(getApplicationContext(),
									R.string.pwdfail, Toast.LENGTH_SHORT)
									.show();
						}
					}
				}
				if (!a) {
					Toast.makeText(getApplicationContext(), R.string.namenon,
							Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v == tui) {
			DemoApplication.getInstance().exit();
		}

	}

}
