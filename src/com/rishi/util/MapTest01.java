package com.rishi.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 查看Map集合特性，
 * 将键和值分开，且能通过键找到值
 * @author Dell
 *
 */
public class MapTest01 {
	

	public static void main(String[] args) {
	/*	Map<String,Integer> m = new HashMap<>();
		m.put("key1", 1);
		m.put("key2", 2);
		m.put("key3", 3);
		m.put("key4", 4);
		m.put("key5", 5);
		System.out.println(m.keySet());
		Set<String> s = m.keySet();
		Iterator<String> i = s.iterator();//针对集合进行迭代的迭代器,Set对象的迭代器方法
		String key = null;
		while(i.hasNext()){//是否有下一个元素
			key = i.next();//返回下一个元素
			System.out.println("键"+key);
			int values = m.get(key);
			//System.out.println(values);
		}*/
		//System.out.println(key);
		
		/*
		GetData gd1 = new GetData();
		File file = new File("E:/MoviesStoryLine/movie00.txt");
		byte[] b = gd1.readWords(file);
		Map<String,Integer> m1 = gd1.getSameCounts(b,b.length);
		System.out.println(m1.entrySet());
		Set<String> s1 = m1.keySet();
		Iterator<String> i1 = s1.iterator();
		while(i1.hasNext()){//是否有下一个元素
			String key1 = i1.next();//返回下一个元素
			System.out.println("键"+key1);
			
			StringBuffer sql = new StringBuffer("alter table moviesInfo add m_");
			sql.append(key1);
			sql.append(" varchar2(20)");
			System.out.println(sql.toString());
			//int values = m1.get(key1);
			//System.out.println(values);
		}*/
		String s1 = "r";
		s1 = s1.toUpperCase();
		//System.out.println(s);
		System.out.println(s1.equals("R"));
	}
}
