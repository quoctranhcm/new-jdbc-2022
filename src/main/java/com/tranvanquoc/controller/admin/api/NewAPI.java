package com.tranvanquoc.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.model.UserModel;
import com.tranvanquoc.service.INewService;
import com.tranvanquoc.utils.HttpUtil;
import com.tranvanquoc.utils.SessionUtil;
@MultipartConfig
@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet{

	@Inject INewService newService;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		resp.setContentType("application/json");
		newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		newModel = newService.save(newModel);
		mapper.writeValue(resp.getOutputStream(), newModel);
		
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel updateNews = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		updateNews.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req,"USERMODEL")).getUserName());
		updateNews = newService.update(updateNews);
		mapper.writeValue(resp.getOutputStream(), updateNews);
		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(resp.getOutputStream(),"{}");
	}
}
