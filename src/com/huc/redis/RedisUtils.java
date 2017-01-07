package com.huc.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.fastjson.JSONObject;



/**
 * reids工具类
 * @author Administrator
 *
 */
public class RedisUtils {

    private static final Log log = LogFactory.getLog(RedisUtils.class);

    private static final String IP = "127.0.0.1";
    private static final int PORT = 6379;
    private static final String AUTH = "";
    private static int MAX_ACTIVE = 1024;
    private static int MAX_IDLE = 200;
    private static long MAX_WAIT	= 10000;
    private static int TIMEOUT = 10000;
    private static boolean BORROW = true;
    private static JedisPool pool = null;

    //域
    public static String USERINFO="user_info"; //用户信息

    /**
     * 初始化连接池
     */
    static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(BORROW);
        pool = new JedisPool(config, IP, PORT,TIMEOUT);
    }

    /***
     * 初始化链接
     * @return
     */
    public static synchronized Jedis getJedis(){
        try {
            if(pool != null){
                return pool.getResource();
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("连接池链接异常");
            return null;
        }
    }

    /***
     * 设置失效时间
     * @param key
     * @param seconds
     */
    public static void setTime(String key,int seconds){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            log.info("设置失效失败");
        }finally{
            getClose(jedis);
        }
    }

    /**
     * 新增对象
     * @param key
     * @param obj
     * @return
     */
    public static boolean addKeyword(String key,Object obj){
        Jedis jedis = null;
        String value = JSONObject.toJSONString(obj);
        try {
            jedis = getJedis();
            String code = jedis.set(key, value);
            if(code.equals("OK")){
                log.info("新增对象成功,key:"+key);
                return true;
            }
        } catch (Exception e) {
            log.info("添加数据异常");
            return false;
        }finally{
            getClose(jedis);
        }
        return false;
    }

    /**
     * 新增key~value
     * @param key
     * @param value
     * @return
     */
    public static boolean addKeyword(String key,String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String code = jedis.set(key, value);
            if(code.equals("OK")){
                log.info("新增对象成功,key:"+key);
                return true;
            }
        } catch (Exception e) {
            log.info("插入数据异常");
            return false;
        }finally{
            getClose(jedis);
        }
        return false;
    }

    /**
     * 获取key
     * @param key
     * @return
     */
    public static String getKeyword(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            log.info("获取key异常："+key);
        }
        return "";
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public static boolean delKey(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Long code = jedis.del(key);
            if(code>1){
                log.info("删除对象成功,key:"+key);
                return true;
            }
        } catch (Exception e) {
            log.info("删除key："+key+"异常");
            return false;
        }finally{
            getClose(jedis);
        }
        return false;
    }

    /**
     * 某一个域添加key
     * @param key
     * @param value
     */
    public static void hset(String key,String value,String group){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if(jedis.hexists(group, key)){
                jedis.hdel(group, key);
            }
            jedis.hset(group, key, value);
            log.info(" channelName:"+key+" 添加值："+value+"更新成功。");
        } catch (Exception e) {
            log.info(" channelName:"+group+" 添加参数:"+key+" 值："+value+"失败，稍后重新添加,异常信息:"+e.getMessage());
        }finally{
            getClose(jedis);
        }
    }

    /**
     * 从某一个域获取key
     * @param key
     * @return
     */
    public static String hget(String key,String group){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if(jedis==null){
                return "";
            }
            return jedis.hget(group, key);
        } catch (Exception e) {
            log.info(" channelName:"+group+" 读取参数:"+key+" 失败，稍后重新添加,异常信息:"+e.getMessage());
            e.printStackTrace();
        }finally{
            getClose(jedis);
        }
        return "";
    }

    /**
     * 删除某个域的key
     * @param key
     * @param group
     */
    public static void hdel(String key,String group){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hdel(group, key);
            log.info(" channelName:"+key+" 删除成功");
        } catch (Exception e) {
            log.info(" channelName:"+group+" 删除参数:"+key+" 失败，稍后重新添加,异常信息:"+e.getMessage());
        }finally{
            getClose(jedis);
        }
    }

    /**
     * 关闭链接
     * @param jedis
     */
    private static void getClose(Jedis jedis) {
        if(jedis != null){
            jedis.close();
        }
    }

}
