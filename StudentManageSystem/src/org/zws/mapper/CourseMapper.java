package org.zws.mapper;

import java.util.List;

import org.zws.domain.Course;

public interface CourseMapper {
	Course queryById(Course course);
	List<Course> queryByAnyField(Course course);
	void insertCourse(Course course);
	void updateCourse(Course course);
	void deleteCourse(Course course);
}
