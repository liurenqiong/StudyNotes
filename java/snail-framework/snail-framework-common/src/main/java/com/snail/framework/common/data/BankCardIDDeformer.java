
package com.snail.framework.common.data;


import com.snail.framework.common.data.encode.Encode;
import com.snail.framework.common.util.StringUtils;

/**
 * Utility class to do data deformation for bank card id. If bank card id
 * contains non-numerical char, just bypass it.
 */
public class BankCardIDDeformer
{

	/**
	 * 加密银行卡号
	 * 
	 * @param bankCardNo
	 * @return
	 */
	public static String encode( String bankCardNo )
	{
		if(StringUtils.isNotEmpty(bankCardNo)){
			return Encode.encodeBankCardNo( bankCardNo, true );
		}else{
			return bankCardNo;
		}

	}

	/**
	 * 解密银行卡号
	 * 
	 * @param bankCardNo
	 * @return
	 */
	public static String decode( String bankCardNo )
	{

		if(StringUtils.isNotEmpty(bankCardNo)){
			return Encode.encodeBankCardNo( bankCardNo, false );
		}else{
			return bankCardNo;
		}
	}
}
