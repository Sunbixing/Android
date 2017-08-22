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

	// 单例模式中获取唯一的MyApplication实例
	public static DemoApplication getInstance() {
		if (null == instance) {
			instance = new DemoApplication();
		}
		return instance;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);

	}

}