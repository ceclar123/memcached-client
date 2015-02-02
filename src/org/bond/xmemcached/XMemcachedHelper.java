package org.bond.xmemcached;

import net.rubyeye.xmemcached.MemcachedClient;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class XMemcachedHelper {
	private static transient Logger log = LogManager
			.getLogger(XMemcachedHelper.class);
	private MemcachedClient memcachedClient;
	private boolean memcachedOpen;

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setMemcachedOpen(boolean memcachedOpen) {
		this.memcachedOpen = memcachedOpen;
	}

	/**
	 * ����key�õ�value
	 *
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		if (getMemcachedOpen()) {
			try {
				return memcachedClient.get(key);
			} catch (Exception e) {
				log.error("MemcachedHelper key is " + key);
			}
		}
		return null;
	}

	/**
	 * ��key-value���뻺��
	 *
	 * @param key
	 *            �洢��key���ƣ�
	 * @param value
	 *            ʵ�ʴ洢������
	 * @param expiry
	 *            ��expireʱ�䣨��λ�룩���������ʱ��,memcached����������滻��ȥ��0��ʾ���ô洢��Ĭ����һ����)
	 * @return
	 */
	public boolean set(String key, Object value, int expiry) {
		if (getMemcachedOpen()) {
			if (null != value) {
				try {
					return memcachedClient.set(key, expiry, value);
				} catch (Exception e) {
					log.error("MemcachedHelper key is " + key);
				}
			} else {
				log.error("MemcachedHelper key is " + key);
			}
		}
		return false;
	}

	/**
	 * ��key-value���뻺��,���ô洢��Ĭ����һ����)
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		return set(key, value, 0);
	}

	/**
	 * ����keyɾ��value
	 *
	 * @param key
	 * @return
	 */
	public boolean delete(String key) {
		if (getMemcachedOpen()) {
			try {
				return memcachedClient.delete(key);
			} catch (Exception e) {
				log.error("MemcachedHelper key is " + key);
			}
		}
		return false;
	}

	public boolean getMemcachedOpen() {
		return memcachedOpen;
	}
}