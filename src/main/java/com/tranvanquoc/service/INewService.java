package com.tranvanquoc.service;

import java.util.List;

import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.paging.Pageble;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
	
	NewModel save(NewModel newModel);
	NewModel update(NewModel updateNews);
	void delete(long[] ids);
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
	NewModel findOne(long Id);
	
}
