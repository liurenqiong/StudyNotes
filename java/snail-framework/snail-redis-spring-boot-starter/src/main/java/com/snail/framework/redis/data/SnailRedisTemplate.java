package com.snail.framework.redis.data;

import com.snail.framework.redis.util.JsonTool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ 功能:dao基类
 * @作者:snail
 */
@Slf4j
public class SnailRedisTemplate implements IRedisTemplate {

	/** redis连接池 */
	protected ShardedJedisPool shardedJedisPool;


    private static final int EXPIRE_LOCK_DEFAULT = 30;//锁默认30秒

    /** 分布式锁创建失败时的等待间隔，单位：毫秒 */
    public static final int LOCK_INTERVAL = 500;
    /**
	 * 查询对象是否存
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public boolean exists(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.exists(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 保存或更新对象
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	@Override
	public long saveOrUpdate(String key, Object obj) {
		return saveOrUpdate(key, obj, 0);
	}

	/**
	 * 设置对象过期时间
	 * 
	 * @param key
	 * @param expSecond
	 * @return 单位秒
	 */
	public long expire(String key, int expSecond) {
		return saveOrUpdate(key, null, expSecond);
	}

	/**
	 * 保存或更新对象,同时更新对象的失效时间,expSecond>0时才设置失效时间
	 * 
	 * @param key
	 * @param obj
	 * @param expSecond 单位秒
	 * @return
	 */
	@Override
	public long saveOrUpdate(String key, Object obj, int expSecond) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			long result = 0;
			if (obj != null) {
				jedis.set(key, JsonTool.getString(obj));
				result = 1;
			}
			if (expSecond > 0) {
				result = jedis.expire(key, expSecond);
			}
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 删除对象:对象不存在，也返回true
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public long delete(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.del(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 根据key取得对象
	 * 
	 * @param key
	 * @param cls
	 * @return
	 */
	@Override
	public <T> T get(String key, Class<T> cls) {
		return get(key, cls, 0);
	}
	
	/** 
     * 获取List列表 
     * @param key 
     * @param start long，开始索引 
     * @param end long， 结束索引 
     * @return List<String> 
     */  
    public List<String> lrange(String key, long start, long end){
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();  
            List<String> list = jedis.lrange(key, start, end);
            return list; 
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    } 

	/**
	 * 根据key取得对象,同时更新对象的失效时间,expSecond>0时才设置失效时间
	 * 
	 * @param key
	 * @param cls
	 * @param expSecond 单位秒
	 * @return
	 */
	@Override
	public <T> T get(String key, Class<T> cls, int expSecond) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			String str = jedis.get(key);
			if (str != null) {
				if (expSecond > 0) {
					jedis.expire(key, expSecond);
				}
				return JsonTool.getObj(str, cls);
			}
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 操作HashMap：保存或更新值
	 * 
	 * @param storekey
	 * @param mapKey
	 * @param obj
	 * @return
	 */
	public long saveOrUpdateMapVal(String storekey, String mapKey, Object obj) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hset(storekey, mapKey, JsonTool.getString(obj));
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 操作HashMap：取得值
	 * 
	 * @param storekey
	 * @param mapKey
	 * @param cls
	 * @return
	 */
	public <T> T getMapVal(String storekey, String mapKey, Class<T> cls) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			String str = jedis.hget(storekey, mapKey);
			if (str != null) {
				return JsonTool.getObj(str, cls);
			}
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 队列处理:从左将对象压入队列中
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public long leftPush(String key, Object obj) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.lpush(key, JsonTool.getString(obj));
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 队列处理:从右取出对象
	 * 
	 * @param key
	 * @param
	 * @return
	 */
	public <T> T rightPop(String key, Class<T> cls) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			String str = jedis.rpop(key);
			if (str != null) {
				return JsonTool.getObj(str, cls);
			}
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

    /**
     * 队列处理:从右取出字符串
     *
     * @param key
     * @param
     * @return
     */
    public String rightPop(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
           return jedis.rpop(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 队列处理:从右取出字符串(阻塞)
     *
     * @param key
     * @param
     * @return
     */
    public String bRightPop(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            List<String> values = jedis.brpop(0 , key);
            return (values != null && values.size() >0) ? values.get(1) : null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 队列处理:删除队列中value值
     *
     * @param key
     * @param
     * @return
     */
    public long lrem(String key , String value) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.lrem(key , 0 , value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

	/**
	 * 队列处理:从右取出对象(阻塞)
	 *
	 * @param key
	 * @param cls
	 * @return
	 */
	public <T> T bRightPop(String key, Class<T> cls) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			List<String> list = jedis.brpop(key);
			if (list != null && list.size() > 0) {
				return JsonTool.getObj(list.get(0), cls);
			}
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}


	/**
	 * 设置对象过期
	 *
	 * @param key
	 * @param expSecond
	 * @param timeUnit
	 * @return
	 */
	@Override
	public boolean expire(String key, int expSecond, TimeUnit timeUnit) {
		return expire(key , expSecond) > 0;
	}

	/**
	 * 加锁
	 *
	 * @param lockKey
	 * @param unit
	 * @param leaseTime
	 */
	@Override
	public boolean lock(String lockKey, TimeUnit unit, int leaseTime) {
        return this.lock(lockKey , unit , leaseTime , leaseTime , false);
	}

	/**
	 * 尝试获取锁
	 *
	 * @param lockKey
	 * @param unit
	 * @param waitTime
	 * @param leaseTime
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws Exception {
        return this.lock(lockKey , unit , waitTime , leaseTime , true);
	}

	/**
	 * 释放锁
	 *
	 * @param lockKey
	 */
	@Override
	public void unlock(String lockKey) {
        this.delete(lockKey);
	}

    /**
     * 加锁
     * @param lockKey
     * @param unit
     * @param waitTime
     * @param leaseTime
     * @param isTry
     * @return
     */
	private boolean lock(String lockKey, TimeUnit unit, int waitTime, int leaseTime , boolean isTry) {
        boolean lockResult = false;
        long expireMillis = TimeUnit.SECONDS.toMillis(waitTime);
        long mill = System.currentTimeMillis();
        ShardedJedis jedis = null;
        String createTime;//锁创建时间
        long existTime;//锁存在时间
        long maxExistTime = EXPIRE_LOCK_DEFAULT * 2000;//锁最大存在时间
        do {
            jedis = shardedJedisPool.getResource();
            Long ret = jedis.setnx(lockKey, String.valueOf(System.currentTimeMillis()));
            if (null != ret && 1 == ret.intValue()) {
                // 锁成功
                jedis.expire(lockKey, leaseTime);
                log.info("创建锁，key＝{}，expire＝{}", lockKey, leaseTime);
                lockResult = true;
            } else {
                if (jedis.ttl(lockKey) == -1) {//处理服务停止，但是过期时间还未设置情况
                    createTime = jedis.get(lockKey);
                    if (null != createTime) {
                        existTime = System.currentTimeMillis() - Long.parseLong(createTime);
                        if (existTime > maxExistTime) {
                            jedis.expire(lockKey, leaseTime);
                            log.warn("锁存在时间过长,重新设置生存时间,key={},existTime={}", lockKey, existTime);
                        }
                    }
                }
                log.debug("锁存在，key＝{}", lockKey);
                try {
                    TimeUnit.MILLISECONDS.sleep(LOCK_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (null != jedis) {
                jedis.close();
            }
        } while (isTry && !lockResult
                && (System.currentTimeMillis() - mill) < expireMillis);

        // 超时
        if (!lockResult) {
            log.info("创建锁失败，key＝{}", lockKey);
        }
        return lockResult;
    }

	/**
	 * 取得jedis对象
	 * 
	 * @return
	 */
	public ShardedJedis getShardedJedis() {
		return shardedJedisPool.getResource();
	}

	/** =============== 当DAO层方法需要传递map参数时 E只是用来定位namespace的 =============== **/
	/**
	 * @设置 redis连接池
	 */
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {

		this.shardedJedisPool = shardedJedisPool;
	}

	/**
	 * 排名初初始化数据
	 *
	 * @param key
	 * @param num
	 * @param member
	 * @return
	 */
	@Override
	public Long zadd(String key, double num, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zadd(key , num , member);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 倒叙获取set列表
	 *
	 * @param key
	 * @param start
	 * @param stop
	 * @return
	 */
	@Override
	public Set<String> zrevrange(String key, int start, int stop) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrevrange(key , start , stop);

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 倒叙获取set列表 , 获取member 和 score
	 *
	 * @param key   map key
	 * @param start 开始值 获取全量用 0
	 * @param stop  结束值 获取全量用 -1
	 * @return
	 */
	@Override
	public List<Element> zrevrangeWithScores(String key, int start, int stop) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return convert(jedis.zrevrangeWithScores(key , start , stop));
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 给指定的排行榜加分数
	 *
	 * @param key    map key
	 * @param num    指定的票数或者分数
	 * @param member 成员
	 * @return
	 */
	@Override
	public Double zincrby(String key, double num, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zincrby(key , num , member);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 移除有序集合中的一个或多个成员
	 *
	 * @param key    map key
	 * @param member 成员
	 * @return
	 */
	@Override
	public Long zrem(String key , String... member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrem(key , member);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	private List<Element> convert(Set<Tuple> tuples) {
		List<Element> set = new ArrayList<>();
		Iterator<Tuple> iterator = tuples.iterator();
		while (iterator.hasNext()) {
			Tuple tuple = iterator.next();
			Element element = new Element();
			element.setMember(tuple.getElement());
			element.setScore(tuple.getScore());
			set.add(element);
		}
		return set;
	}
}
