package com.tranvanquoc.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tranvanquoc.constant.SystemConstant;
import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.paging.PageRequest;
import com.tranvanquoc.paging.Pageble;
import com.tranvanquoc.service.ICategoryService;
import com.tranvanquoc.service.INewService;
import com.tranvanquoc.sort.Sorter;
import com.tranvanquoc.utils.FormUtil;


@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private INewService newService;
	@Inject
	private ICategoryService categoryService;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String pageStr = req.getParameter("page");
//		String maxPageItemStr = req.getParameter("maxPageItem");
//		if(pageStr != null) {
//			model.setPage(Integer.parseInt(pageStr));
//		}else {
//			model.setPage(1);
//		}
//		if(maxPageItemStr !=null) {
//			model.setMaxPageItem(Integer.parseInt(maxPageItemStr));
//		}
		
		String message = req.getParameter("message");
		String alert = req.getParameter("alert");
		if(message != null && alert !=null) {
			req.setAttribute("message", resourceBundle.getString(message));
			req.setAttribute("alert", alert);
		}
		
		NewModel model = FormUtil.toModel(NewModel.class, req);
		String view ="";
		if(model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), 
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view ="views/admin/new/list.jsp";
			
		}else if(model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId()!=null) {
				model = newService.findOne(model.getId());
				
			}
			
			req.setAttribute("categories", categoryService.findAll());
			view = "views/admin/new/edit.jsp";
			
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
