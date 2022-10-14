package com.tranvanquoc.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.tranvanquoc.dao.ICategoryDao;
import com.tranvanquoc.dao.INewsDao;
import com.tranvanquoc.model.CategoryModel;
import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.paging.Pageble;
import com.tranvanquoc.service.INewService;

public class NewService implements INewService{
	@Inject
	private INewsDao newsDao;
	@Inject 
	private ICategoryDao categoryDao;

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return newsDao.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//		newModel.setCreatedBy("");
		CategoryModel categoryModel = categoryDao.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(categoryModel.getId());
		Long newsId = newsDao.save(newModel);
		return newsDao.findOne(newsId);
	}

	@Override
	public NewModel update(NewModel updateNews) {
		NewModel oldNews = newsDao.findOne(updateNews.getId());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDao.findOneByCode(updateNews.getCategoryCode());
		updateNews.setCategoryId(categoryModel.getId());
//		updateNews.setModifiedBy("");
		newsDao.update(updateNews);
		return newsDao.findOne(updateNews.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			newsDao.delete(id);
		}
		
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		return newsDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newsDao.getTotalItem();
	}

	@Override
	public NewModel findOne(long Id) {
		NewModel newModel = newsDao.findOne(Id);
		CategoryModel categoryModel = categoryDao.findOne(newModel.getCategoryId());
		newModel.setCategoryCode(categoryModel.getCode());
		return newModel;
	}

	

		

}
