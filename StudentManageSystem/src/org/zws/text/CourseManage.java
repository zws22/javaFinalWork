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
		System.out.println("������γ̺�:(3λ����)");
		String id=scan.next();
		if(id.matches("\\d{3}")) {
			CourseService service=new CourseService();
			Course course=new Course();
			course.setId(id);
			if(service.findCourseById(course)==null) {
				Id=id;
			}
			else {
				System.out.println("�ÿγ̺��Ѿ����ڣ�����������!");
				break;
			}
		}
		else {
			System.out.println("������Ŀγ̺Ÿ�ʽ����ȷ");
			break;
		}
		System.out.println("������γ���:");
		Name=scan.next();
		System.out.println("������γ�����:");
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
		System.out.println("������!");
	}
	public static void updateCourse() {
		String id=null;
		while(true) {
		System.out.println("��������Ҫ�޸ĵĿγ̵Ŀγ̺�:");
		id=scan.next();
		CourseService service=new CourseService();
		Course course=new Course();
		course.setId(id);
		if(service.findCourseById(course)==null) {
			System.out.println("������Ŀγ̺Ų�����");
		}
		else {
			break;
		}
		}
		System.out.println("�������޸ĺ�Ŀγ���:");
		String name=scan.next();
		System.out.println("�������޸ĺ�Ŀγ̽���:");
		String detail=scan.next();
		CourseService s=new CourseService();
		Course c=new Course(id, name, detail);
		s.updateCourse(c);
		System.out.println("�޸����!");
	}
	public static void deleteCourse() {
		String id=null;
		while(true) {
		System.out.println("��������Ҫ�޸ĵĿγ̵Ŀγ̺�:");
		id=scan.next();
		CourseService service=new CourseService();
		Course course=new Course();
		course.setId(id);
		if(service.findCourseById(course)==null) {
			System.out.println("������Ŀγ̺Ų�����");
		}
		else {
			break;
		}
		}
		CourseService s=new CourseService();
		Course c=new Course();
		c.setId(id);
		s.deleteCourse(c);
		System.out.println("ɾ�����");
		}
}
