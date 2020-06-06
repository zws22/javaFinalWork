package org.zws.domain;

import java.math.BigDecimal;

public class CourseDetail {//学生选课和老师代课情况的类
	private int id;
	private String courseId;
	private String courseName;
	private String userId;
	private String userName;
	private String role;
	private BigDecimal grade;
	public CourseDetail() {}
	public CourseDetail(int id, String courseId,String courseName, String userId,
			String userName,String role, BigDecimal grade) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.courseName=courseName;
		this.userId = userId;
		this.userName=userName;
		this.role = role;
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public BigDecimal getGrade() {
		return grade;
	}
	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "CourseDetail [id=" + id + ", courseId=" + courseId + ", courseName=" + courseName + ", userId=" + userId
				+ ", userName=" + userName + ", role=" + role + ", grade=" + grade + "]";
	}
	
}
