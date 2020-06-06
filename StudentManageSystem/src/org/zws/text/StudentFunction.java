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
		System.out.println("�γ̺�"+"\t"+"�γ���"+"\t"+"�γ�����");
		for(int i=0;i<courses.size();i++) {
			Course C=courses.get(i);
			System.out.println(C.getId()+"\t"+C.getName()+"\t"+C.getDetail());
		}
		System.out.println("�������������һ��:");
		String exit=scan.next();
	}
	public static void chooseCourse(User u) {
		while(true) {
		System.out.println("��ѡ������Ҫ�ϵĿγ̺�:");
		String num=scan.next();
		CourseService service1=new CourseService();
		Course c=new Course();
		c.setId(num);
		List<Course> courses=service1.findAllCourse(c);
		if(courses.size()==0) {
			System.out.println("�ÿγ̺Ų����ڣ�������ѡ��!");
			break;
		}
		else {
			CourseDetailService service2=new CourseDetailService();
			CourseDetail detail1=new CourseDetail();
			//�γ̺ź�ѧ�ŵ���ϲ�ѯ������Ϊ���ݵ�Ψһ��ʶ
			detail1.setCourseId(num);
			detail1.setUserId(u.getId());
			if(service2.findCourseDetail(detail1).size()!=0) {
				System.out.println("�ÿγ�����ѡ���벻Ҫ�ظ�ѡ��!");
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
				System.out.println("ѡ�γɹ�!�Ƿ����ѡ�Σ�Y/N");
				String choice=scan.next();
				if("y".equalsIgnoreCase(choice)) {
					//ʲôҲ����
				}
				else {
					break;//����ѭ��
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
		System.out.println("����:"+u.getName()+"\t"+"�ɼ�����:");
		for(int i=0;i<details.size();i++) {
			System.out.println("�γ�����:"+details.get(i).getCourseName()+":"+details.get(i).getGrade());
		}
		System.out.println("����������ĸ���ز˵�");
		String exit=scan.next();
	}
	public static void changePwd(User u) {
		while(true) {
			System.out.println("�����뵱ǰ�û���ԭ����:");
			String pwd=scan.next();
			if(!u.getUserPwd().equals(pwd)) {
				System.out.println("ԭ�������벻��ȷ,����������");
			}
			else {
			System.out.println("�������µ�����");
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
			if(newPwd.length()<6||lowercase<1||capital<1||number<1) {//����������һ����������
				System.out.println("�������벻���ϸ�����Ҫ��"
						+ "(���볤�Ȳ�����6���ַ���������һ��Сд��ĸ��������һ����д��ĸ������һ������),����������:");
			}
			else {
				UserService service=new UserService();
				u.setUserPwd(newPwd);
				service.changePwd(u);
				System.out.println("�޸ĳɹ�!�����κ���ĸ���ز˵�");
				String exit=scan.next();
				break;
			}
			}
			}
	}
}
