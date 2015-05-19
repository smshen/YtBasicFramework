package com.yitong.utils;
//package com.yitong.utils.yitong;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.location.Location;
//import android.location.LocationManager;
//import android.os.Handler;
//import android.util.Log;
//
///**
// * 百度定位，支持离线定位和网络定位 使用3.3的jar
// * 
// * @Description
// * @author 刘国山 lgs@yitong.com.cn
// * @version 1.0 2013年8月20日
// * @class cn.com.yitong.mobilebank.util.LocationUtil
// */
//public class LocationUtil {
//
//	private static final String TAG = "LocationUtil——----";
//	private static LocationUtil locUtil;
//
//	private SharedPreferences settings = null;
//	// 时间间隔
//	private static int myTime = 1 * 1000;
//	private LocationClient locationClient = null; // 定位类
//	private String mData; // 获取的数据
//	// 定位时间间隔
//	private int myLocationTime = 1 * 1000;
//	// 是否启动了定位API
//	private boolean isOpenLocation = false;
//	// 是否启动了定位线程
//	private static boolean isOpenLocationTask = false;
//	// 经纬度
//	private double jingweidu[] = new double[2];
//
//	public MyLocationListenner myListener;
//
//	public static LocationUtil getInstances() {
//		// 启动位置服务监听
//		if (null == locUtil) {
//			locUtil = new LocationUtil();
//		}
//		return locUtil;
//	}
//
//	private Handler handler;
//
//	public LocationUtil(Handler handler) {
//		this.handler = handler;
//	}
//
//	public LocationUtil() {
//	}
//
//	/***
//	 * 打开定位定时器线程,只有在外网才可用
//	 */
//	public void requestCurrentLocation(Context ctx) {
//		Log.v(TAG, "---------------显示地图,定位器启动-----------------.");
//		locationClient = new LocationClient(ctx);
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(false); // 打开gps
//		// 返回国测局经纬度坐标系 coor=gcj02
//		// 返回百度经纬度坐标系 coor=bd09ll
//		option.setCoorType("gcj02"); // 设置坐标类型为gcj02
//		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
//		option.setAddrType("street_number");
//		locationClient.setLocOption(option);
//		myListener = new MyLocationListenner();
//		locationClient.registerLocationListener(myListener);
//
//		settings = ctx.getSharedPreferences(APPConstants.SharedPreferences_URL,
//				0);
//		try {
//			// 如果不是打开状态，则打开线程
//			if (!isOpenLocationTask) {
//				startLocation();// 启动定位更新经纬度
//				Log.v(TAG, " 打开了定位定时器线程 ");
//				isOpenLocationTask = true; // 标记为打开了定时线程
//			} else {
//				Log.v(TAG, " 已经开启了定位定时器线程 ");
//			}
//		} catch (Exception e) {
//			Log.v(TAG, "打开定位定时器线程 异常" + e.toString());
//		}
//	}
//
//	/**
//	 * start定位
//	 */
//	private void startLocation() {
//		try {
//			if (!isOpenLocation) {
//				// 返回国测局经纬度坐标系 coor=gcj02
//				// 返回百度经纬度坐标系 coor=bd09ll
//				// mLocationClient.setCoorType("bd09ll"); // 设置返回的坐标类型
//				// locationClient.setCoorType("gcj02");
//				// locationClient.setTimeSpan(myLocationTime); // 设置时间
//				// locationClient.setAddrType("street_number"); // 返回地址类型
//				// locationClient.setServiceMode(LocServiceMode.Immediat); //
//				// 定位方式为：即时定位
//				// locationClient.addRecerveListener(new MyReceiveListenner());
//				locationClient.start(); // 打开定位
//				isOpenLocation = true; // 标识为已经打开了定位
//			}
//		} catch (Exception e) {
//			Log.v(TAG, "打开定位异常" + e.toString());
//		}
//	}
//
//	// 被动的接受经纬度信息
//	// private class MyReceiveListenner implements ReceiveListener {
//	// public void onReceive(String strData) {
//	// try {
//	// mData = strData.trim();
//	// /**
//	// * { "result":{ "time":"2011-10-11 17:06:07","error":"161"},
//	// * "content":{
//	// * "point":{"x":"117.13045277196","y":"39.104208211327"},
//	// * "radius":"130", "addr":{"street_number":"206号"} } } }
//	// */
//	// Log.v(TAG, "进入了定位 定时器 更新了经纬度方法  -- 信息：" + mData);
//	// // 解析经纬度
//	// JSONObject jsonObject = new JSONObject(mData);
//	// JSONObject jsonjingweidu = jsonObject.getJSONObject("content")
//	// .getJSONObject("point");
//	// String longitude = jsonjingweidu.getString("y");
//	// String latitude = jsonjingweidu.getString("x");
//	// jingweidu = new double[] { stringToDouble(longitude),
//	// stringToDouble(latitude) };
//	// Log.v(TAG, "longitude :" + jingweidu[0] + "latitude : "
//	// + jingweidu[1]);
//	// int r = setLocalLocation(); // 经纬度保存到本地
//	// if (r == 1) {
//	// Location loc = getLastKnownLocation();
//	// Log.v(TAG, "保存经纬度到本地成功  ,经度：" + loc.getLatitude() + "纬度："
//	// + loc.getLongitude());
//	// // 主动关闭定位服务
//	// removeGPSListener();
//	// } else {
//	// Log.v(TAG, "保存经纬度到本地失败");
//	// }
//	// } catch (Exception e) {
//	// Log.e(TAG, "更新操作异常", e);
//	// }
//	// }
//	// }
//
//	/**
//	 * 监听函数，又新位置的时候，格式化成字符串，输出到屏幕中
//	 */
//	public class MyLocationListenner implements BDLocationListener {
//		@Override
//		public void onReceiveLocation(BDLocation location) {
//			if (location == null)
//				return;
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(location.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(location.getRadius());
//			if (location.getLocType() == BDLocation.TypeGpsLocation) {
//				sb.append("\nspeed : ");
//				sb.append(location.getSpeed());
//				sb.append("\nsatellite : ");
//				sb.append(location.getSatelliteNumber());
//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//				// sb.append("\n省：");
//				// sb.append(location.getProvince());
//				// sb.append("\n市：");
//				// sb.append(location.getCity());
//				// sb.append("\n区/县：");
//				// sb.append(location.getDistrict());
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//			}
//			sb.append("\nsdk version : ");
//			sb.append(locationClient.getVersion());
//			sb.append("\nisCellChangeFlag : ");
//			sb.append(location.isCellChangeFlag());
//			Log.i(TAG, sb.toString());
//			handler.sendEmptyMessage(0);
//			try {
//				jingweidu = new double[] { location.getLatitude(),
//						location.getLongitude() };
//				Log.v(TAG, "latitude :" + jingweidu[0] + "longitude : "
//						+ jingweidu[1]);
//				int r = setLocalLocation(); // 经纬度保存到本地
//				if (r == 1) {
//					Location loc = getLastKnownLocation();
//					Log.v(TAG, "保存经纬度到本地成功  ,经度：" + loc.getLongitude() + "纬度："
//							+ loc.getLatitude());
//					// 主动关闭定位服务
//					removeGPSListener();
//				} else {
//					Log.v(TAG, "保存经纬度到本地失败");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				Log.v(TAG, "保存经纬度到本地发生异常");
//			}
//		}
//
//		public void onReceivePoi(BDLocation poiLocation) {
//			if (poiLocation == null) {
//				return;
//			}
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("Poi time : ");
//			sb.append(poiLocation.getTime());
//			sb.append("\nerror code : ");
//			sb.append(poiLocation.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(poiLocation.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(poiLocation.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(poiLocation.getRadius());
//			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
//				sb.append("\naddr : ");
//				sb.append(poiLocation.getAddrStr());
//			}
//			if (poiLocation.hasPoi()) {
//				sb.append("\nPoi:");
//				sb.append(poiLocation.getPoi());
//			} else {
//				sb.append("noPoi information");
//			}
//			Log.i(TAG, sb.toString());
//			handler.sendEmptyMessage(0);
//			try {
//
//				jingweidu = new double[] { poiLocation.getLatitude(),
//						poiLocation.getLongitude() };
//				Log.v(TAG, "latitude :" + jingweidu[0] + "longitude : "
//						+ jingweidu[1]);
//				int r = setLocalLocation(); // 经纬度保存到本地
//				if (r == 1) {
//					Location loc = getLastKnownLocation();
//					Log.v(TAG, "GPS保存经纬度到本地成功  ,经度：" + loc.getLongitude()
//							+ "纬度：" + loc.getLatitude());
//					// 主动关闭定位服务
//					removeGPSListener();
//				} else {
//					Log.v(TAG, "保存经纬度到本地失败");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				Log.v(TAG, "保存经纬度到本地发生异常");
//			}
//
//		}
//	}
//
//	/***
//	 * 关闭定位定时器线程
//	 */
//	public void removeGPSListener() {
//		try {
//			if (isOpenLocationTask) {
//				closeLocation();
//				Log.v(TAG, " 关闭了定位定时器线程 ");
//				isOpenLocationTask = false; // 标记为关闭了定时线程
//			} else {
//				Log.v(TAG, " 已经关闭了定位定时器线程 ");
//			}
//		} catch (Exception e) {
//			Log.v(TAG, "关闭定位定时器线程异常: " + e.toString());
//		}
//	}
//
//	/**
//	 * end 定位
//	 */
//	public void closeLocation() {
//		try {
//			if (locationClient != null)
//				locationClient.stop(); // 结束定位
//			isOpenLocation = false; // 标识为已经结束了定位
//			Log.v(TAG, "结束定位");
//		} catch (Exception e) {
//			Log.v(TAG, "结束定位异常" + e.toString());
//		}
//	}
//
//	public boolean isStop() {
//		return isOpenLocation;
//	}
//
//	/***
//	 * 将经纬度保存在本地
//	 */
//	private int setLocalLocation() {
//		int r = 0;
//		try {
//			// 获取活动的 preferences 对象
//			SharedPreferences usersetting = settings;
//			// 获取编辑对象
//			Editor userinfoeditor = usersetting.edit();
//			userinfoeditor.putLong("LAST_LOCAL_TIME",
//					System.currentTimeMillis());
//			userinfoeditor
//					.putFloat("LAST_LOCAL_LATITUDE", (float) jingweidu[0]);// 经度
//			userinfoeditor.putFloat("LAST_LOCAL_LONGITUDE",
//					(float) jingweidu[1]);// 纬度
//			Log.d(TAG, "获取本地的经纬度:" + jingweidu[0] + "," + jingweidu[1]);
//			userinfoeditor.commit();
//			return 1;
//		} catch (Exception e) {
//			Log.v(TAG, "  保存经纬度，到本地失败" + e.toString());
//			r = 0;
//		}
//		return r;
//	}
//
//	/***
//	 * 获取本地的经纬度
//	 */
//	public Location getLastKnownLocation() {
//		Location loc = null;
//		try {
//			// 获取活动的 preferences 对象
//			loc = new Location(LocationManager.NETWORK_PROVIDER);
//			double longitude = settings.getFloat("LAST_LOCAL_LATITUDE", 0); // 经度
//			double latitude = settings.getFloat("LAST_LOCAL_LONGITUDE", 0); // 纬度
//			Log.d(TAG, "获取本地的经纬度:" + longitude + "," + latitude);
//			if (longitude == 0 && 0 == latitude)
//				return null;
//			loc.setLongitude(longitude);
//			loc.setLatitude(latitude);
//		} catch (NumberFormatException e) {
//			Log.e(TAG, "空值异常", e);
//			return null;
//		} catch (Exception e) {
//			Log.e(TAG, "获取异常", e);
//			return null;
//		}
//		return loc;
//	}
//}
