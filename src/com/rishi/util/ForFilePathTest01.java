package com.rishi.util;

import java.io.File;
import java.io.IOException;

/**
 * ����ĳ·���µ������ļ�
 * @author Dell
 *@version 1.0
 */
public class ForFilePathTest01 {
	public static void main(String[] args) {
		File file = new File("E:/MoviesStoryLine/movie14.txt");
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
	
	}
}
