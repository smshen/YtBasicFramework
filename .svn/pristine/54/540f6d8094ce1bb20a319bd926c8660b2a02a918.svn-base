/*
 * 作者		刘国山 lgs@yitong.com.cn
 * 开发环境	Win7 Eclipse3.5 JDK1.6
 * 开发日期	2012-7-19
 */
package com.yitong.utils;

/**
 * GPS帮助类
 * @Description 
 * @Author Lewis(lgs@yitong.com.cn) 2014年8月13日 上午9:57:56
 * @Class GpsTools
 * Copyright (c) 2014 Shanghai P&C Information Technology Co.,Ltd. All rights reserved.
 */
public class GpsTools {

	private static final double EARTH_RADIUS = 6378137.0;

	/**
	 * 
	 * @Description 
	 * @param lat_a
	 * @param lng_a
	 * @param lat_b
	 * @param lng_b
	 * @return 距离 单位米
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-7-19
	 */
	public static double gpsDistance(double lat_a, double lng_a, double lat_b, double lng_b) {
		double radLat1 = (lat_a * Math.PI / 180.0);
		double radLat2 = (lat_b * Math.PI / 180.0);
		double a = radLat1 - radLat2;
		double b = (lng_a - lng_b) * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
}
