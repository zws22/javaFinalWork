package org.zws.dao;

import java.util.List;
import org.zws.domain.User;

public interface IUserDao {
	//根据指定的字段查询单个
	User findUserByIdAndPwd(User user);
	//根据任意组合字段查询，可以根据姓名，角色，性别进行查询
	List<User> queryByAnyField(User user);
	void updateAnyField(User user);
	void insert(User u);
	void deleteUser(User user);
}
