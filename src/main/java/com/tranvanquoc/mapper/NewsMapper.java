package com.tranvanquoc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tranvanquoc.model.NewModel;

public class NewsMapper implements RowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet resultSet) {
		NewModel newModel = new NewModel();
		try {
			newModel.setId(resultSet.getLong("id"));
			newModel.setTitle(resultSet.getString("title"));
			newModel.setContent(resultSet.getString("content"));
			newModel.setThumbnail(resultSet.getString("thumbnail"));
			newModel.setShortDescription(resultSet.getString("shortdescription"));
			newModel.setCategoryId(resultSet.getLong("categoryid"));
			newModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			newModel.setCreatedBy(resultSet.getString("createdby"));
			newModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			newModel.setModifiedBy(resultSet.getString("modifiedby"));
		
			return newModel;
		} catch (SQLException e) {
			return null;
			
		}
		
	}

}
