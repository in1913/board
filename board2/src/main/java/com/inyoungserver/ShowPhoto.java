package com.inyoungserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.MemberDDL;


public class ShowPhoto extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userid = req.getParameter("userid");
		String fileName = MemberDDL.selectPhoto(userid);
		
		if(fileName != null) {
			File file = new File(fileName);
			
			if(file.getName().endsWith(".svg")) {
				System.out.println("svg 파일입니다.");
				res.setContentType("image/svg+xml");
				byte [] buffer = new byte[1024];
				ServletOutputStream so = res.getOutputStream();
				
				String dir = req.getSession().getServletContext().getRealPath("./upload/users");

				try {
					String img = dir + "/" + fileName;
					
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(img));
					int i = 0;
					while((i = in.read(buffer, 0, 1024)) != -1) {
						so.write(buffer, 0, i);
					}
					so.close();
					in.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}

			}else {
				res.setContentType("image/jpeg");
				byte [] buffer = new byte[1024];
				ServletOutputStream so = res.getOutputStream();
				
				String dir = req.getSession().getServletContext().getRealPath("./upload/users");

				try {
					String img = dir + "/" + fileName;
					
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(img));
					int i = 0;
					while((i = in.read(buffer, 0, 1024)) != -1) {
						so.write(buffer, 0, i);
					}
					so.close();
					in.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}

			}
			
		}

	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

}
