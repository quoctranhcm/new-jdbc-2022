package com.tranvanquoc.dao;

import java.util.List;

import com.tranvanquoc.model.CategoryModel;

public interface ICategoryDao {
	List<CategoryModel> findAll();
	CategoryModel findOne(long id);
	CategoryModel findOneByCode(String code);
}
