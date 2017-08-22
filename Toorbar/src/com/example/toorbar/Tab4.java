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

	// ��λ�������
	public LocationClient locationClient = null;
	// �Զ���ͼ��
	BitmapDescriptor mCurrentMarker = null;
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ

	public BDLocationListener myListener = new BDLocationListener() {
		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mapView == null)
				return;

			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			baiduMap.setMyLocationData(locData); // ���ö�λ����

			if (isFirstLoc) {
				isFirstLoc = false;

				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory
						.newLatLngZoom(ll, 16); // ���õ�ͼ���ĵ��Լ����ż���
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

		mapView = view.findViewById(R.id.bmapview); // ��ȡ��ͼ�ؼ�����
		baiduMap = mapView.getMap();
		// ������λͼ��
		baiduMap.setMyLocationEnabled(true);

		locationClient = new LocationClient(context); // ʵ����LocationClient��
		locationClient.registerLocationListener(myListener); // ע���������
		getBestLocation(); // ���ö�λ����
		locationClient.start(); // ��ʼ��λ
		// baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // ����Ϊһ���ͼ

		// baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //����Ϊ���ǵ�ͼ
		// baiduMap.setTrafficEnabled(true); //������ͨͼ

		return view;
	}

	@SuppressWarnings("unused")
	private void initPermission() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			// ���Ȩ��
			if (ActivityCompat.checkSelfPermission(context,
					Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
					|| ActivityCompat.checkSelfPermission(context,
							Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				// ����Ȩ��
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
	 * Ȩ�޵Ľ���ص�����
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
	 * ͨ��GPS��ȡ��λ��Ϣ
	 */
	public void getGPSLocation() {
		Location gps = LocationUtils.getGPSLocation(context);
		if (gps == null) {
			// ���ö�λ��������ΪGPS��λ����һ�ν������ܻ�ȡ������ͨ�����ü�������������Ч��ʱ�䷶Χ�ڻ�ȡ��λ��Ϣ
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
	 * ͨ������Ȼ�ȡ��λ��Ϣ
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
	 * ������õķ�ʽ��ȡ��λ��Ϣ
	 */
	private void getBestLocation() {
		Criteria c = new Criteria();// Criteria�������ö�λ�ı�׼��Ϣ��ϵͳ��������Ҫ��ƥ�����ʺ���Ķ�λ��Ӧ�̣���һ����λ�ĸ�����Ϣ����
		c.setPowerRequirement(Criteria.POWER_LOW);// ���õͺĵ�
		c.setAltitudeRequired(true);// ������Ҫ����
		c.setBearingAccuracy(Criteria.ACCURACY_COARSE);// ����COARSE���ȱ�׼
		c.setAccuracy(Criteria.ACCURACY_LOW);// ���õ;���
		// ... Criteria �����������ԣ��Ͳ�һһ������
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

	// ����״̬ʵ�ֵ�ͼ�������ڹ���
	@Override
	public void onDestroy() {
		// �˳�ʱ���ٶ�λ
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
	 * ���ö�λ����
	 */
	@SuppressWarnings("unused")
	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // ��GPS
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// ���ö�λģʽ
		option.setCoorType("bd09ll"); // ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(5000); // ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true); // ���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true); // ���صĶ�λ��������ֻ���ͷ�ķ���

		locationClient.setLocOption(option);
	}

}
