package org.zws.text;

import java.util.Scanner;

import org.zws.domain.User;

public class Menu {
	static Scanner scan=new Scanner(System.in);
	public static void menu(String role,String name) {
		switch(role) {
		case "学生":
			System.out.println("*******当前用户为:"+name+"\t"+"角色为:"+role+"*******");
			System.out.println("             1.查询所有课程");
			System.out.println("             2.选课");
			System.out.println("             3.成绩查询");
			System.out.println("             4.修改密码");
			System.out.println("*******请输入你的选择(1-4)*******");
			break;
		case "老师":
			System.out.println("*******当前用户为:"+name+"\t"+"角色为:"+role+"*******");
			System.out.println("             1.查询担任的课程信息");
			System.out.println("             2.查看课程的选课名单");
			System.out.println("             3.名单导出");
			System.out.println("             4.输入课程的成绩");
			System.out.println("             5.成绩导出");
			System.out.println("             6.个人信息维护");
			System.out.println("*******请输入你的选择(1-6)*******");
			break;
		case "管理员":
			System.out.println("*******当前用户为:"+name+"\t"+"角色为:"+role+"*******");
			System.out.println(              "1.用户管理"                            );
			System.out.println(              "2.课程管理"                             );
			System.out.println("*******请输入你的选择(1-2)*******");
		}
	}
	public static void showMenu(User u) {
		while(true) {
			menu(u.getRole(),u.getName());
			showChoice(u);
		}
	}
	public static void showChoice(User u) {
		int choice=scan.nextInt();
		if("学生".equals(u.getRole())) {
			switch(choice) {
			case 1:
				StudentFunction.showAllCourse();
				break; 
			case 2:
				StudentFunction.chooseCourse(u);
				break;
			case 3:
				StudentFunction.showGrade(u);
				break;
			case 4:
				StudentFunction.changePwd(u);
				break;
			default:System.out.println("你输入的数字无效!");break;
			}
		}
		else if("老师".equals(u.getRole())) {
			switch(choice) {
			case 1:
				TeacherFunction.showCourseByOwn(u);;
				break;
			case 2:
				TeacherFunction.showAllStudent(u);
				break;
			case 3:
				TeacherFunction.exportStudentList(u);
				break;
			case 4:
				TeacherFunction.importGrade(u);
				break;
			case 5:
				TeacherFunction.exportGrade(u);
				break;
			case 6:TeacherFunction.updateOwnInfo(u);
				break;
			default:System.out.println("你输入的数字无效!");break;
			}
		}
		else if("管理员".equals(u.getRole())){
			switch(choice) {
			case 1:AdminFunction.showChoice(choice);break;
			case 2:AdminFunction.showChoice(choice);break;
				default:System.out.println("你输入的数字无效!");break;
			}
		}
	}
}
