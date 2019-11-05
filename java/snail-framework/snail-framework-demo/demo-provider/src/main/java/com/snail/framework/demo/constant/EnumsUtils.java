package com.snail.framework.demo.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName：mgmt-provider
 * @ClassName：EnumsUtils @Description： 枚举工具类
 * @author：lrq
 * @Date：2019年9月20日 上午11:00:09
 * @version
 */

@Slf4j
public class EnumsUtils {

	private static Map<Class, Object> map = new ConcurrentHashMap();

	/**
	 * 根据条件获取枚举对象
	 * 
	 * @param className 枚举类
	 * @param predicate 筛选条件
	 * @param           <T>
	 * @return
	 */
	public static <T> Optional<T> getEnumObject(Class<T> className, Predicate<T> predicate) {
		if (!className.isEnum()) {
			log.info("Class 不是枚举类");
			return null;
		}
		Object obj = map.get(className);
		T[] ts = null;
		if (obj == null) {
			ts = className.getEnumConstants();
			map.put(className, ts);
		} else {
			ts = (T[]) obj;
		}
		return Arrays.stream(ts).filter(predicate).findAny();
	}

	public static void main(String[] args) {
		Optional<AwardTypeEnum> m = EnumsUtils.getEnumObject(AwardTypeEnum.class, e -> e.getCode().equals("1"));
		System.out.println( m.isPresent()?m.get().getMsg():null);
	}
	

}
