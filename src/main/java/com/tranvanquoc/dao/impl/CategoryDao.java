package com.tranvanquoc.dao.impl;

import java.util.List;

import com.tranvanquoc.dao.ICategoryDao;
import com.tranvanquoc.mapper.CategoryMapper;
import com.tranvanquoc.mapper.NewsMapper;
import com.tranvanquoc.model.CategoryModel;
import com.tranvanquoc.model.NewModel;

public class CategoryDao extends AbstractDAO<CategoryModel> implements ICategoryDao{

	
	
	@Override
	public List<CategoryModel> findAll() {
		String sql ="SELECT * FROM  category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql = "SELECT * FROM category WHERE id = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), id);
		return category.isEmpty() ? null : category.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), code);
		return category.isEmpty() ? null : category.get(0);
	}
}
