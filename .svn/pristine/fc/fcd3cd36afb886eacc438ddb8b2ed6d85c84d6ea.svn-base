package com.yitong.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 随机数工具类
 *  
 * @Author Lewis(lgs@yitong.com.cn) 2014年8月13日 上午9:56:45
 * @Class RandomUtils
 * Copyright (c) 2014 Shanghai P&C Information Technology Co.,Ltd. All rights reserved.
 */
public class RandomUtils {
	
	/**
	 * 生成指定长度的随机数，纯数字
	 */
	private static final char[] CHAR_RANDOMS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	public static String generateRandomNumber(int length) {
		Random random = new Random();
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < length; i++) {
			ret.append(CHAR_RANDOMS[random.nextInt(CHAR_RANDOMS.length)]);
		}
		random = null;
		return ret.toString();
	}
	
	/**
	 * 生成指定长度的随机字符串，数字字母组合
	 */
	private static final String POSSIBLE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS
					.length())));
		}
		return sb.toString();
	}	
}
