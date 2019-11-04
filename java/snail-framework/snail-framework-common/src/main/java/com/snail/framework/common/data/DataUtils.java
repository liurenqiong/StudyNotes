
package com.snail.framework.common.data;

import com.snail.framework.common.util.StringUtils;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.text.DecimalFormat;

public final class DataUtils
{

	// Salt
	private static final int SALT = 1234;

	/**
	 * Get hash value.
	 * 
	 * @param prefix
	 * @param len
	 * @return
	 */
	public static int getHashValue( String prefix, String ext, int len )
	{
		String inputStr = prefix + ext + SALT;
		int hashVal = Hashing.md5( )
				.hashString( inputStr, Charset.forName( "UTF-8" ) ).asInt( );
		String hashStr = String.valueOf( Math.abs( hashVal ) );
		String val = hashStr.length( ) >= len
				? hashStr.substring( 0, len )
				: hashStr;
		return Integer.valueOf( val );
	}

	/**
	 * Align number.
	 * 
	 * @param val
	 * @param format
	 * @return
	 */
	public static String alignNumber( int val, String format )
	{
		if ( StringUtils.isEmpty( format ) )
		{
			format = "0000";
		}

		DecimalFormat df = new DecimalFormat( format );
		return df.format( val );
	}

	/**
	 * Align number.
	 * 
	 * @param val
	 * @param format
	 * @return
	 */
	public static String alignNumber( long val, String format )
	{
		if ( StringUtils.isEmpty( format ) )
		{
			format = "00000000";
		}

		DecimalFormat df = new DecimalFormat( format );
		return df.format( val );
	}

	/**
	 * Sum String
	 * 
	 * @param val
	 * @return
	 */
	public static int sumString( String val )
	{
		if ( StringUtils.isEmpty( val ) )
		{
			return -1;
		}

		int sum = 0;
		int len = val.length( );
		for ( int i = 0; i < len; i++ )
		{
			sum += val.charAt( i );
		}

		return sum;
	}
}
