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

	/* �Լ���㶨�������� */
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

					Toast.makeText(context, "�����������绰����", Toast.LENGTH_LONG)
							.show();

					phone.requestFocus();

				}

			} else {

				Toast.makeText(context, "���������ĵ绰����", Toast.LENGTH_LONG).show();

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

					Toast.makeText(context, "������������֤��", Toast.LENGTH_LONG)
							.show();

					cord.requestFocus();

				}

			} else {

				Toast.makeText(context, "��������֤��", Toast.LENGTH_LONG).show();

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

		// ɨ���ά��/����ش�
		if (requestCode == REQUEST_CODE_SCAN) {
			if (data != null) {
				String content = data.getStringExtra(Consants.CODED_CONTENT);
				resultTv.setText("ɨ����Ϊ��" + content);
			}
		}
	}

	// ��֤���ͳɹ�����ʾ����

	private void reminderText() {

		now.setVisibility(View.VISIBLE);

		handlerText.sendEmptyMessageDelayed(1, 1000);

	}

	Handler handlerText = new Handler() {

		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				if (time > 0) {

					now.setText("��֤���ѷ���" + time + "��");

					time--;

					handlerText.sendEmptyMessageDelayed(1, 1000);

				} else {

					now.setText("��ʾ��Ϣ");

					time = 60;

					now.setVisibility(View.GONE);

					getCord.setVisibility(View.VISIBLE);

				}

			} else {

				cord.setText("");

				now.setText("��ʾ��Ϣ");

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

				// ����ע��ɹ��󣬷���MainActivity,Ȼ����ʾ�º���

				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// �ύ��֤��ɹ�,��֤ͨ��

					Toast.makeText(context, "��֤��У��ɹ�", Toast.LENGTH_SHORT)
							.show();

					handlerText.sendEmptyMessage(2);

				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {// ��������֤�뷢�ͳɹ�

					reminderText();

					Toast.makeText(context, "��֤���Ѿ�����", Toast.LENGTH_SHORT)
							.show();

				} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {// ����֧�ַ�����֤��Ĺ����б�

					Toast.makeText(context, "��ȡ�����б�ɹ�", Toast.LENGTH_SHORT)
							.show();

				}

			} else {

				if (flag) {

					getCord.setVisibility(View.VISIBLE);

					Toast.makeText(context, "��֤���ȡʧ�ܣ������»�ȡ", Toast.LENGTH_SHORT)
							.show();

					phone.requestFocus();

				} else {

					((Throwable) data).printStackTrace();

					Toast.makeText(context, "��֤�����", Toast.LENGTH_SHORT).show();

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
