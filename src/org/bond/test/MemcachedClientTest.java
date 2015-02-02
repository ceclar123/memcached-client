package org.bond.test;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedClientTest {

	public static void main(String[] args) {
		try {
			/**
			 * 初始化SockIOPool，管理memcached的连接池
			 * */
			String[] servers = { "121.40.161.96:11211" };
			SockIOPool pool = SockIOPool.getInstance();
			pool.setServers(servers);
			pool.setFailover(true);
			pool.setInitConn(10);
			pool.setMinConn(5);
			pool.setMaxConn(250);
			pool.setMaintSleep(30);
			pool.setNagle(false);
			pool.setSocketTO(3000);
			pool.setAliveCheck(true);
			pool.initialize();

			/**
			 * 建立MemcachedClient实例
			 * */
			MemCachedClient memCachedClient = new MemCachedClient();
			for (int i = 0; i < 10; i++) {
				/**
				 * 将对象加入到memcached缓存
				 * */
				boolean success = memCachedClient.set("user_" + i, "Hello!");
				/**
				 * 从memcached缓存中按key值取对象
				 * */
				String result = (String) memCachedClient.get("user_" + i);
				System.out.println(String.format("set( %d ): %s", i, success));
				System.out.println(String.format("get( %d ): %s", i, result));
			}

			boolean b = memCachedClient.flushAll();
			if (true == b) {
				System.out.println("------删除成功------");
			}
			System.out.println("user_0:" + memCachedClient.get("user_0"));

			pool.shutDown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
