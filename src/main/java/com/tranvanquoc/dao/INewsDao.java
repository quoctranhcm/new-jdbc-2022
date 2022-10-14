package com.tranvanquoc.dao;

import java.util.List;

import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.paging.Pageble;

public interface INewsDao {
	NewModel findOne(long id);
	List<NewModel> findByCategoryId(Long  categoryId);
	Long save(NewModel newModel);
	void update(NewModel updateNews);
	void delete(long id);
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();

}
