package org.zws.text;

import java.util.Scanner;

import org.zws.domain.Course;
import org.zws.service.CourseService;

public class CourseManage {
	static Scanner scan=new Scanner(System.in);
	static String Id;
	static String Name;
	static String Detail;
	public static void addCourse() {
		while(true) {
		while(true) {
		System.out.println("请输入课程号:(3位数字)");
		String id=scan.next();
		if(id.matches("\\d{3}")) {
			CourseService service=new CourseService();
			Course course=new Course();
			course.setId(id);
			if(service.findCourseById(course)==null) {
				Id=id;
			}
			else {
				System.out.println("该课程号已经存在，请重新输入!");
				break;
			}
		}
		else {
			System.out.println("你输入的课程号格式不正确");
			break;
		}
		System.out.println("请输入课程名:");
		Name=scan.next();
		System.out.println("请输入课程描述:");
		Detail=scan.next();
		break;
		}
		if(Id!=null&&Name!=null&&Detail!=null) {
			break;
		}
		}
		CourseService s=new CourseService();
		Course c=new Course(Id, Name, Detail);
		s.insertCourse(c);
		System.out.println("添加完毕!");
	}
	public static void updateCourse() {
		String id=null;
		while(true) {
		System.out.println("请输入需要修改的课程的课程号:");
		id=scan.next();
		CourseService service=new CourseService();
		Course course=new Course();
		course.setId(id);
		if(service.findCourseById(course)==null) {
			System.out.println("你输入的课程号不存在");
		}
		else {
			break;
		}
		}
		System.out.println("请输入修改后的课程名:");
		String name=scan.next();
		System.out.println("请输入修改后的课程介绍:");
		String detail=scan.next();
		CourseService s=new CourseService();
		Course c=new Course(id, name, detail);
		s.updateCourse(c);
		System.out.println("修改完毕!");
	}
	public static void deleteCourse() {
		String id=null;
		while(true) {
		System.out.println("请输入需要修改的课程的课程号:");
		id=scan.next();
		CourseService service=new CourseService();
		Course course=new Course();
		course.setId(id);
		if(service.findCourseById(course)==null) {
			System.out.println("你输入的课程号不存在");
		}
		else {
			break;
		}
		}
		CourseService s=new CourseService();
		Course c=new Course();
		c.setId(id);
		s.deleteCourse(c);
		System.out.println("删除完毕");
		}
}
