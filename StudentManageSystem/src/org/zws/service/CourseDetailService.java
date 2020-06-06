package org.zws.service;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.zws.dao.ICourseDetail;
import org.zws.domain.CourseDetail;
import org.zws.factory.DaoFactory;
import org.zws.util.GetSession;
public class CourseDetailService {
	private SqlSession session;
	public CourseDetailService() {
		this.session=GetSession.getSession();
	}
	public List<CourseDetail> findCourseDetail(CourseDetail detail) {
		ICourseDetail dao=DaoFactory.getNewInstanceICourseDetailDao(session);
		List<CourseDetail> d=dao.findCourseDetail(detail);
		GetSession.closeSession(session);
		return d;
	}
	public void insertCourseDetail(CourseDetail detail) {
		ICourseDetail dao=DaoFactory.getNewInstanceICourseDetailDao(session);
		dao.insertCourseDetail(detail);
		GetSession.closeSession(session);
	}
	public void updateGrade(CourseDetail detail) {
		ICourseDetail dao=DaoFactory.getNewInstanceICourseDetailDao(session);
		dao.update(detail);
		GetSession.closeSession(session);
	}
}
