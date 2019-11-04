package com.snail.framework.common.data;


import com.snail.framework.common.data.encode.Encode;
import com.snail.framework.common.util.StringUtils;

public class NameDeformer {
	/**
	 * 加密姓名
	 * 
	 * @param name
	 * @return
	 */
	public static String encode(String name) {
		if(StringUtils.isNotEmpty(name)){
			return Encode.encodeName(name, true);
		}else{
			return name;
		}

	}

	/**
	 * 解密姓名
	 * 
	 * @param name
	 * @return
	 */
	public static String decode(String name) {
		if(StringUtils.isNotEmpty(name)){
			return Encode.encodeName(name, false);
		}else{
			return name;
		}

	}
}
