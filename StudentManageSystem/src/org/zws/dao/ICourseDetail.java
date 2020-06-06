package org.zws.dao;

import java.util.List;

import org.zws.domain.CourseDetail;

public interface ICourseDetail {
	void insertCourseDetail(CourseDetail detail);
	List<CourseDetail> findCourseDetail(CourseDetail detail);
	void update(CourseDetail detail);
}
