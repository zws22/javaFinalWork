package org.zws.text;

import java.util.Scanner;

import org.zws.domain.CourseDetail;
import org.zws.domain.User;
import org.zws.service.CourseDetailService;
import org.zws.service.UserService;

public class UserManage {
	static Scanner scan=new Scanner(System.in);
	static String Role;
	static String Id;
	static String Name;
	static String Sex;
	public static void addUser() {
		while(true) {
		while(true) {
			//ֻ�е�RoleΪ��ʱ�Ž���
			if(Role==null) {
				System.out.println("����������Ҫ��ӵĽ�ɫ:(ѧ��,��ʦ)");
				String role=scan.next();
		    	if("ѧ��".equals(role)||"��ʦ".equals(role)) {
		    		//�������Ĳ�Ϊ����������������
		    		Role=role;
		    	}
		    	else {
		    		System.out.println("��������ȷ�Ľ�ɫ!");
		    		break;
		    	}
			}
			if(Id==null) {
				System.out.println("���������ݵ�ѧ��:(10����)");
				String id=scan.next();
				if(id.matches("\\d{10}")) {
					UserService service=new UserService();
					User u=new User();
					u.setId(id);
					if(service.checkLogin(u)==null) {
					Id=id;
					}
					else {
						System.out.println("�������ѧ���Ѵ��ڣ�����������!");
						break;
					}
				}
				else {
					System.out.println("��������ȷ��ʽ��ѧ��");
					break;
				}
			}
			if(Name==null) {
		System.out.println("����������:(��ʵ����)");
		String name=scan.next();
		if(name.matches("[\\u4e00-\\u9fa5]{2,3}")) {
			Name=name;
		}
		else {
			System.out.println("��������ʵ����");
			break;
		}
			}
			if(Sex==null) {
		System.out.println("�������Ա�:(��/Ů)");
		String sex=scan.next();
		if("��".equals(sex)||"Ů".equals(sex)) {
			Sex=sex;
		}
		else {
			System.out.println("�������л�Ů");
			break;
		}
			}
		//�ɹ������õ����û�����ʼ���붼Ϊ123456
			break;//�������������һ��ѭ��
		}
		if(Role!=null&&Name!=null&&Id!=null&&Sex!=null) {
			break;//�����������������һ��ѭ���������ڶ���ѭ��
		}
	}
		UserService s=new UserService();
		//��ʼ����123456
		User u=new User(Id, Name, Sex, "123456", Role);
		s.insertUser(u);
		System.out.println("������!");
	}
	public static void updateUser() {
		//�ȰѾ�̬��������Ϊnull
		User user=null;
			while(true) {
				System.out.println("��������Ҫ�޸��û���ѧ��:");
				String id=scan.next();
				User u=new User();
				u.setId(id);
				UserService service=new UserService();
				user=service.checkLogin(u);
				if(user==null) {
					System.out.println("�������ѧ�Ų�����!");
				}
				else {
					break;
				}
			}	
			while(true) {
			System.out.println("�����������޸ĵ��ֶ�:(ѧ�ţ��������Ա����룬��ɫ������)");
			String choice=scan.next();
			switch(choice) {
			case "ѧ��":
				System.out.println("�������޸ĺ��ѧ��:");
				String id=scan.next();
				if(id.matches("\\d{10}")) {
					UserService service=new UserService();
					User u=new User();
					u.setId(id);
					if(service.checkLogin(u)!=null) {
						System.out.println("��ѧ���Ѵ��ڣ��޸�ʧ��!");
					}
					else {
						UserService s=new UserService();
						user.setId(id);
						s.updateUser(user);
						System.out.println("�޸ĳɹ�!");
					}
				}
				else {
					System.out.println("������ĸ�ʽ����ȷ,�޸�ʧ��");
				}
				break;
			case "����":
				System.out.println("�������޸ĺ�����:");
				String name=scan.next();
				if(name.matches("[\\u4e00-\\u9fa5]{2,3}")) {
					UserService s=new UserService();
					user.setName(name);
					s.updateUser(user);
					CourseDetailService ss=new CourseDetailService();
					CourseDetail dd=new CourseDetail();
					dd.setUserName(name);
					dd.setUserId(user.getId());
					ss.updateGrade(dd);
					System.out.println("�޸ĳɹ�!");
				}
				else {
					System.out.println("���ֲ��Ϸ����޸�ʧ��!");
				}
				break;
			case "�Ա�":
				System.out.println("�������޸ĺ���Ա�:");
				String sex=scan.next();
				if("��".equals(sex)||"Ů".equals(sex)) {
					user.setSex(sex);
					UserService s=new UserService();
					s.updateUser(user);
				}
				else{
					System.out.println("�������л�Ů!");
				}
				break;
			case "����":
				System.out.println("�����뵱ǰ�û���ԭ����:");
				String pwd=scan.next();
				if(!user.getUserPwd().equals(pwd)) {
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
					user.setUserPwd(newPwd);
					s.updateUser(user);
					System.out.println("�޸ĳɹ�!");
				}
				}
				break;
			case "��ɫ":
				System.out.println("�������޸ĺ�Ľ�ɫ(ֻ������ʦ����ѧ��)");
				String role=scan.next();
				if("ѧ��".equals(role)||"��ʦ".equals(role)){
					user.setRole(role);
					UserService s=new UserService();
					s.updateUser(user);
					CourseDetailService ss=new CourseDetailService();
					CourseDetail dd=new CourseDetail();
					dd.setRole(role);
					dd.setUserId(user.getId());
					ss.updateGrade(dd);
					System.out.println("�޸ĳɹ�!");
				}
				else {
					System.out.println("������ѧ�����߻�����ʦ!");
				}
			case "����":break;
			default:System.out.println("��Ч�ֶ�!");break;
			}
			if("����".equals(choice)) {
				break;
			}
			}
	}
	public static void deleteUser() {
		String id=null;
		while(true) {
			id=scan.next();
		System.out.println("����������Ҫɾ�����û�ѧ��:(ֻ��ɾ��ѧ��������ʦ������Ա����ɾ��!~)");
		UserService service=new UserService();
		User u=new User();
		u.setId(id);
		if(service.checkLogin(u)==null) {
			System.out.println("�������ѧ�Ų�����!");
		}
		else {
			break;
		}
		}
		UserService s=new UserService();
		User user=new User();
		s.deleteUser(user);
		System.out.println("ɾ�����!");
	}
}
