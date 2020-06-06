package org.zws.text;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.zws.domain.Course;
import org.zws.domain.CourseDetail;
import org.zws.domain.User;
import org.zws.service.CourseDetailService;
import org.zws.service.CourseService;
import org.zws.service.UserService;

public class StudentFunction {
	static Scanner scan=new Scanner(System.in);
	public static void showAllCourse() {
		CourseService service=new CourseService();
		Course c=new Course();
		List<Course> courses=service.findAllCourse(c);
		System.out.println("课程号"+"\t"+"课程名"+"\t"+"课程描述");
		for(int i=0;i<courses.size();i++) {
			Course C=courses.get(i);
			System.out.println(C.getId()+"\t"+C.getName()+"\t"+C.getDetail());
		}
		System.out.println("按任意键返回上一层:");
		String exit=scan.next();
	}
	public static void chooseCourse(User u) {
		while(true) {
		System.out.println("请选择你需要上的课程号:");
		String num=scan.next();
		CourseService service1=new CourseService();
		Course c=new Course();
		c.setId(num);
		List<Course> courses=service1.findAllCourse(c);
		if(courses.size()==0) {
			System.out.println("该课程号不存在，请重新选择!");
			break;
		}
		else {
			CourseDetailService service2=new CourseDetailService();
			CourseDetail detail1=new CourseDetail();
			//课程号和学号的组合查询可以作为数据的唯一标识
			detail1.setCourseId(num);
			detail1.setUserId(u.getId());
			if(service2.findCourseDetail(detail1).size()!=0) {
				System.out.println("该课程你已选择，请不要重复选择!");
				break;
			}
			else {
				CourseDetailService service3=new CourseDetailService();
				CourseDetail detail2=new CourseDetail();
				detail2.setCourseId(num);
				detail2.setUserId(u.getId());
				detail2.setRole(u.getRole());
				detail2.setUserName(u.getName());
				CourseService service4=new CourseService();
				Course course=new Course();
				course.setId(num);
				System.out.println();
				detail2.setCourseName(service4.findCourseById(course).getName());
				detail2.setGrade(BigDecimal.valueOf(0));
				service3.insertCourseDetail(detail2);
				System.out.println("选课成功!是否继续选课？Y/N");
				String choice=scan.next();
				if("y".equalsIgnoreCase(choice)) {
					//什么也不干
				}
				else {
					break;//跳出循环
				}
			}
		}
		}
	}
	public static void showGrade(User u) {
		CourseDetailService service=new CourseDetailService();
		CourseDetail detail=new CourseDetail();
		detail.setUserId(u.getId());
		List<CourseDetail> details=service.findCourseDetail(detail);
		System.out.println("姓名:"+u.getName()+"\t"+"成绩如下:");
		for(int i=0;i<details.size();i++) {
			System.out.println("课程名称:"+details.get(i).getCourseName()+":"+details.get(i).getGrade());
		}
		System.out.println("输入任意字母返回菜单");
		String exit=scan.next();
	}
	public static void changePwd(User u) {
		while(true) {
			System.out.println("请输入当前用户的原密码:");
			String pwd=scan.next();
			if(!u.getUserPwd().equals(pwd)) {
				System.out.println("原密码输入不正确,请重新输入");
			}
			else {
			System.out.println("请设置新的密码");
			int lowercase=0;
			int number=0;
			int capital=0;
			String newPwd=scan.next();
			char[] a=newPwd.toCharArray();
			for(int i=0;i<a.length;i++) {
				if(a[i]>='a'&&a[i]<'z') {
					lowercase++;
				}
				else if(a[i]>='A'&&a[i]<'Z') {
					capital++;
				}
				else if(a[i]>='0'&&a[i]<'9'){
					number++;
				}
			}
			if(newPwd.length()<6||lowercase<1||capital<1||number<1) {//不满足任意一个条件报错
				System.out.println("您的密码不符合复杂性要求"
						+ "(密码长度不少于6个字符，至少有一个小写字母，至少有一个大写字母，至少一个数字),请重新输入:");
			}
			else {
				UserService service=new UserService();
				u.setUserPwd(newPwd);
				service.changePwd(u);
				System.out.println("修改成功!输入任何字母返回菜单");
				String exit=scan.next();
				break;
			}
			}
			}
	}
}
