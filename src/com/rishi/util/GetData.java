package com.rishi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
/**
 * 1.实现文件的输入
 * 2.扫描提取出所要的数据
 * 3.返回所需数据的键值对
 * @author Dell
 *@version 1.0
 */
public class GetData {
	InputStream fis = null;	
	byte[] b = null;
	int byteLength = 0;
	public byte[] readWords(File file){		
		/*判断文件是否存在，不存在就创建*/
		if(file.exists()){
			System.out.println("文本文件存在");
		}else{
			System.out.println("文本文件不存在！");
			/*创建文件*/
			try {
				boolean f = file.createNewFile();
				if(f){
					System.out.println("文件创建成功！请向里面拷入内容");
				}else{
					System.out.println("文件创建失败！！！");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
		/*获取输入流，并且读取数据*/
		try {
			fis = new FileInputStream(file);
			byteLength = fis.available();
			b = new byte[byteLength];//available()获取输入流的有效字节数
			fis.read(b);
			
			//getSameCounts(b,byteLength);						
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			/*关闭流*/
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return b;
	}
	
	/*传入byte数组，返回所有单词（字符串数组）*/
	public String[] getWords(byte[] b){
		
		String storyLine = new String(b);		
		//利用正则表达式读取每一个单词,但未忽略符号与空格之间的空字符串
		String[] words = storyLine.split("[!?, .:;\\/] |[!?, .:;\\/]");
		
		/*int k = 0;
		
		去掉空字符串，并且把它赋值给一个新的List<String>集合
		List<String> wordsList = new ArrayList<String>();
		for(int i = 0;i<words.length-k;i++){
			
			去掉空字符串
			if(words[i].equals("")){
				for(int j = i;j<words.length-k;j++){
					words[j] = words[j+1];
				}
				i -= 1;
				k += 1;
				continue;
			}
			words[i] = words[i].toLowerCase();
			wordsList.add(words[i]);//将去除空的单词添加到List集合中
		}*/
		int wordsLength = words.length;
		for(int i = 0;i<wordsLength;i++){
			words[i] = words[i].toUpperCase();
		}
		
		return words;
	}
	
	
	

	/*获取输入流文本文件中相同单词的个数方法*/
	public Map<String,Integer> getSameCounts(String[] words){
		
		
		/*int startIndex = 0;
		ArrayList<String> wordsList = new ArrayList<String>();//定义String类型的ArrayList集合
		for(int i = 0;i<byteLength;i++){
			if(b[i]>=32 && b[i]<=47 && b[i] != 39 && b[i] != 45 || b[i]>=58 && b[i]<=64){
				String word = storyLine.substring(startIndex,i);
				//单引号在再sql语言中标注字符串，在此去掉单词中的单引号，用_代替单引号
				
				word = word.toLowerCase();
				startIndex = i+1;
				if(! word.equals("")){
					wordsList.add(word);
				}			
			}				
		}*/
		//统计相同单词的个数
		Map<String,Integer> m = new HashMap<String, Integer>();
		int wordsLength = words.length;
		for(int i = 0;i<wordsLength;i++){
			int wordCount = 0;
			for(int j = 0;j<wordsLength;j++){
				if(words[i].equals(words[j]) && j<i){//到已经查询过的单词时，跳出循环
					break;
				}
				if(words[i].equals(words[j])){
					wordCount += 1;
				}
			}
			String word = words[i].replaceAll("[-]", "0");//将-替换为_,因为在pl/sql语句中此符号无效
			word = word.replaceAll("[']", "1");
			if(wordCount != 0){
			
				//System.out.println(word+"的个数是："+wordCount);
				m.put(word, wordCount);//添加以单词和单词数量的键值对到HashMap集合中
			}
		}
		return m;
	}			
	/*
	public static void main(String[] args) {
		GetData gd = new GetData();
		File file1 = new File("E:/MoviesStoryLine/");
		File[] listPath = file1.listFiles();
		遍历"E:\MoviesStoryLine\"目录下的所有的文本文件
		for(File f : listPath){
			String d = f.getPath().toString();
			File file = new File(d);
			gd.readWords(file);
			System.out.println("-----------------------分割线---------------------------");
		}	
	}*/	
	/*public static void main(String[] args) {
		GetData gd = new GetData();
		File file = new File("E:/MoviesStoryLine/movie00.txt");
		byte[] b = gd.readWords(file);
		gd.getSameCounts(b, b.length);
	}*/
	
}
