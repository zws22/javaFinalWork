package org.zws.mapper;

import java.util.List;

import org.zws.domain.CourseDetail;

public interface CourseDetailMapper {
	List<CourseDetail> query(CourseDetail detail);
	void insert(CourseDetail detail);
	void updateByAnyField(CourseDetail detail);
}
