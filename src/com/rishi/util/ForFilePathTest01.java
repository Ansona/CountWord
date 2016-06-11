package com.rishi.util;

import java.io.File;
import java.io.IOException;

/**
 * 遍历某路径下的所有文件
 * @author Dell
 *@version 1.0
 */
public class ForFilePathTest01 {
	public static void main(String[] args) {
		File file = new File("E:/MoviesStoryLine/movie14.txt");
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
	
	}
}
