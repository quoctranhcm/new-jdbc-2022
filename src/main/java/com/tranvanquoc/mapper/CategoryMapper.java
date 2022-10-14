package com.tranvanquoc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tranvanquoc.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		CategoryModel categoryModel = new CategoryModel();
		try {
			categoryModel.setCode(resultSet.getString("code"));
			categoryModel.setId(resultSet.getLong("id"));
			categoryModel.setName(resultSet.getString("name"));
			return categoryModel;

		} catch (SQLException e) {
			return null;
		}
	}
}
