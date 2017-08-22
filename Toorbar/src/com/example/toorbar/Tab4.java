package com.example.toorbar;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.service.LocationUtils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Tab4 extends Fragment {
	private Context context;

	public Tab4(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unused")
	private boolean flag;
	public MapView mapView = null;
	public BaiduMap baiduMap = null;

	// 定位相关声明
	public LocationClient locationClient = null;
	// 自定义图标
	BitmapDescriptor mCurrentMarker = null;
	boolean isFirstLoc = true;// 是否首次定位

	public BDLocationListener myListener = new BDLocationListener() {
		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mapView == null)
				return;

			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			baiduMap.setMyLocationData(locData); // 设置定位数据

			if (isFirstLoc) {
				isFirstLoc = false;

				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory
						.newLatLngZoom(ll, 16); // 设置地图中心点以及缩放级别
				// MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				baiduMap.animateMapStatus(u);
			}
		}

		@Override
		public void onConnectHotSpotMessage(String arg0, int arg1) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab4, container, false);

		mapView = view.findViewById(R.id.bmapview); // 获取地图控件引用
		baiduMap = mapView.getMap();
		// 开启定位图层
		baiduMap.setMyLocationEnabled(true);

		locationClient = new LocationClient(context); // 实例化LocationClient类
		locationClient.registerLocationListener(myListener); // 注册监听函数
		getBestLocation(); // 设置定位参数
		locationClient.start(); // 开始定位
		// baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图

		// baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //设置为卫星地图
		// baiduMap.setTrafficEnabled(true); //开启交通图

		return view;
	}

	@SuppressWarnings("unused")
	private void initPermission() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			// 检查权限
			if (ActivityCompat.checkSelfPermission(context,
					Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
					|| ActivityCompat.checkSelfPermission(context,
							Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				// 请求权限
				ActivityCompat.requestPermissions(getActivity(), new String[] {
						Manifest.permission.ACCESS_COARSE_LOCATION,
						Manifest.permission.ACCESS_FINE_LOCATION }, 1);
			} else {
				flag = true;
			}
		} else {
			flag = true;
		}
	}

	/**
	 * 权限的结果回调函数
	 */
	@SuppressLint("NewApi")
	@Override
	public void onRequestPermissionsResult(int requestCode,
			@NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1) {
			flag = grantResults[0] == PackageManager.PERMISSION_GRANTED
					&& grantResults[1] == PackageManager.PERMISSION_GRANTED;
		}
	}

	/**
	 * 通过GPS获取定位信息
	 */
	public void getGPSLocation() {
		Location gps = LocationUtils.getGPSLocation(context);
		if (gps == null) {
			// 设置定位监听，因为GPS定位，第一次进来可能获取不到，通过设置监听，可以在有效的时间范围内获取定位信息
			LocationUtils.addLocationListener(context,
					LocationManager.GPS_PROVIDER,
					new LocationUtils.ILocationListener() {
						@Override
						public void onSuccessLocation(Location location) {
							if (location != null) {
								Toast.makeText(
										context,
										"gps onSuccessLocation location:  lat=="
												+ location.getLatitude()
												+ "     lng=="
												+ location.getLongitude(),
										Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(context,
										"gps location is null",
										Toast.LENGTH_SHORT).show();
							}
						}
					});
		} else {
			Toast.makeText(
					context,
					"gps location: lat==" + gps.getLatitude() + "  lng=="
							+ gps.getLongitude(), Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 通过网络等获取定位信息
	 */
	@SuppressWarnings("unused")
	private void getNetworkLocation() {
		Location net = LocationUtils.getNetWorkLocation(context);
		if (net == null) {
			Toast.makeText(context, "net location is null", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(
					context,
					"network location: lat==" + net.getLatitude() + "  lng=="
							+ net.getLongitude(), Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 采用最好的方式获取定位信息
	 */
	private void getBestLocation() {
		Criteria c = new Criteria();// Criteria类是设置定位的标准信息（系统会根据你的要求，匹配最适合你的定位供应商），一个定位的辅助信息的类
		c.setPowerRequirement(Criteria.POWER_LOW);// 设置低耗电
		c.setAltitudeRequired(true);// 设置需要海拔
		c.setBearingAccuracy(Criteria.ACCURACY_COARSE);// 设置COARSE精度标准
		c.setAccuracy(Criteria.ACCURACY_LOW);// 设置低精度
		// ... Criteria 还有其他属性，就不一一介绍了
		Location best = LocationUtils.getBestLocation(context, c);
		if (best == null) {
			Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(
					context,
					"best location: lat==" + best.getLatitude() + " lng=="
							+ best.getLongitude(), Toast.LENGTH_SHORT).show();
		}
	}

	// 三个状态实现地图生命周期管理
	@Override
	public void onDestroy() {
		// 退出时销毁定位
		locationClient.stop();
		baiduMap.setMyLocationEnabled(false);
		// TODO Auto-generated method stub
		super.onDestroy();
		mapView.onDestroy();
		mapView = null;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mapView.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 设置定位参数
	 */
	@SuppressWarnings("unused")
	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开GPS
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向

		locationClient.setLocOption(option);
	}

}
