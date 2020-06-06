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
			//加载配置文件
			Reader reader=Resources.getResourceAsReader("config.xml");
			//获取SqlSessionFactory对象
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
			//获取SqlSession对象
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
