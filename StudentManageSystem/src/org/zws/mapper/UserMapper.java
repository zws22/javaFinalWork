package org.zws.mapper;

import org.zws.domain.User;

public interface UserMapper {
	User findUserByUserByIdAndPwd(User user);
	void updateAnyField(User user);
	void insertUser(User u);
	void deleteUser(User user);
}
