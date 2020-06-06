package org.zws.text;

import org.zws.domain.User;

public class Driver {
	public static void main(String[] args) {
		System.out.println("欢迎进入学生管理系统");
		User u=Login.inputIdAndPwd();
		if(u!=null) {
			Menu.showMenu(u);
			}
		else {
			System.out.println("最多只能尝试3次，你已被强制退出了");
		}
	}
}
