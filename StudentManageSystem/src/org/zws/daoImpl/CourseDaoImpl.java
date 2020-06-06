package org.zws.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.zws.dao.ICourseDao;
import org.zws.domain.Course;
import org.zws.mapper.CourseMapper;

public class CourseDaoImpl implements ICourseDao{
	private SqlSession session;
	public CourseDaoImpl(SqlSession session) {
		this.session=session;
	}
	@Override
	public List<Course> queryByAnyField(Course course) {
		// TODO Auto-generated method stub
		CourseMapper mapper=session.getMapper(CourseMapper.class);
		List<Course> courses=mapper.queryByAnyField(course);
		return courses;
	}
	public Course queryById(Course course) {
		CourseMapper mapper=session.getMapper(CourseMapper.class);
		Course c=mapper.queryById(course);
		return c;
	}
	public void insertCourse(Course course) {
		CourseMapper mapper=session.getMapper(CourseMapper.class);
		mapper.insertCourse(course);
		session.commit();
	}
	public void updateCourse(Course course) {
		CourseMapper mapper=session.getMapper(CourseMapper.class);
		mapper.updateCourse(course);
		session.commit();
	}
	public void deleteCourse(Course course) {
		CourseMapper mapper=session.getMapper(CourseMapper.class);
		mapper.deleteCourse(course);
		session.commit();
	}
}
