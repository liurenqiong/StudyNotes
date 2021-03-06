
package com.snail.framework.common.data.encode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @功能:编码配置
 * @作者:snail
 */
public class EncodeCfg {
	/** 方案数量 */
	public static final int kindsCount = 10;
	/** 手机号编码配置 */
	public static final List<ReplaceObj> telNoConfig;
	/** 身份证编码配置 */
	public static final Map<Integer, List<ReplaceObj>> idCardNoConfig;
	/** 银行卡编码配置 */
	public static final List<ReplaceObj> bankcardNoConfig;
	/** 邮件编码配置 */
	public static final List<ReplaceObj> emailConfig;
	/** 姓名编码配置 */
	public static final List<ReplaceObj> nameConfig;

	/** 手机号 */
	static {
		telNoConfig = new ArrayList<ReplaceObj>();
		telNoConfig.add(new ReplaceObj("0", "8", "6", "3", "5"));
		telNoConfig.add(new ReplaceObj("1", "9", "7", "4", "6"));
		telNoConfig.add(new ReplaceObj("2", "0", "8", "5", "7"));
		telNoConfig.add(new ReplaceObj("3", "1", "9", "6", "8"));
		telNoConfig.add(new ReplaceObj("4", "2", "0", "7", "9"));
		telNoConfig.add(new ReplaceObj("5", "3", "1", "8", "0"));
		telNoConfig.add(new ReplaceObj("6", "4", "2", "9", "1"));
		telNoConfig.add(new ReplaceObj("7", "5", "3", "0", "2"));
		telNoConfig.add(new ReplaceObj("8", "6", "4", "1", "3"));
		telNoConfig.add(new ReplaceObj("9", "7", "5", "2", "4"));
	}

	/** 身份证： */
	static {
		idCardNoConfig = new HashMap<Integer, List<ReplaceObj>>();
		List<ReplaceObj> temp = new ArrayList<ReplaceObj>();
		temp.add(new ReplaceObj("0", "4", "3", "2", "1"));
		temp.add(new ReplaceObj("1", "0", "4", "3", "2"));
		temp.add(new ReplaceObj("2", "1", "0", "4", "3"));
		temp.add(new ReplaceObj("3", "2", "1", "0", "4"));
		temp.add(new ReplaceObj("4", "3", "2", "1", "0"));
		idCardNoConfig.put(0, temp);// 身份证3-6位

		temp = new ArrayList<ReplaceObj>();// 奇偶对称
		temp.add(new ReplaceObj("0", "6", "4", "2", "8"));
		temp.add(new ReplaceObj("1", "7", "5", "3", "9"));
		temp.add(new ReplaceObj("2", "8", "6", "4", "0"));
		temp.add(new ReplaceObj("3", "9", "7", "5", "1"));
		temp.add(new ReplaceObj("4", "0", "8", "6", "2"));
		temp.add(new ReplaceObj("5", "1", "9", "7", "3"));
		temp.add(new ReplaceObj("6", "2", "0", "8", "4"));
		temp.add(new ReplaceObj("7", "3", "1", "9", "5"));
		temp.add(new ReplaceObj("8", "4", "2", "0", "6"));
		temp.add(new ReplaceObj("9", "5", "3", "1", "7"));
		idCardNoConfig.put(1, temp);
	}

	/** 银行卡号 */
	static {
		bankcardNoConfig = new ArrayList<ReplaceObj>();
		bankcardNoConfig.add(new ReplaceObj("0", "7", "4", "1", "6"));
		bankcardNoConfig.add(new ReplaceObj("1", "8", "5", "2", "7"));
		bankcardNoConfig.add(new ReplaceObj("2", "9", "6", "3", "8"));
		bankcardNoConfig.add(new ReplaceObj("3", "0", "7", "4", "9"));
		bankcardNoConfig.add(new ReplaceObj("4", "1", "8", "5", "0"));
		bankcardNoConfig.add(new ReplaceObj("5", "2", "9", "6", "1"));
		bankcardNoConfig.add(new ReplaceObj("6", "3", "0", "7", "2"));
		bankcardNoConfig.add(new ReplaceObj("7", "4", "1", "8", "3"));
		bankcardNoConfig.add(new ReplaceObj("8", "5", "2", "9", "4"));
		bankcardNoConfig.add(new ReplaceObj("9", "6", "3", "0", "5"));
	}

	/** 邮箱地址 */
	static {
		emailConfig = new ArrayList<ReplaceObj>();
		emailConfig.add(new ReplaceObj("0", "T", "K", "y", "d"));
		emailConfig.add(new ReplaceObj("1", "W", "M", "z", "f"));
		emailConfig.add(new ReplaceObj("2", "X", "P", "A", "g"));
		emailConfig.add(new ReplaceObj("3", "Y", "Q", "B", "h"));
		emailConfig.add(new ReplaceObj("4", "Z", "S", "C", "j"));
		emailConfig.add(new ReplaceObj("5", "0", "T", "D", "k"));
		emailConfig.add(new ReplaceObj("6", "1", "W", "F", "m"));
		emailConfig.add(new ReplaceObj("7", "2", "X", "G", "p"));
		emailConfig.add(new ReplaceObj("8", "3", "Y", "H", "q"));
		emailConfig.add(new ReplaceObj("9", "4", "Z", "J", "s"));
		emailConfig.add(new ReplaceObj("a", "5", "0", "K", "t"));
		emailConfig.add(new ReplaceObj("b", "6", "1", "M", "w"));
		emailConfig.add(new ReplaceObj("c", "7", "2", "P", "x"));
		emailConfig.add(new ReplaceObj("d", "8", "3", "Q", "y"));
		emailConfig.add(new ReplaceObj("f", "9", "4", "S", "z"));
		emailConfig.add(new ReplaceObj("g", "a", "5", "T", "A"));
		emailConfig.add(new ReplaceObj("h", "b", "6", "W", "B"));
		emailConfig.add(new ReplaceObj("j", "c", "7", "X", "C"));
		emailConfig.add(new ReplaceObj("k", "d", "8", "Y", "D"));
		emailConfig.add(new ReplaceObj("m", "f", "9", "Z", "F"));
		emailConfig.add(new ReplaceObj("p", "g", "a", "0", "G"));
		emailConfig.add(new ReplaceObj("q", "h", "b", "1", "H"));
		emailConfig.add(new ReplaceObj("s", "j", "c", "2", "J"));
		emailConfig.add(new ReplaceObj("t", "k", "d", "3", "K"));
		emailConfig.add(new ReplaceObj("w", "m", "f", "4", "M"));
		emailConfig.add(new ReplaceObj("x", "p", "g", "5", "P"));
		emailConfig.add(new ReplaceObj("y", "q", "h", "6", "Q"));
		emailConfig.add(new ReplaceObj("z", "s", "j", "7", "S"));
		emailConfig.add(new ReplaceObj("A", "t", "k", "8", "T"));
		emailConfig.add(new ReplaceObj("B", "w", "m", "9", "W"));
		emailConfig.add(new ReplaceObj("C", "x", "p", "a", "X"));
		emailConfig.add(new ReplaceObj("D", "y", "q", "b", "Y"));
		emailConfig.add(new ReplaceObj("F", "z", "s", "c", "Z"));
		emailConfig.add(new ReplaceObj("G", "A", "t", "d", "0"));
		emailConfig.add(new ReplaceObj("H", "B", "w", "f", "1"));
		emailConfig.add(new ReplaceObj("J", "C", "x", "g", "2"));
		emailConfig.add(new ReplaceObj("K", "D", "y", "h", "3"));
		emailConfig.add(new ReplaceObj("M", "F", "z", "j", "4"));
		emailConfig.add(new ReplaceObj("P", "G", "A", "k", "5"));
		emailConfig.add(new ReplaceObj("Q", "H", "B", "m", "6"));
		emailConfig.add(new ReplaceObj("S", "J", "C", "p", "7"));
		emailConfig.add(new ReplaceObj("T", "K", "D", "q", "8"));
		emailConfig.add(new ReplaceObj("W", "M", "F", "s", "9"));
		emailConfig.add(new ReplaceObj("X", "P", "G", "t", "a"));
		emailConfig.add(new ReplaceObj("Y", "Q", "H", "w", "b"));
		emailConfig.add(new ReplaceObj("Z", "S", "J", "x", "c"));
	}

	/** 姓名 */
	static {
		nameConfig = new ArrayList<ReplaceObj>();
		nameConfig.add(new ReplaceObj("a", "W", "J", "t", "Q"));
		nameConfig.add(new ReplaceObj("b", "X", "K", "w", "S"));
		nameConfig.add(new ReplaceObj("c", "Y", "M", "x", "T"));
		nameConfig.add(new ReplaceObj("d", "Z", "P", "y", "W"));
		nameConfig.add(new ReplaceObj("f", "a", "Q", "z", "X"));
		nameConfig.add(new ReplaceObj("g", "b", "S", "A", "Y"));
		nameConfig.add(new ReplaceObj("h", "c", "T", "B", "Z"));
		nameConfig.add(new ReplaceObj("j", "d", "W", "C", "a"));
		nameConfig.add(new ReplaceObj("k", "f", "X", "D", "b"));
		nameConfig.add(new ReplaceObj("m", "g", "Y", "F", "c"));
		nameConfig.add(new ReplaceObj("p", "h", "Z", "G", "d"));
		nameConfig.add(new ReplaceObj("q", "j", "a", "H", "f"));
		nameConfig.add(new ReplaceObj("s", "k", "b", "J", "g"));
		nameConfig.add(new ReplaceObj("t", "m", "c", "K", "h"));
		nameConfig.add(new ReplaceObj("w", "p", "d", "M", "j"));
		nameConfig.add(new ReplaceObj("x", "q", "f", "P", "k"));
		nameConfig.add(new ReplaceObj("y", "s", "g", "Q", "m"));
		nameConfig.add(new ReplaceObj("z", "t", "h", "S", "p"));
		nameConfig.add(new ReplaceObj("A", "w", "j", "T", "q"));
		nameConfig.add(new ReplaceObj("B", "x", "k", "W", "s"));
		nameConfig.add(new ReplaceObj("C", "y", "m", "X", "t"));
		nameConfig.add(new ReplaceObj("D", "z", "p", "Y", "w"));
		nameConfig.add(new ReplaceObj("F", "A", "q", "Z", "x"));
		nameConfig.add(new ReplaceObj("G", "B", "s", "a", "y"));
		nameConfig.add(new ReplaceObj("H", "C", "t", "b", "z"));
		nameConfig.add(new ReplaceObj("J", "D", "w", "c", "A"));
		nameConfig.add(new ReplaceObj("K", "F", "x", "d", "B"));
		nameConfig.add(new ReplaceObj("M", "G", "y", "f", "C"));
		nameConfig.add(new ReplaceObj("P", "H", "z", "g", "D"));
		nameConfig.add(new ReplaceObj("Q", "J", "A", "h", "F"));
		nameConfig.add(new ReplaceObj("S", "K", "B", "j", "G"));
		nameConfig.add(new ReplaceObj("T", "M", "C", "k", "H"));
		nameConfig.add(new ReplaceObj("W", "P", "D", "m", "J"));
		nameConfig.add(new ReplaceObj("X", "Q", "F", "p", "K"));
		nameConfig.add(new ReplaceObj("Y", "S", "G", "q", "M"));
		nameConfig.add(new ReplaceObj("Z", "T", "H", "s", "P"));
		nameConfig.add(new ReplaceObj("王", "富", "智", "茅", "奉"));
		nameConfig.add(new ReplaceObj("李", "尧", "幸", "利", "植"));
		nameConfig.add(new ReplaceObj("张", "闭", "奉", "於", "衡"));
		nameConfig.add(new ReplaceObj("刘", "由", "植", "呼", "富"));
		nameConfig.add(new ReplaceObj("陈", "王", "衡", "居", "尧"));
		nameConfig.add(new ReplaceObj("杨", "李", "富", "揭", "闭"));
		nameConfig.add(new ReplaceObj("黄", "张", "尧", "干", "由"));
		nameConfig.add(new ReplaceObj("吴", "刘", "闭", "但", "王"));
		nameConfig.add(new ReplaceObj("赵", "陈", "由", "尉", "李"));
		nameConfig.add(new ReplaceObj("周", "杨", "王", "冶", "张"));
		nameConfig.add(new ReplaceObj("徐", "黄", "李", "斯", "刘"));
		nameConfig.add(new ReplaceObj("孙", "吴", "张", "元", "陈"));
		nameConfig.add(new ReplaceObj("马", "赵", "刘", "束", "杨"));
		nameConfig.add(new ReplaceObj("朱", "周", "陈", "檀", "黄"));
		nameConfig.add(new ReplaceObj("胡", "徐", "杨", "衣", "吴"));
		nameConfig.add(new ReplaceObj("林", "孙", "黄", "信", "赵"));
		nameConfig.add(new ReplaceObj("郭", "马", "吴", "展", "周"));
		nameConfig.add(new ReplaceObj("何", "朱", "赵", "阴", "徐"));
		nameConfig.add(new ReplaceObj("高", "胡", "周", "昝", "孙"));
		nameConfig.add(new ReplaceObj("罗", "林", "徐", "智", "马"));
		nameConfig.add(new ReplaceObj("郑", "郭", "孙", "幸", "朱"));
		nameConfig.add(new ReplaceObj("梁", "何", "马", "奉", "胡"));
		nameConfig.add(new ReplaceObj("谢", "高", "朱", "植", "林"));
		nameConfig.add(new ReplaceObj("宋", "罗", "胡", "衡", "郭"));
		nameConfig.add(new ReplaceObj("唐", "郑", "林", "富", "何"));
		nameConfig.add(new ReplaceObj("许", "梁", "郭", "尧", "高"));
		nameConfig.add(new ReplaceObj("邓", "谢", "何", "闭", "罗"));
		nameConfig.add(new ReplaceObj("冯", "宋", "高", "由", "郑"));
		nameConfig.add(new ReplaceObj("韩", "唐", "罗", "王", "梁"));
		nameConfig.add(new ReplaceObj("曹", "许", "郑", "李", "谢"));
		nameConfig.add(new ReplaceObj("曾", "邓", "梁", "张", "宋"));
		nameConfig.add(new ReplaceObj("彭", "冯", "谢", "刘", "唐"));
		nameConfig.add(new ReplaceObj("萧", "韩", "宋", "陈", "许"));
		nameConfig.add(new ReplaceObj("蔡", "曹", "唐", "杨", "邓"));
		nameConfig.add(new ReplaceObj("潘", "曾", "许", "黄", "冯"));
		nameConfig.add(new ReplaceObj("田", "彭", "邓", "吴", "韩"));
		nameConfig.add(new ReplaceObj("董", "萧", "冯", "赵", "曹"));
		nameConfig.add(new ReplaceObj("袁", "蔡", "韩", "周", "曾"));
		nameConfig.add(new ReplaceObj("于", "潘", "曹", "徐", "彭"));
		nameConfig.add(new ReplaceObj("余", "田", "曾", "孙", "萧"));
		nameConfig.add(new ReplaceObj("叶", "董", "彭", "马", "蔡"));
		nameConfig.add(new ReplaceObj("蒋", "袁", "萧", "朱", "潘"));
		nameConfig.add(new ReplaceObj("杜", "于", "蔡", "胡", "田"));
		nameConfig.add(new ReplaceObj("苏", "余", "潘", "林", "董"));
		nameConfig.add(new ReplaceObj("魏", "叶", "田", "郭", "袁"));
		nameConfig.add(new ReplaceObj("程", "蒋", "董", "何", "于"));
		nameConfig.add(new ReplaceObj("吕", "杜", "袁", "高", "余"));
		nameConfig.add(new ReplaceObj("丁", "苏", "于", "罗", "叶"));
		nameConfig.add(new ReplaceObj("沈", "魏", "余", "郑", "蒋"));
		nameConfig.add(new ReplaceObj("任", "程", "叶", "梁", "杜"));
		nameConfig.add(new ReplaceObj("姚", "吕", "蒋", "谢", "苏"));
		nameConfig.add(new ReplaceObj("卢", "丁", "杜", "宋", "魏"));
		nameConfig.add(new ReplaceObj("傅", "沈", "苏", "唐", "程"));
		nameConfig.add(new ReplaceObj("钟", "任", "魏", "许", "吕"));
		nameConfig.add(new ReplaceObj("姜", "姚", "程", "邓", "丁"));
		nameConfig.add(new ReplaceObj("崔", "卢", "吕", "冯", "沈"));
		nameConfig.add(new ReplaceObj("谭", "傅", "丁", "韩", "任"));
		nameConfig.add(new ReplaceObj("廖", "钟", "沈", "曹", "姚"));
		nameConfig.add(new ReplaceObj("范", "姜", "任", "曾", "卢"));
		nameConfig.add(new ReplaceObj("汪", "崔", "姚", "彭", "傅"));
		nameConfig.add(new ReplaceObj("陆", "谭", "卢", "萧", "钟"));
		nameConfig.add(new ReplaceObj("金", "廖", "傅", "蔡", "姜"));
		nameConfig.add(new ReplaceObj("石", "范", "钟", "潘", "崔"));
		nameConfig.add(new ReplaceObj("戴", "汪", "姜", "田", "谭"));
		nameConfig.add(new ReplaceObj("贾", "陆", "崔", "董", "廖"));
		nameConfig.add(new ReplaceObj("韦", "金", "谭", "袁", "范"));
		nameConfig.add(new ReplaceObj("夏", "石", "廖", "于", "汪"));
		nameConfig.add(new ReplaceObj("邱", "戴", "范", "余", "陆"));
		nameConfig.add(new ReplaceObj("方", "贾", "汪", "叶", "金"));
		nameConfig.add(new ReplaceObj("侯", "韦", "陆", "蒋", "石"));
		nameConfig.add(new ReplaceObj("邹", "夏", "金", "杜", "戴"));
		nameConfig.add(new ReplaceObj("熊", "邱", "石", "苏", "贾"));
		nameConfig.add(new ReplaceObj("孟", "方", "戴", "魏", "韦"));
		nameConfig.add(new ReplaceObj("秦", "侯", "贾", "程", "夏"));
		nameConfig.add(new ReplaceObj("白", "邹", "韦", "吕", "邱"));
		nameConfig.add(new ReplaceObj("江", "熊", "夏", "丁", "方"));
		nameConfig.add(new ReplaceObj("阎", "孟", "邱", "沈", "侯"));
		nameConfig.add(new ReplaceObj("薛", "秦", "方", "任", "邹"));
		nameConfig.add(new ReplaceObj("尹", "白", "侯", "姚", "熊"));
		nameConfig.add(new ReplaceObj("段", "江", "邹", "卢", "孟"));
		nameConfig.add(new ReplaceObj("雷", "阎", "熊", "傅", "秦"));
		nameConfig.add(new ReplaceObj("黎", "薛", "孟", "钟", "白"));
		nameConfig.add(new ReplaceObj("史", "尹", "秦", "姜", "江"));
		nameConfig.add(new ReplaceObj("龙", "段", "白", "崔", "阎"));
		nameConfig.add(new ReplaceObj("陶", "雷", "江", "谭", "薛"));
		nameConfig.add(new ReplaceObj("贺", "黎", "阎", "廖", "尹"));
		nameConfig.add(new ReplaceObj("顾", "史", "薛", "范", "段"));
		nameConfig.add(new ReplaceObj("毛", "龙", "尹", "汪", "雷"));
		nameConfig.add(new ReplaceObj("郝", "陶", "段", "陆", "黎"));
		nameConfig.add(new ReplaceObj("龚", "贺", "雷", "金", "史"));
		nameConfig.add(new ReplaceObj("邵", "顾", "黎", "石", "龙"));
		nameConfig.add(new ReplaceObj("万", "毛", "史", "戴", "陶"));
		nameConfig.add(new ReplaceObj("钱", "郝", "龙", "贾", "贺"));
		nameConfig.add(new ReplaceObj("严", "龚", "陶", "韦", "顾"));
		nameConfig.add(new ReplaceObj("赖", "邵", "贺", "夏", "毛"));
		nameConfig.add(new ReplaceObj("覃", "万", "顾", "邱", "郝"));
		nameConfig.add(new ReplaceObj("洪", "钱", "毛", "方", "龚"));
		nameConfig.add(new ReplaceObj("武", "严", "郝", "侯", "邵"));
		nameConfig.add(new ReplaceObj("莫", "赖", "龚", "邹", "万"));
		nameConfig.add(new ReplaceObj("孔", "覃", "邵", "熊", "钱"));
		nameConfig.add(new ReplaceObj("汤", "洪", "万", "孟", "严"));
		nameConfig.add(new ReplaceObj("向", "武", "钱", "秦", "赖"));
		nameConfig.add(new ReplaceObj("常", "莫", "严", "白", "覃"));
		nameConfig.add(new ReplaceObj("温", "孔", "赖", "江", "洪"));
		nameConfig.add(new ReplaceObj("康", "汤", "覃", "阎", "武"));
		nameConfig.add(new ReplaceObj("施", "向", "洪", "薛", "莫"));
		nameConfig.add(new ReplaceObj("文", "常", "武", "尹", "孔"));
		nameConfig.add(new ReplaceObj("牛", "温", "莫", "段", "汤"));
		nameConfig.add(new ReplaceObj("樊", "康", "孔", "雷", "向"));
		nameConfig.add(new ReplaceObj("葛", "施", "汤", "黎", "常"));
		nameConfig.add(new ReplaceObj("邢", "文", "向", "史", "温"));
		nameConfig.add(new ReplaceObj("安", "牛", "常", "龙", "康"));
		nameConfig.add(new ReplaceObj("齐", "樊", "温", "陶", "施"));
		nameConfig.add(new ReplaceObj("易", "葛", "康", "贺", "文"));
		nameConfig.add(new ReplaceObj("乔", "邢", "施", "顾", "牛"));
		nameConfig.add(new ReplaceObj("伍", "安", "文", "毛", "樊"));
		nameConfig.add(new ReplaceObj("庞", "齐", "牛", "郝", "葛"));
		nameConfig.add(new ReplaceObj("颜", "易", "樊", "龚", "邢"));
		nameConfig.add(new ReplaceObj("倪", "乔", "葛", "邵", "安"));
		nameConfig.add(new ReplaceObj("庄", "伍", "邢", "万", "齐"));
		nameConfig.add(new ReplaceObj("聂", "庞", "安", "钱", "易"));
		nameConfig.add(new ReplaceObj("章", "颜", "齐", "严", "乔"));
		nameConfig.add(new ReplaceObj("鲁", "倪", "易", "赖", "伍"));
		nameConfig.add(new ReplaceObj("岳", "庄", "乔", "覃", "庞"));
		nameConfig.add(new ReplaceObj("翟", "聂", "伍", "洪", "颜"));
		nameConfig.add(new ReplaceObj("殷", "章", "庞", "武", "倪"));
		nameConfig.add(new ReplaceObj("詹", "鲁", "颜", "莫", "庄"));
		nameConfig.add(new ReplaceObj("申", "岳", "倪", "孔", "聂"));
		nameConfig.add(new ReplaceObj("欧", "翟", "庄", "汤", "章"));
		nameConfig.add(new ReplaceObj("耿", "殷", "聂", "向", "鲁"));
		nameConfig.add(new ReplaceObj("关", "詹", "章", "常", "岳"));
		nameConfig.add(new ReplaceObj("兰", "申", "鲁", "温", "翟"));
		nameConfig.add(new ReplaceObj("焦", "欧", "岳", "康", "殷"));
		nameConfig.add(new ReplaceObj("俞", "耿", "翟", "施", "詹"));
		nameConfig.add(new ReplaceObj("左", "关", "殷", "文", "申"));
		nameConfig.add(new ReplaceObj("柳", "兰", "詹", "牛", "欧"));
		nameConfig.add(new ReplaceObj("甘", "焦", "申", "樊", "耿"));
		nameConfig.add(new ReplaceObj("祝", "俞", "欧", "葛", "关"));
		nameConfig.add(new ReplaceObj("包", "左", "耿", "邢", "兰"));
		nameConfig.add(new ReplaceObj("宁", "柳", "关", "安", "焦"));
		nameConfig.add(new ReplaceObj("尚", "甘", "兰", "齐", "俞"));
		nameConfig.add(new ReplaceObj("符", "祝", "焦", "易", "左"));
		nameConfig.add(new ReplaceObj("舒", "包", "俞", "乔", "柳"));
		nameConfig.add(new ReplaceObj("阮", "宁", "左", "伍", "甘"));
		nameConfig.add(new ReplaceObj("柯", "尚", "柳", "庞", "祝"));
		nameConfig.add(new ReplaceObj("纪", "符", "甘", "颜", "包"));
		nameConfig.add(new ReplaceObj("梅", "舒", "祝", "倪", "宁"));
		nameConfig.add(new ReplaceObj("童", "阮", "包", "庄", "尚"));
		nameConfig.add(new ReplaceObj("凌", "柯", "宁", "聂", "符"));
		nameConfig.add(new ReplaceObj("毕", "纪", "尚", "章", "舒"));
		nameConfig.add(new ReplaceObj("单", "梅", "符", "鲁", "阮"));
		nameConfig.add(new ReplaceObj("季", "童", "舒", "岳", "柯"));
		nameConfig.add(new ReplaceObj("裴", "凌", "阮", "翟", "纪"));
		nameConfig.add(new ReplaceObj("霍", "毕", "柯", "殷", "梅"));
		nameConfig.add(new ReplaceObj("涂", "单", "纪", "詹", "童"));
		nameConfig.add(new ReplaceObj("成", "季", "梅", "申", "凌"));
		nameConfig.add(new ReplaceObj("苗", "裴", "童", "欧", "毕"));
		nameConfig.add(new ReplaceObj("谷", "霍", "凌", "耿", "单"));
		nameConfig.add(new ReplaceObj("盛", "涂", "毕", "关", "季"));
		nameConfig.add(new ReplaceObj("曲", "成", "单", "兰", "裴"));
		nameConfig.add(new ReplaceObj("翁", "苗", "季", "焦", "霍"));
		nameConfig.add(new ReplaceObj("冉", "谷", "裴", "俞", "涂"));
		nameConfig.add(new ReplaceObj("骆", "盛", "霍", "左", "成"));
		nameConfig.add(new ReplaceObj("蓝", "曲", "涂", "柳", "苗"));
		nameConfig.add(new ReplaceObj("路", "翁", "成", "甘", "谷"));
		nameConfig.add(new ReplaceObj("游", "冉", "苗", "祝", "盛"));
		nameConfig.add(new ReplaceObj("辛", "骆", "谷", "包", "曲"));
		nameConfig.add(new ReplaceObj("靳", "蓝", "盛", "宁", "翁"));
		nameConfig.add(new ReplaceObj("管", "路", "曲", "尚", "冉"));
		nameConfig.add(new ReplaceObj("柴", "游", "翁", "符", "骆"));
		nameConfig.add(new ReplaceObj("蒙", "辛", "冉", "舒", "蓝"));
		nameConfig.add(new ReplaceObj("鲍", "靳", "骆", "阮", "路"));
		nameConfig.add(new ReplaceObj("华", "管", "蓝", "柯", "游"));
		nameConfig.add(new ReplaceObj("喻", "柴", "路", "纪", "辛"));
		nameConfig.add(new ReplaceObj("祁", "蒙", "游", "梅", "靳"));
		nameConfig.add(new ReplaceObj("蒲", "鲍", "辛", "童", "管"));
		nameConfig.add(new ReplaceObj("房", "华", "靳", "凌", "柴"));
		nameConfig.add(new ReplaceObj("滕", "喻", "管", "毕", "蒙"));
		nameConfig.add(new ReplaceObj("屈", "祁", "柴", "单", "鲍"));
		nameConfig.add(new ReplaceObj("饶", "蒲", "蒙", "季", "华"));
		nameConfig.add(new ReplaceObj("解", "房", "鲍", "裴", "喻"));
		nameConfig.add(new ReplaceObj("牟", "滕", "华", "霍", "祁"));
		nameConfig.add(new ReplaceObj("艾", "屈", "喻", "涂", "蒲"));
		nameConfig.add(new ReplaceObj("尤", "饶", "祁", "成", "房"));
		nameConfig.add(new ReplaceObj("阳", "解", "蒲", "苗", "滕"));
		nameConfig.add(new ReplaceObj("时", "牟", "房", "谷", "屈"));
		nameConfig.add(new ReplaceObj("穆", "艾", "滕", "盛", "饶"));
		nameConfig.add(new ReplaceObj("农", "尤", "屈", "曲", "解"));
		nameConfig.add(new ReplaceObj("司", "阳", "饶", "翁", "牟"));
		nameConfig.add(new ReplaceObj("卓", "时", "解", "冉", "艾"));
		nameConfig.add(new ReplaceObj("古", "穆", "牟", "骆", "尤"));
		nameConfig.add(new ReplaceObj("吉", "农", "艾", "蓝", "阳"));
		nameConfig.add(new ReplaceObj("缪", "司", "尤", "路", "时"));
		nameConfig.add(new ReplaceObj("简", "卓", "阳", "游", "穆"));
		nameConfig.add(new ReplaceObj("车", "古", "时", "辛", "农"));
		nameConfig.add(new ReplaceObj("项", "吉", "穆", "靳", "司"));
		nameConfig.add(new ReplaceObj("连", "缪", "农", "管", "卓"));
		nameConfig.add(new ReplaceObj("芦", "简", "司", "柴", "古"));
		nameConfig.add(new ReplaceObj("麦", "车", "卓", "蒙", "吉"));
		nameConfig.add(new ReplaceObj("褚", "项", "古", "鲍", "缪"));
		nameConfig.add(new ReplaceObj("娄", "连", "吉", "华", "简"));
		nameConfig.add(new ReplaceObj("窦", "芦", "缪", "喻", "车"));
		nameConfig.add(new ReplaceObj("戚", "麦", "简", "祁", "项"));
		nameConfig.add(new ReplaceObj("岑", "褚", "车", "蒲", "连"));
		nameConfig.add(new ReplaceObj("景", "娄", "项", "房", "芦"));
		nameConfig.add(new ReplaceObj("党", "窦", "连", "滕", "麦"));
		nameConfig.add(new ReplaceObj("宫", "戚", "芦", "屈", "褚"));
		nameConfig.add(new ReplaceObj("费", "岑", "麦", "饶", "娄"));
		nameConfig.add(new ReplaceObj("卜", "景", "褚", "解", "窦"));
		nameConfig.add(new ReplaceObj("冷", "党", "娄", "牟", "戚"));
		nameConfig.add(new ReplaceObj("晏", "宫", "窦", "艾", "岑"));
		nameConfig.add(new ReplaceObj("席", "费", "戚", "尤", "景"));
		nameConfig.add(new ReplaceObj("卫", "卜", "岑", "阳", "党"));
		nameConfig.add(new ReplaceObj("米", "冷", "景", "时", "宫"));
		nameConfig.add(new ReplaceObj("柏", "晏", "党", "穆", "费"));
		nameConfig.add(new ReplaceObj("宗", "席", "宫", "农", "卜"));
		nameConfig.add(new ReplaceObj("瞿", "卫", "费", "司", "冷"));
		nameConfig.add(new ReplaceObj("桂", "米", "卜", "卓", "晏"));
		nameConfig.add(new ReplaceObj("全", "柏", "冷", "古", "席"));
		nameConfig.add(new ReplaceObj("佟", "宗", "晏", "吉", "卫"));
		nameConfig.add(new ReplaceObj("应", "瞿", "席", "缪", "米"));
		nameConfig.add(new ReplaceObj("臧", "桂", "卫", "简", "柏"));
		nameConfig.add(new ReplaceObj("闵", "全", "米", "车", "宗"));
		nameConfig.add(new ReplaceObj("苟", "佟", "柏", "项", "瞿"));
		nameConfig.add(new ReplaceObj("邬", "应", "宗", "连", "桂"));
		nameConfig.add(new ReplaceObj("边", "臧", "瞿", "芦", "全"));
		nameConfig.add(new ReplaceObj("卞", "闵", "桂", "麦", "佟"));
		nameConfig.add(new ReplaceObj("姬", "苟", "全", "褚", "应"));
		nameConfig.add(new ReplaceObj("师", "邬", "佟", "娄", "臧"));
		nameConfig.add(new ReplaceObj("和", "边", "应", "窦", "闵"));
		nameConfig.add(new ReplaceObj("仇", "卞", "臧", "戚", "苟"));
		nameConfig.add(new ReplaceObj("栾", "姬", "闵", "岑", "邬"));
		nameConfig.add(new ReplaceObj("隋", "师", "苟", "景", "边"));
		nameConfig.add(new ReplaceObj("商", "和", "邬", "党", "卞"));
		nameConfig.add(new ReplaceObj("刁", "仇", "边", "宫", "姬"));
		nameConfig.add(new ReplaceObj("沙", "栾", "卞", "费", "师"));
		nameConfig.add(new ReplaceObj("荣", "隋", "姬", "卜", "和"));
		nameConfig.add(new ReplaceObj("巫", "商", "师", "冷", "仇"));
		nameConfig.add(new ReplaceObj("寇", "刁", "和", "晏", "栾"));
		nameConfig.add(new ReplaceObj("桑", "沙", "仇", "席", "隋"));
		nameConfig.add(new ReplaceObj("郎", "荣", "栾", "卫", "商"));
		nameConfig.add(new ReplaceObj("甄", "巫", "隋", "米", "刁"));
		nameConfig.add(new ReplaceObj("丛", "寇", "商", "柏", "沙"));
		nameConfig.add(new ReplaceObj("仲", "桑", "刁", "宗", "荣"));
		nameConfig.add(new ReplaceObj("虞", "郎", "沙", "瞿", "巫"));
		nameConfig.add(new ReplaceObj("敖", "甄", "荣", "桂", "寇"));
		nameConfig.add(new ReplaceObj("巩", "丛", "巫", "全", "桑"));
		nameConfig.add(new ReplaceObj("明", "仲", "寇", "佟", "郎"));
		nameConfig.add(new ReplaceObj("佘", "虞", "桑", "应", "甄"));
		nameConfig.add(new ReplaceObj("池", "敖", "郎", "臧", "丛"));
		nameConfig.add(new ReplaceObj("查", "巩", "甄", "闵", "仲"));
		nameConfig.add(new ReplaceObj("麻", "明", "丛", "苟", "虞"));
		nameConfig.add(new ReplaceObj("苑", "佘", "仲", "邬", "敖"));
		nameConfig.add(new ReplaceObj("迟", "池", "虞", "边", "巩"));
		nameConfig.add(new ReplaceObj("邝", "查", "敖", "卞", "明"));
		nameConfig.add(new ReplaceObj("官", "麻", "巩", "姬", "佘"));
		nameConfig.add(new ReplaceObj("封", "苑", "明", "师", "池"));
		nameConfig.add(new ReplaceObj("谈", "迟", "佘", "和", "查"));
		nameConfig.add(new ReplaceObj("匡", "邝", "池", "仇", "麻"));
		nameConfig.add(new ReplaceObj("鞠", "官", "查", "栾", "苑"));
		nameConfig.add(new ReplaceObj("惠", "封", "麻", "隋", "迟"));
		nameConfig.add(new ReplaceObj("荆", "谈", "苑", "商", "邝"));
		nameConfig.add(new ReplaceObj("乐", "匡", "迟", "刁", "官"));
		nameConfig.add(new ReplaceObj("冀", "鞠", "邝", "沙", "封"));
		nameConfig.add(new ReplaceObj("郁", "惠", "官", "荣", "谈"));
		nameConfig.add(new ReplaceObj("胥", "荆", "封", "巫", "匡"));
		nameConfig.add(new ReplaceObj("南", "乐", "谈", "寇", "鞠"));
		nameConfig.add(new ReplaceObj("班", "冀", "匡", "桑", "惠"));
		nameConfig.add(new ReplaceObj("储", "郁", "鞠", "郎", "荆"));
		nameConfig.add(new ReplaceObj("原", "胥", "惠", "甄", "乐"));
		nameConfig.add(new ReplaceObj("栗", "南", "荆", "丛", "冀"));
		nameConfig.add(new ReplaceObj("燕", "班", "乐", "仲", "郁"));
		nameConfig.add(new ReplaceObj("楚", "储", "冀", "虞", "胥"));
		nameConfig.add(new ReplaceObj("鄢", "原", "郁", "敖", "南"));
		nameConfig.add(new ReplaceObj("劳", "栗", "胥", "巩", "班"));
		nameConfig.add(new ReplaceObj("谌", "燕", "南", "明", "储"));
		nameConfig.add(new ReplaceObj("奚", "楚", "班", "佘", "原"));
		nameConfig.add(new ReplaceObj("皮", "鄢", "储", "池", "栗"));
		nameConfig.add(new ReplaceObj("粟", "劳", "原", "查", "燕"));
		nameConfig.add(new ReplaceObj("冼", "谌", "栗", "麻", "楚"));
		nameConfig.add(new ReplaceObj("蔺", "奚", "燕", "苑", "鄢"));
		nameConfig.add(new ReplaceObj("楼", "皮", "楚", "迟", "劳"));
		nameConfig.add(new ReplaceObj("盘", "粟", "鄢", "邝", "谌"));
		nameConfig.add(new ReplaceObj("满", "冼", "劳", "官", "奚"));
		nameConfig.add(new ReplaceObj("闻", "蔺", "谌", "封", "皮"));
		nameConfig.add(new ReplaceObj("位", "楼", "奚", "谈", "粟"));
		nameConfig.add(new ReplaceObj("厉", "盘", "皮", "匡", "冼"));
		nameConfig.add(new ReplaceObj("伊", "满", "粟", "鞠", "蔺"));
		nameConfig.add(new ReplaceObj("仝", "闻", "冼", "惠", "楼"));
		nameConfig.add(new ReplaceObj("区", "位", "蔺", "荆", "盘"));
		nameConfig.add(new ReplaceObj("郜", "厉", "楼", "乐", "满"));
		nameConfig.add(new ReplaceObj("海", "伊", "盘", "冀", "闻"));
		nameConfig.add(new ReplaceObj("阚", "仝", "满", "郁", "位"));
		nameConfig.add(new ReplaceObj("花", "区", "闻", "胥", "厉"));
		nameConfig.add(new ReplaceObj("权", "郜", "位", "南", "伊"));
		nameConfig.add(new ReplaceObj("强", "海", "厉", "班", "仝"));
		nameConfig.add(new ReplaceObj("帅", "阚", "伊", "储", "区"));
		nameConfig.add(new ReplaceObj("屠", "花", "仝", "原", "郜"));
		nameConfig.add(new ReplaceObj("豆", "权", "区", "栗", "海"));
		nameConfig.add(new ReplaceObj("朴", "强", "郜", "燕", "阚"));
		nameConfig.add(new ReplaceObj("盖", "帅", "海", "楚", "花"));
		nameConfig.add(new ReplaceObj("练", "屠", "阚", "鄢", "权"));
		nameConfig.add(new ReplaceObj("廉", "豆", "花", "劳", "强"));
		nameConfig.add(new ReplaceObj("禹", "朴", "权", "谌", "帅"));
		nameConfig.add(new ReplaceObj("井", "盖", "强", "奚", "屠"));
		nameConfig.add(new ReplaceObj("祖", "练", "帅", "皮", "豆"));
		nameConfig.add(new ReplaceObj("漆", "廉", "屠", "粟", "朴"));
		nameConfig.add(new ReplaceObj("巴", "禹", "豆", "冼", "盖"));
		nameConfig.add(new ReplaceObj("丰", "井", "朴", "蔺", "练"));
		nameConfig.add(new ReplaceObj("支", "祖", "盖", "楼", "廉"));
		nameConfig.add(new ReplaceObj("卿", "漆", "练", "盘", "禹"));
		nameConfig.add(new ReplaceObj("国", "巴", "廉", "满", "井"));
		nameConfig.add(new ReplaceObj("狄", "丰", "禹", "闻", "祖"));
		nameConfig.add(new ReplaceObj("平", "支", "井", "位", "漆"));
		nameConfig.add(new ReplaceObj("计", "卿", "祖", "厉", "巴"));
		nameConfig.add(new ReplaceObj("索", "国", "漆", "伊", "丰"));
		nameConfig.add(new ReplaceObj("宣", "狄", "巴", "仝", "支"));
		nameConfig.add(new ReplaceObj("晋", "平", "丰", "区", "卿"));
		nameConfig.add(new ReplaceObj("相", "计", "支", "郜", "国"));
		nameConfig.add(new ReplaceObj("初", "索", "卿", "海", "狄"));
		nameConfig.add(new ReplaceObj("门", "宣", "国", "阚", "平"));
		nameConfig.add(new ReplaceObj("云", "晋", "狄", "花", "计"));
		nameConfig.add(new ReplaceObj("容", "相", "平", "权", "索"));
		nameConfig.add(new ReplaceObj("敬", "初", "计", "强", "宣"));
		nameConfig.add(new ReplaceObj("来", "门", "索", "帅", "晋"));
		nameConfig.add(new ReplaceObj("扈", "云", "宣", "屠", "相"));
		nameConfig.add(new ReplaceObj("晁", "容", "晋", "豆", "初"));
		nameConfig.add(new ReplaceObj("芮", "敬", "相", "朴", "门"));
		nameConfig.add(new ReplaceObj("都", "来", "初", "盖", "云"));
		nameConfig.add(new ReplaceObj("普", "扈", "门", "练", "容"));
		nameConfig.add(new ReplaceObj("阙", "晁", "云", "廉", "敬"));
		nameConfig.add(new ReplaceObj("浦", "芮", "容", "禹", "来"));
		nameConfig.add(new ReplaceObj("戈", "都", "敬", "井", "扈"));
		nameConfig.add(new ReplaceObj("伏", "普", "来", "祖", "晁"));
		nameConfig.add(new ReplaceObj("鹿", "阙", "扈", "漆", "芮"));
		nameConfig.add(new ReplaceObj("薄", "浦", "晁", "巴", "都"));
		nameConfig.add(new ReplaceObj("邸", "戈", "芮", "丰", "普"));
		nameConfig.add(new ReplaceObj("雍", "伏", "都", "支", "阙"));
		nameConfig.add(new ReplaceObj("辜", "鹿", "普", "卿", "浦"));
		nameConfig.add(new ReplaceObj("羊", "薄", "阙", "国", "戈"));
		nameConfig.add(new ReplaceObj("阿", "邸", "浦", "狄", "伏"));
		nameConfig.add(new ReplaceObj("乌", "雍", "戈", "平", "鹿"));
		nameConfig.add(new ReplaceObj("母", "辜", "伏", "计", "薄"));
		nameConfig.add(new ReplaceObj("裘", "羊", "鹿", "索", "邸"));
		nameConfig.add(new ReplaceObj("亓", "阿", "薄", "宣", "雍"));
		nameConfig.add(new ReplaceObj("修", "乌", "邸", "晋", "辜"));
		nameConfig.add(new ReplaceObj("邰", "母", "雍", "相", "羊"));
		nameConfig.add(new ReplaceObj("赫", "裘", "辜", "初", "阿"));
		nameConfig.add(new ReplaceObj("杭", "亓", "羊", "门", "乌"));
		nameConfig.add(new ReplaceObj("况", "修", "阿", "云", "母"));
		nameConfig.add(new ReplaceObj("那", "邰", "乌", "容", "裘"));
		nameConfig.add(new ReplaceObj("宿", "赫", "母", "敬", "亓"));
		nameConfig.add(new ReplaceObj("鲜", "杭", "裘", "来", "修"));
		nameConfig.add(new ReplaceObj("印", "况", "亓", "扈", "邰"));
		nameConfig.add(new ReplaceObj("逯", "那", "修", "晁", "赫"));
		nameConfig.add(new ReplaceObj("隆", "宿", "邰", "芮", "杭"));
		nameConfig.add(new ReplaceObj("茹", "鲜", "赫", "都", "况"));
		nameConfig.add(new ReplaceObj("诸", "印", "杭", "普", "那"));
		nameConfig.add(new ReplaceObj("战", "逯", "况", "阙", "宿"));
		nameConfig.add(new ReplaceObj("慕", "隆", "那", "浦", "鲜"));
		nameConfig.add(new ReplaceObj("危", "茹", "宿", "戈", "印"));
		nameConfig.add(new ReplaceObj("玉", "诸", "鲜", "伏", "逯"));
		nameConfig.add(new ReplaceObj("银", "战", "印", "鹿", "隆"));
		nameConfig.add(new ReplaceObj("亢", "慕", "逯", "薄", "茹"));
		nameConfig.add(new ReplaceObj("嵇", "危", "隆", "邸", "诸"));
		nameConfig.add(new ReplaceObj("公", "玉", "茹", "雍", "战"));
		nameConfig.add(new ReplaceObj("哈", "银", "诸", "辜", "慕"));
		nameConfig.add(new ReplaceObj("湛", "亢", "战", "羊", "危"));
		nameConfig.add(new ReplaceObj("宾", "嵇", "慕", "阿", "玉"));
		nameConfig.add(new ReplaceObj("戎", "公", "危", "乌", "银"));
		nameConfig.add(new ReplaceObj("勾", "哈", "玉", "母", "亢"));
		nameConfig.add(new ReplaceObj("茅", "湛", "银", "裘", "嵇"));
		nameConfig.add(new ReplaceObj("利", "宾", "亢", "亓", "公"));
		nameConfig.add(new ReplaceObj("於", "戎", "嵇", "修", "哈"));
		nameConfig.add(new ReplaceObj("呼", "勾", "公", "邰", "湛"));
		nameConfig.add(new ReplaceObj("居", "茅", "哈", "赫", "宾"));
		nameConfig.add(new ReplaceObj("揭", "利", "湛", "杭", "戎"));
		nameConfig.add(new ReplaceObj("干", "於", "宾", "况", "勾"));
		nameConfig.add(new ReplaceObj("但", "呼", "戎", "那", "茅"));
		nameConfig.add(new ReplaceObj("尉", "居", "勾", "宿", "利"));
		nameConfig.add(new ReplaceObj("冶", "揭", "茅", "鲜", "於"));
		nameConfig.add(new ReplaceObj("斯", "干", "利", "印", "呼"));
		nameConfig.add(new ReplaceObj("元", "但", "於", "逯", "居"));
		nameConfig.add(new ReplaceObj("束", "尉", "呼", "隆", "揭"));
		nameConfig.add(new ReplaceObj("檀", "冶", "居", "茹", "干"));
		nameConfig.add(new ReplaceObj("衣", "斯", "揭", "诸", "但"));
		nameConfig.add(new ReplaceObj("信", "元", "干", "战", "尉"));
		nameConfig.add(new ReplaceObj("展", "束", "但", "慕", "冶"));
		nameConfig.add(new ReplaceObj("阴", "檀", "尉", "危", "斯"));
		nameConfig.add(new ReplaceObj("昝", "衣", "冶", "玉", "元"));
		nameConfig.add(new ReplaceObj("智", "信", "斯", "银", "束"));
		nameConfig.add(new ReplaceObj("幸", "展", "元", "亢", "檀"));
		nameConfig.add(new ReplaceObj("奉", "阴", "束", "嵇", "衣"));
		nameConfig.add(new ReplaceObj("植", "昝", "檀", "公", "信"));
		nameConfig.add(new ReplaceObj("衡", "智", "衣", "哈", "展"));
		nameConfig.add(new ReplaceObj("富", "幸", "信", "湛", "阴"));
		nameConfig.add(new ReplaceObj("尧", "奉", "展", "宾", "昝"));
		nameConfig.add(new ReplaceObj("闭", "植", "阴", "戎", "智"));
		nameConfig.add(new ReplaceObj("由", "衡", "昝", "勾", "幸"));
	}
}
