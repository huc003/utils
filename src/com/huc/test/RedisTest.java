package com.huc.test;

import java.util.LinkedHashMap;
import java.util.Map;

import com.huc.redis.RedisUtils;

public class RedisTest {
	public static void main(String[] args) {
//		addkeyword("user","hucheng");
//		delKey("user");
//		getKeyword("user");
//		addObject();
//		setTime("user-obj",5);
//		getKeyword("user-obj");
//		hset("username_"+1274525,"hucheng");
//		hget("username_"+2);
//		hdel("username"+2);
		
	}
	
	//添加key
	public static void addkeyword(String key,String value){
		RedisUtils.addKeyword(key, value);
	}
	
	//添加对象
	public static void addObject(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("user", "zhangsan");
		RedisUtils.addKeyword("user-obj", map);
	}
	
	//获得key
	public static void getKeyword(String key){
		System.out.println(RedisUtils.getKeyword(key));
	}
	
	//删除key
	public static void delKey(String key){
		RedisUtils.delKey(key);
	}
	
	//设置失效时间
	public static void setTime(String key,int seconds){
		RedisUtils.setTime(key, seconds);
	}
	
	//在某个域新增key
	public static void hset(String key,String value){
		RedisUtils.hset(key, value,RedisUtils.USERINFO);
	}
	
	//从某个域获取key
	public static void hget(String key){
		System.out.println(RedisUtils.hget(key,RedisUtils.USERINFO));
	}
	
	//删除某个域的key
	public static void hdel(String key){
		RedisUtils.hdel(key, RedisUtils.USERINFO);
	}
	
}
