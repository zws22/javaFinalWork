package org.zws.factory;

import org.apache.ibatis.session.SqlSession;
import org.zws.dao.ICourseDao;
import org.zws.dao.ICourseDetail;
import org.zws.dao.IUserDao;
import org.zws.daoImpl.CourseDaoImpl;
import org.zws.daoImpl.CourseDetailImpl;
import org.zws.daoImpl.UserDaoImpl;

public class DaoFactory {
	public static IUserDao getNewInstanceIUserDao(SqlSession session) {
		return new UserDaoImpl(session);
	}
	public static ICourseDao getNewInstanceICourseDao(SqlSession session) {
		return new CourseDaoImpl(session);
	}
	public static ICourseDetail getNewInstanceICourseDetailDao(SqlSession session) {
		return new CourseDetailImpl(session);
	}
}
