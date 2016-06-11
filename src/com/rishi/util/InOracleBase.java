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
 * ������д�����ݿ���
 *    1.�������ݿ�
 *    2.��ȡ���ݿ�����
 *    3.�������ݿ�
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
			conn = DriverManager.getConnection(URL,USER,PASSWORD);//��ȡ���ݿ�����
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	//������ֶκ�ֵ
	public void addColumn(Map<String, Integer> map) throws SQLException{
		if(map==null){
			return;
		}
		/*��ȡ��ͬ���ʵļ���*/
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		List<String> realWordsList = new ArrayList<String>();
		while(it.hasNext()){			
			String key = it.next();
			//���ֶ���ӵ�List������
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
		//�������϶Ա������е��ֶ����Ƿ���ڸ��ֶ�
		int columnListSize = columnList.size();
		int realWordsListSize = realWordsList.size();
		//����ֶ�
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
			//������ֶ�
			if(flag==1){
				String sql_addColumn = sqlAddColumn+thisColumn+dataType;
				System.out.println(sql_addColumn);
				s.execute(sql_addColumn);
			}
			
		}
		//���ֵ
		/*���ֵ*/
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
	
	
	
/*	//����ȡ������д�����ݿ⣬��Ȼ��Ҫ�����ݿ�����һ����������moviesInfo��		
	public void writeData(Map<String, Integer> map) throws SQLException{
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		Statement s = conn.createStatement();//��ȡ�������ݿ����   
		StringBuffer stringBuffer1 = null;
		StringBuffer stringBuffer2 = null;
		String sql = null;
		List<String> list = new ArrayList<String>();
		while(it.hasNext()){			
			String key = it.next();
			//����ֶ�
			stringBuffer1 = new StringBuffer("alter table MOVIESINFO add sl_");
			stringBuffer1.append(key);
			stringBuffer1.append(" number(2)");
			sql = stringBuffer1.toString();
			
			//���ֶ���ӵ�List������
			list.add(key);
			
			System.out.println(sql);		             
			s.execute(sql);
			System.out.println("�ѳɹ�����ֶ�");			
		}
		
		
		
		���ֵ
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
