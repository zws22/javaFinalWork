package org.zws.service;

import org.apache.ibatis.session.SqlSession;
import org.zws.dao.IUserDao;
import org.zws.domain.User;
import org.zws.factory.DaoFactory;
import org.zws.util.GetSession;

public class UserService {
	private SqlSession session;
	public UserService() {
		this.session=GetSession.getSession();
	}
	public User checkLogin(User user) {
		IUserDao dao=DaoFactory.getNewInstanceIUserDao(session);
		User u=dao.findUserByIdAndPwd(user);
		//�ѹر�session�������service�п��Լ�����Դ��Ƶ�������͹رգ���֮ǰconnection����
		GetSession.closeSession(session);
		if(u!=null) {
			return u;
		}
		else {
			return null;
		}
	}
	public void changePwd(User user) {
		IUserDao dao=DaoFactory.getNewInstanceIUserDao(session);
		dao.updateAnyField(user);
		GetSession.closeSession(session);
	}
	public void insertUser(User user) {
		IUserDao dao=DaoFactory.getNewInstanceIUserDao(session);
		dao.insert(user);
		GetSession.closeSession(session);
	}
	public void deleteUser(User user) {
		IUserDao dao=DaoFactory.getNewInstanceIUserDao(session);
		dao.deleteUser(user);
		GetSession.closeSession(session);
	}
	public void updateUser(User user) {
		IUserDao dao=DaoFactory.getNewInstanceIUserDao(session);
		dao.updateAnyField(user);
		GetSession.closeSession(session);
	}
}
