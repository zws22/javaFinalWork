package org.zws.text;

import java.util.Scanner;

public class AdminFunction {
	static Scanner scan=new Scanner(System.in);
	public static void showUserManageMenu() {
		System.out.println("*****用户管理*****");
		System.out.println("    1.增加用户信息");
		System.out.println("    2.修改用户信息");
		System.out.println("    3.删除用户信息");
		System.out.println("    4.返回上一层");
		System.out.println("请输入你的选择(1-4)");
	}
	public static void showCourseManageMenu() {
		System.out.println("*****课程管理*****");
		System.out.println("    1.增加课程信息");
		System.out.println("    2.修改课程信息");
		System.out.println("    3.删除课程信息");
		System.out.println("    4.返回上一层");
		System.out.println("请输入你的选择(1-4)");
	}
	public static void showChoice(int i) {
		while(true) {
			if(i==1) {
				showUserManageMenu();
				int choice=scan.nextInt();
				switch(choice) {
				case 1:UserManage.addUser();break;
				case 2:UserManage.updateUser();break;
				case 3:UserManage.deleteUser();break;
				case 4:break;
				default:System.out.println("你输入的数字选项无效,请重新输入!");break;
				}
				if(choice==4) {
					break;//退出该层循环，返回上一层
				}
			}
			else if(i==2) {
				showCourseManageMenu();
				int choice=scan.nextInt();
				switch(choice) {
				case 1:CourseManage.addCourse();break;
				case 2:CourseManage.updateCourse();break;
				case 3:CourseManage.deleteCourse();break;
				case 4:break;
				default:System.out.println("你输入的数字选项无效,请重新输入!");break;
				}
				if(choice==4) {
					break;//退出该层循环，返回上一层
				}
			}
		}
	}
}
