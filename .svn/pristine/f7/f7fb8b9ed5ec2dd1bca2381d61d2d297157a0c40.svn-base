package com.yitong.utils;

import java.lang.reflect.Method;

import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.yitong.logs.Logs;

/**
 * 设备信息
 * 
 * @Description
 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-3-3 下午5:20:06
 * @Class DeviceInfoUtil Copyright (c) 2014 Shanghai P&C Information Technology
 *        Co.,Ltd. All rights reserved.
 */
public class DeviceInfoUtil {
	private static TelephonyManager tm;

	public DeviceInfoUtil(Context context) {
		// TODO Auto-generated constructor stub
		tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		/*
		 * 电话状态： 1.tm.CALL_STATE_IDLE=0 无活动 2.tm.CALL_STATE_RINGING=1 响铃
		 * 3.tm.CALL_STATE_OFFHOOK=2 摘机
		 */
		tm.getCallState();// int

		/*
		 * 电话方位：
		 */
		tm.getCellLocation();// CellLocation
		/*
		 * 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID. Return null if device ID is not
		 * available.
		 */
		tm.getDeviceId();// String

		/*
		 * 设备的软件版本号： 例如：the IMEI/SV(software version) for GSM phones. Return
		 * null if the software version is not available.
		 */
		tm.getDeviceSoftwareVersion();// String

		/*
		 * 手机号： GSM手机的 MSISDN. Return null if it is unavailable.
		 */
		tm.getLine1Number();// String

		/*
		 * 附近的电话的信息: 类型：List
		 * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
		 */
		tm.getNeighboringCellInfo();// List
		/*
		 * 获取ISO标准的国家码，即国际长途区号。 注意：仅当用户已在网络注册后有效。 在CDMA网络中结果也许不可靠。
		 */
		tm.getNetworkCountryIso();// String

		/*
		 * MCC+MNC(mobile country code + mobile network code) 注意：仅当用户已在网络注册时有效。
		 * 在CDMA网络中结果也许不可靠。
		 */
		tm.getNetworkOperator();// String

		/*
		 * 按照字母次序的current registered operator(当前已注册的用户)的名字 注意：仅当用户已在网络注册时有效。
		 * 在CDMA网络中结果也许不可靠。
		 */
		tm.getNetworkOperatorName();// String

		/*
		 * 当前使用的网络类型： 例如： NETWORK_TYPE_UNKNOWN 网络类型未知 0 NETWORK_TYPE_GPRS GPRS网络
		 * 1 NETWORK_TYPE_EDGE EDGE网络 2 NETWORK_TYPE_UMTS UMTS网络 3
		 * NETWORK_TYPE_HSDPA HSDPA网络 8 NETWORK_TYPE_HSUPA HSUPA网络 9
		 * NETWORK_TYPE_HSPA HSPA网络 10 NETWORK_TYPE_CDMA CDMA网络,IS95A 或 IS95B. 4
		 * NETWORK_TYPE_EVDO_0 EVDO网络, revision 0. 5 NETWORK_TYPE_EVDO_A EVDO网络,
		 * revision A. 6 NETWORK_TYPE_1xRTT 1xRTT网络 7
		 */
		tm.getNetworkType();// int

		/*
		 * 手机类型： 例如： PHONE_TYPE_NONE 无信号 PHONE_TYPE_GSM GSM信号 PHONE_TYPE_CDMA
		 * CDMA信号
		 */
		tm.getPhoneType();// int

		/*
		 * 服务商名称： 例如：中国移动、联通 SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
		 */
		tm.getSimOperatorName();// String

		/*
		 * SIM卡的序列号： 需要权限：READ_PHONE_STATE
		 */
		tm.getSimSerialNumber();// String

		/*
		 * SIM的状态信息： SIM_STATE_UNKNOWN 未知状态 0 SIM_STATE_ABSENT 没插卡 1
		 * SIM_STATE_PIN_REQUIRED 锁定状态，需要用户的PIN码解锁 2 SIM_STATE_PUK_REQUIRED
		 * 锁定状态，需要用户的PUK码解锁 3 SIM_STATE_NETWORK_LOCKED 锁定状态，需要网络的PIN码解锁 4
		 * SIM_STATE_READY 就绪状态 5
		 */
		tm.getSimState();// int

		/*
		 * 唯一的用户ID： 例如：IMSI(国际移动用户识别码) for a GSM phone. 需要权限：READ_PHONE_STATE
		 */
		tm.getSubscriberId();// String

		StringBuilder sb = new StringBuilder();
		sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());
		sb.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());
		sb.append("\nLine1Number = " + tm.getLine1Number());
		sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());
		sb.append("\nNetworkOperator = " + tm.getNetworkOperator());
		sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());
		sb.append("\nNetworkType = " + tm.getNetworkType());
		sb.append("\nPhoneType = " + tm.getPhoneType());
		sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
		sb.append("\nSimOperator = " + tm.getSimOperator());
		sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
		sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
		sb.append("\nSimState = " + tm.getSimState());
		sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
		sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());
		Logs.e("info", sb.toString());
	}

	/*
	 * 取得操作系统版本号
	 */
	public static String getOSVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 可以用于获取设备唯一标示
	 * 
	 * @Description
	 * @Author dmq(dmq@yitong.com.cn) 2014-3-5 上午11:45:02
	 */
	public static String getClientDeviceInfo(Context ctx) {
		String deviceID = "";
		String serial = "";
		TelephonyManager tm = (TelephonyManager) ctx
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm.getDeviceId() != null) {
			deviceID = tm.getDeviceId();
			try {
				Class<?> c = Class.forName("android.os.SystemProperties");
				Method get = c.getMethod("get", String.class);
				serial = (String) get.invoke(c, "ro.serialno");
			} catch (Exception e) {
			}
		}
		String buildVersion = android.os.Build.VERSION.RELEASE;
		return deviceID + "|android" + "|android|" + buildVersion + "|android";
	}

	// 获取根目录路径
	public static String getSDPath() {
		boolean hasSDCard = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
		// 如果有sd卡，则返回sd卡的目录
		if (hasSDCard) {
			return Environment.getExternalStorageDirectory().getPath();
		} else
			// 如果没有sd卡，则返回存储目录
			return Environment.getDownloadCacheDirectory().getPath();
	}
	
	
}
