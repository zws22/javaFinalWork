package org.zws.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.zws.domain.CourseDetail;
import org.zws.domain.User;
import org.zws.service.CourseDetailService;
import org.zws.service.UserService;

public class TeacherFunction {
	static Scanner scan=new Scanner(System.in);
	public static void showCourseByOwn(User u) {
		CourseDetailService service1=new CourseDetailService();
		CourseDetail detail1=new CourseDetail();
		detail1.setUserId(u.getId());
		List<CourseDetail> details=service1.findCourseDetail(detail1);
		System.out.println("姓名:"+u.getName()+"\t"+"担任"+details.size()+"门代课老师");
		for(int i=0;i<details.size();i++) {
			CourseDetail d=details.get(i);
			CourseDetailService service2=new CourseDetailService();
			CourseDetail detail2=new CourseDetail();
			//根据课程号和角色确定选择该课程的学生数
			detail2.setCourseId(d.getCourseId());
			detail2.setRole("学生");
			List<CourseDetail> details2=service2.findCourseDetail(detail2);
			System.out.println("课程名称:"+d.getCourseName()+"该课程人数:"+details2.size());
		}
		System.out.println("输入任何字母返回主菜单");
		String exit=scan.next();
	}
	public static void showAllStudent(User u) {
		CourseDetailService service1=new CourseDetailService();
		CourseDetail detail1=new CourseDetail();
		detail1.setUserId(u.getId());
		List<CourseDetail> details=service1.findCourseDetail(detail1);
		for(int i=0;i<details.size();i++) {
			CourseDetail d=details.get(i);
			System.out.println("课程名称:"+d.getCourseName()+"课程号:"+d.getCourseId()+"\t"+"学生名单如下:");
			CourseDetailService service2=new CourseDetailService();
			CourseDetail detail2=new CourseDetail();
			//根据课程号和角色确定选择该课程的学生数
			detail2.setCourseId(d.getCourseId());
			detail2.setRole("学生");
			List<CourseDetail> details2=service2.findCourseDetail(detail2);
			for(int j=0;j<details2.size();j++) {
				System.out.println("学号:"+details2.get(j).getUserId()+"\t"+"姓名:"+details.get(j).getUserName());
				System.out.println("总人数为:"+details2.size());
			}
		}
		System.out.println("输入任何字母返回主菜单");
		String exit=scan.next();
	}
	public static void importGrade(User u) {
		//展示课程课对应的学生信息
		showAllStudent(u);
		System.out.println("请输入你需要总结成绩的课程号:");
		String courseId=scan.next();
		System.out.println("请输入你需要总结成绩的课程名称:");
		String courseName=scan.next();
		CourseDetailService service=new CourseDetailService();
		CourseDetail detail=new CourseDetail();
		detail.setCourseId(courseId);
		detail.setCourseName(courseName);
		detail.setRole("学生");
		if(service.findCourseDetail(detail).size()!=0) {
			System.out.println("请输入导入学生学号:");
			String userId=scan.next();
			System.out.println("请输入学生姓名:");
			String userName=scan.next();
			CourseDetailService service1=new CourseDetailService();
			CourseDetail detail1=new CourseDetail();
			detail1.setUserId(userId);
			detail1.setUserName(userName);
			//如果输入的学号姓名正确，已选课
			if(service1.findCourseDetail(detail1).size()!=0) {
				CourseDetailService service2=new CourseDetailService();
				CourseDetail detail2=new CourseDetail();
				detail2.setId(service1.findCourseDetail(detail1).get(0).getId());
				detail2.setCourseId(courseId);
				detail2.setCourseName(courseName);
				detail2.setRole("学生");
				detail2.setUserId(userId);
				detail2.setUserName(userName);
				System.out.println("请输入成绩");
				BigDecimal grade=scan.nextBigDecimal();
				detail2.setGrade(grade);
				service2.updateGrade(detail2);
				System.out.println("录入完毕!");
			}
			else{
				System.out.println("你输入的学生姓名或者学号不存在，没有该学生的信息!");
			}
		}
		else {
			System.out.println("查不到指定数据!");
		}
	}
	public static void exportStudentList(User u) {
		CourseDetailService service=new CourseDetailService();
		CourseDetail detail=new CourseDetail();
		detail.setUserId(u.getId());
		List<CourseDetail> details=service.findCourseDetail(detail);
		while(true) {
		System.out.println("请选择导入的文件类型:");
		System.out.println("     1.导入到TXT");
		System.out.println("     2.导入到XML");
		System.out.println("     3.返回上一层");
		System.out.println("请输入你的选择(1-3)");
		int choice=scan.nextInt();
		switch(choice) {
		case 1:
			exportToTxt(details);
			System.out.println("写入完毕!");
			break;
		case 2:
			exportToXml(details);
			System.out.println("写入完毕!");
			break;
		case 3:break;
		}
		if(choice==3) {
			break;//退出该层循环
		}
		}
		
	}
	public static void exportToTxt(List<CourseDetail> details) {
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("base/student.txt")));
			for(int i=0;i<details.size();i++) {
				CourseDetail d=new CourseDetail();
				d.setCourseId(details.get(i).getCourseId());
				d.setRole("学生");
				CourseDetailService service=new CourseDetailService();
				List<CourseDetail> ds=service.findCourseDetail(d);
				bw.write(details.get(i).getCourseName()+"学生名单如下:");
				bw.write("学号"+"\t"+"姓名");
				bw.newLine();
				for(int j=0;j<ds.size();j++) {
					CourseDetail w=ds.get(j);
					bw.write(w.getUserId()+"\t"+w.getCourseName());
					bw.newLine();
				}
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void exportToXml(List<CourseDetail> details) {
		Document document=DocumentHelper.createDocument();
		//创建根节点courses
		Element root=document.addElement("courses");
		for(int i=0;i<details.size();i++) {
			Element course=root.addElement(details.get(i).getCourseName());
			CourseDetail d=new CourseDetail();
			d.setCourseId(details.get(i).getCourseId());
			d.setRole("学生");
			CourseDetailService service=new CourseDetailService();
			List<CourseDetail> ds=service.findCourseDetail(d);
			for(int j=0;j<ds.size();j++) {
				Element el=course.addElement("student");
				el.addElement("id").addText(ds.get(j).getUserId());
				el.addElement("name").addText(ds.get(j).getUserName());
			}
		}
		//实例化输出格式对象
		OutputFormat out=OutputFormat.createCompactFormat();
		//设置字符集
		out.setEncoding("utf-8");
		try {
			XMLWriter writer=new XMLWriter(new FileOutputStream(new File("base/student.xml")),out);
			try {
				writer.write(document);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateOwnInfo(User u) {
		while(true) {
		System.out.println("请输入你需要维护的信息:(姓名，性别，密码,返回)");
		String choice=scan.next();
		switch(choice) {
		case "姓名":
			System.out.println("请输入修改后的姓名:");
			String name=scan.next();
			if(name.matches("[\\u4e00-\\u9fa5]{2,3}")) {
				u.setName(name);
				UserService service=new UserService();
				service.updateUser(u);
				CourseDetailService ss=new CourseDetailService();
				CourseDetail dd=new CourseDetail();
				dd.setUserName(name);
				dd.setUserId(u.getId());
				ss.updateGrade(dd);
				System.out.println("修改完成!");
			}
			else {
				System.out.println("你输入的姓名不合法，请重新选择!");
			}
			break;
		case "性别":
			System.out.println("请输入修改后的性别(男或女):");
			String sex=scan.next();
			if("男".equals(sex)||"女".equals(sex)) {
				u.setSex(sex);
				UserService s=new UserService();
				s.updateUser(u);
			}
			else{
				System.out.println("请输入男或女!");
			}
			break;
		case "密码":
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
						+ "(密码长度不少于6个字符，至少有一个小写字母，至少有一个大写字母，至少一个数字)，修改失败");
			}
			else {
				UserService s=new UserService();
				u.setUserPwd(newPwd);
				s.updateUser(u);
				System.out.println("修改成功!");
			}
			}
			break;
		case "返回":break;
		default:System.out.println("你输入的选择无效，请重新输入!");break;
		}
		if("返回".equals(choice)) {
			break;
		}
	}
	}
	public static void exportGrade(User u) {
		CourseDetailService service=new CourseDetailService();
		CourseDetail detail=new CourseDetail();
		detail.setUserId(u.getId());
		List<CourseDetail> details=service.findCourseDetail(detail);
		while(true) {
			System.out.println("请选择导入的文件类型:");
			System.out.println("     1.导入到TXT");
			System.out.println("     2.导入到XML");
			System.out.println("     3.返回上一层");
			System.out.println("请输入你的选择(1-3)");
			int choice=scan.nextInt();
			switch(choice) {
			case 1:
				exportGradeToTxt(details);
				System.out.println("写入完毕!");
				break;
			case 2:
				exportGradeToXml(details);
				System.out.println("写入完毕!");
				break;
			case 3:break;
			}
			if(choice==3) {
				break;//退出该层循环
			}
			}
	}
	public static void exportGradeToTxt(List<CourseDetail> details) {
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("base/score.txt")));
			for(int i=0;i<details.size();i++) {
				CourseDetail d=new CourseDetail();
				d.setCourseId(details.get(i).getCourseId());
				d.setRole("学生");
				CourseDetailService service=new CourseDetailService();
				List<CourseDetail> ds=service.findCourseDetail(d);
				bw.write(details.get(i).getCourseName()+"学生成绩名单如下:");
				bw.write("学号"+"\t"+"姓名"+"\t"+"成绩");
				bw.newLine();
				for(int j=0;j<ds.size();j++) {
					CourseDetail w=ds.get(j);
					bw.write(w.getUserId()+"\t"+w.getCourseName()+"\t"+w.getGrade());
					bw.newLine();
				}
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void exportGradeToXml(List<CourseDetail> details) {
		Document document=DocumentHelper.createDocument();
		//创建根节点courses
		Element root=document.addElement("courses");
		for(int i=0;i<details.size();i++) {
			Element course=root.addElement(details.get(i).getCourseName());
			CourseDetail d=new CourseDetail();
			d.setCourseId(details.get(i).getCourseId());
			d.setRole("学生");
			CourseDetailService service=new CourseDetailService();
			List<CourseDetail> ds=service.findCourseDetail(d);
			for(int j=0;j<ds.size();j++) {
				Element el=course.addElement("student");
				el.addElement("id").addText(ds.get(j).getUserId());
				el.addElement("name").addText(ds.get(j).getUserName());
				el.addElement("grade").addText(ds.get(j).getGrade()+"");
			}
		}
		//实例化输出格式对象
		OutputFormat out=OutputFormat.createCompactFormat();
		//设置字符集
		out.setEncoding("utf-8");
		try {
			XMLWriter writer=new XMLWriter(new FileOutputStream(new File("base/score.xml")),out);
			try {
				writer.write(document);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
