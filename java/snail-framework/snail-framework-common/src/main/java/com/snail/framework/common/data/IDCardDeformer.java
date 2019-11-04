package com.snail.framework.common.data;


import com.snail.framework.common.data.encode.Encode;
import com.snail.framework.common.util.StringUtils;

public class IDCardDeformer {
	/**
	 * 加密18位身份证
	 * 
	 * @param idCardNo
	 * @return
	 */
	public static String encode(String idCardNo) {
		if(StringUtils.isNotEmpty(idCardNo)){
			return Encode.encodeIdCardNo( idCardNo, true );
		}else{
			return idCardNo;
		}
	}

	/**
	 * 解密18位身份证
	 * 
	 * @param idCardNo
	 * @return
	 */
	public static String decode(String idCardNo) {
		if(StringUtils.isNotEmpty(idCardNo)){
			return Encode.encodeIdCardNo(idCardNo, false);
		}else{
			return idCardNo;
		}

	}

}
