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
	 * 根据key得到value
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
	 * 将key-value存入缓存
	 *
	 * @param key
	 *            存储的key名称，
	 * @param value
	 *            实际存储的数据
	 * @param expiry
	 *            是expire时间（单位秒），超过这个时间,memcached将这个数据替换出去，0表示永久存储（默认是一个月)
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
	 * 将key-value存入缓存,永久存储（默认是一个月)
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		return set(key, value, 0);
	}

	/**
	 * 根据key删除value
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