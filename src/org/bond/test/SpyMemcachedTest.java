package org.bond.test;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

public class SpyMemcachedTest {

	public static void main(String[] args) {
		try {
			/* ����MemcachedClient ʵ������ָ��memcached�����IP��ַ�Ͷ˿ں� */
			MemcachedClient mc = new MemcachedClient(new InetSocketAddress(
					"121.40.161.96", 11211));
			Future<Boolean> b = null;
			/* ��keyֵ������ʱ��(��)��Ҫ����Ķ���set��memcached�� */
			b = mc.set("user_name_001", 900, "����");
			if (b.get().booleanValue() == true) {
				// mc.shutdown();
				System.out.println("------��ӳɹ�------");
			}

			/* ����keyֵ��memcached�в��һ��棬�������򷵻�null */
			Object obj = mc.get("user_name_001");
			System.out.println("user_name_001:" + obj);

			b = mc.delete("user_name_001");
			if (b.get().booleanValue() == true) {
				// mc.shutdown();
				System.out.println("------ɾ���ɹ�------");
			}
			obj = mc.get("user_name_001");
			System.out.println("user_name_001:" + obj);
			mc.shutdown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
