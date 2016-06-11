package com.rishi.util;

import java.io.File;
import java.util.Map;

public class Test01 {
	public static void main(String[] args) throws Exception{
		GetData gd = new GetData();
		InOracleBase iob = new InOracleBase();
		
		File folder = new File("E:/MoviesStoryLine/");//获取storyLine所在文件目录
		File[] files = folder.listFiles();//返回所有文件的路径
		for (File file : files) {
			//String filePath = file.getPath();
			//File f = new File(fil);
			
			byte[] b = gd.readWords(file);//读取storyLine
			String[] words = gd.getWords(b);
			
			
			
			Map<String,Integer> map = gd.getSameCounts(words);//统计相同单词和对应的单词个数
			
			System.out.println(map);
			iob.addColumn(map);;//写入数据库
		}
		
		
		
	}
}
