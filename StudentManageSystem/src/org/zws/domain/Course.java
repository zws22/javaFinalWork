package org.zws.domain;

public class Course {
	private String id;
	private String name;
	private String detail;
	public Course() {}
	public Course(String id, String name, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", detail=" + detail + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
