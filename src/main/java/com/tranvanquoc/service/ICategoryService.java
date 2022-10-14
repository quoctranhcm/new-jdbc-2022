package com.tranvanquoc.service;

import java.util.List;

import com.tranvanquoc.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
	public CategoryModel findOneByCode(String code) ;
	
	

}
