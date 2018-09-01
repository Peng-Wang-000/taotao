package com.taotao.order.dao;

public interface JedisClient {

	public String get(String key);
	public String set(String key,String value);
	public String hget(String hkey,String key);
	public long hset(String hkey,String key,String value);
	
	public long hdel(String hkey,String key);
	public long incr(String key);
	/**
	 * 设置生命周期
	 * @param key
	 * @param seconds
	 * @return
	 */
	public long expire(String key,int seconds);
	/**
	 * 查看生命周期剩余时间
	 * @param key
	 * @return
	 */
	public long ttl(String key);
	
	
}
