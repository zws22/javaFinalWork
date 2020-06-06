package org.zws.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.zws.dao.ICourseDao;
import org.zws.domain.Course;
import org.zws.factory.DaoFactory;
import org.zws.util.GetSession;

public class CourseService {
	private SqlSession session;
	public CourseService() {
		this.session=GetSession.getSession();
	}
	public List<Course> findAllCourse(Course course){
		ICourseDao dao=DaoFactory.getNewInstanceICourseDao(session);
		List<Course> courses=dao.queryByAnyField(course);
		GetSession.closeSession(session);
		return courses;
	}
	public Course findCourseById(Course course) {
		ICourseDao dao=DaoFactory.getNewInstanceICourseDao(session);
		Course c=dao.queryById(course);
		GetSession.closeSession(session);
		return c;
	}
	public void insertCourse(Course course) {
		ICourseDao dao=DaoFactory.getNewInstanceICourseDao(session);
		dao.insertCourse(course);
		GetSession.closeSession(session);
	}
	public void updateCourse(Course course) {
		ICourseDao dao=DaoFactory.getNewInstanceICourseDao(session);
		dao.updateCourse(course);
		GetSession.closeSession(session);
	}
	public void deleteCourse(Course course) {
		ICourseDao dao=DaoFactory.getNewInstanceICourseDao(session);
		dao.deleteCourse(course);
		GetSession.closeSession(session);
	}
}
