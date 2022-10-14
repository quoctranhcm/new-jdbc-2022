package com.tranvanquoc.controller.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.jsp.PageContext;

import com.tranvanquoc.constant.SystemConstant;
import com.tranvanquoc.model.CategoryModel;
import com.tranvanquoc.model.NewModel;
import com.tranvanquoc.model.UserModel;
import com.tranvanquoc.paging.PageRequest;
import com.tranvanquoc.paging.Pageble;
import com.tranvanquoc.service.ICategoryService;
import com.tranvanquoc.service.INewService;
import com.tranvanquoc.service.IUserService;
import com.tranvanquoc.sort.Sorter;
import com.tranvanquoc.utils.FormUtil;
import com.tranvanquoc.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap","/thoat" })
public class HomeController extends HttpServlet {
//	@Inject 
//	private  ICategoryService categoryService;
	@Inject
	private INewService newService;
	@Inject
	IUserService userService;
	
	@Inject
	ICategoryService categoryService;
	private static final long serialVersionUID = 1L;
	
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Long categoryId = 1L;
//		String title = "bài viết 4";
//		String content = "bài viết 4";
//		
//		NewModel newModel = new NewModel();
//		newModel.setTitle(title);
//		newModel.setContent(content);
//		newModel.setCategoryId(categoryId);
//		

//			newService.save(newModel);
//			req.setAttribute("news", newService.findByCategoryId(categoryId)  );
//			req.setAttribute("categories",categoryService.findAll());
		
		
		String action = req.getParameter("action");
		String categoryCode = req.getParameter("categoryCode");
		
		String idNewString = req.getParameter("idNew");

		if (action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if(message != null && alert !=null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			
			rd.forward(req, resp);
		}else if(action != null && action.equals("logout")){
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath()+"/trang-chu");
		
		}else if(idNewString != null) {
			Long idNew = Long.parseLong(idNewString);
			NewModel model = newService.findOne(idNew);
			Pageble pageble = new PageRequest(null, null, null);
			model.setListResult(newService.findAll(pageble));
			req.setAttribute(SystemConstant.MODEL, model);
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/item.jsp");
			rd.forward(req, resp);
		}else if(categoryCode!=null) {
			NewModel model = FormUtil.toModel(NewModel.class, req);
			CategoryModel categoryModel = categoryService.findOneByCode(categoryCode);
			List<NewModel> modelList = newService.findByCategoryId(categoryModel.getId());
			model.setListResult(modelList);
			req.setAttribute(SystemConstant.MODEL, model);
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, resp);
			
		}
		else {
			NewModel model = FormUtil.toModel(NewModel.class, req);
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), 
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newService.findAll(pageble));
			req.setAttribute(SystemConstant.MODEL, model);
	
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, req);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if(model!=null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", model);
				if(model.getRole().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath() +"/trang-chu");
				}else if(model.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath() +"/admin-home");
				}
			}else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		
		}
		
	}
	

}
