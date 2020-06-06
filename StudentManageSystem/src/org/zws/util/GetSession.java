package org.zws.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetSession {
	public static SqlSession getSession() {
		try {
			//���������ļ�
			Reader reader=Resources.getResourceAsReader("config.xml");
			//��ȡSqlSessionFactory����
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
			//��ȡSqlSession����
			SqlSession session=factory.openSession();
			return session;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void closeSession(SqlSession session) {
		if(session!=null) {
			session.close();
		}
	}
}
