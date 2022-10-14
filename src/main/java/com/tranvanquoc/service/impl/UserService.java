package com.tranvanquoc.service.impl;

import javax.inject.Inject;

import com.tranvanquoc.dao.IUserDao;
import com.tranvanquoc.model.UserModel;
import com.tranvanquoc.service.IUserService;

public class UserService implements IUserService{

	@Inject 
	IUserDao userDao;
	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDao.findByUserNameAndPasswordAndStatus(userName, password, status);
	}
	
	

}
