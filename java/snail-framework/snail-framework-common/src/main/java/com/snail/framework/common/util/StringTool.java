package com.snail.framework.common.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * @功能:
 * @作者:snail
 */
@Slf4j
public class StringTool {
	
    /**
     * 判断string是否为空或空字符串
     * @param obj
     * @return
     */
	public static boolean isEmpty(String obj) {
		return obj == null || obj.trim().length() < 1;
	}

	/**
	 * 解压缩字符串
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String unCompress(String str) throws IOException {
		if (isEmpty(str)) {
			return str;
		}
		return unCompress(str.getBytes("ISO-8859-1"));
	}

	/**
	 * 解压缩字符串
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static String unCompress(byte[] bytes) throws IOException {
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		GZIPInputStream gUnzip = null;
		try {
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(bytes);// 测试只能用"ISO-8859-1",否则报Not in
													// GZIP format
			gUnzip = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = gUnzip.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.flush();
			return out.toString("UTf-8");
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			if (gUnzip != null) {
				gUnzip.close();
			}
		}
	}

	/**
	 * 将java转义字符转换成html标签
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceJavaCharToHtml(String str) {
		if (str != null) {
			str = str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
			str = str.replaceAll("(\")", "&quot;");
			str = str.replaceAll("(\')", "&quot;");
			str = str.replaceAll("\\\\", "");
			str = str.replaceAll("&quot;", "\"");
			str = str.replaceAll("&gt;", ">");
			str = str.replaceAll("&lt;", "<");
			str = str.replaceAll("&amp;", "&");
		}
		return str;
	}

	/**
	 * 用*号隐藏手机号码
	 * 
	 * @param telNo
	 * @return
	 */
	public static String hiddenTelNo(String telNo) {
		if (isEmpty(telNo)) {
			return null;
		}
		return telNo.replaceAll("^(\\d{3})(\\d*)(\\d{4})$", "$1****$3");
	}

	/**
	 * 用*号隐藏银行卡号
	 * 例：6222 **** **** 6666
	 * 
	 * @param cardNo
	 * @return
	 */
	public static String hiddenBankCardCode(String cardNo) {
		if (isEmpty(cardNo)) {
			return null;
		}
		return cardNo.replaceAll("^(\\d{4})(\\d*)(\\d{4})$", "$1 **** **** $3");
	}

	/**
	 * 除最后4位，用*号隐藏银行卡号
	 * 例：**** **** **** 6666
	 * 
	 * @param cardNo
	 * @param num 最后的位数，默认为4
	 * @return
	 */
	public static String hiddenBankCardCodeExceptLast4(String cardNo, Integer num) {
		num = num != null ? num : 4;
		if (isEmpty(cardNo)) {
			return null;
		}
		return cardNo.replaceAll("^(\\d*)(\\d{" + num + "})$", "**** **** **** $2");
	}

	/**
	 * 用*号隐藏身份证号码
	 * 
	 * @param idCard
	 * @return
	 */
	public static String hiddenIdCardCode(String idCard) {
		if (isEmpty(idCard)) {
			return null;
		}
		return idCard.replaceAll("^(\\d{2})(\\d*)(\\d{4})$", "$1************$3");
	}

	/**
	 * 用*号隐藏身份证号码
	 * 匹配前4位和后四位字母或者数字
	 * 
	 * @param idCard
	 * @return
	 */
	public static String hiddenIdCardCode2(String idCard) {
		if (isEmpty(idCard)) {
			return null;
		}
		return idCard.replaceAll("^(\\w{4})(\\w*)(\\w{4})$", "$1************$3");
	}

	/**
	 * 用*号隐藏用户真实姓名
	 * 
	 * @param idCardName
	 * @return
	 */
	public static String hiddenIdCardName(String idCardName) {
		if (isEmpty(idCardName)) {
			return null;
		} else {
			StringBuffer name = new StringBuffer();
			if (idCardName.length() > 3) {
				name.append(idCardName.substring(0, 2));
			} else {
				name.append(idCardName.substring(0, 1));
			}
			for (int i = name.length(); i < idCardName.length(); i++) {
				name.append("*");
			}
			return name.toString();
		}
	}

	/**
	 * 产生length长度的一个随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String bringValidateCode(int length) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < length; i++) {
			Double d = Math.random() * 10;
			s.append(d.intValue());
		}
		return s.toString();
	}

	/**
	 * 获取交易时的批次号、交易号等等
	 * 
	 * @param prefix 前缀如 DD、PC...
	 * @param dateFormat 时间格式 如 yyyyMMddHHmmssSSS
	 * @param suffixLen 末尾追加的随即串长度
	 * @return
	 */
	public static String getTimeCode(String prefix, String dateFormat, int suffixLen) {
		return prefix + new SimpleDateFormat(dateFormat).format(new Date()) + bringValidateCode(suffixLen);
	}

	/**
	 * 在字符前加0，达到一定的位数，目前最长支持补10位零
	 * 
	 * @param oldStr
	 * @param length
	 * @return
	 */
	public static String fillZero(String oldStr, int length) {
		String[] zeorArray = new String[] { "", "0", "00", "000", "0000", "00000", "000000", "0000000", "00000000",
				"000000000", "0000000000" };
		if (length < oldStr.length()) {
			return oldStr;
		}
		return zeorArray[length - oldStr.length()] + oldStr;
	}

	/**
	 * md5加密:使用UTF-8，结果转成全大写
	 * 
	 * @param str
	 * @return
	 */
	public static String MD5Encode(String str) {
		return MD5Encode(str, "UTF-8", true);
	}

	/**
	 * md5加密：使用UTF-8,根据参数转成全大写
	 * 
	 * @param str
	 * @param upperCase
	 * @return
	 */
	public static String MD5Encode(String str, boolean upperCase) {
		return MD5Encode(str, "UTF-8", upperCase);
	}

	/**
	 * md5加密
	 * 
	 * @param str 要加密的字符串
	 * @param charsetname 字符编码 UTF-8
	 * @param upperCase 是否转为大写
	 * @return
	 */
	public static String MD5Encode(String str, String charsetname, boolean upperCase) {
		StringBuffer resultSb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] arr = md.digest(str.getBytes(charsetname));
			for (int b : arr) {
				if (b < 0) {
					b = 256 + b;
				}
				if (b <= 15) {
					resultSb.append("0");
				}
				resultSb.append(Integer.toHexString(b));
			}
			if (upperCase) {
				return resultSb.toString().toUpperCase();
			} else {
				return resultSb.toString();
			}
		} catch (Exception ex) {
			log.error("md5加密失败！", ex);
		}
		return null;
	}

	/**
	 * sha1加密
	 * 
	 * @param str
	 * @return
	 */
	public static String sha1Encode(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.reset();
			md.update(str.getBytes("UTF-8"));
			byte[] arr = md.digest();

			Formatter formatter = new Formatter();
			for (byte b : arr) {
				formatter.format("%02x", b);
			}
			String result = formatter.toString();
			formatter.close();
			return result;
		} catch (Exception ex) {
			log.error("SHA-1加密失败！", ex);
		}
		return null;
	}

	/**
	 * 验证字符串是否是手机号码
	 * 
	 * @param string 要验证的字符串
	 * @return
	 */
	public static boolean isPhoneNumber(String string) {
		// Pattern pattern =
		// Pattern.compile("^(13[0-9]|14[57]|15[012356789]|17[01678]|18[0-9])[0-9]{8}$"); // 手机号
		Pattern pattern = Pattern.compile("^1[0-9]{10}$"); // 手机号
		Matcher matcher = pattern.matcher(string);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 检查银行卡(Luhm校验) 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
	 * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和。
	 * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
	 * 
	 * @param cardNo
	 * @return
	 */
	public static boolean checkBankCardNo(String cardNo) {
		if (!isEmpty(cardNo)) {
			try {
				int luhmSum = 0;
				int num = 0;
				int index = 1;// 逆向后奇偶标志
				for (int i = cardNo.length() - 1; i >= 0; i--) {
					num = Integer.parseInt(cardNo.charAt(i) + "");
					if (index % 2 == 0) {
						num = num * 2 > 9 ? num * 2 - 9 : num * 2;
					}
					luhmSum += num;
					index++;
				}
				return luhmSum % 10 == 0;
			} catch (Exception ex) {}
		}
		return false;
	}

	/**
	 * 检查身份证号码
	 * 
	 * @param idCardNo
	 * @return
	 */
	public static boolean checkIdCardNo(String idCardNo) {
		if (!isEmpty(idCardNo)) {
			idCardNo = idCardNo.toUpperCase();
			int sum = 0;
			int[] tempNum = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
			String[] code = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
			if (idCardNo != null && idCardNo.length() == 18) {
				try {
					for (int i = 0; i < 17; i++) {
						sum += Integer.parseInt(idCardNo.substring(i, i + 1)) * tempNum[i];
					}
					if (idCardNo.substring(17, 18).equals(code[sum % 11])) {
						return true;
					}
				} catch (Exception e) {}
			}
		}
		return false;
	}

	/**
	 * 根据身份证号码取得性别
	 * 
	 * @param idCardNo
	 * @param isCheckNo 是否检查身份证号码
	 * @return null、1男、2女
	 */
	public static Integer getSexByIdCardNo(String idCardNo, boolean isCheckNo) {
		Integer result = null;
		try {
			boolean isRight = true;
			if (isCheckNo) {
				isRight = checkIdCardNo(idCardNo);
			}
			if (isRight && idCardNo.length() == 18) {
				int num = Integer.parseInt(idCardNo.substring(16, 17)) % 2;
				if (num == 0) {
					result = 2;
				} else {
					result = 1;
				}
			}
		} catch (Exception ex) {
			log.error("根据身份证号码取得性别失败");
		}
		return result;
	}

	
	/**
	 * 根据出生计算当前年龄
	 *
	 * @param birthday
	 * @return
	 */
	public static int getCurrAge(Date birthday) {
	    Calendar now = Calendar.getInstance();
	    Calendar c = Calendar.getInstance();
	    c.setTime(birthday);

	    int age = now.get(Calendar.YEAR) - c.get(Calendar.YEAR);
	    if (now.get(Calendar.MONTH) < c.get(Calendar.MONTH)) {// 不满月
	        age--;
	    } else if (now.get(Calendar.MONTH) == c.get(Calendar.MONTH)) {// 不满日
	        if (now.get(Calendar.DAY_OF_MONTH) < c.get(Calendar.DAY_OF_MONTH)) {
	            age--;
	        }
	    }
	    return age;
	}

	public static String getString(String data) {
		if (!isEmpty(data)) {
			return data.trim();
		}
		return null;
	}

	public static Double getDouble(String data) {
		if (data != null && !isEmpty(data.trim())) {
			return Double.parseDouble(data.trim());
		}
		return null;
	}

	public static String getSerno() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int min = 10000;
		int max = 99999;
		Random random = new Random();
		return format.format(new Date()) + "00000" + (random.nextInt(max) % (max - min + 1) + min);
	}


    public static String generateApplyCode(String prefix) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
        int min = 10000;
        int max = 99999;
        Random random = new Random();
        return prefix + format.format(new Date()) + "0000" + (random.nextInt(max) % (max - min + 1) + min);
    }

	public static boolean isJson(String data) {
		try {
			JSONObject jsonObject = JSONObject.parseObject(data);
			return true;
		} catch (Exception e) {
			log.info("验证字符串出错,e :" + e);
			return false;
		}
	}

	public static String inputStream2String(InputStream in, String encode) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		try {
			if (encode == null || encode.equals("")) {
				encode = "utf-8";
			}
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
			}
			return out.toString(encode);
		} catch (IOException e) {
			log.info("inputstring 2 string出现异常");
		}
		return "";
	}

    /**
     * yyyyMMdd ——> yyyy-MM-dd
     * @param dateOrigin
     * @return
     */
	public static String dateFormateToDash(String dateOrigin){
        try{
            return dateOrigin.substring(0, 4) + "-" + dateOrigin.substring(4, 6) + "-" + dateOrigin.substring(6, 8);
        }catch (Exception e){
            log.info("date格式转换失败");
            return null;
        }
    }

    /**
     * 校验身份证有效期格式
     * @param effDate
     * @return
     */
    public static boolean isEffDateValid(String effDate){
	    try{
            int length = effDate.length();
            return length == 8 && isNumeric(effDate);
        }catch (Exception e){
	        log.error("校验有效期失败", e);
        }
        return false;
    }

    /**
     * 是否整数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 除最后4位，用*号隐藏银行卡号
     * 例：**** **** **** 6666
     *
     * @param num 最后的位数，默认为4
     * @return
     */
    public static String getBankCardLastNum(String bankCardCode, Integer num) {
        num = num != null ? num : 4;
        if (isEmpty(bankCardCode)) {
            return null;
        }
        int length = bankCardCode.length();
        return bankCardCode.substring(length - num , length);
    }

    /**
     * 获取银行卡bin，默认6位
     * @param bankCardCode
     * @param num
     * @return
     */
    public static String getBankCardBin(String bankCardCode, Integer num) {
        num = num != null ? num : 6;
        if (isEmpty(bankCardCode)) {
            return null;
        }
        int length = bankCardCode.length();
        return bankCardCode.substring(0 , num);
    }


    /**
     * 将指定的字符串用MD5加密
     * originstr 需要加密的字符串
     * @param originstr
     * @return
     */
    public static String encodeByMD5(String originstr) {
        String result = null;
        char hexDigits[] = {//用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        if(originstr != null){
            try {
                //返回实现指定摘要算法的 MessageDigest 对象
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用utf-8编码将originstr字符串编码并保存到source字节数组
                byte[] source = originstr.getBytes("utf-8");
                //使用指定的 byte 数组更新摘要
                md.update(source);
                //通过执行诸如填充之类的最终操作完成哈希计算，结果是一个128位的长整数
                byte[] tmp = md.digest();
                //用16进制数表示需要32位
                char[] str = new char[32];
                for(int i=0,j=0; i < 16; i++){
                    //j表示转换结果中对应的字符位置
                    //从第一个字节开始，对 MD5 的每一个字节
                    //转换成 16 进制字符
                    byte b = tmp[i];
                    //取字节中高 4 位的数字转换
                    //无符号右移运算符>>> ，它总是在左边补0
                    //0x代表它后面的是十六进制的数字. f转换成十进制就是15
                    str[j++] = hexDigits[b>>>4 & 0xf];
                    // 取字节中低 4 位的数字转换
                    str[j++] = hexDigits[b&0xf];
                }
                result = new String(str);//结果转换成字符串用于返回
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
