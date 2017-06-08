package com.yc.utils.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;

public class Codis {
    @Autowired
    private static JedisPool pool;

    private static Logger logger = LogManager.getLogger(Codis.class);
    
    
    public static boolean set(String key, String value) {
        Jedis jedis = pool.getResource();
        try {
            String result = jedis.set(key, value);
            if (result.equals("OK")) {
                return true;
            }
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public static boolean set(String key, int expire, String value) {
        Jedis jedis = pool.getResource();
        try {
            String result = jedis.setex(key, expire, value);
            if (result.equals("OK")) {
                return true;
            }
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public static String get(String key) {
        Jedis jedis = pool.getResource();
        String result = null;
        try {
            result = jedis.get(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return result;
    }

    public static Set<String> keys(String pattern) {
        Jedis jedis = pool.getResource();
        Set<String> result = null;
        try {
            result = jedis.keys(pattern);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return result;
    }

    public static long del(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.del(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return 0;
    }


    public static boolean exists(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return false;
    }

    /**
     * 头入队列
     *
     * @param key
     * @param value
     * @return
     */
    public static Long lpush(String key, String value) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * 未入队列
     *
     * @param key
     * @param value
     * @return
     */
    public static Long rpush(String key, String value) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.rpush(key, value);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * 从左出队列
     *
     * @param key
     * @return
     */
    public static String lpop(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.lpop(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * 从右出队列
     *
     * @param key
     * @return
     */
    public static String rpop(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.rpop(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * lrange quue length
     *
     * @param key
     * @return
     */
    public static Long llen(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.llen(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * 有序集合
     *
     * @param key
     * @param value
     * @return
     */
    public static void addSet(String key, String value) {
        Jedis jedis = pool.getResource();
        try {

            Long score = jedis.zcard(key);
            jedis.zadd(key, ++score, value);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
    }

    /**
     * 得到有序集合
     *
     * @param key
     * @return
     */
    public static Set<String> getSet(String key) {
        Jedis jedis = pool.getResource();
        try {

            Set<String> sets = jedis.zrange(key, 0, -1);
            return sets;
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * 得到有序集合
     *
     * @param key
     * @return
     */
    public static String getSet(String key, String value) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.getSet(key, value);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    public static String loadScript(String script) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.scriptLoad(script);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 分页得到有序集合
     *
     * @param key
     * @param size
     * @return
     */
    public static Set<String> zrange(String key, int size) {
        Jedis jedis = pool.getResource();
        try {
            Set<String> sets = jedis.zrange(key, 0, size);
            return sets;
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * 删除指定的元素
     *
     * @param key
     * @param values
     * @return
     */
    public static Long zrem(String key, String... values) {
        Jedis jedis = pool.getResource();
        try {
            Long count = jedis.zrem(key, values);
            return count;
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return 0l;
        } finally {
            jedis.close();
        }
    }

    public static Map<String, String> hget(String key) {
        Jedis jedis = pool.getResource();

        Map<String, String> map = null;
        try {
            map = jedis.hgetAll(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return map;
    }

    public static boolean hset(String key, String field, String fieldVal) {
        Jedis jedis = pool.getResource();
        try {
            jedis.hset(key, field, fieldVal);
            return true;
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public static boolean hset(String key, Map<String, String> map) {
        Jedis jedis = pool.getResource();
        try {
            for (Map.Entry<String, String> e : map.entrySet()) {
                jedis.hset(key, e.getKey(), e.getValue());
            }
            return true;
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public static boolean hset(String key, Map<String, String> map, int exprise) {
        Jedis jedis = pool.getResource();
        try {
            for (Map.Entry<String, String> e : map.entrySet()) {
                jedis.hset(key, e.getKey(), e.getValue());
            }
            jedis.expire(key, exprise);
            return true;
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return false;
    }

    /**
     * 批量更新codis
     *
     * @param keys
     * @param maps
     * @return
     */
    public static boolean batchHset(List<String> keys, List<Map<String, String>> maps) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            int index = 0;
            for (String key : keys) {
                pipeline.hmset(key, maps.get(index++)); //key -map
            }
            if (index % keys.size() == 0) {
                pipeline.sync();
            }
            return true;
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            if (jedis != null) {
                jedis.disconnect();
                jedis.close();
            }
        }
        return false;
    }

    public static long incr(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.incr(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1L;
    }

    public static long decr(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.decr(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1L;
    }
    public static long decrBy(String key,long count) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.decrBy(key,count);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1L;
    }
    public static long setnx(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.setnx(key, "1");
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1L;
    }

    public static long setnx(String key, String value) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.setnx(key, value);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1L;
    }

    public static long setnx(String key, int expire, String value) {
        Jedis jedis = pool.getResource();
        try {

            if ("OK".equals(jedis.set(key, value, "NX", "EX", expire))) {
                return 1;
            }
            return 0;

        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1L;
    }


    public static void expire(String key, int second) {
        Jedis jedis = pool.getResource();
        try {
            jedis.expire(key, second);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
    }

    public static long sadd(String key, String... members) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.sadd(key, members);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1L;
    }

    public static long scard(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1L;
    }

    public static Set<String> smembers(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.smembers(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return null;
    }

    public static long srem(String redisKey, String... strings) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.srem(redisKey, strings);
        } catch (Exception e) {
            logger.error("codis异常:", e);
        } finally {
            jedis.close();
        }
        return -1l;
    }

    public static long zadd(String key, double score, String value) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.zadd(key, score, value);
        } catch (Exception e) {
            logger.error("-----zadd: {}", e);
        } finally {
            jedis.close();
        }
        return -1;
    }

    public static double zscore(String key, String value) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.zscore(key, value);
        }  catch (Exception e) {
            logger.error("zrevrank: ");
        } finally {
            jedis.close();
        }
        return -1;
    }

    public static double zrevrank(String key, String value) {
        Jedis jedis = pool.getResource();
        try {
            jedis.zrevrank(key, value);
            return jedis.zrevrank(key, value);
        } catch (Exception e) {
            logger.error("zrevrank: ");
        } finally {
            jedis.close();
        }
        return -1;
    }

    public static Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            logger.error("zrevrank: ");
        } finally {
            jedis.close();
        }
        return Sets.newHashSet();
    }

    public static Set<String> zrange(String key, long start, long end) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
            logger.error("zrevrank: ");
        } finally {
            jedis.close();
        }
        return Sets.newHashSet();
    }

    public static Long zdel(String key, long start, long end) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.zremrangeByRank(key, start, end);
        } catch (Exception e) {
            logger.error("zrevrank: ");
        } finally {
            jedis.close();
        }
        return 0l;
    }

    public static long zcard(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.zcard(key);
        } catch (Exception e) {
            logger.error("zcard exception:");
        }  finally {
            jedis.close();
        }
        return 0;
    }

    public static long zrem(String key, String value) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.zrem(key, value);
        } catch (Exception e) {
            logger.error("zcard exception:");
        } finally {
            jedis.close();
        }
        return 0;
    }

    public static Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.zrevrangeWithScores(key, start, end);
        }catch (Exception e) {
            logger.error("zrevrangeWithScores exception");
        } finally {
            jedis.close();
        }
        return Sets.newHashSet();
    }


    public static String srandmember(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.srandmember(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    public static String spop(String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.spop(key);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    public static boolean sismember(String key,String member) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.sismember(key,member);
        } catch (Exception e) {
            logger.error("codis异常:", e);
            return false;
        } finally {
            jedis.close();
        }
    }
}
