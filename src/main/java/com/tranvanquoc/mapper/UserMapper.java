package com.tranvanquoc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.model.RoleModel;
import com.tranvanquoc.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel userModel = new UserModel();
		try {
			userModel.setId(resultSet.getLong("id"));
			userModel.setUserName(resultSet.getString("username"));
			userModel.setFullName(resultSet.getString("fullname"));
			userModel.setPassword(resultSet.getString("password"));
			userModel.setStatus(resultSet.getInt("status"));
			
			try {
				RoleModel roleModel = new RoleModel();
				roleModel.setCode(resultSet.getString("code"));
				roleModel.setName(resultSet.getString("name"));
				userModel.setRole(roleModel);
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return userModel;
		} catch (SQLException e) {
			return null;
			
		}
		
	}

}
