
package com.snail.framework.common.data;


import com.snail.framework.common.data.encode.Encode;
import com.snail.framework.common.util.StringUtils;

/**
 * Utility class to do data deformation for mobile no. If mobile no contains
 * non-numerical char, just bypass it.
 */
	public final class MobileNoDeformer
{

	/**
	 * 加密手机号
	 * @param mobileNo
	 * @return
	 */
	public static String encode( String mobileNo )
	{
		if(StringUtils.isNotEmpty(mobileNo)){
			return Encode.encodeTelNo( mobileNo, true );
		}else{
			return mobileNo;
		}

	}

	/**
	 * 解密手机号
	 * 
	 * @param mobileNo
	 * @return
	 */
	public static String decode( String mobileNo )
	{
		if(StringUtils.isNotEmpty(mobileNo)){
			return Encode.encodeTelNo( mobileNo, false );
		}else{
			return mobileNo;
		}

	}
	
}
