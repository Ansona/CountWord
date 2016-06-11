package com.rishi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
/**
 * 1.ʵ���ļ�������
 * 2.ɨ����ȡ����Ҫ������
 * 3.�����������ݵļ�ֵ��
 * @author Dell
 *@version 1.0
 */
public class GetData {
	InputStream fis = null;	
	byte[] b = null;
	int byteLength = 0;
	public byte[] readWords(File file){		
		/*�ж��ļ��Ƿ���ڣ������ھʹ���*/
		if(file.exists()){
			System.out.println("�ı��ļ�����");
		}else{
			System.out.println("�ı��ļ������ڣ�");
			/*�����ļ�*/
			try {
				boolean f = file.createNewFile();
				if(f){
					System.out.println("�ļ������ɹ����������濽������");
				}else{
					System.out.println("�ļ�����ʧ�ܣ�����");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
		/*��ȡ�����������Ҷ�ȡ����*/
		try {
			fis = new FileInputStream(file);
			byteLength = fis.available();
			b = new byte[byteLength];//available()��ȡ����������Ч�ֽ���
			fis.read(b);
			
			//getSameCounts(b,byteLength);						
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			/*�ر���*/
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
	
	/*����byte���飬�������е��ʣ��ַ������飩*/
	public String[] getWords(byte[] b){
		
		String storyLine = new String(b);		
		//����������ʽ��ȡÿһ������,��δ���Է�����ո�֮��Ŀ��ַ���
		String[] words = storyLine.split("[!?, .:;\\/] |[!?, .:;\\/]");
		
		/*int k = 0;
		
		ȥ�����ַ��������Ұ�����ֵ��һ���µ�List<String>����
		List<String> wordsList = new ArrayList<String>();
		for(int i = 0;i<words.length-k;i++){
			
			ȥ�����ַ���
			if(words[i].equals("")){
				for(int j = i;j<words.length-k;j++){
					words[j] = words[j+1];
				}
				i -= 1;
				k += 1;
				continue;
			}
			words[i] = words[i].toLowerCase();
			wordsList.add(words[i]);//��ȥ���յĵ�����ӵ�List������
		}*/
		int wordsLength = words.length;
		for(int i = 0;i<wordsLength;i++){
			words[i] = words[i].toUpperCase();
		}
		
		return words;
	}
	
	
	

	/*��ȡ�������ı��ļ�����ͬ���ʵĸ�������*/
	public Map<String,Integer> getSameCounts(String[] words){
		
		
		/*int startIndex = 0;
		ArrayList<String> wordsList = new ArrayList<String>();//����String���͵�ArrayList����
		for(int i = 0;i<byteLength;i++){
			if(b[i]>=32 && b[i]<=47 && b[i] != 39 && b[i] != 45 || b[i]>=58 && b[i]<=64){
				String word = storyLine.substring(startIndex,i);
				//����������sql�����б�ע�ַ������ڴ�ȥ�������еĵ����ţ���_���浥����
				
				word = word.toLowerCase();
				startIndex = i+1;
				if(! word.equals("")){
					wordsList.add(word);
				}			
			}				
		}*/
		//ͳ����ͬ���ʵĸ���
		Map<String,Integer> m = new HashMap<String, Integer>();
		int wordsLength = words.length;
		for(int i = 0;i<wordsLength;i++){
			int wordCount = 0;
			for(int j = 0;j<wordsLength;j++){
				if(words[i].equals(words[j]) && j<i){//���Ѿ���ѯ���ĵ���ʱ������ѭ��
					break;
				}
				if(words[i].equals(words[j])){
					wordCount += 1;
				}
			}
			String word = words[i].replaceAll("[-]", "0");//��-�滻Ϊ_,��Ϊ��pl/sql����д˷�����Ч
			word = word.replaceAll("[']", "1");
			if(wordCount != 0){
			
				//System.out.println(word+"�ĸ����ǣ�"+wordCount);
				m.put(word, wordCount);//����Ե��ʺ͵��������ļ�ֵ�Ե�HashMap������
			}
		}
		return m;
	}			
	/*
	public static void main(String[] args) {
		GetData gd = new GetData();
		File file1 = new File("E:/MoviesStoryLine/");
		File[] listPath = file1.listFiles();
		����"E:\MoviesStoryLine\"Ŀ¼�µ����е��ı��ļ�
		for(File f : listPath){
			String d = f.getPath().toString();
			File file = new File(d);
			gd.readWords(file);
			System.out.println("-----------------------�ָ���---------------------------");
		}	
	}*/	
	/*public static void main(String[] args) {
		GetData gd = new GetData();
		File file = new File("E:/MoviesStoryLine/movie00.txt");
		byte[] b = gd.readWords(file);
		gd.getSameCounts(b, b.length);
	}*/
	
}
