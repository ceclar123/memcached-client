package org.bond.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.bond.xmemcached.XMemcachedHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmemcachedTest {

	public static void main(String[] args) {
		test2();
	}

	/*
	 * 采用spring 方式
	 */
	private static void test2() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring-xmemcached.xml");

		XMemcachedHelper helper = appContext.getBean(XMemcachedHelper.class);

		helper.set("hello", "Hello,xmemcached");

		String value = (String) helper.get("hello");
		System.out.println("hello=" + value);

		helper.delete("hello");
		value = (String) helper.get("hello");
		System.out.println("hello=" + value);
	}

	/*
	 * 原始方式
	 */
	private static void test1() {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("121.40.161.96:11211"));
		MemcachedClient memcachedClient;
		try {
			memcachedClient = builder.build();
			memcachedClient.set("hello", 0, "Hello,xmemcached");

			String value = memcachedClient.get("hello");
			System.out.println("hello=" + value);

			memcachedClient.delete("hello");
			value = memcachedClient.get("hello");
			System.out.println("hello=" + value);

			// 关闭
			memcachedClient.shutdown();
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}
	}

}
