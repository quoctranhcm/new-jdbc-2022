package com.tranvanquoc.service;

import com.tranvanquoc.model.UserModel;

public interface IUserService {
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
