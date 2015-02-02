package org.bond.test;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

public class SpyMemcachedTest {

	public static void main(String[] args) {
		try {
			/* 建立MemcachedClient 实例，并指定memcached服务的IP地址和端口号 */
			MemcachedClient mc = new MemcachedClient(new InetSocketAddress(
					"121.40.161.96", 11211));
			Future<Boolean> b = null;
			/* 将key值，过期时间(秒)和要缓存的对象set到memcached中 */
			b = mc.set("user_name_001", 900, "张三");
			if (b.get().booleanValue() == true) {
				// mc.shutdown();
				System.out.println("------添加成功------");
			}

			/* 按照key值从memcached中查找缓存，不存在则返回null */
			Object obj = mc.get("user_name_001");
			System.out.println("user_name_001:" + obj);

			b = mc.delete("user_name_001");
			if (b.get().booleanValue() == true) {
				// mc.shutdown();
				System.out.println("------删除成功------");
			}
			obj = mc.get("user_name_001");
			System.out.println("user_name_001:" + obj);
			mc.shutdown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
