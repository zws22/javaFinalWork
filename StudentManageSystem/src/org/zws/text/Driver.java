package org.zws.text;

import org.zws.domain.User;

public class Driver {
	public static void main(String[] args) {
		System.out.println("��ӭ����ѧ������ϵͳ");
		User u=Login.inputIdAndPwd();
		if(u!=null) {
			Menu.showMenu(u);
			}
		else {
			System.out.println("���ֻ�ܳ���3�Σ����ѱ�ǿ���˳���");
		}
	}
}
