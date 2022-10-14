package com.tranvanquoc.utils;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tranvanquoc.constant.SystemConstant;

public class UploadImageUtil {
		public static void upLoad(HttpServletRequest req) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Parse the request
			try {
				List<FileItem> items = upload.parseRequest(req);
				for (FileItem item : items) {
					String fileName = item.getName();
					
					File uploadFile = new File(SystemConstant.DIR + "\\" + fileName);
					
					try {
						item.write(uploadFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
