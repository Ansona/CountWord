package com.rishi.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
/**
 * 将数据写入数据库中
 *    1.加载数据库
 *    2.获取数据库连接
 *    3.操作数据库
 * @author Dell
 *@version 1.0
 */
public class InOracleBase {
	private static Connection conn = null;
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "scott";
	private static final String PASSWORD = "123456";
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL,USER,PASSWORD);//获取数据库链接
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	//添加新字段和值
	public void addColumn(Map<String, Integer> map) throws SQLException{
		if(map==null){
			return;
		}
		/*获取相同单词的集合*/
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		List<String> realWordsList = new ArrayList<String>();
		while(it.hasNext()){			
			String key = it.next();
			//将字段添加到List集合中
			realWordsList.add(key);			
		}
		
		
		Statement s = conn.createStatement();
		String sql = "select column_name from user_tab_columns where table_name='MOVIESINFO'";
		ResultSet rs = s.executeQuery(sql);
		String columnName;
		List<String> columnList = new ArrayList<String>();
		while(rs.next()){
			columnName = rs.getString("column_name");
			columnList.add(columnName);
		}
		System.out.println(columnList);
		//遍历集合对比在已有的字段中是否存在该字段
		int columnListSize = columnList.size();
		int realWordsListSize = realWordsList.size();
		//添加字段
		String sqlAddColumn = "alter table MOVIESINFO add ";
		String dataType = " number(2)";
		for(int i = 0;i<realWordsListSize;i++){
			int flag = 0;
			String thisColumn = "SL_"+realWordsList.get(i);
			for(int j = 0;j<columnListSize;j++){
				if(thisColumn.equals(columnList.get(j))){
					flag = 0;
					break;
				}else{
					flag = 1;
				}
			}
			//添加新字段
			if(flag==1){
				String sql_addColumn = sqlAddColumn+thisColumn+dataType;
				System.out.println(sql_addColumn);
				s.execute(sql_addColumn);
			}
			
		}
		//添加值
		/*添加值*/
		StringBuffer sqlInsertData = new StringBuffer("insert into MOVIESINFO(");
		for(int i = 0;i<realWordsListSize;i++){
			String key = realWordsList.get(i);
			sqlInsertData.append("SL_");
			if(i<realWordsListSize-1){
				sqlInsertData.append(key+",");
			}else{
				sqlInsertData.append(key);
			}
			
		}
		sqlInsertData.append(") values(");
		for(int i = 0;i<realWordsListSize;i++){
			String key = realWordsList.get(i);
			if(i<realWordsListSize-1){
				sqlInsertData.append(map.get(key)+",");
			}else{
				sqlInsertData.append(map.get(key));
			}
			
		}	
		sqlInsertData.append(")");
		System.out.println(sqlInsertData.toString());
		s.execute(sqlInsertData.toString());
	}
	
	
	
/*	//将获取的数据写入数据库，当然先要在数据库中制一个基表（表名moviesInfo）		
	public void writeData(Map<String, Integer> map) throws SQLException{
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		Statement s = conn.createStatement();//获取操纵数据库对象   
		StringBuffer stringBuffer1 = null;
		StringBuffer stringBuffer2 = null;
		String sql = null;
		List<String> list = new ArrayList<String>();
		while(it.hasNext()){			
			String key = it.next();
			//添加字段
			stringBuffer1 = new StringBuffer("alter table MOVIESINFO add sl_");
			stringBuffer1.append(key);
			stringBuffer1.append(" number(2)");
			sql = stringBuffer1.toString();
			
			//将字段添加到List集合中
			list.add(key);
			
			System.out.println(sql);		             
			s.execute(sql);
			System.out.println("已成功添加字段");			
		}
		
		
		
		添加值
		stringBuffer2 = new StringBuffer("insert into MOVIESINFO(");	
		for(int i = 0;i<list.size();i++){
			String key = list.get(i);
			stringBuffer2.append("sl_");
			if(i<list.size()-1){
				stringBuffer2.append(key+",");
			}else{
				stringBuffer2.append(key);
			}
			
		}
		stringBuffer2.append(") values(");
		for(int i = 0;i<list.size();i++){
			String key = list.get(i);
			if(i<list.size()-1){
				stringBuffer2.append(map.get(key)+",");
			}else{
				stringBuffer2.append(map.get(key));
			}
			
		}
		
		
		stringBuffer2.append(")");
		System.out.println(stringBuffer2.toString());
		s.execute(stringBuffer2.toString());
		
	}*/
}
