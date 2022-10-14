package com.tranvanquoc.dao.impl;

import java.util.List;

import com.tranvanquoc.dao.IUserDao;
import com.tranvanquoc.mapper.UserMapper;
import com.tranvanquoc.model.UserModel;

public class UserDao extends AbstractDAO<UserModel> implements IUserDao{

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		String pass = password;
		StringBuilder sql =  new StringBuilder(" SELECT * FROM user As u ");
		sql.append(" INNER JOIN role As r ON r.id = u.roleid ");
		sql.append(" WHERE username = ? AND password = ? AND status = ? ");
		List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
		
	}


}
