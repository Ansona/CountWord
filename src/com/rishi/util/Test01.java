package com.rishi.util;

import java.io.File;
import java.util.Map;

public class Test01 {
	public static void main(String[] args) throws Exception{
		GetData gd = new GetData();
		InOracleBase iob = new InOracleBase();
		
		File folder = new File("E:/MoviesStoryLine/");//��ȡstoryLine�����ļ�Ŀ¼
		File[] files = folder.listFiles();//���������ļ���·��
		for (File file : files) {
			//String filePath = file.getPath();
			//File f = new File(fil);
			
			byte[] b = gd.readWords(file);//��ȡstoryLine
			String[] words = gd.getWords(b);
			
			
			
			Map<String,Integer> map = gd.getSameCounts(words);//ͳ����ͬ���ʺͶ�Ӧ�ĵ��ʸ���
			
			System.out.println(map);
			iob.addColumn(map);;//д�����ݿ�
		}
		
		
		
	}
}
