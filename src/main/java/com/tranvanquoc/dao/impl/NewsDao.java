package com.tranvanquoc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mysql.cj.xdevapi.Statement;
import com.tranvanquoc.dao.INewsDao;
import com.tranvanquoc.mapper.NewsMapper;
import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.paging.Pageble;

import javassist.bytecode.ClassFileWriter.MethodWriter;

public class NewsDao extends AbstractDAO<NewModel> implements INewsDao {

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM  news WHERE categoryid = ?";
		return query(sql, new NewsMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news"); 
		sql.append("(title, thumbnail, shortdescription, content, categoryid, createddate, createdby)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
				newModel.getContent(), newModel.getCategoryId(), newModel.getCreatedDate(), newModel.getCreatedBy());
	}

	@Override
	public NewModel findOne(long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewModel> news = query(sql, new NewsMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNews) {
		StringBuilder  sql = new StringBuilder("UPDATE news SET  title = ?, thumbnail = ?,");
		sql.append("shortdescription = ?, content =?, categoryid = ?, modifieddate = ?, modifiedby =?  WHERE id = ?");
		update(sql.toString(), updateNews.getTitle(), updateNews.getThumbnail(), updateNews.getShortDescription()
				, updateNews.getContent(), updateNews.getCategoryId(), updateNews.getModifiedDate(), updateNews.getModifiedBy(),
				updateNews.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
		
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
//		String sql = "SELECT * FROM news LIMIT ?, ?";
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if(pageble.getSorter()!=null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) &&StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() +" "+ pageble.getSorter().getSortBy());
		}
		if(pageble.getOffset()!= null && pageble.getLimit()!= null) {
			sql.append(" LIMIT " +pageble.getOffset() +", "+ pageble.getLimit());
		}
			return query(sql.toString(), new NewsMapper());
		
		
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM  news";
		return count(sql);
	}

	

	
}
