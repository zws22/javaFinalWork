package org.zws.dao;

import java.util.List;

import org.zws.domain.Course;

public interface ICourseDao {
	List<Course> queryByAnyField(Course course);
	Course queryById(Course course);
	void insertCourse(Course course);
	void updateCourse(Course course);
	void deleteCourse(Course course);
}
