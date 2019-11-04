package com.snail.framework.common.data;


import com.snail.framework.common.data.encode.Encode;

public class EmailDeformer {
	/**
	 * 加密邮箱地址
	 * 
	 * @param email
	 * @return
	 */
	public static String encodeEmail(String email) {
		return Encode.encodeEmail(email, true);
	}

	/**
	 * 解密邮箱地址
	 * 
	 * @param email
	 * @return
	 */
	public static String decodeEmail(String email) {
		return Encode.encodeEmail(email, false);
	}

}
