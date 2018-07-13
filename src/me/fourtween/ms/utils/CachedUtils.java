package me.fourtween.ms.utils;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class CachedUtils {
	private static MemcachedClient memcachedClient;
	private static MemcachedClient getMemcachedClient(){
//		if(memcachedClient==null){
//			try {
//				memcachedClient = new MemcachedClient(
//					    new InetSocketAddress("localhost", 11211));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return memcachedClient;
	}
	
	public static void set(String key,int exp,Object o){
		if(memcachedClient==null)
			return;
		getMemcachedClient().set(key, exp, o);
	}
	
	public static void add(String key,int exp,Object o){
		if(memcachedClient==null)
			return;
		getMemcachedClient().add(key, exp, o);
	}
	
	public static Object get(String key){
		if(memcachedClient==null)
			return null;
		return getMemcachedClient().get(key);
	}
}
