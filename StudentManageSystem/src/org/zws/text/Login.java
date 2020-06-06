package org.zws.text;

import java.util.Scanner;

import org.zws.domain.User;
import org.zws.service.UserService;

public class Login {
	static Scanner scan=new Scanner(System.in);
	public static User inputIdAndPwd(){
		int count=0;//定义一个计数器
		while(true) {
		System.out.println("请输入用户名:(学号或者admin)");
		String id=scan.next();
		System.out.println("请输入密码:");
		String userPwd=scan.next();
			//每次使用服务层，都需要重新实例化一个service对象，因为调用一次后该session对象被关闭了
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
			System.out.println("你输入的用户名或密码错误，请重新输入!");
			if(count==3) {
				return null;
			}
		}
	}
}
}
