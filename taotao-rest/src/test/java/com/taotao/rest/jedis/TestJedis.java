package com.taotao.rest.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 测试jedis链接redis服务
 * 
 * @author Wait
 *
 */
public class TestJedis {

	/**
	 * 创建Single连接redis服务
	 */
	@Test
	public void testJedisSingle() {

		// 创建jedis客户端
		Jedis jedis = new Jedis("192.168.127.66", 6379);
		// 设置数据
		jedis.set("name", "bar");
		// 获取数据
		String value = jedis.get("name");
		System.out.println(value);
		// 关闭连接
		jedis.close();
	}
	/**
	 * 通过Pool连接redis服务
	 */
	@Test
	public void testJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		//设置最大连接数
		config.setMaxTotal(30);
		//设置最大连接空闲数
		config.setMaxIdle(2);
		
		JedisPool pool = new JedisPool(config,"192.168.127.66", 6379);
		// 创建jedis客户端
		Jedis jedis = pool.getResource();
		// 设置数据
		jedis.set("name", "pool");
		// 获取数据
		String value = jedis.get("name");
		System.out.println(value);
		// 关闭连接
		jedis.close();
		pool.close();
	}
	/**
	 * 通过jedis连接redis集群服务
	 */
	@Test
	public void testJedisCluster() {
		//创建jedisCluster
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.127.66", 7001));
		nodes.add(new HostAndPort("192.168.127.66", 7002));
		nodes.add(new HostAndPort("192.168.127.66", 7003));
		nodes.add(new HostAndPort("192.168.127.66", 7004));
		nodes.add(new HostAndPort("192.168.127.66", 7005));
		nodes.add(new HostAndPort("192.168.127.66", 7006));
		// 创建jedis客户端
		JedisCluster cluster  = new JedisCluster(nodes);
		// 设置数据
		cluster.set("cluster", "test");
		// 获取数据
		String value = cluster.get("cluster");
		System.out.println(value);
		// 关闭连接
		cluster.close();
	}
}
