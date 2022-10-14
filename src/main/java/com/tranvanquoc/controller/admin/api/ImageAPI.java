package com.tranvanquoc.controller.admin.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tranvanquoc.constant.SystemConstant;

/**
 * Servlet implementation class ImageAPI
 */
@WebServlet(urlPatterns = { "/api-image" })
public class ImageAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		// Create a new file upload handler
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		// Parse the request
//		String uploadPath = getServletContext().getRealPath("") + File.separator +"upload";
//		File uploadDir = new File(uploadPath);
//		
//		// creates the directory if it does not exist
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//		try {
//			 
//			List<FileItem> items = upload.parseRequest(request);
//			for (FileItem item : items) {
//				 String fileName = new File(item.getName()).getName();
//                 String filePath = uploadPath + File.separator + fileName;
//                 File storeFile = new File(filePath);
//				
//				try {
//					item.write(storeFile);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				String fileName = item.getName();
				if (com.google.common.io.Files.getFileExtension(fileName).equals("png")
						|| com.google.common.io.Files.getFileExtension(fileName).equals("jpg")) {
					
					File uploadFile = new File(SystemConstant.DIR + "\\" + fileName);

					try {
						item.write(uploadFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
