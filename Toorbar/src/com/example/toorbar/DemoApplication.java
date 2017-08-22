package com.example.toorbar;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class DemoApplication extends Application {
	public List<Activity> activityList = new LinkedList<Activity>();
	public static DemoApplication instance;

	public DemoApplication() {
	}

	// ����ģʽ�л�ȡΨһ��MyApplicationʵ��
	public static DemoApplication getInstance() {
		if (null == instance) {
			instance = new DemoApplication();
		}
		return instance;
	}

	// ���Activity��������
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// ��������Activity��finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// ��ʹ�� SDK �����֮ǰ��ʼ�� context ��Ϣ������ ApplicationContext
		SDKInitializer.initialize(this);

	}

}