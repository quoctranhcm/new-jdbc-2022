package com.tranvanquoc.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.management.loading.PrivateClassLoader;

import com.tranvanquoc.dao.ICategoryDao;
import com.tranvanquoc.dao.impl.CategoryDao;
import com.tranvanquoc.mapper.CategoryMapper;
import com.tranvanquoc.mapper.NewsMapper;
import com.tranvanquoc.model.CategoryModel;
import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.service.ICategoryService;

public class CategoryService implements ICategoryService {
	@Inject
	private ICategoryDao categoryDao;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		 return categoryDao.findOneByCode(code);
	}
	
	

}
