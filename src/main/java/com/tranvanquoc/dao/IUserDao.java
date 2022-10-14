package com.tranvanquoc.dao;

import com.tranvanquoc.model.UserModel;

public interface IUserDao {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

}
