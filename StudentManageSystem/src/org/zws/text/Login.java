package org.zws.text;

import java.util.Scanner;

import org.zws.domain.User;
import org.zws.service.UserService;

public class Login {
	static Scanner scan=new Scanner(System.in);
	public static User inputIdAndPwd(){
		int count=0;//����һ��������
		while(true) {
		System.out.println("�������û���:(ѧ�Ż���admin)");
		String id=scan.next();
		System.out.println("����������:");
		String userPwd=scan.next();
			//ÿ��ʹ�÷���㣬����Ҫ����ʵ����һ��service������Ϊ����һ�κ��session���󱻹ر���
		UserService service=new UserService();
		User user=new User();
		user.setId(id);
		user.setUserPwd(userPwd);
		User u=service.checkLogin(user);
		if(u!=null) {
			return u;
		}
		else {
			count++;
			System.out.println("��������û����������������������!");
			if(count==3) {
				return null;
			}
		}
	}
}
}
