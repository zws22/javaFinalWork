package org.zws.text;

import java.util.Scanner;

import org.zws.domain.User;

public class Menu {
	static Scanner scan=new Scanner(System.in);
	public static void menu(String role,String name) {
		switch(role) {
		case "ѧ��":
			System.out.println("*******��ǰ�û�Ϊ:"+name+"\t"+"��ɫΪ:"+role+"*******");
			System.out.println("             1.��ѯ���пγ�");
			System.out.println("             2.ѡ��");
			System.out.println("             3.�ɼ���ѯ");
			System.out.println("             4.�޸�����");
			System.out.println("*******���������ѡ��(1-4)*******");
			break;
		case "��ʦ":
			System.out.println("*******��ǰ�û�Ϊ:"+name+"\t"+"��ɫΪ:"+role+"*******");
			System.out.println("             1.��ѯ���εĿγ���Ϣ");
			System.out.println("             2.�鿴�γ̵�ѡ������");
			System.out.println("             3.��������");
			System.out.println("             4.����γ̵ĳɼ�");
			System.out.println("             5.�ɼ�����");
			System.out.println("             6.������Ϣά��");
			System.out.println("*******���������ѡ��(1-6)*******");
			break;
		case "����Ա":
			System.out.println("*******��ǰ�û�Ϊ:"+name+"\t"+"��ɫΪ:"+role+"*******");
			System.out.println(              "1.�û�����"                            );
			System.out.println(              "2.�γ̹���"                             );
			System.out.println("*******���������ѡ��(1-2)*******");
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
		if("ѧ��".equals(u.getRole())) {
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
			default:System.out.println("�������������Ч!");break;
			}
		}
		else if("��ʦ".equals(u.getRole())) {
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
			default:System.out.println("�������������Ч!");break;
			}
		}
		else if("����Ա".equals(u.getRole())){
			switch(choice) {
			case 1:AdminFunction.showChoice(choice);break;
			case 2:AdminFunction.showChoice(choice);break;
				default:System.out.println("�������������Ч!");break;
			}
		}
	}
}
