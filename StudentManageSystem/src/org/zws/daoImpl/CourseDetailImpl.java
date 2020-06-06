package org.zws.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.zws.dao.ICourseDetail;
import org.zws.domain.CourseDetail;
import org.zws.mapper.CourseDetailMapper;

public class CourseDetailImpl implements ICourseDetail{
	private SqlSession session;
	public CourseDetailImpl(SqlSession session) {
		this.session=session;
	}
	@Override
	public void insertCourseDetail(CourseDetail detail) {
		// TODO Auto-generated method stub
		CourseDetailMapper mapper=session.getMapper(CourseDetailMapper.class);
		mapper.insert(detail);
		session.commit();
	}
	public List<CourseDetail> findCourseDetail(CourseDetail detail) {
		CourseDetailMapper mapper=session.getMapper(CourseDetailMapper.class);
		List<CourseDetail> d=mapper.query(detail);
		return d;
	}
	public void update(CourseDetail detail) {
		CourseDetailMapper mapper=session.getMapper(CourseDetailMapper.class);
		mapper.updateByAnyField(detail);
		session.commit();
	}
}
