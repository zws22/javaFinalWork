package org.zws.text;

import java.util.Scanner;

public class AdminFunction {
	static Scanner scan=new Scanner(System.in);
	public static void showUserManageMenu() {
		System.out.println("*****�û�����*****");
		System.out.println("    1.�����û���Ϣ");
		System.out.println("    2.�޸��û���Ϣ");
		System.out.println("    3.ɾ���û���Ϣ");
		System.out.println("    4.������һ��");
		System.out.println("���������ѡ��(1-4)");
	}
	public static void showCourseManageMenu() {
		System.out.println("*****�γ̹���*****");
		System.out.println("    1.���ӿγ���Ϣ");
		System.out.println("    2.�޸Ŀγ���Ϣ");
		System.out.println("    3.ɾ���γ���Ϣ");
		System.out.println("    4.������һ��");
		System.out.println("���������ѡ��(1-4)");
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
				default:System.out.println("�����������ѡ����Ч,����������!");break;
				}
				if(choice==4) {
					break;//�˳��ò�ѭ����������һ��
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
				default:System.out.println("�����������ѡ����Ч,����������!");break;
				}
				if(choice==4) {
					break;//�˳��ò�ѭ����������һ��
				}
			}
		}
	}
}
