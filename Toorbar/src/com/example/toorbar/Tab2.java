package com.example.toorbar;

import com.mob.MobSDK;
import com.yzq.zxinglibrary.Consants;
import com.yzq.zxinglibrary.android.CaptureActivity;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class Tab2 extends Fragment implements OnClickListener {
	private EditText phone;
	private Button btn;
	private TextView resultTv;

	/* 自己随便定义请求码 */
	private final int REQUEST_CODE_SCAN = 555;
	private EditText cord;

	private TextView now;

	private Button getCord;

	private Button saveCord;

	private String iPhone;

	private String iCord;

	private int time = 60;

	private boolean flag = true;
	private Context context;

	public Tab2(Context context) {
		this.context = context;
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.tab2, container, false);
		btn = view.findViewById(R.id.btn);
		btn.setOnClickListener(this);

		resultTv = view.findViewById(R.id.resultTv);
		phone = view.findViewById(R.id.phone);

		cord = view.findViewById(R.id.cord);

		now = view.findViewById(R.id.now);

		getCord = view.findViewById(R.id.getcord);

		saveCord = view.findViewById(R.id.savecord);

		getCord.setOnClickListener(this);

		saveCord.setOnClickListener(this);
		MobSDK.init(context, "202c14d63b995",
				"2cb9f5007267475d41478b146f16f66c");
		EventHandler eh = new EventHandler() {

			@Override
			public void afterEvent(int event, int result, Object data) {

				Message msg = new Message();

				msg.arg1 = event;

				msg.arg2 = result;

				msg.obj = data;

				handler.sendMessage(msg);

			}

		};

		SMSSDK.registerEventHandler(eh);

		// TODO Auto-generated method stub
		return view;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.getcord:

			if (!TextUtils.isEmpty(phone.getText().toString().trim())) {

				if (phone.getText().toString().trim().length() == 11) {

					iPhone = phone.getText().toString().trim();

					SMSSDK.getVerificationCode("86", iPhone);

					cord.requestFocus();

					getCord.setVisibility(View.GONE);

				} else {

					Toast.makeText(context, "请输入完整电话号码", Toast.LENGTH_LONG)
							.show();

					phone.requestFocus();

				}

			} else {

				Toast.makeText(context, "请输入您的电话号码", Toast.LENGTH_LONG).show();

				phone.requestFocus();

			}

			break;

		case R.id.savecord:

			if (!TextUtils.isEmpty(cord.getText().toString().trim())) {

				if (cord.getText().toString().trim().length() == 4) {

					iCord = cord.getText().toString().trim();

					SMSSDK.submitVerificationCode("86", iPhone, iCord);

					flag = false;

				} else {

					Toast.makeText(context, "请输入完整验证码", Toast.LENGTH_LONG)
							.show();

					cord.requestFocus();

				}

			} else {

				Toast.makeText(context, "请输入验证码", Toast.LENGTH_LONG).show();

				cord.requestFocus();

			}

			break;

		case R.id.btn:

			Intent intent = new Intent(context, CaptureActivity.class);
			startActivityForResult(intent, REQUEST_CODE_SCAN);
			break;

		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// 扫描二维码/条码回传
		if (requestCode == REQUEST_CODE_SCAN) {
			if (data != null) {
				String content = data.getStringExtra(Consants.CODED_CONTENT);
				resultTv.setText("扫描结果为：" + content);
			}
		}
	}

	// 验证码送成功后提示文字

	private void reminderText() {

		now.setVisibility(View.VISIBLE);

		handlerText.sendEmptyMessageDelayed(1, 1000);

	}

	Handler handlerText = new Handler() {

		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				if (time > 0) {

					now.setText("验证码已发送" + time + "秒");

					time--;

					handlerText.sendEmptyMessageDelayed(1, 1000);

				} else {

					now.setText("提示信息");

					time = 60;

					now.setVisibility(View.GONE);

					getCord.setVisibility(View.VISIBLE);

				}

			} else {

				cord.setText("");

				now.setText("提示信息");

				time = 60;

				now.setVisibility(View.GONE);

				getCord.setVisibility(View.VISIBLE);

			}

		};

	};

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			// TODO Auto-generated method stub

			super.handleMessage(msg);

			int event = msg.arg1;

			int result = msg.arg2;

			Object data = msg.obj;

			Log.e("event", "event=" + event);

			if (result == SMSSDK.RESULT_COMPLETE) {

				// 短信注册成功后，返回MainActivity,然后提示新好友

				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功,验证通过

					Toast.makeText(context, "验证码校验成功", Toast.LENGTH_SHORT)
							.show();

					handlerText.sendEmptyMessage(2);

				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {// 服务器验证码发送成功

					reminderText();

					Toast.makeText(context, "验证码已经发送", Toast.LENGTH_SHORT)
							.show();

				} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {// 返回支持发送验证码的国家列表

					Toast.makeText(context, "获取国家列表成功", Toast.LENGTH_SHORT)
							.show();

				}

			} else {

				if (flag) {

					getCord.setVisibility(View.VISIBLE);

					Toast.makeText(context, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT)
							.show();

					phone.requestFocus();

				} else {

					((Throwable) data).printStackTrace();

					Toast.makeText(context, "验证码错误", Toast.LENGTH_SHORT).show();

					cord.selectAll();

				}

			}

		}

	};

	@Override
	public void onDestroy() {

		super.onDestroy();

		SMSSDK.unregisterAllEventHandler();

	}

}
