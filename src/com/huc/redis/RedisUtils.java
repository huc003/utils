package com.huc.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.fastjson.JSONObject;



/**
 * reids������
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
	
	//��
	public static String USERINFO="user_info"; //�û���Ϣ
	
	/**
	 * ��ʼ�����ӳ�
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
	 * ��ʼ������
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
			log.info("���ӳ������쳣");
			return null;
		}
	}
	
	/***
	 * ����ʧЧʱ��
	 * @param key
	 * @param seconds
	 */
	public static void setTime(String key,int seconds){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			log.info("����ʧЧʧ��");
		}finally{
			getClose(jedis);
		}
	}
	
	/**
	 * ��������
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
				log.info("��������ɹ�,key:"+key);
				return true;
			}
		} catch (Exception e) {
			log.info("��������쳣");
			return false;
		}finally{
			getClose(jedis);
		}
		return false;
	}
	
	/**
	 * ����key~value
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
				log.info("��������ɹ�,key:"+key);
				return true;
			}
		} catch (Exception e) {
			log.info("���������쳣");
			return false;
		}finally{
			getClose(jedis);
		}
		return false;
	}
	
	/**
	 * ��ȡkey
	 * @param key
	 * @return
	 */
	public static String getKeyword(String key){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			log.info("��ȡkey�쳣��"+key);
		}
		return "";
	}
	
	/**
	 * ɾ��key
	 * @param key
	 * @return
	 */
	public static boolean delKey(String key){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Long code = jedis.del(key);
			if(code>1){
				log.info("ɾ������ɹ�,key:"+key);
				return true;
			}
		} catch (Exception e) {
			log.info("ɾ��key��"+key+"�쳣");
			return false;
		}finally{
			getClose(jedis);
		}
		return false;
	}
	
	/**
	 * ĳһ�������key
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
			log.info(" channelName:"+key+" ���ֵ��"+value+"���³ɹ���");
		} catch (Exception e) {
			log.info(" channelName:"+group+" ��Ӳ���:"+key+" ֵ��"+value+"ʧ�ܣ��Ժ��������,�쳣��Ϣ:"+e.getMessage());
		}finally{
			getClose(jedis);
		}
	}
	
	/**
	 * ��ĳһ�����ȡkey
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
			log.info(" channelName:"+group+" ��ȡ����:"+key+" ʧ�ܣ��Ժ��������,�쳣��Ϣ:"+e.getMessage());
			e.printStackTrace();
		}finally{
			getClose(jedis);
		}
		return "";
	}
	
	/**
	 * ɾ��ĳ�����key
	 * @param key
	 * @param group
	 */
	public static void hdel(String key,String group){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hdel(group, key);
			log.info(" channelName:"+key+" ɾ���ɹ�");
		} catch (Exception e) {
			log.info(" channelName:"+group+" ɾ������:"+key+" ʧ�ܣ��Ժ��������,�쳣��Ϣ:"+e.getMessage());
		}finally{
			getClose(jedis);
		}
	}

	/**
	 * �ر�����
	 * @param jedis
	 */
	private static void getClose(Jedis jedis) {
		if(jedis != null){
			jedis.close();
		}
	}
	
}
