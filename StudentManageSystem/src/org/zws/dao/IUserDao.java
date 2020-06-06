package org.zws.dao;

import java.util.List;
import org.zws.domain.User;

public interface IUserDao {
	//����ָ�����ֶβ�ѯ����
	User findUserByIdAndPwd(User user);
	//������������ֶβ�ѯ�����Ը�����������ɫ���Ա���в�ѯ
	List<User> queryByAnyField(User user);
	void updateAnyField(User user);
	void insert(User u);
	void deleteUser(User user);
}
