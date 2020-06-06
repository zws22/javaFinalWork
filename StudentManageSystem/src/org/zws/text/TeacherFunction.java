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
		System.out.println("����:"+u.getName()+"\t"+"����"+details.size()+"�Ŵ�����ʦ");
		for(int i=0;i<details.size();i++) {
			CourseDetail d=details.get(i);
			CourseDetailService service2=new CourseDetailService();
			CourseDetail detail2=new CourseDetail();
			//���ݿγ̺źͽ�ɫȷ��ѡ��ÿγ̵�ѧ����
			detail2.setCourseId(d.getCourseId());
			detail2.setRole("ѧ��");
			List<CourseDetail> details2=service2.findCourseDetail(detail2);
			System.out.println("�γ�����:"+d.getCourseName()+"�ÿγ�����:"+details2.size());
		}
		System.out.println("�����κ���ĸ�������˵�");
		String exit=scan.next();
	}
	public static void showAllStudent(User u) {
		CourseDetailService service1=new CourseDetailService();
		CourseDetail detail1=new CourseDetail();
		detail1.setUserId(u.getId());
		List<CourseDetail> details=service1.findCourseDetail(detail1);
		for(int i=0;i<details.size();i++) {
			CourseDetail d=details.get(i);
			System.out.println("�γ�����:"+d.getCourseName()+"�γ̺�:"+d.getCourseId()+"\t"+"ѧ����������:");
			CourseDetailService service2=new CourseDetailService();
			CourseDetail detail2=new CourseDetail();
			//���ݿγ̺źͽ�ɫȷ��ѡ��ÿγ̵�ѧ����
			detail2.setCourseId(d.getCourseId());
			detail2.setRole("ѧ��");
			List<CourseDetail> details2=service2.findCourseDetail(detail2);
			for(int j=0;j<details2.size();j++) {
				System.out.println("ѧ��:"+details2.get(j).getUserId()+"\t"+"����:"+details.get(j).getUserName());
				System.out.println("������Ϊ:"+details2.size());
			}
		}
		System.out.println("�����κ���ĸ�������˵�");
		String exit=scan.next();
	}
	public static void importGrade(User u) {
		//չʾ�γ̿ζ�Ӧ��ѧ����Ϣ
		showAllStudent(u);
		System.out.println("����������Ҫ�ܽ�ɼ��Ŀγ̺�:");
		String courseId=scan.next();
		System.out.println("����������Ҫ�ܽ�ɼ��Ŀγ�����:");
		String courseName=scan.next();
		CourseDetailService service=new CourseDetailService();
		CourseDetail detail=new CourseDetail();
		detail.setCourseId(courseId);
		detail.setCourseName(courseName);
		detail.setRole("ѧ��");
		if(service.findCourseDetail(detail).size()!=0) {
			System.out.println("�����뵼��ѧ��ѧ��:");
			String userId=scan.next();
			System.out.println("������ѧ������:");
			String userName=scan.next();
			CourseDetailService service1=new CourseDetailService();
			CourseDetail detail1=new CourseDetail();
			detail1.setUserId(userId);
			detail1.setUserName(userName);
			//��������ѧ��������ȷ����ѡ��
			if(service1.findCourseDetail(detail1).size()!=0) {
				CourseDetailService service2=new CourseDetailService();
				CourseDetail detail2=new CourseDetail();
				detail2.setId(service1.findCourseDetail(detail1).get(0).getId());
				detail2.setCourseId(courseId);
				detail2.setCourseName(courseName);
				detail2.setRole("ѧ��");
				detail2.setUserId(userId);
				detail2.setUserName(userName);
				System.out.println("������ɼ�");
				BigDecimal grade=scan.nextBigDecimal();
				detail2.setGrade(grade);
				service2.updateGrade(detail2);
				System.out.println("¼�����!");
			}
			else{
				System.out.println("�������ѧ����������ѧ�Ų����ڣ�û�и�ѧ������Ϣ!");
			}
		}
		else {
			System.out.println("�鲻��ָ������!");
		}
	}
	public static void exportStudentList(User u) {
		CourseDetailService service=new CourseDetailService();
		CourseDetail detail=new CourseDetail();
		detail.setUserId(u.getId());
		List<CourseDetail> details=service.findCourseDetail(detail);
		while(true) {
		System.out.println("��ѡ������ļ�����:");
		System.out.println("     1.���뵽TXT");
		System.out.println("     2.���뵽XML");
		System.out.println("     3.������һ��");
		System.out.println("���������ѡ��(1-3)");
		int choice=scan.nextInt();
		switch(choice) {
		case 1:
			exportToTxt(details);
			System.out.println("д�����!");
			break;
		case 2:
			exportToXml(details);
			System.out.println("д�����!");
			break;
		case 3:break;
		}
		if(choice==3) {
			break;//�˳��ò�ѭ��
		}
		}
		
	}
	public static void exportToTxt(List<CourseDetail> details) {
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("base/student.txt")));
			for(int i=0;i<details.size();i++) {
				CourseDetail d=new CourseDetail();
				d.setCourseId(details.get(i).getCourseId());
				d.setRole("ѧ��");
				CourseDetailService service=new CourseDetailService();
				List<CourseDetail> ds=service.findCourseDetail(d);
				bw.write(details.get(i).getCourseName()+"ѧ����������:");
				bw.write("ѧ��"+"\t"+"����");
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
		//�������ڵ�courses
		Element root=document.addElement("courses");
		for(int i=0;i<details.size();i++) {
			Element course=root.addElement(details.get(i).getCourseName());
			CourseDetail d=new CourseDetail();
			d.setCourseId(details.get(i).getCourseId());
			d.setRole("ѧ��");
			CourseDetailService service=new CourseDetailService();
			List<CourseDetail> ds=service.findCourseDetail(d);
			for(int j=0;j<ds.size();j++) {
				Element el=course.addElement("student");
				el.addElement("id").addText(ds.get(j).getUserId());
				el.addElement("name").addText(ds.get(j).getUserName());
			}
		}
		//ʵ���������ʽ����
		OutputFormat out=OutputFormat.createCompactFormat();
		//�����ַ���
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
		System.out.println("����������Ҫά������Ϣ:(�������Ա�����,����)");
		String choice=scan.next();
		switch(choice) {
		case "����":
			System.out.println("�������޸ĺ������:");
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
				System.out.println("�޸����!");
			}
			else {
				System.out.println("��������������Ϸ���������ѡ��!");
			}
			break;
		case "�Ա�":
			System.out.println("�������޸ĺ���Ա�(�л�Ů):");
			String sex=scan.next();
			if("��".equals(sex)||"Ů".equals(sex)) {
				u.setSex(sex);
				UserService s=new UserService();
				s.updateUser(u);
			}
			else{
				System.out.println("�������л�Ů!");
			}
			break;
		case "����":
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
						+ "(���볤�Ȳ�����6���ַ���������һ��Сд��ĸ��������һ����д��ĸ������һ������)���޸�ʧ��");
			}
			else {
				UserService s=new UserService();
				u.setUserPwd(newPwd);
				s.updateUser(u);
				System.out.println("�޸ĳɹ�!");
			}
			}
			break;
		case "����":break;
		default:System.out.println("�������ѡ����Ч������������!");break;
		}
		if("����".equals(choice)) {
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
			System.out.println("��ѡ������ļ�����:");
			System.out.println("     1.���뵽TXT");
			System.out.println("     2.���뵽XML");
			System.out.println("     3.������һ��");
			System.out.println("���������ѡ��(1-3)");
			int choice=scan.nextInt();
			switch(choice) {
			case 1:
				exportGradeToTxt(details);
				System.out.println("д�����!");
				break;
			case 2:
				exportGradeToXml(details);
				System.out.println("д�����!");
				break;
			case 3:break;
			}
			if(choice==3) {
				break;//�˳��ò�ѭ��
			}
			}
	}
	public static void exportGradeToTxt(List<CourseDetail> details) {
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("base/score.txt")));
			for(int i=0;i<details.size();i++) {
				CourseDetail d=new CourseDetail();
				d.setCourseId(details.get(i).getCourseId());
				d.setRole("ѧ��");
				CourseDetailService service=new CourseDetailService();
				List<CourseDetail> ds=service.findCourseDetail(d);
				bw.write(details.get(i).getCourseName()+"ѧ���ɼ���������:");
				bw.write("ѧ��"+"\t"+"����"+"\t"+"�ɼ�");
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
		//�������ڵ�courses
		Element root=document.addElement("courses");
		for(int i=0;i<details.size();i++) {
			Element course=root.addElement(details.get(i).getCourseName());
			CourseDetail d=new CourseDetail();
			d.setCourseId(details.get(i).getCourseId());
			d.setRole("ѧ��");
			CourseDetailService service=new CourseDetailService();
			List<CourseDetail> ds=service.findCourseDetail(d);
			for(int j=0;j<ds.size();j++) {
				Element el=course.addElement("student");
				el.addElement("id").addText(ds.get(j).getUserId());
				el.addElement("name").addText(ds.get(j).getUserName());
				el.addElement("grade").addText(ds.get(j).getGrade()+"");
			}
		}
		//ʵ���������ʽ����
		OutputFormat out=OutputFormat.createCompactFormat();
		//�����ַ���
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
