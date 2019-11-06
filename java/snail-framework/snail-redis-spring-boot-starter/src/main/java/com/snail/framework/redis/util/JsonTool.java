/** */
package com.snail.framework.redis.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @功能: Json转换器
 * @作者:snail
 */
public class JsonTool {

	/**
	 * 转换Json字符串：无格式、日期(数字)、非空对象
	 * 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		ValueFilter filter = new ValueFilter() {
			public Object process(Object object, String name, Object value) {
				if (value instanceof BigDecimal || value instanceof Double || value instanceof Float) {
					return new BigDecimal(value.toString());
				}
				return value;
			}
		};
		return JSON.toJSONString(obj, filter, new SerializerFeature[0]);
	}

	/**
	 * 转换Json字符串：无格式、日期(yyyy-MM-dd HH:mm:ss.SSS)、非空对象
	 * 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj, String dateFmt) {
		if (dateFmt == null) {
			dateFmt = "yyyy-MM-dd HH:mm:ss.SSS";
		}
		return getString(obj, dateFmt, false, false);
	}

	/**
	 * 自定义转换Json字符串：无格式、日期(数字)、包含空对象
	 * 
	 * @param obj
	 * @param dateFmt 日期格式
	 * @param isAllField 是否所有字段
	 * @param isFormatText 是否美化输出
	 * @return
	 */
	public static String getString(Object obj, String dateFmt, boolean isAllField, boolean isFormatText) {
		SerializeWriter out = new SerializeWriter();
		try {
			// QuoteFieldNames———-输出key时是否使用双引号,默认为true
			// WriteMapNullValue——–是否输出值为null的字段,默认为false
			// WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
			// WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
			// WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
			// WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
			// DisableCircularReferenceDetect禁用循环引用{"s1":{"age":16},"s2":{"$ref":"$.s1"}}
			JSONSerializer serializer = new JSONSerializer(out);
			serializer.config(SerializerFeature.DisableCircularReferenceDetect, true);
			if (isFormatText) {// 格式化json文本
				serializer.config(SerializerFeature.PrettyFormat, true);
				serializer.config(SerializerFeature.SortField, true);
			}
			if (dateFmt != null) {// 格式化日期
				serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
				serializer.setDateFormat(dateFmt);
			}
			if (isAllField) {// 所有字段
				serializer.config(SerializerFeature.WriteMapNullValue, true);
			}
			serializer.write(obj);
			return out.toString();
		} finally {
			out.close();
		}
	}

	/**
	 * 将json字符串转换成对象
	 * @param str
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public static <T> T getObj(String str, Class<T> cls) {
		if (str == null) {
			return null;
		}
		return JSON.parseObject(str, cls);
	}

	/**
	 * 将json字符串转成List
	 * 
	 * @param str
	 * @param cls
	 * @return
	 */
	public static <T> List<T> getList(String str, Class<T> cls) {
		if (str == null) {
			return null;
		}
		return JSON.parseArray(str, cls);
	}

	/**
	 * 将json字符串转成Map
	 * 
	 * @param str
	 * @param kCls
	 * @param vCls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> getMap(String str, Class<K> kCls, Class<V> vCls) {
		if (str == null) {
			return null;
		}
		return JSON.parseObject(str, Map.class);
	}

	/**
	 * 将字符串转换成包装类型
	 * 
	 * @param str
	 * @param typeRef
	 * @return
	 */
	public static <T> T getTypeRefObject(String str, TypeReference<T> typeRef) {
		if (str == null) {
			return null;
		}
		return JSON.parseObject(str, typeRef);
	}


	/**
	 * 将java对象转换成json字符串
	 *
	 * @param javaObj
	 * @return
	 */
	public static String getJsonStringFromObject(Object javaObj) {
		return JSON.toJSONString(javaObj);
	}

	/**
	 * 从json HASH表达式中获取一个map
	 *
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map getMapFromJsonString(String jsonString) {
		JSONObject jsonObject = JSON.parseObject(jsonString);
		Iterator keyIter = jsonObject.keySet().iterator();
		String key;
		Object value;
		Map valueMap = new HashMap();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}

	public static Map<String,Object> toMap(String jsonString) {
		JSONObject jsonObject = JSON.parseObject(jsonString);
		Iterator keyIter = jsonObject.keySet().iterator();
		String key;
		Object value;
		Map<String,Object> valueMap = new HashMap();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}

	/**
	 * 从Map对象得到Json字串
	 *
	 * @param map
	 * @return
	 */
	public static String getJsonStringFromMap(Map map) {
		return JSON.toJSONString(map);
	}

	/**
	 * 从json字串中得到相应java数组
	 *
	 * @param jsonString
	 *            like "[\"李斯\",100]"
	 * @return
	 */
	public static Object[] getObjectArrayFromJsonString(String jsonString) {
		return JSON.parseArray(jsonString).toArray();
	}

}
