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
			//只有当Role为空时才进入
			if(Role==null) {
				System.out.println("请输入你需要添加的角色:(学生,老师)");
				String role=scan.next();
		    	if("学生".equals(role)||"老师".equals(role)) {
		    		//如果输入的不为这三项请重新输入
		    		Role=role;
		    	}
		    	else {
		    		System.out.println("请输入正确的角色!");
		    		break;
		    	}
			}
			if(Id==null) {
				System.out.println("请输入该身份的学号:(10数字)");
				String id=scan.next();
				if(id.matches("\\d{10}")) {
					UserService service=new UserService();
					User u=new User();
					u.setId(id);
					if(service.checkLogin(u)==null) {
					Id=id;
					}
					else {
						System.out.println("你输入的学号已存在，请重新输入!");
						break;
					}
				}
				else {
					System.out.println("请输入正确格式的学号");
					break;
				}
			}
			if(Name==null) {
		System.out.println("请输入姓名:(真实姓名)");
		String name=scan.next();
		if(name.matches("[\\u4e00-\\u9fa5]{2,3}")) {
			Name=name;
		}
		else {
			System.out.println("请输入真实姓名");
			break;
		}
			}
			if(Sex==null) {
		System.out.println("请输入性别:(男/女)");
		String sex=scan.next();
		if("男".equals(sex)||"女".equals(sex)) {
			Sex=sex;
		}
		else {
			System.out.println("请输入男或女");
			break;
		}
			}
		//由管理设置的新用户，初始密码都为123456
			break;//输入完成跳出第一层循环
		}
		if(Role!=null&&Name!=null&&Id!=null&&Sex!=null) {
			break;//都输入完成且跳出第一层循环就跳出第二层循环
		}
	}
		UserService s=new UserService();
		//初始密码123456
		User u=new User(Id, Name, Sex, "123456", Role);
		s.insertUser(u);
		System.out.println("添加完毕!");
	}
	public static void updateUser() {
		//先把静态变量均变为null
		User user=null;
			while(true) {
				System.out.println("请输入需要修改用户的学号:");
				String id=scan.next();
				User u=new User();
				u.setId(id);
				UserService service=new UserService();
				user=service.checkLogin(u);
				if(user==null) {
					System.out.println("你输入的学号不存在!");
				}
				else {
					break;
				}
			}	
			while(true) {
			System.out.println("请输入你想修改的字段:(学号，姓名，性别，密码，角色，返回)");
			String choice=scan.next();
			switch(choice) {
			case "学号":
				System.out.println("请输入修改后的学号:");
				String id=scan.next();
				if(id.matches("\\d{10}")) {
					UserService service=new UserService();
					User u=new User();
					u.setId(id);
					if(service.checkLogin(u)!=null) {
						System.out.println("该学号已存在，修改失败!");
					}
					else {
						UserService s=new UserService();
						user.setId(id);
						s.updateUser(user);
						System.out.println("修改成功!");
					}
				}
				else {
					System.out.println("你输入的格式不正确,修改失败");
				}
				break;
			case "姓名":
				System.out.println("请输入修改后名字:");
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
					System.out.println("修改成功!");
				}
				else {
					System.out.println("名字不合法，修改失败!");
				}
				break;
			case "性别":
				System.out.println("请输入修改后的性别:");
				String sex=scan.next();
				if("男".equals(sex)||"女".equals(sex)) {
					user.setSex(sex);
					UserService s=new UserService();
					s.updateUser(user);
				}
				else{
					System.out.println("请输入男或女!");
				}
				break;
			case "密码":
				System.out.println("请输入当前用户的原密码:");
				String pwd=scan.next();
				if(!user.getUserPwd().equals(pwd)) {
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
					user.setUserPwd(newPwd);
					s.updateUser(user);
					System.out.println("修改成功!");
				}
				}
				break;
			case "角色":
				System.out.println("请输入修改后的角色(只能是老师或者学生)");
				String role=scan.next();
				if("学生".equals(role)||"老师".equals(role)){
					user.setRole(role);
					UserService s=new UserService();
					s.updateUser(user);
					CourseDetailService ss=new CourseDetailService();
					CourseDetail dd=new CourseDetail();
					dd.setRole(role);
					dd.setUserId(user.getId());
					ss.updateGrade(dd);
					System.out.println("修改成功!");
				}
				else {
					System.out.println("请输入学生或者或者老师!");
				}
			case "返回":break;
			default:System.out.println("无效字段!");break;
			}
			if("返回".equals(choice)) {
				break;
			}
			}
	}
	public static void deleteUser() {
		String id=null;
		while(true) {
			id=scan.next();
		System.out.println("请输入你需要删除的用户学号:(只能删除学生或者老师，管理员不能删除!~)");
		UserService service=new UserService();
		User u=new User();
		u.setId(id);
		if(service.checkLogin(u)==null) {
			System.out.println("你输入的学号不存在!");
		}
		else {
			break;
		}
		}
		UserService s=new UserService();
		User user=new User();
		s.deleteUser(user);
		System.out.println("删除完毕!");
	}
}
