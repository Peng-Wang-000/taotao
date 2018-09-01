package com.taotao.rest.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
public class TestSpringJedis {

	/**
	 * 单机版测试redis
	 */
	@Test
	public void testSpringJedisSingle(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("jedisPool");
		Jedis jedis = pool.getResource();
		jedis.set("name", "xiaoming");
		String value = jedis.get("name");
		System.out.println(value);
		jedis.close();
		pool.close();
	}
	/**
	 * 集群版测试redis
	 */
	@Test
	public void testSpringJedisCluster(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster) applicationContext.getBean("redisCluster");
		cluster.set("name", "xiaoming");
		String value = cluster.get("name");
		System.out.println(value);
		cluster.close();
	}
}
