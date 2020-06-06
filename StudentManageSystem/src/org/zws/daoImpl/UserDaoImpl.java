package org.zws.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.zws.dao.IUserDao;
import org.zws.domain.User;
import org.zws.mapper.UserMapper;

public class UserDaoImpl implements IUserDao{
	private SqlSession session;
	public UserDaoImpl(SqlSession session) {
		this.session=session;
	}
	@Override
	public User findUserByIdAndPwd(User user) {
		// TODO Auto-generated method stub
		UserMapper mapper=session.getMapper(UserMapper.class);
		User u=mapper.findUserByUserByIdAndPwd(user);
		return u;
	}

	@Override
	public List<User> queryByAnyField(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateAnyField(User user) {
		// TODO Auto-generated method stub
		UserMapper mapper=session.getMapper(UserMapper.class);
		mapper.updateAnyField(user);
		session.commit();
	}
	public void insert(User u) {
		UserMapper mapper=session.getMapper(UserMapper.class);
		mapper.insertUser(u);
		session.commit();
	}
	public void deleteUser(User user) {
		UserMapper mapper=session.getMapper(UserMapper.class);
		mapper.deleteUser(user);
		session.commit();
	}
}
